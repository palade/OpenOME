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
		 if (element instanceof ServiceAnnotation){
			 ServiceAnnotation p = (ServiceAnnotation)element;
             if(columnIndex == 0){
                 return p.getPro();
             }else if(columnIndex == 1){
                 return p.getValue();
             }else if (columnIndex ==2){
                 return p.getLang();
             }
         }
         return null;
	}

}
