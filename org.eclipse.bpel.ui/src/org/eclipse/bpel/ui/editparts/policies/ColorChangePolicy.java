package org.eclipse.bpel.ui.editparts.policies;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.editpolicies.SelectionEditPolicy;

public class ColorChangePolicy extends SelectionEditPolicy {

	@Override
	protected void hideSelection() {
		// TODO Auto-generated method stub
		IFigure node = (IFigure)getHostFigure();
		node.setBackgroundColor(ColorConstants.white);

	}

	@Override
	protected void showSelection() {
		// TODO Auto-generated method stub
		IFigure node = (IFigure)getHostFigure();
		System.out.println("is op : "+node.isOpaque());
		node.setOpaque(true);
		node.setForegroundColor(ColorConstants.green);
		node.setBackgroundColor(ColorConstants.green);
		
		System.out.println("show---------------------------------------------------------");
	}

}
