/**
 * Copyright 2001-2008 University of Toronto
 *
 * $Id$
 */
package edu.toronto.cs.openome_model.provider;


import edu.toronto.cs.openome_model.Task;

import edu.toronto.cs.openome_model.openome_modelPackage;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;

/**
 * This is the item provider adapter for a {@link edu.toronto.cs.openome_model.Task} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class TaskItemProvider
	extends IntentionItemProvider
	implements
		IEditingDomainItemProvider,
		IStructuredItemContentProvider,
		ITreeItemContentProvider,
		IItemLabelProvider,
		IItemPropertySource {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = "Copyright 2001-2008 University of Toronto";

	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TaskItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns the property descriptors for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

			addHasInputPropertyDescriptor(object);
			addHasLocalPropertyDescriptor(object);
			addHasOutputPropertyDescriptor(object);
			addHasClinetPropertyDescriptor(object);
			addHasParameterPropertyDescriptor(object);
			addHasParticipantPropertyDescriptor(object);
			addHasPreconditionPropertyDescriptor(object);
			addHasResultPropertyDescriptor(object);
			addPerformedByPropertyDescriptor(object);
			addRealizesPropertyDescriptor(object);
			addDescribesPropertyDescriptor(object);
			addNextTaskPropertyDescriptor(object);
			addLoopPropertyDescriptor(object);
			addWaitPropertyDescriptor(object);
			addContextRulePropertyDescriptor(object);
			addFlowNamePropertyDescriptor(object);
			addScopeNamePropertyDescriptor(object);
			addOnMessagePropertyDescriptor(object);
			addSwitchToPropertyDescriptor(object);
			addMergeToPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Has Input feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	protected void addHasInputPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Task_hasInput_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Task_hasInput_feature", "_UI_Task_type"),
				 openome_modelPackage.Literals.TASK__HAS_INPUT,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 "IOPE",
				 null));
	}

	/**
	 * This adds a property descriptor for the Has Local feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	protected void addHasLocalPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Task_hasLocal_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Task_hasLocal_feature", "_UI_Task_type"),
				 openome_modelPackage.Literals.TASK__HAS_LOCAL,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 "IOPE",
				 null));
	}

	/**
	 * This adds a property descriptor for the Has Output feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	protected void addHasOutputPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Task_hasOutput_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Task_hasOutput_feature", "_UI_Task_type"),
				 openome_modelPackage.Literals.TASK__HAS_OUTPUT,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 "IOPE",
				 null));
	}

	/**
	 * This adds a property descriptor for the Has Clinet feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	protected void addHasClinetPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Task_hasClinet_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Task_hasClinet_feature", "_UI_Task_type"),
				 openome_modelPackage.Literals.TASK__HAS_CLINET,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 "IOPE",
				 null));
	}

	/**
	 * This adds a property descriptor for the Has Parameter feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	protected void addHasParameterPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Task_hasParameter_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Task_hasParameter_feature", "_UI_Task_type"),
				 openome_modelPackage.Literals.TASK__HAS_PARAMETER,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 "IOPE",
				 null));
	}

	/**
	 * This adds a property descriptor for the Has Participant feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	protected void addHasParticipantPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Task_hasParticipant_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Task_hasParticipant_feature", "_UI_Task_type"),
				 openome_modelPackage.Literals.TASK__HAS_PARTICIPANT,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 "IOPE",
				 null));
	}

	/**
	 * This adds a property descriptor for the Has Precondition feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	protected void addHasPreconditionPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Task_hasPrecondition_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Task_hasPrecondition_feature", "_UI_Task_type"),
				 openome_modelPackage.Literals.TASK__HAS_PRECONDITION,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 "IOPE",
				 null));
	}

	/**
	 * This adds a property descriptor for the Has Result feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	protected void addHasResultPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Task_hasResult_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Task_hasResult_feature", "_UI_Task_type"),
				 openome_modelPackage.Literals.TASK__HAS_RESULT,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 "IOPE",
				 null));
	}

	/**
	 * This adds a property descriptor for the Performed By feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	protected void addPerformedByPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Task_performedBy_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Task_performedBy_feature", "_UI_Task_type"),
				 openome_modelPackage.Literals.TASK__PERFORMED_BY,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 "IOPE",
				 null));
	}

	/**
	 * This adds a property descriptor for the Realizes feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	protected void addRealizesPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Task_realizes_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Task_realizes_feature", "_UI_Task_type"),
				 openome_modelPackage.Literals.TASK__REALIZES,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 "IOPE",
				 null));
	}

	/**
	 * This adds a property descriptor for the Describes feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	protected void addDescribesPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Task_describes_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Task_describes_feature", "_UI_Task_type"),
				 openome_modelPackage.Literals.TASK__DESCRIBES,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 "IOPE",
				 null));
	}

	/**
	 * This adds a property descriptor for the Next Task feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	protected void addNextTaskPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Task_nextTask_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Task_nextTask_feature", "_UI_Task_type"),
				 openome_modelPackage.Literals.TASK__NEXT_TASK,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 "Bussiness Flow",
				 null));
	}

	/**
	 * This adds a property descriptor for the Loop feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	protected void addLoopPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Task_loop_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Task_loop_feature", "_UI_Task_type"),
				 openome_modelPackage.Literals.TASK__LOOP,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 "Bussiness Flow",
				 null));
	}

	/**
	 * This adds a property descriptor for the Wait feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	protected void addWaitPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Task_wait_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Task_wait_feature", "_UI_Task_type"),
				 openome_modelPackage.Literals.TASK__WAIT,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
				 "Bussiness Flow",
				 null));
	}

	/**
	 * This adds a property descriptor for the Context Rule feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	protected void addContextRulePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Task_contextRule_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Task_contextRule_feature", "_UI_Task_type"),
				 openome_modelPackage.Literals.TASK__CONTEXT_RULE,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 "Context",
				 null));
	}

	/**
	 * This adds a property descriptor for the Flow Name feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	protected void addFlowNamePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Task_flowName_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Task_flowName_feature", "_UI_Task_type"),
				 openome_modelPackage.Literals.TASK__FLOW_NAME,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 "Bussiness Flow",
				 null));
	}

	/**
	 * This adds a property descriptor for the Scope Name feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	protected void addScopeNamePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Task_scopeName_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Task_scopeName_feature", "_UI_Task_type"),
				 openome_modelPackage.Literals.TASK__SCOPE_NAME,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 "Bussiness Flow",
				 null));
	}

	/**
	 * This adds a property descriptor for the On Message feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	protected void addOnMessagePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Task_onMessage_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Task_onMessage_feature", "_UI_Task_type"),
				 openome_modelPackage.Literals.TASK__ON_MESSAGE,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 "Bussiness Flow",
				 null));
	}

	/**
	 * This adds a property descriptor for the Switch To feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	protected void addSwitchToPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Task_switchTo_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Task_switchTo_feature", "_UI_Task_type"),
				 openome_modelPackage.Literals.TASK__SWITCH_TO,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 "Bussiness Flow",
				 null));
	}

	/**
	 * This adds a property descriptor for the Merge To feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	protected void addMergeToPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Task_mergeTo_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Task_mergeTo_feature", "_UI_Task_type"),
				 openome_modelPackage.Literals.TASK__MERGE_TO,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 "Bussiness Flow",
				 null));
	}

	/**
	 * This returns Task.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/Task"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		String label = ((Task)object).getName();
		return label == null || label.length() == 0 ?
			getString("_UI_Task_type") :
			getString("_UI_Task_type") + " " + label;
	}

	/**
	 * This handles model notifications by calling {@link #updateChildren} to update any cached
	 * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void notifyChanged(Notification notification) {
		updateChildren(notification);

		switch (notification.getFeatureID(Task.class)) {
			case openome_modelPackage.TASK__HAS_INPUT:
			case openome_modelPackage.TASK__HAS_LOCAL:
			case openome_modelPackage.TASK__HAS_OUTPUT:
			case openome_modelPackage.TASK__HAS_CLINET:
			case openome_modelPackage.TASK__HAS_PARAMETER:
			case openome_modelPackage.TASK__HAS_PARTICIPANT:
			case openome_modelPackage.TASK__HAS_PRECONDITION:
			case openome_modelPackage.TASK__HAS_RESULT:
			case openome_modelPackage.TASK__PERFORMED_BY:
			case openome_modelPackage.TASK__REALIZES:
			case openome_modelPackage.TASK__DESCRIBES:
			case openome_modelPackage.TASK__NEXT_TASK:
			case openome_modelPackage.TASK__LOOP:
			case openome_modelPackage.TASK__WAIT:
			case openome_modelPackage.TASK__CONTEXT_RULE:
			case openome_modelPackage.TASK__FLOW_NAME:
			case openome_modelPackage.TASK__SCOPE_NAME:
			case openome_modelPackage.TASK__ON_MESSAGE:
			case openome_modelPackage.TASK__SWITCH_TO:
			case openome_modelPackage.TASK__MERGE_TO:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
		}
		super.notifyChanged(notification);
	}

	/**
	 * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children
	 * that can be created under this object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);
	}

	/**
	 * This returns the label text for {@link org.eclipse.emf.edit.command.CreateChildCommand}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getCreateChildText(Object owner, Object feature, Object child, Collection<?> selection) {
		Object childFeature = feature;
		Object childObject = child;

		boolean qualify =
			childFeature == openome_modelPackage.Literals.INTENTION__LABEL_BAG ||
			childFeature == openome_modelPackage.Literals.INTENTION__REVERSE_LABEL_BAG;

		if (qualify) {
			return getString
				("_UI_CreateChild_text2",
				 new Object[] { getTypeText(childObject), getFeatureText(childFeature), getTypeText(owner) });
		}
		return super.getCreateChildText(owner, feature, child, selection);
	}

}
