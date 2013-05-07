/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.bpel.model.tests;

import junit.textui.TestRunner;

import org.eclipse.bpel.model.BPELFactory;
import org.eclipse.bpel.model.PartnerActivity;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Partner Activity</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class PartnerActivityTest extends ActivityTest {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(PartnerActivityTest.class);
	}

	/**
	 * Constructs a new Partner Activity test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PartnerActivityTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Partner Activity test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected PartnerActivity getFixture() {
		return (PartnerActivity) fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(BPELFactory.eINSTANCE.createPartnerActivity());
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

} //PartnerActivityTest
