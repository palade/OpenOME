package edu.toronto.cs.openome_model.diagram.edit.policies;

import java.util.Collections;
import java.util.Iterator;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gef.requests.ReconnectRequest;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.common.core.command.ICompositeCommand;
import org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.diagram.ui.commands.CommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.SemanticEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.requests.EditCommandRequestWrapper;
import org.eclipse.gmf.runtime.emf.commands.core.command.CompositeTransactionalCommand;
import org.eclipse.gmf.runtime.emf.type.core.ElementTypeRegistry;
import org.eclipse.gmf.runtime.emf.type.core.IEditHelperContext;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.type.core.requests.ConfigureRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateRelationshipRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyReferenceRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DuplicateElementsRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.GetEditContextRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.IEditCommandRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.MoveRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.ReorientReferenceRelationshipRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.ReorientRelationshipRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.SetRequest;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.gmf.tooling.runtime.edit.helpers.GeneratedEditHelperBase;

/**
 * @generated
 */
public class Openome_modelBaseItemSemanticEditPolicy extends SemanticEditPolicy {

	/**
	 * Extended request data key to hold editpart visual id.
	 * 
	 * @generated
	 */
	public static final String VISUAL_ID_KEY = "visual_id"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	private final IElementType myElementType;

	/**
	 * @generated
	 */
	protected Openome_modelBaseItemSemanticEditPolicy(IElementType elementType) {
		myElementType = elementType;
	}

	/**
	 * Extended request data key to hold editpart visual id.
	 * Add visual id of edited editpart to extended data of the request
	 * so command switch can decide what kind of diagram element is being edited.
	 * It is done in those cases when it's not possible to deduce diagram
	 * element kind from domain element.
	 * 
	 * @generated
	 */
	public Command getCommand(Request request) {
		if (request instanceof ReconnectRequest) {
			Object view = ((ReconnectRequest) request).getConnectionEditPart()
					.getModel();
			if (view instanceof View) {
				Integer id = new Integer(
						edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
								.getVisualID((View) view));
				request.getExtendedData().put(VISUAL_ID_KEY, id);
			}
		}
		return super.getCommand(request);
	}

	/**
	 * Returns visual id from request parameters.
	 * 
	 * @generated
	 */
	protected int getVisualID(IEditCommandRequest request) {
		Object id = request.getParameter(VISUAL_ID_KEY);
		return id instanceof Integer ? ((Integer) id).intValue() : -1;
	}

	/**
	 * @generated
	 */
	protected Command getSemanticCommand(IEditCommandRequest request) {
		IEditCommandRequest completedRequest = completeRequest(request);
		Command semanticCommand = getSemanticCommandSwitch(completedRequest);
		semanticCommand = getEditHelperCommand(completedRequest,
				semanticCommand);
		if (completedRequest instanceof DestroyRequest) {
			DestroyRequest destroyRequest = (DestroyRequest) completedRequest;
			return shouldProceed(destroyRequest) ? addDeleteViewCommand(
					semanticCommand, destroyRequest) : null;
		}
		return semanticCommand;
	}

	/**
	 * @generated
	 */
	protected Command addDeleteViewCommand(Command mainCommand,
			DestroyRequest completedRequest) {
		Command deleteViewCommand = getGEFWrapper(new DeleteCommand(
				getEditingDomain(), (View) getHost().getModel()));
		return mainCommand == null ? deleteViewCommand : mainCommand
				.chain(deleteViewCommand);
	}

	/**
	 * @generated
	 */
	private Command getEditHelperCommand(IEditCommandRequest request,
			Command editPolicyCommand) {
		if (editPolicyCommand != null) {
			ICommand command = editPolicyCommand instanceof ICommandProxy ? ((ICommandProxy) editPolicyCommand)
					.getICommand() : new CommandProxy(editPolicyCommand);
			request.setParameter(GeneratedEditHelperBase.EDIT_POLICY_COMMAND,
					command);
		}
		IElementType requestContextElementType = getContextElementType(request);
		request.setParameter(GeneratedEditHelperBase.CONTEXT_ELEMENT_TYPE,
				requestContextElementType);
		ICommand command = requestContextElementType.getEditCommand(request);
		request.setParameter(GeneratedEditHelperBase.EDIT_POLICY_COMMAND, null);
		request.setParameter(GeneratedEditHelperBase.CONTEXT_ELEMENT_TYPE, null);
		if (command != null) {
			if (!(command instanceof CompositeTransactionalCommand)) {
				command = new CompositeTransactionalCommand(getEditingDomain(),
						command.getLabel()).compose(command);
			}
			return new ICommandProxy(command);
		}
		return editPolicyCommand;
	}

