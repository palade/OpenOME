package org.bflow.toolbox.interchange.store;

import java.util.ArrayList;
import java.util.List;

import org.bflow.toolbox.interchange.model.ImportDescription;

/**
 * Provides a store for import scripts.
 * @author Arian Storch
 * @since 13/07/11
 *
 */
public class ImportscriptStore {

	/**
	 * static collection instance
	 */
	private static List<ImportDescription> depository = new ArrayList<ImportDescription>();
	
	/**
	 * Registers the import description to the store.
	 * @param exp import description
	 */
	public static void register(ImportDescription exp) {
		depository.add(exp);
	}
	
	/**
	 * Returns the depository.
	 * @return depository
	 */
	public static List<ImportDescription> getDepository() {
		return depository;
	}
	
	/**
	 * Returns the import description fitting to the given name.
	 * @param name name of the export description
	 * @return import description or null
	 */
	public static ImportDescription getExportDescription(String name) {
		for (ImportDescription exd : depository)
			if (exd.getName().equalsIgnoreCase(name))
				return exd;

		return null;
	}
	
}
