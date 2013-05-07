package edu.toronto.cs.openome.conversion.handler;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.resources.IFile;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import edu.toronto.cs.openome.conversion.AbstractHandler;
import edu.toronto.cs.openome.conversion.convertor.GRL2BPELConvertor;
import edu.toronto.cs.openome.conversion.convertor.GRL2OWLConvertor;
import edu.toronto.cs.openome.conversion.discovery.AbstractServiceDiscovery;
import edu.toronto.cs.openome.conversion.discovery.IServiceDiscovery;

@SuppressWarnings("restriction")
public class ServiceDiscoveryHandler extends AbstractHandler implements
		IHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {

		try {

			GRL2OWLConvertor owlCon = new GRL2OWLConvertor();
			String owlFilePath = owlCon.convert();
			// To check if the BPEL file exists, if not, convert to
			String bpelFilePath = checkBPEL(owlFilePath);

			// Invoke the service discovery operation and get the result
			IServiceDiscovery sd = new AbstractServiceDiscovery();
			Map<String, List<String>> result = sd.findService(null, null, null);

			openBPELFile(bpelFilePath);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}

	public void openBPELFile(String filePath) throws PartInitException {

		IFile file = getFile(filePath);
		IWorkbenchPage page = PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getActivePage();
		org.eclipse.ui.ide.IDE.openEditor(page, file);

		PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
				.showView("Hello.viewForTest");

	}

	private String checkBPEL(String filePath) throws InvocationTargetException,
			InterruptedException {

		String pathWithoutExt = filePath
				.substring(0, filePath.lastIndexOf("."));
		String bpelFilePath = pathWithoutExt + ".bpel";
		if (getFile(bpelFilePath).getLocation().toFile().exists() == false) {
			GRL2BPELConvertor con = new GRL2BPELConvertor();
			bpelFilePath = con.convert();
		}

		return bpelFilePath;

	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean isHandled() {
		return true;
	}

}
