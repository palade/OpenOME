package org.bflow.toolbox.interchange.events;

/**
 * Defines an interface for import process listener.
 * @author Arian Storch
 * @since 13/07/11 
 *
 */
public interface IImportListener {

	/**
	 * Notices the listener that an import event occurred.
	 * @param event event
	 */
	public void noticeImportEvent(ImportEvent event);
	
}
