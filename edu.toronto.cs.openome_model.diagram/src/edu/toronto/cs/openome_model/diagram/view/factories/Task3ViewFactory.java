package edu.toronto.cs.openome_model.diagram.view.factories;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.diagram.ui.view.factories.AbstractShapeViewFactory;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.gmf.runtime.notation.NotationFactory;
import org.eclipse.gmf.runtime.notation.View;

/**
 * @generated
 */
public class Task3ViewFactory extends AbstractShapeViewFactory {

	/**
	 * @generated
	 */
	protected List createStyles(View view) {
		List styles = new ArrayList();
		styles.add(NotationFactory.eINSTANCE.createShapeStyle());
		return styles;
	}

	/**
	 * @generated
	 */
	protected void decorateView(View containerView, View view,
			IAdaptable semanticAdapter, String semanticHint, int index,
			boolean persisted) {
		if (semanticHint == null) {
			semanticHint = edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
					.getType(edu.toronto.cs.openome_model.diagram.edit.parts.Task3EditPart.VISUAL_ID);
			view.setType(semanticHint);
		}
		super.decorateView(containerView, view, semanticAdapter, semanticHint,
				index, persisted);
		IAdaptable eObjectAdapter = null;
		EObject eObject = (EObject) semanticAdapter.getAdapter(EObject.class);
		if (eObject != null) {
			eObjectAdapter = new EObjectAdapter(eObject);
		}
		getViewService()
				.createNode(
						eObjectAdapter,
						view,
						edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
								.getType(edu.toronto.cs.openome_model.diagram.edit.parts.TaskName3EditPart.VISUAL_ID),
						ViewUtil.APPEND, true, getPreferencesHint());
		getViewService()
				.createNode(
						eObjectAdapter,
						view,
						edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
								.getType(edu.toronto.cs.openome_model.diagram.edit.parts.TaskQualitativeReasoningCom3EditPart.VISUAL_ID),
						ViewUtil.APPEND, true, getPreferencesHint());
	}
}
