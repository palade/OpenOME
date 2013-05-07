package org.bflow.toolbox.addons.components;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.bflow.toolbox.addons.core.exceptions.ComponentException;
import org.bflow.toolbox.addons.core.model.IComponent;
import org.bflow.toolbox.addons.interfaces.IDiagramExportComponent;
import org.bflow.toolbox.attributes.AttributeFile;
import org.bflow.toolbox.attributes.AttributeViewPart;
import org.bflow.toolbox.interchange.model.ExportDescription;
import org.bflow.toolbox.interchange.store.ExportscriptStore;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.emf.core.util.EMFCoreUtil;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;

/**
 * Implements <code>IComponent</code> for exporting a gmf diagram.
 * <p/>
 * The component expects a file object to the diagram as input source. If it
 * isn't a <code>ComponentException</code> is thrown.<br/>
 * The exported file is saved in the local OS temp folder and will be deleted on
 * exit.<br/>
 * The export runs by using the <code>org.bflow.toolbox.export.xslt</code>
 * plugin. Look there for further informations.<br/>
 * After the export has been finished a file object of the exported file will
 * returned through <code>transformOutput()</code> method.
 * 
 * @author Arian Storch
 * @since 12/10/09
 * @version 23/09/10
 */
public class DiagramExportComponent implements IDiagramExportComponent {
	private File target;
	private File source;
	private ExportDescription xDescription;

	private boolean finished = false;

	/**
	 * Default constructor.
	 */
	public DiagramExportComponent() {
	}

	/**
	 * Default constructor.
	 * 
	 * @param xDescription
	 *            export description
	 * 
	 * @deprecated
	 */
	public DiagramExportComponent(ExportDescription xDescription) {
		this.xDescription = xDescription;
	}

	@Override
	public void finish() {
		if (target != null)
			target.deleteOnExit();
	}

	@Override
	public boolean hasFinished() {
		return finished;
	}

	@Override
	public void init() {
	}

	@Override
	public void invoke() throws ComponentException {
		try {
			target = File.createTempFile("addontmp", xDescription
					.getFileExtension());

			xDescription.parse();
			
			// Auslesen der selektierten Objekte
			IStructuredSelection sel = (IStructuredSelection) AttributeViewPart.getInstance().getSelection();
			
			if(sel == null)
				sel = new StructuredSelection();
			
			ArrayList<String> selElements = new ArrayList<String>();
			
			for(Object obj:sel.toArray()) {
				if(obj instanceof ShapeNodeEditPart) {
					selElements.add(EMFCoreUtil.getProxyID(((ShapeNodeEditPart)obj).resolveSemanticElement()));
				}
			}
			
			// Hinzufügen des Attributs
			AttributeFile aF = AttributeViewPart.getInstance().getAttributeFile();
			
			if (aF != null) {
				aF.load();
			}
			
			for(String id:selElements) {
				aF.add(id, "marked", "true");
			}
			
			// speichern wenn nötig
			if(selElements.size() > 0)
				aF.save();
			
			// export durchführen
			xDescription.run(source, target, false, false);
			
			// attribut wieder entfernen
			for(String id:selElements) {
				aF.remove(id, "marked");
			}
			
			// speichern wenn nötig
			if(selElements.size() > 0)
				aF.save();
			
		} catch (IOException ex) {
			throw new ComponentException("could not create temp file");
		} finally {
			this.finished = true;
		}

	}

	@Override
	public void transformInput(Object inputSource) throws ComponentException {
		if(inputSource == null)
			throw new ComponentException("Quelle ist null");
		
		if (inputSource.getClass() != File.class)
			throw new ComponentException(
					"input source has not exspected format");

		source = (File) inputSource;
	}

	@Override
	public Object transformOutput() throws ComponentException {
		return target;
	}

	@Override
	public String getDescription(String abbreviation) {

		if (abbreviation.equalsIgnoreCase("de")) {
			String str = "Exportiert das zu bearbeitende Diagramm in ein angegebenes Format zur weiteren Verarbeitung"
					+ " für das externe Programm";

			return str;
		}

		String str = "Exports the diagram in a specified format which shall be processed by an external program";

		return str;

	}

	@Override
	public void setExportDescription(ExportDescription exd) {
		this.xDescription = exd;
	}

	@Override
	public boolean isValid() {
		return true;
	}

	@Override
	public String getDisplayName() {
		return "Diagram export";
	}

	@Override
	public boolean canLinkWith(IComponent component) {
		return false;
	}

	@Override
	public boolean hasParams() {
		return true;
	}

	@Override
	public void setParams(String param) {
		this.xDescription = ExportscriptStore.getExportDescription(param);
	}

}
