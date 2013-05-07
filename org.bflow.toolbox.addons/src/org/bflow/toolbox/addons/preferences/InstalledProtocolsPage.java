package org.bflow.toolbox.addons.preferences;

import java.io.File;
import java.io.FileWriter;
import java.util.Iterator;

import org.bflow.toolbox.addons.core.model.IComponent;
import org.bflow.toolbox.addons.interfaces.IToolRunComponent;
import org.bflow.toolbox.addons.preferences.dialogs.EditProtocolDialog;
import org.bflow.toolbox.addons.protocols.Standardprotocol;
import org.bflow.toolbox.addons.store.Key;
import org.bflow.toolbox.addons.store.ProtocolStore;
import org.bflow.toolbox.addons.store.ToolStore;
import org.bflow.toolbox.addons.utils.ProtocolDescriptor;
import org.bflow.toolbox.addons.utils.ToolDescriptor;
import org.bflow.toolbox.i18n.MessageProvider;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

/**
 * Defines the preferences page for the installed protocols.
 * 
 * @author Arian Storch
 * @since 13/04/10
 * @version 07/06/11
 */
public class InstalledProtocolsPage extends
		FieldEditorPreferencePage implements IWorkbenchPreferencePage {

	private TableViewer viewer;

	@Override
	protected void createFieldEditors() {
	}

	@Override
	public void init(IWorkbench workbench) {
	}

	@Override
	protected Control createContents(Composite parent) {
		final Composite composite = new Composite(parent, SWT.WRAP);

		composite.setLayout(new GridLayout(1, false));

		Label lblText = new Label(composite, SWT.NONE);
		lblText.setText(MessageProvider
				.getMessage("MitammPreferencesInstalledProtocolsPage#msg1"));

		Composite panel = new Composite(composite, SWT.NONE);
		panel.setLayout(new GridLayout(5, false));

		Button btnAdd = new Button(panel, SWT.NONE);
		btnAdd.setText(MessageProvider
				.getMessage("MitammPreferencesInstalledProtocolsPage#msg2"));

		btnAdd.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				Standardprotocol prot = new Standardprotocol();
				prot.setName("unnamed");
				prot.setDescription("");

				ProtocolDescriptor pd = new ProtocolDescriptor("", null, true);
				pd.setProtocol(prot);
				pd.setName("unnamed");

				EditProtocolDialog dlg = new EditProtocolDialog(composite
						.getShell(), pd);

				dlg.open();

				String name = prot.getName().replaceAll(" ", "");
				pd.setFile(new File(Key.KEY_PROTOCOLSTORE_PATH
						.getAbsolutePath()
						+ "/" + name + ".xml"));
				pd.setId("org.bflow.toolbox.mitamm.protocols.user." + name);
				pd.setName(prot.getName());

				pd.addDisplayName("default", prot.getName());
				pd.addDescriptionText("default", prot.getDescription(null));

				ProtocolStore.installProtocol(pd);
				viewer.add(pd);
			}
		});

		Button btnRemove = new Button(panel, SWT.NONE);
		btnRemove.setText(MessageProvider
				.getMessage("MitammPreferencesInstalledProtocolsPage#msg7"));

		btnRemove.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int index = viewer.getTable().getSelectionIndex();

				if (index == -1)
					return;

				ProtocolDescriptor pd = (ProtocolDescriptor) viewer
						.getElementAt(index);
				
				if(!pd.storable)
					return ;

				if (MessageDialog.openConfirm(composite.getShell(),
						"Protokoll löschen?", "Sind Sie sicher?")) {
					ProtocolStore.removeProtocol(pd);
					viewer.remove(pd);
				}
			}
		});

		Button btnEdit = new Button(panel, SWT.NONE);
		btnEdit.setText(MessageProvider
				.getMessage("MitammPreferencesInstalledProtocolsPage#msg8"));

		btnEdit.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int sel = viewer.getTable().getSelectionIndex();

				if (sel == -1)
					return;

				ProtocolDescriptor pd = (ProtocolDescriptor) viewer.getTable()
						.getItem(sel).getData();
				
				if(!pd.storable)
					return ;

				EditProtocolDialog dlg = new EditProtocolDialog(composite
						.getShell(), pd);

				dlg.open();
			}
		});

		Button btnExport = new Button(panel, SWT.NONE);
		btnExport.setText("Export...");

		btnExport.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int index = viewer.getTable().getSelectionIndex();

				if (index == -1)
					return;

				ProtocolDescriptor pd = (ProtocolDescriptor) viewer
						.getElementAt(index);

				FileDialog fd = new FileDialog(composite.getShell(), SWT.SAVE);

				fd.setFilterExtensions(new String[] { "*.xml", "*.*" });
				fd.setFileName(pd.getName());

				String path = null;

				if ((path = fd.open()) != null) {
					try {
						File src = pd.getFile();
						File tgt = new File(path);

						SAXReader reader = new SAXReader();

						Document doc = reader.read(src);

						Element root = doc.getRootElement();

						Standardprotocol sp = (Standardprotocol) pd
								.getProtocol();

						for (IComponent comp : sp.getComponents()) {
							if (comp instanceof IToolRunComponent) {
								String toolname = sp.getComponentParam(comp);

								ToolDescriptor td = ToolStore.getTool(toolname);

								Element toolhint = root.addElement("toolhint");
								toolhint.addAttribute("name", td.getName())
										.addAttribute("param", td.getParam());
							}
						}

						OutputFormat format = OutputFormat.createPrettyPrint();
						XMLWriter xmlWriter = new XMLWriter(
								new FileWriter(tgt), format);

						xmlWriter.write(doc);
						xmlWriter.close();

					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			}
		});

		Button btnImport = new Button(panel, SWT.NONE);
		btnImport.setText("Import...");

		btnImport.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog fd = new FileDialog(composite.getShell(), SWT.OPEN);

				fd.setFilterExtensions(new String[] { "*.xml", "*.*" });

				String path = null;

				if ((path = fd.open()) != null) {
					try {
						File src = new File(path);

						SAXReader reader = new SAXReader();
						
						Document doc = reader.read(src);
						Element root = doc.getRootElement();
						
						for(Iterator<?> it = root.elementIterator("toolhint"); it.hasNext(); ) {
							Element el = (Element) it.next();
							String name = el.attributeValue("name");
							String param = el.attributeValue("param");
							
							ToolDescriptor td = new ToolDescriptor(name, "", param);
							ToolStore.installTool(td);
							
							InstalledToolsPage prefPage =
											InstalledToolsPage.getInstance();
							
							if(prefPage != null)
								prefPage.insert(td);
							
							root.remove(el);
						}
						
						File tgt = new File(Key.KEY_PROTOCOLSTORE_PATH
								.getAbsolutePath()
								+ "/" + root.attributeValue("name") + ".xml");
						
						OutputFormat format = OutputFormat.createPrettyPrint();
						XMLWriter xmlWriter = new XMLWriter(
								new FileWriter(tgt), format);

						xmlWriter.write(doc);
						xmlWriter.close();
						
						ProtocolDescriptor pd = new ProtocolDescriptor(root.attributeValue("name"), tgt, true);
						
						ProtocolStore.installProtocol(pd);
						viewer.add(pd);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			}
		});

		viewer = new TableViewer(composite, SWT.FULL_SELECTION);

		Table table = viewer.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		GridData tableData = new GridData(SWT.FILL, SWT.FILL, true, true);
		tableData.heightHint = 200;
		tableData.widthHint = 400;
		table.setLayoutData(tableData);

		TableViewerColumn colToolName = new TableViewerColumn(viewer, SWT.NONE);
		colToolName.getColumn().setText("Protocol");
		colToolName.getColumn().setWidth(400);
		ColumnViewerToolTipSupport.enableFor(colToolName.getViewer());
		colToolName.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				ProtocolDescriptor desc = (ProtocolDescriptor) element;
				return ((Standardprotocol) desc.getProtocol()).getName();
			}

			@Override
			public String getToolTipText(Object element) {
				ProtocolDescriptor desc = (ProtocolDescriptor) element;

				return desc.getProtocol().getDescription(
						MessageProvider.getActiveLanguageAbbreviation());
			}

		});

		for (ProtocolDescriptor descriptor : ProtocolStore
				.getInstalledProtocols()) {
			viewer.add(descriptor);
		}

		return composite;
	}

	@Override
	protected void performApply() {
		ProtocolStore.save();
	}

	@Override
	public boolean performOk() {
		performApply();

		return super.performOk();
	}

}
