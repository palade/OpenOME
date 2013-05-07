/**
 * Copyright 2001-2008 University of Toronto
 *
 * $Id$
 */
package edu.toronto.cs.openome_model;

import org.eclipse.emf.common.util.EList;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Task</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link edu.toronto.cs.openome_model.Task#getHasInput <em>Has Input</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.Task#getHasLocal <em>Has Local</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.Task#getHasOutput <em>Has Output</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.Task#getHasClinet <em>Has Clinet</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.Task#getHasParameter <em>Has Parameter</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.Task#getHasParticipant <em>Has Participant</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.Task#getHasPrecondition <em>Has Precondition</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.Task#getHasResult <em>Has Result</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.Task#getPerformedBy <em>Performed By</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.Task#getRealizes <em>Realizes</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.Task#getDescribes <em>Describes</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.Task#getNextTask <em>Next Task</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.Task#getLoop <em>Loop</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.Task#isWait <em>Wait</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.Task#getContextRule <em>Context Rule</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.Task#getFlowName <em>Flow Name</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.Task#getScopeName <em>Scope Name</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.Task#getOnMessage <em>On Message</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.Task#getSwitchTo <em>Switch To</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.Task#getMergeTo <em>Merge To</em>}</li>
 * </ul>
 * </p>
 *
 * @see edu.toronto.cs.openome_model.openome_modelPackage#getTask()
 * @model
 * @generated
 */
public interface Task extends Intention {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String copyright = "Copyright 2001-2008 University of Toronto";

	/**
	 * Returns the value of the '<em><b>Has Input</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Has Input</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Has Input</em>' attribute list.
	 * @see edu.toronto.cs.openome_model.openome_modelPackage#getTask_HasInput()
	 * @model
	 * @generated
	 */
	EList<String> getHasInput();

	/**
	 * Returns the value of the '<em><b>Has Local</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Has Local</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Has Local</em>' attribute.
	 * @see #setHasLocal(String)
	 * @see edu.toronto.cs.openome_model.openome_modelPackage#getTask_HasLocal()
	 * @model
	 * @generated
	 */
	String getHasLocal();

	/**
	 * Sets the value of the '{@link edu.toronto.cs.openome_model.Task#getHasLocal <em>Has Local</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Has Local</em>' attribute.
	 * @see #getHasLocal()
	 * @generated
	 */
	void setHasLocal(String value);

	/**
	 * Returns the value of the '<em><b>Has Output</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Has Output</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Has Output</em>' attribute.
	 * @see #setHasOutput(String)
	 * @see edu.toronto.cs.openome_model.openome_modelPackage#getTask_HasOutput()
	 * @model
	 * @generated
	 */
	String getHasOutput();

	/**
	 * Sets the value of the '{@link edu.toronto.cs.openome_model.Task#getHasOutput <em>Has Output</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Has Output</em>' attribute.
	 * @see #getHasOutput()
	 * @generated
	 */
	void setHasOutput(String value);

	/**
	 * Returns the value of the '<em><b>Has Clinet</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Has Clinet</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Has Clinet</em>' attribute.
	 * @see #setHasClinet(String)
	 * @see edu.toronto.cs.openome_model.openome_modelPackage#getTask_HasClinet()
	 * @model
	 * @generated
	 */
	String getHasClinet();

	/**
	 * Sets the value of the '{@link edu.toronto.cs.openome_model.Task#getHasClinet <em>Has Clinet</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Has Clinet</em>' attribute.
	 * @see #getHasClinet()
	 * @generated
	 */
	void setHasClinet(String value);

	/**
	 * Returns the value of the '<em><b>Has Parameter</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Has Parameter</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Has Parameter</em>' attribute list.
	 * @see edu.toronto.cs.openome_model.openome_modelPackage#getTask_HasParameter()
	 * @model
	 * @generated
	 */
	EList<String> getHasParameter();

	/**
	 * Returns the value of the '<em><b>Has Participant</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Has Participant</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Has Participant</em>' attribute.
	 * @see #setHasParticipant(String)
	 * @see edu.toronto.cs.openome_model.openome_modelPackage#getTask_HasParticipant()
	 * @model
	 * @generated
	 */
	String getHasParticipant();

