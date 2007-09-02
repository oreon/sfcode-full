package oaw4.demo.classic.uml.meta;

import org.openarchitectureware.core.meta.core.ElementSet;
import org.openarchitectureware.meta.uml.classifier.Attribute;

public class Entity extends org.openarchitectureware.meta.uml.classifier.Class {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// nothing to do in the simplest case
	
	public ElementSet Findable(){
		ElementSet findables = new ElementSet();
		ElementSet attributes = getAllAttributes();
		
		for (Object object : attributes) {
			Attribute attribute = (Attribute)object;
			if(attribute.getMetaClass().getSimpleName().equals("Findable")) //TODO hardocoding
				findables.add(attribute);
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