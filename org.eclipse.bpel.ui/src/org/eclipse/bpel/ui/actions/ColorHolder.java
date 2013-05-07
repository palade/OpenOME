package org.eclipse.bpel.ui.actions;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;

public class ColorHolder {
	private static RGB color = new RGB(124,205,124);

	public static RGB getColor() {
		return color;
	}

	public static void setColor(RGB color) {
		ColorHolder.color = color;
	}
	
	
}
