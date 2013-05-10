package customsrc;

import org.eclipse.gmf.runtime.common.ui.action.ActionMenuManager;
import org.eclipse.jface.action.Action;

@SuppressWarnings("restriction")
public class ChangeTypeMenuManager extends ActionMenuManager {
	
	protected static String id = "ChangeTypeMenu";
	
	protected static String text = "Change Type";
	
	private static class ActivityTypesManagerAction extends Action {
		public ActivityTypesManagerAction() {
			setText(text);
			setId(id);
		}
		
		/**
		 * This constructor is used to build the blank action of the submenus.
		 * @param id the id of the menu
		 * @param text the text of the menu
		 */
		public ActivityTypesManagerAction(String id, String text) {
            setText(text);
            setId(id);
        }
		
	}
	
	public ChangeTypeMenuManager(){
		super(id, new ActivityTypesManagerAction());
	}
	
	public ChangeTypeMenuManager(String text){
		super(id, new ActivityTypesManagerAction(id, text));
	}

}