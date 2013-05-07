package hello.Provider;

import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;

import org.eclipse.jface.viewers.Viewer;


public class ServiceAnnoContentProvider implements IStructuredContentProvider {

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void inputChanged(Viewer arg0, Object arg1, Object arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public Object[] getElements(Object arg0) {
		// TODO Auto-generated method stub
		 if(arg0 instanceof List){
             return ((List)arg0).toArray();
         }else{
             return new Object[0];
         }
	}

}
