package org.bflow.toolbox.addons.interfaces;

import org.bflow.toolbox.addons.utils.AttributeAdjustMessage;

/**
 * Defines an interfaces to the {@link AttributeAdjustMessage}.
 * @author Arian Storch
 * @since 11/10/10
 */
public interface IAttributeAdjustMessage extends IMitammMessage {
	
	/**
	 * flag that indicates the add type
	 */
	public static int TYPE_ADD = 1000;
	
	/**
	 * flag that indicates the change type
	 */
	public static int TYPE_SET = 1001;
	
	/**
	 * flag that indicates the remove type
	 */
	public static int TYPE_REMOVE = 1002;
	
	/**
	 * flag that indicates the add all type
	 */
	public static int TYPE_XADD = 1003;
	
	/**
	 * flag that indicates the remove all type
	 */
	public static int TYPE_XREMOVE = 1004;
}
