package org.bflow.toolbox.interchange.wizard;

import java.io.File;
import java.util.List;
import org.bflow.toolbox.i18n.MessageProvider;
import org.bflow.toolbox.interchange.model.ImportDescription;
import org.bflow.toolbox.interchange.pages.ImportWizardPage;
import org.bflow.toolbox.interchange.store.ImportscriptStore;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IImportWizard;
import org.eclipse.ui.IWorkbench;

/**
 * This class provides the import wizard.
 * 
 * @author Arian Storch
 * @since 17/08/09
 * @version 13/07/11
 * 
 */
public class ImportWizard extends Wizard implements IImportWizard {
	private ImportWizardPage importWizardPage;

	@Override
	public boolean performFinish() {
		String sourceFile = importWizardPage.getTextFieldFile().getText();
		String targetFile = importWizardPage.getTextFieldTarget().getText();
		ImportDescription importDescription = importWizardPage
				.getSelectedImportDescription();

		if (importDescription == null) {
			importWizardPage.setErrorMessage(MessageProvider
					.getMessage("ImportWizard#msg1"));
			return false;
		}

		if (sourceFile.isEmpty()) {
			importWizardPage.setErrorMessage(MessageProvider
					.getMessage("ImportWizard#msg2"));
			return false;
		}

		if (targetFile.isEmpty()) {
			importWizardPage.setErrorMessage(MessageProvider
					.getMessage("ImportWizard#msg3"));
			return false;
		}

		String fileNames[] = sourceFile.split("; ");
		String bPath = importWizardPage.getBasicPath();

		for (String fileName : fileNames)
			if (!importDescription.run(new File(bPath + "/" + fileName),
					new File(targetFile), true)) {
				MessageDialog.openError(this.getShell(), MessageProvider
						.getMessage("ImportWizard#msg4"), MessageProvider
						.getMessage("ImportWizard#msg5"));

				return false;
			}

		try {
			ResourcesPlugin.getWorkspace().getRoot().refreshLocal(
					IResource.DEPTH_INFINITE, null);
		} catch (CoreException e) {
			e.printStackTrace();
		}

		return true;
	}

	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		this.setWindowTitle(MessageProvider.getMessage("ImportWizard#msg6"));

		importWizardPage = new ImportWizardPage();

		this.addPage(importWizardPage);

		importWizardPage
				.setImportDescriptions(resolveAvailableImportDescriptions());
	}

	private List<ImportDescription> resolveAvailableImportDescriptions() {
		return ImportscriptStore.getDepository();
	}

}
