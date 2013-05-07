package dialog;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;

public class CandidateSerInfoDialog extends Dialog {

	public CandidateSerInfoDialog(Shell parentShell) {
		super(parentShell);
		// TODO Auto-generated constructor stub
	}
	public void configureShell(Shell newShell) {
		super .configureShell(newShell);
	}
	public Point getInitialSize() {
		return new Point(849, 526);
		}
	public Control createDialogArea(Composite parent) {
		Composite shell = (Composite) super .createDialogArea(parent);
		shell.setLayout(null);
		
		Label lblServiceID = new Label(shell, SWT.NONE);
		lblServiceID.setBounds(10, 10, 144, 17);
		lblServiceID.setText("serviceID:");
		
		Label ServiceIDLabel = new Label(shell, SWT.NONE);
		ServiceIDLabel.setBounds(164, 10, 244, 17);
		ServiceIDLabel.setText("1.1");
		
		Label lblServiceNameLabel = new Label(shell, SWT.NONE);
		lblServiceNameLabel.setText("serviceName:");
		lblServiceNameLabel.setBounds(10, 33, 144, 17);
		
		Label ServiceNameLabel = new Label(shell, SWT.NONE);
		ServiceNameLabel.setText("Service1");
		ServiceNameLabel.setBounds(164, 33, 244, 17);
		
		Label lblTextDescriptionLabel = new Label(shell, SWT.NONE);
		lblTextDescriptionLabel.setText("TextDescription:");
		lblTextDescriptionLabel.setBounds(10, 56, 144, 17);
		
		Label TextDescriptionLabel = new Label(shell, SWT.NONE);
		TextDescriptionLabel.setBounds(164, 56, 244, 17);
		
		Label lblContactInformationLabel = new Label(shell, SWT.NONE);
		lblContactInformationLabel.setText("ContactInformation:");
		lblContactInformationLabel.setBounds(10, 79, 144, 17);
		
		Label ContactInformationLabel = new Label(shell, SWT.NONE);
		ContactInformationLabel.setBounds(164, 79, 244, 17);
		
		Label lblServiceCategoryListLabel = new Label(shell, SWT.NONE);
		lblServiceCategoryListLabel.setText("ServiceCategoryList:");
		lblServiceCategoryListLabel.setBounds(10, 102, 144, 17);
		
		Label ServiceCategoryListLabel = new Label(shell, SWT.NONE);
		ServiceCategoryListLabel.setBounds(164, 102, 244, 17);
		
		Label lblInputsListLabel = new Label(shell, SWT.NONE);
		lblInputsListLabel.setText("InputsList:");
		lblInputsListLabel.setBounds(10, 125, 144, 17);
		
		Label InputsListLabel = new Label(shell, SWT.NONE);
		InputsListLabel.setBounds(164, 125, 244, 17);
		
		Label lblOutputsListLabel = new Label(shell, SWT.NONE);
		lblOutputsListLabel.setText("OutputsList:");
		lblOutputsListLabel.setBounds(10, 148, 144, 17);
		
		Label OutputsListLabel = new Label(shell, SWT.NONE);
		OutputsListLabel.setBounds(164, 148, 244, 17);
		
		Label lblResultListLabel = new Label(shell, SWT.NONE);
		lblResultListLabel.setText("ResultList:");
		lblResultListLabel.setBounds(10, 171, 144, 17);
		
		Label ResultListLabel = new Label(shell, SWT.NONE);
		ResultListLabel.setBounds(164, 171, 244, 17);
		
		Label lblServiceProductsLabel = new Label(shell, SWT.NONE);
		lblServiceProductsLabel.setText("ServiceProducts:");
		lblServiceProductsLabel.setBounds(10, 194, 144, 17);
		
		Label ServiceProductsLabel = new Label(shell, SWT.NONE);
		ServiceProductsLabel.setBounds(164, 194, 244, 17);
		return parent;
	}
	protected void buttonPressed(int id)
	{
		super.buttonPressed(id);
	}
	protected void initializeBounds(){
        Composite comp = (Composite)getButtonBar();
     
        super.createButton(comp, IDialogConstants.OK_ID, "设为候选服务", true);
        super.createButton(comp, IDialogConstants.CANCEL_ID, "Cancel", false);
        
    }
	protected void createButtonsForButtonBar(Composite parent) {
    }
}