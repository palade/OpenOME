package org.eclipse.bpel.ui.actions;

import java.util.Iterator;

import org.eclipse.bpel.ui.BPELEditor;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceImpl;
import org.eclipse.jface.action.Action;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

public class StartAction extends Action {
	public void run()
	{
		System.out.println("in start action---------------------------------------");
		String name = "test";
		try
		{
			IWorkbenchWindow iww = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
			IWorkbenchPage iwp = iww.getActivePage();
			IEditorPart idp = iwp.getActiveEditor();
			BPELEditor e = (BPELEditor)idp;
			ResourceSet set = e.getResourceSet();
			XMLResourceImpl xmires = null;
			for(Resource res : set.getResources())
			{
				if(res instanceof XMLResourceImpl)
					xmires = (XMLResourceImpl)res;
			}
			for(EObject obj : xmires.getContents())
			{
				EClass eClass = obj.eClass();
				Iterator iter = eClass.getEAllAttributes().iterator();
				while(iter.hasNext())
				{
					EAttribute ea = (EAttribute) iter.next();
					if(ea.getName().equals("name"))
					{
						String attrName = (String)obj.eGet(ea);
						if(attrName != null && attrName.equals(name))
							System.out.println("here---------------------------------------------------");
					}
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

	}
}
