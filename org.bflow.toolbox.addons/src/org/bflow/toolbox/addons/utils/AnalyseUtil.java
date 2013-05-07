package org.bflow.toolbox.addons.utils;

import org.bflow.toolbox.addons.core.exceptions.ComponentException;
import org.bflow.toolbox.addons.interfaces.IAttributeAdjustMessage;
import org.bflow.toolbox.addons.interfaces.IMitammMessage;
import org.eclipse.core.resources.IFile;

/**
 * Util class that analyses data streams.
 * @author Arian Storch
 * @since 14/06/11
 */
public class AnalyseUtil {

	/**
	 * Analyses a stream that begins with [MESSAGE].
	 * @param contents string array without []
	 * @param markerResource 
	 * @return {@link ProblemsViewMessage}
	 * @throws ComponentException
	 */
	public static IMitammMessage analyseMessage(String contents[], IFile markerResource) throws ComponentException {
		if(contents.length != 5)
			throw new ComponentException("Message has unexpected number of records");
		
		String type = contents[1];
		
		String id = contents[2];
		String msg = contents[3];

		int messageType = (type.equalsIgnoreCase("INFO") ? ProblemsViewMessage.TYPE_INFO
				: (type.equalsIgnoreCase("ERROR") ? ProblemsViewMessage.TYPE_ERROR
						: ProblemsViewMessage.TYPE_WARNING));

		return new ProblemsViewMessage(messageType,
				id, msg, markerResource, markerResource.getLocation()
						.toFile().getName(), markerResource
						.getLocation().toFile().getParent());
	}
	
	/**
	 * Analyses a stream that begins with [ATTRIBUTE].
	 * @param contents string array without []
	 * @return {@link AttributeAdjustMessage}
	 * @throws ComponentException
	 */
	public static IMitammMessage analyseAttribute(String contents[]) throws ComponentException {
		if(contents.length != 5)
			throw new ComponentException("Attribute has unexpected number of records");
		
		String type = contents[1];
		String id = contents[2];
		String name = contents[3];
		String value = contents[4];
		
		int msgType = resolveType(type);
		
		return new AttributeAdjustMessage(msgType, id, name, value);
	}
	
	/**
	 * Resolves the attribute adjust message type.
	 * @param str str to check
	 * @return message type
	 * @throws ComponentException
	 */
	private static int resolveType(String str) throws ComponentException {
		
		if(str.equalsIgnoreCase("add"))
			return IAttributeAdjustMessage.TYPE_ADD;
		
		if(str.equalsIgnoreCase("xadd")) {
			return IAttributeAdjustMessage.TYPE_XADD;
		}
		
		if(str.equalsIgnoreCase("remove")) {
			return IAttributeAdjustMessage.TYPE_REMOVE;
		}
		
		if(str.equalsIgnoreCase("xremove")) {
			return IAttributeAdjustMessage.TYPE_XREMOVE;
		}
		
		if(str.equalsIgnoreCase("set")) {
			return IAttributeAdjustMessage.TYPE_SET;
		}
		
		throw new ComponentException("attribute adjust type "+str+" is not known");
	}
	
}
