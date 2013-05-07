package org.eclipse.bpel.ui.event;

import java.util.TimerTask;

import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

public class OpenEditorTimerTask extends TimerTask {

	private IEditorInput input;
	private String id;
	private IWorkbenchPage iwp;

	public OpenEditorTimerTask(IWorkbenchPage iwp, IEditorInput input, String id) {
		this.input = input;
		this.id = id;
		this.iwp = iwp;
	}

	@Override
	public void run() {
//		IWorkbenchWindow iww = PlatformUI.getWorkbench()
//				.getActiveWorkbenchWindow();
//		IWorkbenchPage iwp = iww.getActivePage();

		try {
			iwp.openEditor(input, id);
		} catch (PartInitException e) {
			e.printStackTrace();
		}
	}

}
