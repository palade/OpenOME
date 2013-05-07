package edu.toronto.cs.openome.conversion.handler;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import edu.toronto.cs.openome.conversion.AbstractHandler;
import edu.toronto.cs.openome.conversion.convertor.CopyOfGRL2BPELConvertor;
import edu.toronto.cs.openome.conversion.convertor.GRL2BPELConvertor;

@SuppressWarnings("restriction")
public class GRL2BPELHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {

		try {
			CopyOfGRL2BPELConvertor con = new CopyOfGRL2BPELConvertor();
			String bpelFilePath = con.convert();
			openBPELFile(bpelFilePath);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean isHandled() {
		// TODO Auto-generated method stub
		return true;
	}

	public void openBPELFile(String filePath) throws PartInitException {

		IFile file = getFile(filePath);
		IWorkbenchPage page = PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getActivePage();
		org.eclipse.ui.ide.IDE.openEditor(page, file);

	}

}
