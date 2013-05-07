package hello.actions;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class ServicePerspective implements IPerspectiveFactory {

	@Override
	public void createInitialLayout(IPageLayout arg0) {
		// TODO Auto-generated method stub
		String editerArea = arg0.getEditorArea();
		
		IFolderLayout left=arg0.createFolder("left", IPageLayout.LEFT , 0.25f, editerArea);
	
		//left.addView("org.eclipse.t11.view.PersonalView");
		left.addView("Hello.view2");
		
		IFolderLayout top2=  arg0.createFolder("top", IPageLayout.TOP, 1f, editerArea);
		
		top2.addView("Hello.view3");
		
		arg0.setEditorAreaVisible(true);
		

	}

}
