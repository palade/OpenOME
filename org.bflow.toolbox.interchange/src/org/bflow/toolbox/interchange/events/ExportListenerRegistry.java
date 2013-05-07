package org.bflow.toolbox.interchange.events;

import java.util.ArrayList;

/**
 * Provides an export listener registry to add listeners to export processes.
 * @author Arian Storch
 * @since 23/06/11
 */
public class ExportListenerRegistry {
	
	/**
	 * Collection of listeners
	 */
	private static ArrayList<IExportListener> listeners = new ArrayList<IExportListener>();
	
	/**
	 * Adds the listener to the registry.
	 * @param listener listener
	 */
	public static void addExportListener(IExportListener listener) {
		listeners.add(listener);
	}
	
	/**
	 * Removes the listener from the registry.
	 * @param listener listener
	 */
	public static void removeExportListener(IExportListener listener) {
		listeners.remove(listener);
	}
	
	/**
	 * Dispatches an export event to the listeners.
	 * @param event event to dispatch
	 */
	public static void dispatchEvent(ExportEvent event) {
		for(IExportListener l:listeners) {
			l.noticeExportEvent(event);
		}
	}

}