	/**
	 * @generated
	 */
	private IElementType getContextElementType(IEditCommandRequest request) {
		IElementType requestContextElementType = edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes
				.getElementType(getVisualID(request));
		return requestContextElementType != null ? requestContextElementType
				: myElementType;
	}

	/**
	 * @generated
	 */
	protected Command getSemanticCommandSwitch(IEditCommandRequest req) {
		if (req instanceof CreateRelationshipRequest) {
			return getCreateRelationshipCommand((CreateRelationshipRequest) req);
		} else if (req instanceof CreateElementRequest) {
			return getCreateCommand((CreateElementRequest) req);
		} else if (req instanceof ConfigureRequest) {
			return getConfigureCommand((ConfigureRequest) req);
		} else if (req instanceof DestroyElementRequest) {
			return getDestroyElementCommand((DestroyElementRequest) req);
		} else if (req instanceof DestroyReferenceRequest) {
			return getDestroyReferenceCommand((DestroyReferenceRequest) req);
		} else if (req instanceof DuplicateElementsRequest) {
			return getDuplicateCommand((DuplicateElementsRequest) req);
		} else if (req instanceof GetEditContextRequest) {
			return getEditContextCommand((GetEditContextRequest) req);
		} else if (req instanceof MoveRequest) {
			return getMoveCommand((MoveRequest) req);
		} else if (req instanceof ReorientReferenceRelationshipRequest) {
			return getReorientReferenceRelationshipCommand((ReorientReferenceRelationshipRequest) req);
		} else if (req instanceof ReorientRelationshipRequest) {
			return getReorientRelationshipCommand((ReorientRelationshipRequest) req);
		} else if (req instanceof SetRequest) {
			return getSetCommand((SetRequest) req);
		}
		return null;
	}

	/**
	 * @generated
	 */
	protected Command getConfigureCommand(ConfigureRequest req) {
		return null;
	}

	/**
	 * @generated
	 */
	protected Command getCreateRelationshipCommand(CreateRelationshipRequest req) {
		return null;
	}

	/**
	 * @generated
	 */
	protected Command getCreateCommand(CreateElementRequest req) {
		return null;
	}

	/**
	 * @generated
	 */
	protected Command getSetCommand(SetRequest req) {
		return null;
	}

	/**
	 * @generated
	 */
	protected Command getEditContextCommand(GetEditContextRequest req) {
		return null;
	}

	/**
	 * @generated
	 */
	protected Command getDestroyElementCommand(DestroyElementRequest req) {
		return null;
	}

	/**
	 * @generated
	 */
	protected Command getDestroyReferenceCommand(DestroyReferenceRequest req) {
		return null;
	}

	/**
	 * @generated
	 */
	protected Command getDuplicateCommand(DuplicateElementsRequest req) {
		return null;
	}

	/**
	 * @generated
	 */
	protected Command getMoveCommand(MoveRequest req) {
		return null;
	}

	/**
	 * @generated
	 */
	protected Command getReorientReferenceRelationshipCommand(
			ReorientReferenceRelationshipRequest req) {
		return UnexecutableCommand.INSTANCE;
	}

	/**
	 * @generated
	 */
	protected Command getReorientRelationshipCommand(
			ReorientRelationshipRequest req) {
		return UnexecutableCommand.INSTANCE;
	}

	/**
	 * @generated
	 */
	protected final Command getGEFWrapper(ICommand cmd) {
		return new ICommandProxy(cmd);
	}

	/**
	 * Returns editing domain from the host edit part.
	 * 
	 * @generated
	 */
	protected TransactionalEditingDomain getEditingDomain() {
		return ((IGraphicalEditPart) getHost()).getEditingDomain();
	}

	/**
	 * Clean all shortcuts to the host element from the same diagram
	 * @generated
	 */
	protected void addDestroyShortcutsCommand(ICompositeCommand cmd, View view) {
		assert view.getEAnnotation("Shortcut") == null; //$NON-NLS-1$
		for (Iterator it = view.getDiagram().getChildren().iterator(); it
				.hasNext();) {
			View nextView = (View) it.next();
			if (nextView.getEAnnotation("Shortcut") == null || !nextView.isSetElement() || nextView.getElement() != view.getElement()) { //$NON-NLS-1$
				continue;
			}
			cmd.add(new DeleteCommand(getEditingDomain(), nextView));
		}
	}

