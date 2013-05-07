package org.bflow.toolbox.interchange.pages;

import java.util.List;

import org.bflow.toolbox.i18n.MessageProvider;
import org.bflow.toolbox.interchange.model.ExportDescription;
import org.eclipse.core.resources.IFile;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * defines the export wizard page used by the export wizard
 * @author Arian Storch
 * @since 14/08/09
 * @version 07/06/11
 *
 */
public class ExportWizardPage extends WizardPage 
{
	private IStructuredSelection selectedFiles;
	
	private Combo cbExportTypes;
	
	private List<ExportDescription> exportDescriptions;
	
	private Text textFieldTargetFile;
	private Text textFieldSourceFile;
	private Text txtDescription;
	
	private ExportDescription selectedExportDescription;
	
	/**
	 * constructor
	 * @param selectedFile selected file into the workbench
	 */
	public ExportWizardPage(IStructuredSelection selectedFiles)
	{
		super("Add-ons Export Wizard");
		this.setTitle(MessageProvider.getMessage("ExportWizardPage#msg1"));
		this.setDescription(MessageProvider.getMessage("ExportWizardPage#msg2"));
		this.setPageComplete(true);
		
		this.selectedFiles = selectedFiles;
	}
	
	@Override
	public void createControl(Composite parent) 
	{
		final Composite composite = new Composite(parent, SWT.NONE);
		
		composite.setLayout(new GridLayout(2, false));
		composite.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_FILL
				| GridData.HORIZONTAL_ALIGN_FILL));
		
		Label lblSelDiagram = new Label(composite, SWT.NONE);
		lblSelDiagram.setText(MessageProvider.getMessage("ExportWizardPage#msg3"));
		
		textFieldSourceFile = new Text(composite, SWT.BORDER);
		
		String selectionString = "";
		
		for(Object f:selectedFiles.toArray())
			selectionString += ((IFile)f).getName()+" ";
		
		textFieldSourceFile.setText(selectionString);
		textFieldSourceFile.setEditable(false);
		
		GridData txtSelFileGridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL
																| GridData.GRAB_HORIZONTAL);
		txtSelFileGridData.widthHint = 150;
		textFieldSourceFile.setLayoutData(txtSelFileGridData);
		
		Label lblSelExportType = new Label(composite, SWT.NONE);
		lblSelExportType.setText(MessageProvider.getMessage("ExportWizardPage#msg4"));
		
		cbExportTypes = new Combo(composite, SWT.BORDER | SWT.READ_ONLY);
		prepareComboBox();
		
		Label lblDescription = new Label(composite, SWT.NONE);
		lblDescription.setText(MessageProvider.getMessage("ExportWizardPage#msg5"));
		
		txtDescription = new Text(composite, SWT.NONE | SWT.READ_ONLY);
		txtDescription.setText("");
		
		GridData txtDescriptionGridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL
																| GridData.GRAB_HORIZONTAL);
		txtDescriptionGridData.widthHint = 200;
		txtDescriptionGridData.heightHint = 50;
		
		txtDescription.setLayoutData(txtDescriptionGridData);
		
		Label lblTargetFile = new Label(composite, SWT.NONE);
		lblTargetFile.setText(MessageProvider.getMessage("ExportWizardPage#msg6"));
		
		Composite fileSelection = new Composite(composite, SWT.NONE);
		
		GridLayout fileSelectionLayout = new GridLayout(2, false);
		fileSelectionLayout.marginLeft = 0; 
		fileSelectionLayout.horizontalSpacing = 0;
		
		fileSelection.setLayout(fileSelectionLayout);
		fileSelection.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_FILL
														| GridData.HORIZONTAL_ALIGN_FILL));
		
		textFieldTargetFile = new Text(fileSelection, SWT.BORDER);
		textFieldTargetFile.setText("");
		textFieldTargetFile.setEnabled(false);
		
		GridData txtTargetFileGridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL
																| GridData.GRAB_HORIZONTAL);
		
		txtTargetFileGridData.widthHint = 200;
		textFieldTargetFile.setLayoutData(txtTargetFileGridData);
		
		Button btnBrowse = new Button(fileSelection, SWT.NONE);
		btnBrowse.setText(MessageProvider.getMessage("ExportWizardPage#msg7"));
		btnBrowse.addSelectionListener(new SelectionListener(){

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {				
			}

			@Override
			public void widgetSelected(SelectionEvent e) 
			{				
				DirectoryDialog dialog = new DirectoryDialog(composite.getShell());
				
				dialog.setFilterPath(null);
				dialog.setMessage(MessageProvider.getMessage("ExportWizardPage#msg8"));
				dialog.setText("Folder Selection");
				String path = dialog.open();
				if (path != null) {
					textFieldTargetFile.setText(path);
					//Update buttons.
					getWizard().getContainer().updateButtons();
				}
			}});
		
		this.setControl(composite);
	}
	
	/**
	 * sets the export description files
	 * @param exportDescriptions
	 */
	public void setExportDescriptions(
			List<ExportDescription> exportDescriptions) {
		this.exportDescriptions = exportDescriptions;
	}
	
	private void prepareComboBox()
	{		
		if(exportDescriptions == null)
			return ;
		
		for(ExportDescription expDescr:exportDescriptions)
			cbExportTypes.add(expDescr.getName()+" (*."+expDescr.getFileExtension()+")");
		
		cbExportTypes.addSelectionListener(new SelectionListener()
		{

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {				
			}

			@Override
			public void widgetSelected(SelectionEvent e) 
			{
				int selIndex = cbExportTypes.getSelectionIndex();
				
				if(selIndex == -1)
					return ;
				
				ExportDescription exportDescription = exportDescriptions.get(selIndex);
				
				selectedExportDescription = exportDescription;
				
				txtDescription.setText(exportDescription.getDescription());
				
				//Update buttons.
				getWizard().getContainer().updateButtons();
				
			}});
		
		cbExportTypes.setVisibleItemCount(10);
	}
	
	/**
	 * Return the selection index of the combo box.
	 * @return the selection index of the combo box.
	 */
	public int getSelectionIndex() {
		return cbExportTypes.getSelectionIndex();
	}
	
	/**
	 * returns the text field that contains the path for the source file
	 * @return text field containing path to the source file
	 */
	public Text getTextFieldSourceFile() {
		return textFieldSourceFile;
	}
	
	/**
	 * returns the text field that contains the path for the target file
	 * @return text field containing path to the target file
	 */
	public Text getTextFieldTargetFile() {
		return textFieldTargetFile;
	}
	
	/**
	 * returns the export description that has been selected or currently is<br/>
	 * if none is selected null will be returned
	 * @return selected export description or null
	 */
	public ExportDescription getSelectedExportDescription() {
		return selectedExportDescription;
	}
	
	

}
