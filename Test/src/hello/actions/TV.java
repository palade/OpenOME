package hello.actions;

import hello.util.OWLParser;

import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.wb.swt.ResourceManager;
import org.eclipse.wb.swt.SWTResourceManager;

public class TV extends ViewPart implements IPropertyChangeListener{
	public int counter = 1;
	public List  list_1 = null;
	private Group service;
	public List list_2 =null;
	public List list_3 = null;
	public List list = null;
	public TV() {
	}

	@Override
	public void createPartControl(Composite parent) {
		parent.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		parent.setLayout(null);
		
		Group grpProcessprocess = new Group(parent, SWT.NONE);
		grpProcessprocess.setText("process:Process");
		grpProcessprocess.setBounds(15, 300, 284, 135);
		
		list_2 = new List(grpProcessprocess, SWT.BORDER);
		list_2.setBounds(10, 37, 271, 83);
		list_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			
				String[] sels = ((List)e.getSource()).getSelection();
				if(sels.length > 0)
				{
					String name = sels[0];
					System.out.println(name);
					IWorkbenchPage wbp = getSite().getPage();
					IViewPart view = null;
					try {
						view = wbp.showView("ProcessPropertyView");
					} catch (PartInitException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					ProcessPropertyView v = (ProcessPropertyView)view;
					Text nameText = (Text)v.getText();
					nameText.setText(name);
				}
			}
		});
		Button button_4 = new Button(grpProcessprocess, SWT.NONE);
		button_4.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				InputDialog dialog = new InputDialog(getSite().getShell(),"title","please input","",null);
				String str = null;
				if(dialog.open() == InputDialog.OK)
				{
					str = dialog.getValue();
					System.out.println(str);
					list_2.add(str);
				}
				
			}
		});
		
		button_4.setImage(ResourceManager.getPluginImage("Test", "icons/11.jpg"));

//		button_4.setImage(SWTResourceManager.getImage("E:\\course\\\u6BD5\u4E1A\u8BBE\u8BA1\\11.jpg"));
		button_4.setBounds(213, 10, 21, 21);
		
		Button button_5 = new Button(grpProcessprocess, SWT.NONE);
		button_5.addSelectionListener(new SelectionAdapter(){
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				String[] sels = list_2.getSelection();
				if(sels.length == 0)
					return;
				else
				{
					for(String sel : sels)
					{
						list_2.remove(sel);
					}
				}
			}
		});
		button_5.setImage(ResourceManager.getPluginImage("Test", "icons/22.jpg"));
		button_5.setBounds(240, 10, 21, 21);
		
		Button button_6 = new Button(grpProcessprocess, SWT.NONE);
		button_6.addSelectionListener(new SelectionAdapter(){
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				String[] sels = list_2.getSelection();
				if(sels.length == 0)
					return;
				else
				{
					for(String sel : sels)
					{
						list_2.remove(sel);
					}
				}
			}
		});
		button_6.setImage(ResourceManager.getPluginImage("Test", "icons/33.jpg"));
		button_6.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		button_6.setBounds(264, 10, 20, 21);
		
		service = new Group(parent, SWT.NONE);
		service.setText("service:Service");
		service.setBounds(15, 10, 284, 135);
		
		list = new List(service, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		list.setBounds(10, 42, 271, 83);
		list.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			
				String[] sels = ((List)e.getSource()).getSelection();
				if(sels.length > 0)
				{
					String name = sels[0];
					System.out.println(name);
					IWorkbenchPage wbp = getSite().getPage();
					IViewPart view = null;
					try {
						view = wbp.showView("Hello.view3");
					} catch (PartInitException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					ServicePropertyView v = (ServicePropertyView)view;
					Text nameText = (Text)v.getText();
					nameText.setText(name);
				}
			}
		});
		Button button = new Button(service, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				InputDialog dialog = new InputDialog(getSite().getShell(),"title","please input","",null);
				String str = null;
				if(dialog.open() == InputDialog.OK)
				{
					str = dialog.getValue();
					System.out.println(str);
					list.add(str);
					ElementValue.vals.put(str, str);
				}
				
			}
		});
		button.setImage(ResourceManager.getPluginImage("Test", "icons/11.jpg"));
		button.setBounds(208, 12, 21, 21);
		
		Button button_7 = new Button(service, SWT.NONE);
