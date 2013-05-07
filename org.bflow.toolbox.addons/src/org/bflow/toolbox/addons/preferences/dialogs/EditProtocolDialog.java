package org.bflow.toolbox.addons.preferences.dialogs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import org.bflow.toolbox.addons.components.DiagramExportComponent;
import org.bflow.toolbox.addons.components.ToolAdapterComponent;
import org.bflow.toolbox.addons.components.ToolRunComponent;
import org.bflow.toolbox.addons.core.exceptions.ComponentException;
import org.bflow.toolbox.addons.core.model.IComponent;
import org.bflow.toolbox.addons.protocols.Standardprotocol;
import org.bflow.toolbox.addons.store.ComponentStore;
import org.bflow.toolbox.addons.store.ToolStore;
import org.bflow.toolbox.addons.utils.ProtocolDescriptor;
import org.bflow.toolbox.addons.utils.ToolDescriptor;
import org.bflow.toolbox.i18n.MessageProvider;
import org.bflow.toolbox.interchange.model.ExportDescription;
import org.bflow.toolbox.interchange.store.ExportscriptStore;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;

/**
 * Defines a dialog to edit protocols.
 * 
 * @author Arian Storch
 * @since 25/09/10
 */
public class EditProtocolDialog extends Dialog {

	private ProtocolDescriptor protdesc;

	private TableViewer viewer;
	
	private Composite dlgComposite;

	public EditProtocolDialog(Shell parent, ProtocolDescriptor protdesc) {
		super(parent, SWT.APPLICATION_MODAL | SWT.DIALOG_TRIM);
		this.setText("Edit " + protdesc.getName() + " protocol");
		this.protdesc = protdesc;
	}

