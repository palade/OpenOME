package edu.toronto.cs.openome.conversion.convertor;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import edu.toronto.cs.openome_model.Contribution;
import edu.toronto.cs.openome_model.Intention;
import edu.toronto.cs.openome_model.Model;
import edu.toronto.cs.openome_model.impl.BreakContributionImpl;
import edu.toronto.cs.openome_model.impl.HelpContributionImpl;
import edu.toronto.cs.openome_model.impl.HurtContributionImpl;
import edu.toronto.cs.openome_model.impl.MakeContributionImpl;
import edu.toronto.cs.openome_model.impl.TaskImpl;

/**
 * Class to convert Diagram Representation of the model to Q7 syntax.
 * 
 * @author showzeb
 * 
 */
@SuppressWarnings("restriction")
public class GoalModel_BPEL {

	private edu.toronto.cs.openome_model.Model model;
	private String sourceFile = "";
	private String targetFile = "";
	private BufferedWriter writer = null;
	private URI uri;
	private String warning = "";

	public GoalModel_BPEL(String sourceFile, String targetFile) {
		this.sourceFile = sourceFile;
		this.targetFile = targetFile;
		initialize();
	}

	/**
	 * Initialize the setup, get the Model and Diagram from the source file.
	 * 
	 * @throws IOException
	 */
	private void initialize() {

		// Extracting the model and the diagram from the XMI file.
		uri = URI.createFileURI(sourceFile);
		Resource resource = new XMIResourceImpl();
		resource.unload();
		resource.setURI(uri);
		try {
			resource.load(null);
			for (Object o : resource.getContents()) {
				if (o instanceof Model) {
					model = (Model) o;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Checking for invalid syntax and prompting the user with warning if
		// there is invalid syntax.
		if (invalidSyntax()) {
			if (messageDialog()) {

			}
		} else {

			try {
				Map<String, List<String>> tasks = new HashMap<String, List<String>>();
				EList<Intention> intentions = model.getAllIntentions();
				for (Intention i : intentions) {
					if (i instanceof TaskImpl) {
						// List<String> nextTasks = ((TaskImpl)
						// i).getNextTasks();
						// tasks.put(i.getName(), nextTasks);
					}
				}

				toBPEL(tasks);

			} catch (Exception e) {

				e.printStackTrace();

			}
		}

	}

	private void toBPEL(Map<String, List<String>> tasks)
			throws ParserConfigurationException, SAXException, IOException {

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = factory.newDocumentBuilder();
		Document doc = db.newDocument();

		Element bpel = doc.createElement("bpel:process");
		bpel.setAttribute("name", "Test");
		bpel.setAttribute("xmlns:bpel",
				"http://docs.oasis-open.org/wsbpel/2.0/process/executable");
		bpel.setAttribute("xmlns:tns", "Test");

		Element mainSequence = doc.createElement("bpel:sequence");
		mainSequence.setAttribute("name", "main");
		Element mainRecieve = doc.createElement("bpel:receive");
		mainRecieve.setAttribute("name", "receiveInput");
		mainRecieve.setAttribute("createInstance", "yes");
		mainSequence.appendChild(mainRecieve);
		Element mainReply = doc.createElement("bpel:reply");
		mainReply.setAttribute("name", "replyOutput");

		Map<String, Element> taskEles = new HashMap<String, Element>();

		// First create all the task elements
		for (String keyTask : tasks.keySet()) {
			Element e = createTaskElement(doc, keyTask);
			taskEles.put(keyTask, e);
		}

		for (String keyTask : tasks.keySet()) {
			List<String> nextTasks = tasks.get(keyTask);

			// If the current task node has one next task only
			if (nextTasks != null && nextTasks.size() == 1) {
				Element ele = taskEles.get(keyTask);
				String nextTaskName = nextTasks.get(0);
				Element nextTaskEle = taskEles.get(nextTaskName);
				// Make sure there exists
				if (ele != null && nextTaskName != null) {
					NodeList nodes = nextTaskEle.getChildNodes();

					for (int i = 0; i < nodes.getLength(); ++i) {
						ele.appendChild(nodes.item(i));
					}

					taskEles.remove(nextTaskName);
				}

			} else if (nextTasks != null && nextTasks.size() > 1) {
				
				Element beforeIfEle = taskEles.get(keyTask);

				if (beforeIfEle != null) {

					// Create an IF element
					Element ifEle = createIfElement(doc, beforeIfEle);
					
					boolean isFirstBranch = true;
					for (String nextTaskName : nextTasks) {

						Element nextTaskEle = taskEles.get(nextTaskName);
						if (nextTaskEle != null) {
							if (isFirstBranch) {
								ifEle.appendChild(nextTaskEle);
								isFirstBranch = false;
							} else {

								Element elseEle = doc
										.createElement("bpel:elseif");
								elseEle.appendChild(nextTaskEle);
								ifEle.appendChild(elseEle);

							}
							taskEles.remove(nextTaskName);
						}

					}
				}

			}
		}
		for(Element e : taskEles.values()){
			mainSequence.appendChild(e);
		}
		
		mainSequence.appendChild(mainReply);
		bpel.appendChild(mainSequence);
		doc.appendChild(bpel);

		output(doc);

	}

	private Element createIfElement(Document doc, Element beforeIfEle) {

		Element ifEle = doc.createElement("bpel:if");
		beforeIfEle.appendChild(ifEle);

		return ifEle;
	}

	public void output(Node node) {
		TransformerFactory transFactory = TransformerFactory.newInstance();
		try {
			Transformer transformer = transFactory.newTransformer();
			transformer.setOutputProperty("encoding", "gb2312");
			transformer.setOutputProperty("indent", "yes");

			DOMSource source = new DOMSource();
			source.setNode(node);
			StreamResult result = new StreamResult();
			result.setOutputStream(System.out);

			transformer.transform(source, result);
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}
	}

	private Element createTaskElement(Document doc, String keyTask) {

		Element sequenceEle = doc.createElement("bpel:sequence");
		sequenceEle.setAttribute("name", keyTask + "_sequence");
		Element assignEle1 = doc.createElement("bpel:assign");
		assignEle1.setAttribute("name", keyTask + "_assign" + "_1");
		Element invokeEle = doc.createElement("bpel:invoke");
		invokeEle.setAttribute("name", keyTask + "_invoke");
		Element assignEle2 = doc.createElement("bpel:assign");
		assignEle2.setAttribute("name", keyTask + "_assign" + "_2");

		sequenceEle.appendChild(assignEle1);
		sequenceEle.appendChild(invokeEle);
		sequenceEle.appendChild(assignEle2);

		return sequenceEle;

	}

	/**
	 * Create the targer file with the .q7 extension. Remember to close the
	 * buffered stream after writing is done.
	 */
	private void createFile() {
		try {
			FileWriter fstream = new FileWriter(targetFile);
			writer = new BufferedWriter(fstream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Prompts the user to either continue with the Export process or to stop
	 * it.
	 * 
	 * @return true if the user click's yes otherwise false.
	 */
	public boolean messageDialog() {
		Shell shell = new Shell(Display.getCurrent());

		MessageBox messageBox = new MessageBox(shell, SWT.YES | SWT.NO);
		messageBox.setMessage(warning + "Do you want to continue?");

		int rc = messageBox.open();

		switch (rc) {
		case SWT.YES:
			return true;
		case SWT.NO:
			return false;
		}

		return false;
	}

	/**
	 * Return true if the model contains element that are not supported by Q7
	 * syntax, otherwise false.
	 * 
	 * @return true if the model contains element that are not supported by Q7
	 *         syntax, otherwise false.
	 */
	public boolean invalidSyntax() {

		boolean syntax = false;
		for (Intention intention : model.getAllIntentions()) {
			if (intention instanceof edu.toronto.cs.openome_model.Resource) {
				syntax = true;
				warning += "- "
						+ intention.getName()
						+ " Resource is not recognized by Q7. It will be converted to a Goal."
						+ " Accepted Intentions are \"Goal\", \"Softgoal\", and \"Task\".\n";
			}
			for (Contribution link : intention.getContributesTo()) {
				if (!(link instanceof MakeContributionImpl
						|| link instanceof HelpContributionImpl
						|| link instanceof HurtContributionImpl || link instanceof BreakContributionImpl)) {
					syntax = true;
					warning += "- "
							+ link.getContributionType()
							+ " is not recognized by Q7 as a contribution link. It will be removed. "
							+ "Accepted contribution links are \"Make\", \"Help\", \"Hurt\", and \"Break\".\n";
				}
			}
		}

		if (model.getAssociations().size() > 0) {
			syntax = true;
			warning += "- Association links are not recognized by Q7 and they will be removed.\n";
		}
		return syntax;
	}

}
