package oaw4.demo.classic.uml.meta;

import org.openarchitectureware.core.meta.core.ElementSet;
import org.openarchitectureware.meta.uml.classifier.Attribute;

public abstract class AbstractEntity  extends org.openarchitectureware.meta.uml.classifier.Class{
	
	/**
	 * @return All elements which have Column stereotype applied
	 */
	public ElementSet Column(){
		ElementSet columns = new ElementSet();
		ElementSet attributes = getAllAttributes();
		
		for (Object object : attributes) {
			Attribute attribute = (Attribute)object;
			if(attribute.getMetaClass().getSimpleName().equals("Column")) //TODO hardocoding - find a better way
				columns.add(attribute);
		}

		return columns;
	}
	
	
	/**
	 * @return - All columns which have searchable set to true
	 */
	public ElementSet Findable(){
		ElementSet columns = Column();
		ElementSet findables = new ElementSet();
		
		for (Object object : columns) {
			Column col = (Column)object;
	
			if(col.isSearchable())
				findables.add(col);
		}

		return findables;
	}
	
	/** This method returns all attributes , of this class and of all superclasses
	 * @return
	 */
	public ElementSet getAllAttributes(){
		ElementSet attributes = new ElementSet();
		org.openarchitectureware.meta.uml.classifier.Class cls = this;
		
		do{
			attributes.addAll(cls.Attribute());
			cls = cls.SuperClass();
		}while(cls != null);
		
		return attributes;
	}
}
