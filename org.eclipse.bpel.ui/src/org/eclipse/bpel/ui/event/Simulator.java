package org.eclipse.bpel.ui.event;

import java.util.Date;
import java.util.Timer;

import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

public class Simulator
{

	private static Simulator	instance;
	private IEditorInput		bpelInput;
	private IEditorInput		goalInput;

	private IWorkbenchPage		iwp;

	Simulator()
	{

	}

	public void setBPELInput(IEditorInput bpelInput)
	{
		this.bpelInput = bpelInput;
	}

	public void setGoalInput(IEditorInput goalInput)
	{
		this.goalInput = goalInput;
	}

	public void setWorkbenchPage(IWorkbenchPage page)
	{
		this.iwp = page;
	}

	public static Simulator getInstance()
	{
		if (instance == null)
			instance = new Simulator();
		return instance;
	}

	public void sim()
	{

		Publisher p = new Publisher();
		Observer ob = new Observer();
		p.add(ob);

		ProcessBPELTimerTask timerTask = new ProcessBPELTimerTask();
		timerTask.setPB(p);

		Timer timer = new Timer();

		Date date = new Date();

		timer.scheduleAtFixedRate(timerTask, date, 2000);
	}
}
