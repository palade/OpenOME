package hello.actions;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.actions.ActionGroup;

public class MyActionGroup extends ActionGroup {
	public TestViwer viewer;
	 public void fillActionBars(IActionBars actionBars) {
	       
		 IToolBarManager manager = actionBars.getToolBarManager();
		 manager.add(new AddAction(viewer));
	    }
	    public void fillContextMenu(IMenuManager menu) {
	        // do nothing
	    	menu.add(new AddAction(viewer));
	    }
}
