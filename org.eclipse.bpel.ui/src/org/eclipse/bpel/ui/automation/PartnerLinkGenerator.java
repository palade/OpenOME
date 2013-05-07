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


//PartnerLink����ӹ���
public class PartnerLinkGenerator {
	public boolean generateLink(String serviceName,String methodName,String namespace,String wsdlLocation) throws JDOMException, CoreException, IOException
	{
		
		if(serviceName == null || "".equals(serviceName))
			return false;
		String PartnerLinknameValue = methodName+"PL";
		String partnerLinkTypeValue = "tns:"+methodName+"PLT";
		String partnerRoleValue = methodName+"Provider";
		String PortTypeValue = "wsdl:"+serviceName.substring(0, serviceName.lastIndexOf("Service"));
		
		IWorkbenchWindow iww = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		IWorkbenchPage iwp = iww.getActivePage();
		IEditorPart idp = iwp.getActiveEditor();
		BPELMultipageEditorPart e = (BPELMultipageEditorPart)idp;
		BPELEditor editor = (BPELEditor)e.getActiveEditor();//��ȡbpel editorͼ�λ��Ľ���
		ResourceSet set = editor.getResourceSet();
		BPELResourceImpl xmires = null;//��bpel
		WSDLResourceImpl wsdlres = null;//��wsdl
		for(Resource res : set.getResources())
		{
			System.out.println(res);
			System.out.println(res.getURI());
			if(res instanceof BPELResourceImpl)
				xmires = (BPELResourceImpl)res;
			
		}
		
		for(EObject obj : xmires.getContents())//obj��ͼ�λ�.bpel�ļ��е����ݣ���model
		{
			ProcessImpl process = (ProcessImpl)obj;//process��bpelģ���еĸ�ģ��
			org.eclipse.bpel.model.impl.PartnerLinksImpl links =(org.eclipse.bpel.model.impl.PartnerLinksImpl)process.getPartnerLinks();
			
			EList<PartnerLink> list = links.getChildren();//��õ�ǰ���еĻ������

			//��EMF��.bpel�ļ������޸ģ�ֱ���޸�ģ�ͷ�ʽ��ֱ�Ӵ������ģ��
			
			//��emf�д���ģ����Ҫ�ȹ�����ʵ�������������
			PartnerLink pl = BPELFactory.eINSTANCE.createPartnerLink();//����PartnerLinkģ�͵�һ��ʵ��pl
			pl.setName(PartnerLinknameValue);//����ʵ�������ָ�ֵ
		
//			XSDElementDeclaration ddd = new XSDElementDeclaration();
//			vr.setXSDElement(value)
			//partnerLinkType���������͵�һ�����ԣ�tns�������ط����壬ͨ��tns�õ�types������
			PartnerLinkType type = PartnerlinktypeFactory.eINSTANCE.createPartnerLinkType();
			type.setName(partnerLinkTypeValue);//tns:addPLT
			
			pl.setPartnerLinkType(type);// partnerLinkType="tns:addPLT" 
			//partnerRole
			Role role = PartnerlinktypeFactory.eINSTANCE.createRole();
			role.setName(partnerRoleValue);
			role.setPortType(PortTypeValue);
			pl.setPartnerRole(role);
			type.getRole().add(role);
			
			org.eclipse.wst.wsdl.PortType port =  WSDLFactoryImpl.eINSTANCE.createPortType();
			port.setQName(new QName(PortTypeValue));
			role.setPortType(port);
			list.add(pl);
			
//			System.out.println(wsdlres.getContents().size());
//			for(EObject wsdlobj : wsdlres.getContents())
//			{
//				Definition def = (Definition)wsdlobj;
//				
//				
//				Import importDel =
//						WSDLFactory.eINSTANCE.createImport();
//			
//				importDel.setLocationURI("AddService.wsdl");
//				importDel.setNamespaceURI("http://add.example.ws");
//				importDel.setNamespaceURI(def.getTargetNamespace()!=null?def.getTargetNamespace():"");
//				importDel.setDefinition(def);
//				importDel.setEnclosingDefinition(def);
//				def.addImport(importDel);
//				def.getEImports().add(importDel);
//				
//				System.out.println("z1------------");
//				
//				PartnerLinkType plt = PartnerlinktypeFactory.eINSTANCE.createPartnerLinkType();
//				plt.setName( "testservice" );
//				
//				
//				// Element doc = plt.getDocumentationElement();		
//				// doc.setNodeValue("Generated by CreatePartnerLinkWizard");
//				
//				Role role1 =  PartnerlinktypeFactory.eINSTANCE.createRole();
//				role1.setName ("RoleName()" );
//				role1.setPortType( port );
//				plt.getRole().add(role1);
//				
//				
//				
//				
//				def.getExtensibilityElements().add(plt);
//				def.getEExtensibilityElements().add(plt);
//				plt.setEnclosingDefinition(def);
//				
//				//ֱ�������ļ���ʽ
////				org.w3c.dom.Document d = def.getDocument();
////				NodeList ee = d.getElementsByTagName("definitions");
////				Node defNode = ee.item(0);
////				
////				Node fNode = d.createElement("partnerLinkType");
////				defNode.appendChild(fNode);
//
//			}
//		}
//		for(EObject obj : xmires.getContents())
//		{
//			System.out.println(obj);
//			ExtensionMapImpl pi = (ExtensionMapImpl)obj;
//			for(EObject o : pi.eContents())
//			{
//				ExtensionImpl ex = (ExtensionImpl)o;
//				System.out.println(ex.basicGetExtendedObject());
//				
//				
//			}
//			
//			
//		}
		
			//��.wsdl�ļ���DOM��ʽ�����޸�
			
		String fileName = serviceName;
		IEditorPart activeEditor =PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
		 IEditorInput editorInput = activeEditor.getEditorInput();
		 if(editorInput instanceof FileEditorInput)
		 {
			 FileEditorInput input = (FileEditorInput)   editorInput;
			 IFile iFile = input.getFile();
			 IProject iProject = iFile.getProject();
			 System.out.println(iProject.getLocation());
			 IResource[] irs = iProject.members();
			 for(int i = 0 ; i < irs.length ; i++)
			 {
				 String name = irs[i].getName();
				 if(name != null && name.indexOf("Artifacts.wsdl")!= -1)
				 {
					fileName = irs[i].getLocation().toString();
				 }
			 }
			 if(fileName == null)
				 return false;
			 //����DOM������
			 DOMParser parser = new DOMParser();     
			  
		      try {
				parser.parse(fileName);
			} catch (SAXException ep) {
				// TODO Auto-generated catch block
				ep.printStackTrace();
			}     
		  
		      Document document = parser.getDocument(); //document�������ļ��ĸ�
		      org.w3c.dom.Element def = (org.w3c.dom.Element)document.getElementsByTagName("definitions").item(0);//�õ�<definitions ��Ԫ��
		      org.w3c.dom.Element partnerLinkType = document.createElement("plnk:partnerLinkType");//����Ԫ��
		
		      partnerLinkType.setAttribute("name",partnerLinkTypeValue.split(":")[1]);//���name����
		      
		      org.w3c.dom.Element roleElement = document.createElement("plnk:role");
		      roleElement.setAttribute("name", partnerRoleValue);
		      roleElement.setAttribute("portType", PortTypeValue);
		      
		      partnerLinkType.appendChild(roleElement);//roleԪ�طŵ�type����
		     
		      def.appendChild(partnerLinkType);//type�ŵ�defԪ����
		      def.setAttribute("xmlns:vprop", "http://docs.oasis-open.org/wsbpel/2.0/varprop");
		      def.setAttribute("xmlns:wsdl", namespace);
		      org.w3c.dom.Element importElement = document.createElement("import");
		      importElement.setAttribute("location", wsdlLocation.split("/")[wsdlLocation.split("/").length-1]);
		      importElement.setAttribute("namespace", namespace);
		      
		      def.appendChild(importElement);
		      
		      XMLSerializer serial;     
		      //д���ļ���
		      try    
		    
		      {     
		    
		        serial = new XMLSerializer(new FileOutputStream(fileName), null);     
		    
		        serial.serialize(document);     
		    
		      } catch (FileNotFoundException e1)     
		    
		      {     
		    
		        e1.printStackTrace();     
		    
		      } catch (IOException ew)     
		    
		      {     
		    
		        ew.printStackTrace();     
		    
		      }     
			 //dom
//		 	 SAXBuilder builder = new SAXBuilder();
//		 	 InputStream file = new FileInputStream(fileName);
//		 	 Document document = builder.build(file);//����ĵ�����
//		 	 Element root = document.getRootElement();//��ø��ڵ�
////		 	 Namespace ns = new Namespace("bpel","http://www.bpel.org");
////		 	 root.addNamespaceDeclaration(ns);
//		 	
//		 	List<Element> list = root.getChildren();
//		 	for(Element e : list)
//		 	{
//		 		if(e.getName().equals("partnerLinks"))
//		 		{
//		 			
//		 			 Element linkElement = new Element("partnerLink");
//		 			 linkElement.setAttribute("name",nameValue);
//		 			 linkElement.setAttribute("partnerLinkType",partnerLinkTypeValue);
//		 			 linkElement.setAttribute("partnerRole",partnerRoleValue);
//		 			 Namespace namespace = Namespace.getNamespace("bpel");
//		 			 
//		 			 linkElement.setNamespace(namespace);
//		 			 e.addContent(linkElement);
//		 		}
//		 	}
////		 	XPath path = XPath.newInstance("//bpel:process");
////		 	path.addNamespace("bpel", "http://www.bpel.org");
////		 	List<Element> list = path.selectNodes(document);
////		 	System.out.println(list.size());
////		 	 Element partnerLinksElement = (Element)path.selectNodes(root);
////		 	 Element linkElement = new Element("bpel:partnerLink");
////		 	 linkElement.setAttribute("name",nameValue);
////		 	 linkElement.setAttribute("partnerLinkType",partnerLinkTypeValue);
////		 	 linkElement.setAttribute("partnerRole",partnerRoleValue);
////		 	 
////		 	partnerLinksElement.addContent(linkElement);
//		 	document.setRootElement(root);
//		 	 
//		        //XMLOutputter���ṩ�˽�JDOM�����Ϊ�ֽ���������
//		        XMLOutputter XMLOut=new XMLOutputter();
//		        //XMLOut.setEncoding("GBK");
//		       // XMLOut.setIndent("	");
//		        //������ļ�
//		        XMLOut.setEncoding("utf-8");
//		        XMLOut.setIndent("	");
//		        XMLOut.output(document,new FileOutputStream(fileName));
		 }
		}
		 return true;
	}
}
