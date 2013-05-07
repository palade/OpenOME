/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.bpel.model.tests;

import junit.textui.TestRunner;

import org.eclipse.bpel.model.BPELFactory;
import org.eclipse.bpel.model.Import;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Import</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class ImportTest extends BPELExtensibleElementTest {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(ImportTest.class);
	}

	/**
	 * Constructs a new Import test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ImportTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Import test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected Import getFixture() {
		return (Import) fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(BPELFactory.eINSTANCE.createImport());
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

} //ImportTest
