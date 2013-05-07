package edu.toronto.cs.openome.evaluation.listener;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;

import edu.toronto.cs.openome_model.EvaluationLabel;

public class TestListenerHandler implements IHandler {

	@Override
	public void addHandlerListener(IHandlerListener handlerListener) {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {

		Map<String, EvaluationLabel> stas = new HashMap<String, EvaluationLabel>();

		// stas.put("Security", EvaluationLabel.SATISFIED);
		stas.put("Restrict Structure of Password",
				EvaluationLabel.PARTIALLY_SATISFIED);
		stas.put("Ask for Secret Question", EvaluationLabel.PARTIALLY_SATISFIED);

		AbstractEvaluationListener l = new AbstractEvaluationListener(null);
		l.evaluate(stas);

		return null;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isHandled() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void removeHandlerListener(IHandlerListener handlerListener) {
		// TODO Auto-generated method stub

	}

}
