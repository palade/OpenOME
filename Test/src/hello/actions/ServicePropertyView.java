package hello.actions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import hello.Provider.ServiceAnnoCellModifier;
import hello.Provider.ServiceAnnoContentProvider;
import hello.Provider.ServiceAnnoLabelProvider;
import hello.Provider.ServiceAnnotation;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.wb.swt.ResourceManager;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.custom.TableCursor;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.TextCellEditor;

import dialog.NewResourceDialog;
import dialog.PresentDialog;
import dialog.SupportsDialog;

public class ServicePropertyView extends ViewPart {
	
	private Text text;
	public Text getText() {
		return text;
	}

	public void setText(Text text) {
		this.text = text;
	}
	public Text text_des;
	public Text text_pre;
	public Text text_pro;
	public Text text_sup;
	public Table table;
	private String oldValue = "";
	private TableViewer tableViewer;
	public ServicePropertyView() {
	}

	@Override
	public void createPartControl(final Composite arg0) {
		arg0.setLayout(null);
		
		ScrolledComposite scrolledComposite = new ScrolledComposite(arg0, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		scrolledComposite.setBounds(0, 0, 1000, 1000);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		
		Group group = new Group(scrolledComposite, SWT.NONE);
		group.setText("INDIVIDUAL EDITOR");
		
		text = new Text(group, SWT.BORDER);
		text.setBounds(100, 23, 578, 23);
		text.addFocusListener(new FocusListener(){

			@Override
			public void focusGained(FocusEvent arg0) {
				// TODO Auto-generated method stub
				oldValue = text.getText();
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				// TODO Auto-generated method stub
				String str = text.getText();
				IWorkbenchPage wbp = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
				IViewPart view2 = null;
				try {
					view2 = wbp.showView("Hello.view2");
				}
				catch (PartInitException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				//service list
				TV tv = (TV)view2;
						
				//tv.list.remove(oldValue);
				int index = tv.list.indexOf(oldValue);
				tv.list.setItem(index, str);
				//tv.list.add(str);
				//for save
				Set<Entry<String,String>> set = ElementValue.vals.entrySet();
				for(Entry<String,String> val : set)
				{
					if(val.getKey().equalsIgnoreCase(oldValue) || val.getValue().equalsIgnoreCase(oldValue))
						val.setValue(str);
				}
				
			}});
//		text.addModifyListener(new ModifyListener(){
//
//			@Override
//			public void modifyText(ModifyEvent arg0) {
//				// TODO Auto-generated method stub
//				String str = text.getText();
//				IWorkbenchPage wbp = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
//				IViewPart view2 = null;
//				try {
//					view2 = wbp.showView("Hello.view2");
//				}
//				catch (PartInitException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//				//service list
//				TV tv = (TV)view2;
//				String[] sels = tv.list.getSelection();
//				for(String sel : sels)
//				{
//					tv.list.remove(sel);
//				}
//				tv.list.add(str);
//			}});
//			
		
		
		Label label = new Label(group, SWT.NONE);
		label.setText("for individual:");
		label.setBounds(10, 26, 97, 17);
		
		Label label_1 = new Label(group, SWT.NONE);
		label_1.setText("(instance of service:Service)");
		label_1.setBounds(684, 29, 161, 17);
		
		Button button = new Button(group, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				NameDialog dialog = new NameDialog(arg0.getShell());
				if(dialog.open() == InputDialog.OK)
				{
					String sel = dialog.value;
					if(sel!= null && !"".equals(sel))
					{
						List input = (List)tableViewer.getInput();
						if(input == null)
							input = new ArrayList();
						ServiceAnnotation sa = new ServiceAnnotation();
						sa.setPro(sel);
						input.add(sa);
						tableViewer.setInput(input);
						System.out.println(sel);
					}
				}
			}
		});
		button.setImage(ResourceManager.getPluginImage("Test", "icons/44.jpg"));
		button.setBounds(10, 52, 23, 23);
		
		Button button_1 = new Button(group, SWT.NONE);
		button_1.setImage(ResourceManager.getPluginImage("Test", "icons/55.jpg"));
		button_1.setBounds(33, 52, 23, 23);
		
		Button button_2 = new Button(group, SWT.NONE);
		button_2.setImage(ResourceManager.getPluginImage("Test", "icons/66.jpg"));
		button_2.setBounds(55, 52, 23, 23);
		
		Button button_3 = new Button(group, SWT.NONE);
		button_3.setImage(ResourceManager.getPluginImage("Test", "icons/77.jpg"));
		button_3.setBounds(84, 52, 23, 23);
		
		Group grpServicedesribedby = new Group(group, SWT.NONE);
		grpServicedesribedby.setText("service:desribedBy");
		grpServicedesribedby.setBounds(8, 195, 278, 77);
		
		Button button_4 = new Button(grpServicedesribedby, SWT.NONE);
		button_4.setImage(ResourceManager.getPluginImage("Test", "icons/11.jpg"));
		button_4.setBounds(206, 10, 23, 21);
		button_4.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(SelectionEvent e)
			{
				Shell shell = PlatformUI. getWorkbench ().getActiveWorkbenchWindow().getShell();
				NewResourceDialog dialog = new NewResourceDialog(shell);
				if(dialog.open() == InputDialog.OK)
				{
					
					String name = dialog.name;
					if(name == null)
						return;
					IWorkbenchPage wbp = getSite().getPage();
					IViewPart view = null;
					try {
						view = wbp.showView("Hello.view2");
					} catch (PartInitException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					TV tv = (TV)view;
					org.eclipse.swt.widgets.List list = tv.list_2;
					list.add(name.split(":")[1] + "_" + tv.counter);
					text_des.setText(name.split(":")[1] + "_" + tv.counter++);
				}
			}
		});
		Button button_5 = new Button(grpServicedesribedby, SWT.NONE);
		button_5.setImage(ResourceManager.getPluginImage("Test", "icons/66.jpg"));
		button_5.setBounds(230, 10, 23, 21);
		
