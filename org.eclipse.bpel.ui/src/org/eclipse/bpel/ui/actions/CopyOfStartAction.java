package org.eclipse.bpel.ui.actions;

import org.eclipse.bpel.ui.event.Observer;
import org.eclipse.bpel.ui.event.Publisher;
import org.eclipse.bpel.ui.event.ServiceMonitor;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

import edu.toronto.cs.openome.evaluation.listener.AbstractEvaluationListener;

public class CopyOfStartAction implements IWorkbenchWindowActionDelegate {

	private String name;

	public void run(IAction action) {

		Publisher p = new Publisher();
		Observer ob = new Observer();
		ob.addEvaluationListener(new AbstractEvaluationListener("Example.ood"));
		p.add(ob);

		ServiceMonitor sm = new ServiceMonitor(p);
		sm.start();
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {

	}

	@Override
	public void dispose() {

	}

	@Override
	public void init(IWorkbenchWindow window) {
		
//		String editorTitle = window.getActivePage().getActiveEditor()
//				.getTitle();
//		name = editorTitle.split(".")[0] + ".ood";

	}
}
