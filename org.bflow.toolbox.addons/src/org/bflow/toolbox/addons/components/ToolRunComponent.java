package org.bflow.toolbox.addons.components;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.commons.io.FilenameUtils;
import org.bflow.toolbox.addons.core.exceptions.ComponentException;
import org.bflow.toolbox.addons.core.model.IComponent;
import org.bflow.toolbox.addons.core.model.Protocol;
import org.bflow.toolbox.addons.interfaces.IToolRunComponent;
import org.bflow.toolbox.addons.protocols.Standardprotocol;
import org.bflow.toolbox.addons.store.ToolStore;
import org.bflow.toolbox.addons.utils.TemporaryFileServer;
import org.bflow.toolbox.addons.utils.ToolDescriptor;
import org.eclipse.core.resources.IFile;

/**
 * <p>
 * Defines a tool run component which implements {@link IToolRunComponent} to
 * run a tool within the protocol.
 * </p>
 * <p>
 * The tool runs inside an externel process and finishes when the process exits.
 * <br/>
 * If there are parameters for the tool, they are replace before the tool is
 * beeing started.
 * </p>
 * <p>
 * Look at the documentation for futher details like the mapped parameters.
 * </p>
 * <p>
 * A file object containing the source file for the tool is beeing expected and
 * used to replace the "$file" parameter.
 * </p>
 * 
 * @author Arian Storch
 * @since 25/04/10
 * @version 19/11/10
 * 
 */
public class ToolRunComponent implements IToolRunComponent {
	private ToolDescriptor tool;

	private File srcFile;

	private File tgtFile;

	private boolean finished = false;
	private boolean collectingConsoleMessages = true;
	
	private File tmpPath;
	
	private StringBuffer shellStream;

	/**
	 * Constructor.
	 */
	public ToolRunComponent() {
		super();
	}

	@Override
	public void finish() {
	}

	@Override
	public String getDescription(String abbreviation) {
		if (abbreviation.equalsIgnoreCase("de")) {
			String str = "Startet ein externes Programm und überwacht dessen Ausgabe nach Meldungen, die für bflow* bestimmt sind.";

			return str;
		}

		String str = "Starts an external program and logs its output.";

		return str;
	}

	@Override
	public boolean hasFinished() {
		return finished;
	}

	@Override
	public void init() {
		finished = false;
		
		String tmpDir = System.getProperty("java.io.tmpdir");
		String stmpPath = tmpDir+"/bflowtemp/";
		
		tmpPath = new File(stmpPath);
		tmpPath.mkdir();
		
		shellStream = new StringBuffer(1024);
		
	}

	@Override
	public void invoke() throws ComponentException {
		try {

			String command = "";

			if (tool.getPath().endsWith(".jar"))
				command += "java -jar ";

			command += tool.getPath() + " ";

			command += tool.getParam();

			command = adjustFileCommands(command);
			command = command.replace("$addon_temp", tmpPath.getAbsolutePath());
			command = command.replace("$install_dir", FilenameUtils.getFullPath(tool.getPath()));
			command = command.replace("$name", FilenameUtils.getName(srcFile.getAbsolutePath()));

			// System.err.println("[ToolRunComponent] "+command);

			try {
				Process p = Runtime.getRuntime().exec(command);
				
				BufferedReader br = new BufferedReader(new InputStreamReader(p
						.getInputStream()));
				
				BufferedReader errReader = new BufferedReader(new InputStreamReader(
						p.getErrorStream()));
													
				/*
				 * the protocol must wait until the process has been terminated
				 */
				boolean running = true;

				while (running) {
					try {
						readInputStream(br); // dummy, needed so the process will terminate
						int exitValue = p.exitValue(); // terminated?
						
						if(exitValue < 0) {
							readInputStream(errReader);
							running = false;
							throw new ComponentException("Process ended usual! Look into the log file.");
						}
						
						readInputStream(br);
						running = false;
					} catch (IllegalThreadStateException ex) {
						continue;
					} finally {
						readInputStream(br);
					}
				}
			} catch (IOException ex) {
				throw new ComponentException("Could not execute tool", ex);
			}
		} catch (Exception ex) {
			throw new ComponentException(ex);
		} finally {
			finished = true;
			Standardprotocol.getLogger().info(shellStream.toString());
		}
	}

	/**
	 * Reads the input stream of the executed process.
	 * 
	 * @param br
	 *            buffered reader
	 */
	private void readInputStream(BufferedReader br) {
		//CharBuffer charBuffer = CharBuffer.allocate(1024);
		
		int i = -1;
		
		try {
			while(br.ready() && (i = br.read()) != -1)
				shellStream.append((char)i);
		} catch (IOException e) {
			Standardprotocol.getLogger().error("read input stream failed", e);
		}
	}
	
	private String adjustFileCommands(String command) {		
		command = command.replace("$source", srcFile.getAbsolutePath());
		
		command = command.replace("$wsSource", 
				((IFile)Standardprotocol.getRunning().getSource()).getLocation().toOSString());
		
		int pos = -1;
		int v = 1;
		
		while((pos = command.indexOf("$file"+v)) != -1) {
			String param = command.substring(pos, pos+6);
			
			File f = TemporaryFileServer.getFileReference(param);
			
			command = command.replace(param, (f == null ? "" : f.getAbsolutePath()));
			v++;
		}
		
		return command;
	}

	@Override
	public void transformInput(Object inputSource) throws ComponentException {
		if(inputSource == null)
			throw new ComponentException("Quelle ist null");
		
		if (inputSource.getClass() != File.class)
			throw new ComponentException(
					"input source has not exspected format!");

		srcFile = (File) inputSource;
	}

	@Override
	public Object transformOutput() throws ComponentException {
		if (collectingConsoleMessages)
			return shellStream;

		return tgtFile;
	}

	@Override
	public void setProtocol(Protocol protocol) {
	}

	@Override
	public void setToolDescriptor(ToolDescriptor td) {
		this.tool = td;
	}

	@Override
	public boolean isValid() {
		if (tool != null && tool.getPath() != null && !tool.getPath().isEmpty())
			return true;

		return false;
	}

	@Override
	public String getDisplayName() {
		return "Tool run";
	}

	@Override
	public boolean canLinkWith(IComponent component) {
		if (component instanceof DiagramExportComponent)
			return true;

		if (component instanceof ToolAdapterComponent)
			return true;

		return false;
	}

	@Override
	public boolean hasParams() {
		return true;
	}

	@Override
	public void setParams(String param) {
		ToolDescriptor td = ToolStore.getTool(param);
		this.tool = td;
	}

}
