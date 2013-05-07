package org.eclipse.bpel.ui.properties;

import hello.actions.FilePath;

import hello.util.OWLParser;

import java.util.List;


import org.eclipse.bpel.common.ui.details.DelegateIValue;
import org.eclipse.bpel.common.ui.details.IDetailsAreaConstants;
import org.eclipse.bpel.common.ui.details.IValue;
import org.eclipse.bpel.common.ui.details.TextIValue;
import org.eclipse.bpel.common.ui.details.widgets.DecoratedLabel;
import org.eclipse.bpel.common.ui.details.widgets.StatusLabel2;
import org.eclipse.bpel.common.ui.flatui.FlatFormAttachment;
import org.eclipse.bpel.common.ui.flatui.FlatFormData;
import org.eclipse.bpel.model.Activity;
import org.eclipse.bpel.model.BPELPackage;
import org.eclipse.bpel.model.Import;
import org.eclipse.bpel.model.Process;
import org.eclipse.bpel.model.util.BPELUtils;
import org.eclipse.bpel.ui.IHelpContextIds;
import org.eclipse.bpel.ui.Messages;
import org.eclipse.bpel.ui.adapters.INamedElement;
import org.eclipse.bpel.ui.details.providers.ColumnTableProvider;
import org.eclipse.bpel.ui.dialogs.CandidateSerInfoDialog;
import org.eclipse.bpel.ui.dialogs.NamespaceMappingDialog;
import org.eclipse.bpel.ui.properties.NamespacePrefixesSection.ContextColumn;
import org.eclipse.bpel.ui.properties.NamespacePrefixesSection.LocationColumn;
import org.eclipse.bpel.ui.properties.NamespacePrefixesSection.NamespaceColumn;
import org.eclipse.bpel.ui.properties.NamespacePrefixesSection.PrefixColumn;
import org.eclipse.bpel.ui.util.BPELUtil;
import org.eclipse.bpel.ui.util.MultiObjectAdapter;
import org.eclipse.bpel.ui.util.NamespaceUtils;
import org.eclipse.bpel.ui.util.TableCursor;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.commands.Command;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.WorkbenchException;
import org.eclipse.ui.part.FileEditorInput;

public class CandidateServicesSection extends BPELPropertySection {
	protected Composite parentComposite;

	protected Composite activityComposite;

	protected Label tabLabel;

	protected Table prefixesTable;

	protected TableViewer prefixesViewer;

	protected ColumnTableProvider tableProvider;

	protected TableCursor tableCursor = null;

	/**
	 * Make this section use all the vertical space it can get.
	 * 
	 */
	@Override
	public boolean shouldUseExtraSpace() {
		return true;
	}

	/**
	 * Bug 290085 - Override the super-class because the input is Process not Import
	 * If use super-class's directly, when change the import attributes
	 * the properties section do not change. Grid Qian
	 */
	@Override
	protected void addAllAdapters() {
		super.addAllAdapters();
		if (fAdapters.length > 0) {
			if (getModel() != null) {
				EObject obj = getModel();
				if (obj instanceof Process) {
					List<Import> list = ((Process) obj).getImports();
					for (int i = 0; i < list.size(); i++) {
						fAdapters[0].addToObject((Import) list.get(i));
					}
				}
			}
		}
	}

	@Override
	protected MultiObjectAdapter[] createAdapters() {
		return new MultiObjectAdapter[] { new MultiObjectAdapter() {
			@Override
			public void notify(Notification n) {
				prefixesViewer.setInput(getInput());
			}
		}, };
	}

