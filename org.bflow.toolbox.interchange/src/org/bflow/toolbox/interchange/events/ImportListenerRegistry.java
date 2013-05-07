package org.bflow.toolbox.interchange.events;

import java.util.ArrayList;

/**
 * Provides an import listener registry to add listeners to import processes.
 * @author Arian Storch
 * @since 13/07/11
 */
public class ImportListenerRegistry {

	/**
	 * Collection of listeners
	 */
	private static ArrayList<IImportListener> listeners = new ArrayList<IImportListener>();
	
	/**
	 * Adds the listener to the registry.
	 * @param listener listener
	 */
	public static void addImportListener(IImportListener listener) {
		listeners.add(listener);
	}
	
	/**
	 * Removes the listener from the registry.
	 * @param listener listener
	 */
	public static void removeImportListener(IImportListener listener) {
		listeners.remove(listener);
	}
	
	/**
	 * Dispatches an export event to the listeners.
	 * @param event event to dispatch
	 */
	public static void dispatchEvent(ImportEvent event) {
		for(IImportListener l:listeners) {
			l.noticeImportEvent(event);
		}
	}
	
}
