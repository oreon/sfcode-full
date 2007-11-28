package oaw4.demo.classic.uml.meta;

import org.openarchitectureware.core.meta.core.ElementSet;
import org.openarchitectureware.meta.uml.classifier.Class;

/** Reperesents a class containing executable logic
 * @author jsingh
 *
 */
public class Service extends org.openarchitectureware.meta.uml.classifier.Class{

	public ElementSet getAllAttribs(){
		return getAttributesForThisClassAndSuperClasses();
		
	}
	
	/**
	 * @return
	 * FIXME : Duplicate code - find a way to reuse in AbstractEntity
	 */
	private ElementSet getAttributesForThisClassAndSuperClasses() {
		ElementSet attributes = new ElementSet();

		ElementSet superClasses = SuperClasss();

		for (Object object : superClasses) {
			Class clazz = (Class) object;
			attributes.addAll(clazz.Attribute());
		}

		attributes.addAll(Attribute());
		
		return attributes;
	}
}
