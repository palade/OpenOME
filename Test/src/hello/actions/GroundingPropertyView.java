package hello.actions;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;

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
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
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

public class GroundingPropertyView extends ViewPart {

	private Text text;
	private String oldValue = "";
	public Text getText() {
		return text;
	}

	public void setText(Text text) {
		this.text = text;
	}
	private Table table;
	private TableViewer tableViewer;
	public Text text_1;
	public org.eclipse.swt.widgets.List list;
	public GroundingPropertyView() {
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
				System.out.println(str);
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
				int index = tv.list_3.indexOf(oldValue);
				tv.list_3.setItem(index, str);
				Set<Entry<String,String>> set = ElementValue.vals.entrySet();
				for(Entry<String,String> val : set)
				{
					if(val.getKey().equalsIgnoreCase(oldValue) || val.getValue().equalsIgnoreCase(oldValue))
						val.setValue(str);
				}
			}});
		
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
        
        Group grpServicesupportedby = new Group(group, SWT.NONE);
        grpServicesupportedby.setText("service:supportedBy");
        grpServicesupportedby.setBounds(10, 195, 334, 85);
        
        Button button_4 = new Button(grpServicesupportedby, SWT.NONE);
        button_4.setImage(ResourceManager.getPluginImage("Test", "icons/11.jpg"));
        button_4.setBounds(224, 10, 23, 21);
        
        Button button_5 = new Button(grpServicesupportedby, SWT.NONE);
        button_5.setImage(ResourceManager.getPluginImage("Test", "icons/66.jpg"));
        button_5.setBounds(253, 10, 23, 21);
        
        Button button_6 = new Button(grpServicesupportedby, SWT.NONE);
        button_6.addSelectionListener(new SelectionAdapter(){
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				String[] sels = list.getSelection();
				if(sels.length == 0)
					return;
				else
				{
					for(String sel : sels)
					{
						list.remove(sel);
					}
				}
			}
		});
        button_6.setImage(ResourceManager.getPluginImage("Test", "icons/88.jpg"));
        button_6.setBounds(281, 10, 23, 21);
        
        text_1 = new Text(grpServicesupportedby, SWT.BORDER);
        text_1.setBounds(10, 37, 301, 38);
        
        Group group_1 = new Group(group, SWT.NONE);
        group_1.setText("grounding:WsdlGrouding");
        group_1.setBounds(10, 286, 334, 108);
        
        list = new org.eclipse.swt.widgets.List(group_1, SWT.BORDER);
        list.setBounds(10, 42, 304, 49);
        
        Button button_7 = new Button(group_1, SWT.NONE);
        button_7.setImage(ResourceManager.getPluginImage("Test", "icons/11.jpg"));
        button_7.setBounds(222, 10, 21, 21);
        
        Button button_8 = new Button(group_1, SWT.NONE);
        button_8.setImage(ResourceManager.getPluginImage("Test", "icons/66.jpg"));
        button_8.setBounds(249, 10, 23, 21);
        
        Button button_9 = new Button(group_1, SWT.NONE);
        button_9.addSelectionListener(new SelectionAdapter(){
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				String[] sels = list.getSelection();
				if(sels.length == 0)
					return;
				else
				{
					for(String sel : sels)
					{
						list.remove(sel);
					}
				}
			}
		});
        button_9.setImage(ResourceManager.getPluginImage("Test", "icons/88.jpg"));
        button_9.setBounds(278, 10, 23, 21);
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}
}
