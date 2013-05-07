package org.bflow.toolbox.addons.preferences;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import org.apache.commons.io.FileUtils;
import org.bflow.toolbox.addons.AddonPlugin;
import org.bflow.toolbox.addons.validation.Rule;
import org.bflow.toolbox.addons.validation.ValidationService;
import org.bflow.toolbox.i18n.MessageProvider;
import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.osgi.service.prefs.Preferences;

/**
 * Defines the validation preferences for the add-on plug-in page used by the
 * eclipse preferences dialog.
 * 
 * @author Arian Storch
 * @since 29/03/11
 * @version 24/05/11
 * 
 */
public class ValidationPage extends FieldEditorPreferencePage implements
		IWorkbenchPreferencePage {

	private Tree rulesTree;
	private Text descriptionText;
	private Link link;
	private Label imageLabel;
	private Composite top;
	private Image ruleImg;

	private Preferences prefStore;
	// private String diagramType;
	private String imgPackage;

	private boolean validationRulesSet;

	private HashMap<String, TreeItem> treeMenuItems = new HashMap<String, TreeItem>();
	private HashMap<String, TreeItem> backPutItems = new HashMap<String, TreeItem>();
	private HashMap<TreeItem, Rule> installedItems = new HashMap<TreeItem, Rule>();

	private List<Rule> installedRules;

	@Override
	protected void createFieldEditors() {
	}

	@Override
	public void init(IWorkbench workbench) {
		String abbr = MessageProvider.getActiveLanguageAbbreviation();

		installedRules = ValidationService.getInstance().getRules(abbr);

		Assert.isNotNull(installedRules, "Could not found validation rules");

		this.prefStore = AddonPlugin.getInstance().getPreferencesStore(); //(ScopedPreferenceStore) MitammPlugin.getDefault()
				//.getPreferenceStore();
		//this.setPreferenceStore(prefStore);

		validationRulesSet = prefStore.getBoolean("validationRulesSet", false);

		//setUpDefaultValues();
	}

	@Override
	public boolean performOk() {
		// save state within the preferences store
		for (TreeItem item : installedItems.keySet())
			prefStore.putBoolean(installedItems.get(item).getId(), item
					.getChecked());

		prefStore.putBoolean("validationRulesSet", true);

		// this.runValidation();

		return super.performOk();
	}

	@Override
	protected void performDefaults() {
		for (TreeItem item : installedItems.keySet()) {
			Rule rule = installedItems.get(item);
			item.setChecked(rule.isDfault());

			// clear user error messages
			prefStore.remove(rule.getId() + "Message");
		}

		super.performDefaults();
	}

	@Override
	protected Control createContents(Composite parent) {
		top = new Composite(parent, SWT.NONE);
		top.setLayout(new GridLayout());

		// Regelbaum
		rulesTree = new Tree(top, SWT.CHECK | SWT.BORDER);
		GridData data = new GridData(GridData.FILL_BOTH);
		rulesTree.setLayoutData(data);

		fillTree(rulesTree, getRuleClasses());

		ruleImg = new Image(top.getDisplay(), 120, 120);

		// Bild
		imageLabel = new Label(top, SWT.BORDER);
		imageLabel.setImage(ruleImg);
		imageLabel.setAlignment(SWT.CENTER);

		// Beschreibung
		descriptionText = new Text(top, SWT.WRAP | SWT.MULTI | SWT.BORDER
				| SWT.H_SCROLL | SWT.V_SCROLL);

		GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL
				| GridData.VERTICAL_ALIGN_FILL);

		gridData.horizontalSpan = 3;
		gridData.grabExcessVerticalSpace = true;

		descriptionText.setLayoutData(gridData);
		descriptionText.setEditable(false);

		link = new Link(top, SWT.NONE);
		link.setText("URL:");
		link.setSize(400, 100);

		link.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					URL url = new URL(e.text);
					PlatformUI.getWorkbench().getBrowserSupport()
							.createBrowser("validation browser").openURL(url);
				} catch (MalformedURLException e1) {
					e1.printStackTrace();
				} catch (PartInitException e1) {
					e1.printStackTrace();
				}
			}
		});

		rulesTree.addSelectionListener(

		new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				fillDescription();
				fillImage();

			}
		});

		rulesTree.addListener(SWT.Selection, new Listener() {

			@Override
			public void handleEvent(Event event) {
				if (event.detail == SWT.CHECK)
					checkChildren(event);
			}
		});

		// Beschreibungänderungsbutton

		Composite btnPane = new Composite(top, SWT.NONE);
		btnPane.setLayout(new GridLayout(3, false));

		GridData grdDataBtns = new GridData(SWT.LEAD, SWT.TOP, true, false);
		grdDataBtns.minimumWidth = 80;

		Button btnEditText = new Button(btnPane, SWT.NONE);

		// btnEditText.setText(MessageProvider.getMessage("AbstractDiagramValidationPreferencesPage#msg2")+"...");
		btnEditText.setText("Edit...");
		btnEditText.setLayoutData(grdDataBtns);
		btnEditText.setEnabled(true);

		btnEditText.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Rule rule = getSelectedRule();

				String prefMsg = "";

				if (prefStore.get(rule.getId() + "Message", null) != null)
					prefMsg = prefStore.get(rule.getId() + "Message", null);
				else
					prefMsg = rule.getMessage();

				InputDialog dlg = new InputDialog(
						top.getShell(),
						MessageProvider
								.getMessage("AbstractDiagramValidationPreferencesPage#msg2"),
						MessageProvider
								.getMessage("AbstractDiagramValidationPreferencesPage#msg3"),
						prefMsg, null);

				if (dlg.open() == InputDialog.OK) {
					prefStore.put(rule.getId() + "Message", dlg.getValue());

					fillDescription();
				} else
					return;
			}
		});

		Button btnExport = new Button(btnPane, SWT.NONE);
		btnExport.setText("Export");
		btnExport.setLayoutData(grdDataBtns);
		
		btnExport.addSelectionListener(new SelectionAdapter(){
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog fd = new FileDialog(getShell(), SWT.SAVE);
				
				fd.setOverwrite(true);
				fd.setFilterExtensions(new String[] {"*.ars"});
				fd.setFilterNames(new String[] {"Add-ons rule set (*.ars)"});
				
				String filename = fd.open();
				
				if(filename == null)
					return ;
				
				if(!filename.endsWith(".ars"))
					filename += ".ars";
				
				File file = new File(filename);
				List<String> lines = new ArrayList<String>();
				
				for(TreeItem item:installedItems.keySet()) {
					if(item.getChecked()) {
						lines.add(installedItems.get(item).getId());
					}
				}
				
				try {
					FileUtils.writeLines(file, lines);
				} catch (IOException e1) {
					MessageDialog.openError(getShell(), "Error", "Export process went wrong.\n\r"
							+e1.getLocalizedMessage());
				}
				
				MessageDialog.openInformation(getShell(), "Finished", "Export finished.");
				
			}
		});

		Button btnImport = new Button(btnPane, SWT.NONE);
		btnImport.setText("Import");
		btnImport.setLayoutData(grdDataBtns);
		
		btnImport.addSelectionListener(new SelectionAdapter() {
			@SuppressWarnings("unchecked")
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog fd = new FileDialog(getShell(), SWT.OPEN);
				
				fd.setFilterExtensions(new String[] {"*.ars"});
				fd.setFilterNames(new String[] {"Add-ons rule set (*.ars)"});
				
				String filename = fd.open();
				
				if(filename == null)
					return ;
				
				List<String> lines;
				
				try {
					lines = FileUtils.readLines(new File(filename));
				} catch (IOException e1) {
					MessageDialog.openError(getShell(), "Error", "Import process went wrong.\n\r"
							+e1.getLocalizedMessage());
					return ;
				}
				
				boolean overwrite = MessageDialog.openConfirm(getShell(), "Decide import behavior", "Shall the loaded rule set" +
						" overwrite the existing one?\n\r(Choosing Cancel will lead to integration.)");
				
				for(TreeItem item:installedItems.keySet()) {
					Rule rule = installedItems.get(item);
					
					boolean set = false;
					
					for(String s:lines)
						if(s.equalsIgnoreCase(rule.getId())) {
							set = true;
							lines.remove(s);
							break;
						}
					
					if(set) {
						item.setChecked(true);
					} else {
						if(overwrite) {
							item.setChecked(false);
						}
					}
				}
				
				MessageDialog.openInformation(getShell(), "Finished", "Import finished.");
			}
		});

		return top;
	}

	/**
	 * Proofs the state of the child items and handles changes
	 * 
	 * @param item
	 * @param readonly
	 *            set true if you only want to update the layout
	 */
	private void checkChildren(TreeItem item, boolean readonly) {
		if (item == null)
			return;

		if (item.getItemCount() > 0 && !readonly)
			setSelectionState(item);

		TreeItem parent = item.getParentItem();

		if (parent == null)
			parent = item;

		/*
		 * es wird nach einen Kernknoten gesucht
		 */

		TreeItem coreNode = null;

		if (parent.getParentItem() != null) {
			TreeItem sp = parent;

			while (sp.getParentItem() != null)
				sp = sp.getParentItem();

			coreNode = sp;
		}

		boolean allChecked = true;
		boolean nonChecked = true;

		for (TreeItem otherChild : parent.getItems())
			if (!otherChild.getChecked())
				allChecked = false;
			else
				nonChecked = false;

		Font font = parent.getFont();
		FontData fontData = font.getFontData()[0];

		parent.setForeground(this.getShell().getDisplay().getSystemColor(
				SWT.COLOR_BLACK));

		if (allChecked) {
			parent.setChecked(true);

			fontData.setStyle(SWT.BOLD);
		} else {
			parent.setChecked(false);

			if (nonChecked)
				parent.setForeground(this.getShell().getDisplay()
						.getSystemColor(SWT.COLOR_GRAY));

			fontData.setStyle(SWT.NORMAL);
		}

		font = new Font(top.getDisplay(), fontData);
		parent.setFont(font);

		if (coreNode != null)
			checkChildren(coreNode, true);
	}

	/**
	 * Event-Handler; selektiert alle Kinder, wenn ein Elter selektiert ist
	 * 
	 * @param event
	 */
	private void checkChildren(Event event) {
		TreeItem item = (TreeItem) event.item;

		boolean b = item.getChecked();

		setChildItemsChecked(item, b);

		// if(item != null)
		// checkChildren(item, false);
	}

	private void setChildItemsChecked(TreeItem child, boolean state) {
		for (TreeItem item : child.getItems()) {
			setChildItemsChecked(item, state);
		}

		child.setChecked(state);
	}

	/**
	 * sets the state of items
	 * 
	 * @param item
	 */
	private void setSelectionState(TreeItem item) {
		if (item.getItemCount() > 0) // hat Kinder
			for (TreeItem child : item.getItems())
				setSelectionState(child);
		else // hat keine
		{
			boolean checked = item.getChecked();

			if (checked)
				return;
			else
				item.setChecked(true);
		}

	}

	/**
	 * Event-Handler; füllt das Bildfeld
	 */
	private void fillImage() {
		Rule rule = getSelectedRule();

		if (rule == null)
			return;

		if (rule.getImage().length() < 1)
			ruleImg = new Image(top.getDisplay(), this.getClass()
					.getResourceAsStream(imgPackage + "question.png"));
		else
			ruleImg = new Image(top.getDisplay(), this.getClass()
					.getResourceAsStream(imgPackage + rule.getImage()));

		ImageData imgData = ruleImg.getImageData();

		double fH = 120.0 / imgData.height;
		double fW = 120.0 / imgData.width;

		double f = (fH < fW ? fH : fW);

		if (f > 1)
			f = 1;

		int nWidth = (int) (imgData.width * f);
		int nHeight = (int) (imgData.height * f);

		ruleImg = new Image(top.getDisplay(), imgData.scaledTo(nWidth, nHeight));

		imageLabel.setImage(ruleImg);
	}

	/**
	 * Event handler; fills the description text
	 */
	private void fillDescription() {
		Rule rule = getSelectedRule();

		if (rule == null)
			return;

		String prefMsg = "";
		
		if (prefStore.get(rule.getId() + "Message", null) != null)
			prefMsg = prefStore.get(rule.getId() + "Message", null);
		else
			prefMsg = rule.getMessage();

		String urlMsg = "URL: <a>" + rule.getUrl() + "</a>";

		link.setText(urlMsg);
		link.setSize(400, 15);

		descriptionText
				.setText(rule.getDescription()
						+ "\n\r\n\r"
						+ MessageProvider
								.getMessage("AbstractDiagramValidationPreferencesPage#msg4")
						+ "\n\r" + prefMsg);
	}

	/**
	 * Fills the tree with the installed rules.
	 * 
	 * @param tree
	 *            to fill
	 */
	private void fillTree(Tree tree, String[] columns) {
		// Turn off drawing to avoid flicker
		tree.setRedraw(false);

		for (String column : columns) {
			String[] subColumns = column.split("/");

			if (subColumns.length > 1) {
				String parentColumn = subColumns[0];
				String itemName = subColumns[subColumns.length - 1];

				for (int i = 1; i < subColumns.length - 1; i++)
					parentColumn += "/" + subColumns[i];

				TreeItem parent = treeMenuItems.get(parentColumn);

				if (parent == null) {
					parent = new TreeItem(tree, SWT.NONE);
					parent.setText(parentColumn);

					treeMenuItems.put(parentColumn, parent);
				}

				TreeItem child = new TreeItem(parent, SWT.NONE);
				child.setText(itemName);

				treeMenuItems.put(column, child);
			} else {
				TreeItem item = new TreeItem(tree, SWT.NONE);
				item.setText(column);

				treeMenuItems.put(column, item);
			}
		}

		if (backPutItems.size() > 0)
			fillTree(tree, backPutItems.keySet().toArray(
					new String[backPutItems.size()]));

		for (String column : treeMenuItems.keySet())
			fillTreeItem(treeMenuItems.get(column), column);

		for (TreeItem installedItem : installedItems.keySet())
			checkChildren(installedItem, false);

		// Turn drawing back on!
		tree.setRedraw(true);
	}

	private void fillTreeItem(TreeItem item, String column) {
		if (validationRulesSet)
			if (prefStore.getInt(column, 0) == 1)
				item.setChecked(true);
			else
				item.setChecked(false);

		for (Rule rule : installedRules)
			if (rule.getClazz().equalsIgnoreCase(column)) {
				TreeItem subItem = new TreeItem(item, SWT.NONE);

				installedItems.put(subItem, rule);

				subItem.setText(rule.getName());

				if (validationRulesSet)
					subItem.setChecked(prefStore.getBoolean(rule.getId(), false));
				else
					subItem.setChecked(rule.isDfault());
			}
	}

	/**
	 * sets up the default values
	 */
	//private void setUpDefaultValues() {
		//for (Rule rule : installedRules)
			//prefStore.setDefault(rule.getId(), rule.isDfault());
	//}

	/**
	 * Returns the classes of the rules.
	 * <p/>
	 * E.g. {syntax, control flow, spelling,...}
	 * 
	 * @return classes of rules
	 */
	private String[] getRuleClasses() {
		Vector<String> clazzes = new Vector<String>();

		for (Rule rule : installedRules)
			if (!clazzes.contains(rule.getClazz()))
				clazzes.add(rule.getClazz());

		String array[] = clazzes.toArray(new String[] {});

		return array;
	}

	/**
	 * returns the rule that is selected in the tree
	 * <p/>
	 * if no item is selected <code>null</code> will be returned
	 * 
	 * @return selected rule or null
	 */
	private Rule getSelectedRule() {
		TreeItem[] selItems = rulesTree.getSelection();

		if (selItems.length < 1)
			return null;

		return installedItems.get(selItems[0]);
	}

}
