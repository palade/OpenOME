package edu.toronto.cs.openome.conversion.convertor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
			Map<String, String> sequence = getTasks(model);
			Map<String, Boolean> loops = getLoops(model);
			List<String> waits = getWaits(model);
			Map<String, String> rules = getRules(model);
			Map<String, List<String>> scopes = getScopes(model);
			Map<String, List<String>> flows = getFlows(model);
			Map<String, List<String>> onMsgs = getOnMsgs(model);
			Map<String, List<String>> switchTo = getSwitchTo(model);
			Map<String, String> mergeTo = getMergeTo(model);

			toBPEL(doc, sequence, loops, waits, rules, scopes, flows, onMsgs,
					switchTo, mergeTo);
			filePath = toFile(doc);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return filePath;
	}

	private Map<String, String> getMergeTo(Model model) {
		Map<String, String> mergeTo = new HashMap<String, String>();
		EList<Intention> intentions = model.getAllIntentions();
		for (Intention i : intentions) {
			if (i instanceof TaskImpl) {
				String merge = ((TaskImpl) i).getMergeTo();
				if (merge != null && !merge.isEmpty()) {
					String taskName = ((TaskImpl) i).getName();
					mergeTo.put(taskName, merge);
				}
			}
		}
		return mergeTo;
	}

	private Map<String, List<String>> getSwitchTo(Model model) {
		Map<String, List<String>> switchTo = new HashMap<String, List<String>>();
		EList<Intention> intentions = model.getAllIntentions();
		for (Intention i : intentions) {
			if (i instanceof TaskImpl) {
				String taskName = ((TaskImpl) i).getName();
				List<String> ss = ((TaskImpl) i).getSwitchTo();
				if (ss != null && ss.size() > 0)
					switchTo.put(taskName, ss);
			}
		}
		return switchTo;
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
	private Map<String, String> getTasks(Model model) {
		Map<String, String> tasks = new HashMap<String, String>();
		EList<Intention> intentions = model.getAllIntentions();
		for (Intention i : intentions) {
			if (i instanceof TaskImpl) {
				String nextTask = ((TaskImpl) i).getNextTask();
				// if (nextTask != null)
				tasks.put(i.getName(), nextTask == null ? null : nextTask);
			}
		}
		return tasks;
	}

	/**
	 * To BPEL file
	 * 
	 * @param sequence
	 * @param loops
	 * @param waits
	 * @param rules
	 * @param flows
	 * @param scopes
	 * @param onMsgs
	 * @param mergeTo
	 * @param switchTo
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	private void toBPEL(Document doc, Map<String, String> sequence,
			Map<String, Boolean> loops, List<String> waits,
			Map<String, String> rules, Map<String, List<String>> scopes,
			Map<String, List<String>> flows, Map<String, List<String>> onMsgs,
			Map<String, List<String>> switchTo, Map<String, String> mergeTo)
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
		for (String keyTask : sequence.keySet()) {
			Element e = createTaskElement(doc, keyTask, rules);
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

		} // handle the waits
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
				handleSequecneAndBranch(ts, sequence, taskEles, mergeTo,
						switchTo, rules, true);
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
			handleSequecneAndBranch(ts, sequence, taskEles, mergeTo, switchTo,
					rules, true);
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
			handleSequecneAndBranch(ts, sequence, taskEles, mergeTo, switchTo,
					rules, true);
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

		List<String> tmpList = new ArrayList<String>();
		for (String s : sequence.keySet()) {
			tmpList.add(s);
		}

		handleSequecneAndBranch(tmpList, sequence, taskEles, mergeTo, switchTo,
				rules, false);

		for (Element e : taskEles.values()) {
			mainSequence.add(e);
		}
		mainSequence.add(mainReply);
		bpel.add(mainSequence);
		doc.add(bpel);

	}

	private void handleSequecneAndBranch(List<String> keyList,
			Map<String, String> sequence, Map<String, Element> taskEles,
			Map<String, String> mergeTo, Map<String, List<String>> switchTo,
			Map<String, String> rules, boolean limited) {
		// Handle the sequence
		for (String keyTask1 : keyList) {
			handleSequence(sequence, keyTask1, taskEles, mergeTo, switchTo);
		}
		// Handle the branches
		if (limited) {
			for (String ketTask2 : keyList) {
				handleBranch(ketTask2, sequence, switchTo, taskEles, mergeTo,
						rules);
			}
		} else {
			for (String ketTask2 : switchTo.keySet()) {
				handleBranch(ketTask2, sequence, switchTo, taskEles, mergeTo,
						rules);
			}
		}
		// Handle the sequence again
		for (String keyTask3 : keyList) {
			handleSequence(sequence, keyTask3, taskEles, mergeTo, switchTo);
		}
	}

	private void handleSequence(Map<String, String> sequence, String keyTask,
			Map<String, Element> taskEles, Map<String, String> mergeTo,
			Map<String, List<String>> switchTo) {

		String nextTask = sequence.get(keyTask);
		if (nextTask != null && !nextTask.isEmpty()) {
			handleSequence(sequence, nextTask, taskEles, mergeTo, switchTo);
		} else {
			return;
		}

		if (nextTask != null) {
			Element curEle = taskEles.get(keyTask);
			String nextTaskName = nextTask;
			Element nextTaskEle = taskEles.get(nextTaskName);
			String merge = mergeTo.get(nextTaskName);

			// If the next node is a merge to node
			if (merge != null) {
				mergeTo.put(keyTask, merge);
				mergeTo.remove(nextTaskName);
			}
			// If the next node is a switch node
			if (switchTo.containsKey(nextTaskName)) {
				List<String> tos = switchTo.get(nextTaskName);
				if (tos != null) {
					switchTo.put(keyTask, tos);
					switchTo.remove(nextTaskName);
				}
			}

			// Make sure there exists
			if (curEle != null && nextTaskEle != null) {
				if (nextTaskEle.getName().equals("bpel:while")) {
					curEle.add(nextTaskEle);
				} else {
					@SuppressWarnings("unchecked")
					List<Element> nodes = nextTaskEle.elements();
					for (int i = 0; i < nodes.size(); ++i) {
						Element e = nodes.get(i);
						e.detach();
						curEle.add(e);
					}
				}
				taskEles.remove(nextTaskName);
			}
		}

	}

	private void handleBranch(String keyTask, Map<String, String> sequence,
			Map<String, List<String>> switchTo, Map<String, Element> taskEles,
			Map<String, String> mergeTo, Map<String, String> rules) {

		// For each switch to construct
		Element beforeIfEle = taskEles.get(keyTask);
		List<String> tos = switchTo.get(keyTask);
		if (beforeIfEle != null && tos != null) {

			String mergeToName = null;
			Element ifEle = null;
			boolean isFirstBranch = true;
			// For each branch, construct a if-elseIf-else branch
			for (String nextTaskName : tos) {
				// Recursive invoke
				if (switchTo.get(nextTaskName) != null
						&& switchTo.get(nextTaskName).size() > 0) {
					handleBranch(nextTaskName, sequence, switchTo, taskEles,
							mergeTo, rules);
				}
				// If the next node has a next node
				if (sequence.get(nextTaskName) != null) {
					handleSequence(sequence, nextTaskName, taskEles, mergeTo,
							switchTo);
				}

				mergeToName = mergeTo.get(nextTaskName);
				mergeTo.remove(nextTaskName);
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

				// Handle the mergeTo, reassign the sequence
				if (mergeToName != null) {
					sequence.put(keyTask, mergeToName);

				}
			}
		}
	}

	private Element createIfElement(Element beforeIfEle) {

		Element ifEle = new DOMElement("bpel:if");
		beforeIfEle.add(ifEle);
		return ifEle;
	}

	private Element createTaskElement(Document doc, String keyTask,
			Map<String, String> rules) {

		Element sequenceEle = new DOMElement("bpel:sequence");
		sequenceEle.addAttribute("name", keyTask + "_sequence");

		Element contextEle = new DOMElement("context");
		contextEle.addAttribute("name", keyTask + "_context");
		String context = rules.get(keyTask) == null ? "" : rules.get(keyTask);
		contextEle.addText(context);

		Element revEle = new DOMElement("bpel:recieve");
		revEle.addAttribute("name", keyTask + "_rev");

		Element assignEle1 = new DOMElement("bpel:assign");
		assignEle1.addAttribute("name", keyTask + "_assign" + "_1");
		
		Element invokeEle = new DOMElement("bpel:invoke");
		invokeEle.addAttribute("name", keyTask + "_invoke");
//		Element assignEle2 = new DOMElement("bpel:assign");
//		assignEle2.addAttribute("name", keyTask + "_assign" + "_2");

		sequenceEle.add(contextEle);
		sequenceEle.add(revEle);
		sequenceEle.add(assignEle1);
		sequenceEle.add(invokeEle);
		// sequenceEle.add(assignEle2);

		return sequenceEle;

	}

}
