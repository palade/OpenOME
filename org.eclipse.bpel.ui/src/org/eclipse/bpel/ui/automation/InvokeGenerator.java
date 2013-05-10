package org.eclipse.bpel.ui.automation;

import java.io.FileInputStream;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Set;



import org.apache.xml.serialize.XMLSerializer;
import org.eclipse.wst.wsdl.Definition;
import org.eclipse.wst.wsdl.ExtensibilityElement;
import org.eclipse.wst.wsdl.Import;
import org.eclipse.wst.wsdl.Operation;
import org.eclipse.wst.wsdl.PortType;
import org.eclipse.wst.wsdl.Service;
import org.eclipse.wst.wsdl.Types;
import org.eclipse.wst.wsdl.WSDLFactory;
import org.eclipse.wst.wsdl.internal.impl.DefinitionImpl;
import org.eclipse.wst.wsdl.internal.impl.WSDLFactoryImpl;
import org.eclipse.wst.wsdl.util.WSDLResourceImpl;
import org.eclipse.xsd.XSDElementDeclaration;

import org.eclipse.bpel.common.extension.model.impl.ExtensionImpl;
import org.eclipse.bpel.common.extension.model.impl.ExtensionMapImpl;
import org.eclipse.bpel.model.BPELFactory;
import org.eclipse.bpel.model.Invoke;
import org.eclipse.bpel.model.PartnerLink;
import org.eclipse.bpel.model.Variable;
import org.eclipse.bpel.model.impl.ActivityImpl;
import org.eclipse.bpel.model.partnerlinktype.PartnerLinkType;
import org.eclipse.bpel.model.partnerlinktype.PartnerlinktypeFactory;
import org.eclipse.bpel.model.partnerlinktype.PartnerlinktypePackage;
import org.eclipse.bpel.model.partnerlinktype.Role;
import org.eclipse.bpel.model.partnerlinktype.RolePortType;
import org.eclipse.bpel.ui.BPELEditor;
import org.eclipse.bpel.ui.BPELMultipageEditorPart;
import org.eclipse.bpel.ui.actions.ColorHolder;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.emf.ecore.xml.type.internal.QName;

import org.eclipse.bpel.model.resource.BPELResourceImpl;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;

import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.Namespace;

import org.eclipse.bpel.model.impl.*;
import org.exolab.castor.util.Iterator;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

//import org.w3c.dom.Document;     

import org.w3c.dom.Document;     

import org.w3c.dom.NamedNodeMap;     
  
import org.w3c.dom.Node;     
import org.xml.sax.SAXException;
  
    
  
import com.sun.org.apache.xerces.internal.parsers.DOMParser;      


public class InvokeGenerator {
	public boolean generateInvoke(String serviceName,String methodName,String namespace,String inputMessage,String outputMessage,String invokeName) throws JDOMException, CoreException, IOException
	{
		
		if(serviceName == null || "".equals(serviceName))
			return false;
		String partnerLinkValue = methodName+"PL";
		String PortTypeValue = "ns1:"+serviceName.substring(0, serviceName.lastIndexOf("Service"));
		System.out.println(PortTypeValue);
		
		IWorkbenchWindow iww = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		IWorkbenchPage iwp = iww.getActivePage();
		IEditorPart idp = iwp.getActiveEditor();
		BPELMultipageEditorPart e = (BPELMultipageEditorPart)idp;
		BPELEditor editor = (BPELEditor)e.getActiveEditor();//获取bpel editor图形化的界面
		ResourceSet set = editor.getResourceSet();
		BPELResourceImpl xmires = null;//。bpel
		WSDLResourceImpl wsdlres = null;//。wsdl
		for(Resource res : set.getResources())
		{
			System.out.println(res);
			System.out.println(res.getURI());
			if(res instanceof BPELResourceImpl)
				xmires = (BPELResourceImpl)res;
			
		}
		
		for(EObject obj : xmires.getContents())//obj是图形化.bpel文件中的内容，即model
		{
			ProcessImpl process = (ProcessImpl)obj;//process是bpel模型中的根模型
			process.getElement().setAttribute("xmlns:ns1", namespace);
			org.eclipse.bpel.model.impl.PartnerLinksImpl links =(org.eclipse.bpel.model.impl.PartnerLinksImpl)process.getPartnerLinks();
			
			//EList<PartnerLink> list = links.getChildren();//获得当前所有的伙伴连接
			System.out.println("2-------------------------------");
			List<EObject> u = process.eContents();
			for(EObject ee : u)
			{
				//System.out.println(ee);
				if(ee instanceof SequenceImpl)
				{
					SequenceImpl s = (SequenceImpl)ee;
					EList<EObject> t = s.getWSDLContents();
					for(EObject ooo : t)
					{
						if(ooo instanceof InvokeImpl)
						{
							InvokeImpl invo = (InvokeImpl)ooo;
							if(invo.getName().equalsIgnoreCase(invokeName))
							{
								System.out.println(invo.getName());
								System.out.println("zzzzzzzzzzzzzzzzzzzzzzzzzzzzzz:"+invo);
								PartnerLink pl = BPELFactory.eINSTANCE.createPartnerLink();//创建PartnerLink模型的一个实例pl
								pl.setName(partnerLinkValue);//将此实例的名字赋值
								invo.setPartnerLink(pl);		
								
								Operation op = WSDLFactory.eINSTANCE.createOperation();
								op.setName(methodName);
								invo.setOperation(op);
								
//								PortType pt = WSDLFactory.eINSTANCE.createPortType();
//								pt.getElement().setAttribute("ns1",serviceName.substring(0, serviceName.lastIndexOf("Service")));
							
//								QName temp = new QName(PortTypeValue);
//								temp.setPrefix("ns1:");
//								
//								pt.setQName(temp);
//								invo.setPortType(pt);
								PortType port =  WSDLFactoryImpl.eINSTANCE.createPortType();
//								port.getElement().setAttribute("ns1:", PortTypeValue);
								port.setQName(new QName(PortTypeValue));
								invo.setPortType(port);
								
								Variable vr = BPELFactory.eINSTANCE.createVariable();
								vr.setName(inputMessage);
								invo.setInputVariable(vr);
								Variable vr1 = BPELFactory.eINSTANCE.createVariable();
								vr1.setName(outputMessage);
								invo.setOutputVariable(vr1);
							}
						}
					}
					System.out.println("fffffffffffffffffffffffffffffffffff");
				}
			}
//			Invoke invo = BPELFactory.eINSTANCE.createInvoke();
//			
//			PartnerLink pl = BPELFactory.eINSTANCE.createPartnerLink();//创建PartnerLink模型的一个实例pl
//			pl.setName(partnerLinkValue);//将此实例的名字赋值
//			invo.setPartnerLink(pl);		
//			
//			Operation op = WSDLFactory.eINSTANCE.createOperation();
//			op.setName(methodName);
//			invo.setOperation(op);
//			
//			PortType pt = WSDLFactory.eINSTANCE.createPortType();
//			pt.setQName(new QName("tns:"+serviceName));
//			invo.setPortType(pt);
//			
//			Variable vr = BPELFactory.eINSTANCE.createVariable();
//			vr.setName(inputMessage);
//			invo.setInputVariable(vr);
//			Variable vr1 = BPELFactory.eINSTANCE.createVariable();
//			vr1.setName(outputMessage);
//			invo.setOutputVariable(vr1);
//			
			//用EMF对.bpel文件进行修改，直接修改模型方式，直接创建添加模型
			
		 
		}
		 return true;
	}
}
