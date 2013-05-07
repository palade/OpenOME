package org.eclipse.bpel.ui.automation;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.xml.namespace.QName;
import org.eclipse.bpel.ui.uiextensionmodel.impl.VariableExtensionImpl;
import org.eclipse.bpel.common.extension.model.Extension;
import org.eclipse.bpel.common.extension.model.ExtensionmodelFactory;
import org.eclipse.bpel.common.extension.model.impl.ExtensionMapImpl;
import org.eclipse.bpel.model.BPELFactory;
import org.eclipse.bpel.model.Extensions;
import org.eclipse.bpel.model.Variable;
import org.eclipse.bpel.common.extension.model.impl.ExtensionImpl;

import org.eclipse.bpel.model.impl.ProcessImpl;
import org.eclipse.bpel.model.resource.BPELResourceImpl;
import org.eclipse.bpel.ui.BPELEditor;
import org.eclipse.bpel.ui.BPELMultipageEditorPart;
import org.eclipse.bpel.ui.uiextensionmodel.ProcessExtension;
import org.eclipse.bpel.ui.uiextensionmodel.UiextensionmodelFactory;
import org.eclipse.bpel.ui.uiextensionmodel.VariableExtension;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.wst.wsdl.Definition;
import org.eclipse.wst.wsdl.ExtensibilityElement;
import org.eclipse.wst.wsdl.Message;
import org.eclipse.wst.wsdl.WSDLFactory;
import org.eclipse.xsd.XSDElementDeclaration;
import org.eclipse.xsd.XSDFactory;
import org.eclipse.xsd.XSDTypeDefinition;

public class VariableGenerator {
	public boolean generateVariable(String serviceName,String methodName,String namespace,String wsdlLocation,String inputMessage,String outputMessage)
	{
		if(serviceName == null || "".equals(serviceName))
			return false;
		IWorkbenchWindow iww = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		IWorkbenchPage iwp = iww.getActivePage();
		IEditorPart idp = iwp.getActiveEditor();
		BPELMultipageEditorPart e = (BPELMultipageEditorPart)idp;
		BPELEditor editor = (BPELEditor)e.getActiveEditor();
		ResourceSet set = editor.getResourceSet();
		BPELResourceImpl xmires = null;
		XMIResourceImpl xmi = null;//。bpelex文件
		for(Resource res : set.getResources())
		{
			System.out.println(res);
			if(res instanceof BPELResourceImpl)
				xmires = (BPELResourceImpl)res;
			if(res instanceof XMIResourceImpl)
				xmi = (XMIResourceImpl)res;
		}
	
		//System.out.println(xmi.getContents());
		//在xmi文件中申明了bpel模型的所有元素，使用href进行链接。
		// <extensionObject xsi:type="org.eclipse.bpel.ui.uiextensionmodel:ProcessExtension" modificationStamp="1365586276391"/>
		ExtensionMapImpl eMap = (ExtensionMapImpl)xmi.getContents().get(0);//得到根元素ProcessExtension
		
		for(EObject o :eMap.getExtensions())
		{
			ExtensionImpl ii = (ExtensionImpl)o;
			
			if(ii.basicGetExtendedObject() instanceof ProcessImpl)
			{
				//System.out.println("------------"+ii.getExtensionObject());
				ProcessImpl pi = (ProcessImpl)ii.basicGetExtendedObject();
				pi.getElement().setAttribute("xmlns:ns1", namespace);
				//在模型中添加variable
//				<bpel:variables>
//		        <!-- Reference to the message passed as input during initiation -->
//		        <bpel:variable name="input"
//		                  messageType="tns:pmmRequestMessage"/>
//		                  
//		        <!-- 
//		          Reference to the message that will be returned to the requester
//		          -->
//		        <bpel:variable name="output"
//		                  messageType="tns:pmmResponseMessage"/>
//		        <bpel:variable name="addRequest" messageType="ns1:addRequest"></bpel:variable>
//		        <bpel:variable name="addResponse" messageType="ns1:addResponse"></bpel:variable>
//		       
//		    </bpel:variables>

				org.eclipse.bpel.model.impl.VariablesImpl vars=(org.eclipse.bpel.model.impl.VariablesImpl)pi.getVariables();
				EList<Variable> varList = vars.getChildren();
				//process.getVariables().addExtensibilityElement(arg0)
				//创建variable实例并set name为inputMessage
				Variable vr = BPELFactory.eINSTANCE.createVariable();
				vr.setName(inputMessage);
				//创建message实例，设置messageType为“ns1:"+inputMessage”格式
				Message msg = WSDLFactory.eINSTANCE.createMessage();
				msg.setQName(new QName("ns1:"+inputMessage));
				vr.setMessageType(msg);
			
				Variable vrout = BPELFactory.eINSTANCE.createVariable();
				vrout.setName(outputMessage);
				
				Message outMsg = WSDLFactory.eINSTANCE.createMessage();
				outMsg.setQName(new QName("ns1:"+outputMessage));
				vrout.setMessageType(outMsg);
				//将variable标签内容添加入list中，包括输入输出名称以及消息类型
				varList.add(vr);
				varList.add(vrout);
				//在xmi文件中添加申明
				Extension i = ExtensionmodelFactory.eINSTANCE.createExtension();
				i.setExtendedObject(vr);
				VariableExtension vei = UiextensionmodelFactory.eINSTANCE.createVariableExtension();
				i.setExtensionObject(vei);
				
				Extension i2 = ExtensionmodelFactory.eINSTANCE.createExtension();
				i2.setExtendedObject(vrout);
				VariableExtension vei2 = UiextensionmodelFactory.eINSTANCE.createVariableExtension();
				i2.setExtensionObject(vei2);
				
				
				eMap.getExtensions().add(i);
				eMap.getExtensions().add(i2);
			}
		}
	
//		for(EObject obj : xmires.getContents())
//		{
//			ProcessImpl process = (ProcessImpl)obj;
//			process.getElement().setAttribute("xmlns:ns1", namespace);
//			
//			org.eclipse.bpel.model.impl.VariablesImpl vars=(org.eclipse.bpel.model.impl.VariablesImpl)process.getVariables();
//			EList<Variable> varList = vars.getChildren();
//			//process.getVariables().addExtensibilityElement(arg0)
//			Variable vr = BPELFactory.eINSTANCE.createVariable();
//			vr.setName("InputVar");
//			
//			Message msg = WSDLFactory.eINSTANCE.createMessage();
//			msg.setQName(new QName("ns1:"+inputMessage));
//			vr.setMessageType(msg);
//		
//			Variable vrout = BPELFactory.eINSTANCE.createVariable();
//			vrout.setName("OutputVar");
//			
//			Message outMsg = WSDLFactory.eINSTANCE.createMessage();
//			outMsg.setQName(new QName("ns1:"+outputMessage));
//			vrout.setMessageType(outMsg);
////			varList.add(vr);
////			varList.add(vrout);
//		
//			
//		}
		return true;
	}
}
