package oaw4.demo.classic.uml.meta;

import org.openarchitectureware.core.meta.core.ElementSet;
import org.openarchitectureware.meta.uml.Type;
import org.openarchitectureware.meta.uml.classifier.AssociationEnd;
import org.openarchitectureware.meta.uml.classifier.Attribute;
import org.openarchitectureware.meta.uml.classifier.Class;

public abstract class AbstractEntity extends
		org.openarchitectureware.meta.uml.classifier.Class {

	/**
	 * @return All elements which have Column stereotype applied
	 */
	public ElementSet Column() {
		ElementSet columns = new ElementSet();
		ElementSet attributes = getAllAttributes();

		for (Object object : attributes) {
			Attribute attribute = (Attribute) object;
			
			// TODO hardocoding find a better way to determine this
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

		ElementSet embeddables = getAllContianedAssociations();

		for (Object object : embeddables) {
			AssociationEnd ae = (AssociationEnd) object;
			
			ElementSet assocAttributes = ae.Class().Attribute();
			for (Object obj : assocAttributes) {
				Attribute attribute = (Attribute) obj;
				attribute.setTypeModifier(ae.NameS());
			}
			
			attributes.addAll(ae.Class().Attribute());
		}

		System.out.println(NameS() + " has " + attributes.size());
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
	 * This method returns all attributes which are not Columns
	 * superclasses
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

	private ElementSet getContainedAssociations(Class cls) {
		ElementSet associations = cls.AssociationEnd();
		ElementSet embeddables = new ElementSet();
		for (Object object : associations) {
			AssociationEnd ae = (AssociationEnd) object;

			if ((!ae.isMultiple()) && ae.Opposite().isNavigable()
					&& ae.isComposition()) {
				embeddables.add(ae.Opposite());

				ElementSet attribs = ae.Opposite().Class().Attribute();
				for (int i = 0; i < attribs.size(); i++) {
					Attribute attrib = (Attribute) attribs.get(i);
					if (attrib instanceof Column) {
						((Column) attrib).setContainerName(ae.Opposite()
								.NameS());
					}

				}
			}
		}
		return embeddables;
	}
}
