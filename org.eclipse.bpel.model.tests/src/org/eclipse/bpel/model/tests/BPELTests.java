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

/**
 * <!-- begin-user-doc -->
 * A test suite for the '<em><b>model</b></em>' package.
 * <!-- end-user-doc -->
 * @generated
 */
public class BPELTests extends TestSuite {

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
		TestSuite suite = new BPELTests("model Tests"); //$NON-NLS-1$
		suite.addTestSuite(ProcessTest.class);
		suite.addTestSuite(PartnerLinkTest.class);
		suite.addTestSuite(FaultHandlerTest.class);
		suite.addTestSuite(ActivityTest.class);
		suite.addTestSuite(CorrelationSetTest.class);
		suite.addTestSuite(InvokeTest.class);
		suite.addTestSuite(LinkTest.class);
		suite.addTestSuite(CatchTest.class);
		suite.addTestSuite(ReplyTest.class);
		suite.addTestSuite(PartnerActivityTest.class);
		suite.addTestSuite(ReceiveTest.class);
		suite.addTestSuite(ExitTest.class);
		suite.addTestSuite(ThrowTest.class);
		suite.addTestSuite(WaitTest.class);
		suite.addTestSuite(EmptyTest.class);
		suite.addTestSuite(SequenceTest.class);
		suite.addTestSuite(WhileTest.class);
		suite.addTestSuite(PickTest.class);
		suite.addTestSuite(FlowTest.class);
		suite.addTestSuite(OnAlarmTest.class);
		suite.addTestSuite(AssignTest.class);
		suite.addTestSuite(CopyTest.class);
		suite.addTestSuite(ExtensionTest.class);
		suite.addTestSuite(ScopeTest.class);
		suite.addTestSuite(CompensateScopeTest.class);
		suite.addTestSuite(CompensationHandlerTest.class);
		suite.addTestSuite(ToTest.class);
		suite.addTestSuite(FromTest.class);
		suite.addTestSuite(OnMessageTest.class);
		suite.addTestSuite(ExpressionTest.class);
		suite.addTestSuite(BooleanExpressionTest.class);
		suite.addTestSuite(CorrelationTest.class);
		suite.addTestSuite(MessageExchangeTest.class);
		suite.addTestSuite(EventHandlerTest.class);
		suite.addTestSuite(SourceTest.class);
		suite.addTestSuite(TargetTest.class);
		suite.addTestSuite(PartnerLinksTest.class);
		suite.addTestSuite(MessageExchangesTest.class);
		suite.addTestSuite(VariablesTest.class);
		suite.addTestSuite(CorrelationSetsTest.class);
		suite.addTestSuite(LinksTest.class);
		suite.addTestSuite(CatchAllTest.class);
		suite.addTestSuite(CorrelationsTest.class);
		suite.addTestSuite(VariableTest.class);
		suite.addTestSuite(UnknownExtensibilityAttributeTest.class);
		suite.addTestSuite(OnEventTest.class);
		suite.addTestSuite(ImportTest.class);
		suite.addTestSuite(RethrowTest.class);
		suite.addTestSuite(ConditionTest.class);
		suite.addTestSuite(TargetsTest.class);
		suite.addTestSuite(SourcesTest.class);
		suite.addTestSuite(QueryTest.class);
		suite.addTestSuite(ServiceRefTest.class);
		suite.addTestSuite(ExtensionsTest.class);
		suite.addTestSuite(ExtensionActivityTest.class);
		suite.addTestSuite(FromPartTest.class);
		suite.addTestSuite(ToPartTest.class);
		suite.addTestSuite(OpaqueActivityTest.class);
		suite.addTestSuite(ForEachTest.class);
		suite.addTestSuite(RepeatUntilTest.class);
		suite.addTestSuite(TerminationHandlerTest.class);
		suite.addTestSuite(ValidateTest.class);
		suite.addTestSuite(IfTest.class);
		suite.addTestSuite(ElseIfTest.class);
		suite.addTestSuite(ElseTest.class);
		suite.addTestSuite(CompletionConditionTest.class);
		suite.addTestSuite(BranchesTest.class);
		suite.addTestSuite(BPELExtensibleElementTest.class);
		suite.addTestSuite(DocumentationTest.class);
		suite.addTestSuite(CompensateTest.class);
		suite.addTestSuite(FromPartsTest.class);
		suite.addTestSuite(ToPartsTest.class);
		return suite;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BPELTests(String name) {
		super(name);
	}

} //BPELTests
