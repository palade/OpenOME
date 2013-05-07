package org.bflow.toolbox.interchange.store;

import java.util.ArrayList;
import java.util.List;

import org.bflow.toolbox.interchange.model.ExportDescription;

/**
 * Provides a store for export scripts.
 * @author Arian Storch
 * @since 07/06/11
 *
 */
public class ExportscriptStore {
	
	/**
	 * static collection instance
	 */
	private static List<ExportDescription> depository = new ArrayList<ExportDescription>();
	
	/**
	 * Registers the export description to the store.
	 * @param exp exprot description
	 */
	public static void register(ExportDescription exp) {
		depository.add(exp);
	}
	
	/**
	 * Returns the depository.
	 * @return depository
	 */
	public static List<ExportDescription> getDepository() {
		return depository;
	}
	
	/**
	 * Returns the export description fitting to the given name.
	 * @param name name of the export description
	 * @return export description or null
	 */
	public static ExportDescription getExportDescription(String name) {
		for (ExportDescription exd : depository)
			if (exd.getName().equalsIgnoreCase(name))
				return exd;

		return null;
	}

}
