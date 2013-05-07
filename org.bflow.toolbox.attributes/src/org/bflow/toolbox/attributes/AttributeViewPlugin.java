package org.bflow.toolbox.attributes;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class AttributeViewPlugin extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "org.bflow.toolbox.attributeadapter";

	// The shared instance
	private static AttributeViewPlugin plugin;
	
	/**
	 * The constructor
	 */
	public AttributeViewPlugin() {
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
		
		registerDynamicEMF();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static AttributeViewPlugin getInstance() {
		return plugin;
	}
	
	/**
	 * Registers dynamic emf objects that are used by the add-on plugin.
	 */
	private void registerDynamicEMF() {
		EcoreFactory theCoreFactory = EcoreFactory.eINSTANCE;

		EClass addonEClass = theCoreFactory.createEClass();
		addonEClass.setName("AddonAttributes");
		addonEClass.setInstanceClassName("org.modellingaddons.attributes.eclass");
		
		EClass attrEClass = theCoreFactory.createEClass();
		attrEClass.setName("Attribute");

		EPackage addonEPackage = theCoreFactory.createEPackage();
		addonEPackage.setName("AddonAttributesPackage");
		addonEPackage.setNsPrefix("addon");
		addonEPackage.setNsURI("http://org.bflow.addon");

		EReference addonAttributes_attributes = theCoreFactory
				.createEReference();
		addonAttributes_attributes.setName("attributes");
		addonAttributes_attributes.setEType(attrEClass);
		addonAttributes_attributes
				.setUpperBound(EStructuralFeature.UNBOUNDED_MULTIPLICITY);
		addonAttributes_attributes.setContainment(true);

		// erzeugen der attribute
		EcorePackage theCorePackage = EcorePackage.eINSTANCE;

		EAttribute attrId = theCoreFactory.createEAttribute();
		attrId.setName("id");
		attrId.setEType(theCorePackage.getEString());

		EAttribute attrName = theCoreFactory.createEAttribute();
		attrName.setName("name");
		attrName.setEType(theCorePackage.getEString());

		EAttribute attrValue = theCoreFactory.createEAttribute();
		attrValue.setName("value");
		attrValue.setEType(theCorePackage.getEString());

		// hinzufügen der referenzen und eigenschaften
		addonEClass.getEStructuralFeatures().add(addonAttributes_attributes);

		attrEClass.getEStructuralFeatures().add(attrId);
		attrEClass.getEStructuralFeatures().add(attrName);
		attrEClass.getEStructuralFeatures().add(attrValue);

		// dem Paket hinzufügen
		addonEPackage.getEClassifiers().add(addonEClass);
		addonEPackage.getEClassifiers().add(attrEClass);

		EPackage.Registry.INSTANCE.put("http://org.bflow.addon", addonEPackage);

		AttributeFile.addonEClass = addonEClass;
		AttributeFile.attributeEClass = attrEClass;
		AttributeFile.addonEPackage = addonEPackage;
		AttributeFile.attributeId = attrId;
		AttributeFile.attributeName = attrName;
		AttributeFile.attributeValue = attrValue;
		AttributeFile.addon_attributes = addonAttributes_attributes;
	}

}
