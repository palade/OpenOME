package edu.toronto.cs.openome.conversion.convertor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import javax.net.ssl.SSLEngineResult.Status;
import javax.xml.parsers.ParserConfigurationException;

import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.gmf.internal.common.Activator;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.xml.sax.SAXException;

import edu.stanford.smi.protege.exception.OntologyLoadException;
import edu.stanford.smi.protege.util.URIUtilities;
import edu.stanford.smi.protegex.owl.ProtegeOWL;
import edu.stanford.smi.protegex.owl.model.OWLIndividual;
import edu.stanford.smi.protegex.owl.model.OWLModel;
import edu.stanford.smi.protegex.owl.model.OWLNamedClass;
import edu.stanford.smi.protegex.owl.model.RDFProperty;
import edu.stanford.smi.protegex.owl.model.util.ImportHelper;
import edu.stanford.smi.protegex.owl.writer.rdfxml.rdfwriter.OWLModelWriter;
import edu.toronto.cs.openome_model.Association;
import edu.toronto.cs.openome_model.Container;
import edu.toronto.cs.openome_model.Contribution;
import edu.toronto.cs.openome_model.Decomposition;
import edu.toronto.cs.openome_model.Dependable;
import edu.toronto.cs.openome_model.Dependency;
import edu.toronto.cs.openome_model.Intention;
import edu.toronto.cs.openome_model.Model;
import org.eclipse.core.runtime.jobs.*;

public class GRL2OWLConvertor extends AbstractConvertor {
	private String owlFilePath;
	private Model model;
	private OWLModel grlMetaModel;
	private static java.net.URI metaModelURI = URIUtilities
			.createURI("D:/Graduate/MetaModel/GRL.owl");
	private static String namespace = "http://www.owl-ontologies.com/GRL.owl#";

	public String getOWLFilePath() {
		return this.owlFilePath;
	}

	/**
	 * A map class which contains the Goal related classes and the corresponding
	 * names
	 * 
	 * @author ZackChen
	 * 
	 */
	@SuppressWarnings("rawtypes")
	private static class GoalClassAndNameMap {

		private static HashMap<Class, String> map;

		public static HashMap<Class, String> getMap() {
			if (map == null) {
				initMap();
			}
			return map;
		}

		@SuppressWarnings("restriction")
		private static void initMap() {
			map = new HashMap<Class, String>();
			// Intentions
			map.put(edu.toronto.cs.openome_model.impl.SoftgoalImpl.class,
					"IStarSoftGoalElement");
			map.put(edu.toronto.cs.openome_model.impl.GoalImpl.class,
					"IStarGoalElement");
			map.put(edu.toronto.cs.openome_model.impl.TaskImpl.class,
					"IStarTaskElement");
			map.put(edu.toronto.cs.openome_model.impl.ResourceImpl.class,
					"IStarResourceElement");
			// map.put(edu.toronto.cs.openome_model.impl.TaskImpl.class,
			// "IStarClaimElement");

			// Contribution
			map.put(edu.toronto.cs.openome_model.impl.HelpContributionImpl.class,
					"IStarHelpContribution");
			map.put(edu.toronto.cs.openome_model.impl.HurtContributionImpl.class,
					"IStarHurtContribution");
			map.put(edu.toronto.cs.openome_model.impl.MakeContributionImpl.class,
					"IStarMakeContribution");
			map.put(edu.toronto.cs.openome_model.impl.AndContributionImpl.class,
					"IStarAndContribution");
			map.put(edu.toronto.cs.openome_model.impl.BreakContributionImpl.class,
					"IStarBreakContribution");
			map.put(edu.toronto.cs.openome_model.impl.OrContributionImpl.class,
					"IStarOrContribution");
			map.put(edu.toronto.cs.openome_model.impl.UnknownContributionImpl.class,
					"IStarUnknownContribution");
			map.put(edu.toronto.cs.openome_model.impl.SomeMinusContributionImpl.class,
					"IStarSomeNegativeContribution");
			map.put(edu.toronto.cs.openome_model.impl.SomePlusContributionImpl.class,
					"IStarSomePositiveContribution");
			// map.put(edu.toronto.cs.openome_model.impl.EqualContributionImpl.class,
			// "IStarEqualContribution");

			// Link
			map.put(edu.toronto.cs.openome_model.impl.AndDecompositionImpl.class,
					"IStarDecompositionLink");
			map.put(edu.toronto.cs.openome_model.impl.OrDecompositionImpl.class,
					"IStarMeansEndsLink");

			// Dependency
			map.put(edu.toronto.cs.openome_model.impl.DependencyImpl.class,
					"IStarDependencyLink");

			// Association
			map.put(edu.toronto.cs.openome_model.impl.CoversAssociationImpl.class,
					"IStarCoversLink");
			map.put(edu.toronto.cs.openome_model.impl.INSAssociationImpl.class,
					"IStarINSLink");
			map.put(edu.toronto.cs.openome_model.impl.IsAAssociationImpl.class,
					"IStarISALink");
			map.put(edu.toronto.cs.openome_model.impl.PlaysAssociationImpl.class,
					"IStarPlaysLink");
			map.put(edu.toronto.cs.openome_model.impl.OccupiesAssociationImpl.class,
					"IStarOccupiesLink");
			map.put(edu.toronto.cs.openome_model.impl.IsPartOfAssociationImpl.class,
					"IStarPartsLink");

			// Container
			map.put(edu.toronto.cs.openome_model.impl.AgentImpl.class,
					"IStarAgentElement");
			map.put(edu.toronto.cs.openome_model.impl.ActorImpl.class,
					"IStarAspectElement");
			map.put(edu.toronto.cs.openome_model.impl.PositionImpl.class,
					"IStarPositionElement");
			map.put(edu.toronto.cs.openome_model.impl.RoleImpl.class,
					"IStarRoleElement");

		}
	}

