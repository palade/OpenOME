package org.bflow.toolbox.interchange.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
import org.bflow.toolbox.interchange.events.ExportEvent;
import org.bflow.toolbox.interchange.events.ExportListenerRegistry;
import org.osgi.framework.Bundle;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;

import edu.toronto.cs.openome.conversion.convertor.GoalModel_BPEL;
import edu.toronto.cs.openome.conversion.convertor.GoalModel_OWL;
import edu.toronto.cs.openome.conversion.convertor.GoalModel_Q7;

/**
 * Defines an export description object that is represented by a xml file.
 * 
 * @author Arian Storch
 * @since 14/08/09
 * @version 23/06/11
 * 
 */
public class ExportDescription {

	private InputStream iStream = null;
	private Bundle bundle;

	// class attributes
	private String filename;
	private String name;
	private String description;
	private String fileExtension;
	private Vector<Script> scripts = new Vector<Script>();

	// for subclasses
	private File lastExportedFile = null;

	private boolean parsed = false;

	/**
	 * constructor
	 * 
	 * @param filename
	 *            name of the file
	 * 
	 */
	public ExportDescription(Bundle bundle, InputStream iStream, String filename) {
		this.iStream = iStream;
		this.filename = filename;
		this.bundle = bundle;
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
	 * parses only that content of the file that contains the name and
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

	private void parse(boolean breakAfterDescription)
			throws NullPointerException {
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
	 * runs the export description and invokes necessary functions to fulfil the
	 * export
	 * 
	 * @param sourceFile
	 *            source file
	 * @param targetFile
	 *            target file
	 * @param ignoreFilename
	 *            set true if the target file must have the same name as the
	 *            source one
	 * @param checkFileExtension
	 *            proofs if the file extension of the target file is set correct
	 *            and corrects if necessary
	 * @return true if the export succeed else false
	 */
	public boolean run(File sourceFile, File targetFile,
			boolean ignoreFilename, boolean checkFileExtension) {
		if (ignoreFilename)
			targetFile = new File(targetFile.getPath() + "/"
					+ FilenameUtils.getBaseName(sourceFile.getAbsolutePath()));

		// checking file extension and correcting if necessary
		if (checkFileExtension)
			if (!FilenameUtils.getExtension(targetFile.getAbsolutePath())
					.equalsIgnoreCase(this.fileExtension))
				targetFile = new File(targetFile.getPath() + "."
						+ this.fileExtension);

		if (this.fileExtension.equals("q7")) {
			try {

				GoalModel_Q7 q7 = new GoalModel_Q7(
						sourceFile.getCanonicalPath(),
						targetFile.getCanonicalPath());

			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (this.fileExtension.equals("owl")) {
			try {

				GoalModel_OWL owl = new GoalModel_OWL(
						sourceFile.getCanonicalPath(),
						targetFile.getCanonicalPath());

			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (this.fileExtension.equals("bpel")) {

			try {
				GoalModel_BPEL bpel = new GoalModel_BPEL(
						sourceFile.getCanonicalPath(),
						targetFile.getCanonicalPath());
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else {
			Vector<File> createdFiles = new Vector<File>(); // beinhaltet
															// tempor�r
															// angelegte Dateien

			fireExportEvent(ExportEvent.START, -1, sourceFile, targetFile);

			File newSourceFile = null;

			// removing unhandable extensions
			try {
				String fContent = FileUtils.readFileToString(sourceFile);

				int begin = 0;

				while ((begin = fContent.indexOf("<Subdiagram>")) != -1) {
					int end = fContent.indexOf("</Subdiagram>", begin);
					String rpl = fContent.substring(begin, end + 13);

					fContent = fContent.replaceFirst(rpl, "");
				}

				newSourceFile = File.createTempFile("newSourceFile", ".epctmp");
				createdFiles.add(newSourceFile);

				FileUtils.writeStringToFile(newSourceFile, fContent);
			} catch (Exception ex) {
				ex.printStackTrace();
			}

			if (newSourceFile != null)
				sourceFile = newSourceFile;

			// finished

			int countScripts = this.getScripts().size(); // Anzahl der
															// XSLT-Skripte

			for (int i = 0; i < countScripts; i++) {
				Script script = this.getScripts().get(i);

				/*
				 * copying the script file temporarly on local
				 */
				String scriptFileLocation;
				InputStream scriptStream = null;

				scriptFileLocation = script.getFile();

				try {
					if (bundle != null) {
						scriptStream = bundle.getEntry(scriptFileLocation)
								.openStream();
					} else {
						scriptStream = new FileInputStream(new File(
								FilenameUtils.getFullPath(this.filename)
										+ script.getFile()));
					}

					if (scriptStream == null)
						throw new NullPointerException(
								"Input stream for XSLT script is null");

				} catch (Exception ex) {
					ex.printStackTrace();
					fireExportEvent(ExportEvent.BROKEN, i, sourceFile,
							targetFile);
					return false;
				}

				File tempScriptFile;

				try {
					tempScriptFile = File.createTempFile("temp", ".xslt");

					OutputStream os = new FileOutputStream(tempScriptFile);

					IOUtils.copy(scriptStream, os);

				} catch (Exception ex) {
					ex.printStackTrace();
					fireExportEvent(ExportEvent.BROKEN, i, sourceFile,
							targetFile);
					return false;
				}

				/*
				 * finished
				 */

				/*
				 * setting up source file
				 */
				File source;

				if (createdFiles.size() == 0) // es muss stets die zuletzt
					source = sourceFile; // angelegte Datei zur
											// Weiterverarbeitung
				else
					// genutzt werden
					source = createdFiles.lastElement(); //

				/*
				 * setting up target file
				 */
				File target = null;

				if (i == countScripts - 1) // beim letzten Skript
					target = targetFile; // ist die Zieldatei
				else
					try {
						target = File.createTempFile("temp_" + i, "tmp");
					} catch (IOException e) {
						e.printStackTrace();
						fireExportEvent(ExportEvent.BROKEN, i, source, target);
						return false;
					}

				try {

					ArrayList<String> parameterList = new ArrayList<String>();

					TransformerFactoryImpl tFactory = new TransformerFactoryImpl();
					Transformer transformer = tFactory
							.newTransformer(new StreamSource(tempScriptFile));

					for (String key : script.getParams().keySet()) {
						if (isControlKey(key))
							continue;

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

					if (script.getParams().containsKey("insertAttributes")) {
						fireExportEvent(ExportEvent.INSERT_ATTRIBUTES, i,
								source, target);
					}

				} catch (Exception ex) {
					ex.printStackTrace();
					fireExportEvent(ExportEvent.BROKEN, i, source, target);
					return false;
				}

				tempScriptFile.delete();
				createdFiles.add(target);

				lastExportedFile = target; // targetFile;

				fireExportEvent(ExportEvent.STEP_DONE, i, source, target);
			}

			for (int i = 0; i < countScripts - 1; i++)
				createdFiles.get(i).delete();

			fireExportEvent(ExportEvent.EXPORT_DONE, -1, sourceFile, targetFile);
		}

		return true;
	}

	/**
	 * runs the export description and invokes necessary functions to fulfil the
	 * export
	 * 
	 * @param exportDescription
	 *            ExportDescription that shall run
	 * @param sourceFile
	 *            source file
	 * @param targetFile
	 *            target file
	 * @param checkFileExtension
	 *            proofs if the file extension of the target file is set correct
	 *            and corrects if necessary
	 * @return true if the export succeed else false
	 */
	public static boolean run(ExportDescription exportDescription,
			File sourceFile, File targetFile, boolean checkFileExtension) {
		exportDescription.parse();

		return exportDescription.run(sourceFile, targetFile, false,
				checkFileExtension);
	}

	/**
	 * runs the export description and invokes necessary functions to fulfil the
	 * export
	 * 
	 * @param exportDescription
	 *            ExportDescription that shall run
	 * @param sourceFile
	 *            source file
	 * @param targetFile
	 *            target file
	 * @return true if the export succeed else false
	 */
	public static boolean run(ExportDescription exportDescription,
			File sourceFile, File targetFile) {
		exportDescription.parse();

		return exportDescription.run(sourceFile, targetFile, false, true);
	}

	/**
	 * This method is invoked when a script includes the "insertAttribute"
	 * parameter. By default this is empty. Subclasses may override.
	 * 
	 * @param attributeFile
	 *            attribute file or null
	 * @param targetFile
	 *            target file
	 */
	protected void insertAttributes(File targetFile) {

	}

	/**
	 * Proofs if the given key is an control key and not an xslt parameter
	 * 
	 * @param key
	 *            key to proof
	 * @return true or false
	 */
	protected boolean isControlKey(String key) {
		if (key.equalsIgnoreCase("insertAttribute"))
			return true;

		return false;
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
				String attrValue = attrArray[1].substring(1,
						attrArray[1].length() - 1);

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
	 * returns the name of the ExportDescription<br/>
	 * <b>note:</b> call <code>parse()</code> first, else null will be returned
	 * 
	 * @return name of the ExportDescription
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
	 * Set the name of the ExportDescription
	 * 
	 * @param name
	 *            name of the ExportDescription
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Set the description of the ExportDescription
	 * 
	 * @param description
	 *            description of the ExportDescription
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Set the extension of the exported file.
	 * 
	 * @param extension
	 *            extension of the exported file.
	 */
	public void setFileExtension(String extension) {
		this.fileExtension = extension;
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
	 * Returns the last exported file by this script.
	 * 
	 * @return last exported file
	 */
	protected File getLastExportedFile() {
		return lastExportedFile;
	}

	@Override
	public String toString() {
		String retString = "[ExportDescription] Filename: " + filename
				+ " Name: " + name + " Description: " + description;

		for (Script script : scripts)
			retString += " " + script;

		return retString;
	}

	/**
	 * Fires an export event.
	 * 
	 * @param type
	 *            type of the event
	 * @param source
	 *            source file
	 * @param target
	 *            target file
	 */
	private void fireExportEvent(int type, int step, File source, File target) {
		ExportEvent event = new ExportEvent();
		event.exportDescription = this;
		event.sourceFile = source;
		event.targetFile = target;
		event.type = type;
		event.step = step;

		ExportListenerRegistry.dispatchEvent(event);
	}
}
