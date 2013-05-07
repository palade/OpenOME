package org.bflow.toolbox.addons.store;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Vector;

import org.bflow.toolbox.addons.events.ProtocolStoreListener;
import org.bflow.toolbox.addons.utils.ProtocolDescriptor;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

/**
 * Defines a store that contains all installed protocols.
 * @author Arian Storch
 * @since 17/04/10
 * @version 07/06/11
 *
 */
public class ProtocolStore 
{	
	/**
	 * holds all installed chains
	 */
	private static Vector<ProtocolDescriptor> installedProtocols = new Vector<ProtocolDescriptor>();
	
	/**
	 * init state
	 */
	private static boolean init = false;
	
	/**
	 * holds the listeners
	 */
	private static Vector<ProtocolStoreListener> storeListener = new Vector<ProtocolStoreListener>();
	
	/**
	 * Inits the store.
	 */
	protected static void init()
	{	
		SAXReader reader = new SAXReader();
		
		try
		{
			Document xmlDocument = reader.read(Key.KEY_PROTOCOLSTORE_FILE);
			Element root = xmlDocument.getRootElement();
			
			for(Iterator<?> it = root.elementIterator("protocol"); it.hasNext();)
			{
				Element prot = (Element) it.next();
				
				String id = prot.attributeValue("id");
				String fName = prot.attributeValue("file");
				File file = new File(Key.KEY_PROTOCOLSTORE_PATH.getAbsolutePath()+"/"+fName);
				
				installedProtocols.add(new ProtocolDescriptor(id, file, true));				
			}
		}
		catch(DocumentException ex)
		{
			//ex.printStackTrace();
		}
			
		init = true;
	}
	
	/**
	 * Installs a protocol.
	 * @param descriptor protocol descriptor
	 */
	public static void installProtocol(ProtocolDescriptor descriptor)
	{
		if(!init)
			init();
		
		if(installedProtocols.contains(descriptor)) {
			String name = descriptor.getName();
			String id = descriptor.getId();
			
			name += "*";
			id += "X";
			
			descriptor.setName(name);
			descriptor.setId(id);
		}
		
		installedProtocols.add(descriptor);
		
		fireAddedEvent(descriptor);
	}
	
	/**
	 * Removes the protocol specified by the descriptor.
	 * @param descriptor protocol descriptor
	 */
	public static void removeProtocol(ProtocolDescriptor descriptor)
	{
		if(!init)
			init();
		
		installedProtocols.remove(descriptor);
		fireRemovedEvent(descriptor);
	}
	
	/**
	 * Saves the add-ons protocols.
	 */
	public static void save()
	{
		if(!init)
			init();
		
		Document xmlDocument = DocumentHelper.createDocument();
		
		Element root = xmlDocument.addElement("protocolstore");
		
		int id = 1;
		
		for(ProtocolDescriptor td:installedProtocols) {
			if(!td.storable) {
				continue;
			}
			
			Element protocol = root.addElement("protocol");
			protocol.addAttribute("id", ""+(id++));
			protocol.addAttribute("file", td.getFile().getName());
			
			td.saveDescription();
		}
		
		try
		{
			OutputFormat format = OutputFormat.createPrettyPrint();
			
			if(!Key.KEY_PROTOCOLSTORE_FILE.exists())
			{
				Key.KEY_PROTOCOLSTORE_FILE.getParentFile().mkdirs();
				Key.KEY_PROTOCOLSTORE_FILE.createNewFile();
			}
			
			XMLWriter writer = new XMLWriter(new FileWriter(Key.KEY_PROTOCOLSTORE_FILE), format);
			writer.write(xmlDocument);
			writer.close();
		}
		catch(IOException ex)
		{
			ex.printStackTrace();
		}
	}
	
	/**
	 * Returns a vector containing all installed chains.
	 * @return vector containing all installed chains
	 */
	public static Vector<ProtocolDescriptor> getInstalledProtocols()
	{
		if(!init)
			init();
		
		return installedProtocols;
	}

	/**
	 * Adds the listener to the store.
	 * @param listener
	 */
	public static void addStoreListener(ProtocolStoreListener listener) {
		storeListener.add(listener);
	}
	
	/**
	 * Removes the listener from the store.
	 * @param listener
	 */
	public static void removeStoreListener(ProtocolStoreListener listener) {
		storeListener.remove(listener);
	}
	
	/**
	 * Fires the protocol added event.
	 * @param pd protocol descriptor
	 */
	private static void fireAddedEvent(ProtocolDescriptor pd) {
		for(ProtocolStoreListener l:storeListener)
			l.protocolAdded(pd);
	}
	
	/**
	 * Fires the protocol removed event.
	 * @param pd protocol descriptor
	 */
	private static void fireRemovedEvent(ProtocolDescriptor pd) {
		for(ProtocolStoreListener l:storeListener) 
			l.protocolRemoved(pd);
	}
}
