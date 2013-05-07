package org.bflow.toolbox.interchange.pages;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.bflow.toolbox.i18n.MessageProvider;
import org.bflow.toolbox.interchange.model.ImportDescription;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.ContainerSelectionDialog;

/**
 * This class provides the import wizard page.
 * @author Arian Storch
 * @since 17/08/09
 * @version 13/07/11
 *
 */
public class ImportWizardPage extends WizardPage 
{
	private List<ImportDescription> importDescriptions = new ArrayList<ImportDescription>();
	
	private String fileExtensions[];
	
	private Text textFieldFile;
	
	private Combo cbImportDescriptions;
	
	private ImportDescription selectedImportDescription = null;
	
	private Text textFieldDescription;
	
	private Text textFieldTarget;
	
	private String basicPath;
	
	/**
	 * constructor
	 */
	public ImportWizardPage() 
	{
		super("Add-ons import wizard page");
		this.setTitle(MessageProvider.getMessage("ImportWizardPage#msg1"));
		this.setDescription(MessageProvider.getMessage("ImportWizardPage#msg2"));
	}

	
	@Override
	public void createControl(Composite parent) 
	{
		final Composite composite = new Composite(parent, SWT.NONE);
		
		composite.setLayout(new GridLayout(2, false));
		
		Label lblFormat = new Label(composite, SWT.NONE);
		lblFormat.setText(MessageProvider.getMessage("ImportWizardPage#msg3"));
		
		cbImportDescriptions = new Combo(composite, SWT.READ_ONLY);
		prepareComboBox();
		
		Label lblDescription = new Label(composite, SWT.NONE);
		lblDescription.setText(MessageProvider.getMessage("ImportWizardPage#msg4"));
		
		textFieldDescription = new Text(composite, SWT.READ_ONLY);
		
		GridData txtDescriptionGridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL
																| GridData.GRAB_HORIZONTAL);
		txtDescriptionGridData.widthHint = 200;
		txtDescriptionGridData.heightHint = 50;

		textFieldDescription.setLayoutData(txtDescriptionGridData);
		
		Label lblSelectFile = new Label(composite, SWT.NONE);
		lblSelectFile.setText(MessageProvider.getMessage("ImportWizardPage#msg5"));
		
		textFieldFile = new Text(composite, SWT.BORDER);
		
		GridData textFieldFileGridData = new GridData(SWT.BEGINNING, SWT.BEGINNING, true, false);
		textFieldFileGridData.widthHint = 400;
		
		textFieldFile.setLayoutData(textFieldFileGridData);
		
		Label nullLbl = new Label(composite, SWT.NONE);
		nullLbl.setText("");
		
		Button btnBrowse = new Button(composite, SWT.NONE);
		
		btnBrowse.setText(MessageProvider.getMessage("ImportWizardPage#msg6")+"...");
		
		btnBrowse.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {				
			}

