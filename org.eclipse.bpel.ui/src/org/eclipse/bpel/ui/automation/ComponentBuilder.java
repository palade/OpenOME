package org.eclipse.bpel.ui.automation;


import java.util.ArrayList;


import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Vector;

import javax.wsdl.Binding;
import javax.wsdl.BindingInput;
import javax.wsdl.BindingOperation;
import javax.wsdl.BindingOutput;
import javax.wsdl.Definition;
import javax.wsdl.Input;
import javax.wsdl.Message;
import javax.wsdl.Operation;
import javax.wsdl.Output;
import javax.wsdl.Part;
import javax.wsdl.Port;
import javax.wsdl.Service;
import javax.wsdl.extensions.ExtensibilityElement;
import javax.wsdl.extensions.soap.SOAPAddress;
import javax.wsdl.extensions.soap.SOAPBinding;
import javax.wsdl.extensions.soap.SOAPBody;
import javax.wsdl.extensions.soap.SOAPOperation;
import javax.wsdl.factory.WSDLFactory;
import javax.wsdl.xml.WSDLReader;
import javax.xml.namespace.QName;

import org.exolab.castor.xml.schema.ComplexType;
import org.exolab.castor.xml.schema.ElementDecl;
import org.exolab.castor.xml.schema.Group;
import org.exolab.castor.xml.schema.Particle;
import org.exolab.castor.xml.schema.Schema;
import org.exolab.castor.xml.schema.SimpleTypesFactory;
import org.exolab.castor.xml.schema.Structure;
import org.exolab.castor.xml.schema.XMLType;
import org.jdom.input.DOMBuilder;




public class ComponentBuilder {

	WSDLFactory wsdlFactory = null;

	SimpleTypesFactory simpleTypesFactory = null;

	private Vector wsdlTypes = new Vector();

	public final static String DEFAULT_SOAP_ENCODING_STYLE = "http://schemas.xmlsoap.org/soap/encoding/";

	public ComponentBuilder() {
		try {
			wsdlFactory = WSDLFactory.newInstance();
			simpleTypesFactory = new SimpleTypesFactory();
		} catch (Throwable t) {
			System.err.println(t.getMessage());
		}
	}

	public ServiceInfo buildserviceinformation(ServiceInfo serviceinfo)
			throws Exception{
		WSDLReader reader=wsdlFactory.newWSDLReader();
		Definition def=reader.readWSDL(null, serviceinfo.getWsdllocation());//wsdl:definitions Definition��Ӧ�����ǩ�����������������
		wsdlTypes = createSchemaFromTypes(def);//wsdl�е�types  <wsdl:types>
		System.out.println("WSDL��Schema�Ѿ��ɹ�������,�ܹ��ж��ٸ�Schema����:"+wsdlTypes.size());
		Map ns = def.getNamespaces();//�õ�wsdl�Ĺ����ռ�
		
		Set ss = ns.entrySet();
	Iterator iter = ss.iterator();//������
	while(iter.hasNext())
	{
		Entry e = (Entry)iter.next();
		System.out.print("key "+e.getKey());System.out.println(",val "+e.getValue());
		
	}
		Map services=def.getServices();//<wsdl:service name="AddServiceService">
		if (services!= null) {
			Iterator svcIter = services.values().iterator();
			populateComponent(serviceinfo, (Service) svcIter.next());//�õ�name��operation
			System.out.println("***��ϲ��!ϵͳ��Web�������:ServiceInfo�Ѿ��ɹ�����***");
			System.out.println("");
		}
		return serviceinfo;
	}

	private Schema createschemafromtype(org.w3c.dom.Element schemaElement,
			Definition wsdlDefinition) {
		System.out.println("���ڵ�Schema����һ��Dom�͵�<xsd:schema>Ԫ��,���Ի�������ȫ,���빹�������ռ������");
		System.out.println("ʹ��JDom,�Ȱ�Dom�͵�<xsd:schema>Ԫ��ת����JDom��...");
		System.out.println("��ʼ����...");
		if (schemaElement == null) {
			System.err.println("Unable to find schema extensibility element in WSDL");
			return null;
		}
		DOMBuilder domBuilder = new DOMBuilder();
		org.jdom.Element jdomSchemaElement = domBuilder.build(schemaElement);
		if (jdomSchemaElement == null) {
			System.err.println("Unable to read schema defined in WSDL");
			return null;
		}
		Map namespaces = wsdlDefinition.getNamespaces();
		if (namespaces != null && !namespaces.isEmpty()) {
			System.out.println("WSDL�ĵ�Definition�����������ռ�Ϊ:");
			Iterator nsIter = namespaces.keySet().iterator();
			while (nsIter.hasNext()) {
				String nsPrefix = (String) nsIter.next();
				String nsURI = (String) namespaces.get(nsPrefix);
				System.out.println("�����ռ�:"+nsPrefix+" "+nsURI);
				if (nsPrefix!=null&&nsPrefix.length() > 0) {
					org.jdom.Namespace nsDecl = org.jdom.Namespace
							.getNamespace(nsPrefix, nsURI);
					jdomSchemaElement.addNamespaceDeclaration(nsDecl);
				}
			}
		}
		//jdomSchemaElement.detach();
		Schema schema = null;
		try {
			schema = XMLSupport.convertElementToSchema(jdomSchemaElement);
		}
		catch (Exception e) {
			System.out.println("a");
			System.err.println(e.getMessage());
			System.out.println("a");
		}
		return schema;
	}

