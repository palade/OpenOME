package org.bflow.toolbox.interchange;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.bflow.toolbox.interchange.model.ExportDescription;
import org.bflow.toolbox.interchange.model.ImportDescription;
import org.bflow.toolbox.interchange.store.ExportscriptStore;
import org.bflow.toolbox.interchange.store.ImportscriptStore;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IContributor;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class AddonsInterchangePlugin extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "org.bflow.toolbox.export.xslt";

	// The shared instance
	private static AddonsInterchangePlugin plugin;

	/**
	 * The constructor
	 */
	public AddonsInterchangePlugin() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext
	 * )
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;

		registerExportscripts();
		registerImportscripts();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext
	 * )
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 * 
	 * @return the shared instance
	 */
	public static AddonsInterchangePlugin getInstance() {
		return plugin;
	}

	/**
	 * Registers all export scripts defined by using the extension point or
	 * stored on local drive.
	 * 
	 * @throws IOException
	 */
	private void registerExportscripts() throws IOException {
		IConfigurationElement[] config = Platform.getExtensionRegistry()
				.getConfigurationElementsFor(EXTENSION_ID_ADDON_EXPORTSCRIPT);

		for (IConfigurationElement element : config) {
			String file = element.getAttribute("File");
			IContributor con = element.getContributor();
			Bundle bundle = Platform.getBundle(con.getName());
			InputStream is = bundle.getEntry(file).openStream();

			ExportDescription exp = new ExportDescription(bundle, is, file);
			exp.parseDescription();

			ExportscriptStore.register(exp);
		}

		// scripts on local drive
		IWorkspaceRoot wsRoot = ResourcesPlugin.getWorkspace().getRoot();

		IPath wsRootPath = wsRoot.getLocation();

		Path exportPath = (Path) wsRootPath.append(".export/");
		File exportDir = exportPath.toFile();

		if (exportDir.exists()) // es wurden manuell Skripte hinzugef�gt
		{
			File files[] = exportDir.listFiles(new FileFilter() {

				@Override
				public boolean accept(File arg0) {
					if (arg0.isDirectory())
						return false;

					String filename = arg0.getName();

					if (filename.contains(".exd."))
						return true;

					return false;
				}
			});

			for (File f : files) {
				FileInputStream fis = new FileInputStream(f.getAbsoluteFile());
				
				ExportDescription exp = new ExportDescription(null, fis, f.getAbsolutePath());
				exp.parseDescription();
				
				ExportscriptStore.register(exp);
			}
		}
		
		//Add export description for Q7 export.
		ExportDescription q7Exp = new ExportDescription(null,null,null);
		q7Exp.setName("Q7");
		q7Exp.setDescription("Exports an openOME diagram as Q7 file");
		q7Exp.setFileExtension("q7");
		ExportDescription owlExp = new ExportDescription(null,null,null);
		owlExp.setName("OWL");
		owlExp.setDescription("Exports an openOME diagram as OWL file");
		owlExp.setFileExtension("owl");
		ExportDescription bpelExp = new ExportDescription(null,null,null);
		bpelExp.setName("bpel");
		bpelExp.setDescription("Exports an openOME diagram as bpel file");
		bpelExp.setFileExtension("bpel");
		
		ExportscriptStore.register(q7Exp);
		ExportscriptStore.register(owlExp);
		ExportscriptStore.register(bpelExp);
	}

	private void registerImportscripts() throws IOException {
		IConfigurationElement[] config = Platform.getExtensionRegistry()
				.getConfigurationElementsFor(EXTENSION_ID_ADDON_IMPORTSCRIPT);

		for (IConfigurationElement element : config) {
			String file = element.getAttribute("File");
			IContributor con = element.getContributor();
			Bundle bundle = Platform.getBundle(con.getName());
			InputStream is = bundle.getEntry(file).openStream();

			ImportDescription imp = new ImportDescription(bundle, is, file);
			imp.parseDescription();

			ImportscriptStore.register(imp);
		}
		
		// scripts on local drive
		IWorkspaceRoot wsRoot = ResourcesPlugin.getWorkspace().getRoot();

		IPath wsRootPath = wsRoot.getLocation();

		Path importPath = (Path) wsRootPath.append(".import/");
		File importDir = importPath.toFile();

		if (importDir.exists()) // es wurden manuell Skripte hinzugef�gt
		{
			File files[] = importDir.listFiles(new FileFilter() {

				@Override
				public boolean accept(File arg0) {
					if (arg0.isDirectory())
						return false;

					String filename = arg0.getName();

					if (filename.contains(".ixd."))
						return true;

					return false;
				}
			});

			for (File f : files) {
				FileInputStream fis = new FileInputStream(f.getAbsoluteFile());
				
				ImportDescription imp = new ImportDescription(null, fis, f
						.getAbsolutePath());
				imp.parseDescription();
				
				ImportscriptStore.register(imp);
			}
		}
	}

	/**
	 * Extension point id for add-ons export scripts.
	 */
	public static final String EXTENSION_ID_ADDON_EXPORTSCRIPT = "org.bflow.toolbox.interchange.exportscript";

	/**
	 * Extension point id for add-ons import scripts.
	 */
	public static final String EXTENSION_ID_ADDON_IMPORTSCRIPT = "org.bflow.toolbox.interchange.importscript";

}
