package org.bflow.toolbox.addons.providers;

import org.bflow.toolbox.addons.AddonPlugin;
import org.bflow.toolbox.addons.core.model.Protocol;
import org.bflow.toolbox.addons.events.ProtocolStoreListener;
import org.bflow.toolbox.addons.events.ToolStoreListener;
import org.bflow.toolbox.addons.protocols.Standardprotocol;
import org.bflow.toolbox.addons.store.ConfigurationStore;
import org.bflow.toolbox.addons.store.ProtocolStore;
import org.bflow.toolbox.addons.store.ToolStore;
import org.bflow.toolbox.addons.utils.ProtocolDescriptor;
import org.bflow.toolbox.addons.utils.ProtocolProgressDialog;
import org.bflow.toolbox.i18n.MessageProvider;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.action.ContributionItem;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.menus.IWorkbenchContribution;
import org.eclipse.ui.services.IServiceLocator;

/**
 * Defines a MenuContributionService that adds dynamically all installed and
 * valid protocols to the mitamm menu.
 * 
 * @author Arian Storch
 * @since 17/04/10
 * @version 24/05/11
 * 
 */
public class MenuContributionProvider extends ContributionItem implements
		IWorkbenchContribution, ProtocolStoreListener, ToolStoreListener {

	private Menu mitammMenu;

	/**
	 * Constructor.
	 */
	public MenuContributionProvider() {
		ProtocolStore.addStoreListener(this);
		ToolStore.addStoreListener(this);
	}

	@Override
	public void initialize(IServiceLocator serviceLocator) {
	}

	@Override
	public void fill(Menu menu, int index) {
		MenuItem toolchainItem = new MenuItem(menu, SWT.CASCADE);
		toolchainItem.setText("Add-ons");

		toolchainItem.setImage(new Image(menu.getDisplay(), this.getClass()
				.getResourceAsStream("/icons/Addons-16.png")));

		Menu tcMenu = new Menu(menu);
		toolchainItem.setMenu(tcMenu);
		this.mitammMenu = tcMenu;

		for (ProtocolDescriptor descriptor : ProtocolStore
				.getInstalledProtocols())
			addMenuItem(descriptor);
	}

	private void addMenuItem(final ProtocolDescriptor pd) {
		MenuItem sItem = new MenuItem(mitammMenu, SWT.NONE);
		sItem.setText(((Standardprotocol) pd.getProtocol()).getName());
		sItem.setData(pd);

		if (!pd.isValid())
			sItem.setEnabled(false);

		sItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// Löschen aller Marker
				DiagramMarkerNavigationProvider.getInstance().deleteMarkers(
						ResourcesPlugin.getWorkspace().getRoot(),
						IResource.DEPTH_INFINITE);

				try {
					Protocol chain = pd.getProtocol();

					IEditorPart activeEditor = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
					
					String strBool = ConfigurationStore.getInstance().get(ConfigurationStore.ID_SAVE_ALL_OPEN_DIAGRAMS);
					boolean saveAllOpen = (strBool == null ? false : Boolean.parseBoolean(strBool));

					// speichern aller offenen Diagramme
					if (saveAllOpen) {

						boolean doIt = MessageDialog.openConfirm(activeEditor.getSite().getShell(),
										MessageProvider.getMessage("MenuContributionService#msg1"),
										MessageProvider.getMessage("MenuContributionService#msg2"));

						if (doIt) {
							activeEditor.getSite().getPage().saveAllEditors(true);
						}
					}

					if (activeEditor != null) {
						if (activeEditor.isDirty())
							if (MessageDialog.openQuestion(activeEditor
									.getSite().getShell(), MessageProvider
									.getMessage("XMV#msg3"), MessageProvider
									.getMessage("XMV#msg4")))
								activeEditor.getSite().getPage().saveEditor(
										activeEditor, false);
							else
								return;

						IEditorInput input = activeEditor.getEditorInput();
						if (input instanceof IFileEditorInput) {
							IFile resource = ((IFileEditorInput) input)
									.getFile();

							chain.setSource(resource.getLocation().toFile());

							AddonPlugin.getProgressMonitorDialog().run(true,
									true, new ProtocolProgressDialog(chain));
						}
					}

				} catch (Exception ex) {
					ex.printStackTrace();
				}

			}
		});
	}

	@Override
	public void protocolAdded(final ProtocolDescriptor pd) {
		addMenuItem(pd);
	}

	@Override
	public void protocolRemoved(ProtocolDescriptor pd) {
		for (MenuItem item : mitammMenu.getItems()) {
			if (item.getText().equalsIgnoreCase(
					((Standardprotocol) pd.getProtocol()).getName())) {
				item.dispose();
				return;
			}
		}
	}

	@Override
	public void storeUpdate() {
		for (MenuItem item : mitammMenu.getItems()) {
			ProtocolDescriptor pd = (ProtocolDescriptor) item.getData();

			item.setEnabled(pd.isValid());
		}
	}
}