	protected Vector createSchemaFromTypes(Definition wsdlDefinition) {
		System.out.println("*****************");
		System.out.println("����createSchemaFromTypes()����");
		System.out.println("��ʼ��Types�в���Schema,Definition��typesԪ�ص�SchemaԪ�ظ������Զ��,�������Ĳ�����һ��Definition����:");
		Vector schemas=new Vector();
		org.w3c.dom.Element schemaElementt=null;
		wsdlDefinition.addNamespace("xsd", "http://www.w3.org/2001/XMLSchema");
		if (wsdlDefinition.getTypes()!= null) {
			Vector schemaExtElem=findExtensibilityElement(wsdlDefinition
					.getTypes().getExtensibilityElements(), "schema");
			for (int i = 0; i < schemaExtElem.size(); i++) {
				ExtensibilityElement schemaElement = (ExtensibilityElement) schemaExtElem
						.elementAt(i);
				 
//				if (schemaElement != null
//						&& schemaElement instanceof UnknownExtensibilityElement) {
//					schemaElementt = ((UnknownExtensibilityElement) schemaElement)
//							.getElement();
//					Schema schema = createschemafromtype(schemaElementt,
//							wsdlDefinition);
//					schemas.add(schema);
//				}
				if (schemaElement != null) {
					schemaElementt = ((com.ibm.wsdl.extensions.schema.SchemaImpl) schemaElement)
							.getElement();
					
					Schema schema = createschemafromtype(schemaElementt,
							wsdlDefinition);
					schema.addNamespace("xsd", "http://www.w3.org/2001/XMLSchema");
					schemas.add(schema);
				}
			}

		}
		System.out.println("*****************");
		return schemas;
	}

	private ServiceInfo populateComponent(ServiceInfo component, Service service) {//��service��ǩ�е�������䵽component��
		System.out.println("***************************");
		System.out.println("");
		System.out.println("***��ʼ����ϵͳ��Web�������:ServiceInfo***");
		QName qName = service.getQName();
		String namespace = qName.getNamespaceURI();
		System.out.println("namespaceΪ:"+namespace);
		String name = qName.getLocalPart();//service��name
		System.out.println("nameΪ:"+name);
		component.setName(name);
		component.setTargetnamespace(namespace);//����namespace
		Map ports=service.getPorts();//�õ�port
		Iterator portIter = ports.values().iterator();
		while (portIter.hasNext()) {
			Port port = (Port) portIter.next();
			Binding binding = port.getBinding();//port�ж�Ӧ��binding�к���operation�Ķ���Ͳ���
			List operations=buildOperations(binding);//��������
			Iterator operIter = operations.iterator();
			while (operIter.hasNext()) {
				OperationInfo operation = (OperationInfo) operIter.next();
				Vector addrElems = findExtensibilityElement(port
						.getExtensibilityElements(), "address");
				ExtensibilityElement element = (ExtensibilityElement) addrElems
						.elementAt(0);
				if (element != null && element instanceof SOAPAddress) {
					SOAPAddress soapAddr = (SOAPAddress) element;
					operation.setTargetURL(soapAddr.getLocationURI());
				}
				component.addOperation(operation);//����������component��
			}
		}
		return component;//��Ҫ����name��operation��namespace�ȵ�
	}

