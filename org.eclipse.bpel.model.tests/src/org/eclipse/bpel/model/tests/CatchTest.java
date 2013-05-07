/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.bpel.model.tests;

import junit.textui.TestRunner;

import org.eclipse.bpel.model.BPELFactory;
import org.eclipse.bpel.model.Catch;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Catch</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class CatchTest extends BPELExtensibleElementTest {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(CatchTest.class);
	}

	/**
	 * Constructs a new Catch test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CatchTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Catch test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected Catch getFixture() {
		return (Catch) fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(BPELFactory.eINSTANCE.createCatch());
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

} //CatchTest
