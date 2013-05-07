package org.bflow.toolbox.interchange.wizard;

import java.io.File;
import java.util.List;

import org.bflow.toolbox.i18n.MessageProvider;
import org.bflow.toolbox.interchange.model.ExportDescription;
import org.bflow.toolbox.interchange.pages.ExportWizardPage;
import org.bflow.toolbox.interchange.store.ExportscriptStore;
import org.eclipse.core.resources.IFile;
import org.eclipse.gef.EditPart;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IExportWizard;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;

/**
 * Defines a wizard for exporting bflow diagrams by xslt scripts.
 * @author Arian Storch
 * @since 14/08/09
 * @version 07/06/11
 *
 */
public class ExportWizard extends Wizard implements IExportWizard 
{	
	private ExportWizardPage exportWizardPage;
	
	private IStructuredSelection selection;
	
	public boolean canFinish() {

		return (exportWizardPage.getSelectionIndex() >= 0 && !exportWizardPage.getTextFieldTargetFile().getText().isEmpty());
	}
	
	@Override
	public boolean performFinish() 
	{		
		String targetFile = exportWizardPage.getTextFieldTargetFile().getText();
		//String sourceFile = exportWizardPage.getTextFieldSourceFile().getText();
		
		ExportDescription exportDescription = exportWizardPage.getSelectedExportDescription();
		
		if(exportDescription == null)
		{
			exportWizardPage.setMessage(MessageProvider.getMessage("ExportWizard#msg1"), 
																		WizardPage.ERROR);
			
			return false; 
		}
		
		if(targetFile.isEmpty())
		{
			exportWizardPage.setMessage(MessageProvider.getMessage("ExportWizard#msg2"), 
																		WizardPage.ERROR);
			
			return false;
		}
		
		if(selection.size() > 1) // mutiple files are selected
		{
			Object selFiles[] = selection.toArray();
			
			for(int i = 0; i < selFiles.length; i++)
			{
				IFile sFile = (IFile)selFiles[i];
				
				if(!exportDescription.run(sFile.getLocation().toFile(), 
										new File(targetFile), true, true))
					{
						MessageDialog.openError(this.getShell(), MessageProvider.getMessage("ExportWizard#msg3"),
																MessageProvider.getMessage("ExportWizard#msg4"));
					
						return false;
					}
			}
			
			return true;
		}
		else					// only one file is selected
			return exportDescription.run(((IFile)selection.getFirstElement()).getLocation().toFile(), 
																			new File(targetFile), true, true);
	}

	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) 
	{	    
		this.setWindowTitle("Add-ons Export Wizard");
		
	    if (selection != null && !selection.isEmpty()) 
	    {	    	
	    	this.selection = selection;
	    	
	    	Object obj = selection.getFirstElement();
	    	
	    	if(!(obj instanceof IFile))
    		{
	    		if(obj instanceof EditPart)
    			{
	    			EditPart part = (EditPart)obj;
	    			
	    			if(!part.getClass().getName().equalsIgnoreCase("org.bflow.toolbox.epc.diagram.edit.parts.EpcEditPart")) {
	    				part = part.getParent();
	    			}

    				IEditorPart activeEditor = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
					
					if (activeEditor != null) 
		            {
						if(activeEditor.isDirty())
							if(MessageDialog.openQuestion(activeEditor.getSite().getShell(), 
									MessageProvider.getMessage("ExportWizard#msg8"),
									MessageProvider.getMessage("ExportWizard#msg7")))
								activeEditor.getSite().getPage().saveEditor(activeEditor, false);
							else
								return ;							
						
		                IEditorInput input = activeEditor.getEditorInput();
		                if (input instanceof IFileEditorInput) 
		                {
		                	final IFile resource = ((IFileEditorInput) input).getFile();
		                	
		                	this.selection = new StructuredSelection(resource);			                	
		                }
		            }
	    			
    			}
	    		else
	    		{
		    		MessageDialog.openInformation(workbench.getDisplay().getActiveShell(), 
		    											MessageProvider.getMessage("ExportWizard#msg5"),
		    											MessageProvider.getMessage("ExportWizard#msg6"));
	    		
		    		return ;
	    		}
	    			
    		}
	    									
	    	//if(!workbench.saveAllEditors(true))
	    		//return ;
	       
	    	exportWizardPage = new ExportWizardPage(this.selection);
			
	    	exportWizardPage.setExportDescriptions(resolveAvailableExportDescriptions());
	       
	    	this.addPage(exportWizardPage);
	    }		
	}
	
	private List<ExportDescription> resolveAvailableExportDescriptions() {
		return ExportscriptStore.getDepository();
	}

}
