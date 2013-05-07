package hello.util;

import hello.actions.ElementValue;
import hello.actions.GroundingPropertyView;
import hello.actions.ProcessPropertyView;
import hello.actions.ProfilePropertyView;
import hello.actions.ServicePropertyView;
import hello.actions.TV;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class OWLParser {
	private String filePath = null;
	private Document document = null;
	private List<String> serNames = null;
	private List<String> proNames = null;
	private List<String> procNames = null;
	private List<String> grouNames = null;
	private List<String> inputValues = null;
	private List<String> outputValues =null;
	private List<String> atoProcGouValues = null;
	public OWLParser(){
		init();
	}
	public OWLParser(String filePath)
	{
		this.filePath = filePath;
		init();
	}
	public void setFilePath(String filePath)
	{
		this.filePath = filePath;
	}
	
	private void init()
	{
		if(filePath == null)
			return;
		File file = new File(filePath);  
		if(!file.exists())
		{
			System.out.println("file not exist!");
			return;
		}
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();  
		try {   
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();    
			document = documentBuilder.parse(file);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		if(document == null)
			return;
	}
	public void parse()
	{
		serNames = getServiceName();
		proNames = getProfileName();
		procNames = getProcessName();
		grouNames =getGroundingName();
		inputValues = getInput();
		outputValues = getOutput();
		atoProcGouValues = getAtomicProcessGrounding();
	}
	
	public void show(IWorkbenchPage wbp)
	{
		
		IViewPart view2 = null;
		IViewPart view3 = null;
		IViewPart view4 = null;
		IViewPart view5 = null;
		IViewPart view6 = null;
		try {
			view2 = wbp.showView("Hello.view2");
			view3 = wbp.showView("Hello.view3");
			view4 = wbp.showView("ProfilePropertyView");
			view5 = wbp.showView("ProcessPropertyView");
			view6 = wbp.showView("GroundingPropertyView");
		
		} catch (PartInitException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//service list
		TV tv = (TV)view2;
		org.eclipse.swt.widgets.List serList = tv.list;
		for(String serName : serNames)
		{
			serList.add(serName);
		}
		org.eclipse.swt.widgets.List proList = tv.list_1;
		for(String proName: proNames)
		{
			proList.add(proName);
		}
		org.eclipse.swt.widgets.List procList = tv.list_2;
		for(String procName: procNames)
		{
			procList.add(procName);
		}
		org.eclipse.swt.widgets.List grouList = tv.list_3;
		for(String grouName: grouNames)
		{
			grouList.add(grouName);
		}
		//service
		ServicePropertyView serProV = (ServicePropertyView)view3;
		org.eclipse.swt.widgets.Text serProcText = serProV.text_des;
		for(String procName : procNames)
		{
			serProcText.setText(procName);
		}
		org.eclipse.swt.widgets.Text serProText = serProV.text_pre;
		for(String proName : proNames)
		{
			serProText.setText(proName);
		}
		org.eclipse.swt.widgets.Text serSupText = serProV.text_sup;
		for(String grouName : grouNames)
		{
			serSupText.setText(grouName);
		}
		//profile
		ProfilePropertyView profProV = (ProfilePropertyView)view4;
		org.eclipse.swt.widgets.List inputList = profProV.list;
		for(String inputValue : inputValues)
		{
			inputList.add(inputValue);
		}
		org.eclipse.swt.widgets.List outputList = profProV.list_1;
		for(String outputValue : outputValues)
		{
			outputList.add(outputValue);
		}
		org.eclipse.swt.widgets.List ParaList = profProV.list_2;
		for(String inputValue : inputValues)
		{
			ParaList.add(inputValue);
		}
		for(String outputValue : outputValues)
		{
			ParaList.add(outputValue);
		}
		org.eclipse.swt.widgets.Text PreText = profProV.text_preBy;
		for(String serName : serNames)
		{
			PreText.setText(serName);
		}
		//process
		ProcessPropertyView processProV = (ProcessPropertyView)view5;
		org.eclipse.swt.widgets.List input2List = processProV.list;
		for(String inputValue : inputValues)
		{
			input2List.add(inputValue);
		}
		org.eclipse.swt.widgets.List Para2List = processProV.list_1;
		for(String inputValue : inputValues)
		{
			Para2List.add(inputValue);
		}
		for(String outputValue : outputValues)
		{
			Para2List.add(outputValue);
		}
		org.eclipse.swt.widgets.Text desText = processProV.text_1;
		for(String serName : serNames)
		{
			desText.setText(serName);
		}
		//grounding GroundingPropertyView
		GroundingPropertyView grouProV = (GroundingPropertyView)view6;
		org.eclipse.swt.widgets.Text supText = grouProV.text_1;
		for(String serName : serNames)
		{
			supText.setText(serName);
		}
		org.eclipse.swt.widgets.List atoProGouList = grouProV.list;
		for(String atoProcGouValue : atoProcGouValues)
		{
			atoProGouList.add(atoProcGouValue);
		}
	}
	private List<String> getServiceName()
	{
		List<String> serNames = new LinkedList<String>();
		
		NodeList serList = this.document.getElementsByTagName("j.1:Service");
		
		for(int i = 0;i<serList.getLength();i++)
		{
			Node node = serList.item(i);
			NamedNodeMap map = node.getAttributes();
			String name = map.getNamedItem("rdf:ID").getNodeValue();
			serNames.add(name);
		}
		for(String name : serNames)
		{
			ElementValue.vals.put(name, name);
		}
		return  serNames;
	}
	private List<String> getProfileName()
	{
		List<String> proNames = new LinkedList<String>();
		
		NodeList proList = this.document.getElementsByTagName("j.0:Profile");
		
		for(int i = 0;i<proList.getLength();i++)
		{
			Node node = proList.item(i);
			NamedNodeMap map = node.getAttributes();
			String name = map.getNamedItem("rdf:ID").getNodeValue();
			proNames.add(name);
		}
		for(String name : proNames)
		{
			ElementValue.profileVals.put(name, name);
		}
		return  proNames;
	}
	private List<String> getProcessName()
	{
		List<String> procNames = new LinkedList<String>();
		
		NodeList procList = this.document.getElementsByTagName("j.2:AtomicProcess");
		
		for(int i = 0;i<procList.getLength();i++)
		{
			Node node = procList.item(i);
			NamedNodeMap map = node.getAttributes();
			String name = map.getNamedItem("rdf:ID").getNodeValue();
			procNames.add(name);
		}
		return  procNames;
	}
	private List<String> getGroundingName()
	{
		List<String> grouNames = new LinkedList<String>();
		
		NodeList grouList = this.document.getElementsByTagName("j.3:WsdlGrounding");
		
		for(int i = 0;i<grouList.getLength();i++)
		{
			Node node =grouList.item(i);
			NamedNodeMap map = node.getAttributes();
			String name = map.getNamedItem("rdf:ID").getNodeValue();
			grouNames.add(name);
		}
		return  grouNames;
	}
	private List<String> getInput()
	{
		List<String> inputValues = new LinkedList<String>();
		
		NodeList inputList = this.document.getElementsByTagName("j.2:Input");
		
		for(int i = 0;i<inputList.getLength();i++)
		{
			Node node =inputList.item(i);
			NamedNodeMap map = node.getAttributes();
			String name = map.getNamedItem("rdf:ID").getNodeValue();
			inputValues.add(name);
			System.out.println(name);
		}
		return  inputValues;
		
	}
	private List<String> getOutput()
	{
		List<String> outputValues = new LinkedList<String>();
		
		NodeList outputList = this.document.getElementsByTagName("j.2:Output");
		
		for(int i = 0;i<outputList.getLength();i++)
		{
			Node node =outputList.item(i);
			NamedNodeMap map = node.getAttributes();
			String name = map.getNamedItem("rdf:ID").getNodeValue();
			outputValues.add(name);
			System.out.println(name);
		}
		return  outputValues;
		
	}
	private List<String> getAtomicProcessGrounding()
	{
		List<String> atoProcGouValues = new LinkedList<String>();
		
		NodeList atoProcGouList = this.document.getElementsByTagName("j.3:WsdlAtomicProcessGrounding");
		
		for(int i = 0;i<atoProcGouList.getLength();i++)
		{
			Node node =atoProcGouList.item(i);
			NamedNodeMap map = node.getAttributes();
			String name = map.getNamedItem("rdf:ID").getNodeValue();
			atoProcGouValues.add(name);
			System.out.println(name);
		}
		return  atoProcGouValues;
		
	}
	 
}