	/**
	 * @generated
	 */
	public static LinkConstraints getLinkConstraints() {
		LinkConstraints cached = edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramEditorPlugin
				.getInstance().getLinkConstraints();
		if (cached == null) {
			edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramEditorPlugin
					.getInstance().setLinkConstraints(
							cached = new LinkConstraints());
		}
		return cached;
	}

	/**
	 * @generated
	 */
	public static class LinkConstraints {

		/**
		 * @generated
		 */
		LinkConstraints() {
			// use static method #getLinkConstraints() to access instance
		}

		/**
		 * @generated
		 */
		public boolean canCreateDependency_4001(
				edu.toronto.cs.openome_model.Model container,
				edu.toronto.cs.openome_model.Dependable source,
				edu.toronto.cs.openome_model.Dependable target) {
			return canExistDependency_4001(container, null, source, target);
		}

		/**
		 * @generated
		 */
		public boolean canCreateAndDecomposition_4002(
				edu.toronto.cs.openome_model.Model container,
				edu.toronto.cs.openome_model.Intention source,
				edu.toronto.cs.openome_model.Intention target) {
			return canExistAndDecomposition_4002(container, null, source,
					target);
		}

		/**
		 * @generated
		 */
		public boolean canCreateOrDecomposition_4003(
				edu.toronto.cs.openome_model.Model container,
				edu.toronto.cs.openome_model.Intention source,
				edu.toronto.cs.openome_model.Intention target) {
			return canExistOrDecomposition_4003(container, null, source, target);
		}

		/**
		 * @generated
		 */
		public boolean canCreateHelpContribution_4005(
				edu.toronto.cs.openome_model.Model container,
				edu.toronto.cs.openome_model.Intention source,
				edu.toronto.cs.openome_model.Intention target) {
			return canExistHelpContribution_4005(container, null, source,
					target);
		}

		/**
		 * @generated
		 */
		public boolean canCreateHurtContribution_4006(
				edu.toronto.cs.openome_model.Model container,
				edu.toronto.cs.openome_model.Intention source,
				edu.toronto.cs.openome_model.Intention target) {
			return canExistHurtContribution_4006(container, null, source,
					target);
		}

		/**
		 * @generated
		 */
		public boolean canCreateMakeContribution_4007(
				edu.toronto.cs.openome_model.Model container,
				edu.toronto.cs.openome_model.Intention source,
				edu.toronto.cs.openome_model.Intention target) {
			return canExistMakeContribution_4007(container, null, source,
					target);
		}

		/**
		 * @generated
		 */
		public boolean canCreateBreakContribution_4008(
				edu.toronto.cs.openome_model.Model container,
				edu.toronto.cs.openome_model.Intention source,
				edu.toronto.cs.openome_model.Intention target) {
			return canExistBreakContribution_4008(container, null, source,
					target);
		}

		/**
		 * @generated
		 */
		public boolean canCreateSomePlusContribution_4009(
				edu.toronto.cs.openome_model.Model container,
				edu.toronto.cs.openome_model.Intention source,
				edu.toronto.cs.openome_model.Intention target) {
			return canExistSomePlusContribution_4009(container, null, source,
					target);
		}

		/**
		 * @generated
		 */
		public boolean canCreateSomeMinusContribution_4010(
				edu.toronto.cs.openome_model.Model container,
				edu.toronto.cs.openome_model.Intention source,
				edu.toronto.cs.openome_model.Intention target) {
			return canExistSomeMinusContribution_4010(container, null, source,
					target);
		}

		/**
		 * @generated
		 */
		public boolean canCreateUnknownContribution_4011(
				edu.toronto.cs.openome_model.Model container,
				edu.toronto.cs.openome_model.Intention source,
				edu.toronto.cs.openome_model.Intention target) {
			return canExistUnknownContribution_4011(container, null, source,
					target);
		}

		/**
		 * @generated
		 */
		public boolean canCreateAndContribution_4012(
				edu.toronto.cs.openome_model.Model container,
				edu.toronto.cs.openome_model.Intention source,
				edu.toronto.cs.openome_model.Intention target) {
			return canExistAndContribution_4012(container, null, source, target);
		}

