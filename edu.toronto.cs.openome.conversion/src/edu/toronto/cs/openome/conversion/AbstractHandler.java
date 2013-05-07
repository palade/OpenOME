package edu.toronto.cs.openome.conversion;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramCommandStack;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramWorkbenchPart;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.protege.editor.owl.model.hierarchy.roots.Path;

import edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramEditor;
import edu.toronto.cs.openome_model.impl.ModelImpl;

@SuppressWarnings("restriction")
public class AbstractHandler implements IHandler {

	private Openome_modelDiagramEditor mDE;

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		return null;
	}

	@Override
	public void addHandlerListener(IHandlerListener handlerListener) {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isHandled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void removeHandlerListener(IHandlerListener handlerListener) {
		// TODO Auto-generated method stub

	}

	public URI getModelURI() {
		EditingDomain editingDomain = mDE.getEditingDomain();
		ResourceSet resourceSet = editingDomain.getResourceSet();

		for (Resource tmp : resourceSet.getResources()) {
			if (tmp instanceof XMIResourceImpl) {
				return tmp.getURI();
			}
		}
		return null;
	}

	public ModelImpl getModelImpl() {
		getModelDiagramEditor();

		EditingDomain editingDomain = mDE.getEditingDomain();

		ResourceSet resourceSet = editingDomain.getResourceSet();

		XMIResourceImpl xmires = null;

		for (Resource tmp : resourceSet.getResources()) {
			if (tmp instanceof XMIResourceImpl) {
				xmires = (XMIResourceImpl) tmp;
			}
		}

		ModelImpl model = null;

		for (EObject tmp2 : xmires.getContents()) {
			if (tmp2 instanceof ModelImpl)
				model = (ModelImpl) tmp2;
		}

		return model;

	}

	private void getModelDiagramEditor() {

		try {
			IWorkbenchWindow iww = PlatformUI.getWorkbench()
					.getActiveWorkbenchWindow();

			IWorkbenchPage iwp = iww.getActivePage(); // assume correct page is
														// showing ... dubious

			IEditorPart iep = iwp.getActiveEditor(); //

			mDE = (Openome_modelDiagramEditor) iep; //

		} catch (Exception e) {
			System.out.println("Exception getting modelEditor");
		}

	}

	public DiagramCommandStack getDiagramCommandStack() {
		IWorkbenchWindow iww = PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow();

		IWorkbenchPage iwp = iww.getActivePage(); // assume correct page is
													// showing ... dubious
		IWorkbenchPart part = iwp.getActivePart();
		IDiagramWorkbenchPart diagramPart = (IDiagramWorkbenchPart) part;
		return diagramPart.getDiagramEditDomain().getDiagramCommandStack();
	}

	public CommandStack getCommandStack() {
		EditingDomain editingDomain = mDE.getEditingDomain();

		CommandStack cs = editingDomain.getCommandStack();

		return cs;
	}

	public IFile getFile(String filePath) {

		String tmp = filePath.substring(1);
		int index = tmp.indexOf("/");
		String peojectName = tmp.substring(0, index);
		String filePathName = tmp.substring(index);
		IProject project = ResourcesPlugin.getWorkspace().getRoot()
				.getProject(peojectName);
		IFile file = project.getFile(new org.eclipse.core.runtime.Path(
				filePathName));

		return file;

	}

}
