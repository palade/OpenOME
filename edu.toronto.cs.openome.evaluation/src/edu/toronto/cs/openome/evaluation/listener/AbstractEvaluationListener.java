package edu.toronto.cs.openome.evaluation.listener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.gmf.runtime.common.ui.action.AbstractActionHandler;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import edu.toronto.cs.openome.evaluation.commands.RemoveHumanJudgmentCommand;
import edu.toronto.cs.openome.evaluation.commands.RemoveReverseJudgmentCommand;
import edu.toronto.cs.openome.evaluation.commands.SetQualitativeEvaluationLabelCommand;
import edu.toronto.cs.openome.evaluation.qualitativeinteractivereasoning.InteractiveQualReasoner;
import edu.toronto.cs.openome.evaluation.reasoning.Reasoning;
import edu.toronto.cs.openome_model.EvaluationLabel;
import edu.toronto.cs.openome_model.HumanJudgment;
import edu.toronto.cs.openome_model.Intention;
import edu.toronto.cs.openome_model.diagram.edit.commands.HighlightIntentionsCommand;
import edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramEditor;
import edu.toronto.cs.openome_model.impl.ModelImpl;

public class AbstractEvaluationListener implements EvaluationListener {

	private Openome_modelDiagramEditor mDE;
	private CommandStack cs;
	private ModelImpl grlModel;
	private List editParts;
	private String modelName;

	// private DiagramCommandStack dcs;

	public AbstractEvaluationListener(String modelName) {
		this.modelName = modelName;
		getModelDiagramEditor();
		getCommandStack();
		getModelImpl();
		getEditParts();

		// getDiagramCommandStack();
	}

	@Override
	public void evaluate(Map<String, EvaluationLabel> stas) {
		// openGRLEditor();
		setEvaluationLabel(stas);
		// reason();
		// Set<String> keySet = stas.keySet();
		// for (String key : keySet) {
		// EvaluationLabel l = stas.get(key);
		// System.out.println(key + " : " + l.getName());
		// }

	}

	private void openGRLEditor() {
		IWorkbenchWindow iww = PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow();
		IWorkbenchPage iwp = iww.getActivePage();
		try {
			iwp.openEditor(mDE.getEditorInput(),
					"edu.toronto.cs.openome_model.presentation.openome_modelEditorID");
		} catch (PartInitException e) {
			e.printStackTrace();
		}

	}

	private void setEvaluationLabel(Map<String, EvaluationLabel> stas) {

		if (cs == null) {
			getCommandStack();
		}
		if (grlModel == null)
			getModelImpl();

		List<Intention> is = grlModel.getAllIntentions();
		for (Intention i : is) {

			String name = i.getName();
			EvaluationLabel label = stas.get(name);
			String color = "red";
			if (label != null) {
				SetQualitativeEvaluationLabelCommand setLabel = new SetQualitativeEvaluationLabelCommand(
						i, label);
				cs.execute(setLabel);

				if (label == EvaluationLabel.SATISFIED) {
					color = "green";
				} else if (label == EvaluationLabel.DENIED) {
					color = "red";
				} else if (label == EvaluationLabel.PARTIALLY_SATISFIED) {
					color = "blue";
				} else if (label == EvaluationLabel.UNKNOWN
						|| label == EvaluationLabel.CONFLICT) {
					color = "yellow";
				} else if (label == EvaluationLabel.PARTIALLY_DENIED) {
					color = "orange";
				}
				List<Intention> l = new ArrayList<Intention>();
				l.add(i);
				HighlightIntentionsCommand highlight = new HighlightIntentionsCommand(
						editParts, l, color);
				cs.execute(highlight);
			} 


		}

	}

	private void getEditParts() {
		if (mDE == null) {
			getModelDiagramEditor();
		}

		DiagramEditPart dep = mDE.getDiagramEditPart();
		editParts = dep.getPrimaryEditParts();

	}

	private void getModelImpl() {
		if (mDE == null)
			getModelDiagramEditor();

		EditingDomain editingDomain = mDE.getEditingDomain();

		ResourceSet resourceSet = editingDomain.getResourceSet();

		XMIResourceImpl xmires = null;

		for (Resource tmp : resourceSet.getResources()) {
			if (tmp instanceof XMIResourceImpl) {
				xmires = (XMIResourceImpl) tmp;
			}
		}

		for (EObject tmp2 : xmires.getContents()) {
			if (tmp2 instanceof ModelImpl)
				grlModel = (ModelImpl) tmp2;
		}

	}

	public void getCommandStack() {
		if (mDE == null)
			getModelDiagramEditor();

		EditingDomain editingDomain = mDE.getEditingDomain();

		cs = editingDomain.getCommandStack();

	}

	private void getModelDiagramEditor() {
		try {
			IWorkbenchWindow iww = PlatformUI.getWorkbench()
					.getActiveWorkbenchWindow();
			IWorkbenchPage iwp = iww.getActivePage();
			// org.eclipse.ui.ide.IDE.openEditor(iwp, file);
			IEditorPart[] eps = iwp.getEditors();
			for (IEditorPart p : eps) {
				if (p.getTitle().equals(modelName)) {
					mDE = (Openome_modelDiagramEditor) p;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// private void getDiagramCommandStack() {
	// IWorkbenchWindow iww = PlatformUI.getWorkbench()
	// .getActiveWorkbenchWindow();
	// IWorkbenchPage iwp = iww.getActivePage();
	// IWorkbenchPart part = iwp.getActivePart();
	// IDiagramWorkbenchPart diagramPart = (IDiagramWorkbenchPart) part;
	// dcs = diagramPart.getDiagramEditDomain().getDiagramCommandStack();
	// }

	private void reason() {

		InteractiveQualReasoner iQualReasoner = new InteractiveQualReasoner(
				grlModel, cs, null, editParts, true);
		Reasoning reasoning = new Reasoning(iQualReasoner);

		// for now, remove later
		clearAllJudgments(grlModel, cs);

		reasoning.reason();

	}

	public void clearAllJudgments(ModelImpl m, CommandStack c) {
		Command removeJudgment;
		Command removeReverseJudgment;
		if (c == null)
			System.out.println("command stack is null in clear");
		if (m == null)
			System.out.println("model is null in clear");
		for (Intention i : m.getAllIntentions()) {
			System.out.println("Clearing judgments for: " + i.getName());
			if (i.getHumanJudgments() != null) {
				int size = i.getHumanJudgments().size();
				System.out.println("# human judgments: " + size);
				for (int j = 0; j < size; j++) {
					HumanJudgment togo = i.getHumanJudgments().get(0);
					System.out.println("\tClearing judgment: "
							+ togo.getResultLabel());
					removeJudgment = new RemoveHumanJudgmentCommand(i, togo);
					c.execute(removeJudgment);
				}
			}

			if (i.getReverseLabelBag() != null) {
				removeReverseJudgment = new RemoveReverseJudgmentCommand(i);
				c.execute(removeReverseJudgment);
			}
		}
	}

}
