/**
 * Copyright 2001-2008 University of Toronto
 *
 * $Id$
 */
package edu.toronto.cs.openome_model.impl;

import edu.toronto.cs.openome_model.Task;
import edu.toronto.cs.openome_model.openome_modelPackage;

import java.util.Collection;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Task</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link edu.toronto.cs.openome_model.impl.TaskImpl#getHasInput <em>Has Input</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.impl.TaskImpl#getHasLocal <em>Has Local</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.impl.TaskImpl#getHasOutput <em>Has Output</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.impl.TaskImpl#getHasClinet <em>Has Clinet</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.impl.TaskImpl#getHasParameter <em>Has Parameter</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.impl.TaskImpl#getHasParticipant <em>Has Participant</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.impl.TaskImpl#getHasPrecondition <em>Has Precondition</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.impl.TaskImpl#getHasResult <em>Has Result</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.impl.TaskImpl#getPerformedBy <em>Performed By</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.impl.TaskImpl#getRealizes <em>Realizes</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.impl.TaskImpl#getDescribes <em>Describes</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.impl.TaskImpl#getNextTask <em>Next Task</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.impl.TaskImpl#getLoop <em>Loop</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.impl.TaskImpl#isWait <em>Wait</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.impl.TaskImpl#getContextRule <em>Context Rule</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.impl.TaskImpl#getFlowName <em>Flow Name</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.impl.TaskImpl#getScopeName <em>Scope Name</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.impl.TaskImpl#getOnMessage <em>On Message</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.impl.TaskImpl#getSwitchTo <em>Switch To</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.impl.TaskImpl#getMergeTo <em>Merge To</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TaskImpl extends IntentionImpl implements Task {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = "Copyright 2001-2008 University of Toronto";

	/**
	 * The cached value of the '{@link #getHasInput() <em>Has Input</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHasInput()
	 * @generated
	 * @ordered
	 */
	protected EList<String> hasInput;
	/**
	 * The default value of the '{@link #getHasLocal() <em>Has Local</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHasLocal()
	 * @generated
	 * @ordered
	 */
	protected static final String HAS_LOCAL_EDEFAULT = null;
	/**
	 * The cached value of the '{@link #getHasLocal() <em>Has Local</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHasLocal()
	 * @generated
	 * @ordered
	 */
	protected String hasLocal = HAS_LOCAL_EDEFAULT;
	/**
	 * The default value of the '{@link #getHasOutput() <em>Has Output</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHasOutput()
	 * @generated
	 * @ordered
	 */
	protected static final String HAS_OUTPUT_EDEFAULT = null;
	/**
	 * The cached value of the '{@link #getHasOutput() <em>Has Output</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHasOutput()
	 * @generated
	 * @ordered
	 */
	protected String hasOutput = HAS_OUTPUT_EDEFAULT;
	/**
	 * The default value of the '{@link #getHasClinet() <em>Has Clinet</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHasClinet()
	 * @generated
	 * @ordered
	 */
	protected static final String HAS_CLINET_EDEFAULT = null;
	/**
	 * The cached value of the '{@link #getHasClinet() <em>Has Clinet</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHasClinet()
	 * @generated
	 * @ordered
	 */
	protected String hasClinet = HAS_CLINET_EDEFAULT;
	/**
	 * The cached value of the '{@link #getHasParameter() <em>Has Parameter</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHasParameter()
	 * @generated
	 * @ordered
	 */
	protected EList<String> hasParameter;
	/**
	 * The default value of the '{@link #getHasParticipant() <em>Has Participant</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHasParticipant()
	 * @generated
	 * @ordered
	 */
	protected static final String HAS_PARTICIPANT_EDEFAULT = null;
	/**
	 * The cached value of the '{@link #getHasParticipant() <em>Has Participant</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHasParticipant()
	 * @generated
	 * @ordered
	 */
	protected String hasParticipant = HAS_PARTICIPANT_EDEFAULT;
	/**
	 * The default value of the '{@link #getHasPrecondition() <em>Has Precondition</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHasPrecondition()
	 * @generated
	 * @ordered
	 */
	protected static final String HAS_PRECONDITION_EDEFAULT = null;
	/**
	 * The cached value of the '{@link #getHasPrecondition() <em>Has Precondition</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHasPrecondition()
	 * @generated
	 * @ordered
	 */
	protected String hasPrecondition = HAS_PRECONDITION_EDEFAULT;
	/**
	 * The default value of the '{@link #getHasResult() <em>Has Result</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHasResult()
	 * @generated
	 * @ordered
	 */
	protected static final String HAS_RESULT_EDEFAULT = null;
	/**
	 * The cached value of the '{@link #getHasResult() <em>Has Result</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHasResult()
	 * @generated
	 * @ordered
	 */
	protected String hasResult = HAS_RESULT_EDEFAULT;
	/**
	 * The default value of the '{@link #getPerformedBy() <em>Performed By</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPerformedBy()
	 * @generated
	 * @ordered
	 */
	protected static final String PERFORMED_BY_EDEFAULT = null;
	/**
	 * The cached value of the '{@link #getPerformedBy() <em>Performed By</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPerformedBy()
	 * @generated
	 * @ordered
	 */
	protected String performedBy = PERFORMED_BY_EDEFAULT;
	/**
	 * The default value of the '{@link #getRealizes() <em>Realizes</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRealizes()
	 * @generated
	 * @ordered
	 */
	protected static final String REALIZES_EDEFAULT = null;
	/**
	 * The cached value of the '{@link #getRealizes() <em>Realizes</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRealizes()
	 * @generated
	 * @ordered
	 */
	protected String realizes = REALIZES_EDEFAULT;
	/**
	 * The default value of the '{@link #getDescribes() <em>Describes</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescribes()
	 * @generated
	 * @ordered
	 */
	protected static final String DESCRIBES_EDEFAULT = null;
	/**
	 * The cached value of the '{@link #getDescribes() <em>Describes</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescribes()
	 * @generated
	 * @ordered
	 */
	protected String describes = DESCRIBES_EDEFAULT;

	/**
	 * The default value of the '{@link #getNextTask() <em>Next Task</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNextTask()
	 * @generated
	 * @ordered
	 */
	protected static final String NEXT_TASK_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getNextTask() <em>Next Task</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNextTask()
	 * @generated
	 * @ordered
	 */
	protected String nextTask = NEXT_TASK_EDEFAULT;

	/**
	 * The default value of the '{@link #getLoop() <em>Loop</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLoop()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean LOOP_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLoop() <em>Loop</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLoop()
	 * @generated
	 * @ordered
	 */
	protected Boolean loop = LOOP_EDEFAULT;

	/**
	 * The default value of the '{@link #isWait() <em>Wait</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isWait()
	 * @generated
	 * @ordered
	 */
	protected static final boolean WAIT_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isWait() <em>Wait</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isWait()
	 * @generated
	 * @ordered
	 */
	protected boolean wait = WAIT_EDEFAULT;

	/**
	 * The default value of the '{@link #getContextRule() <em>Context Rule</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getContextRule()
	 * @generated
	 * @ordered
	 */
	protected static final String CONTEXT_RULE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getContextRule() <em>Context Rule</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getContextRule()
	 * @generated
	 * @ordered
	 */
	protected String contextRule = CONTEXT_RULE_EDEFAULT;

	/**
	 * The default value of the '{@link #getFlowName() <em>Flow Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFlowName()
	 * @generated
	 * @ordered
	 */
	protected static final String FLOW_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFlowName() <em>Flow Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFlowName()
	 * @generated
	 * @ordered
	 */
	protected String flowName = FLOW_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getScopeName() <em>Scope Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getScopeName()
	 * @generated
	 * @ordered
	 */
	protected static final String SCOPE_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getScopeName() <em>Scope Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getScopeName()
	 * @generated
	 * @ordered
	 */
	protected String scopeName = SCOPE_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getOnMessage() <em>On Message</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOnMessage()
	 * @generated
	 * @ordered
	 */
	protected static final String ON_MESSAGE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getOnMessage() <em>On Message</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOnMessage()
	 * @generated
	 * @ordered
	 */
	protected String onMessage = ON_MESSAGE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getSwitchTo() <em>Switch To</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSwitchTo()
	 * @generated
	 * @ordered
	 */
	protected EList<String> switchTo;

	/**
	 * The default value of the '{@link #getMergeTo() <em>Merge To</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMergeTo()
	 * @generated
	 * @ordered
	 */
	protected static final String MERGE_TO_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getMergeTo() <em>Merge To</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMergeTo()
	 * @generated
	 * @ordered
	 */
	protected String mergeTo = MERGE_TO_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TaskImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return openome_modelPackage.Literals.TASK;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getHasInput() {
		if (hasInput == null) {
			hasInput = new EDataTypeUniqueEList<String>(String.class, this, openome_modelPackage.TASK__HAS_INPUT);
		}
		return hasInput;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getHasLocal() {
		return hasLocal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHasLocal(String newHasLocal) {
		String oldHasLocal = hasLocal;
		hasLocal = newHasLocal;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, openome_modelPackage.TASK__HAS_LOCAL, oldHasLocal, hasLocal));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getHasOutput() {
		return hasOutput;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHasOutput(String newHasOutput) {
		String oldHasOutput = hasOutput;
		hasOutput = newHasOutput;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, openome_modelPackage.TASK__HAS_OUTPUT, oldHasOutput, hasOutput));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getHasClinet() {
		return hasClinet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHasClinet(String newHasClinet) {
		String oldHasClinet = hasClinet;
		hasClinet = newHasClinet;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, openome_modelPackage.TASK__HAS_CLINET, oldHasClinet, hasClinet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getHasParameter() {
		if (hasParameter == null) {
			hasParameter = new EDataTypeUniqueEList<String>(String.class, this, openome_modelPackage.TASK__HAS_PARAMETER);
		}
		return hasParameter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getHasParticipant() {
		return hasParticipant;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHasParticipant(String newHasParticipant) {
		String oldHasParticipant = hasParticipant;
		hasParticipant = newHasParticipant;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, openome_modelPackage.TASK__HAS_PARTICIPANT, oldHasParticipant, hasParticipant));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getHasPrecondition() {
		return hasPrecondition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHasPrecondition(String newHasPrecondition) {
		String oldHasPrecondition = hasPrecondition;
		hasPrecondition = newHasPrecondition;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, openome_modelPackage.TASK__HAS_PRECONDITION, oldHasPrecondition, hasPrecondition));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getHasResult() {
		return hasResult;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHasResult(String newHasResult) {
		String oldHasResult = hasResult;
		hasResult = newHasResult;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, openome_modelPackage.TASK__HAS_RESULT, oldHasResult, hasResult));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPerformedBy() {
		return performedBy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPerformedBy(String newPerformedBy) {
		String oldPerformedBy = performedBy;
		performedBy = newPerformedBy;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, openome_modelPackage.TASK__PERFORMED_BY, oldPerformedBy, performedBy));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getRealizes() {
		return realizes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRealizes(String newRealizes) {
		String oldRealizes = realizes;
		realizes = newRealizes;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, openome_modelPackage.TASK__REALIZES, oldRealizes, realizes));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDescribes() {
		return describes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDescribes(String newDescribes) {
		String oldDescribes = describes;
		describes = newDescribes;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, openome_modelPackage.TASK__DESCRIBES, oldDescribes, describes));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getNextTask() {
		return nextTask;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNextTask(String newNextTask) {
		String oldNextTask = nextTask;
		nextTask = newNextTask;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, openome_modelPackage.TASK__NEXT_TASK, oldNextTask, nextTask));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getLoop() {
		return loop;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLoop(Boolean newLoop) {
		Boolean oldLoop = loop;
		loop = newLoop;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, openome_modelPackage.TASK__LOOP, oldLoop, loop));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isWait() {
		return wait;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWait(boolean newWait) {
		boolean oldWait = wait;
		wait = newWait;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, openome_modelPackage.TASK__WAIT, oldWait, wait));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getContextRule() {
		return contextRule;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setContextRule(String newContextRule) {
		String oldContextRule = contextRule;
		contextRule = newContextRule;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, openome_modelPackage.TASK__CONTEXT_RULE, oldContextRule, contextRule));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getFlowName() {
		return flowName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFlowName(String newFlowName) {
		String oldFlowName = flowName;
		flowName = newFlowName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, openome_modelPackage.TASK__FLOW_NAME, oldFlowName, flowName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getScopeName() {
		return scopeName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setScopeName(String newScopeName) {
		String oldScopeName = scopeName;
		scopeName = newScopeName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, openome_modelPackage.TASK__SCOPE_NAME, oldScopeName, scopeName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getOnMessage() {
		return onMessage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOnMessage(String newOnMessage) {
		String oldOnMessage = onMessage;
		onMessage = newOnMessage;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, openome_modelPackage.TASK__ON_MESSAGE, oldOnMessage, onMessage));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getSwitchTo() {
		if (switchTo == null) {
			switchTo = new EDataTypeUniqueEList<String>(String.class, this, openome_modelPackage.TASK__SWITCH_TO);
		}
		return switchTo;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getMergeTo() {
		return mergeTo;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMergeTo(String newMergeTo) {
		String oldMergeTo = mergeTo;
		mergeTo = newMergeTo;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, openome_modelPackage.TASK__MERGE_TO, oldMergeTo, mergeTo));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case openome_modelPackage.TASK__HAS_INPUT:
				return getHasInput();
			case openome_modelPackage.TASK__HAS_LOCAL:
				return getHasLocal();
			case openome_modelPackage.TASK__HAS_OUTPUT:
				return getHasOutput();
			case openome_modelPackage.TASK__HAS_CLINET:
				return getHasClinet();
			case openome_modelPackage.TASK__HAS_PARAMETER:
				return getHasParameter();
			case openome_modelPackage.TASK__HAS_PARTICIPANT:
				return getHasParticipant();
			case openome_modelPackage.TASK__HAS_PRECONDITION:
				return getHasPrecondition();
			case openome_modelPackage.TASK__HAS_RESULT:
				return getHasResult();
			case openome_modelPackage.TASK__PERFORMED_BY:
				return getPerformedBy();
			case openome_modelPackage.TASK__REALIZES:
				return getRealizes();
			case openome_modelPackage.TASK__DESCRIBES:
				return getDescribes();
			case openome_modelPackage.TASK__NEXT_TASK:
				return getNextTask();
			case openome_modelPackage.TASK__LOOP:
				return getLoop();
			case openome_modelPackage.TASK__WAIT:
				return isWait();
			case openome_modelPackage.TASK__CONTEXT_RULE:
				return getContextRule();
			case openome_modelPackage.TASK__FLOW_NAME:
				return getFlowName();
			case openome_modelPackage.TASK__SCOPE_NAME:
				return getScopeName();
			case openome_modelPackage.TASK__ON_MESSAGE:
				return getOnMessage();
			case openome_modelPackage.TASK__SWITCH_TO:
				return getSwitchTo();
			case openome_modelPackage.TASK__MERGE_TO:
				return getMergeTo();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case openome_modelPackage.TASK__HAS_INPUT:
				getHasInput().clear();
				getHasInput().addAll((Collection<? extends String>)newValue);
				return;
			case openome_modelPackage.TASK__HAS_LOCAL:
				setHasLocal((String)newValue);
				return;
			case openome_modelPackage.TASK__HAS_OUTPUT:
				setHasOutput((String)newValue);
				return;
			case openome_modelPackage.TASK__HAS_CLINET:
				setHasClinet((String)newValue);
				return;
			case openome_modelPackage.TASK__HAS_PARAMETER:
				getHasParameter().clear();
				getHasParameter().addAll((Collection<? extends String>)newValue);
				return;
			case openome_modelPackage.TASK__HAS_PARTICIPANT:
				setHasParticipant((String)newValue);
				return;
			case openome_modelPackage.TASK__HAS_PRECONDITION:
				setHasPrecondition((String)newValue);
				return;
			case openome_modelPackage.TASK__HAS_RESULT:
				setHasResult((String)newValue);
				return;
			case openome_modelPackage.TASK__PERFORMED_BY:
				setPerformedBy((String)newValue);
				return;
			case openome_modelPackage.TASK__REALIZES:
				setRealizes((String)newValue);
				return;
			case openome_modelPackage.TASK__DESCRIBES:
				setDescribes((String)newValue);
				return;
			case openome_modelPackage.TASK__NEXT_TASK:
				setNextTask((String)newValue);
				return;
			case openome_modelPackage.TASK__LOOP:
				setLoop((Boolean)newValue);
				return;
			case openome_modelPackage.TASK__WAIT:
				setWait((Boolean)newValue);
				return;
			case openome_modelPackage.TASK__CONTEXT_RULE:
				setContextRule((String)newValue);
				return;
			case openome_modelPackage.TASK__FLOW_NAME:
				setFlowName((String)newValue);
				return;
			case openome_modelPackage.TASK__SCOPE_NAME:
				setScopeName((String)newValue);
				return;
			case openome_modelPackage.TASK__ON_MESSAGE:
				setOnMessage((String)newValue);
				return;
			case openome_modelPackage.TASK__SWITCH_TO:
				getSwitchTo().clear();
				getSwitchTo().addAll((Collection<? extends String>)newValue);
				return;
			case openome_modelPackage.TASK__MERGE_TO:
				setMergeTo((String)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case openome_modelPackage.TASK__HAS_INPUT:
				getHasInput().clear();
				return;
			case openome_modelPackage.TASK__HAS_LOCAL:
				setHasLocal(HAS_LOCAL_EDEFAULT);
				return;
			case openome_modelPackage.TASK__HAS_OUTPUT:
				setHasOutput(HAS_OUTPUT_EDEFAULT);
				return;
			case openome_modelPackage.TASK__HAS_CLINET:
				setHasClinet(HAS_CLINET_EDEFAULT);
				return;
			case openome_modelPackage.TASK__HAS_PARAMETER:
				getHasParameter().clear();
				return;
			case openome_modelPackage.TASK__HAS_PARTICIPANT:
				setHasParticipant(HAS_PARTICIPANT_EDEFAULT);
				return;
			case openome_modelPackage.TASK__HAS_PRECONDITION:
				setHasPrecondition(HAS_PRECONDITION_EDEFAULT);
				return;
			case openome_modelPackage.TASK__HAS_RESULT:
				setHasResult(HAS_RESULT_EDEFAULT);
				return;
			case openome_modelPackage.TASK__PERFORMED_BY:
				setPerformedBy(PERFORMED_BY_EDEFAULT);
				return;
			case openome_modelPackage.TASK__REALIZES:
				setRealizes(REALIZES_EDEFAULT);
				return;
			case openome_modelPackage.TASK__DESCRIBES:
				setDescribes(DESCRIBES_EDEFAULT);
				return;
			case openome_modelPackage.TASK__NEXT_TASK:
				setNextTask(NEXT_TASK_EDEFAULT);
				return;
			case openome_modelPackage.TASK__LOOP:
				setLoop(LOOP_EDEFAULT);
				return;
			case openome_modelPackage.TASK__WAIT:
				setWait(WAIT_EDEFAULT);
				return;
			case openome_modelPackage.TASK__CONTEXT_RULE:
				setContextRule(CONTEXT_RULE_EDEFAULT);
				return;
			case openome_modelPackage.TASK__FLOW_NAME:
				setFlowName(FLOW_NAME_EDEFAULT);
				return;
			case openome_modelPackage.TASK__SCOPE_NAME:
				setScopeName(SCOPE_NAME_EDEFAULT);
				return;
			case openome_modelPackage.TASK__ON_MESSAGE:
				setOnMessage(ON_MESSAGE_EDEFAULT);
				return;
			case openome_modelPackage.TASK__SWITCH_TO:
				getSwitchTo().clear();
				return;
			case openome_modelPackage.TASK__MERGE_TO:
				setMergeTo(MERGE_TO_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case openome_modelPackage.TASK__HAS_INPUT:
				return hasInput != null && !hasInput.isEmpty();
			case openome_modelPackage.TASK__HAS_LOCAL:
				return HAS_LOCAL_EDEFAULT == null ? hasLocal != null : !HAS_LOCAL_EDEFAULT.equals(hasLocal);
			case openome_modelPackage.TASK__HAS_OUTPUT:
				return HAS_OUTPUT_EDEFAULT == null ? hasOutput != null : !HAS_OUTPUT_EDEFAULT.equals(hasOutput);
			case openome_modelPackage.TASK__HAS_CLINET:
				return HAS_CLINET_EDEFAULT == null ? hasClinet != null : !HAS_CLINET_EDEFAULT.equals(hasClinet);
			case openome_modelPackage.TASK__HAS_PARAMETER:
				return hasParameter != null && !hasParameter.isEmpty();
			case openome_modelPackage.TASK__HAS_PARTICIPANT:
				return HAS_PARTICIPANT_EDEFAULT == null ? hasParticipant != null : !HAS_PARTICIPANT_EDEFAULT.equals(hasParticipant);
			case openome_modelPackage.TASK__HAS_PRECONDITION:
				return HAS_PRECONDITION_EDEFAULT == null ? hasPrecondition != null : !HAS_PRECONDITION_EDEFAULT.equals(hasPrecondition);
			case openome_modelPackage.TASK__HAS_RESULT:
				return HAS_RESULT_EDEFAULT == null ? hasResult != null : !HAS_RESULT_EDEFAULT.equals(hasResult);
			case openome_modelPackage.TASK__PERFORMED_BY:
				return PERFORMED_BY_EDEFAULT == null ? performedBy != null : !PERFORMED_BY_EDEFAULT.equals(performedBy);
			case openome_modelPackage.TASK__REALIZES:
				return REALIZES_EDEFAULT == null ? realizes != null : !REALIZES_EDEFAULT.equals(realizes);
			case openome_modelPackage.TASK__DESCRIBES:
				return DESCRIBES_EDEFAULT == null ? describes != null : !DESCRIBES_EDEFAULT.equals(describes);
			case openome_modelPackage.TASK__NEXT_TASK:
				return NEXT_TASK_EDEFAULT == null ? nextTask != null : !NEXT_TASK_EDEFAULT.equals(nextTask);
			case openome_modelPackage.TASK__LOOP:
				return LOOP_EDEFAULT == null ? loop != null : !LOOP_EDEFAULT.equals(loop);
			case openome_modelPackage.TASK__WAIT:
				return wait != WAIT_EDEFAULT;
			case openome_modelPackage.TASK__CONTEXT_RULE:
				return CONTEXT_RULE_EDEFAULT == null ? contextRule != null : !CONTEXT_RULE_EDEFAULT.equals(contextRule);
			case openome_modelPackage.TASK__FLOW_NAME:
				return FLOW_NAME_EDEFAULT == null ? flowName != null : !FLOW_NAME_EDEFAULT.equals(flowName);
			case openome_modelPackage.TASK__SCOPE_NAME:
				return SCOPE_NAME_EDEFAULT == null ? scopeName != null : !SCOPE_NAME_EDEFAULT.equals(scopeName);
			case openome_modelPackage.TASK__ON_MESSAGE:
				return ON_MESSAGE_EDEFAULT == null ? onMessage != null : !ON_MESSAGE_EDEFAULT.equals(onMessage);
			case openome_modelPackage.TASK__SWITCH_TO:
				return switchTo != null && !switchTo.isEmpty();
			case openome_modelPackage.TASK__MERGE_TO:
				return MERGE_TO_EDEFAULT == null ? mergeTo != null : !MERGE_TO_EDEFAULT.equals(mergeTo);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (hasInput: ");
		result.append(hasInput);
		result.append(", hasLocal: ");
		result.append(hasLocal);
		result.append(", hasOutput: ");
		result.append(hasOutput);
		result.append(", hasClinet: ");
		result.append(hasClinet);
		result.append(", hasParameter: ");
		result.append(hasParameter);
		result.append(", hasParticipant: ");
		result.append(hasParticipant);
		result.append(", hasPrecondition: ");
		result.append(hasPrecondition);
		result.append(", hasResult: ");
		result.append(hasResult);
		result.append(", performedBy: ");
		result.append(performedBy);
		result.append(", realizes: ");
		result.append(realizes);
		result.append(", describes: ");
		result.append(describes);
		result.append(", nextTask: ");
		result.append(nextTask);
		result.append(", loop: ");
		result.append(loop);
		result.append(", wait: ");
		result.append(wait);
		result.append(", contextRule: ");
		result.append(contextRule);
		result.append(", flowName: ");
		result.append(flowName);
		result.append(", scopeName: ");
		result.append(scopeName);
		result.append(", onMessage: ");
		result.append(onMessage);
		result.append(", switchTo: ");
		result.append(switchTo);
		result.append(", mergeTo: ");
		result.append(mergeTo);
		result.append(')');
		return result.toString();
	}

} //TaskImpl