	protected void createWidgets(final Composite parent) {

		FlatFormData data;

		
	

		data = new FlatFormData();
		data.right = new FlatFormAttachment(100, -5
				* IDetailsAreaConstants.HSPACE);
		data.top = new FlatFormAttachment(0, IDetailsAreaConstants.VSPACE);
		

//		tabLabel = fWidgetFactory.createLabel(parent,
//				Messages.NamespacePrefixesSection);
		data = new FlatFormData();
		data.left = new FlatFormAttachment(0, IDetailsAreaConstants.HSPACE);
		
		//tabLabel.setLayoutData(data);

		// create table
		prefixesTable = fWidgetFactory.createTable(parent, SWT.FULL_SELECTION | SWT.V_SCROLL
				| SWT.READ_ONLY);

		data = new FlatFormData();
		data.left = new FlatFormAttachment(0, IDetailsAreaConstants.HSPACE);
		data.right = new FlatFormAttachment(100, -5
				* IDetailsAreaConstants.HSPACE);
		data.top = new FlatFormAttachment(tabLabel,
				IDetailsAreaConstants.VSPACE);
		data.bottom = new FlatFormAttachment(100, -IDetailsAreaConstants.HSPACE);
		prefixesTable.setLayoutData(data);

		// set up table
		prefixesTable.setLinesVisible(true);
		prefixesTable.setHeaderVisible(true);
		
		tableProvider = new ColumnTableProvider();//包含所有列的信息
		tableProvider.add(new IDColumn());//添加一个列名为ID的列
		tableProvider.add(new NameColumn());
//		tableProvider.add(new ContextColumn());
//		tableProvider.add(new LocationColumn());
		prefixesTable.addMouseListener(new MouseAdapter(){
			public void mouseDoubleClick(MouseEvent e)
			{
				System.out.println("double click table-----------------------------");
				TableItem[] items = prefixesTable.getSelection();
				for(TableItem item : items)
				{
					try {
						PlatformUI.getWorkbench().showPerspective("hello.actions.ServicePerspective", PlatformUI.getWorkbench().getActiveWorkbenchWindow());
						String serName = item.getText(1);
						String fileName = null;
						 IEditorPart activeEditor =PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();//获得Editor
						 IEditorInput editorInput = activeEditor.getEditorInput();//获得editor的输入 就是编辑的对象是谁
						 if(editorInput instanceof FileEditorInput)//输入是否是文件输入
						 {
							 FileEditorInput input = (FileEditorInput)   editorInput;
							 IFile iFile = input.getFile();//获取当前输入文件
							 IProject iProject = iFile.getProject();//获得文件所在的工程
							 
							 fileName = iProject.getLocation()+"/"+serName+".owl";
							 System.out.println(fileName);
						 }
						 FilePath.filePath = fileName;
						 OWLParser owl = new OWLParser(fileName);
						 owl.parse();
						 owl.show(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage());
						 
					} catch (WorkbenchException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
			}
		});
		prefixesViewer = new TableViewer(prefixesTable);//创建一个table控件
		tableProvider.createTableLayout(prefixesTable);
		prefixesViewer.setLabelProvider(tableProvider);//将表的columnName和表格的内容放到table中
		prefixesViewer.setCellModifier(tableProvider);
		prefixesViewer.setContentProvider(new NamespacePrefixesProvider());
		prefixesViewer.setColumnProperties(tableProvider.getColumnProperties());
		prefixesViewer.setCellEditors(tableProvider.createCellEditors(prefixesTable));
		
//		prefixesViewer
//				.addPostSelectionChangedListener(new ISelectionChangedListener() {
//					public void selectionChanged(SelectionChangedEvent event) {
//						if (event.getSelection() instanceof StructuredSelection)
//						{
//							StructuredSelection sel = (StructuredSelection)event.getSelection();
//							NamespacePrefixElement elem = (NamespacePrefixElement)sel.getFirstElement();
//							if (elem!=null && (elem.prefix==null || elem.prefix.length()==0))
//								assignPrefixButton.setEnabled(true);
//							else
//								assignPrefixButton.setEnabled(false);
//						}
//					}
//				});

		tableCursor = BPELUtil.createTableCursor(prefixesTable, prefixesViewer);
	}

	public class IDColumn extends ColumnTableProvider.Column implements
			ILabelProvider {
		private  int i = 0;
		@Override
		public String getHeaderText() {
			return "ID";
		}

		@Override
		public String getProperty() {
			return "ID"; //$NON-NLS-1$
		}

		@Override
		public int getInitialWeight() {
			return 10;
		}

		public String getText(Object element) {//获得cell的内容
			
//			String s = ((NamespacePrefixElement)element).prefix;
//			return (s == null) ? "" : s; //$NON-NLS-1$		
			i++;
			return "1."+i;
		}
	}
	
	public class NameColumn extends ColumnTableProvider.Column implements
			ILabelProvider {
		private int i = 0;
		@Override
		public String getHeaderText() {
			return "Name";
		}

		@Override
		public String getProperty() {
			return "Name"; //$NON-NLS-1$
		} 

		@Override
		public int getInitialWeight() {
			return 40;
		}

		public String getText(Object element) {

//			String s = ((NamespacePrefixElement)element).namespace;
//			return (s == null) ? "" : s; //$NON-NLS-1$	
		i++;
//			return "Service"+i;
			return"CheckLuggageSecurityService"+i;
			
			
		}
	}


	@Override
	protected void basicSetInput(EObject newInput) {		
		super.basicSetInput(newInput);
		
		if (getInput() != null) {
			prefixesViewer.setInput(getInput());
		}
	}
	

	@Override
	protected void createClient(Composite parent) {
		Composite composite = parentComposite = createFlatFormComposite(parent);

		createWidgets(composite);
		PlatformUI.getWorkbench().getHelpSystem().setHelp(parentComposite,
				IHelpContextIds.PROPERTY_PAGE_NAMESPACE_PREFIXES);
	}

	@Override
	public Object getUserContext() {
		return ((StructuredSelection) prefixesViewer.getSelection())
				.getFirstElement();
	}

	@Override
	public void restoreUserContext(Object userContext) {
		prefixesTable.setFocus();
		if (userContext != null) {
			prefixesViewer.setSelection(new StructuredSelection(userContext));
		}
	}

	void assignPrefix () {
		
		ISelection selection = prefixesViewer.getSelection();
		if (selection.isEmpty()) {
			return ;
		}
		IStructuredSelection ssel = null;
		if ( (selection instanceof IStructuredSelection) == false) {
			return;
		}
		
		ssel = (IStructuredSelection) selection;		
		NamespacePrefixElement elem = (NamespacePrefixElement)ssel.getFirstElement();
		
		EObject modelObject = elem.context;
		if (modelObject==null)
			modelObject = this.fModelObject;
		
		// We have to map the namespace to a prefix. 
		NamespaceMappingDialog dialog = new NamespaceMappingDialog(tabLabel.getShell(),modelObject);
		dialog.setNamespace( elem.namespace );
		
		if (dialog.open() == Window.CANCEL) {
			return;
		}
		
		// define the prefix
		BPELUtils.setPrefix( modelObject, elem.namespace, dialog.getPrefix());
		elem.prefix = dialog.getPrefix();
		elem.context = modelObject;
		
		prefixesViewer.refresh();
	}

	
	
	@Override
	public void gotoMarker(IMarker marker) {
		// TODO Auto-generated method stub
		super.gotoMarker(marker);
	}
	
	/**
	 * 
	 */
	

	@Override
	public boolean isValidMarker(IMarker marker) {
		return super.isValidMarker(marker);
	}

}
