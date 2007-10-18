package oaw4.demo.classic.uml.meta;

import oaw4.demo.classic.uml.extend.ClassUtil;

import org.openarchitectureware.core.meta.core.ElementSet;
import org.openarchitectureware.meta.uml.Type;
import org.openarchitectureware.meta.uml.classifier.AssociationEnd;
import org.openarchitectureware.meta.uml.classifier.Attribute;
import org.openarchitectureware.meta.uml.classifier.Class;

public abstract class AbstractEntity extends
		org.openarchitectureware.meta.uml.classifier.Class {
	
	private String displayName;

	/**
	 * @return All elements which have Column stereotype applied
	 */
	public ElementSet Column() {
		ElementSet columns = new ElementSet();
		ElementSet attributes = getAllAttributes();

		for (Object object : attributes) {
			Attribute attribute = (Attribute) object;

			// TODO hardcoding find a better way to determine this
			if (isAttributeColumn(attribute))
				columns.add(attribute);
		}

		return columns;
	}

	private boolean isAttributeColumn(Attribute attribute) {
		return attribute.getMetaClass().getSimpleName().equals("Column");
	}

	/**
	 * @return - All columns which have searchable set to true
	 */
	public ElementSet Findable() {
		ElementSet columns = Column();
		ElementSet findables = new ElementSet();

		for (Object object : columns) {
			Column col = (Column) object;

			if (col.isSearchable() || col.isUnique())
				findables.add(col);
		}

		return findables;
	}

	/**
	 * @return - All columns which have a unique constraint
	 */
	public ElementSet Unique() {
		ElementSet columns = Column();
		ElementSet findables = new ElementSet();

		for (Object object : columns) {
			Column col = (Column) object;
			System.out.println("checking if " + col.NameS() + " is unique");

			if (col.isUnique())
				findables.add(col);
		}

		return findables;
	}

	/**
	 * This method returns all attributes , of this class and of all
	 * superclasses
	 * 
	 * @return
	 */
	public ElementSet getAllAttributes() {

		ElementSet attributes = getAttributesForThisClassAndSuperClasses();

		//clear existing type modifiers
		for (Object obj : attributes) {
			Attribute attribute = (Attribute) obj;
			attribute.setTypeModifier(null);
		}

		ElementSet embeddables = getAllContianedAssociations();

		for (Object object : embeddables) {
			AssociationEnd ae = (AssociationEnd) object;

			ElementSet assocAttributes = ae.Class().Attribute();
			for (Object obj : assocAttributes) {
				Attribute attribute = (Attribute) obj;
				attribute.setTypeModifier(ae.NameS());
			}

			
			//System.out.println(NameS() + " has assoc " + assocAttributes.size());
			attributes.addAll(ae.Class().Attribute());
		}
		return attributes;
	}

	public ElementSet getAttributesForThisClassAndSuperClasses() {
		ElementSet attributes = new ElementSet();

		ElementSet superClasses = SuperClasss();

		for (Object object : superClasses) {
			Class clazz = (Class) object;
			attributes.addAll(clazz.Attribute());
		}

		attributes.addAll(Attribute());

		return attributes;
	}

	/**
	 * This method returns all attributes which are not Columns superclasses
	 * 
	 * @return
	 */
	public ElementSet NonColumnAttribute() {
		ElementSet attributes = getAttributesForThisClassAndSuperClasses();
		ElementSet nonColAttribs = new ElementSet();

		for (Object object : attributes) {
			Attribute attribute = (Attribute) object;

			if (!isAttributeColumn(attribute))
				nonColAttribs.add(attribute);
		}

		return nonColAttribs;
	}

	/**
	 * Get all contained associations of this class and all superclasses
	 * 
	 * @return
	 */
	public ElementSet getAllContianedAssociations() {
		ElementSet embeddables = new ElementSet();
		embeddables.addAll(getContainedAssociations(this));

		ElementSet superclasses = SuperClasss();
		for (Object object : superclasses) {
			embeddables.addAll(getContainedAssociations((Class) object));
		}

		return embeddables;
	}

	/**
	 * We look for all embedded components and one on associations
	 * 
	 * @param cls
	 * @return
	 */
	private ElementSet getContainedAssociations(Class cls) {
		ElementSet associations = cls.AssociationEnd();
		ElementSet embeddables = new ElementSet();
		for (Object object : associations) {
			AssociationEnd ae = (AssociationEnd) object;

			if (ClassUtil.isAssociationOneOnOne(ae) && ae.Opposite().isNavigable()) {
				embeddables.add(ae.Opposite());
			}
		}
		return embeddables;
	}

	/**
	 * @param ae
	 * @return
	 */
	private boolean isAssociationEmbeddableContainment(AssociationEnd ae) {
		
		boolean result = ClassUtil.isAssociationOneOnOne(ae) && ae.Opposite().isNavigable() && ae
				.isComposition();
	
		
		return result;
	}
	
	/** This value indicates how entitie's should be shown in associations
	 * for instance for a person it can be "lastName, firstName" for product
	 * it can be the product name
	 * @return
	 */
	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
}
