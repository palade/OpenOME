/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.bpel.model.tests;

import junit.framework.Test;
import junit.framework.TestSuite;

import junit.textui.TestRunner;

import org.eclipse.bpel.model.messageproperties.tests.MessagepropertiesTests;

import org.eclipse.bpel.model.partnerlinktype.tests.PartnerlinktypeTests;

/**
 * <!-- begin-user-doc -->
 * A test suite for the '<em><b>Bpel</b></em>' model.
 * <!-- end-user-doc -->
 * @generated
 */
public class BpelAllTests extends TestSuite {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(suite());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static Test suite() {
		TestSuite suite = new BpelAllTests("Bpel Tests"); //$NON-NLS-1$
		suite.addTest(BPELTests.suite());
		suite.addTest(PartnerlinktypeTests.suite());
		suite.addTest(MessagepropertiesTests.suite());
		return suite;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BpelAllTests(String name) {
		super(name);
	}

} //BpelAllTests