			@Override
			public void widgetSelected(SelectionEvent e) 
			{
				if(selectedImportDescription == null)
					return ;
								
				FileDialog fd = new FileDialog(composite.getShell(), SWT.OPEN | SWT.MULTI);
				
				Vector<String> ext = new Vector<String>();
				
				for(String s:selectedImportDescription.getFileExtension().split(";"))
					ext.add("*."+s);
				
				ext.add("*.*");
								
				fd.setFilterExtensions(ext.toArray(new String[ext.size()]));
				             
	            String ret = fd.open();
	            
	            if(ret == null)
	            	return ;
	            
	            String files[] = fd.getFileNames();
	            basicPath = fd.getFilterPath();
				
				if(files.length > 1)
				{
					String filelist = "";
					
					for(int i = 0; i < files.length-1; i++)
						filelist += ""+files[i]+"; ";
					
					filelist += files[files.length-1];
					
					textFieldFile.setText(filelist);
				}
				else
					textFieldFile.setText(files[0]);
				
				
			}});
		
		Label lblTargetDir = new Label(composite, SWT.NONE);
		lblTargetDir.setText(MessageProvider.getMessage("ImportWizardPage#msg7"));
		
		textFieldTarget = new Text(composite, SWT.BORDER);
		
		//IProject projects[] = ResourcesPlugin.getWorkspace().getRoot().getProjects();
		
		//if(projects.length > 0)
			//textFieldTarget.setText(projects[0].getLocation().toFile().getAbsolutePath());
		
		textFieldTarget.setText("");
		
		GridData textFieldTargetGridData = new GridData(SWT.BEGINNING, SWT.BEGINNING, true, false);
		textFieldTargetGridData.widthHint = 400;
		
		textFieldTarget.setLayoutData(textFieldTargetGridData);
		
		Label lblNull = new Label(composite, SWT.NONE);
		lblNull.setText("");
		
		Button btnBrowseTarget = new Button(composite, SWT.NONE);
		btnBrowseTarget.setText(MessageProvider.getMessage("ImportWizardPage#msg8")+"...");
		
		btnBrowseTarget.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {				
			}

			@Override
			public void widgetSelected(SelectionEvent e) 
			{
				IWorkspaceRoot wsRoot = ResourcesPlugin.getWorkspace().getRoot();
				
				ContainerSelectionDialog dlg = new ContainerSelectionDialog(getShell(), 
						wsRoot, true, null);
				
				if(dlg.open() == ContainerSelectionDialog.CANCEL)
					return ;
				
				Object selections[] = dlg.getResult();
				
				if(selections.length == 0)
					return ;
				
				IPath selPath = (IPath) selections[0];
				IPath target = wsRoot.getLocation().append(selPath);
				String osFile = target.toFile().getAbsolutePath();
				
				textFieldTarget.setText(osFile);
				
			}});
		
		this.setControl(composite);
	}
	
	private void prepareComboBox()
	{
		for(ImportDescription impDesc:importDescriptions)
			cbImportDescriptions.add(""+impDesc.getName()+" (*."+impDesc.getFileExtension()+")");
		
		cbImportDescriptions.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {				
			}

			@Override
			public void widgetSelected(SelectionEvent e) 
			{
				selectedImportDescription = importDescriptions.get(cbImportDescriptions.getSelectionIndex());
				
				textFieldDescription.setText(selectedImportDescription.getDescription().replaceAll("//", "\r\n"));
				
			}});
		
		cbImportDescriptions.setVisibleItemCount(10);
	}
		
	/**
	 * returns the text field that contains the name of the selected file
	 * @return text field with name of the selected file
	 */
	public Text getTextFieldFile() {
		return textFieldFile;
	}
	
	/**
	 * returns the selected import description
	 * @return selected import description
	 */
	public ImportDescription getSelectedImportDescription() {
		return selectedImportDescription;
	}
	
	/**
	 * returns the text field that contains the target path
	 * @return text field containing target path
	 */
	public Text getTextFieldTarget() {
		return textFieldTarget;
	}
	
	/**
	 * returns the vector containing the import descriptions
	 * @return vector containing the import descriptions
	 */
	public List<ImportDescription> getImportDescriptions() {
		return importDescriptions;
	}
	
	/**
	 * sets the vector containing the import descriptions
	 * @param importDescriptions vector containing the import descriptions
	 */
	public void setImportDescriptions(
			List<ImportDescription> importDescriptions) {
		this.importDescriptions = importDescriptions;
		
		/*
		 * updating the file extensions
		 */
		fileExtensions = new String[importDescriptions.size()];
		
		for(int i = 0; i < fileExtensions.length; i++)
			fileExtensions[i] = "*."+importDescriptions.get(i).getFileExtension();
	}
	
	/**
	 * returns the basic path of the selected files
	 * @return basic path of the selected files
	 */
	public String getBasicPath() {
		return basicPath;
	}
	
	

}