	private List buildOperations(Binding binding) {
       System.out.println("����buildOperations(Binding binding)����,�����������еĲ�������List buildOperations");
		List operationInfos = new ArrayList();

		List operations=binding.getBindingOperations();

		if (operations!= null && !operations.isEmpty()) {
             
			Vector soapBindingElems = findExtensibilityElement(binding
					.getExtensibilityElements(), "binding");
			String style = "document"; // default
			
			ExtensibilityElement soapBindingElem = (ExtensibilityElement) soapBindingElems
					.elementAt(0);
			if (soapBindingElem != null
					&& soapBindingElem instanceof SOAPBinding) {
				//SOAPBinding�����ľ���<wsdl:binding>�µ���Ԫ��:<wsdlsoap:bindingԪ��>
				SOAPBinding soapBinding = (SOAPBinding) soapBindingElem;
				style = soapBinding.getStyle();
			}

			Iterator opIter = operations.iterator();

			while (opIter.hasNext()) {
				//BindingOperation�����ľ���<wsdl:binding>�µ���Ԫ��:<wsdlsoap:operationԪ��>
				BindingOperation oper = (BindingOperation) opIter.next();
				Vector operElems = findExtensibilityElement(oper
						.getExtensibilityElements(), "operation");
				ExtensibilityElement operElem = (ExtensibilityElement) operElems
						.elementAt(0);
				//SOAPOperation�����ľ���<wsdlsoap:operation>�µ���Ԫ��:<wsdlsoap:operation/>
				if (operElem != null && operElem instanceof SOAPOperation) {

					OperationInfo operationInfo = new OperationInfo(style);

					buildOperation(operationInfo, oper);

					operationInfos.add(operationInfo);
				}
			}
		}

		return operationInfos;
	}
//ͨ��operation���������������б�ȵ�
	private OperationInfo buildOperation(OperationInfo operationInfo,
			BindingOperation bindingOper) {
        System.out.println("��һ��BindingOperation����(<wsdl:operation>)����OperationInfo����");
		Operation oper = bindingOper.getOperation();
		operationInfo.setTargetMethodName(oper.getName());
		Vector operElems = findExtensibilityElement(bindingOper
				.getExtensibilityElements(), "operation");
		ExtensibilityElement operElem = (ExtensibilityElement) operElems
				.elementAt(0);
		if (operElem != null && operElem instanceof SOAPOperation) {
			SOAPOperation soapOperation = (SOAPOperation) operElem;
			operationInfo.setSoapActionURI(soapOperation.getSoapActionURI());
		}
		BindingInput bindingInput = bindingOper.getBindingInput();
		BindingOutput bindingOutput = bindingOper.getBindingOutput();
		Vector bodyElems = findExtensibilityElement(bindingInput
				.getExtensibilityElements(), "body");
		ExtensibilityElement bodyElem = (ExtensibilityElement) bodyElems
				.elementAt(0);

		if (bodyElem != null && bodyElem instanceof SOAPBody) {
			SOAPBody soapBody = (SOAPBody) bodyElem;

			List styles = soapBody.getEncodingStyles();
			String encodingStyle = null;

			if (styles != null) {

				encodingStyle = styles.get(0).toString();
			}

			if (encodingStyle == null) {

				encodingStyle = DEFAULT_SOAP_ENCODING_STYLE;
			}

			operationInfo.setEncodingStyle(encodingStyle.toString());

			operationInfo.setTargetObjectURI(soapBody.getNamespaceURI());
		}

		Input inDef = oper.getInput();
        System.out.println("��ʼת�Ƶ���<wsdl:portTyp>����µ�<wsdl:input>");
		if (inDef != null) {
			Message inMsg = inDef.getMessage();
			if (inMsg != null) {
				operationInfo.setInputMessageName(inMsg.getQName().getLocalPart());
                //������Ϣ�Ĳ�������
				getParameterFromMessage(operationInfo, inMsg, 1);
				System.out.println("***����:"+operationInfo.getTargetMethodName()+"��������������Ѿ��������***");
				  System.out.println("");
				operationInfo.setInmessage(inMsg);
			}
		}

		Output outDef = oper.getOutput();

		if (outDef != null) {

			Message outMsg = outDef.getMessage();

			if (outMsg != null) {
				operationInfo.setOutputMessageName(outMsg.getQName()
						.getLocalPart());
                //�����Ϣ�Ĳ�������
				getParameterFromMessage(operationInfo, outMsg, 2);
				System.out.println("***����:"+operationInfo.getTargetMethodName()+"��������������Ѿ��������***");
				  System.out.println("");
				operationInfo.setOutmessage(outMsg);
			}
		}
		

		return operationInfo;
	}
//����������Ϣ
	private void getParameterFromMessage(OperationInfo operationInfo,
			Message msg, int manner) {
        String tip="";
		System.out.println("*******************");
        if(manner==1){
        	tip="����";
        }
        else{
        	
        	tip="���";
        }
        System.out.println("");
		System.out.println("***��ʼ����"+operationInfo.getTargetMethodName()+"������������Ϣ"+tip+"����***");
       
		List msgParts = msg.getOrderedParts(null);
		Schema wsdlType = null;
		Iterator iter = msgParts.iterator();
		while (iter.hasNext()) {
			Part part = (Part) iter.next();
			String targetnamespace = "";
			XMLType xmlType=getXMLType(part, wsdlType, operationInfo);
			if (xmlType!=null&&xmlType.isComplexType()) {
				buildComplexParameter((ComplexType) xmlType, operationInfo,
						manner);
			}
			else {
				System.out.print("part�����õ�xmlԪ���Ǽ�����");
				String partName = part.getName();
				ParameterInfo parameter = new ParameterInfo();
				parameter.setName(partName);
				parameter.setKind(part.getTypeName().getLocalPart());
				if (manner == 1) {
					//1��ʾ�������ǲ������������
					operationInfo.addInparameter(parameter);
				} else {
					operationInfo.addOutparameter(parameter);
				}
			}
			operationInfo.setWsdltype(wsdlTypes);

		}

	}
//�������������е�����
	private void buildComplexParameter(ComplexType type,
			OperationInfo operationInfo, int manner) {
		//XML Schema �淶�����˴����������
		//��schema��complexType��simpleType��group��annotation��include��import��element �� attribute �ȵȡ�
		//particleEnum����ComplexType�µ���Ԫ������,����������Ĳ���������
		Enumeration particleEnum=type.enumerate();
		//group����Ԫ��(�����Ǹ�������)����
		Group group = null;
		if(!particleEnum.hasMoreElements()){
			System.out.println(operationInfo+"��������Ҫ�������");
		}
		while (particleEnum.hasMoreElements()) {
			System.out.println("����<complexType>�����µ���Ԫ��");
			Particle particle = (Particle) particleEnum.nextElement();
			if (particle instanceof Group) {
				System.out.println("��Ԫ��Ҳ��һ��Ԫ�ؼ���(<xsd:element...>)");
				group = (Group) particle;
				break;
			}
		}
		if (group != null) {
			
			Enumeration groupEnum = group.enumerate();
			while (groupEnum.hasMoreElements()) {
					//�����˸����������͵�ÿһ��Element���
				Structure item = (Structure) groupEnum.nextElement();
				if (item.getStructureType()==Structure.ELEMENT) {	
					ElementDecl elementDecl = (ElementDecl) item;
					 System.out.println("�����������͵���Ԫ����һ��Element:"+elementDecl.getReferenceId());
					XMLType xmlType = elementDecl.getType();
					
					if (xmlType != null && xmlType.isComplexType()) {
						System.out.println("***"+elementDecl.getReferenceId()+"Ԫ����һ����������,����ݹ����****");
						buildComplexParameter((ComplexType)xmlType,operationInfo, manner);
						
					} else {
						
						System.out.println("���ڿ�ʼ�������������");
						ParameterInfo parameter = new ParameterInfo();
						parameter.setName(elementDecl.getName());
						System.out.println("������Ϊ:" + elementDecl.getName());
						parameter.setKind(elementDecl.getType().getName());
						System.out.println("��������Ϊ:" + elementDecl.getType().getName());
						if (manner == 1) {
							operationInfo.addInparameter(parameter);
						} else {
							operationInfo.addOutparameter(parameter);
						}
					}

				}
			}
		
		}
		
	}