		Button button_6 = new Button(grpServicedesribedby, SWT.NONE);
		button_6.setImage(ResourceManager.getPluginImage("Test", "icons/88.jpg"));
		button_6.setBounds(255, 10, 23, 21);
		
		text_des = new Text(grpServicedesribedby, SWT.BORDER);
		text_des.setBounds(10, 37, 268, 30);
		
		Group grpServicepresent = new Group(group, SWT.NONE);
		grpServicepresent.setText("service:present");
		grpServicepresent.setBounds(10, 278, 276, 108);
		
		Button button_7 = new Button(grpServicepresent, SWT.NONE);
		button_7.setImage(ResourceManager.getPluginImage("Test", "icons/11.jpg"));
		button_7.setBounds(204, 10, 23, 21);
		button_7.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(SelectionEvent e)
			{
				Shell shell = PlatformUI. getWorkbench ().getActiveWorkbenchWindow().getShell();
				PresentDialog dialog = new PresentDialog(shell);
				if(dialog.open() == InputDialog.OK)
				{
					
					String name = dialog.name;
					if(name == null)
						return;
					IWorkbenchPage wbp = getSite().getPage();
					IViewPart view = null;
					try {
						view = wbp.showView("Hello.view2");
					} catch (PartInitException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					TV tv = (TV)view;
					org.eclipse.swt.widgets.List list = tv.list_1;
					list.add(name.split(":")[1] + "_" + tv.counter);
					text_pre.setText(name.split(":")[1] + "_" + tv.counter++);

				}
			}
		});
		Button button_8 = new Button(grpServicepresent, SWT.NONE);
		button_8.setImage(ResourceManager.getPluginImage("Test", "icons/66.jpg"));
		button_8.setBounds(228, 10, 23, 21);
		
		Button button_9 = new Button(grpServicepresent, SWT.NONE);
		button_9.setImage(ResourceManager.getPluginImage("Test", "icons/88.jpg"));
		button_9.setBounds(253, 10, 23, 21);
		
		text_pre = new Text(grpServicepresent, SWT.BORDER);
		text_pre.setBounds(10, 37, 266, 62);
		
		Group grpServiceprovideby = new Group(group, SWT.NONE);
		grpServiceprovideby.setText("service:provideBy");
		grpServiceprovideby.setBounds(10, 392, 276, 95);
		
		Button button_10 = new Button(grpServiceprovideby, SWT.NONE);
		button_10.setImage(ResourceManager.getPluginImage("Test", "icons/11.jpg"));
		button_10.setBounds(204, 10, 23, 21);
		
		Button button_11 = new Button(grpServiceprovideby, SWT.NONE);
		button_11.setImage(ResourceManager.getPluginImage("Test", "icons/66.jpg"));
		button_11.setBounds(228, 10, 23, 21);
		
		Button button_12 = new Button(grpServiceprovideby, SWT.NONE);
		button_12.setImage(ResourceManager.getPluginImage("Test", "icons/88.jpg"));
		button_12.setBounds(253, 10, 23, 21);
		
		text_pro = new Text(grpServiceprovideby, SWT.BORDER);
		text_pro.setBounds(10, 37, 266, 52);
		
		Group grpServicesupports = new Group(group, SWT.NONE);
		grpServicesupports.setText("service:supports");
		grpServicesupports.setBounds(318, 200, 358, 287);
		
		Button button_13 = new Button(grpServicesupports, SWT.NONE);
		button_13.setImage(ResourceManager.getPluginImage("Test", "icons/11.jpg"));
		button_13.setBounds(286, 10, 23, 21);
		button_13.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(SelectionEvent e)
			{
				Shell shell = PlatformUI. getWorkbench ().getActiveWorkbenchWindow().getShell();
				SupportsDialog dialog = new SupportsDialog(shell);
				if(dialog.open() == InputDialog.OK)
				{
					
					String name = dialog.name;
					if(name == null)
						return;
					IWorkbenchPage wbp = getSite().getPage();
					IViewPart view = null;
					try {
						view = wbp.showView("Hello.view2");
					} catch (PartInitException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					TV tv = (TV)view;
					org.eclipse.swt.widgets.List list = tv.list_3;
					list.add(name.split(":")[1] + "_" + tv.counter);
					text_sup.setText(name.split(":")[1] + "_" + tv.counter++);

				}
			}
		});
		Button button_14 = new Button(grpServicesupports, SWT.NONE);
		button_14.setImage(ResourceManager.getPluginImage("Test", "icons/66.jpg"));
		button_14.setBounds(310, 10, 23, 21);
		
		Button button_15 = new Button(grpServicesupports, SWT.NONE);
		button_15.setImage(ResourceManager.getPluginImage("Test", "icons/88.jpg"));
		button_15.setBounds(335, 10, 23, 21);
		
		text_sup = new Text(grpServicesupports, SWT.BORDER);
		text_sup.setBounds(10, 37, 338, 240);
		
		tableViewer = new TableViewer(group, SWT.BORDER | SWT.FULL_SELECTION);
		table = tableViewer.getTable();
		table.setBounds(10, 81, 668, 108);
		table.setHeaderVisible(true);
		
		TableViewerColumn tableViewerColumn = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnProperty = tableViewerColumn.getColumn();
		tblclmnProperty.setWidth(147);
		tblclmnProperty.setText("Property");
		
		TableViewerColumn tableViewerColumn_2 = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnNewColumn = tableViewerColumn_2.getColumn();
		tblclmnNewColumn.setWidth(432);
		tblclmnNewColumn.setText("Value");
		
		TableViewerColumn tableViewerColumn_1 = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnValue = tableViewerColumn_1.getColumn();
		tblclmnValue.setWidth(85);
		tblclmnValue.setText("Lang");
		scrolledComposite.setContent(group);
		scrolledComposite.setMinSize(group.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		
		// TODO Auto-generated method stub
		tableViewer.setContentProvider(new ServiceAnnoContentProvider());
		tableViewer.setLabelProvider(new ServiceAnnoLabelProvider());
//		List l =  new ArrayList();
//		ServiceAnnotation sa = new ServiceAnnotation();
//		sa.setPro("1");
//		sa.setValue("v");
//		sa.setLang("3");
//		l.add(sa);
//		tableViewer.setInput(l);
		tableViewer.setColumnProperties(new String[]{"pro","value","lang"});
        CellEditor[] cellEditor = new CellEditor[3];
        cellEditor[0] = null;
        cellEditor[1] = new TextCellEditor(tableViewer.getTable());
        cellEditor[2] = new ComboBoxCellEditor(tableViewer.getTable(),ServiceAnnoCellModifier.cols,SWT.READ_ONLY);
       
       
       
        tableViewer.setCellEditors(cellEditor);
        ICellModifier modifier = new ServiceAnnoCellModifier(tableViewer);
        tableViewer.setCellModifier(modifier);
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}
}
