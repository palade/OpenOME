package org.bflow.toolbox.interchange.model;

import java.util.HashMap;

/**
 * defines a script object used by the ExportDescription Object
 * @author Arian Storch
 * @since 14/08/09
 *
 */
public class Script 
{
	private String file;
	private HashMap<String, String> params = new HashMap<String, String>();
	
	/**
	 * constructor
	 */
	public Script() {
	}
	
	/**
	 * constructor
	 * @param file name of the file including path
	 */
	public Script(String file){
		this.file = file;
	}
	
	/**
	 * constructor
	 * @param file name of the file incl. path
	 * @param params hash map of params
	 */
	public Script(String file, HashMap<String, String> params)
	{
		this.file = file;
		this.params = params;
	}
	
	/**
	 * returns the file including path of the file
	 */
	public String getFile() {
		return file;
	}
	
	/**
	 * sets the file
	 * @param file name of the file including path
	 */
	public void setFile(String file) {
		this.file = file;
	}
	
	/**
	 * returns the hash map with the params of the script
	 */
	public HashMap<String, String> getParams() {
		return params;
	}
	
	@Override
	public String toString() 
	{
		return "[Skript] File: "+file+" Params: "+params;
	}
	
	
}
