package edu.toronto.cs.openome.conversion.convertor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.dom.DOMElement;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.xml.sax.SAXException;

import edu.toronto.cs.openome_model.Intention;
import edu.toronto.cs.openome_model.Model;
import edu.toronto.cs.openome_model.impl.TaskImpl;

public class GRL2BPELConvertor extends AbstractConvertor {

	private Model model;
	private String bpelFilePath;

	public String getBPELFilePath() {
		return this.bpelFilePath;
	}

	public String convert() {
		String filePath = null;
		try {
			model = getModelImpl();
			Document doc = DocumentHelper.createDocument();
			Map<String, List<String>> tasks = getTasks(model);
			Map<String, Boolean> loops = getLoops(model);
			List<String> waits = getWaits(model);
			Map<String, String> rules = getRules(model);
			Map<String, List<String>> scopes = getScopes(model);
			Map<String, List<String>> flows = getFlows(model);
			Map<String, List<String>> onMsgs = getOnMsgs(model);
			toBPEL(doc, tasks, loops, waits, rules, scopes, flows, onMsgs);
			filePath = toFile(doc);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return filePath;
	}

	private Map<String, List<String>> getOnMsgs(Model model) {
		Map<String, List<String>> onMsgs = new HashMap<String, List<String>>();
		EList<Intention> intentions = model.getAllIntentions();
		for (Intention i : intentions) {
			if (i instanceof TaskImpl) {
				String msg = ((TaskImpl) i).getOnMessage();
				if (msg != null && !msg.isEmpty()) {
					String taskName = ((TaskImpl) i).getName();
					List<String> tasks = onMsgs.get(msg);
					if (tasks == null) {
						tasks = new ArrayList<String>();
					}
					tasks.add(taskName);
					onMsgs.put(msg, tasks);
				}
			}
		}
		return onMsgs;
	}

	private Map<String, List<String>> getFlows(Model model) {
		Map<String, List<String>> flows = new HashMap<String, List<String>>();
		EList<Intention> intentions = model.getAllIntentions();
		for (Intention i : intentions) {
			if (i instanceof TaskImpl) {
				String rule = ((TaskImpl) i).getFlowName();
				if (rule != null && !rule.isEmpty()) {
					String taskName = ((TaskImpl) i).getName();
					List<String> tasks = flows.get(rule);
					if (tasks == null) {
						tasks = new ArrayList<String>();
					}
					tasks.add(taskName);
					flows.put(rule, tasks);
				}
			}
		}
		return flows;
	}

	private Map<String, List<String>> getScopes(Model model) {
		Map<String, List<String>> scopes = new HashMap<String, List<String>>();
		EList<Intention> intentions = model.getAllIntentions();
		for (Intention i : intentions) {
			if (i instanceof TaskImpl) {
				String scope = ((TaskImpl) i).getScopeName();
				if (scope != null && !scope.isEmpty()) {
					String taskName = ((TaskImpl) i).getName();
					List<String> tasks = scopes.get(scope);
					if (tasks == null) {
						tasks = new ArrayList<String>();
					}
					tasks.add(taskName);
					scopes.put(scope, tasks);
				}
			}
		}
		return scopes;
	}

	private Map<String, String> getRules(Model model) {

		Map<String, String> rules = new HashMap<String, String>();
		EList<Intention> intentions = model.getAllIntentions();
		for (Intention i : intentions) {
			if (i instanceof TaskImpl) {
				String rule = ((TaskImpl) i).getContextRule();
				rules.put(i.getName(), rule);
			}
		}
		return rules;

	}

	private List<String> getWaits(Model model) {
		List<String> waits = new ArrayList<String>();
		EList<Intention> intentions = model.getAllIntentions();
		for (Intention i : intentions) {
			if (i instanceof TaskImpl) {
				TaskImpl t = ((TaskImpl) i);
				if (t.isWait()) {
					waits.add(i.getName());
				}
			}
		}

		return waits;

	}

	private Map<String, Boolean> getLoops(Model model) {
		Map<String, Boolean> loops = new HashMap<String, Boolean>();
		EList<Intention> intentions = model.getAllIntentions();
		for (Intention i : intentions) {
			if (i instanceof TaskImpl) {
				Boolean isLoop = ((TaskImpl) i).getLoop();
				loops.put(i.getName(), isLoop);
			}
		}
		return loops;
	}

	private String toFile(Document doc) throws FileNotFoundException,
			IOException, CoreException {
		URI modelURI = getModelURI();
		IWorkspace w = ResourcesPlugin.getWorkspace();
		String workspacePath = w.getRoot().getLocationURI().getPath();
		String modelFilePath = modelURI.path().replaceFirst("/resource", "");
		int index = modelFilePath.lastIndexOf(modelURI.fileExtension());
		String bpelFilePath = modelFilePath.substring(0, index) + "bpel";
		OutputFormat format = OutputFormat.createPrettyPrint();
		XMLWriter writer = new XMLWriter(new FileWriter(new File(workspacePath
				+ bpelFilePath)), format);
		writer.write(doc);
		writer.close();

		w.getRoot().refreshLocal(
				org.eclipse.core.resources.IResource.DEPTH_INFINITE, null);

		return bpelFilePath;
	}

	/**
	 * Get all the task elements from the goal model
	 * 
	 * @param model
	 * @return
	 */
	private Map<String, List<String>> getTasks(Model model) {
		Map<String, List<String>> tasks = new HashMap<String, List<String>>();
		EList<Intention> intentions = model.getAllIntentions();
		for (Intention i : intentions) {
			if (i instanceof TaskImpl) {
//				List<String> nextTasks = ((TaskImpl) i).getNextTasks();
//				tasks.put(i.getName(), nextTasks);
			}
		}
		return tasks;
	}

	/**
	 * To BPEL file
	 * 
	 * @param tasks
	 * @param loops
	 * @param waits
	 * @param rules
	 * @param flows
	 * @param scopes
	 * @param onMsgs
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	private void toBPEL(Document doc, Map<String, List<String>> tasks,
			Map<String, Boolean> loops, List<String> waits,
			Map<String, String> rules, Map<String, List<String>> scopes,
			Map<String, List<String>> flows, Map<String, List<String>> onMsgs)
			throws ParserConfigurationException, SAXException, IOException {

		Element bpel = new DOMElement("bpel:process");
		bpel.addAttribute("name", "Test");
		bpel.addAttribute("xmlns:bpel",
				"http://docs.oasis-open.org/wsbpel/2.0/process/executable");
		bpel.addAttribute("xmlns:tns", "Test");

		Element mainSequence = new DOMElement("bpel:sequence");
		mainSequence.addAttribute("name", "main");
		Element mainRecieve = new DOMElement("bpel:receive");
		mainRecieve.addAttribute("name", "receiveInput");
		mainRecieve.addAttribute("createInstance", "yes");
		mainSequence.add(mainRecieve);
		Element mainReply = new DOMElement("bpel:reply");
		mainReply.addAttribute("name", "replyOutput");

		Map<String, Element> taskEles = new HashMap<String, Element>();

		// First create all the task elements
		for (String keyTask : tasks.keySet()) {
			Element e = createTaskElement(doc, keyTask);
			taskEles.put(keyTask, e);
		}

		// Handle the loops
		for (String keyTask : loops.keySet()) {

			if (loops.get(keyTask) != null && loops.get(keyTask) == true) {
				Element loopEle = taskEles.get(keyTask);
				Element whileSeq = new DOMElement("bepl:sequence");
				Element whileEle = new DOMElement("bpel:while");
				Element conEle = new DOMElement("bpel:condition");
				whileSeq.add(whileEle);
				whileEle.add(conEle);
				whileEle.add(loopEle);
				taskEles.put(keyTask, whileSeq);
			}

		}
		// handle the waits
		for (String w : waits) {

			Element seqEle = taskEles.get(w);
			Element waitEle = new DOMElement("bpel:wait");
			boolean first = true;
			@SuppressWarnings("unchecked")
			List<Element> eles = seqEle.elements();
			for (Element e : eles) {
				if (first) {
					seqEle.add(waitEle);
					first = false;
				}
				e.detach();
				seqEle.add(e);
			}

		}

		// Handle the scopes
		for (String scope : scopes.keySet()) {

			List<String> ts = scopes.get(scope);
			for (String t : ts) {
				handleTask(tasks, taskEles, t, rules);
			}

			for (String t : ts) {
				Element e = taskEles.get(t);
				if (e != null) {
					Element pe = taskEles.get(scope);
					if (pe == null) {
						Element scopeEle = new DOMElement("bpel:flow");
						scopeEle.addAttribute("name", scope);
						scopeEle.add(e);
						taskEles.put(scope, scopeEle);
					} else {
						pe.add(e);
						taskEles.put(scope, pe);
					}
					taskEles.remove(t);
				}

			}

		}

		// Handle the On Message
		for (String msg : onMsgs.keySet()) {

			List<String> ts = onMsgs.get(msg);
			for (String t : ts) {
				handleTask(tasks, taskEles, t, rules);
			}

			for (String t : ts) {
				Element e = taskEles.get(t);

				if (e != null) {

					Element onMsgEle = new DOMElement("bpel:onMessage");
					onMsgEle.add(e);

					Element pe = taskEles.get(msg);
					if (pe == null) {
						Element pickEle = new DOMElement("bpel:pick");
						pickEle.addAttribute("name", msg);
						pickEle.add(onMsgEle);
						taskEles.put(msg, pickEle);
					} else {
						pe.add(onMsgEle);
						taskEles.put(msg, pe);
					}
					taskEles.remove(t);
				}

			}

		}

		// Handle the flows
		for (String flow : flows.keySet()) {

			List<String> ts = flows.get(flow);
			for (String t : ts) {
				handleTask(tasks, taskEles, t, rules);
			}

			for (String t : ts) {
				Element e = taskEles.get(t);
				if (e != null) {
					Element pe = taskEles.get(flow);
					if (pe == null) {
						Element flowEle = new DOMElement("bpel:flow");
						flowEle.addAttribute("name", flow);
						flowEle.add(e);
						taskEles.put(flow, flowEle);
					} else {
						pe.add(e);
						taskEles.put(flow, pe);
					}
					taskEles.remove(t);
				}
			}

		}
		// Handle the sequence and branch
		for (String keyTask : tasks.keySet()) {
			handleTask(tasks, taskEles, keyTask, rules);
		}

		for (Element e : taskEles.values()) {
			mainSequence.add(e);
		}

		mainSequence.add(mainReply);
		bpel.add(mainSequence);
		doc.add(bpel);

	}

	private void handleTask(Map<String, List<String>> tasks,
			Map<String, Element> taskEles, String keyTask,
			Map<String, String> rules) {

		List<String> nextTasks = tasks.get(keyTask);
		if (nextTasks != null && nextTasks.size() >= 1) {
			for (String s : nextTasks)
				handleTask(tasks, taskEles, s, rules);
		} else {
			return;
		}

		// If the current task node has one next task only
		if (nextTasks.size() == 1) {
			Element ele = taskEles.get(keyTask);
			String nextTaskName = nextTasks.get(0);
			Element nextTaskEle = taskEles.get(nextTaskName);
			// Make sure there exists
			if (ele != null && nextTaskEle != null) {
				if (nextTaskEle.getName().equals("bpel:while")) {
					ele.add(nextTaskEle);
				} else {
					@SuppressWarnings("unchecked")
					List<Element> nodes = nextTaskEle.elements();
					for (int i = 0; i < nodes.size(); ++i) {
						Element e = nodes.get(i);
						e.detach();
						ele.add(e);
					}
				}

				taskEles.remove(nextTaskName);
			}
		} else if (nextTasks.size() > 1) {

			Element beforeIfEle = taskEles.get(keyTask);

			if (beforeIfEle != null) {
				Element ifEle = null;

				boolean isFirstBranch = true;
				for (String nextTaskName : nextTasks) {

					Element nextTaskEle = taskEles.get(nextTaskName);
					if (nextTaskEle != null) {
						if (isFirstBranch) {
							// Create an IF element
							ifEle = createIfElement(beforeIfEle);
							Element condition = new DOMElement("bpel:condition");
							condition.addCDATA(rules.get(nextTaskName));
							ifEle.add(condition);
							ifEle.add(nextTaskEle);
							isFirstBranch = false;
						} else {
							// Create an ELSE-IF element
							Element elseEle = new DOMElement("bpel:elseif");
							Element condition = new DOMElement("bpel:condition");
							condition.addCDATA(rules.get(nextTaskName));
							elseEle.add(condition);
							elseEle.add(nextTaskEle);
							ifEle.add(elseEle);
						}
						taskEles.remove(nextTaskName);
					}

				}
			}
		}

	}

	private Element createIfElement(Element beforeIfEle) {

		Element ifEle = new DOMElement("bpel:if");
		beforeIfEle.add(ifEle);
		return ifEle;
	}

	private Element createTaskElement(Document doc, String keyTask) {

		Element sequenceEle = new DOMElement("bpel:sequence");
		sequenceEle.addAttribute("name", keyTask + "_sequence");
		Element assignEle1 = new DOMElement("bpel:assign");
		assignEle1.addAttribute("name", keyTask + "_assign" + "_1");
		Element invokeEle = new DOMElement("bpel:invoke");
		invokeEle.addAttribute("name", keyTask + "_invoke");
		Element assignEle2 = new DOMElement("bpel:assign");
		assignEle2.addAttribute("name", keyTask + "_assign" + "_2");

		sequenceEle.add(assignEle1);
		sequenceEle.add(invokeEle);
		sequenceEle.add(assignEle2);

		return sequenceEle;

	}

}
