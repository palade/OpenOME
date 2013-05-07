/**
 * 
 */
package edu.toronto.cs.openome.evaluation.handlers;

import java.util.List;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramCommandStack;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

import edu.toronto.cs.openome.evaluation.qualitativeautomaticreasoning.AutomaticQualReasoner;
import edu.toronto.cs.openome.evaluation.qualitativeinteractivereasoning.InteractiveQualReasoner;
import edu.toronto.cs.openome.evaluation.reasoning.Reasoning;
import edu.toronto.cs.openome_model.Alternative;
import edu.toronto.cs.openome_model.openome_modelFactory;
import edu.toronto.cs.openome_model.openome_modelPackage;
import edu.toronto.cs.openome_model.impl.ModelImpl;

/**
 * @author jenhork
 * 
 */
public class AutomaticQualReasonerHandler extends ReasonerHandler {

	public AutomaticQualReasonerHandler() {
		super();
	}

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

		Shell[] ar = PlatformUI.getWorkbench().getDisplay().getShells();

		Shell shell = ar[0];
		DiagramCommandStack dcs = null;

		try {
			dcs = getDiagramCommandStack();
		} catch (Exception e) {

			showMessage("Alternatives","Please select a diagram model", shell);
			return null;
		}

		openome_modelPackage _openome_modelPackage = openome_modelPackage.eINSTANCE;
		openome_modelFactory _openome_modelFactory = _openome_modelPackage
				.getopenome_modelFactory();

		/* Create an Alternative */
		Alternative alt = _openome_modelFactory.createAlternative();

		alt.setName("name");
		alt.setDescription("Des");
		alt.setDirection("forward");

		ModelImpl mi = getModelImpl();
		CommandStack cs = getCommandStack();
		List editParts = getEditParts();

		InteractiveQualReasoner iQualReasoner = new InteractiveQualReasoner(mi,
				cs, dcs, editParts, true);
		Reasoning reasoning = new Reasoning(iQualReasoner);

		// for now, remove later
		clearAllJudgments(mi, cs);

		reasoning.reason();

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
