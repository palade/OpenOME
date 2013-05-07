package org.bflow.toolbox.attributes;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import org.bflow.toolbox.i18n.MessageProvider;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.workspace.EMFCommandOperation;
import org.eclipse.gmf.runtime.diagram.ui.OffscreenEditPartFactory;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.internal.util.DiagramIOUtil;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor;
import org.eclipse.gmf.runtime.emf.core.util.EMFCoreUtil;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.part.ViewPart;

/**
 * Implements the view part to support the add-ons attribute view.
 * 
 * @author Arian Storch
 * @since 20/04/10
 * @version 13/07/11
 */
@SuppressWarnings("restriction")
public class AttributeViewPart extends ViewPart implements ISelectionListener,
		IPartListener {
	private AttributeFile attrFile;
	
	private AttributeResourceSetListener resourceSetListener = new AttributeResourceSetListener();

	private ShapeNodeEditPart selectedEditPart;

	private IStructuredSelection selection;

	private DiagramEditPart diagramEditPart;

	private HashMap<DiagramDocumentEditor, AttributeFile> fileMap = new HashMap<DiagramDocumentEditor, AttributeFile>();

	private static AttributeViewPart instance;

	private DiagramDocumentEditor diagramEditor;

	private boolean init = false;

	/**
	 * Constructor.
	 */
	public AttributeViewPart() {
	}

	@SuppressWarnings("deprecation")
	@Override
	public void init(IViewSite site) throws PartInitException {
		super.init(site);
		site.getPage().addSelectionListener(this);
		site.getPage().addPartListener(this);

		instance = this;
		AttributeViewAdapter.registerAttributeViewPart(this);
	}

	@Override
	public void dispose() {
		super.dispose();
	}

	@Override
	public void createPartControl(final Composite parent) {
		GridLayout parLayout = new GridLayout(1, false);
		parent.setLayout(parLayout);

		Composite mainPane = new Composite(parent, SWT.BORDER);

		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 9;

		GridData gridData = new GridData();
		gridData.horizontalAlignment = SWT.FILL;
		gridData.grabExcessHorizontalSpace = true;
		gridData.heightHint = 35;

		mainPane.setLayout(gridLayout);
		mainPane.setLayoutData(gridData);

		Label lblName = new Label(mainPane, SWT.NONE);
		lblName.setText(MessageProvider.getMessage("AttributeViewPart#msg1"));

		txtName = new Text(mainPane, SWT.BORDER);

		gridData = new GridData();
		gridData.widthHint = 120;

		txtName.setLayoutData(gridData);

		Label lblValue = new Label(mainPane, SWT.NONE);
		lblValue.setText(MessageProvider.getMessage("AttributeViewPart#msg2"));

		txtValue = new Text(mainPane, SWT.BORDER);

		gridData = new GridData();
		gridData.widthHint = 120;

		txtValue.setLayoutData(gridData);

		btnAdd = new Button(mainPane, SWT.NONE);
		btnAdd.setImage(new Image(mainPane.getDisplay(), this.getClass()
				.getResourceAsStream("/icons/add8.png")));
		btnAdd.setToolTipText(MessageProvider.getMessage("AttributeViewPart#msg3"));

		btnAdd.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent e) {
				if (selectedEditPart == null && diagramEditPart == null)
					return;

				if (txtName.getText().isEmpty())
					return;

				EObject eObj = (selectedEditPart == null ? diagramEditPart
						.getNotationView().getElement() : selectedEditPart
						.getPrimaryView().getElement());

				String id = EMFCoreUtil.getProxyID(eObj);
				String name = txtName.getText();
				String value = txtValue.getText();

				attrFile.add(id, name, (value.isEmpty() ? " " : value));

				txtName.setText("");
				txtValue.setText("");
				txtName.setFocus();

				((IAttributableDocumentEditor) diagramEditor)
						.firePropertyChanged();

				updateView();
			}
		});

		btnAddAll = new Button(mainPane, SWT.NONE);
		btnAddAll.setImage(new Image(mainPane.getDisplay(), this.getClass()
				.getResourceAsStream("/icons/add8.png")));
		btnAddAll
				.setToolTipText(MessageProvider.getMessage("AttributeViewPart#msg4"));
		btnAddAll.setText("All");

		btnAddAll.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (selectedEditPart == null && diagramEditPart == null)
					return;

				for (Object child : diagramEditPart.getChildren())
					if (child.getClass() == selectedEditPart.getClass()) {
						ShapeNodeEditPart ePart = (ShapeNodeEditPart) child;
						EObject eObj = ePart.resolveSemanticElement();

						String id = EMFCoreUtil.getProxyID(eObj);
						String name = txtName.getText();
						String value = txtValue.getText();

						attrFile.add(id, name, (value.isEmpty() ? " " : value));
					}

				// default attributes
				String diagram = getActiveResource(diagramEditor).getName();

				String type = selectedEditPart.resolveSemanticElement().eClass().getInstanceClassName();

				DefaultAttributeProvider.addAttributeForDiagram(diagram,
						txtName.getText(), txtValue.getText(), type);
				// done

				txtName.setText("");
				txtValue.setText("");
				txtName.setFocus();

				((IAttributableDocumentEditor) diagramEditor)
						.firePropertyChanged();

				updateView();
			}
		});

		btnDel = new Button(mainPane, SWT.NONE);
		btnDel.setImage(new Image(mainPane.getDisplay(), this.getClass()
				.getResourceAsStream("/icons/del8.png")));
		btnDel.setToolTipText(MessageProvider.getMessage("AttributeViewPart#msg5"));

		btnDel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (selectedEditPart == null && diagramEditPart == null)
					return;

				int sel = attributeTable.getSelectionIndex();

				EObject eObj = (selectedEditPart == null ? diagramEditPart
						.getNotationView().getElement() : selectedEditPart
						.getPrimaryView().getElement());
				String id = EMFCoreUtil.getProxyID(eObj);

				if (sel > -1) {
					ValuePair pair = (ValuePair) viewer.getElementAt(sel);

					attrFile.remove(id, pair.getName());
					viewer.remove(pair);

					((IAttributableDocumentEditor) diagramEditor)
							.firePropertyChanged();

					updateView();
				}
			}
		});

		btnDelAll = new Button(mainPane, SWT.NONE);
		btnDelAll.setImage(new Image(mainPane.getDisplay(), this.getClass()
				.getResourceAsStream("/icons/del8.png")));
		btnDelAll
				.setToolTipText(MessageProvider.getMessage("AttributeViewPart#msg6"));
		btnDelAll.setText("All");

		btnDelAll.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (selectedEditPart == null && diagramEditPart == null)
					return;

				if (!MessageDialog.openConfirm(parent.getShell(),
						MessageProvider.getMessage("AttributeViewPart#msg7"), 
						MessageProvider.getMessage("AttributeViewPart#msg8")))
					return;

				int sel = attributeTable.getSelectionIndex();

				if (sel > -1) {
					ValuePair pair = (ValuePair) viewer.getElementAt(sel);

					for (Object child : diagramEditPart.getChildren())
						if (child.getClass() == selectedEditPart.getClass()) {
							ShapeNodeEditPart ePart = (ShapeNodeEditPart) child;
							EObject eObj = ePart.resolveSemanticElement();

							String id = EMFCoreUtil.getProxyID(eObj);

							attrFile.remove(id, pair.getName());
						}

					// default attributes
					String diagram = getActiveResource(diagramEditor).getName();

					String type = selectedEditPart.resolveSemanticElement().eClass().getInstanceClassName();

					DefaultAttributeProvider.removeAttributeForDiagram(diagram,
							pair.getName(), type);
					// done

				}

				((IAttributableDocumentEditor) diagramEditor)
						.firePropertyChanged();

				updateView();
			}
		});

		btnAddProject = new Button(mainPane, SWT.NONE);
		btnAddProject.setImage(new Image(mainPane.getDisplay(), this.getClass()
				.getResourceAsStream("/icons/add8.png")));
		btnAddProject
				.setToolTipText(MessageProvider.getMessage("AttributeViewPart#msg9"));
		btnAddProject.setText("Project");
		btnAddProject.setEnabled(true);

		btnAddProject.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (selectedEditPart == null && diagramEditPart == null)
					return;

				if (txtName.getText().isEmpty())
					return;

				String attrName = txtName.getText();
				String attrValue = (txtValue.getText().isEmpty() ? ""
						: txtValue.getText());

				String proj = null;

				try {
					IProject project = getActiveResource(diagramEditor)
							.getProject();
					proj = project.getName();

					String fExt = ((IAttributableDocumentEditor) diagramEditor)
							.getFileExtension();

					for (IResource resource : project.members())
						if (resource.getFileExtension().equalsIgnoreCase(fExt)) {
							IFile iFile = (IFile) resource;

							String type = selectedEditPart.resolveSemanticElement().eClass().getInstanceClassName();

							if (type == null && selectedEditPart != null)
								return;

							Diagram d = DiagramIOUtil.load(diagramEditPart
									.getEditingDomain(), iFile, true, null);

							DiagramEditPart prEditPart = OffscreenEditPartFactory
									.getInstance().createDiagramEditPart(d,
											getSite().getShell());

							AttributeFile prFile = null;

							// ist das Dokument vlt. schon offen?
							for (DiagramDocumentEditor DDE : fileMap.keySet()) {
								DiagramEditPart DEP = DDE.getDiagramEditPart();
								String idMap = EMFCoreUtil.getProxyID(DEP
										.resolveSemanticElement());
								String idLoad = EMFCoreUtil
										.getProxyID(prEditPart
												.resolveSemanticElement());

								if (idMap.equalsIgnoreCase(idLoad)) {
									prFile = fileMap.get(DDE);
								}
							}

							// dok ist nicht offen und muss geladen werden
							if (prFile == null) {
								prFile = new AttributeFile(prEditPart);
								prFile.load();
							}

							if (prEditPart.getClass().getSimpleName()
									.equalsIgnoreCase(
											selectedEditPart.getClass()
													.getSimpleName())) {
								String id = EMFCoreUtil.getProxyID(prEditPart
										.resolveSemanticElement());
								prFile.add(id, attrName, attrValue);

								prFile.save();
								continue;
							}

							for (Object obj : prEditPart.getChildren()) {
								EObject eObj = ((ShapeNodeEditPart)obj).resolveSemanticElement();
								String eObjType = eObj.eClass().getInstanceClassName();
								
								
								if (eObjType.equalsIgnoreCase(type)) {
									String id = EMFCoreUtil
											.getProxyID(((ShapeNodeEditPart) obj)
													.resolveSemanticElement());

									prFile.add(id, attrName, attrValue);
								}
							}

							prFile.save();
							continue;

						}
				} catch (Exception ex) {
					ex.printStackTrace();
				}

				// default attributes
				String type = selectedEditPart.resolveSemanticElement().eClass().getInstanceClassName();

				DefaultAttributeProvider.addAttributeForProject(proj, txtName
						.getText(), txtValue.getText(), type);
				// done
				
				// clean up
				txtName.setText("");
				txtValue.setText("");
				txtName.setFocus();

				((IAttributableDocumentEditor) diagramEditor)
						.firePropertyChanged();

				updateView();
			}
		});

		gridData = new GridData();
		gridData.heightHint = 100;
		gridData.grabExcessHorizontalSpace = true;
		gridData.grabExcessVerticalSpace = true;
		gridData.verticalAlignment = SWT.BEGINNING;
		gridData.horizontalAlignment = SWT.FILL;

		viewer = new TableViewer(parent, SWT.FULL_SELECTION | SWT.MULTI);

		TableViewerColumn viewColAttr = new TableViewerColumn(viewer, SWT.NONE);
		viewColAttr.getColumn().setText("Attribute");
		viewColAttr.getColumn().setWidth(120);
		viewColAttr.setLabelProvider(new MyColumnLabelProvider(0));

		TableViewerColumn viewColVal = new TableViewerColumn(viewer, SWT.NONE);
		viewColVal.getColumn().setText("Value");
		viewColVal.getColumn().setWidth(180);
		viewColVal.setEditingSupport(new MyColumnViewerEditor(viewer));
		viewColVal.setLabelProvider(new MyColumnLabelProvider(1));

		attributeTable = viewer.getTable();
		attributeTable.setLinesVisible(true);
		attributeTable.setHeaderVisible(true);
		attributeTable.setLayoutData(gridData);

		setUpPopUpMenu(attributeTable);

	}

	private void setUpPopUpMenu(final Table table) {
		Menu popUpMenu = new Menu(table);

		final MenuItem mItemOpenFile = new MenuItem(popUpMenu, SWT.NONE);
		mItemOpenFile.setText("Open file");

		mItemOpenFile.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				IStructuredSelection structSel = (IStructuredSelection) viewer
						.getSelection();
				ValuePair pair = (ValuePair) structSel.getFirstElement();

				String strUrl = pair.value;

				try {
					URL url = new URL(strUrl);

					String prot = url.getProtocol();

					if (prot.equalsIgnoreCase("file")) {
						File fileToOpen = new File(url.getAuthority()
								+ url.getPath());

						if (fileToOpen.exists() && fileToOpen.isFile()) {
							IFileStore fileStore = EFS.getLocalFileSystem()
									.getStore(fileToOpen.toURI());
							IWorkbenchPage page = PlatformUI.getWorkbench()
									.getActiveWorkbenchWindow().getActivePage();

							try {
								IDE.openEditorOnFileStore(page, fileStore);
							} catch (PartInitException e1) {
								MessageDialog.openError(table.getShell(),
										"Error", "Could not open editor part!");
								return;
							}
						} else {
							MessageDialog.openError(table.getShell(), "Error",
									"The file doesn't exist!");
							return;
						}

					} else {
						try {
							PlatformUI.getWorkbench().getBrowserSupport()
									.createBrowser(
											"attribute view part browser")
									.openURL(url);
						} catch (PartInitException e1) {
							e1.printStackTrace();
						}
					}

				} catch (MalformedURLException e1) {
					MessageDialog.openError(table.getShell(), "Error",
							"This isn't a valid url!");
					return;
				}
			}
		});

		table.setMenu(popUpMenu);
	}

	/**
	 * Updates the view content.
	 */
	private void updateView() {
		viewer.setItemCount(0);

		if (selectedEditPart == null && diagramEditPart == null)
			return;

		EObject eObj = (selectedEditPart == null ? diagramEditPart
				.getNotationView().getElement() : selectedEditPart
				.getPrimaryView().getElement());

		String id = EMFCoreUtil.getProxyID(eObj);

		HashMap<String, String> map = attrFile.get(id);

		if (map == null)
			return;

		for (String name : map.keySet())
			viewer.add(new ValuePair(name, map.get(name)));
	}

	/**
	 * Sets a add-on attribute.
	 * 
	 * @param attribute
	 */
	public void setAttribute(final IAttribute attribute) {

		String id = attribute.getElementID();
		String name = attribute.getName();

		if (attribute.isLoner() && attribute.getValue() == null) {
			attrFile.remove(id, name);
			return;
		}

		String value = attribute.getValue();

		// attribut $name
		if (attribute.getName().equalsIgnoreCase("$name")) {

			for (Object o : diagramEditPart.getChildren()) {
				ShapeNodeEditPart snPart = (ShapeNodeEditPart) o;
				EObject eObj = snPart.resolveSemanticElement();

				if (EMFCoreUtil.getProxyID(eObj).equalsIgnoreCase(
						attribute.getElementID())) {

					EMFEditCommand com = new EMFEditCommand(eObj, value);

					EMFCommandOperation op = new EMFCommandOperation(
							diagramEditPart.getEditingDomain(), com);
					try {
						op.execute(null, null);
					} catch (ExecutionException e1) {
						e1.printStackTrace();
					}

					return;
				}
			}

			return;
		}

		// attribut $fontname
		if (attribute.getName().equalsIgnoreCase("$fontname")) {

			for (Object o : diagramEditPart.getChildren()) {
				ShapeNodeEditPart snEditPart = (ShapeNodeEditPart) o;

				EObject eObj = snEditPart.resolveSemanticElement();

				if (EMFCoreUtil.getProxyID(eObj).equalsIgnoreCase(
						attribute.getElementID())) {

					DiagramEditorUtils.setElementFont(snEditPart, attribute
							.getValue(), -1, -1);

					return;
				}
			}

			return;
		}

		// attribut $fontsize
		if (attribute.getName().equalsIgnoreCase("$fontsize")) {

			for (Object o : diagramEditPart.getChildren()) {
				ShapeNodeEditPart snEditPart = (ShapeNodeEditPart) o;

				EObject eObj = snEditPart.resolveSemanticElement();

				if (EMFCoreUtil.getProxyID(eObj).equalsIgnoreCase(
						attribute.getElementID())) {

					DiagramEditorUtils.setElementFont(snEditPart, null, Integer
							.parseInt(attribute.getValue()), -1);

					return;
				}
			}

			return;
		}

		// attribut $fontstyle
		if (attribute.getName().equalsIgnoreCase("$fontstyle")) {

			for (Object o : diagramEditPart.getChildren()) {
				ShapeNodeEditPart snEditPart = (ShapeNodeEditPart) o;

				EObject eObj = snEditPart.resolveSemanticElement();

				if (EMFCoreUtil.getProxyID(eObj).equalsIgnoreCase(
						attribute.getElementID())) {

					int style = -1;

					if (attribute.getValue().equalsIgnoreCase("bold"))
						style = SWT.BOLD;

					if (attribute.getValue().equalsIgnoreCase("italic"))
						style = SWT.ITALIC;

					if (attribute.getValue().equalsIgnoreCase("normal"))
						style = SWT.NORMAL;

					DiagramEditorUtils.setElementFont(snEditPart, null, -1,
							style);

					return;
				}
			}

			return;
		}

		// attribut $location
		if (attribute.getName().equalsIgnoreCase("$location")) {

			for (Object o : diagramEditPart.getChildren()) {
				ShapeNodeEditPart snEditPart = (ShapeNodeEditPart) o;

				EObject eObj = snEditPart.resolveSemanticElement();

				if (EMFCoreUtil.getProxyID(eObj).equalsIgnoreCase(
						attribute.getElementID())) {

					String strLocation = attribute.getValue();

					String strSplit[] = strLocation.split(",");

					if (strSplit.length != 2)
						return;

					if (strSplit[0].startsWith("-")
							|| strSplit[0].startsWith("+")
							|| strSplit[1].startsWith("-")
							|| strSplit[1].startsWith("+")) {

						strSplit[0] = strSplit[0].replace("+", "");
						strSplit[1] = strSplit[1].replace("+", "");

						DiagramEditorUtils.moveElementLocation(snEditPart,
								Integer.parseInt(strSplit[0]), Integer
										.parseInt(strSplit[1]));
					} else {

						Point location = new Point(Integer
								.parseInt(strSplit[0]), Integer
								.parseInt(strSplit[1]));

						DiagramEditorUtils.setElementLocation(snEditPart,
								location);
					}

					return;
				}
			}

			return;
		}
		
		if (!attribute.isLoner()) {

			Class<?> baseClass = null;

			for (Object obj : diagramEditPart.getChildren()) {
				ShapeNodeEditPart child = (ShapeNodeEditPart) obj;

				EObject eObj = child.resolveSemanticElement();
				String eObjID = EMFCoreUtil.getProxyID(eObj);

				if (eObjID.equalsIgnoreCase(attribute.getElementID())) {
					baseClass = child.getClass();
					break;
				}
			}

			if (baseClass != null) {

				for (Object obj : diagramEditPart.getChildren()) {
					ShapeNodeEditPart child = (ShapeNodeEditPart) obj;

					if (baseClass.isInstance(child)) {
						EObject eObj = child.resolveSemanticElement();
						String eObjID = EMFCoreUtil.getProxyID(eObj);

						if (attribute.getValue() == null) {
							if (attrFile != null){
								attrFile.remove(eObjID, name);
							}
							continue;
						}
						if (attrFile != null){
							attrFile.add(eObjID, name, value);
						}
					}
				}
			}
			return;
		}

		// speichern
		if (attrFile != null){
			attrFile.add(id, name, (value.isEmpty() ? " " : value));
		}
		if (diagramEditor != null){
			((IAttributableDocumentEditor) diagramEditor).firePropertyChanged();
		}
	}

	@Override
	public void setFocus() {

	}

	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		setUpControls((part instanceof DiagramDocumentEditor));

		selectedEditPart = null;

		IStructuredSelection sel = (IStructuredSelection) selection;

		this.selection = sel;

		if (sel.getFirstElement() instanceof ShapeNodeEditPart) {
			selectedEditPart = (ShapeNodeEditPart) sel.getFirstElement();
			btnAddAll.setEnabled(true);
			btnDelAll.setEnabled(true);
		}

		updateView();

		if (sel.getFirstElement() instanceof ConnectionNodeEditPart) {
			setUpControls(false);
			viewer.setItemCount(0);
		}
	}

	/**
	 * Returns the selected elements.
	 * 
	 * @return IStructuredSelection
	 */
	public Object getSelection() {
		return this.selection;
	}

	/**
	 * Sets up the enabled state of the controls.
	 * 
	 * @param b
	 *            true or false
	 */
	private void setUpControls(boolean b) {
		if (txtName == null)
			return;

		txtName.setEnabled(b);
		txtValue.setEnabled(b);
		attributeTable.setEnabled(b);
		btnAdd.setEnabled(b);
		btnDel.setEnabled(b);
		btnAddProject.setEnabled(b);
		btnAddAll.setEnabled(false);
		btnDelAll.setEnabled(false);
	}

	/**
	 * Returns the associated resource file for the opened edit part.
	 * 
	 * @param part
	 *            edit part
	 * @return associated resource file
	 */
	private IFile getActiveResource(IWorkbenchPart part) {
		if (part instanceof DiagramDocumentEditor) {
			IEditorInput input = ((DiagramDocumentEditor) part)
					.getEditorInput();

			if (input instanceof IFileEditorInput)
				return ((IFileEditorInput) input).getFile();
		}

		return null;
	}

	@Override
	public void partActivated(IWorkbenchPart part) {
		if (part instanceof DiagramDocumentEditor) {
			diagramEditPart = ((DiagramDocumentEditor) part)
					.getDiagramEditPart();

			if (fileMap.size() == 0) // init zustand
				partOpened(part);

			attrFile = fileMap.get(part);
			resourceSetListener.setFile(attrFile);
			resourceSetListener.setDiagramName(getActiveResource(part).getName());
			resourceSetListener.setProjectName(getActiveResource(part).getProject().getName());

			this.diagramEditor = (DiagramDocumentEditor) part;
			diagramEditor.getEditingDomain().addResourceSetListener(resourceSetListener);
		}
	}

	@Override
	public void partDeactivated(IWorkbenchPart part) {
		if (part instanceof DiagramDocumentEditor) {
			if(part == diagramEditor) {
				diagramEditor.getEditingDomain().removeResourceSetListener(resourceSetListener);
				resourceSetListener.setFile(null);
				resourceSetListener.setDiagramName(null);
			}
		}
	}

	@Override
	public void partBroughtToTop(IWorkbenchPart part) {
	}

	@Override
	public void partClosed(IWorkbenchPart part) {
		if (part instanceof DiagramDocumentEditor) {
			AttributeFile aFile = fileMap.get(part);

			if (aFile == null)
				return;

			fileMap.get(part).save();

			fileMap.remove(part);
			diagramEditor.getEditingDomain().removeResourceSetListener(resourceSetListener);
		}
	}

	@Override
	public void partOpened(IWorkbenchPart part) {
		if (part instanceof DiagramDocumentEditor) {
			diagramEditPart = ((DiagramDocumentEditor) part)
					.getDiagramEditPart();

			AttributeFile f = new AttributeFile(diagramEditPart);
			f.load();

			fileMap.put((DiagramDocumentEditor) part, f);
			
			init = true;
		}
	}

	/**
	 * Returns the actually opened attribute file.
	 * 
	 * @return attribute file or null
	 */
	public AttributeFile getAttributeFile() {
		if (!init)
			partOpened(PlatformUI.getWorkbench().getActiveWorkbenchWindow()
					.getActivePage().getActiveEditor().getEditorSite().getPart());

		return attrFile;
	}

	/**
	 * Returns the instance of this view part.
	 * 
	 * @return instance of this view part
	 */
	public static AttributeViewPart getInstance() {

		if (instance == null) {
			IWorkbenchWindow wnd = PlatformUI.getWorkbench()
					.getActiveWorkbenchWindow();
			IWorkbenchPage pge = wnd.getActivePage();

			if (pge != null) {
				try {
					pge
							.showView("org.bflow.toolbox.epc.extensions.attribute.view");
				} catch (PartInitException e) {
					e.printStackTrace();
				}
			}
		}

		return instance;
	}

	// Layout components
	private Text txtName;
	private Text txtValue;
	private Table attributeTable;
	private TableViewer viewer;
	private Button btnAdd;
	private Button btnDel;
	private Button btnAddAll;
	private Button btnDelAll;
	private Button btnAddProject;

	/**
	 * Implements the {@link EditingSupport} for the attribute view table.
	 * 
	 * @author Arian Storch
	 * @since 23/04/10
	 * 
	 */
	private class MyColumnViewerEditor extends EditingSupport {
		private CellEditor editor;

		/**
		 * Constructor.
		 * 
		 * @param viewer
		 *            TableViewer
		 */
		public MyColumnViewerEditor(ColumnViewer viewer) {
			super(viewer);

			this.editor = new TextCellEditor(((TableViewer) viewer).getTable());
		}

		@Override
		protected boolean canEdit(Object element) {
			return true;
		}

		@Override
		protected CellEditor getCellEditor(Object element) {
			return editor;
		}

		@Override
		protected Object getValue(Object element) {
			ValuePair pair = (ValuePair) element;

			return pair.getValue();
		}

		@Override
		protected void setValue(Object element, Object value) {
			ValuePair pair = (ValuePair) element;
			pair.setValue((String) value);

			EObject eObj = (selectedEditPart == null ? diagramEditPart
					.getNotationView().getElement() : selectedEditPart
					.getPrimaryView().getElement());
			String id = EMFCoreUtil.getProxyID(eObj);

			attrFile.set(id, pair.getName(), (String) value);

			((IAttributableDocumentEditor) diagramEditor).firePropertyChanged();

			updateView();
		}

	}

	/**
	 * Implements the {@link ColumnLabelProvider} for the attribute view table.
	 * 
	 * @author Arian Storch
	 * @since 23/04/10
	 * 
	 */
	private class MyColumnLabelProvider extends ColumnLabelProvider {
		private int col;

		/**
		 * Constructor.
		 * 
		 * @param col
		 *            column count of the label provider
		 */
		public MyColumnLabelProvider(int col) {
			this.col = col;
		}

		@Override
		public String getText(Object element) {
			ValuePair pair = (ValuePair) element;

			return (col == 0 ? pair.getName() : pair.getValue());
		}

	}

	/**
	 * Help class to store values into the attribute view table.
	 * 
	 * @author Arian Storch
	 * @since 23/04/10
	 * 
	 */
	private class ValuePair {
		private String name;
		private String value;

		/**
		 * Constructor.
		 * 
		 * @param name
		 * @param value
		 */
		public ValuePair(String name, String value) {
			super();
			this.name = name;
			this.value = value;
		}

		/**
		 * @return the name
		 */
		public String getName() {
			return name;
		}

		/**
		 * @param name
		 *            the name to set
		 */
		@SuppressWarnings("unused")
		public void setName(String name) {
			this.name = name;
		}

		/**
		 * @return the value
		 */
		public String getValue() {
			return value;
		}

		/**
		 * @param value
		 *            the value to set
		 */
		public void setValue(String value) {
			this.value = value;
		}

	}

	private class EMFEditCommand extends AbstractCommand {

		EObject eObject;
		String value;
		String oldValue;

		public EMFEditCommand(EObject eObject, String value) {
			super("SetNameCommend", "Sets the name of an eobject during an "
					+ "attribute adjust component is running.");
			this.eObject = eObject;
			this.value = value;
		}

		@Override
		public void execute() {
			oldValue = EMFCoreUtil.getName(eObject);
			EMFCoreUtil.setName(eObject, value);
		}

		@Override
		public void redo() {
		}

		@Override
		public void undo() {
			EMFCoreUtil.setName(eObject, oldValue);
		}
	}
}
