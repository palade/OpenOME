package edu.toronto.cs.openome.conversion.convertor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.Collection;
import java.util.HashMap;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

import edu.stanford.smi.protege.exception.OntologyLoadException;
import edu.stanford.smi.protegex.owl.ProtegeOWL;
import edu.stanford.smi.protegex.owl.model.OWLIndividual;
import edu.stanford.smi.protegex.owl.model.OWLModel;
import edu.stanford.smi.protegex.owl.model.OWLNamedClass;
import edu.stanford.smi.protegex.owl.model.OWLOntology;
import edu.stanford.smi.protegex.owl.model.RDFProperty;
import edu.stanford.smi.protegex.owl.model.impl.DefaultOWLOntology;
import edu.stanford.smi.protegex.owl.model.triplestore.TripleStoreModel;
import edu.stanford.smi.protegex.owl.repository.Repository;
import edu.stanford.smi.protegex.owl.repository.RepositoryManager;
import edu.stanford.smi.protegex.owl.repository.impl.LocalFolderRepository;
import edu.stanford.smi.protegex.owl.writer.rdfxml.rdfwriter.OWLModelWriter;
import edu.toronto.cs.openome_model.Container;
import edu.toronto.cs.openome_model.Contribution;
import edu.toronto.cs.openome_model.Decomposition;
import edu.toronto.cs.openome_model.Dependency;
import edu.toronto.cs.openome_model.Intention;
import edu.toronto.cs.openome_model.Model;
import edu.toronto.cs.openome_model.impl.BreakContributionImpl;
import edu.toronto.cs.openome_model.impl.HelpContributionImpl;
import edu.toronto.cs.openome_model.impl.HurtContributionImpl;
import edu.toronto.cs.openome_model.impl.MakeContributionImpl;

/**
 * Class to convert Diagram Representation of the model to Q7 syntax.
 * 
 * @author showzeb
 * 
 */
@SuppressWarnings("restriction")
public class GoalModel_OWL {

	private edu.toronto.cs.openome_model.Model model;
	private String sourceFile = "";
	private String targetFile = "";
	private BufferedWriter writer = null;
	private URI uri;
	private String warning = "";
	private OWLModel grlMetaModel;
	private HashMap<Class, String> map;

	public GoalModel_OWL(String sourceFile, String targetFile) {
		this.sourceFile = sourceFile;
		this.targetFile = targetFile;
		initialize();
	}

	private void initMap() {
		map = new HashMap<Class, String>();
		map.put(edu.toronto.cs.openome_model.impl.SoftgoalImpl.class,
				"IStarSoftGoalElement");
		map.put(edu.toronto.cs.openome_model.impl.GoalImpl.class,
				"IStarGoalElement");
		map.put(edu.toronto.cs.openome_model.impl.TaskImpl.class,
				"IStarTaskElement");

		map.put(edu.toronto.cs.openome_model.impl.HelpContributionImpl.class,
				"IStarHelpContribution");
		map.put(edu.toronto.cs.openome_model.impl.HurtContributionImpl.class,
				"IStarHurtContribution");
		map.put(edu.toronto.cs.openome_model.impl.MakeContributionImpl.class,
				"IStarMakeContribution");
		map.put(edu.toronto.cs.openome_model.impl.OrDecompositionImpl.class,
				"IStarDecompositionLink");
		map.put(edu.toronto.cs.openome_model.impl.AgentImpl.class,
				"IStarAgentElement");
	}

	/**
	 * Initialize the setup, get the Model and Diagram from the source file.
	 * 
	 * @throws IOException
	 */
	private void initialize() {

		initMap();

		try {
			
			Repository repo = new LocalFolderRepository(new File("D:\\Graduate\\MetaModel"));
			Collection<java.net.URI> uris = repo.getOntologies();
			
			
			
			
			
			grlMetaModel = ProtegeOWL
					.createJenaOWLModelFromInputStream(new FileInputStream(
							new File(
									"D:\\Graduate\\MetaModel\\GRL.owl")));
			
//			owlModel.setWriterSettings(WriterSettings.);
//			grlMetaModel.
			
			

		} catch (OntologyLoadException e1) {
			e1.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
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
				// Creates target file
				createFile();

				for (Intention intention : model.getRoots()) {
					// if (!alreadyVisited.contains(intention))
					// startProcess(intention, "", false, true);
				}

				try {
					writer.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} else {
			// Creates target file
			createFile();

			EList<Intention> intentions = model.getAllIntentions();
			EList<Contribution> contributions = model.getContributions();
			EList<Decomposition> decompositions = model.getDecompositions();
			EList<Dependency> dependencies = model.getDependencies();
			EList<Container> containers = model.getContainers();

			for (Intention i : intentions) {
				String clsName = map.get(i.getClass());
				OWLNamedClass cls = grlMetaModel.getOWLNamedClass(clsName);
				String name = i.getName().replace(" ", "_");
				cls.createOWLIndividual(name);

			}
			for (Container c : containers) {

				String clsName = map.get(c.getClass());
				OWLNamedClass cls = grlMetaModel.getOWLNamedClass(clsName);
				OWLIndividual ind = cls.createOWLIndividual(c.getName()
						.replace(" ", "_"));

				EList<Intention> children = c.getIntentions();
				RDFProperty childProperty = grlMetaModel.getOWLProperty("children");
				for (Intention child : children) {
					OWLIndividual childInd = grlMetaModel.getOWLIndividual(child
							.getName().replace(" ", "_"));
					ind.addPropertyValue(childProperty, childInd);

				}
			}
			int count = 0;
			for (Contribution contribution : contributions) {
				count++;
				String clsType = map.get(contribution.getClass());
				OWLNamedClass cls = grlMetaModel.getOWLNamedClass(clsType);
				OWLIndividual ind = cls.createOWLIndividual(clsType
						+ "_unamed_" + count);
				OWLIndividual sourceInd = grlMetaModel
						.getOWLIndividual(contribution.getSource().getName()
								.replace(" ", "_"));
				OWLIndividual targetInd = grlMetaModel
						.getOWLIndividual(contribution.getTarget().getName()
								.replace(" ", "_"));
				ind.addPropertyValue(grlMetaModel.getOWLProperty("from"), sourceInd);
				ind.addPropertyValue(grlMetaModel.getOWLProperty("to"), targetInd);

			}

			String filePath = "D:\\ss.owl";
			FileOutputStream outFile;
			try {
				outFile = new FileOutputStream(filePath);
				Writer out = new OutputStreamWriter(outFile, "UTF-8");
				OWLModelWriter omw = new OWLModelWriter(grlMetaModel, grlMetaModel
						.getTripleStoreModel().getActiveTripleStore(), out);
				omw.write();
				out.close();

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

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
