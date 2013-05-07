package hello.actions;

import hello.util.OWLParser;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.xerces.parsers.DOMParser;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
public class OpenAction implements IWorkbenchWindowActionDelegate {
	private IWorkbenchWindow win = null;
	@Override
	public void run(IAction arg0) {
		// TODO Auto-generated method stub
		
		FileDialog dialog = new FileDialog (win.getShell(),SWT.OPEN); 
		dialog.setText("Ñ¡ÔñOWLÎÄ¼þ");
		dialog.setFilterExtensions(new String[] {"*.owl"});  
		String filePath = dialog.open();  
		FilePath.filePath = filePath;
		if(filePath == null)
			return;
		OWLParser owl = new OWLParser(filePath);
		owl.parse();
		owl.show(win.getActivePage());
//		File file = new File(filePath);  
//		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();  
//		Document document = null;
//		try {   
//			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();    
//			document = documentBuilder.parse(file);
//		}
//		catch(Exception e)
//		{
//			e.printStackTrace();
//		}			
//		OWLParser owl = new OWLParser(filePath);
//		
//		List<String> serNames = owl.getServiceName();
//		List<String> proNames = owl.getProfileName();
//		List<String> procNames = owl.getProcessName();
//		List<String> grouNames = owl.getGroundingName();
//		List<String> inputValues = owl.getInput();
//		List<String> outputValues = owl.getOutput();
//		List<String> atoProcGouValues = owl.getAtomicProcessGrounding();
		
//		IWorkbenchPage wbp = win.getActivePage();
//		IViewPart view2 = null;
//		IViewPart view3 = null;
//		IViewPart view4 = null;
//		IViewPart view5 = null;
//		IViewPart view6 = null;
//		try {
//			view2 = wbp.showView("Hello.view2");
//			view3 = wbp.showView("Hello.view3");
//			view4 = wbp.showView("ProfilePropertyView");
//			view5 = wbp.showView("ProcessPropertyView");
//			view6 = wbp.showView("GroundingPropertyView");
//		
//		} catch (PartInitException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		//service list
//		TV tv = (TV)view2;
//		org.eclipse.swt.widgets.List serList = tv.list;
//		for(String serName : serNames)
//		{
//			serList.add(serName);
//		}
//		org.eclipse.swt.widgets.List proList = tv.list_1;
//		for(String proName: proNames)
//		{
//			proList.add(proName);
//		}
//		org.eclipse.swt.widgets.List procList = tv.list_2;
//		for(String procName: procNames)
//		{
//			procList.add(procName);
//		}
//		org.eclipse.swt.widgets.List grouList = tv.list_3;
//		for(String grouName: grouNames)
//		{
//			grouList.add(grouName);
//		}
//		//service
//		ServicePropertyView serProV = (ServicePropertyView)view3;
//		org.eclipse.swt.widgets.Text serProcText = serProV.text_des;
//		for(String procName : procNames)
//		{
//			serProcText.setText(procName);
//		}
//		org.eclipse.swt.widgets.Text serProText = serProV.text_pre;
//		for(String proName : proNames)
//		{
//			serProText.setText(proName);
//		}
//		org.eclipse.swt.widgets.Text serSupText = serProV.text_sup;
//		for(String grouName : grouNames)
//		{
//			serSupText.setText(grouName);
//		}
//		//profile
//		ProfilePropertyView profProV = (ProfilePropertyView)view4;
//		org.eclipse.swt.widgets.List inputList = profProV.list;
//		for(String inputValue : inputValues)
//		{
//			inputList.add(inputValue);
//		}
//		org.eclipse.swt.widgets.List outputList = profProV.list_1;
//		for(String outputValue : outputValues)
//		{
//			outputList.add(outputValue);
//		}
//		org.eclipse.swt.widgets.List ParaList = profProV.list_2;
//		for(String inputValue : inputValues)
//		{
//			ParaList.add(inputValue);
//		}
//		for(String outputValue : outputValues)
//		{
//			ParaList.add(outputValue);
//		}
//		org.eclipse.swt.widgets.Text PreText = profProV.text_preBy;
//		for(String serName : serNames)
//		{
//			PreText.setText(serName);
//		}
//		//process
//		ProcessPropertyView processProV = (ProcessPropertyView)view5;
//		org.eclipse.swt.widgets.List input2List = processProV.list;
//		for(String inputValue : inputValues)
//		{
//			input2List.add(inputValue);
//		}
//		org.eclipse.swt.widgets.List Para2List = processProV.list_1;
//		for(String inputValue : inputValues)
//		{
//			Para2List.add(inputValue);
//		}
//		for(String outputValue : outputValues)
//		{
//			Para2List.add(outputValue);
//		}
//		org.eclipse.swt.widgets.Text desText = processProV.text_1;
//		for(String serName : serNames)
//		{
//			desText.setText(serName);
//		}
//		//grounding GroundingPropertyView
//		GroundingPropertyView grouProV = (GroundingPropertyView)view6;
//		org.eclipse.swt.widgets.Text supText = grouProV.text_1;
//		for(String serName : serNames)
//		{
//			supText.setText(serName);
//		}
//		org.eclipse.swt.widgets.List atoProGouList = grouProV.list;
//		for(String atoProcGouValue : atoProcGouValues)
//		{
//			atoProGouList.add(atoProcGouValue);
//		}
	}
//	private List<String> getServiceName(Document doc)
//	{
//		List<String> serNames = new LinkedList<String>();
//		
//		NodeList serList = doc.getElementsByTagName("j.1:Service");
//		
//		for(int i = 0;i<serList.getLength();i++)
//		{
//			Node node = serList.item(i);
//			NamedNodeMap map = node.getAttributes();
//			String name = map.getNamedItem("rdf:ID").getNodeValue();
//			serNames.add(name);
//		}
//		for(String name : serNames)
//		{
//			ElementValue.vals.put(name, name);
//		}
//		return  serNames;
//	}
//	private List<String> getProfileName(Document doc)
//	{
//		List<String> proNames = new LinkedList<String>();
//		
//		NodeList proList = doc.getElementsByTagName("j.0:Profile");
//		
//		for(int i = 0;i<proList.getLength();i++)
//		{
//			Node node = proList.item(i);
//			NamedNodeMap map = node.getAttributes();
//			String name = map.getNamedItem("rdf:ID").getNodeValue();
//			proNames.add(name);
//		}
//		for(String name : proNames)
//		{
//			ElementValue.profileVals.put(name, name);
//		}
//		return  proNames;
//	}
//	private List<String> getProcessName(Document doc)
//	{
//		List<String> procNames = new LinkedList<String>();
//		
//		NodeList procList = doc.getElementsByTagName("j.2:AtomicProcess");
//		
//		for(int i = 0;i<procList.getLength();i++)
//		{
//			Node node = procList.item(i);
//			NamedNodeMap map = node.getAttributes();
//			String name = map.getNamedItem("rdf:ID").getNodeValue();
//			procNames.add(name);
//		}
//		return  procNames;
//	}
//	private List<String> getGroundingName(Document doc)
//	{
//		List<String> grouNames = new LinkedList<String>();
//		
//		NodeList grouList = doc.getElementsByTagName("j.3:WsdlGrounding");
//		
//		for(int i = 0;i<grouList.getLength();i++)
//		{
//			Node node =grouList.item(i);
//			NamedNodeMap map = node.getAttributes();
//			String name = map.getNamedItem("rdf:ID").getNodeValue();
//			grouNames.add(name);
//		}
//		return  grouNames;
//	}
//	private List<String> getInput(Document doc)
//	{
//		List<String> inputValues = new LinkedList<String>();
//		
//		NodeList inputList = doc.getElementsByTagName("j.2:Input");
//		
//		for(int i = 0;i<inputList.getLength();i++)
//		{
//			Node node =inputList.item(i);
//			NamedNodeMap map = node.getAttributes();
//			String name = map.getNamedItem("rdf:ID").getNodeValue();
//			inputValues.add(name);
//			System.out.println(name);
//		}
//		return  inputValues;
//		
//	}
//	private List<String> getOutput(Document doc)
//	{
//		List<String> outputValues = new LinkedList<String>();
//		
//		NodeList outputList = doc.getElementsByTagName("j.2:Output");
//		
//		for(int i = 0;i<outputList.getLength();i++)
//		{
//			Node node =outputList.item(i);
//			NamedNodeMap map = node.getAttributes();
//			String name = map.getNamedItem("rdf:ID").getNodeValue();
//			outputValues.add(name);
//			System.out.println(name);
//		}
//		return  outputValues;
//		
//	}
//	private List<String> getAtomicProcessGrounding(Document doc)
//	{
//		List<String> atoProcGouValues = new LinkedList<String>();
//		
//		NodeList atoProcGouList = doc.getElementsByTagName("j.3:WsdlAtomicProcessGrounding");
//		
//		for(int i = 0;i<atoProcGouList.getLength();i++)
//		{
//			Node node =atoProcGouList.item(i);
//			NamedNodeMap map = node.getAttributes();
//			String name = map.getNamedItem("rdf:ID").getNodeValue();
//			atoProcGouValues.add(name);
//			System.out.println(name);
//		}
//		return  atoProcGouValues;
//		
//	}
	@Override
	public void selectionChanged(IAction arg0, ISelection arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(IWorkbenchWindow arg0) {
		// TODO Auto-generated method stub
		this.win = arg0;
	}

}
