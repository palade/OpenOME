package org.bflow.toolbox.addons.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import org.bflow.toolbox.addons.core.model.IComponent;
import org.bflow.toolbox.addons.core.model.Protocol;
import org.bflow.toolbox.addons.protocols.Standardprotocol;
import org.bflow.toolbox.addons.store.ComponentStore;
import org.bflow.toolbox.i18n.MessageProvider;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

/**
 * <p>Defines a description of a protocol.</p>
 * <p>To seperate internal protocols from external (user installed) there is parameter called classpath. By default
 * it is set NULL, indicating that the implementing class is an internal. So, if you want to use external classes
 * set classpath to the location where the implementing class is installed, e.g. "C:/chains/myChain.jar".</p>
 * @author Arian Storch
 * @since 17/04/10
 * @version 07/06/11
 */
public class ProtocolDescriptor 
{
	public boolean storable;
	
	private String name;
	private Protocol protocol;
	private String classPath = null;
	
	private String id;
	private File file;
	
	private HashMap<String, String> displayMap = new HashMap<String, String>();
	private HashMap<String, String> dscrptnMap = new HashMap<String, String>();
	
	/**
	 * Constructor.
	 * @param id id of the descriptor
	 * @param file file
	 */
	public ProtocolDescriptor(String id, File file, boolean storable) {
		this.id = id;
		this.file = file;
		this.storable = storable;
		
		parseDescription();
	}
	
	
	/**
	 * @return the name
	 */
	public String getName() {		
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Returns the file of the protocol.
	 * @return file of the protocol
	 */
	public File getFile() {
		return file;
	}
	
	/**
	 * Sets the file of the protocol.
	 * @param file file
	 */
	public void setFile(File file) {
		this.file = file;
	}
	
	/**
	 * Returns the id of the protocol descriptor.
	 * @return id of the protocol descriptor
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * Sets the id of the protocol descriptor.
	 * @param id id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Returns the protocol.
	 * @return the protocol
	 */
	public Protocol getProtocol() {
		return protocol;
	}

	/**
	 * Sets the protocol.
	 * @param protocol protocol
	 */
	public void setProtocol(Protocol protocol) {
		this.protocol = protocol;
	}
	
	/**
	 * Add an abbreviation specified display name for the protocol.
	 * @param abbreviation
	 * @param name
	 */
	public void addDisplayName(String abbreviation, String name) {
		displayMap.put(abbreviation, name);
	}
	
	/**
	 * Add an abbreviation specified description text for the protocol.
	 * @param abbreviation
	 * @param text
	 */
	public void addDescriptionText(String abbreviation, String text) {
		dscrptnMap.put(abbreviation, text);
	}

	/**
	 * @return the classPath
	 */
	public String getClassPath() {
		return classPath;
	}

	/**
	 * @param classPath the classPath to set
	 */
	public void setClassPath(String classPath) {
		this.classPath = classPath;
	}
	
	private void parseDescription() {
		if(file != null) {
			SAXReader reader = new SAXReader();
			
			try {
				Document doc = reader.read(file);
				Element root = doc.getRootElement();
				
				this.name = root.attributeValue("name");
				
				// display
				Element display = root.element("display");
				
				for(Iterator<?> it = display.elementIterator(); it.hasNext(); ) {
					Element el = (Element) it.next();
					String elName = el.getName();
					String elText = el.getText();
					
					displayMap.put(elName, elText);
				}
				
				String name = displayMap.get(MessageProvider.getActiveLanguageAbbreviation());
				
				if(name == null)
					name = displayMap.get("default");
				
				// description
				Element description = root.element("description");
				
				for(Iterator<?> it = description.elementIterator(); it.hasNext();) {
					Element el = (Element) it.next();
					
					String elName = el.getName();
					String elText = el.getText();
					
					dscrptnMap.put(elName, elText);
				}
				
				String descTxt = dscrptnMap.get(MessageProvider.getActiveLanguageAbbreviation());
				
				if(descTxt == null)
					descTxt = dscrptnMap.get("default");
												
				// protocol
				this.protocol = new Standardprotocol();
				Standardprotocol prot = (Standardprotocol)this.protocol;
				prot.setName(name);
				prot.setDescription(descTxt);
								
				// components
				Element components = root.element("components");
				for(Iterator<?> it = components.elementIterator("component"); it.hasNext(); ) {
					Element component = (Element)it.next();
					
					String clazz = component.attributeValue("class");					
					String params = component.attributeValue("params");
					
					IComponent iComp = ComponentStore.identify(clazz, false);
					iComp = iComp.getClass().newInstance();
										
					prot.setComponentParam(iComp, params);
					prot.addComponent(iComp);
				}
				
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	
	/**
	 * Saves the description into its file.
	 */
	public void saveDescription() {		
		Document doc = DocumentHelper.createDocument();
		
		Element protocol = doc.addElement("protocol");
		protocol.addAttribute("name", this.getName());
		
		Element display = protocol.addElement("display");
		
		for(Iterator<Entry<String, String>> it = displayMap.entrySet().iterator(); it.hasNext(); ) {
			Entry<String, String> entry = it.next();
			
			Element el = display.addElement(entry.getKey());
			el.addText(entry.getValue());
		}
		
		Element description = protocol.addElement("description");
		
		for(Iterator<Entry<String, String>> it = dscrptnMap.entrySet().iterator(); it.hasNext();) {
			Entry<String, String> entry = it.next();
			
			Element el = description.addElement(entry.getKey());
			el.addText(entry.getValue());
		}
		
		Element components = protocol.addElement("components");
		
		for(IComponent comp:this.getProtocol().getComponents()) {
			Element component = components.addElement("component");
			
			String clazzName = comp.getClass().getName();
			String param = this.protocol.getComponentParam(comp);
			
			if(param == null )
				param = "";
			
			component.addAttribute("class", clazzName);
			component.addAttribute("params", param);
		}
		
		try
		{
			OutputFormat format = OutputFormat.createPrettyPrint();
			format.setEncoding("ISO-8859-15");
			
			if(!file.exists())
			{
				file.getParentFile().mkdirs();
				file.createNewFile();
			}
			
			XMLWriter writer = new XMLWriter(new FileWriter(file), format);
			writer.write(doc);
			writer.close();
		}
		catch(IOException ex)
		{
			ex.printStackTrace();
		}
		
	}
	
	/**
	 * Returns true if the descriptor and its protocol is valid and can be used.
	 * @return true or false
	 */
	public boolean isValid() {
		if(protocol != null && protocol.isValid())
			return true;
		else 
			return false;
	}
	
}
