package org.bflow.toolbox.interchange.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import net.sf.saxon.TransformerFactoryImpl;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.bflow.toolbox.interchange.events.ImportEvent;
import org.bflow.toolbox.interchange.events.ImportListenerRegistry;
import org.osgi.framework.Bundle;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;

/**
 * This class provides the import function.
 * 
 * @author Arian Storch
 * @since 17/08/09
 * @version 13/07/11
 * 
 */
public class ImportDescription {

	private InputStream iStream = null;
	private Bundle bundle;

	// class attributes
	private String filename;
	private String name;
	private String description;
	private String fileExtension;
	private Vector<Script> scripts = new Vector<Script>();

	// subclasses
	private File lastImportedFile;

	private boolean parsed = false;

	/**
	 * constructor
	 * 
	 * @param filename
	 *            name of the file
	 */
	public ImportDescription(Bundle bundle, InputStream iStream, String filename) {
		this.iStream = iStream;
		this.filename = filename;
		this.bundle = bundle;
	}

	/**
	 * returns the filename of the export description
	 * 
	 * @return name of the file
	 */
	public String getFilename() {
		return filename;
	}

	/**
	 * parses the file given by the constructor
	 * <p/>
	 * <b>note:</b> call this first, else getters will return null
	 */
	public void parse() {
		parse(false);
	}

	/**
	 * Parses only that content of the file that contains the name and
	 * description
	 * <p/>
	 * this is usefull when you only want to get some basic informations about
	 * the file and it isn't neccessary to parse the whole file which may need
	 * more time
	 * <p/>
	 * <b>note:</b> call this first, else getters will return null
	 */
	public void parseDescription() {
		parse(true);
	}

