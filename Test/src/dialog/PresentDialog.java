package dialog;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

public class PresentDialog extends Dialog {
	public Tree tree = null;
	public String name = null;
	public PresentDialog(Shell parentShell) {
		super(parentShell);
		// TODO Auto-generated constructor stub
	}
	public void configureShell(Shell newShell) {
		super .configureShell(newShell);
		newShell.setText( "Select type of new resource..." );
	}
	public Point getInitialSize() {
		return new Point(300, 399);
		}
	public Control createDialogArea(Composite parent) {
		Composite shell = (Composite) super .createDialogArea(parent);
		GridLayout gridLayout = (GridLayout) shell.getLayout();
		gridLayout.numColumns = 2;
		
		 tree = new Tree(shell, SWT.BORDER);
		tree.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 2));
		
		TreeItem item1 = new TreeItem(tree,SWT.NONE);
		item1.setText("service:ServiceProfile");
		
		TreeItem item2 = new TreeItem(item1,SWT.NONE);
		item2.setText("profile:Profile");
		
		
		return parent;
		}
	protected void buttonPressed(int id)
	{
		if(id == IDialogConstants.OK_ID && tree.getSelection().length > 0)
			name = tree.getSelection()[0].getText();
		super.buttonPressed(id);
			
	}
}