	private void getGRLMetaModel() {
		try {
			grlMetaModel = ProtegeOWL.createJenaOWLModel();
			ImportHelper importHelper = new ImportHelper(grlMetaModel);
			importHelper.addImport(metaModelURI);
			importHelper.importOntologies();

		} catch (OntologyLoadException e) {
			e.printStackTrace();
		}

	}

	public String convert() {
		String filePath = null;
		try {
			model = getModelImpl();

			getGRLMetaModel();
			toOWL();
			filePath = toFile();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return filePath;
	}

	private String toFile() throws FileNotFoundException, IOException {
		URI modelURI = getModelURI();
		IWorkspace w = ResourcesPlugin.getWorkspace();

		String workspacePath = w.getRoot().getLocationURI().getPath();
		String modelFilePath = modelURI.path().replaceFirst("/resource", "");
		int index = modelFilePath.lastIndexOf(modelURI.fileExtension());
		String owlFilePath = modelFilePath.substring(0, index) + "owl";

		String filePath = workspacePath + owlFilePath;
		FileOutputStream outFile;
		try {
			outFile = new FileOutputStream(filePath);
			Writer out = new OutputStreamWriter(outFile, "UTF-8");
			OWLModelWriter omw = new OWLModelWriter(grlMetaModel, grlMetaModel
					.getTripleStoreModel().getActiveTripleStore(), out);
			omw.write();
			out.close();

			w.getRoot().refreshLocal(
					org.eclipse.core.resources.IResource.DEPTH_INFINITE, null);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (CoreException e) {
			e.printStackTrace();
		}

		return owlFilePath;
	}

	/**
	 * To BPEL file
	 * 
	 * @param tasks
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	private void toOWL() throws ParserConfigurationException, SAXException,
			IOException {

		EList<Intention> intentions = model.getAllIntentions();
		EList<Contribution> contributions = model.getContributions();
		EList<Decomposition> decompositions = model.getDecompositions();
		EList<Dependency> dependencies = model.getDependencies();
		EList<Container> containers = model.getContainers();
		EList<Association> associations = model.getAssociations();

		for (Intention i : intentions) {
			String clsName = GoalClassAndNameMap.getMap().get(i.getClass());
			OWLNamedClass cls = grlMetaModel.getOWLNamedClass(namespace
					+ clsName);
			String name = i.getName().replace(" ", "_");
			cls.createOWLIndividual(name);

		}

		for (Container c : containers) {

			String clsName = GoalClassAndNameMap.getMap().get(c.getClass());
			OWLNamedClass cls = grlMetaModel.getOWLNamedClass(namespace
					+ clsName);
			OWLIndividual ind = cls.createOWLIndividual(c.getName().replace(
					" ", "_"));

			EList<Intention> children = c.getIntentions();
			RDFProperty childProperty = grlMetaModel.getOWLProperty(namespace
					+ "children");
			for (Intention child : children) {
				OWLIndividual childInd = grlMetaModel.getOWLIndividual(child
						.getName().replace(" ", "_"));
				ind.addPropertyValue(childProperty, childInd);

			}
		}

		int count = 0;
		for (Contribution contribution : contributions) {
			String clsType = GoalClassAndNameMap.getMap().get(
					contribution.getClass());
			OWLNamedClass cls = grlMetaModel.getOWLNamedClass(namespace
					+ clsType);
			OWLIndividual ind = cls.createOWLIndividual(clsType + "_unamed_"
					+ count++);
			OWLIndividual sourceInd = grlMetaModel
					.getOWLIndividual(contribution.getSource().getName()
							.replace(" ", "_"));
			OWLIndividual targetInd = grlMetaModel
					.getOWLIndividual(contribution.getTarget().getName()
							.replace(" ", "_"));
			ind.addPropertyValue(
					grlMetaModel.getOWLProperty(namespace + "from"), sourceInd);
			ind.addPropertyValue(grlMetaModel.getOWLProperty(namespace + "to"),
					targetInd);

		}

		count = 0;
		for (Decomposition decom : decompositions) {
			String clsType = GoalClassAndNameMap.getMap().get(decom.getClass());
			OWLNamedClass cls = grlMetaModel.getOWLNamedClass(namespace
					+ clsType);
			OWLIndividual ind = cls.createOWLIndividual(clsType + "_unamed_"
					+ count++);
			OWLIndividual sourceInd = grlMetaModel.getOWLIndividual(decom
					.getSource().getName().replace(" ", "_"));
			OWLIndividual targetInd = grlMetaModel.getOWLIndividual(decom
					.getTarget().getName().replace(" ", "_"));
			ind.addPropertyValue(
					grlMetaModel.getOWLProperty(namespace + "from"), sourceInd);
			ind.addPropertyValue(grlMetaModel.getOWLProperty(namespace + "to"),
					targetInd);

		}

		count = 0;
		for (Association ass : associations) {
			String clsType = GoalClassAndNameMap.getMap().get(ass.getClass());
			OWLNamedClass cls = grlMetaModel.getOWLNamedClass(namespace
					+ clsType);
			OWLIndividual ind = cls.createOWLIndividual(clsType + "_unamed_"
					+ count++);
			OWLIndividual sourceInd = grlMetaModel.getOWLIndividual(ass
					.getSource().getName().replace(" ", "_"));
			OWLIndividual targetInd = grlMetaModel.getOWLIndividual(ass
					.getTarget().getName().replace(" ", "_"));
			ind.addPropertyValue(
					grlMetaModel.getOWLProperty(namespace + "from"), sourceInd);
			ind.addPropertyValue(grlMetaModel.getOWLProperty(namespace + "to"),
					targetInd);

		}

		count = 0;
		for (Dependency depend : dependencies) {
			String clsType = GoalClassAndNameMap.getMap()
					.get(depend.getClass());
			OWLNamedClass cls = grlMetaModel.getOWLNamedClass(namespace
					+ clsType);
			OWLIndividual ind = cls.createOWLIndividual(clsType + "_unamed_"
					+ count++);

			Dependable depFrom = depend.getDependencyFrom();
			Dependable depTo = depend.getDependencyTo();
			OWLIndividual sourceInd;
			OWLIndividual targetInd;

			if (depFrom instanceof Intention) {
				Intention intentionFrom = (Intention) depFrom;
				targetInd = grlMetaModel.getOWLIndividual(intentionFrom
						.getName().replace(" ", "_"));
			} else {
				Container conFrom = (Container) depFrom;
				targetInd = grlMetaModel.getOWLIndividual(conFrom.getName()
						.replace(" ", "_"));
			}

			if (depTo instanceof Intention) {
				Intention intentionTo = (Intention) depTo;
				sourceInd = grlMetaModel.getOWLIndividual(intentionTo.getName()
						.replace(" ", "_"));
			} else {
				Container conTo = (Container) depTo;
				sourceInd = grlMetaModel.getOWLIndividual(conTo.getName()
						.replace(" ", "_"));
			}

			ind.addPropertyValue(
					grlMetaModel.getOWLProperty(namespace + "from"), sourceInd);
			ind.addPropertyValue(grlMetaModel.getOWLProperty(namespace + "to"),
					targetInd);

		}

	}

}