//		button_7.addSelectionListener(new SelectionAdapter(){
//			@Override
//			public void widgetSelected(SelectionEvent e)
//			{
//				String[] sels = list.getSelection();
//				if(sels.length == 0)
//					return;
//				else
//				{
//					for(String sel : sels)
//					{
//						list.remove(sel);
//					}
//				}
//			}
//		});
		button_7.setImage(ResourceManager.getPluginImage("Test", "icons/22.jpg"));
		button_7.setBounds(235, 12, 21, 21);
		
		Button button_8 = new Button(service, SWT.NONE);
		button_8.addSelectionListener(new SelectionAdapter(){
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
						
						Set<Entry<String,String>> set = ElementValue.vals.entrySet();
						for(Entry<String,String> val : set)
						{
							if(val.getKey().equalsIgnoreCase(sel) || val.getValue().equalsIgnoreCase(sel))
								ElementValue.vals.remove(val.getKey());
						}
					}
				}
			}
		});
		button_8.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		button_8.setImage(ResourceManager.getPluginImage("Test", "icons/33.jpg"));
		button_8.setBounds(259, 12, 20, 21);
		
		final Button btnCheckluggage = new Button(service, SWT.NONE);
		btnCheckluggage.setBounds(122, 9, 80, 27);
		btnCheckluggage.setText("CheckLuggageSecurity");
		btnCheckluggage.addSelectionListener(new SelectionAdapter()
		{
			public void widgetSelected(SelectionEvent e) {
//				Set<Entry<String, String>> set = ElementValue.owlPath.entrySet();
//				
//				for(Entry<String,String> entry : set)
//				{
//					if(entry.getKey().equals(btnCheckluggage.getText()))
//					{
//						FilePath.filePath = entry.getValue();
//						if(FilePath.filePath == null)
//							return;
//						OWLParser owl = new OWLParser(FilePath.filePath);
//						owl.parse();
//						owl.show(getSite().getWorkbenchWindow().getActivePage());
//						return;
//					}
//
//				}
//				IWorkbenchPage wbp = getSite().getPage();
//				IViewPart view = null;
//				try {
//					view = wbp.showView("Hello.view2");
//				} catch (PartInitException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//				TV tv = (TV)view;
//				tv.list.add(btnCheckluggage.getText()+"Service");
//				tv.list_1.add(btnCheckluggage.getText()+"Profile");
//				tv.list_2.add(btnCheckluggage.getText()+"Process");
//				tv.list_3.add(btnCheckluggage.getText()+"Grounding");
//				
//				try {
//					view = wbp.showView("Hello.view3");
//				} catch (PartInitException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//				
//				ServicePropertyView SPV = (ServicePropertyView)view;
//				SPV.getText().setText(btnCheckluggage.getText()+"Service");
//				SPV.text_des.setText(btnCheckluggage.getText()+"Process");
//				SPV.text_pre.setText(btnCheckluggage.getText()+"Profile");
//				SPV.text_sup.setText(btnCheckluggage.getText()+"Grounding");
//				
//				try {
//					view = wbp.showView("ProfilePropertyView");
//				} catch (PartInitException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//				ProfilePropertyView PPV = (ProfilePropertyView)view;
//				PPV.getText().setText(btnCheckluggage.getText()+"Profile");
//				PPV.text_preBy.setText(btnCheckluggage.getText()+"Service");
//				
//				try {
//					view = wbp.showView("ProcessPropertyView");
//				} catch (PartInitException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//				
//				ProcessPropertyView ProcessV = (ProcessPropertyView)view;
//				ProcessV.getText().setText(btnCheckluggage.getText()+"Process");
//				ProcessV.text_1.setText(btnCheckluggage.getText()+"Service");
//				
//				try {
//					view = wbp.showView("GroundingPropertyView");
//				} catch (PartInitException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//				
//				GroundingPropertyView GPV = (GroundingPropertyView)view;
//				GPV.getText().setText(btnCheckluggage.getText()+"Grounding");
//				GPV.text_1.setText(btnCheckluggage.getText()+"Service");
//				GPV.list.add(btnCheckluggage.getText()+"AtomicProcessGrounding");
			}
		});
		
		Group grpProfileprofile = new Group(parent, SWT.NONE);
		grpProfileprofile.setText("profile:Profile");
		grpProfileprofile.setBounds(15, 159, 284, 135);
		
		list_1 = new List(grpProfileprofile, SWT.BORDER);
		list_1.setBounds(10, 42, 271, 83);
		list_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			
				String[] sels = ((List)e.getSource()).getSelection();
				if(sels.length > 0)
				{
					String name = sels[0];
					System.out.println(name);
					IWorkbenchPage wbp = getSite().getPage();
					IViewPart view = null;
					try {
						view = wbp.showView("ProfilePropertyView");
					} catch (PartInitException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					ProfilePropertyView v = (ProfilePropertyView)view;
					Text nameText = (Text)v.getText();
					nameText.setText(name);
				}
			}
		});
		Button button_1 = new Button(grpProfileprofile, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				InputDialog dialog = new InputDialog(getSite().getShell(),"title","please input","",null);
				String str = null;
				if(dialog.open() == InputDialog.OK)
				{
					str = dialog.getValue();
					System.out.println(str);
					list_1.add(str);
					ElementValue.profileVals.put(str, str);
				}
				
			}
		});
		button_1.setImage(ResourceManager.getPluginImage("Test", "icons/11.jpg"));
		button_1.setBounds(210, 10, 21, 21);
		
		Button button_2 = new Button(grpProfileprofile, SWT.NONE);
		button_2.addSelectionListener(new SelectionAdapter(){
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				String[] sels = list_1.getSelection();
				if(sels.length == 0)
					return;
				else
				{
					for(String sel : sels)
					{
						list_1.remove(sel);
					}
				}
			}
		});
		button_2.setImage(ResourceManager.getPluginImage("Test", "icons/22.jpg"));
		button_2.setBounds(237, 10, 21, 21);
		
		Button button_3 = new Button(grpProfileprofile, SWT.NONE);
		button_3.addSelectionListener(new SelectionAdapter(){
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				String[] sels = list_1.getSelection();
				if(sels.length == 0)
					return;
				else
				{
					for(String sel : sels)
					{
						list_1.remove(sel);
						
						Set<Entry<String,String>> set = ElementValue.profileVals.entrySet();
						for(Entry<String,String> val : set)
						{
							if(val.getKey().equalsIgnoreCase(sel) || val.getValue().equalsIgnoreCase(sel))
								ElementValue.profileVals.remove(val.getKey());
						}
					}
				}
			}
		});
		button_3.setImage(ResourceManager.getPluginImage("Test", "icons/33.jpg"));
		button_3.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		button_3.setBounds(261, 10, 20, 21);
		
		Group grpGrouding = new Group(parent, SWT.NONE);
		grpGrouding.setText("grounding:WsdlGrounding");
		grpGrouding.setBounds(15, 442, 284, 135);
		
		list_3 = new List(grpGrouding, SWT.BORDER);
		list_3.setBounds(10, 42, 271, 83);
		list_3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			
				String[] sels = ((List)e.getSource()).getSelection();
				if(sels.length > 0)
				{
					String name = sels[0];
					System.out.println(name);
					IWorkbenchPage wbp = getSite().getPage();
					IViewPart view = null;
					try {
						view = wbp.showView("GroundingPropertyView");
					} catch (PartInitException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					GroundingPropertyView v = (GroundingPropertyView)view;
					Text nameText = (Text)v.getText();
					nameText.setText(name);
				}
			}
		});
		Button button_9 = new Button(grpGrouding, SWT.NONE);
		button_9.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				InputDialog dialog = new InputDialog(getSite().getShell(),"title","please input","",null);
				String str = null;
				if(dialog.open() == InputDialog.OK)
				{
					str = dialog.getValue();
					System.out.println(str);
					list_3.add(str);
				}
				
			}
		});
		button_9.setImage(ResourceManager.getPluginImage("Test", "icons/11.jpg"));
		button_9.setBounds(213, 10, 21, 21);
		
		Button button_10 = new Button(grpGrouding, SWT.NONE);
		button_10.addSelectionListener(new SelectionAdapter(){
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				String[] sels = list_3.getSelection();
				if(sels.length == 0)
					return;
				else
				{
					for(String sel : sels)
					{
						list_3.remove(sel);
					}
				}
			}
		});
		button_10.setImage(ResourceManager.getPluginImage("Test", "icons/22.jpg"));
		button_10.setBounds(240, 10, 21, 21);
		
		Button button_11 = new Button(grpGrouding, SWT.NONE);
		button_11.addSelectionListener(new SelectionAdapter(){
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				String[] sels = list_3.getSelection();
				if(sels.length == 0)
					return;
				else
				{
					for(String sel : sels)
					{
						list_3.remove(sel);
					}
				}
			}
		});
		button_11.setImage(ResourceManager.getPluginImage("Test", "icons/33.jpg"));
		button_11.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		button_11.setBounds(264, 10, 20, 21);
		// TODO Auto-generated method stub

	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

	@Override
	public void propertyChange(PropertyChangeEvent event) {
		
		System.out.println("GET Event: "+event.getNewValue());
		
		String name = (String) event.getNewValue();
		
		Set<Entry<String, String>> set = ElementValue.owlPath.entrySet();
		
		for(Entry<String,String> entry : set)
		{
			if(entry.getKey().equals(name))
			{
				FilePath.filePath = entry.getValue();
				if(FilePath.filePath == null)
					return;
				OWLParser owl = new OWLParser(FilePath.filePath);
				owl.parse();
				owl.show(getSite().getWorkbenchWindow().getActivePage());
				return;
			}

		}
		IWorkbenchPage wbp = getSite().getPage();
		IViewPart view = null;
		try {
			view = wbp.showView("Hello.view2");
		} catch (PartInitException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		TV tv = (TV)view;
		tv.list.add(name+"Service");
		tv.list_1.add(name+"Profile");
		tv.list_2.add(name+"Process");
		tv.list_3.add(name+"Grounding");
		
		try {
			view = wbp.showView("Hello.view3");
		} catch (PartInitException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		ServicePropertyView SPV = (ServicePropertyView)view;
		SPV.getText().setText(name+"Service");
		SPV.text_des.setText(name+"Process");
		SPV.text_pre.setText(name+"Profile");
		SPV.text_sup.setText(name+"Grounding");
		
		try {
			view = wbp.showView("ProfilePropertyView");
		} catch (PartInitException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ProfilePropertyView PPV = (ProfilePropertyView)view;
		PPV.getText().setText(name+"Profile");
		PPV.text_preBy.setText(name+"Service");
		
		try {
			view = wbp.showView("ProcessPropertyView");
		} catch (PartInitException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		ProcessPropertyView ProcessV = (ProcessPropertyView)view;
		ProcessV.getText().setText(name+"Process");
		ProcessV.text_1.setText(name+"Service");
		
		try {
			view = wbp.showView("GroundingPropertyView");
		} catch (PartInitException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		GroundingPropertyView GPV = (GroundingPropertyView)view;
		GPV.getText().setText(name+"Grounding");
		GPV.text_1.setText(name+"Service");
		GPV.list.add(name+"AtomicProcessGrounding");
		
		
		
	}
}
