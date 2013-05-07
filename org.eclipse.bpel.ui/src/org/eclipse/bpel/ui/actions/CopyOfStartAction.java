package org.eclipse.bpel.ui.actions;

import java.util.Iterator;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


import org.eclipse.bpel.common.extension.model.impl.ExtensionImpl;
import org.eclipse.bpel.common.extension.model.impl.ExtensionMapImpl;
import org.eclipse.bpel.model.impl.ActivityImpl;
import org.eclipse.bpel.model.impl.IfImpl;
import org.eclipse.bpel.model.impl.SequenceImpl;
import org.eclipse.bpel.model.resource.BPELResourceImpl;
import org.eclipse.bpel.ui.BPELEditor;
import org.eclipse.bpel.ui.BPELMultipageEditorPart;
import org.eclipse.bpel.ui.event.Observer;
import org.eclipse.bpel.ui.event.Publisher;
import org.eclipse.bpel.ui.event.Simulator;
import org.eclipse.bpel.ui.uiextensionmodel.impl.ActivityExtensionImpl;
import org.eclipse.bpel.ui.uiextensionmodel.impl.ProcessExtensionImpl;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceImpl;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.PlatformUI;

public class CopyOfStartAction implements IWorkbenchWindowActionDelegate {
	private int count = 0;
	public void run(IAction action)
	{
		
//		if(count == 5)
//			count = 0;
//		System.out.println("in start action---------------------------------------");
//		//String name = "Receive";
//		try
//		{
//			IWorkbenchWindow iww = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
//			IWorkbenchPage iwp = iww.getActivePage();
//			IEditorPart idp = iwp.getActiveEditor();
//			BPELMultipageEditorPart e = (BPELMultipageEditorPart)idp;
//			BPELEditor editor = (BPELEditor)e.getActiveEditor();
//			ResourceSet set = editor.getResourceSet();
//			
//			XMIResourceImpl xmires = null;
//			for(Resource res : set.getResources())
//			{
//				
//				if(res instanceof XMIResourceImpl)
//					xmires = (XMIResourceImpl)res;
//			}
//			List q = new LinkedList();
//			q.add("main");
//			q.add("While");
//			q.add("Assign");
//			q.add("Receive");
//			q.add("Invoke");
//	
//			for(EObject obj : xmires.getContents())
//			{
//				System.out.println(obj);
//				ExtensionMapImpl pi = (ExtensionMapImpl)obj;
//				System.out.println(pi.eContents().size());
//				
//				for(EObject o : pi.eContents())
//				{
//					ExtensionImpl ex = (ExtensionImpl)o;
//					if(ex.basicGetExtendedObject() instanceof ActivityImpl)
//					{
//						//System.out.println(ex.basicGetExtendedObject());
//						ActivityImpl ai = (ActivityImpl)ex.basicGetExtendedObject();
//						System.out.println(ai.getName());
//						if(ai.getName().equals(q.get(count)))
//						{
//							ai.setColor(new RGB(0,255,255));
//							System.out.println("after set color---------------------------------------------");
//						}
//						else
//						{
//							ai.setColor(new RGB(255,255,255));
//						}
//					}
//				}
//				
//			}
//			
//			
//		}
//		catch(Exception e)
//		{
//			e.printStackTrace();
//		}
//		count++;
		Publisher p = new Publisher();
		Observer ob = new Observer();
		p.add(ob);
		
		Simulator s = new Simulator();
		s.setPB(p);
		Thread t = new Thread(s);
		t.start();
	}

	
	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(IWorkbenchWindow window) {
		// TODO Auto-generated method stub
		
	}
}