	/**
	 * Sets the value of the '{@link edu.toronto.cs.openome_model.Task#getHasParticipant <em>Has Participant</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Has Participant</em>' attribute.
	 * @see #getHasParticipant()
	 * @generated
	 */
	void setHasParticipant(String value);

	/**
	 * Returns the value of the '<em><b>Has Precondition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Has Precondition</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Has Precondition</em>' attribute.
	 * @see #setHasPrecondition(String)
	 * @see edu.toronto.cs.openome_model.openome_modelPackage#getTask_HasPrecondition()
	 * @model
	 * @generated
	 */
	String getHasPrecondition();

	/**
	 * Sets the value of the '{@link edu.toronto.cs.openome_model.Task#getHasPrecondition <em>Has Precondition</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Has Precondition</em>' attribute.
	 * @see #getHasPrecondition()
	 * @generated
	 */
	void setHasPrecondition(String value);

	/**
	 * Returns the value of the '<em><b>Has Result</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Has Result</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Has Result</em>' attribute.
	 * @see #setHasResult(String)
	 * @see edu.toronto.cs.openome_model.openome_modelPackage#getTask_HasResult()
	 * @model
	 * @generated
	 */
	String getHasResult();

	/**
	 * Sets the value of the '{@link edu.toronto.cs.openome_model.Task#getHasResult <em>Has Result</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Has Result</em>' attribute.
	 * @see #getHasResult()
	 * @generated
	 */
	void setHasResult(String value);

	/**
	 * Returns the value of the '<em><b>Performed By</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Performed By</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Performed By</em>' attribute.
	 * @see #setPerformedBy(String)
	 * @see edu.toronto.cs.openome_model.openome_modelPackage#getTask_PerformedBy()
	 * @model
	 * @generated
	 */
	String getPerformedBy();

	/**
	 * Sets the value of the '{@link edu.toronto.cs.openome_model.Task#getPerformedBy <em>Performed By</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Performed By</em>' attribute.
	 * @see #getPerformedBy()
	 * @generated
	 */
	void setPerformedBy(String value);

	/**
	 * Returns the value of the '<em><b>Realizes</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Realizes</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Realizes</em>' attribute.
	 * @see #setRealizes(String)
	 * @see edu.toronto.cs.openome_model.openome_modelPackage#getTask_Realizes()
	 * @model
	 * @generated
	 */
	String getRealizes();

	/**
	 * Sets the value of the '{@link edu.toronto.cs.openome_model.Task#getRealizes <em>Realizes</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Realizes</em>' attribute.
	 * @see #getRealizes()
	 * @generated
	 */
	void setRealizes(String value);

	/**
	 * Returns the value of the '<em><b>Describes</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Describes</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Describes</em>' attribute.
	 * @see #setDescribes(String)
	 * @see edu.toronto.cs.openome_model.openome_modelPackage#getTask_Describes()
	 * @model
	 * @generated
	 */
	String getDescribes();

	/**
	 * Sets the value of the '{@link edu.toronto.cs.openome_model.Task#getDescribes <em>Describes</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Describes</em>' attribute.
	 * @see #getDescribes()
	 * @generated
	 */
	void setDescribes(String value);

	/**
	 * Returns the value of the '<em><b>Next Task</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Next Task</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Next Task</em>' attribute.
	 * @see #setNextTask(String)
	 * @see edu.toronto.cs.openome_model.openome_modelPackage#getTask_NextTask()
	 * @model
	 * @generated
	 */
	String getNextTask();

	/**
	 * Sets the value of the '{@link edu.toronto.cs.openome_model.Task#getNextTask <em>Next Task</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Next Task</em>' attribute.
	 * @see #getNextTask()
	 * @generated
	 */
	void setNextTask(String value);

	/**
	 * Returns the value of the '<em><b>Loop</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Loop</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Loop</em>' attribute.
	 * @see #setLoop(Boolean)
	 * @see edu.toronto.cs.openome_model.openome_modelPackage#getTask_Loop()
	 * @model
	 * @generated
	 */
	Boolean getLoop();

