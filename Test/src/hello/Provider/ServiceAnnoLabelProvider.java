package hello.Provider;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

public class ServiceAnnoLabelProvider extends LabelProvider implements
		ITableLabelProvider {

	@Override
	public Image getColumnImage(Object arg0, int arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getColumnText(Object element, int columnIndex) {
		// TODO Auto-generated method stub
		if (element instanceof CandidateServiceViewItem) {
			CandidateServiceViewItem p = (CandidateServiceViewItem) element;
			if (columnIndex == 0) {
				return p.getServiceName();
			} else if (columnIndex == 1) {
				return p.getCategory();
			} else if (columnIndex == 2) {
				return p.getActor();
			} else if (columnIndex == 3) {
				return p.getInput();
			} else if (columnIndex == 4) {
				return p.getOutput();
			} else if (columnIndex == 5) {
				return p.getContext();
			}
		}
		return null;
	}

}