	protected XMLType getXMLType(Part part, Schema wsdlType,
			OperationInfo operationInfo) {
		if (wsdlTypes == null) {
			System.out.println("null is here in the 1 ");
			return null;
		}

		XMLType xmlType = null;

		if (part.getElementName()!= null) {
			String elemName=part.getElementName().getLocalPart();
			System.out.println("part���õ�������Ϊ:"+elemName);
			ElementDecl elemDecl = null;
			for (int i = 0; i < wsdlTypes.size(); i++) {
				wsdlType = (Schema) (wsdlTypes.elementAt(i));
				String targetnamespace=wsdlType.getTargetNamespace();
				operationInfo.setNamespaceURI(targetnamespace);
				elemDecl=wsdlType.getElementDecl(elemName);
				if (elemDecl!=null) {
					break;
				}

			}
			if (elemDecl!=null) {
				 xmlType = elemDecl.getType();
                 System.out.println(xmlType);				
			}
		
		}

		return xmlType;
	}

	private static Vector findExtensibilityElement(List extensibilityElements,
			String elementType) {
		int i = 0;
		Vector elements = new Vector();
		if (extensibilityElements!= null) {
			Iterator iter = extensibilityElements.iterator();
			while (iter.hasNext()) {
				ExtensibilityElement elment = (ExtensibilityElement)iter.next();
				if (elment.getElementType().getLocalPart().equalsIgnoreCase(elementType)) {
					elements.add(elment);
				}
			}
		}
		return elements;
	}
}
