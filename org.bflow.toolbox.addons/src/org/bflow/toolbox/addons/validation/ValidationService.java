package org.bflow.toolbox.addons.validation;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bflow.toolbox.addons.AddonPlugin;
import org.bflow.toolbox.i18n.MessageProvider;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IContributor;
import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Bundle;
import org.osgi.service.prefs.Preferences;

/**
 * Implements an administrative instance for the validation rules and behaviour.
 * 
 * @author Arian Storch
 * @since 28/03/11
 * @version 06/07/11
 * 
 */
public class ValidationService {
	
	/**
	 * Validation service type flag
	 */
	public static final int TYPE_SETUP = 0;
	
	/**
	 * Validation service type flag
	 */
	public static final int TYPE_DEFAULT = 1;
	
	/**
	 * Validation service type flag
	 */
	public static final int TYPE_ALL = 2;
	
	/**
	 * Validation service type flag
	 */
	public static final int TYPE_CUSTOM = 3;

	/**
	 * Extension point id.
	 */
	public static final String EXTENSION_ID_VALIDATION_RULE = AddonPlugin.EXTENSION_ID_ADDON_RULE;

	/**
	 * static instance
	 */
	private static ValidationService instance;

	/**
	 * map of rules
	 */
	private HashMap<String, ArrayList<Rule>> rulesMap = new HashMap<String, ArrayList<Rule>>();
	
	/**
	 * Preference store
	 */
	private Preferences prefStore = AddonPlugin.getInstance().getPreferencesStore();

	/**
	 * Constructor.
	 */
	private ValidationService() {
		try {
			loadRules();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Loads the installed rules.
	 * @throws IOException 
	 */
	private void loadRules() throws IOException {
		IConfigurationElement[] config = Platform.getExtensionRegistry()
				.getConfigurationElementsFor(EXTENSION_ID_VALIDATION_RULE);

		for (IConfigurationElement element : config) {
			IContributor con = element.getContributor();
			Bundle bundle = Platform.getBundle(con.getName());

			String file = element.getAttribute("File");
			String abbr = element.getAttribute("Abbr");
			InputStream stream = bundle.getEntry(file).openStream();
			
			ArrayList<Rule> rules = RuleParser.parseFile(stream);
			rulesMap.put(abbr, rules);
		}
	}

	/**
	 * Returns all rules associated with the language abbreviation.
	 * 
	 * @param abbreviation
	 *            language abbreviation
	 * @return list of rules
	 * @see Rule
	 */
	public List<Rule> getRules(String abbreviation) {
		return rulesMap.get(abbreviation);
	}

	/**
	 * Returns all rules of the specified type that are associated with the
	 * language abbreviation.
	 * 
	 * @param abbreviation
	 *            language abbreviation
	 * @param type
	 *            type of rules
	 * @return list of rules
	 */
	@Deprecated
	public List<Rule> getRulesByType(String abbreviation, String type) {
		ArrayList<Rule> filteredList = new ArrayList<Rule>();

		ArrayList<Rule> list = rulesMap.get(abbreviation);

		if (list == null)
			return null;

		for (Rule r : list) {
			if (r.getType().equalsIgnoreCase(type))
				filteredList.add(r);
		}

		return filteredList;
	}
	
	/**
	 * Returns all rules of the specified type that are associated with the
	 * language abbreviation.
	 * 
	 * @param abbr language abbreviation
	 * @param type see Class Constants
	 * @return
	 */
	public List<Rule> getRulesByType(String abbr, int type) {
		ArrayList<Rule> filteredList = new ArrayList<Rule>();
		
		ArrayList<Rule> list = rulesMap.get(abbr);
		
		if(list == null)
			return filteredList;
		
		if(type == ValidationService.TYPE_ALL)
			return list;
		
		for(Rule rule:list) {
			if(type == ValidationService.TYPE_DEFAULT) {
				if(rule.isDfault())
					filteredList.add(rule);
			}
			
			if(type == ValidationService.TYPE_SETUP) {
				if(prefStore.getBoolean(rule.getId(), true))
						filteredList.add(rule);
			}
		}
		
		return filteredList;
	}
	
	/**
	 * Returns true if the rule shall be checked.<p/>
	 * If there is no rule defined, false will be returned.
	 * @param ruleId identifier of the rule
	 * @return true or false
	 */
	public boolean isEnabled(String ruleId) {				
		return prefStore.getBoolean(ruleId, true);
	}
	
	/**
	 * Returns the user defined error message of a rule if there exists one. Otherwise the default message
	 * is returned.<p/>
	 * @param ruleId id of the rule
	 * @param value value that will be inserted into the error message
	 * @return error message of the rule out of the preferences store, the default or null if nothing has been found
	 */
	public String getErrorMessage(String ruleId, String value)	{		
		String prefStoreMsg = prefStore.get(ruleId+"Message", null);
		
		if(prefStoreMsg != null)
			return prefStoreMsg.replace("$value", value);
		
		String abbr = MessageProvider.getActiveLanguageAbbreviation();
		
		ArrayList<Rule> rules = rulesMap.get(abbr);
		
		for(Rule rule:rules)
			if(rule.getId().equalsIgnoreCase(ruleId))				
					return rule.getMessage().replace("$value", value);
		
		return null;
	}
	
	/**
	 * Sets the enabled flag of a rule.
	 * @param ruleId id of the rule
	 * @param value true or false
	 */
	public void setEnabled(String ruleId, boolean value) {		
		prefStore.putBoolean(ruleId, value);
	}

	/**
	 * Returns the instance of the validation service.
	 * 
	 * @return instance
	 */
	public static ValidationService getInstance() {
		if (instance == null) {
			instance = new ValidationService();
		}

		return instance;
	}

}
