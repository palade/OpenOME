package org.eclipse.bpel.ui.actions;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

public class ChangeColorToBlueAction implements IWorkbenchWindowActionDelegate {

	@Override
	public void run(IAction action) {
		// TODO Auto-generated method stub
		ColorHolder.setColor(new RGB(198,226,255));
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
