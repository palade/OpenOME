package org.bflow.toolbox.addons.components;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Vector;

import org.apache.commons.io.FileUtils;
import org.bflow.toolbox.addons.core.exceptions.ComponentException;
import org.bflow.toolbox.addons.core.model.IComponent;
import org.bflow.toolbox.addons.interfaces.IPrologRunComponent;
import org.bflow.toolbox.addons.protocols.Standardprotocol;
import org.bflow.toolbox.addons.store.PrologAdditionStore;
import org.bflow.toolbox.addons.store.ToolStore;
import org.bflow.toolbox.addons.utils.PrologFileHandler;
import org.bflow.toolbox.addons.utils.PrologListener;
import org.bflow.toolbox.addons.utils.ToolDescriptor;
import org.bflow.toolbox.addons.validation.Rule;
import org.bflow.toolbox.addons.validation.ValidationService;
import org.bflow.toolbox.i18n.MessageProvider;
import org.eclipse.jface.bindings.keys.KeyStroke;
import org.eclipse.swt.SWT;

import com.declarativa.interprolog.SWISubprocessEngine;
import com.declarativa.interprolog.SubprocessEngine;

/**
 * Implements the {@link IPrologRunComponent} for running the swi-prolog.
 * 
 * @author Arian Storch
 * @since 24/06/10
 * @version 03/11/10
 */
public class PrologRunComponent implements IPrologRunComponent {

	private String toolParam;

	private File fDiagram;
	private File facts = null;

	private SubprocessEngine engine;
	private PrologListener listener;

	private boolean finished = false;

	private List<Rule> rules;
	private Vector<String> params;

	private StringBuffer simpleStream;
	
	private String startCommand = null;
	private String endCommand = null;

	private boolean internal = false;