		/**
		 * @generated
		 */
		public boolean canCreateOrContribution_4013(
				edu.toronto.cs.openome_model.Model container,
				edu.toronto.cs.openome_model.Intention source,
				edu.toronto.cs.openome_model.Intention target) {
			return canExistOrContribution_4013(container, null, source, target);
		}

		/**
		 * @generated
		 */
		public boolean canCreateIsAAssociation_4014(
				edu.toronto.cs.openome_model.Model container,
				edu.toronto.cs.openome_model.Container source,
				edu.toronto.cs.openome_model.Container target) {
			return canExistIsAAssociation_4014(container, null, source, target);
		}

		/**
		 * @generated
		 */
		public boolean canCreateCoversAssociation_4015(
				edu.toronto.cs.openome_model.Model container,
				edu.toronto.cs.openome_model.Container source,
				edu.toronto.cs.openome_model.Container target) {
			return canExistCoversAssociation_4015(container, null, source,
					target);
		}

		/**
		 * @generated
		 */
		public boolean canCreateOccupiesAssociation_4016(
				edu.toronto.cs.openome_model.Model container,
				edu.toronto.cs.openome_model.Container source,
				edu.toronto.cs.openome_model.Container target) {
			return canExistOccupiesAssociation_4016(container, null, source,
					target);
		}

		/**
		 * @generated
		 */
		public boolean canCreateIsPartOfAssociation_4017(
				edu.toronto.cs.openome_model.Model container,
				edu.toronto.cs.openome_model.Container source,
				edu.toronto.cs.openome_model.Container target) {
			return canExistIsPartOfAssociation_4017(container, null, source,
					target);
		}

		/**
		 * @generated
		 */
		public boolean canCreatePlaysAssociation_4018(
				edu.toronto.cs.openome_model.Model container,
				edu.toronto.cs.openome_model.Container source,
				edu.toronto.cs.openome_model.Container target) {
			return canExistPlaysAssociation_4018(container, null, source,
					target);
		}

		/**
		 * @generated
		 */
		public boolean canCreateINSAssociation_4019(
				edu.toronto.cs.openome_model.Model container,
				edu.toronto.cs.openome_model.Container source,
				edu.toronto.cs.openome_model.Container target) {
			return canExistINSAssociation_4019(container, null, source, target);
		}

		/**
		 * @generated
		 */
		public boolean canExistDependency_4001(
				edu.toronto.cs.openome_model.Model container,
				edu.toronto.cs.openome_model.Dependency linkInstance,
				edu.toronto.cs.openome_model.Dependable source,
				edu.toronto.cs.openome_model.Dependable target) {
			return true;
		}

		/**
		 * @generated
		 */
		public boolean canExistAndDecomposition_4002(
				edu.toronto.cs.openome_model.Model container,
				edu.toronto.cs.openome_model.AndDecomposition linkInstance,
				edu.toronto.cs.openome_model.Intention source,
				edu.toronto.cs.openome_model.Intention target) {
			return true;
		}

		/**
		 * @generated
		 */
		public boolean canExistOrDecomposition_4003(
				edu.toronto.cs.openome_model.Model container,
				edu.toronto.cs.openome_model.OrDecomposition linkInstance,
				edu.toronto.cs.openome_model.Intention source,
				edu.toronto.cs.openome_model.Intention target) {
			return true;
		}

		/**
		 * @generated
		 */
		public boolean canExistHelpContribution_4005(
				edu.toronto.cs.openome_model.Model container,
				edu.toronto.cs.openome_model.HelpContribution linkInstance,
				edu.toronto.cs.openome_model.Intention source,
				edu.toronto.cs.openome_model.Intention target) {
			return true;
		}

		/**
		 * @generated
		 */
		public boolean canExistHurtContribution_4006(
				edu.toronto.cs.openome_model.Model container,
				edu.toronto.cs.openome_model.HurtContribution linkInstance,
				edu.toronto.cs.openome_model.Intention source,
				edu.toronto.cs.openome_model.Intention target) {
			return true;
		}

		/**
		 * @generated
		 */
		public boolean canExistMakeContribution_4007(
				edu.toronto.cs.openome_model.Model container,
				edu.toronto.cs.openome_model.MakeContribution linkInstance,
				edu.toronto.cs.openome_model.Intention source,
				edu.toronto.cs.openome_model.Intention target) {
			return true;
		}

