package hello.actions;

import hello.Provider.CandidateServiceViewItem;
import hello.Provider.ServiceAnnoContentProvider;
import hello.Provider.ServiceAnnoLabelProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.part.ViewPart;

import cn.edu.pku.ss.matchmaker.thrift.Actor;
import cn.edu.pku.ss.matchmaker.thrift.Category;
import cn.edu.pku.ss.matchmaker.thrift.IOModel;
import cn.edu.pku.ss.matchmaker.thrift.PECRModel;
import cn.edu.pku.ss.matchmaker.thrift.ProfileInfo;
import cn.edu.pku.ss.matchmaker.thrift.ResponseInfo;

public class CandidateServicesView extends ViewPart implements
		IPropertyChangeListener {
	// private Table table;
	private TableViewer tableViewer;

	public CandidateServicesView() {
	}

	@Override
	public void createPartControl(Composite parent) {

		tableViewer = new TableViewer(parent, SWT.BORDER | SWT.FULL_SELECTION);
		Table table = tableViewer.getTable();
		table.setBounds(23, 165, 535, 172);
		table.setHeaderVisible(true);

		TableViewerColumn tableViewerColumn = new TableViewerColumn(
				tableViewer, SWT.NONE);
		TableColumn tableColumn = tableViewerColumn.getColumn();
		tableColumn.setWidth(100);
		tableColumn.setText("Service Name");

		TableViewerColumn tableViewerColumn_2 = new TableViewerColumn(
				tableViewer, SWT.NONE);
		TableColumn tableColumn_2 = tableViewerColumn_2.getColumn();
		tableColumn_2.setWidth(100);
		tableColumn_2.setText("Category");

		TableViewerColumn tableViewerColumn_3 = new TableViewerColumn(
				tableViewer, SWT.NONE);
		TableColumn tableColumn_3 = tableViewerColumn_3.getColumn();
		tableColumn_3.setWidth(100);
		tableColumn_3.setText("Provider");

		TableViewerColumn tableViewerColumn_4 = new TableViewerColumn(
				tableViewer, SWT.NONE);
		TableColumn tableColumn_4 = tableViewerColumn_4.getColumn();
		tableColumn_4.setWidth(100);
		tableColumn_4.setText("Service Input");

		TableViewerColumn tableViewerColumn_5 = new TableViewerColumn(
				tableViewer, SWT.NONE);
		TableColumn tableColumn_5 = tableViewerColumn_5.getColumn();
		tableColumn_5.setWidth(100);
		tableColumn_5.setText("Service Output");

		TableViewerColumn tableViewerColumn_6 = new TableViewerColumn(
				tableViewer, SWT.NONE);
		TableColumn tableColumn_6 = tableViewerColumn_6.getColumn();
		tableColumn_6.setWidth(100);
		tableColumn_6.setText("Context");
		


	}

	@Override
	public void setFocus() {

	}

	@Override
	public void propertyChange(PropertyChangeEvent event) {

		ResponseInfo repInfo = (ResponseInfo) event.getNewValue();
		
		tableViewer.setContentProvider(new ServiceAnnoContentProvider());
		tableViewer.setLabelProvider(new ServiceAnnoLabelProvider());
		List<CandidateServiceViewItem> input = new ArrayList<CandidateServiceViewItem>();

		Map<String, List<ProfileInfo>> result = repInfo.getTaskServices();
		
		for (String task : result.keySet()) {
			List<ProfileInfo> profiles = result.get(task);
			
			for (ProfileInfo p : profiles) {
				String serviceName = p.getServiceName();

				StringBuffer categoryName = new StringBuffer();
				for (Category c : p.getCategoryList()) {
					categoryName.append(c.getCategoryName()).append(",");
				}
				StringBuffer actorName = new StringBuffer();
				for (Actor a : p.getActorList()) {
					actorName.append(a.getName()).append(",");
				}
				StringBuffer inputName = new StringBuffer();
				for (IOModel i : p.getInputList()) {
					inputName.append(i.getParameterType()).append(",");
				}
				StringBuffer outputName = new StringBuffer();
				for (IOModel i : p.getOutputList()) {
					outputName.append(i.getParameterType()).append(",");
				}
				StringBuffer contextName = new StringBuffer();

				for (PECRModel m : p.getContext()) {
					contextName.append(m.getExpressionBody()).append(",");
				}

				CandidateServiceViewItem sa = new CandidateServiceViewItem(
						serviceName, actorName.toString(),
						categoryName.toString(), inputName.toString(),
						outputName.toString(), contextName.toString());
				input.add(sa);

			}
		}

		tableViewer.setInput(input);
	}
}