	/**
	 * Creates the content of this dialog.
	 * 
	 * @param composite
	 *            composite
	 */
	protected void createContents(final Composite composite) {
		composite.setLayout(new GridLayout(1, true));

		Label lblName = new Label(composite, SWT.NONE);
		lblName.setText("Name");

		final Text txtName = new Text(composite, SWT.BORDER);

		GridData gridData = new GridData(SWT.LEAD, SWT.TOP, true, true);
		gridData.heightHint = 16;
		gridData.widthHint = 200;
		txtName.setLayoutData(gridData);

		txtName.setText(((Standardprotocol) protdesc.getProtocol()).getName());
		txtName.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String name = txtName.getText();
				if(name == null || name.equalsIgnoreCase(""))
					return ;
				
				((Standardprotocol)protdesc.getProtocol()).setName(name);
			}
		});

		Label lblDescription = new Label(composite, SWT.NONE);
		lblDescription.setText("Beschreibung");

		final Text txtDescription = new Text(composite, SWT.BORDER | SWT.WRAP
				| SWT.MULTI);

		gridData = new GridData(SWT.LEAD, SWT.TOP, true, true);
		gridData.heightHint = 50;
		gridData.widthHint = 650;
		txtDescription.setLayoutData(gridData);
		txtDescription.setText(protdesc.getProtocol().getDescription(
				MessageProvider.getActiveLanguageAbbreviation()));
		
		txtDescription.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String txt = txtDescription.getText();
				
				protdesc.getProtocol().setDescription(txt);
			}
		});

		viewer = new TableViewer(composite, SWT.FULL_SELECTION);
		Table table = viewer.getTable();

		table.setLinesVisible(true);
		table.setHeaderVisible(true);

		GridData tableData = new GridData(SWT.FILL, SWT.FILL, true, true);
		tableData.heightHint = 400;
		tableData.widthHint = 650;
		table.setLayoutData(tableData);

		TableViewerColumn compNameViewer = new TableViewerColumn(viewer,
				SWT.NONE);
		compNameViewer.getColumn().setText("Komponente");
		compNameViewer.getColumn().setWidth(250);
		compNameViewer.setLabelProvider(new ComponentNameLabelProvider());
		compNameViewer
				.setEditingSupport(new ComponentNameEditingSupport(viewer));
		ColumnViewerToolTipSupport.enableFor(compNameViewer.getViewer());

		TableViewerColumn compParamsViewer = new TableViewerColumn(viewer,
				SWT.NONE);
		compParamsViewer.getColumn().setText("Parameter");
		compParamsViewer.getColumn().setWidth(400);
		compParamsViewer.setLabelProvider(new ComponentParamsLabelProvider());
		compParamsViewer.setEditingSupport(new ComponentParamEditingSupport(
				viewer));

		/*
		 * fill table content
		 */
		for (IComponent comp : protdesc.getProtocol().getComponents())
			viewer.add(comp);

		Composite btnPanel = new Composite(composite, SWT.NONE);
		btnPanel.setLayout(new GridLayout(3, true));

		Button btnAdd = new Button(btnPanel, SWT.NONE);
		btnAdd.setText("Hinzufügen");

		btnAdd.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				viewer.add(new NullComponent());
			}
		});

		Button btnDel = new Button(btnPanel, SWT.NONE);
		btnDel.setText("Löschen");
		btnDel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				StructuredSelection selection = (StructuredSelection) viewer.getSelection();
				IComponent comp = (IComponent)selection.getFirstElement();
				
				viewer.remove(comp);
				protdesc.getProtocol().getComponents().remove(comp);
				protdesc.getProtocol().removeComponentParam(comp);
			}
		});

		Button btnClose = new Button(btnPanel, SWT.NONE);
		btnClose.setText("Schließen");
		btnClose.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				close();
			}
		});

		this.dlgComposite = composite;
		this.dlgComposite.getShell().setDefaultButton(btnClose);
	}

	/**
	 * Opens the dialog.
	 * 
	 * @return Returns the inserted values or null if the user cancelled the
	 *         dialog.
	 */
	public void open() {
		Shell shell = new Shell(this.getParent(), this.getStyle());
		shell.setText(this.getText());
		createContents(shell);

		shell.pack();
		shell.open();

		Display display = this.getParent().getDisplay();

		while (!shell.isDisposed())
			if (!display.readAndDispatch())
				display.sleep();
	}
	
	/**
	 * Closes the dialog if the protocol is valid configured.
	 */
	private void close() {
		if(isValid())
			this.dlgComposite.getShell().close();
	}
	
	/**
	 * Proofs if the protocol is valid configured.
	 * @return true or false
	 */
	private boolean isValid() {
		
		int viewerSize = viewer.getTable().getItemCount();
		
		for(int i = 1; i < viewerSize; i++) {
			IComponent comp = (IComponent) viewer.getElementAt(i);
			IComponent prev = (IComponent) viewer.getElementAt(i-1);
			
			if(!comp.canLinkWith(prev)) {
				
				MessageDialog.openError(dlgComposite.getShell(), "Fehler", comp.getDisplayName()+
									" darf nicht nach "+prev.getDisplayName()+" ausgeführt werden!");
				
				return false;
			}
			
			if(comp.hasParams() || comp instanceof DiagramExportComponent) {
				String param = protdesc.getProtocol().getComponentParam(comp);
				
				if(param == null || param.equalsIgnoreCase("")) {
					
					MessageDialog.openError(dlgComposite.getShell(), "Fehler", comp.getDisplayName()+
											" benötigt Parameter!");
					
					return false;
				}
			}
			
			if(comp instanceof NullComponent) {
				MessageDialog.openError(dlgComposite.getShell(), "Fehler", "Eine Komponente ist noch nicht gesetzt!");
				
				return false;
			}
							
		}
		
		return true;
	}

	/**
	 * Defines a ColumnLabelProvider for displaying {@link IComponent} names.
	 * 
	 * @author Arian Storch
	 * @since 25/09/10
	 */
	protected class ComponentNameLabelProvider extends ColumnLabelProvider {

		@Override
		public String getText(Object element) {
			IComponent comp = (IComponent) element;
			return comp.getDisplayName();
		}

		@Override
		public String getToolTipText(Object element) {
			return ((IComponent) element).getDescription(MessageProvider
					.getActiveLanguageAbbreviation());
		}
	}

	/**
	 * Defines a ColumnLabelProvider for displaying {@link IComponent} params.
	 * 
	 * @author Arian Storch
	 * @version 25/09/10
	 * 
	 */
	protected class ComponentParamsLabelProvider extends ColumnLabelProvider {

		@Override
		public String getText(Object element) {
			IComponent comp = (IComponent) element;

			if (comp instanceof NullComponent)
				return null;

			String param = ((Standardprotocol) protdesc.getProtocol())
					.getComponentParam(comp);

			if (param == null)
				param = "";

			return param;
		}
	}

	/**
	 * Dummy {@link IComponent} implementation for the table.
	 * 
	 * @author Arian Storch
	 * @since 28/09/10
	 */
	private class NullComponent implements IComponent {

		@Override
		public boolean canLinkWith(IComponent component) {
			return false;
		}

		@Override
		public void finish() {
		}

		@Override
		public String getDescription(String abbreviation) {
			return null;
		}

		@Override
		public String getDisplayName() {
			return "- select -";
		}

		@Override
		public boolean hasFinished() {
			return false;
		}

		@Override
		public boolean hasParams() {
			return false;
		}

		@Override
		public void init() {
		}

		@Override
		public void invoke() throws ComponentException {
		}

		@Override
		public boolean isValid() {
			return false;
		}

		@Override
		public void setParams(String param) {

		}

		@Override
		public void transformInput(Object inputSource)
				throws ComponentException {
		}

		@Override
		public Object transformOutput() throws ComponentException {
			return null;
		}

	}

	/**
	 * Implements a editing support for the component name.
	 * 
	 * @author Arian Storch
	 * @since 25/09/10
	 */
	protected class ComponentNameEditingSupport extends EditingSupport {

		private CellEditor editor;
		private List<String> cBoxValues;

		public ComponentNameEditingSupport(ColumnViewer viewer) {
			super(viewer);

			cBoxValues = ComponentStore.getUserfriendlyComponentNames();
			int size = cBoxValues.size();

			this.editor = new ComboBoxCellEditor(((TableViewer) viewer)
					.getTable(), cBoxValues.toArray(new String[size]),
					SWT.READ_ONLY);
		}

		@Override
		protected boolean canEdit(Object element) {
			return true;
		}

		@Override
		protected CellEditor getCellEditor(Object element) {
			return this.editor;
		}

		@Override
		protected Object getValue(Object element) {
			IComponent comp = (IComponent) element;

			String name = comp.getDisplayName();
			int index = cBoxValues.indexOf(name);

			return index;
		}

		@Override
		protected void setValue(Object element, Object value) {
			IComponent old = (IComponent) element;

			int protIndex = protdesc.getProtocol().getComponents().indexOf(old);

			int index = (Integer) value;
			
			if(index == -1)
				return ;
			
			int selIndex = ((TableViewer) viewer).getTable()
													.getSelectionIndex();
						
			String compName = this.cBoxValues.get(index);

			IComponent nu = ComponentStore.identify(compName, true);
			
			if(nu.getDisplayName().equalsIgnoreCase(old.getDisplayName()))
				return ;

			if(protIndex != -1)
				protdesc.getProtocol().getComponents().set(protIndex, nu);
			else
				protdesc.getProtocol().getComponents().add(nu);

			((TableViewer) viewer).replace(nu, selIndex);
			((TableViewer) viewer).refresh(nu);

		}
	}

	/**
	 * Implements a editing support for the component value.
	 * 
	 * @author Arian Storch
	 * @since 25/09/10
	 * 
	 */
	protected class ComponentParamEditingSupport extends EditingSupport {

		private CellEditor editorTxt;
		private CellEditor editorBoxExport;
		private CellEditor editorBoxTools;
		private CellEditor editorBoxToolAdapter;

		private List<String> exportNames = new ArrayList<String>();
		private List<String> toolNames = new ArrayList<String>();
		private List<String> toolAdapterKinds = new ArrayList<String>();

		public ComponentParamEditingSupport(ColumnViewer viewer) {
			super(viewer);

			for (ExportDescription exd : ExportscriptStore.getDepository()) {
				exportNames.add(exd.getName());
			}

			int size = exportNames.size();

			this.editorTxt = new TextCellEditor(((TableViewer) viewer)
					.getTable());
			
			this.editorBoxExport = new ComboBoxCellEditor(((TableViewer) viewer)
					.getTable(), exportNames.toArray(new String[size]),
					SWT.READ_ONLY);
			
			Vector<ToolDescriptor> tools = ToolStore.getInstalledTools();
			
			for(ToolDescriptor td:tools)
				toolNames.add(td.getName());
			
			int size2 = toolNames.size();
			
			this.editorBoxTools = new ComboBoxCellEditor(((TableViewer) viewer).getTable(),
									toolNames.toArray(new String[size2]), SWT.READ_ONLY);
			
			toolAdapterKinds = new ArrayList<String>(Arrays.asList(new String[] {"equal", "file to shell", "shell to file"}));
			
			this.editorBoxToolAdapter = new ComboBoxCellEditor(((TableViewer) viewer).getTable(),
										toolAdapterKinds.toArray(new String[toolAdapterKinds.size()]), SWT.READ_ONLY);
		}

		@Override
		protected boolean canEdit(Object element) {
			return ((IComponent) element).hasParams();
		}

		@Override
		protected CellEditor getCellEditor(Object element) {
			IComponent comp = (IComponent) element;

			if (comp instanceof DiagramExportComponent)
				return this.editorBoxExport;
			
			if(comp instanceof ToolRunComponent)
				return this.editorBoxTools;
			
			if(comp instanceof ToolAdapterComponent)
				return this.editorBoxToolAdapter;
			
			return this.editorTxt;
		}

		@Override
		protected Object getValue(Object element) {
			IComponent comp = (IComponent) element;

			if (comp instanceof NullComponent)
				return null;

			String value = ((Standardprotocol) protdesc.getProtocol())
					.getComponentParam(comp);

			if (value == null)
				value = "";

			if (comp instanceof DiagramExportComponent)
				return exportNames.indexOf(value);
			
			if(comp instanceof ToolRunComponent)
				return toolNames.indexOf(value);
			
			if(comp instanceof ToolAdapterComponent)
				return toolAdapterKinds.indexOf(value);
			
			return value;
		}

		@Override
		protected void setValue(Object element, Object value) {
			IComponent comp = (IComponent) element;

			String param = "";

			if (comp instanceof DiagramExportComponent) {
				if((Integer)value == -1)
					return ;
				
				param = this.exportNames.get((Integer) value);
			} else if (comp instanceof ToolRunComponent) {
				if((Integer)value == -1)
					return ;
				
				param = this.toolNames.get((Integer) value);
			} else if (comp instanceof ToolAdapterComponent) {
				if((Integer)value == -1)
					return ;
				
				param = this.toolAdapterKinds.get((Integer)value);
			}
			else {
				param = (String) value;
			}

			((Standardprotocol) protdesc.getProtocol()).setComponentParam(comp,
					param);

			super.getViewer().update(element, null);
		}
	}
}
