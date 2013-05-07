package org.bflow.toolbox.addons.interfaces;

import org.bflow.toolbox.addons.core.model.IComponent;
import org.bflow.toolbox.interchange.model.ExportDescription;


/**
 * <p>Defines the interface to the diagram export component.</p>
 * <p><b>Important note:</b> You have to set an ExportDescription else you will get a NullPointerException!</p>
 * @author Arian Storch
 * @since 05/05/10
 * @version 23/09/10
 *
 */
public interface IDiagramExportComponent extends IComponent
{
	/**
	 * Sets the {@link ExportDescription} used by this link.
	 * @param exd {@link ExportDescription}
	 */
	public void setExportDescription(ExportDescription exd);
}
