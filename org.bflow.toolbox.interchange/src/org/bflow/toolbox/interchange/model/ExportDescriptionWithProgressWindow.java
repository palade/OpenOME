package org.bflow.toolbox.interchange.model;

import java.io.File;
import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;

/**
 * this class offers the ability to run an export script while the user only sees an ProgessDialog<p/>
 * this is useful when you expect that the export will take some time
 * @author Arian Storch
 * @since 25/05/11
 * @version 07/06/11
 *
 */
public class ExportDescriptionWithProgressWindow extends ExportDescription implements IRunnableWithProgress
{
	private ExportDescription expDescr;
	private File srcFile;
	private File tgtFile;
	
	/**
	 * default constructor
	 * @param expDescr ExportDescription to run
	 * @param srcFile source file
	 * @param targetFile target file
	 * @see ExportDescription
	 */
	public ExportDescriptionWithProgressWindow(ExportDescription expDescr, File srcFile, File targetFile)
	{
		super(null, null, "");
		this.srcFile = srcFile;
		this.tgtFile = targetFile;
		this.expDescr = expDescr;
	}

	@Override
	public void run(IProgressMonitor monitor) throws InvocationTargetException,
			InterruptedException {
		
		if(!tgtFile.canWrite()) {
			MessageDialog.openError(null, "Error", "Cannot write the targeted file! Check path or " +
					"permissions.");
			return ;
		}
		
		ExportDescription.run(expDescr, srcFile, tgtFile);
		
	}

}
