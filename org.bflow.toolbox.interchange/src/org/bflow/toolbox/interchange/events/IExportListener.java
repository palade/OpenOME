package org.bflow.toolbox.interchange.events;

/**
 * Defines an interface for export process listener.
 * @author Arian Storch
 * @since 23/06/11 
 *
 */
public interface IExportListener {
	
	/**
	 * Notices the listener that an export event occurred.
	 * @param event event
	 */
	public void noticeExportEvent(ExportEvent event);

}
