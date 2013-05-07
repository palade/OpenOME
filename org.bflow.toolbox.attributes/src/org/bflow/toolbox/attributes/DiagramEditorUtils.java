package org.bflow.toolbox.attributes;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.commands.SetBoundsCommand;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITextAwareEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.figures.BorderedNodeFigure;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;

/**
 * Provides some useful methods to manipulate a diagram and its elements.
 * @author Arian Storch
 * @since 08/06/11
 *
 */
public class DiagramEditorUtils {
	
	/**
	 * Sets the properties of the font used by the edit part.<p/>
	 * If fontname is null, the previous font is used.<br/>
	 * If size is -1, the previous size is used.<br/>
	 * If style is -1, the previous style is used. Style parameter uses SWT constants like SWT.NORMAL, SWT.BOLD, SWT.ITALIC
	 * @param editpart edit part to edit
	 * @param fontName system name of the font
	 * @param size size of the font
	 * @param style style; look at SWT styles
	 */
	public static void setElementFont(ShapeNodeEditPart editpart,
			String fontName, int size, int style) {
		EditPart child = editpart.getPrimaryChildEditPart();

		if (child instanceof ITextAwareEditPart) {

			ITextAwareEditPart g = (ITextAwareEditPart) child;
			
			Display display = PlatformUI.getWorkbench().getDisplay();
			
			if(size == -1)
				size = g.getFigure().getFont().getFontData()[0].getHeight();
			
			if(style == -1)
				style = g.getFigure().getFont().getFontData()[0].getStyle();
			
			if(fontName == null)
				fontName = g.getFigure().getFont().getFontData()[0].getName();
			
			Font f = new Font(display, fontName, size, style);

			g.getFigure().setFont(f);
		}
	}
	
	/**
	 * Sets the location of the element.
	 * @param editpart edit part to set
	 * @param location new location
	 */
	public static void setElementLocation(ShapeNodeEditPart editpart, Point location) {
		SetBoundsCommand command = new SetBoundsCommand(editpart.getEditingDomain(), "Element relocate", 
				new EObjectAdapter((View)editpart.getModel()), location);
				
		editpart.getDiagramEditDomain().getDiagramCommandStack().execute(
				new ICommandProxy(command));
	}
	
	/**
	 * Moves the element along the x and y values.
	 * @param editpart edit part to move
	 * @param x value to move the element along the abscissae
	 * @param y value to move the element along the ordinate
	 */
	public static void moveElementLocation(ShapeNodeEditPart editpart, int x, int y) {		
		IFigure fig = editpart.getFigure();
		BorderedNodeFigure bnf = (BorderedNodeFigure)fig;
		
		Point old = bnf.getLocation();
		
		Point location = new Point(old.x+x, old.y+y);
		
		setElementLocation(editpart, location);
	}

}
