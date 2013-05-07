package org.bflow.toolbox.attributes;

/**
 * This class provides an adapter between two packages that depend on each other. 
 * @author Arian Storch
 * @since 20/10/10
 * @deprecated
 */
public class AttributeViewAdapter {

	private static Object attributeViewPart;
	
	/**
	 * Registers an attribute view part.
	 * @param viewPart
	 */
	public static void registerAttributeViewPart(Object viewPart) {
		attributeViewPart = viewPart;
	}
	
	/**
	 * Returns the registered view part
	 * @return registered view part
	 */
	public static Object getAttributeViewPart() {
		return attributeViewPart;
	}
	
}
