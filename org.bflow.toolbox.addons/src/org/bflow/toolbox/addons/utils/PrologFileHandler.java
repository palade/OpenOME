package org.bflow.toolbox.addons.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Vector;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Bundle;

import edu.toronto.cs.openome.core.action.ExtensionReader;

/**
 * Following class extracts the prolog file according to OS and returns there directory
 * @author showzeb
 *
 */
public class PrologFileHandler extends ExtensionReader{

	Vector<IConfigurationElement> files = null;
	String path;
	
	/**
	 * Extracts the file and returns the path to the folder.
	 */
	public String extract() {
		String osName = System.getProperty("os.name");
		String fileType = osName.contains("Linux") ? "pl" : (osName.contains("Mac") ? "swipl" : "pl.zip");
		
		try {
			files = new Vector<IConfigurationElement>();
			URL installURL = Platform.getConfigurationLocation().getURL();
			path = FileLocator.toFileURL(installURL).getPath() + "Prolog/";
			if (!new File(path).exists() && new File(path).mkdir()) {
				readExtensions("org.bflow.toolbox.addons.prolog", "prolog", files);
				for (IConfigurationElement file: files) {
					String pluginname = file.getDeclaringExtension().getNamespaceIdentifier();
					Bundle bundle = Platform.getBundle(pluginname);
					String filename = file.getAttribute("file");
					if (filename.equals(fileType) || filename.equals("script.sh")) {
						writeToFile(pluginname, filename,bundle);
						
						if (filename.equals("pl.zip")) {
							path = getZipFiles(path + "pl.zip");
						}
					}
				
				}
				if (fileType.equals("pl") || fileType.equals("swipl"))	{
					Process p = new ProcessBuilder("sh", path + "script.sh", path).start();				
				}
			}
		} catch (Exception e) {
			
		}
		
		return path;
	}
	
	private void writeToFile(String pluginname, String filename, Bundle bundle) {
		try {
			String name = pluginname + "/" + filename;
			InputStream stream = FileLocator.openStream(bundle, new Path("Prolog/" + filename), false);
			File dst = new File(path + filename);
		    OutputStream out = new FileOutputStream(dst);
	
		    // Transfer bytes from in to out
		    byte[] buf = new byte[1024];
		    int len;
		    while ((len = stream.read(buf)) > 0) {
		        out.write(buf, 0, len);
		    }
		    stream.close();
		    out.close();
			stream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private String getZipFiles(String filename) {
		try {
			String destinationname = path.replaceFirst("/", "");
			byte[] buf = new byte[1024];
			ZipInputStream zipinputstream = null;
			ZipEntry zipentry;
			zipinputstream = new ZipInputStream(new FileInputStream(filename));

			zipentry = zipinputstream.getNextEntry();
			while (zipentry != null) {
				// for each entry to be extracted
				String entryName = zipentry.getName();
				int n;
				FileOutputStream fileoutputstream;
				File newFile = new File(entryName);
				String directory = newFile.getParent();

				if (newFile.isDirectory() && newFile.exists()) {
					break;
				} else if (directory != null) {
					File parent = new File(destinationname + directory);
					if (!parent.exists()) {
						parent.mkdirs();
					}
				}
				fileoutputstream = new FileOutputStream(destinationname
						+ entryName);

				while ((n = zipinputstream.read(buf, 0, 1024)) > -1)
					fileoutputstream.write(buf, 0, n);

				fileoutputstream.close();
				zipinputstream.closeEntry();
				zipentry = zipinputstream.getNextEntry();

			}// while

			zipinputstream.close();
			return destinationname;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
