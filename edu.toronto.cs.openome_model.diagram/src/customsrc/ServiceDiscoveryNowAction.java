package customsrc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.thrift.TException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gmf.runtime.common.ui.action.AbstractActionHandler;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IPerspectiveDescriptor;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;

import cn.edu.pku.ss.matchmaker.SSDClient.SSDClient;
import cn.edu.pku.ss.matchmaker.thrift.Actor;
import cn.edu.pku.ss.matchmaker.thrift.Category;
import cn.edu.pku.ss.matchmaker.thrift.IOModel;
import cn.edu.pku.ss.matchmaker.thrift.PECRModel;
import cn.edu.pku.ss.matchmaker.thrift.ProfileInfo;
import cn.edu.pku.ss.matchmaker.thrift.RequestInfo;
import cn.edu.pku.ss.matchmaker.thrift.ResponseInfo;
import cn.edu.pku.ss.matchmaker.thrift.ServiceDiscoverer;
import cn.edu.pku.ss.matchmaker.thrift.TaskInfo;
import edu.toronto.cs.openome_model.Intention;
import edu.toronto.cs.openome_model.diagram.edit.parts.Task2EditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.Task3EditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.Task4EditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.Task5EditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.TaskEditPart;

public class ServiceDiscoveryNowAction extends AbstractActionHandler {

	private String privateID = "ServiceDiscoveryNowAction"; // this is how
															// plugin.xml
	// recognize us
	private String privateCommandLabelText = "Service Discovery Now";

	protected ServiceDiscoveryNowAction(IWorkbenchPage workbenchPage) {
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
		try {

			String taskName = getSelectionName();
			String filePath = findIOPEInfo(taskName);
			filePath = "";
			// If the there is no IOPE info, alert and open the IOPE editor
			if (filePath == null) {
				MessageBox msgBox = new MessageBox(new Shell(
						Display.getCurrent()));
				msgBox.setMessage("IOPE Information does not exist, please input");
				msgBox.open();
				editIOPEInfo(taskName);

			} else {
				// Send the query to server
				serviceDiscovery(taskName);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void serviceDiscovery(String taskName) throws TException {
		// SSDClient ssdClient = new SSDClient();
		// ServiceDiscoverer.Client syncClient = null;
		//
		// if ((syncClient = ssdClient.initSyncClient()) == null) {
		// return;
		// }

//		ssdClient.open();

		RequestInfo requestInfo = new RequestInfo();
		TaskInfo taskInfo = new TaskInfo();
		taskInfo.setTaskID(taskName);
		taskInfo.setOwlsContent("");
		List<TaskInfo> owlsInfo = new ArrayList<TaskInfo>();
		owlsInfo.add(taskInfo);
		requestInfo.setOwlsInfo(owlsInfo);

		List<RequestInfo> requestInfoList = new ArrayList<RequestInfo>();
		requestInfoList.add(requestInfo);
		// ResponseInfo repInfo = syncClient.getServices(requestInfoList);

		// IPerspectiveDescriptor p = PlatformUI.getWorkbench()
		// .getPerspectiveRegistry()
		// .findPerspectiveWithId("hello.actions.ServicePerspective");
		// PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
		// .setPerspective(p);

		IViewPart view = PlatformUI.getWorkbench().getActiveWorkbenchWindow()
				.getActivePage().findView("Hello.CandidateService");
		addPropertyChangeListener((IPropertyChangeListener) view);

		ResponseInfo repInfo = mockDate();

		PropertyChangeEvent e = new PropertyChangeEvent(this,
				"CandidateServices", null, repInfo);
		firePropertyChange(e);

		// List<ResponseInfo> responseInfoList = syncClient
		// .getServices(requestinfo);

	}

	private ResponseInfo mockDate() {
		ResponseInfo repInfo = new ResponseInfo();

		Map<String, List<ProfileInfo>> taskServices = new HashMap<String, List<ProfileInfo>>();

		List<ProfileInfo> ss = new ArrayList<ProfileInfo>();

		ProfileInfo profile1 = new ProfileInfo();
		profile1.setServiceName("ServiceA");
		List<Actor> actorList = new ArrayList<Actor>();
		Actor a1 = new Actor();
		a1.setName("actor1");
		Actor a2 = new Actor();
		a2.setName("actor2");
		actorList.add(a1);
		actorList.add(a2);
		profile1.setActorList(actorList);
		List<Category> categoryList = new ArrayList<Category>();
		Category c1 = new Category();
		c1.setCategoryName("c1");
		Category c2 = new Category();
		c2.setCategoryName("c2");
		Category c3= new Category();
		c3.setCategoryName("c3");
		categoryList.add(c1);
		categoryList.add(c2);
		categoryList.add(c3);
		profile1.setCategoryList(categoryList);

		List<IOModel> inputList = new ArrayList<IOModel>();
		inputList.add(new IOModel("", "INT"));
		inputList.add(new IOModel("", "DOUBLE"));
		profile1.setInputList(inputList);

		List<IOModel> outputList = new ArrayList<IOModel>();
		outputList.add(new IOModel("", "STRING"));
		profile1.setOutputList(outputList);

		List<PECRModel> context = new ArrayList<PECRModel>();
		PECRModel m1 = new PECRModel();
		m1.setExpressionBody("Sunny");
		PECRModel m2 = new PECRModel();
		m2.setExpressionBody("Monday");
		context.add(m1);
		context.add(m2);
		profile1.setContext(context);
		ss.add(profile1);

		// ProfileInfo profile2;
		// ss.add(profile2);

		taskServices.put("Task1", ss);
		repInfo.setTaskServices(taskServices);
		return repInfo;

	}

	private String getSelectionName() {

		IStructuredSelection selection = getStructuredSelection();
		if (selection == null) {
			return null;
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
			}

		}
		return taskName;
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

	private String findIOPEInfo(String taskName) {

		return null;
	}

}