		/**
		 * @generated
		 */
		public boolean canExistBreakContribution_4008(
				edu.toronto.cs.openome_model.Model container,
				edu.toronto.cs.openome_model.BreakContribution linkInstance,
				edu.toronto.cs.openome_model.Intention source,
				edu.toronto.cs.openome_model.Intention target) {
			return true;
		}

		/**
		 * @generated
		 */
		public boolean canExistSomePlusContribution_4009(
				edu.toronto.cs.openome_model.Model container,
				edu.toronto.cs.openome_model.SomePlusContribution linkInstance,
				edu.toronto.cs.openome_model.Intention source,
				edu.toronto.cs.openome_model.Intention target) {
			return true;
		}

		/**
		 * @generated
		 */
		public boolean canExistSomeMinusContribution_4010(
				edu.toronto.cs.openome_model.Model container,
				edu.toronto.cs.openome_model.SomeMinusContribution linkInstance,
				edu.toronto.cs.openome_model.Intention source,
				edu.toronto.cs.openome_model.Intention target) {
			return true;
		}

		/**
		 * @generated
		 */
		public boolean canExistUnknownContribution_4011(
				edu.toronto.cs.openome_model.Model container,
				edu.toronto.cs.openome_model.UnknownContribution linkInstance,
				edu.toronto.cs.openome_model.Intention source,
				edu.toronto.cs.openome_model.Intention target) {
			return true;
		}

		/**
		 * @generated
		 */
		public boolean canExistAndContribution_4012(
				edu.toronto.cs.openome_model.Model container,
				edu.toronto.cs.openome_model.AndContribution linkInstance,
				edu.toronto.cs.openome_model.Intention source,
				edu.toronto.cs.openome_model.Intention target) {
			return true;
		}

		/**
		 * @generated
		 */
		public boolean canExistOrContribution_4013(
				edu.toronto.cs.openome_model.Model container,
				edu.toronto.cs.openome_model.OrContribution linkInstance,
				edu.toronto.cs.openome_model.Intention source,
				edu.toronto.cs.openome_model.Intention target) {
			return true;
		}

		/**
		 * @generated
		 */
		public boolean canExistIsAAssociation_4014(
				edu.toronto.cs.openome_model.Model container,
				edu.toronto.cs.openome_model.IsAAssociation linkInstance,
				edu.toronto.cs.openome_model.Container source,
				edu.toronto.cs.openome_model.Container target) {
			return true;
		}

		/**
		 * @generated
		 */
		public boolean canExistCoversAssociation_4015(
				edu.toronto.cs.openome_model.Model container,
				edu.toronto.cs.openome_model.CoversAssociation linkInstance,
				edu.toronto.cs.openome_model.Container source,
				edu.toronto.cs.openome_model.Container target) {
			return true;
		}

		/**
		 * @generated
		 */
		public boolean canExistOccupiesAssociation_4016(
				edu.toronto.cs.openome_model.Model container,
				edu.toronto.cs.openome_model.OccupiesAssociation linkInstance,
				edu.toronto.cs.openome_model.Container source,
				edu.toronto.cs.openome_model.Container target) {
			return true;
		}

		/**
		 * @generated
		 */
		public boolean canExistIsPartOfAssociation_4017(
				edu.toronto.cs.openome_model.Model container,
				edu.toronto.cs.openome_model.IsPartOfAssociation linkInstance,
				edu.toronto.cs.openome_model.Container source,
				edu.toronto.cs.openome_model.Container target) {
			return true;
		}

		/**
		 * @generated
		 */
		public boolean canExistPlaysAssociation_4018(
				edu.toronto.cs.openome_model.Model container,
				edu.toronto.cs.openome_model.PlaysAssociation linkInstance,
				edu.toronto.cs.openome_model.Container source,
				edu.toronto.cs.openome_model.Container target) {
			return true;
		}

		/**
		 * @generated
		 */
		public boolean canExistINSAssociation_4019(
				edu.toronto.cs.openome_model.Model container,
				edu.toronto.cs.openome_model.INSAssociation linkInstance,
				edu.toronto.cs.openome_model.Container source,
				edu.toronto.cs.openome_model.Container target) {
			return true;
		}
	}

}