	/**
	 * Default constructor.
	 */
	public PrologRunComponent() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.bflow.toolbox.mitamm.components.IPrologRunComponent#finish()
	 */
	@Override
	public void finish() {
		if (facts != null && internal)
			facts.deleteOnExit();

		this.engine.shutdown();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.bflow.toolbox.mitamm.components.IPrologRunComponent#getDescription
	 * (java.lang.String)
	 */
	@Override
	public String getDescription(String abbreviation) {

		if (abbreviation.equalsIgnoreCase("de")) {
			String str = "Startet den SWI-Prolog-Interpreter. Dieser muss zuvor installiert und bei bflow* registriert sein!";

			return str;
		}

		String str = "Starts the swi prolog interpreter. It must be installed and registered within bflow* before!";

		return str;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.bflow.toolbox.mitamm.components.IPrologRunComponent#hasFinished()
	 */
	@Override
	public boolean hasFinished() {
		return finished;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.bflow.toolbox.mitamm.components.IPrologRunComponent#init()
	 */
	@Override
	public void init() {
		String osName = System.getProperty("os.name");
		String fileName = osName.contains("Linux") ? "pl" : (osName.contains("Mac") ? "swipl" : "bin\\plcon.exe");
		
		this.engine = new SWISubprocessEngine( (new PrologFileHandler()).extract() + fileName, false);
		
		this.simpleStream = new StringBuffer(1024);

		this.params = new Vector<String>();

		String params[] = this.toolParam.split(" ");

		/*
		 * Parameterauflösung
		 */
		for (String str : params) {
			/*
			 * pl
			 */
			if (str.contains("-pl:")) {
				String value = str.substring(4); 
				
				URL url = PrologAdditionStore.getURL(value);
				
				if(url != null) {
					File prologBaseFile;

					try {
						prologBaseFile = File.createTempFile("epc2009", ".pl");
						prologBaseFile.deleteOnExit();
						FileUtils.copyURLToFile(url, prologBaseFile);

						facts = prologBaseFile;

						internal = true;
					} catch (IOException ex) {
						ex.printStackTrace();
					}
				} else {
					facts = new File(value);

					if (!facts.exists())
						facts = null;
				}
			}

			/*
			 * p
			 */
			if (str.contains("-p:")) {
				String value = str.substring(3);

				if (value.equalsIgnoreCase("$SETUP") || value.equalsIgnoreCase("$DEFAULT") ||
						value.equalsIgnoreCase("$ALL")) {
					
					String abbr = MessageProvider.getActiveLanguageAbbreviation();
					
					int type = ValidationService.TYPE_ALL;
					
					if(value.equalsIgnoreCase("$SETUP"))
						type = ValidationService.TYPE_SETUP;
					
					if(value.equalsIgnoreCase("$DEFAULT"))
						type = ValidationService.TYPE_DEFAULT;
					
					/*
					 * collecting rules that shall be used
					 */
					rules = ValidationService.getInstance().getRulesByType(abbr, type);

					internal = true;
				} else {
					this.params = new Vector<String>();

					for (String p : str.split(","))
						this.params.add(p);
				}

			}
			
			/*
			 * s
			 */
			if(str.contains("-s:")) {
				String value = str.substring(3);
				
				if(!value.isEmpty())
					this.startCommand = value;
			}
			
			/*
			 * e
			 */
			if(str.contains("-e:")) {
				String value = str.substring(3);
				
				if(!value.isEmpty())
					this.endCommand = value;
			}
		}
		
		// start and end command
		if(startCommand == null)
			startCommand = "addon_query";
		
		if(endCommand == null)
			endCommand = "#query_end#";
		
		this.listener = new PrologListener(simpleStream, endCommand);

		this.engine.addPrologOutputListener(listener);
		this.engine.addPrologStdoutListener(listener);
		
		finished = false;
	}

	@Override
	public void invoke() throws ComponentException {
		try {
		
		if (facts == null)
			throw new ComponentException(
					"There is no prolog program set! Look at the parameter for the toolchain!");

		try {
			String factsStream = FileUtils.readFileToString(facts);
			
			if(!factsStream.contains(startCommand))
				throw new ComponentException("Start command does not exist!");
			
			if(!factsStream.contains(endCommand))
				throw new ComponentException("End command does not exist!");
			
		} catch (IOException e1) {
			throw new ComponentException(e1);
		}

		this.engine.consultAbsolute(facts);
		this.engine.consultAbsolute(fDiagram);
//		this.engine.waitUntilIdle();

		String rule_param = "";

		if (internal) {
			if (rules.size() < 1)
				return;

			for (int i = 0; i < rules.size() - 1; i++)
				rule_param += rules.get(i).getId() + ",";

			rule_param += rules.get(rules.size()-1).getId(); 
		} else {
			if (params.size() > 0) {

				for (int i = 0; i < this.params.size() - 1; i++)
					rule_param += this.params.get(i) + ",";

				rule_param += this.params.lastElement();
			}
		}

		String lang = MessageProvider.getActiveLanguageAbbreviation();
	
		this.engine.sendAndFlushLn(startCommand+"(" + lang + ",[" + rule_param
				+ "]).");

		while (!this.listener.isStreamFinished()) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		} catch(Exception ex) {
			throw new ComponentException(ex);
		} finally {
			finished = true;
			Standardprotocol.getLogger().info(simpleStream.toString());
		}
	}

	@Override
	public void transformInput(Object inputSource) throws ComponentException {
		if(inputSource == null)
			throw new ComponentException("Quelle ist null");
		
		if (!(inputSource instanceof File))
			throw new ComponentException(
					"input source has not expected format.");

		fDiagram = (File) inputSource;
	}

	@Override
	public Object transformOutput() throws ComponentException {
		return simpleStream;
	}

	@Override
	public boolean isValid() {
		/*
		 ToolDescriptor td = ToolStore.getTool("SWI-Prolog");

		if (td != null && td.getPath() != null && !td.getPath().isEmpty())
			return true;
		*/

		return true;
	}

	@Override
	public String getDisplayName() {
		return "Prolog run";
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
		this.toolParam = param;
	}
}
