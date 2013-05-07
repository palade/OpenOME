package edu.toronto.cs.openome_model.diagram.part;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.gef.Tool;
import org.eclipse.gef.palette.PaletteContainer;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteGroup;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.PaletteSeparator;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.gmf.runtime.diagram.ui.tools.UnspecifiedTypeConnectionTool;
import org.eclipse.gmf.runtime.diagram.ui.tools.UnspecifiedTypeCreationTool;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;

/**
 * @generated
 */
public class Openome_modelPaletteFactory {

	/**
	 * @generated
	 */
	public void fillPalette(PaletteRoot paletteRoot) {
		paletteRoot.add(createDefault1Group());
		paletteRoot.add(createContributions2Group());
		paletteRoot.add(createAssociations3Group());
	}

	/**
	 * Creates "Default" palette tool group
	 * @generated
	 */
	private PaletteContainer createDefault1Group() {
		PaletteGroup paletteContainer = new PaletteGroup(
				edu.toronto.cs.openome_model.diagram.part.Messages.Default1Group_title);
		paletteContainer.setId("createDefault1Group"); //$NON-NLS-1$
		paletteContainer
				.setDescription(edu.toronto.cs.openome_model.diagram.part.Messages.Default1Group_desc);
		paletteContainer.add(createActor1CreationTool());
		paletteContainer.add(createAgent2CreationTool());
		paletteContainer.add(createPosition3CreationTool());
		paletteContainer.add(createRole4CreationTool());
		paletteContainer.add(new PaletteSeparator());
		paletteContainer.add(createHardgoal6CreationTool());
		paletteContainer.add(createSoftgoal7CreationTool());
		paletteContainer.add(createTask8CreationTool());
		paletteContainer.add(createResource9CreationTool());
		paletteContainer.add(new PaletteSeparator());
		paletteContainer.add(createDependency11CreationTool());
		paletteContainer.add(new PaletteSeparator());
		paletteContainer.add(createDecomposition13CreationTool());
		paletteContainer.add(createMeansends14CreationTool());
		paletteContainer.add(new PaletteSeparator());
		return paletteContainer;
	}

	/**
	 * Creates "Contributions" palette tool group
	 * @generated
	 */
	private PaletteContainer createContributions2Group() {
		PaletteDrawer paletteContainer = new PaletteDrawer(
				edu.toronto.cs.openome_model.diagram.part.Messages.Contributions2Group_title);
		paletteContainer.setId("createContributions2Group"); //$NON-NLS-1$
		paletteContainer
				.setDescription(edu.toronto.cs.openome_model.diagram.part.Messages.Contributions2Group_desc);
		paletteContainer.add(createMake1CreationTool());
		paletteContainer.add(createSome2CreationTool());
		paletteContainer.add(createHelp3CreationTool());
		paletteContainer.add(createUnknown4CreationTool());
		paletteContainer.add(createHurt5CreationTool());
		paletteContainer.add(createSome6CreationTool());
		paletteContainer.add(createBreak7CreationTool());
		paletteContainer.add(createAND8CreationTool());
		paletteContainer.add(createOR9CreationTool());
		return paletteContainer;
	}

	/**
	 * Creates "Associations" palette tool group
	 * @generated
	 */
	private PaletteContainer createAssociations3Group() {
		PaletteDrawer paletteContainer = new PaletteDrawer(
				edu.toronto.cs.openome_model.diagram.part.Messages.Associations3Group_title);
		paletteContainer.setId("createAssociations3Group"); //$NON-NLS-1$
		paletteContainer
				.setDescription(edu.toronto.cs.openome_model.diagram.part.Messages.Associations3Group_desc);
		paletteContainer.add(createISA1CreationTool());
		paletteContainer.add(createCovers2CreationTool());
		paletteContainer.add(createIspartof3CreationTool());
		paletteContainer.add(createOccupies4CreationTool());
		paletteContainer.add(createPlays5CreationTool());
		paletteContainer.add(createINS6CreationTool());
		return paletteContainer;
	}

