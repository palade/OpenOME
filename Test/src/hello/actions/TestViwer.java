package hello.actions;

import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;

public class TestViwer extends ViewPart {
	public TestViwer() {
	}
	public ScrolledComposite scrolledComposite;
	public List list;
	@Override
	public void createPartControl(Composite parent) {
		
		scrolledComposite = new ScrolledComposite(parent, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		
		list = new List(scrolledComposite, SWT.BORDER);
		scrolledComposite.setContent(list);
		scrolledComposite.setMinSize(list.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		// TODO Auto-generated method stub
//		list.add("aa");
//		list.add("bb");
//		list.add("cc");
//		list.add("dd");
		this.addAction = new AddAction(this);
//		IActionBars actionBars = getViewSite().getActionBars();
//		IToolBarManager manager = actionBars.getToolBarManager();
//		manager.add(addAction);
		MyActionGroup group = new MyActionGroup();
		group.viewer = this;
		IActionBars actionBars = getViewSite().getActionBars();
		group.fillActionBars(actionBars);
//		IMenuManager menuManager = getViewSite().getActionBars().getMenuManager();
//		group.fillContextMenu(menuManager);
		list.addSelectionListener(new SelectionListener()
		{

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				System.out.println(((List)e.getSource()).getSelection()[0]);
			}
			
		});
	}
	private AddAction addAction ;
	
	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}
	public void fillContextMenu(IMenuManager manager)
	{
		
	}

}
