/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.bpel.model.tests;

import junit.textui.TestRunner;

import org.eclipse.bpel.model.BPELFactory;
import org.eclipse.bpel.model.Empty;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Empty</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class EmptyTest extends ActivityTest {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(EmptyTest.class);
	}

	/**
	 * Constructs a new Empty test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EmptyTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Empty test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected Empty getFixture() {
		return (Empty) fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(BPELFactory.eINSTANCE.createEmpty());
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

} //EmptyTest
