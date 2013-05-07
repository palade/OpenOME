package hello.actions;

import java.util.ArrayList;
import java.util.List;

import hello.Provider.ServiceAnnoContentProvider;
import hello.Provider.ServiceAnnoLabelProvider;
import hello.Provider.ServiceAnnotation;

import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Table;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.jface.viewers.CheckboxTableViewer;

public class PropertyView extends ViewPart {
	private Table table;
	public PropertyView() {
	}

	@Override
	public void createPartControl(Composite parent) {
		
		TableViewer tableViewer = new TableViewer(parent, SWT.BORDER | SWT.FULL_SELECTION);
		table = tableViewer.getTable();
		table.setBounds(23, 165, 535, 172);
		table.setHeaderVisible(true);
		TableViewerColumn tableViewerColumn = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tableColumn = tableViewerColumn.getColumn();
		tableColumn.setWidth(100);
		tableColumn.setText("1");
		
		TableViewerColumn tableViewerColumn_3 = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tableColumn_3 = tableViewerColumn_3.getColumn();
		tableColumn_3.setWidth(100);
		tableColumn_3.setText("44555");
		
		TableViewerColumn tableViewerColumn_2 = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tableColumn_2 = tableViewerColumn_2.getColumn();
		tableColumn_2.setWidth(100);
		tableColumn_2.setText("3");
		
	
		// TODO Auto-generated method stub
		tableViewer.setContentProvider(new ServiceAnnoContentProvider());
		tableViewer.setLabelProvider(new ServiceAnnoLabelProvider());
		List l =  new ArrayList();
		ServiceAnnotation sa = new ServiceAnnotation();
		sa.setPro("1");
		sa.setValue("v");
		sa.setLang("3");
		l.add(sa);
		tableViewer.setInput(l);
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}
}
