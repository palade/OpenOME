package hello.actions;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;

public class NameDialog extends Dialog {
	public List list ;
	public String value;
	public NameDialog(Shell parentShell) {
		super(parentShell);
		// TODO Auto-generated constructor stub
	}
	public Control createDialogArea(Composite parent) {
//		parent.setLayout(null);
		list = new List(parent,SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		list.setBounds(28, 30, 228, 224);
		list.add("rdfs:label");
		list.add("owl:versionInfo");
		list.add("rdfs:comment");
		list.add("rdfs:isDefindedBy");
		list.add("rdfs:seeAlso");
		
		return list;
	}
	
	protected void buttonPressed(int id)
	{
		if(id == IDialogConstants.OK_ID && list.getSelection().length > 0)
			value = list.getSelection()[0];
		super.buttonPressed(id);
			
	}
}
