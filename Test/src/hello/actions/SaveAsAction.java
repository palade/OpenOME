package hello.actions;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.List;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.PartInitException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class SaveAsAction implements IWorkbenchWindowActionDelegate {
	private IWorkbenchWindow win = null;
	@Override
	public void run(IAction action) {
		// TODO Auto-generated method stub
		FileDialog d = new FileDialog(win.getShell(),SWT.SAVE);
		d.setText("选择OWL文件存放位置");
		d.setFilterExtensions(new String[]{"*.owl"});
		String path = d.open();
		System.out.println(path);
		if(path == null)
			return;
		
		IWorkbenchPage wbp = win.getActivePage();
		IViewPart vp = null;
		IViewPart vp2 = null;
		try {
			 vp = wbp.showView("Hello.view2");
			 vp2 = wbp.showView("ProfilePropertyView");
		} catch (PartInitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		if(vp == null)
			return;
		TV tv = (TV)vp;
		ProfilePropertyView ppv = (ProfilePropertyView)vp2;
		
		List list = tv.list;
		if(list.getItemCount() == 0 || tv.list_1.getItemCount() == 0)
			return;
		
		String serviceName = list.getItem(0);
		String profileName = tv.list_1.getItem(0);
		String processName = tv.list_2.getItem(0);
		String groundingName = tv.list_3.getItem(0);
		
		
	
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();    
		DocumentBuilder builder = null;
		try {
			builder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    
		Document document = builder.newDocument(); 
		Element rdf = document.createElement("rdf:RDF");
		
		rdf.setAttribute("xmlns","http://www.owl-ontologies.com/Ontology1367940844.owl#");
		rdf.setAttribute("xmlns:protege","http://protege.stanford.edu/plugins/owl/protege#");
		rdf.setAttribute("xsp","http://www.owl-ontologies.com/2005/08/07/xsp.owl#");
		rdf.setAttribute("xmlns:service","http://www.daml.org/services/owl-s/1.1/Service.owl#");	
		rdf.setAttribute("xmlns:process", "http://www.daml.org/services/owl-s/1.1/Process.owl#");
		rdf.setAttribute("xmlns:swrlb","http://www.w3.org/2003/11/swrlb#");
		rdf.setAttribute("xmlns:list","http://www.daml.org/services/owl-s/1.1/generic/ObjectList.owl#");
		rdf.setAttribute("xmlns:swrl","http://www.w3.org/2003/11/swrl#");
		rdf.setAttribute("xmlns:rdfs","http://www.w3.org/2000/01/rdf-schema#");
		rdf.setAttribute("xmlns:owl","http://www.w3.org/2002/07/owl#");
		rdf.setAttribute("xmlns:expr","http://www.daml.org/services/owl-s/1.1/generic/Expression.owl#");
		rdf.setAttribute("xmlns:service","http://www.daml.org/services/owl-s/1.1/Service.owl#");
		rdf.setAttribute("xmlns:grounding","http://www.daml.org/services/owl-s/1.1/Grounding.owl#");
		rdf.setAttribute("xmlns:rdf","http://www.w3.org/1999/02/22-rdf-syntax-ns#");
		rdf.setAttribute("xmlns:xsd","http://www.w3.org/2001/XMLSchema#");
		rdf.setAttribute("xmlns:daml","http://www.daml.org/2001/03/daml+oil#");
		rdf.setAttribute("xmlns:dc","http://purl.org/dc/elements/1.1/");
		rdf.setAttribute("xmlns:profile","http://www.daml.org/services/owl-s/1.1/Profile.owl#");
		rdf.setAttribute("xml:base","http://www.example.org/owls/GetWeather.owl#");
		document.appendChild(rdf);
		
		Element Ontology = document.createElement("owl:Ontology");
		Ontology.setAttribute("rdf:about", "");
		rdf.appendChild(Ontology);		
		Element import1 = document.createElement("owl:imports");
		import1.setAttribute("rdf:resource", "http://www.daml.org/services/owl-s/1.2/Profile.owl");
		Ontology.appendChild(import1);
		Element import2 = document.createElement("owl:imports");
		import2.setAttribute("rdf:resource", "http://www.daml.org/services/owl-s/1.2/Grounding.owl");
		Ontology.appendChild(import2);
		Element import3 = document.createElement("owl:imports");
		import3.setAttribute("rdf:resource", "http://www.w3.org/2003/11/swrl");
		Ontology.appendChild(import3);
		Element import4 = document.createElement("owl:imports");
		import4.setAttribute("rdf:resource", "http://www.w3.org/2003/11/swrlb");
		Ontology.appendChild(import4);
		
		Element profile = document.createElement("profile:Profile");
		profile.setAttribute("rdf:ID", profileName);
		rdf.appendChild(profile);
		
		Element presentedBy = document.createElement("service:presentedBy");
		profile.appendChild(presentedBy);			
		
		Element service = document.createElement("service:Service");
		service.setAttribute("rdf:ID", serviceName);
		presentedBy.appendChild(service);
		
		Element presents = document.createElement("service:presents");
		presents.setAttribute("rdf:resource", "#"+profileName);
		service.appendChild(presents);
		
		Element supports = document.createElement("service:supports");
		service.appendChild(supports);		
		
		Element grounding = document.createElement("grounding:WsdlGrounding");
		supports.appendChild(grounding);
		grounding.setAttribute("rdf:ID", groundingName);
		Element hasAtomicProcessGrounding = document.createElement("grounding:hasAtomicProcessGrounding");
		grounding.appendChild(hasAtomicProcessGrounding);
		Element WsdlAtomicProcessGrounding = document.createElement("grounding:WsdlAtomicProcessGrounding");
		hasAtomicProcessGrounding.appendChild(WsdlAtomicProcessGrounding);
		WsdlAtomicProcessGrounding.setAttribute("rdf:ID","WsdlAtomicProcessGrounding_9");
				
		Element describedBy = document.createElement("service:describedBy");
		service.appendChild(describedBy);		
		Element process = document.createElement("process:AtomicProcess");
		describedBy.appendChild(process);
		process.setAttribute("rdf:ID", processName);
		Element describes = document.createElement("service:describes");
		process.appendChild(describes);
		describes.setAttribute("rdf:resource", "#"+serviceName);
		for(int i = 0;i<ppv.list.getItemCount();i++)
		{
			String in = ppv.list.getItem(i);
			Element hasInput = document.createElement("profile:hasInput");
			process.appendChild(hasInput);		
			Element Input = document.createElement("process:Input");
			hasInput.appendChild(Input);
			Input.setAttribute("rdf:ID", in);
		}
		for(int i = 0;i<ppv.list_1.getItemCount();i++)
		{
			String out = ppv.list_1.getItem(i);
			Element hasOutput = document.createElement("profile:hasOutput");
			process.appendChild(hasOutput);		
			Element Output = document.createElement("process:Output");
			hasOutput.appendChild(Output);
			Output.setAttribute("rdf:ID", out);	
		}	
		
		for(int i = 0;i<ppv.list_1.getItemCount();i++)
		{
			String out = ppv.list_1.getItem(i);
			Element hasOutput2 = document.createElement("profile:hasOutput");
			profile.appendChild(hasOutput2);
			hasOutput2.setAttribute("rdf:resource", "#"+out);	
		}
		
		for(int i = 0;i<ppv.list.getItemCount();i++)
		{
			String in = ppv.list.getItem(i);
			Element hasInput2 = document.createElement("profile:hasInput");
			profile.appendChild(hasInput2);
			hasInput2.setAttribute("rdf:resource", "#"+in);
		}
//		Element profile2 = document.createElement("profile:Profile");
//		profile2.setAttribute("rdf:about", "#"+profileName);
//		rdf.appendChild(profile2);
//
//		Element servName = document.createElement("profile:serviceName");
//		servName.appendChild(document.createTextNode(serviceName.substring(0,serviceName.lastIndexOf("Service"))));
//		
//		profile2.appendChild(servName);
		
		
//		Element parameterType = document.createElement("process:parameterType");
//		parameterType.appendChild(document.createTextNode("http://www.w3.org/2001/XMLSchema#string"));
//		Input.appendChild(parameterType);
//		parameterType.setAttribute("rdf:datatype", "http://www.w3.org/2001/XMLSchema#anyURI");
//		Element label = document.createElement("rdfs:label");
//		label.appendChild(document.createTextNode("input1"));
//		Input.appendChild(label);
//		
		
//		Element parameterType2 = document.createElement("process:parameterType");
//		parameterType2.appendChild(document.createTextNode("http://www.w3.org/2001/XMLSchema#string"));		
//		Output.appendChild(parameterType2);
//		parameterType2.setAttribute("rdf:datatype", "http://www.w3.org/2001/XMLSchema#anyURI");
//		Element label2 = document.createElement("rdfs:label");
//		label.appendChild(document.createTextNode("Output1"));
//		Output.appendChild(label2);
		
		
		
		
		 TransformerFactory tf = TransformerFactory.newInstance();          
		 String hpxml="";         
		 try {              
			 Transformer transformer = tf.newTransformer();              
			 DOMSource source = new DOMSource(document);              
			 StringWriter writerStr = new StringWriter();            
			 Result resultStr = new StreamResult(writerStr);             
			 transformer.setOutputProperty(OutputKeys.ENCODING, "gb2312");              
			 transformer.transform(source, resultStr);             
			 hpxml = writerStr.getBuffer().toString();             
			 transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			 //此处设置缩进             
			 PrintWriter pw = new PrintWriter(new FileOutputStream(path));              
			 StreamResult result = new StreamResult(pw);              
			 transformer.transform(source, result);              
			 System.out.println("OWL创建成功");         
			 } 
		 	catch (Exception e) 
		 	{           
		 		e.printStackTrace();
		 		System.out.println("创建OWL失败");         
		 	}
		 	
		 	ElementValue.owlPath.put(serviceName.substring(0,serviceName.lastIndexOf("Service")), path);
		 	ElementValue.updateOWLPathFile();
		 }

	
	private boolean updateServiceName()
	{
		if(FilePath.filePath == null)
			return false;
		File file = new File(FilePath.filePath);  
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();  
		Document document = null;
		try {   
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();    
			document = documentBuilder.parse(file);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}			
		Map<String,String> tempMap = new HashMap<String,String>();//保存更新后的数据
		Set<Entry<String,String>> set = ElementValue.vals.entrySet();//entry键值对保存更新前的数据，key是原始的，value是修改的
		for(Entry<String,String> val : set)
		{
			tempMap.put(val.getValue(), val.getValue());//使用临时的temp保存新值
			
			if(val.getKey().equalsIgnoreCase(val.getValue()))
				continue;
			System.out.println("--------------------------"+val.getKey()+":"+val.getValue());
			NodeList serList = document.getElementsByTagName("j.1:Service");
			for(int i = 0;i<serList.getLength();i++)//将文档更新
			{
				Node node = serList.item(i);
				NamedNodeMap map = node.getAttributes();
				String name = map.getNamedItem("rdf:ID").getNodeValue();
				if(name!= null && name.equalsIgnoreCase(val.getKey()))
				{
					node.getAttributes().getNamedItem("rdf:ID").setNodeValue(val.getValue());
				}
			}
		}
		try {
            Transformer t = TransformerFactory.newInstance().newTransformer();
            t.setOutputProperty("indent", "yes");
            t.transform(new DOMSource(document), new StreamResult(new FileOutputStream(file)));
        } catch (TransformerException e) {
            e.printStackTrace();
            return false;
        } catch (FileNotFoundException e) {
            e.printStackTrace();  
            return false;
        }
		//将内存更新
		ElementValue.vals.clear();//将内存中的原来的旧值先清空
		ElementValue.vals.putAll(tempMap);//将新值放入
		set = ElementValue.vals.entrySet();
		for(Entry<String,String> val : set)
		{
			System.out.println("--------------------------"+val.getKey()+":"+val.getValue());
		}
		return true;
	}
	
	private boolean updateProfileName()
	{
		if(FilePath.filePath == null)
			return false;
		File file = new File(FilePath.filePath);  
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();  
		Document document = null;
		try {   
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();    
			document = documentBuilder.parse(file);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}			
		Map<String,String> tempMap = new HashMap<String,String>();
		Set<Entry<String,String>> set = ElementValue.profileVals.entrySet();
		for(Entry<String,String> val : set)
		{
			tempMap.put(val.getValue(), val.getValue());
			
			if(val.getKey().equalsIgnoreCase(val.getValue()))
				continue;
			System.out.println("--------------------------"+val.getKey()+":"+val.getValue());
			NodeList serList = document.getElementsByTagName("j.0:Profile");
			for(int i = 0;i<serList.getLength();i++)
			{
				Node node = serList.item(i);
				NamedNodeMap map = node.getAttributes();
				String name = map.getNamedItem("rdf:ID").getNodeValue();
				if(name!= null && name.equalsIgnoreCase(val.getKey()))
				{
					node.getAttributes().getNamedItem("rdf:ID").setNodeValue(val.getValue());
				}
			}
		}
		try {
            Transformer t = TransformerFactory.newInstance().newTransformer();
            t.setOutputProperty("indent", "yes");
            t.transform(new DOMSource(document), new StreamResult(new FileOutputStream(file)));
        } catch (TransformerException e) {
            e.printStackTrace();
            return false;
        } catch (FileNotFoundException e) {
            e.printStackTrace();  
            return false;
        }
		ElementValue.profileVals.clear();
		ElementValue.profileVals.putAll(tempMap);
		set = ElementValue.profileVals.entrySet();
		for(Entry<String,String> val : set)
		{
			System.out.println("--------------------------"+val.getKey()+":"+val.getValue());
		}
		return true;
	}
	
	
	private boolean removeService()
	{
		IWorkbenchPage wbp = win.getActivePage();
		IViewPart vp = null;
		try {
			 vp = wbp.showView("Hello.view2");
		} catch (PartInitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		if(vp == null)
			return false;
		TV tv = (TV)vp;
		List list = tv.list;
		if(FilePath.filePath == null)
			return false;
		File file = new File(FilePath.filePath);  
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();  
		Document document = null;
		try {   
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();    
			document = documentBuilder.parse(file);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}			
		NodeList serList = document.getElementsByTagName("j.1:Service");
		for(int i = 0;i<serList.getLength();i++)//对文档中的每一个service做循环
		{
		
			boolean find = false;
			Node node = serList.item(i);
			NamedNodeMap map = node.getAttributes();
			String name = map.getNamedItem("rdf:ID").getNodeValue();
			for(String ser : list.getItems())//对内存中的每一个service做循环
			{
				if(name.equalsIgnoreCase(ser))
					find = true;//将文档中的值与内存匹配，若有，则表示没有删，标识为true
			}
			if(find == false)
			{
				System.out.println("removing node:"+name);
				node.getParentNode().removeChild(node);//若匹配不到，说明内存中该值已经删除，因此在文档中做相应删除
			}
		}
		try {
            Transformer t = TransformerFactory.newInstance().newTransformer();
            t.setOutputProperty("indent", "yes");
            t.transform(new DOMSource(document), new StreamResult(new FileOutputStream(file)));
        } catch (TransformerException e) {
            e.printStackTrace();
            return false;
        } catch (FileNotFoundException e) {
            e.printStackTrace();  
            return false;
        }
		return true;
	}
	private boolean removeProfile()
	{
		IWorkbenchPage wbp = win.getActivePage();
		IViewPart vp = null;
		try {
			 vp = wbp.showView("Hello.view2");
		} catch (PartInitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		if(vp == null)
			return false;
		TV tv = (TV)vp;
		List list = tv.list_1;
		if(FilePath.filePath == null)
			return false;
		File file = new File(FilePath.filePath);  
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();  
		Document document = null;
		try {   
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();    
			document = documentBuilder.parse(file);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}			
		NodeList serList = document.getElementsByTagName("j.0:Profile");
		for(int i = 0;i<serList.getLength();i++)
		{
		
			boolean find = false;
			Node node = serList.item(i);
			NamedNodeMap map = node.getAttributes();
			String name = map.getNamedItem("rdf:ID").getNodeValue();
			for(String ser : list.getItems())
			{
				if(name.equalsIgnoreCase(ser))
					find = true;
			}
			if(find == false)
			{
				System.out.println("removing node:"+name);
				node.getParentNode().removeChild(node);
			}
		}
		try {
            Transformer t = TransformerFactory.newInstance().newTransformer();
            t.setOutputProperty("indent", "yes");
            t.transform(new DOMSource(document), new StreamResult(new FileOutputStream(file)));
        } catch (TransformerException e) {
            e.printStackTrace();
            return false;
        } catch (FileNotFoundException e) {
            e.printStackTrace();  
            return false;
        }
		return true;
	}
	private boolean addService()
	{
		if(FilePath.filePath == null)
			return false;
		Set<Entry<String,String>> set = ElementValue.vals.entrySet();
		File file = new File(FilePath.filePath);  
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();  
		Document document = null;
		try {   
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();    
			document = documentBuilder.parse(file);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}			
		for(Entry<String,String> val : set)
		{
			String serInMem = val.getValue();
			NodeList serList = document.getElementsByTagName("j.1:Service");
			boolean find = false;
			Node node = null;
			for(int i = 0;i<serList.getLength();i++)//与remove反过来，拿内存与文档中找，若文档中没有，则新添上
			{
				node = serList.item(i);
				NamedNodeMap map = node.getAttributes();
				String serInDoc = map.getNamedItem("rdf:ID").getNodeValue();
				if(serInMem.equalsIgnoreCase(serInDoc))
					find = true;
			}
			if(!find)
			{
				System.out.println("adding service .......name:"+serInMem);
				Element serNode = document.createElement("j.1:Service");
				serNode.setAttribute("rdf:ID", serInMem);
				node.getParentNode().appendChild(serNode);
			}
		}
		try {
            Transformer t = TransformerFactory.newInstance().newTransformer();
            t.setOutputProperty("indent", "yes");
            t.transform(new DOMSource(document), new StreamResult(new FileOutputStream(file)));
        } catch (TransformerException e) {
            e.printStackTrace();
            return false;
        } catch (FileNotFoundException e) {
            e.printStackTrace();  
            return false;
        }
		return true;
	}
	private boolean addProfile()
	{
		if(FilePath.filePath == null)
			return false;
		Set<Entry<String,String>> set = ElementValue.profileVals.entrySet();
		File file = new File(FilePath.filePath);  
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();  
		Document document = null;
		try {   
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();    
			document = documentBuilder.parse(file);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}			
		for(Entry<String,String> val : set)
		{
			String serInMem = val.getValue();
			NodeList serList = document.getElementsByTagName("j.0:Profile");
			boolean find = false;
			Node node = null;
			for(int i = 0;i<serList.getLength();i++)
			{
				node = serList.item(i);
				NamedNodeMap map = node.getAttributes();
				String serInDoc = map.getNamedItem("rdf:ID").getNodeValue();
				if(serInMem.equalsIgnoreCase(serInDoc))
					find = true;
			}
			if(!find)
			{
				System.out.println("adding service .......name:"+serInMem);
				Element serNode = document.createElement("j.0:Profile");
				serNode.setAttribute("rdf:ID", serInMem);
				node.getParentNode().appendChild(serNode);
			}
		}
		try {
            Transformer t = TransformerFactory.newInstance().newTransformer();
            t.setOutputProperty("indent", "yes");
            t.transform(new DOMSource(document), new StreamResult(new FileOutputStream(file)));
        } catch (TransformerException e) {
            e.printStackTrace();
            return false;
        } catch (FileNotFoundException e) {
            e.printStackTrace();  
            return false;
        }
		return true;
	}
	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(IWorkbenchWindow window) {
		// TODO Auto-generated method stub
		this.win = window;
	}

}
