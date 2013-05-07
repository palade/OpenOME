package edu.toronto.cs.openome.evaluation.listener;

import java.util.Map;

import org.eclipse.ui.IEditorInput;

import edu.toronto.cs.openome_model.EvaluationLabel;

public interface EvaluationListener {
	public void evaluate(Map<String, EvaluationLabel> stas);
}
