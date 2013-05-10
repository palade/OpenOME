package customsrc;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gmf.runtime.common.ui.action.AbstractActionHandler;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IPerspectiveDescriptor;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;

import edu.toronto.cs.openome_model.Intention;
import edu.toronto.cs.openome_model.diagram.edit.parts.Task2EditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.Task3EditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.Task4EditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.Task5EditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.TaskEditPart;

public class EditIOPEAction extends AbstractActionHandler {

	private String privateID = "EditIOPEAction"; // this is how plugin.xml
													// recognize us
	private String privateCommandLabelText = "Edit IOPE";

	protected EditIOPEAction(IWorkbenchPage workbenchPage) {
		super(workbenchPage);
		//		this.ID = privateID; //$NON-NLS-1$
		setText(privateCommandLabelText);

	}

	public void init() {
		super.init();
		setId(privateID);
		setText(privateCommandLabelText);
		refresh();
	}

	@Override
	public void refresh() {

	}

	@Override
	protected void doRun(IProgressMonitor progressMonitor) {
		// TODO Auto-generated method stub
		System.out.println("Open The OWL-S Editor Here!!");

		try {

			getStructuredSelection();

			IStructuredSelection selection = getStructuredSelection();

			if (selection == null) {
				return;
			}

			String taskName = null;
			for (Object intention : selection.toArray()) {
				Intention i = null;
				if (intention instanceof TaskEditPart) {
					i = (Intention) ((TaskEditPart) intention)
							.resolveSemanticElement();
				} else if (intention instanceof Task2EditPart) {
					i = (Intention) ((Task2EditPart) intention)
							.resolveSemanticElement();
				} else if (intention instanceof Task3EditPart) {
					i = (Intention) ((Task3EditPart) intention)
							.resolveSemanticElement();
				} else if (intention instanceof Task4EditPart) {
					i = (Intention) ((Task4EditPart) intention)
							.resolveSemanticElement();
				} else if (intention instanceof Task5EditPart) {
					i = (Intention) ((Task5EditPart) intention)
							.resolveSemanticElement();
				}

				if (i != null) {
					taskName = i.getName();
					break;
				}

			}

			editIOPEInfo(taskName);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void editIOPEInfo(String taskName) {
		PropertyChangeEvent e = new PropertyChangeEvent(this, "Task", "Old",
				taskName);
		IPerspectiveDescriptor p = PlatformUI.getWorkbench()
				.getPerspectiveRegistry()
				.findPerspectiveWithId("hello.actions.ServicePerspective");
		PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
				.setPerspective(p);
		IViewPart view = PlatformUI.getWorkbench().getActiveWorkbenchWindow()
				.getActivePage().findView("Hello.view2");
		addPropertyChangeListener((IPropertyChangeListener) view);
		firePropertyChange(e);
	}

}