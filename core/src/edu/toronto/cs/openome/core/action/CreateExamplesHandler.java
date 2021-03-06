/*******************************************************************************
 * Adapted from the Eclipse Monkey project
 * Contributors:
 *     Yijun Yu -- adapting it for OpenOME
 *     Bjorn Freeman-Benson - initial implementation
 *     Ward Cunningham - initial implementation
 *******************************************************************************/

package edu.toronto.cs.openome.core.action;

import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.osgi.framework.Bundle;

public class CreateExamplesHandler extends ExtensionReader implements org.eclipse.core.commands.IHandler {

	public CreateExamplesHandler() {	}
	
	Vector<IConfigurationElement> examples = null;
	
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkspace w = ResourcesPlugin.getWorkspace();
		IProject project = w.getRoot().getProject("Examples");
		try {
			if (!project.exists())
				project.create(null);
			project.open(null);
			examples = new Vector<IConfigurationElement>();
			readExtensions("core.example", "example", examples);
			for (IConfigurationElement example: examples) {
				String pluginname = example.getDeclaringExtension().getNamespaceIdentifier();
				Bundle bundle = Platform.getBundle(pluginname);
				String filename = example.getAttribute("file");
				String name = filename;
				String[] words = name.split("/");
				String pathName = "";
				IFolder folder = null;
				for (int i = 0; i < words.length - 1; i++) {
					String string = words[i];
					pathName = pathName + string + "/";
					folder = project.getFolder(pathName);
					try {
					if (!folder.exists())
						folder.create(IResource.FORCE, true, null);
					} catch (Exception e) {}
				}
				InputStream stream = FileLocator.openStream(bundle, new Path("samples/" + filename), false);
				IFile file;
				if (folder == null)
					file = project.getFile(name);
				else
					file = folder.getFile(words[words.length - 1]);
				try {
				if (!file.exists())
					file.create(stream, false, null);
				} catch (Exception e) {}
				stream.close();
			}		
		} catch (IOException e) {
			e.printStackTrace();
		} catch (CoreException e) {
			e.printStackTrace();
		}
		System.out.println("Example project created");
		return null;
	}

	public void selectionChanged(IAction action, ISelection selection) {
	}

	public void dispose() {
	}

	public void init(IWorkbenchWindow window) {
	}

	public void addHandlerListener(IHandlerListener handlerListener) {
		// TODO Auto-generated method stub
		
	}

	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	public boolean isHandled() {
		// TODO Auto-generated method stub
		return false;
	}

	public void removeHandlerListener(IHandlerListener handlerListener) {
		// TODO Auto-generated method stub
		
	}
}
