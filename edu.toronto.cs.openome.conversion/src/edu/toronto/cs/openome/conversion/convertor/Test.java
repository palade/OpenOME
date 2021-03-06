package edu.toronto.cs.openome.conversion.convertor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;

import edu.stanford.smi.protege.exception.OntologyLoadException;
import edu.stanford.smi.protegex.owl.ProtegeOWL;
import edu.stanford.smi.protegex.owl.model.OWLModel;
import edu.toronto.cs.openome_model.Intention;
import edu.toronto.cs.openome_model.Model;

public class Test {

	public static edu.toronto.cs.openome_model.Model model;
	public static OWLModel owlModel;

	public static void main(String args[]) {
		try {
			owlModel = ProtegeOWL
					.createJenaOWLModelFromInputStream(new FileInputStream(
							new File(
									"D:/Graduate/workspace2/edu.toronto.cs.openome.conversion/OWL/GRL.owl")));

			Resource resource = new XMIResourceImpl();
			resource.unload();
			resource.setURI(URI
					.createFileURI("D:\\Graduate\\workspace2\\edu.toronto.cs.openome.conversion\\OWL\\AppModel.xml"));
			resource.load(null);
			for (Object o : resource.getContents()) {
				if (o instanceof Model) {
					model = (Model) o;
				}
			}
			
			EList<Intention> intentions = model.getAllIntentions();
			for(Intention i : intentions){
				System.out.print(i.getName()+"  ");
				System.out.println(i.getClass().getName());
			}

		} catch (FileNotFoundException | OntologyLoadException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