	/**
	 * @generated
	 */
	private ToolEntry createActor1CreationTool() {
		NodeToolEntry entry = new NodeToolEntry(
				edu.toronto.cs.openome_model.diagram.part.Messages.Actor1CreationTool_title,
				edu.toronto.cs.openome_model.diagram.part.Messages.Actor1CreationTool_desc,
				Collections
						.singletonList(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Actor_2001));
		entry.setId("createActor1CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramEditorPlugin
				.findImageDescriptor("/openome_model/icons/actor.gif")); //$NON-NLS-1$
		entry.setLargeIcon(edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramEditorPlugin
				.findImageDescriptor("/openome_model/icons/actor.gif")); //$NON-NLS-1$
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createAgent2CreationTool() {
		NodeToolEntry entry = new NodeToolEntry(
				edu.toronto.cs.openome_model.diagram.part.Messages.Agent2CreationTool_title,
				edu.toronto.cs.openome_model.diagram.part.Messages.Agent2CreationTool_desc,
				Collections
						.singletonList(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Agent_2002));
		entry.setId("createAgent2CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramEditorPlugin
				.findImageDescriptor("/openome_model/icons/agent.gif")); //$NON-NLS-1$
		entry.setLargeIcon(edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramEditorPlugin
				.findImageDescriptor("/openome_model/icons/agent.gif")); //$NON-NLS-1$
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createPosition3CreationTool() {
		NodeToolEntry entry = new NodeToolEntry(
				edu.toronto.cs.openome_model.diagram.part.Messages.Position3CreationTool_title,
				edu.toronto.cs.openome_model.diagram.part.Messages.Position3CreationTool_desc,
				Collections
						.singletonList(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Position_2003));
		entry.setId("createPosition3CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramEditorPlugin
				.findImageDescriptor("/openome_model/icons/position.gif")); //$NON-NLS-1$
		entry.setLargeIcon(edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramEditorPlugin
				.findImageDescriptor("/openome_model/icons/position.gif")); //$NON-NLS-1$
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createRole4CreationTool() {
		NodeToolEntry entry = new NodeToolEntry(
				edu.toronto.cs.openome_model.diagram.part.Messages.Role4CreationTool_title,
				edu.toronto.cs.openome_model.diagram.part.Messages.Role4CreationTool_desc,
				Collections
						.singletonList(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Role_2004));
		entry.setId("createRole4CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramEditorPlugin
				.findImageDescriptor("/openome_model/icons/role.gif")); //$NON-NLS-1$
		entry.setLargeIcon(edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramEditorPlugin
				.findImageDescriptor("/openome_model/icons/role.gif")); //$NON-NLS-1$
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createHardgoal6CreationTool() {
		ArrayList<IElementType> types = new ArrayList<IElementType>(5);
		types.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Goal_3001);
		types.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Goal_3005);
		types.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Goal_3009);
		types.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Goal_3013);
		types.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Goal_2005);
		NodeToolEntry entry = new NodeToolEntry(
				edu.toronto.cs.openome_model.diagram.part.Messages.Hardgoal6CreationTool_title,
				edu.toronto.cs.openome_model.diagram.part.Messages.Hardgoal6CreationTool_desc,
				types);
		entry.setId("createHardgoal6CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramEditorPlugin
				.findImageDescriptor("/openome_model/icons/hardgoal.gif")); //$NON-NLS-1$
		entry.setLargeIcon(edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramEditorPlugin
				.findImageDescriptor("/openome_model/icons/hardgoal.gif")); //$NON-NLS-1$
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createSoftgoal7CreationTool() {
		ArrayList<IElementType> types = new ArrayList<IElementType>(5);
		types.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Softgoal_3002);
		types.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Softgoal_3006);
		types.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Softgoal_3010);
		types.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Softgoal_3014);
		types.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Softgoal_2006);
		NodeToolEntry entry = new NodeToolEntry(
				edu.toronto.cs.openome_model.diagram.part.Messages.Softgoal7CreationTool_title,
				edu.toronto.cs.openome_model.diagram.part.Messages.Softgoal7CreationTool_desc,
				types);
		entry.setId("createSoftgoal7CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramEditorPlugin
				.findImageDescriptor("/openome_model/icons/softgoal.gif")); //$NON-NLS-1$
		entry.setLargeIcon(edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramEditorPlugin
				.findImageDescriptor("/openome_model/icons/softgoal.gif")); //$NON-NLS-1$
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createTask8CreationTool() {
		ArrayList<IElementType> types = new ArrayList<IElementType>(5);
		types.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Task_3004);
		types.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Task_3008);
		types.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Task_3012);
		types.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Task_3016);
		types.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Task_2007);
		NodeToolEntry entry = new NodeToolEntry(
				edu.toronto.cs.openome_model.diagram.part.Messages.Task8CreationTool_title,
				edu.toronto.cs.openome_model.diagram.part.Messages.Task8CreationTool_desc,
				types);
		entry.setId("createTask8CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramEditorPlugin
				.findImageDescriptor("/openome_model/icons/task.gif")); //$NON-NLS-1$
		entry.setLargeIcon(edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramEditorPlugin
				.findImageDescriptor("/openome_model/icons/task.gif")); //$NON-NLS-1$
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createResource9CreationTool() {
		ArrayList<IElementType> types = new ArrayList<IElementType>(5);
		types.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Resource_3003);
		types.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Resource_3007);
		types.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Resource_3011);
		types.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Resource_3015);
		types.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Resource_2008);
		NodeToolEntry entry = new NodeToolEntry(
				edu.toronto.cs.openome_model.diagram.part.Messages.Resource9CreationTool_title,
				edu.toronto.cs.openome_model.diagram.part.Messages.Resource9CreationTool_desc,
				types);
		entry.setId("createResource9CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramEditorPlugin
				.findImageDescriptor("/openome_model/icons/resource.gif")); //$NON-NLS-1$
		entry.setLargeIcon(edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramEditorPlugin
				.findImageDescriptor("/openome_model/icons/resource.gif")); //$NON-NLS-1$
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createDependency11CreationTool() {
		LinkToolEntry entry = new LinkToolEntry(
				edu.toronto.cs.openome_model.diagram.part.Messages.Dependency11CreationTool_title,
				edu.toronto.cs.openome_model.diagram.part.Messages.Dependency11CreationTool_desc,
				Collections
						.singletonList(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Dependency_4001));
		entry.setId("createDependency11CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramEditorPlugin
				.findImageDescriptor("/openome_model/icons/dependency.gif")); //$NON-NLS-1$
		entry.setLargeIcon(edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramEditorPlugin
				.findImageDescriptor("/openome_model/icons/dependency.gif")); //$NON-NLS-1$
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createDecomposition13CreationTool() {
		LinkToolEntry entry = new LinkToolEntry(
				edu.toronto.cs.openome_model.diagram.part.Messages.Decomposition13CreationTool_title,
				edu.toronto.cs.openome_model.diagram.part.Messages.Decomposition13CreationTool_desc,
				Collections
						.singletonList(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.AndDecomposition_4002));
		entry.setId("createDecomposition13CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramEditorPlugin
				.findImageDescriptor("/openome_model/icons/anddecomposition.gif")); //$NON-NLS-1$
		entry.setLargeIcon(edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramEditorPlugin
				.findImageDescriptor("/openome_model/icons/anddecomposition.gif")); //$NON-NLS-1$
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createMeansends14CreationTool() {
		LinkToolEntry entry = new LinkToolEntry(
				edu.toronto.cs.openome_model.diagram.part.Messages.Meansends14CreationTool_title,
				edu.toronto.cs.openome_model.diagram.part.Messages.Meansends14CreationTool_desc,
				Collections
						.singletonList(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.OrDecomposition_4003));
		entry.setId("createMeansends14CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramEditorPlugin
				.findImageDescriptor("/openome_model/icons/ordecomposition.gif")); //$NON-NLS-1$
		entry.setLargeIcon(edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramEditorPlugin
				.findImageDescriptor("/openome_model/icons/ordecomposition.gif")); //$NON-NLS-1$
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createMake1CreationTool() {
		LinkToolEntry entry = new LinkToolEntry(
				edu.toronto.cs.openome_model.diagram.part.Messages.Make1CreationTool_title,
				edu.toronto.cs.openome_model.diagram.part.Messages.Make1CreationTool_desc,
				Collections
						.singletonList(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.MakeContribution_4007));
		entry.setId("createMake1CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramEditorPlugin
				.findImageDescriptor("/openome_model/icons/contribution.png")); //$NON-NLS-1$
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createSome2CreationTool() {
		LinkToolEntry entry = new LinkToolEntry(
				edu.toronto.cs.openome_model.diagram.part.Messages.Some2CreationTool_title,
				edu.toronto.cs.openome_model.diagram.part.Messages.Some2CreationTool_desc,
				Collections
						.singletonList(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.SomePlusContribution_4009));
		entry.setId("createSome2CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramEditorPlugin
				.findImageDescriptor("/openome_model/icons/contribution.png")); //$NON-NLS-1$
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createHelp3CreationTool() {
		LinkToolEntry entry = new LinkToolEntry(
				edu.toronto.cs.openome_model.diagram.part.Messages.Help3CreationTool_title,
				edu.toronto.cs.openome_model.diagram.part.Messages.Help3CreationTool_desc,
				Collections
						.singletonList(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.HelpContribution_4005));
		entry.setId("createHelp3CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramEditorPlugin
				.findImageDescriptor("/openome_model/icons/contribution.png")); //$NON-NLS-1$
		entry.setLargeIcon(edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramEditorPlugin
				.findImageDescriptor("/openome_model/icons/contribution.png")); //$NON-NLS-1$
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createUnknown4CreationTool() {
		LinkToolEntry entry = new LinkToolEntry(
				edu.toronto.cs.openome_model.diagram.part.Messages.Unknown4CreationTool_title,
				edu.toronto.cs.openome_model.diagram.part.Messages.Unknown4CreationTool_desc,
				Collections
						.singletonList(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.UnknownContribution_4011));
		entry.setId("createUnknown4CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramEditorPlugin
				.findImageDescriptor("/openome_model/icons/contribution.png")); //$NON-NLS-1$
		entry.setLargeIcon(edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramEditorPlugin
				.findImageDescriptor("/openome_model/icons/contribution.png")); //$NON-NLS-1$
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createHurt5CreationTool() {
		LinkToolEntry entry = new LinkToolEntry(
				edu.toronto.cs.openome_model.diagram.part.Messages.Hurt5CreationTool_title,
				edu.toronto.cs.openome_model.diagram.part.Messages.Hurt5CreationTool_desc,
				Collections
						.singletonList(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.HurtContribution_4006));
		entry.setId("createHurt5CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramEditorPlugin
				.findImageDescriptor("/openome_model/icons/contribution.png")); //$NON-NLS-1$
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createSome6CreationTool() {
		LinkToolEntry entry = new LinkToolEntry(
				edu.toronto.cs.openome_model.diagram.part.Messages.Some6CreationTool_title,
				edu.toronto.cs.openome_model.diagram.part.Messages.Some6CreationTool_desc,
				Collections
						.singletonList(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.SomeMinusContribution_4010));
		entry.setId("createSome6CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramEditorPlugin
				.findImageDescriptor("/openome_model/icons/contribution.png")); //$NON-NLS-1$
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createBreak7CreationTool() {
		LinkToolEntry entry = new LinkToolEntry(
				edu.toronto.cs.openome_model.diagram.part.Messages.Break7CreationTool_title,
				edu.toronto.cs.openome_model.diagram.part.Messages.Break7CreationTool_desc,
				Collections
						.singletonList(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.BreakContribution_4008));
		entry.setId("createBreak7CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramEditorPlugin
				.findImageDescriptor("/openome_model/icons/contribution.png")); //$NON-NLS-1$
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createAND8CreationTool() {
		LinkToolEntry entry = new LinkToolEntry(
				edu.toronto.cs.openome_model.diagram.part.Messages.AND8CreationTool_title,
				edu.toronto.cs.openome_model.diagram.part.Messages.AND8CreationTool_desc,
				Collections
						.singletonList(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.AndContribution_4012));
		entry.setId("createAND8CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramEditorPlugin
				.findImageDescriptor("/openome_model/icons/contribution.png")); //$NON-NLS-1$
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createOR9CreationTool() {
		LinkToolEntry entry = new LinkToolEntry(
				edu.toronto.cs.openome_model.diagram.part.Messages.OR9CreationTool_title,
				edu.toronto.cs.openome_model.diagram.part.Messages.OR9CreationTool_desc,
				Collections
						.singletonList(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.OrContribution_4013));
		entry.setId("createOR9CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramEditorPlugin
				.findImageDescriptor("/openome_model/icons/contribution.png")); //$NON-NLS-1$
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createISA1CreationTool() {
		LinkToolEntry entry = new LinkToolEntry(
				edu.toronto.cs.openome_model.diagram.part.Messages.ISA1CreationTool_title,
				edu.toronto.cs.openome_model.diagram.part.Messages.ISA1CreationTool_desc,
				Collections
						.singletonList(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.IsAAssociation_4014));
		entry.setId("createISA1CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramEditorPlugin
				.findImageDescriptor("/openome_model/icons/contribution.png")); //$NON-NLS-1$
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createCovers2CreationTool() {
		LinkToolEntry entry = new LinkToolEntry(
				edu.toronto.cs.openome_model.diagram.part.Messages.Covers2CreationTool_title,
				edu.toronto.cs.openome_model.diagram.part.Messages.Covers2CreationTool_desc,
				Collections
						.singletonList(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.CoversAssociation_4015));
		entry.setId("createCovers2CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramEditorPlugin
				.findImageDescriptor("/openome_model/icons/contribution.png")); //$NON-NLS-1$
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createIspartof3CreationTool() {
		LinkToolEntry entry = new LinkToolEntry(
				edu.toronto.cs.openome_model.diagram.part.Messages.Ispartof3CreationTool_title,
				edu.toronto.cs.openome_model.diagram.part.Messages.Ispartof3CreationTool_desc,
				Collections
						.singletonList(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.IsPartOfAssociation_4017));
		entry.setId("createIspartof3CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramEditorPlugin
				.findImageDescriptor("/openome_model/icons/contribution.png")); //$NON-NLS-1$
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createOccupies4CreationTool() {
		LinkToolEntry entry = new LinkToolEntry(
				edu.toronto.cs.openome_model.diagram.part.Messages.Occupies4CreationTool_title,
				edu.toronto.cs.openome_model.diagram.part.Messages.Occupies4CreationTool_desc,
				Collections
						.singletonList(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.OccupiesAssociation_4016));
		entry.setId("createOccupies4CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramEditorPlugin
				.findImageDescriptor("/openome_model/icons/contribution.png")); //$NON-NLS-1$
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createPlays5CreationTool() {
		LinkToolEntry entry = new LinkToolEntry(
				edu.toronto.cs.openome_model.diagram.part.Messages.Plays5CreationTool_title,
				edu.toronto.cs.openome_model.diagram.part.Messages.Plays5CreationTool_desc,
				Collections
						.singletonList(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.PlaysAssociation_4018));
		entry.setId("createPlays5CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramEditorPlugin
				.findImageDescriptor("/openome_model/icons/contribution.png")); //$NON-NLS-1$
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createINS6CreationTool() {
		LinkToolEntry entry = new LinkToolEntry(
				edu.toronto.cs.openome_model.diagram.part.Messages.INS6CreationTool_title,
				edu.toronto.cs.openome_model.diagram.part.Messages.INS6CreationTool_desc,
				Collections
						.singletonList(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.INSAssociation_4019));
		entry.setId("createINS6CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramEditorPlugin
				.findImageDescriptor("/openome_model/icons/contribution.png")); //$NON-NLS-1$
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private static class NodeToolEntry extends ToolEntry {

		/**
		 * @generated
		 */
		private final List<IElementType> elementTypes;

		/**
		 * @generated
		 */
		private NodeToolEntry(String title, String description,
				List<IElementType> elementTypes) {
			super(title, description, null, null);
			this.elementTypes = elementTypes;
		}

		/**
		 * @generated
		 */
		public Tool createTool() {
			Tool tool = new UnspecifiedTypeCreationTool(elementTypes);
			tool.setProperties(getToolProperties());
			return tool;
		}
	}

	/**
	 * @generated
	 */
	private static class LinkToolEntry extends ToolEntry {

		/**
		 * @generated
		 */
		private final List<IElementType> relationshipTypes;

		/**
		 * @generated
		 */
		private LinkToolEntry(String title, String description,
				List<IElementType> relationshipTypes) {
			super(title, description, null, null);
			this.relationshipTypes = relationshipTypes;
		}

		/**
		 * @generated
		 */
		public Tool createTool() {
			Tool tool = new UnspecifiedTypeConnectionTool(relationshipTypes);
			tool.setProperties(getToolProperties());
			return tool;
		}
	}
}
