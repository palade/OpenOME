package hello.actions;

import java.io.File;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.HashMap;

import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
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

public class SaveAction implements IWorkbenchWindowActionDelegate {
	private IWorkbenchWindow win = null;
	@Override
	public void run(IAction action) {
		// TODO Auto-generated method stub
		updateServiceName();
		updateProfileName();
		removeService();
		removeProfile();
		addService();
		addProfile();
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
		Map<String,String> tempMap = new HashMap<String,String>();//������º������
		Set<Entry<String,String>> set = ElementValue.vals.entrySet();//entry��ֵ�Ա������ǰ�����ݣ�key��ԭʼ�ģ�value���޸ĵ�
		for(Entry<String,String> val : set)
		{
			tempMap.put(val.getValue(), val.getValue());//ʹ����ʱ��temp������ֵ
			
			if(val.getKey().equalsIgnoreCase(val.getValue()))
				continue;
			System.out.println("--------------------------"+val.getKey()+":"+val.getValue());
			NodeList serList = document.getElementsByTagName("j.1:Service");
			for(int i = 0;i<serList.getLength();i++)//���ĵ�����
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
		//���ڴ����
		ElementValue.vals.clear();//���ڴ��е�ԭ���ľ�ֵ�����
		ElementValue.vals.putAll(tempMap);//����ֵ����
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
		for(int i = 0;i<serList.getLength();i++)//���ĵ��е�ÿһ��service��ѭ��
		{
		
			boolean find = false;
			Node node = serList.item(i);
			NamedNodeMap map = node.getAttributes();
			String name = map.getNamedItem("rdf:ID").getNodeValue();
			for(String ser : list.getItems())//���ڴ��е�ÿһ��service��ѭ��
			{
				if(name.equalsIgnoreCase(ser))
					find = true;//���ĵ��е�ֵ���ڴ�ƥ�䣬���У����ʾû��ɾ����ʶΪtrue
			}
			if(find == false)
			{
				System.out.println("removing node:"+name);
				node.getParentNode().removeChild(node);//��ƥ�䲻����˵���ڴ��и�ֵ�Ѿ�ɾ����������ĵ�������Ӧɾ��
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
			for(int i = 0;i<serList.getLength();i++)//��remove�����������ڴ����ĵ����ң����ĵ���û�У���������
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
