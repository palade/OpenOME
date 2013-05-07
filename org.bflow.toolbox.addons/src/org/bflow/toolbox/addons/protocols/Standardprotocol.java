package org.bflow.toolbox.addons.protocols;

import java.io.File;
import java.io.IOException;
import java.util.Vector;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.bflow.toolbox.addons.AddonPlugin;
import org.bflow.toolbox.addons.components.AttributeAdjustComponent;
import org.bflow.toolbox.addons.components.EPCMetricsEvaluationComponent;
import org.bflow.toolbox.addons.components.FileAnalysisComponent;
import org.bflow.toolbox.addons.components.ShellAnalysisComponent;
import org.bflow.toolbox.addons.core.exceptions.ProtocolException;
import org.bflow.toolbox.addons.core.model.IComponent;
import org.bflow.toolbox.addons.core.model.Protocol;
import org.bflow.toolbox.addons.store.Key;
import org.bflow.toolbox.addons.utils.TemporaryFileServer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.dialogs.MessageDialog;

/**
 * Defines a standard protocol implementation.
 * <p/>
 * TODO: more explanation here...
 * 
 * @author Arian Storch
 * @since 24/04/10
 * @version 06/03/11
 * 
 */
public class Standardprotocol extends Protocol {

	// class attributes
	private String name;

	/**
	 * well configured flag
	 */
	private boolean wellConfigurated = true;

	/**
	 * source file
	 */
	private IFile source;

	/**
	 * marker id
	 */
	private String markerId;
	
	/**
	 * Instance of running protocol; if no instance is running this is null.
	 */
	private static Standardprotocol running;

	/**
	 * Returns the name of the {@linkplain Standardprotocol}.
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the {@linkplain Standardprotocol}.
	 * 
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	@Override
	protected boolean ReturnToSenderAfterFinish() {
		return true;
	}

	@Override
	protected boolean continueThoughException(IComponent component,
			ProtocolException protex) {

		String errMsg = protex.getMessage();
		
		String error = "Das Protokoll konnte nicht wie geplant ausgeführt werden! "
			+ "Bitte überprüfen Sie Ihre Einstellungen!\r\nFehlermeldung: "+errMsg;
		
		MessageDialog.openError(null, "Kritischer Fehler", error);

		return false;
	}

	@Override
	protected void finish() {
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void handleReturn(Object object) {
		if (object == null)
			return;

		if ((object instanceof Vector) && !((Vector<?>) object).isEmpty()) {
			if (((Vector<?>) object).firstElement() instanceof IMarker) {
				Vector<IMarker> markers = (Vector<IMarker>) object;
				AddonPlugin.getInstance().addMarker(markerId, markers);
			}
		}
	}

	@Override
	protected void init() {

		Standardprotocol.setLogger(Logger.getLogger(this.getName()));

		Standardprotocol.getLogger().addAppender(
				new ConsoleAppender(new PatternLayout()));
		
		try {
			Standardprotocol.getLogger().addAppender(new FileAppender(new PatternLayout(), Key.KEY_MITAMM_LOG_FILE.getAbsolutePath(), false));
		} catch (IOException e) {
			e.printStackTrace();
		}

		Vector<IComponent> locComp = new Vector<IComponent>();

		for (IComponent component : getComponents()) {
			if (component instanceof FileAnalysisComponent) {
				component = new FileAnalysisComponent((IFile) this.getSource());
			}

			if (component instanceof ShellAnalysisComponent) {
				component = new ShellAnalysisComponent((IFile) this.getSource());
			}

			if (component instanceof EPCMetricsEvaluationComponent) {
				component = new EPCMetricsEvaluationComponent((IFile) this
						.getSource());
			}
			
			if (component instanceof AttributeAdjustComponent) {
				((AttributeAdjustComponent)component).setProtocol(this);
			}				

			if (component.hasParams()) {
				String compParam = this.getComponentParam(component);

				if (compParam == null || compParam.equalsIgnoreCase("")) {
					MessageDialog.openConfirm(null, "Fehler",
							"Ihr Protokoll ist nicht korrekt eingerichtet!");
					wellConfigurated = false;
					return;
				}

				component.setParams(compParam);
			}

			locComp.add(component);
		}

		this.getComponents().clear();
		this.getComponents().addAll(locComp);

		markerId = this.getName() + "Markers";

		AddonPlugin.getInstance().deleteMarker(markerId, this.source);
		TemporaryFileServer.init();

		setThread(false);
	}

	@Override
	public void setSource(Object source) {
		this.source = ResourcesPlugin.getWorkspace().getRoot()
				.findFilesForLocationURI(((File) source).toURI())[0];
		super.setSource(source);
	}

	@Override
	public Object getSource() {
		return this.source;
	}

	@Override
	public void run() {
		if (!wellConfigurated)
			return;
		else {
			running = this;
			super.run();
			running = null;
		}
	}
	
	/**
	 * Returns the running instance of the standardprotocol. If no instance is running
	 * this is null.
	 * @return running instance or null
	 */
	public static Standardprotocol getRunning() {
		return running;
	}

	@Override
	public boolean isValid() {
		if (getComponents().size() > 0) {
			init();
			
			for (IComponent c : getComponents())
				if (!c.isValid())
					return false;
			return true;
		} else
			return false;
	}

}
