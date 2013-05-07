package customsrc;

//import hello.actions.ProcessPropertyView;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gmf.runtime.common.ui.action.AbstractActionHandler;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IPerspectiveDescriptor;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.views.IViewDescriptor;

import edu.toronto.cs.openome_model.Intention;
import edu.toronto.cs.openome_model.diagram.edit.parts.Task3EditPart;

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

				if (intention instanceof Task3EditPart) {

					Intention i = (Intention) ((Task3EditPart) intention)
							.resolveSemanticElement();
					taskName = i.getName();
				}

			}

			// if (taskName != null) {
			PropertyChangeEvent e = new PropertyChangeEvent(this, "Task",
					"Old", taskName);
			IPerspectiveDescriptor p = PlatformUI.getWorkbench()
					.getPerspectiveRegistry()
					.findPerspectiveWithId("hello.actions.ServicePerspective");
			PlatformUI.getWorkbench().getActiveWorkbenchWindow()
					.getActivePage().setPerspective(p);
			IViewPart view = PlatformUI.getWorkbench()
					.getActiveWorkbenchWindow().getActivePage()
					.findView("ProcessPropertyView");
			addPropertyChangeListener((IPropertyChangeListener) view);
			firePropertyChange(e);
			// }

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}