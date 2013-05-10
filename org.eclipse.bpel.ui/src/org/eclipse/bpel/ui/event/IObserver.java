package org.eclipse.bpel.ui.event;

import edu.toronto.cs.openome.evaluation.listener.EvaluationListener;

public interface IObserver {
	public void update(String name);
	public void addEvaluationListener(EvaluationListener eListener);
}
