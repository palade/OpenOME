/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.bpel.model.tests;

import junit.textui.TestRunner;

import org.eclipse.bpel.model.BPELFactory;
import org.eclipse.bpel.model.Receive;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Receive</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class ReceiveTest extends PartnerActivityTest {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(ReceiveTest.class);
	}

	/**
	 * Constructs a new Receive test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ReceiveTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Receive test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected Receive getFixture() {
		return (Receive) fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(BPELFactory.eINSTANCE.createReceive());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#tearDown()
	 * @generated
	 */
	@Override
	protected void tearDown() throws Exception {
		setFixture(null);
	}

} //ReceiveTest