	private void parse(boolean breakAfterDescription) {
		if (parsed)
			return;

		if (iStream == null)
			throw new NullPointerException("input stream is null");

		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document;

			document = builder.parse(iStream);

			Element docElement = document.getDocumentElement();
			NodeList list = docElement.getElementsByTagName("config");

			for (int i = 0; i < list.getLength(); i++) {
				Element el = (Element) list.item(i);

				resolveHeaderInformation(el);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		parsed = true;
	}

	/**
	 * runs the import description and invokes necessary functions to fulfil the
	 * import
	 * 
	 * @param sourceFile
	 *            source file
	 * @param targetFile
	 *            target file
	 * @param ignoreFilename
	 *            set true if the target file must have the same name as the
	 *            source one
	 * @return true if the import succeed else false
	 */
	public boolean run(File sourceFile, File targetFile, boolean ignoreFilename) {

		targetFile = new File(targetFile.getAbsolutePath() + "/"
				+ FilenameUtils.getBaseName(sourceFile.getAbsolutePath())
				+ ".epc");

		Vector<File> createdFiles = new Vector<File>(); // beinhaltet temporär
														// angelegte Dateien
		Vector<File> copiedFiles = new Vector<File>(); // beinhaltet temporär
														// kopierte Dateien
		
		fireImportEvent(ImportEvent.START, -1, sourceFile, targetFile);

		/*
		 * some imports need transformations in a temporary folder
		 */
		File tempSourceFile;

		// copying the source file in a temporary folder
		try {
			tempSourceFile = File.createTempFile(sourceFile.getName(), "");
			FileUtils.copyFile(sourceFile, tempSourceFile);
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}

		copiedFiles.add(tempSourceFile);

		int countScripts = this.getScripts().size(); // Anzahl der XSLT-Skripte

		for (int i = 0; i < countScripts; i++) {
			Script script = this.getScripts().get(i);

			if (script.getFile().isEmpty() && script.getParams().size() == 0)
				continue;

			/*
			 * handling of the copy parameter
			 */
			boolean onlyCopy = false;

			if (script.getParams().containsKey("copy"))
				onlyCopy = true;

			if (onlyCopy) {
				String file = script.getFile(); 
				String name = script.getParams().get("name");
				URL cpyURL = bundle.getEntry(file);

				String tempDir = System.getProperty("java.io.tmpdir");

				try {
					File f = new File(tempDir + "/" + name);
					FileUtils.copyURLToFile(cpyURL, f);

					copiedFiles.add(f);
					continue;
				} catch (Exception ex) {
					ex.printStackTrace();
					return false;
				}

			}

			/*
			 * copying the script into the temporary folder
			 */
			String scriptFileLocation = script.getFile();
			InputStream scriptStream = null;

			try {
				if (bundle != null) {
					scriptStream = bundle.getEntry(scriptFileLocation)
							.openStream();
				} else {
					scriptStream = new FileInputStream(new File(FilenameUtils
							.getFullPath(this.filename)
							+ script.getFile()));
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				fireImportEvent(ImportEvent.BROKEN, i, sourceFile, targetFile);
				return false;
			}

			File tempScriptFile;

			try {
				tempScriptFile = File.createTempFile("temp", ".xslt");

				OutputStream os = new FileOutputStream(tempScriptFile);

				IOUtils.copy(scriptStream, os);
			} catch (Exception ex) {
				ex.printStackTrace();
				fireImportEvent(ImportEvent.BROKEN, i, sourceFile, targetFile);
				return false;
			}

			/*
			 * preparation done
			 */

			/*
			 * setting up source file
			 */
			File source;

			if (createdFiles.size() == 0) // es muss stets die zuletzt
				source = tempSourceFile; // angelegte Datei zur
											// Weiterverarbeitung
			else
				// genutzt werden
				source = createdFiles.lastElement(); //

			/*
			 * setting up target file
			 */
			File target = null;

			try {
				if (i == countScripts - 1) // beim letzten Skript
					target = targetFile; // ist die Zieldatei
				else
					// die im WizardDialog angegebene
					target = File.createTempFile("importfile" + i + "_", ""); //
			} catch (Exception ex) {
				ex.printStackTrace();
				fireImportEvent(ImportEvent.BROKEN, i, source, target);
				return false;
			}

			/*
			 * doing xslt-processing
			 */
			try {
				ArrayList<String> parameterList = new ArrayList<String>();

				TransformerFactoryImpl tFactory = new TransformerFactoryImpl();
				Transformer transformer = tFactory
						.newTransformer(new StreamSource(tempScriptFile));

				for (String key : script.getParams().keySet()) {
					String value = key + "=" + script.getParams().get(key);
					parameterList.add(value);

					String val = script.getParams().get(key);
					Object oVal = null;

					try {
						double d = Double.parseDouble(val);
						oVal = d;
					} catch (NumberFormatException ex) {
						oVal = val;
					}

					transformer.setParameter(key, oVal);
				}

				OutputStream os = new FileOutputStream(target);
				StreamResult sr = new StreamResult(os);
				transformer.transform(new StreamSource(source), sr);
				
				/*
				if(script.getParams().containsKey("")) {
					fireImportEvent(ImportEvent.INSERT_ATTRIBUTES, i, source, target);
				}*/
				
			} catch (Exception ex) {
				ex.printStackTrace();
				return false;
			}

			tempScriptFile.delete();
			createdFiles.add(target);
			
			fireImportEvent(ImportEvent.STEP_DONE, i, source, target);
		}

		/*
		 * cleaning up
		 */
		for (int i = 0; i < createdFiles.size() - 1; i++)
			createdFiles.get(i).delete();

		for (File file : copiedFiles)
			file.delete();

		lastImportedFile = targetFile;
		
		fireImportEvent(ImportEvent.IMPORT_DONE, -1, sourceFile, targetFile);

		return true;
	}

	/**
	 * runs the import description and invokes necessary functions to fulfil the
	 * import
	 * 
	 * @param importDescription
	 *            ImportDescription that shall run
	 * @param sourceFile
	 *            source file
	 * @param targetFile
	 *            target file
	 * @return true if the import succeed else false
	 */
	public static boolean run(ImportDescription importDescription,
			File sourceFile, File targetFile) {
		importDescription.parse();

		return importDescription.run(sourceFile, targetFile, false);
	}

	private void resolveHeaderInformation(Element el) {
		String name = getTextValue(el, "name");
		String description = getTextValue(el, "description");
		String fileextension = getTextValue(el, "fileextension");

		this.name = name;
		this.description = description;
		this.fileExtension = fileextension;

		NodeList list = el.getElementsByTagName("callstack");

		for (int i = 0; i < list.getLength(); i++) {
			Element element = (Element) list.item(i);

			resolveCallstackInformation(element);
		}

	}

	private void resolveCallstackInformation(Element el) {
		NodeList list = el.getElementsByTagName("script");

		for (int i = 0; i < list.getLength(); i++) {
			Element element = (Element) list.item(i);

			resolveScriptInformation(element);
		}
	}

	private void resolveScriptInformation(Element el) {
		String file = getTextValue(el, "file");

		NodeList list = el.getElementsByTagName("param");

		Script script = new Script();
		script.setFile(file);

		for (int i = 0; i < list.getLength(); i++) {
			Element element = (Element) list.item(i);

			NamedNodeMap map = element.getAttributes();

			for (int j = 0; j < map.getLength(); j++) {
				Object e = map.item(j);

				String attributeString = "" + e;

				String attrArray[] = attributeString.split("=");

				String attrName = attrArray[0];
				String attrValue = attrArray[1].substring(1, attrArray[1]
						.length() - 1);

				script.getParams().put(attrName, attrValue);
			}
		}

		this.scripts.add(script);
	}

	/**
	 * takes a xml element and the tag name, look for the tag and get the text
	 * content
	 * 
	 * @param ele
	 *            element
	 * @param tagName
	 *            tag name
	 * @return value of the tag name
	 */
	private String getTextValue(Element ele, String tagName) {
		String textVal = "";
		NodeList nl = ele.getElementsByTagName(tagName);
		if (nl != null && nl.getLength() > 0) {
			Element el = (Element) nl.item(0);
			if (el.hasChildNodes())
				textVal = el.getFirstChild().getNodeValue();
		}

		return textVal;
	}

	/**
	 * returns the name of the ImportDescription<br/>
	 * <b>note:</b> call <code>parse()</code> first, else null will be returned
	 * 
	 * @return name of the ImportDescription
	 */
	public String getName() {
		return name;
	}

	/**
	 * returns the description text<br/>
	 * <b>note:</b> call <code>parse()</code> first, else null will be returned
	 * 
	 * @return description text
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * returns the file extension of the exported file
	 * 
	 * @return file extension of the exported file
	 */
	public String getFileExtension() {
		return fileExtension;
	}

	/**
	 * returns the scripts of the export description<br/>
	 * <b>note:</b> call <code>parse()</code> first, else null will be returned
	 * 
	 * @return scripts of the export description
	 */
	public Vector<Script> getScripts() {
		return scripts;
	}

	/**
	 * Returns the last imported file by this script.
	 * 
	 * @return the last imported file
	 */
	protected File getLastImportedFile() {
		return lastImportedFile;
	}

	@Override
	public String toString() {
		String retString = "[ImportDescription] Filename: " + filename
				+ " Name: " + name + " Description: " + description;

		for (Script script : scripts)
			retString += " " + script;

		return retString;
	}
	
	/**
	 * Fires an import event.
	 * @param type type of the event
	 * @param source source file
	 * @param target target file
	 */
	private void fireImportEvent(int type, int step, File source, File target) {
		ImportEvent event = new ImportEvent();
		event.importDescription = this;
		event.sourceFile = source;
		event.targetFile = target;
		event.type = type;
		event.step = step;
		
		ImportListenerRegistry.dispatchEvent(event);
	}

}