	/**
	 * Sets the value of the '{@link edu.toronto.cs.openome_model.Task#getLoop <em>Loop</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Loop</em>' attribute.
	 * @see #getLoop()
	 * @generated
	 */
	void setLoop(Boolean value);

	/**
	 * Returns the value of the '<em><b>Wait</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Wait</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Wait</em>' attribute.
	 * @see #setWait(boolean)
	 * @see edu.toronto.cs.openome_model.openome_modelPackage#getTask_Wait()
	 * @model
	 * @generated
	 */
	boolean isWait();

	/**
	 * Sets the value of the '{@link edu.toronto.cs.openome_model.Task#isWait <em>Wait</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Wait</em>' attribute.
	 * @see #isWait()
	 * @generated
	 */
	void setWait(boolean value);

	/**
	 * Returns the value of the '<em><b>Context Rule</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Context Rule</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Context Rule</em>' attribute.
	 * @see #setContextRule(String)
	 * @see edu.toronto.cs.openome_model.openome_modelPackage#getTask_ContextRule()
	 * @model
	 * @generated
	 */
	String getContextRule();

	/**
	 * Sets the value of the '{@link edu.toronto.cs.openome_model.Task#getContextRule <em>Context Rule</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Context Rule</em>' attribute.
	 * @see #getContextRule()
	 * @generated
	 */
	void setContextRule(String value);

	/**
	 * Returns the value of the '<em><b>Flow Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Flow Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Flow Name</em>' attribute.
	 * @see #setFlowName(String)
	 * @see edu.toronto.cs.openome_model.openome_modelPackage#getTask_FlowName()
	 * @model
	 * @generated
	 */
	String getFlowName();

	/**
	 * Sets the value of the '{@link edu.toronto.cs.openome_model.Task#getFlowName <em>Flow Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Flow Name</em>' attribute.
	 * @see #getFlowName()
	 * @generated
	 */
	void setFlowName(String value);

	/**
	 * Returns the value of the '<em><b>Scope Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Scope Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Scope Name</em>' attribute.
	 * @see #setScopeName(String)
	 * @see edu.toronto.cs.openome_model.openome_modelPackage#getTask_ScopeName()
	 * @model
	 * @generated
	 */
	String getScopeName();

	/**
	 * Sets the value of the '{@link edu.toronto.cs.openome_model.Task#getScopeName <em>Scope Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Scope Name</em>' attribute.
	 * @see #getScopeName()
	 * @generated
	 */
	void setScopeName(String value);

	/**
	 * Returns the value of the '<em><b>On Message</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>On Message</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>On Message</em>' attribute.
	 * @see #setOnMessage(String)
	 * @see edu.toronto.cs.openome_model.openome_modelPackage#getTask_OnMessage()
	 * @model
	 * @generated
	 */
	String getOnMessage();

	/**
	 * Sets the value of the '{@link edu.toronto.cs.openome_model.Task#getOnMessage <em>On Message</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>On Message</em>' attribute.
	 * @see #getOnMessage()
	 * @generated
	 */
	void setOnMessage(String value);

	/**
	 * Returns the value of the '<em><b>Switch To</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Switch To</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Switch To</em>' attribute list.
	 * @see edu.toronto.cs.openome_model.openome_modelPackage#getTask_SwitchTo()
	 * @model
	 * @generated
	 */
	EList<String> getSwitchTo();

	/**
	 * Returns the value of the '<em><b>Merge To</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Merge To</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Merge To</em>' attribute.
	 * @see #setMergeTo(String)
	 * @see edu.toronto.cs.openome_model.openome_modelPackage#getTask_MergeTo()
	 * @model
	 * @generated
	 */
	String getMergeTo();

	/**
	 * Sets the value of the '{@link edu.toronto.cs.openome_model.Task#getMergeTo <em>Merge To</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Merge To</em>' attribute.
	 * @see #getMergeTo()
	 * @generated
	 */
	void setMergeTo(String value);

} // Task
