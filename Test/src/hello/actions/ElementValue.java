package hello.actions;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ElementValue {
	public static Map<String,String> vals = new HashMap<String,String>();
	public static Map<String,String> profileVals = new HashMap<String,String>();
	public static Map<String,String> owlPath = new HashMap<String,String>();
	public static void updateOWLPathFile()
	{
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();  
		Document document = null;
		try {   
			File file = new File("C:\\OWLPath.xml");
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();    
			document = documentBuilder.parse(file);
			
			Set<Entry<String,String>> set = owlPath.entrySet();
			NodeList list = document.getElementsByTagName("Pair");
			for(Entry<String,String> entry : set)
			{
				boolean find = false;
				for(int i = 0;i<list.getLength();i++)
				{
					String serName,path;
					Node node = list.item(i);
					NamedNodeMap map = node.getAttributes();
					serName = map.getNamedItem("serName").getNodeValue();
					if(entry.getKey().equals(serName))
					{
						find = true;
					}
				}
				if(!find)
				{
					Element element = document.createElement("Pair");
					element.setAttribute("serName", entry.getKey());
					element.setAttribute("path", entry.getValue());
					document.getElementsByTagName("OWLPath").item(0).appendChild(element);
				}
			}
			try {
	            Transformer t = TransformerFactory.newInstance().newTransformer();
	            t.setOutputProperty("indent", "yes");
	            t.transform(new DOMSource(document), new StreamResult(new FileOutputStream(file)));
	        } catch (TransformerException e) {
	            e.printStackTrace();
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();  
	        }
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}	
		
	}
	static {
		
		File file = new File("C:\\OWLPath.xml");
		if(file.exists())
		{
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();  
			Document document = null;
			try {   
				DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();    
				document = documentBuilder.parse(file);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}	
			
			NodeList nodeList = document.getElementsByTagName("Pair");
			for(int i = 0;i<nodeList.getLength();i++)
			{
				String serName,path;
				Node node = nodeList.item(i);
				NamedNodeMap map = node.getAttributes();
				serName = map.getNamedItem("serName").getNodeValue();
				path = map.getNamedItem("path").getNodeValue();
				owlPath.put(serName, path);
			}
		}
		else
		{
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();    
			DocumentBuilder builder = null;
			try {
				builder = factory.newDocumentBuilder();
			} catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}    
			Document document = builder.newDocument(); 
			Element root = document.createElement("OWLPath");
			document.appendChild(root);
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
				 PrintWriter pw = new PrintWriter(new FileOutputStream("C:\\OWLPath.xml"));              
				 StreamResult result = new StreamResult(pw);              
				 transformer.transform(source, result);              
				 System.out.println("OWL创建成功");         
				 } 
			 	catch (Exception e) 
			 	{           
			 		e.printStackTrace();
			 		System.out.println("创建OWL失败");         
			 	}
		}
		  
		
	
	}
}
