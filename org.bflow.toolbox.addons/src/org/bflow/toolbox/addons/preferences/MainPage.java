package org.bflow.toolbox.addons.preferences;

import org.bflow.toolbox.addons.store.ConfigurationStore;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

/**
 * Defines the mitamm prefences main page.
 * @author Arian Storch
 * @since 13/04/10
 * @version 24/05/111
 */
public class MainPage extends FieldEditorPreferencePage
		implements IWorkbenchPreferencePage {

	@Override
	protected void createFieldEditors() {
	}

	@Override
	public void init(IWorkbench workbench) {
	}
	
	@Override
	protected Control createContents(Composite parent) 
	{
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(1,false));
		
		Group group = new Group(composite, SWT.SHADOW_ETCHED_IN);
		
		group.setLayout(new GridLayout(1,false));
		group.setText("General settings");
		
		final Button btnSaveAllOpen = new Button(group, SWT.CHECK);
		btnSaveAllOpen.setText("Save all open diagrams before invoking protocols");
		
		btnSaveAllOpen.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				boolean b = btnSaveAllOpen.getSelection();
				ConfigurationStore.getInstance().put(ConfigurationStore.ID_SAVE_ALL_OPEN_DIAGRAMS, ""+b);
			}
		});
		
		String strB = ConfigurationStore.getInstance().get(ConfigurationStore.ID_SAVE_ALL_OPEN_DIAGRAMS);
		
		boolean b = Boolean.parseBoolean((strB == null ? "" : strB));
		
		btnSaveAllOpen.setSelection(b);
		
		return composite;
	}
	
	@Override
	public boolean performOk() {
		ConfigurationStore.getInstance().save();
		return super.performOk();
	}

}
