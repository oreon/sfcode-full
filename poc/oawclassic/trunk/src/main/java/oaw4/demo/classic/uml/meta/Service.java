package oaw4.demo.classic.uml.meta;

import java.util.List;

import org.apache.commons.collections.ListUtils;
import org.openarchitectureware.core.meta.core.ElementSet;
import org.openarchitectureware.meta.uml.classifier.Class;
import org.openarchitectureware.meta.uml.classifier.Interface;
import org.openarchitectureware.meta.uml.classifier.Operation;

/** Reperesents a class containing executable logic
 * @author jsingh
 *
 */
public class Service extends org.openarchitectureware.meta.uml.classifier.Class{

	public ElementSet getAllAttribs(){
		return getAttributesForThisClassAndSuperClasses();
	}
	
	/** Will get the operations for all implemented interfaces
	 * @return
	 */
	public ElementSet getOperationsForInterfaces(){
		List<Interface> interfaces = Interface().toList();
		ElementSet operations = new ElementSet();

		for (int i = 0; i < interfaces.size(); i++) {
			operations.addAll(interfaces.get(i).Operation());
		}
		
		return operations;
	}
	
	public ElementSet getAllOperations(){
		//return ListUtils.sum(getOperationsForInterfaces(), Operation());
		ElementSet allOps = new ElementSet();
		allOps.addAll(Operation());
		allOps.addAll(getOperationsForInterfaces());
		return allOps;
		
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
	
	
	public Interface getFirstInterface(){
		if(!Interface().isEmpty())
			return (Interface) Interface().get(0);
		return null;
	}
}
