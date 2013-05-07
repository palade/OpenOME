package hello.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.InputDialog;

public class AddAction extends Action {
	private TestViwer viwer;
	public void run()
	{
		InputDialog dialog = new InputDialog(viwer.getSite().getShell(),"title","please input","",null);
		String str = null;
		if(dialog.open() == InputDialog.OK)
		{
			str = dialog.getValue();
			System.out.println(str);
		}
		viwer.list.add(str);
	}
	
	public AddAction(TestViwer viwer)
	{
		this.viwer = viwer;
	}
	
}
