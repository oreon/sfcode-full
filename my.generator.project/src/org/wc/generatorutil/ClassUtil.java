package org.wc.generatorutil;

import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Property;

public class ClassUtil {
	Operation operation;
	//static org.eclipse.uml2.uml.Class cls;

	public static void main(String[] args) {
		// cls.getPackage().
		// Association assoc;
		// assoc.getMemberEnds();
		// cls.getA
		// cls.
	}
	
	public EList<Property> getAttributesIncludingSuperclasses(Class cls){
		EList<Property> lstAttributes = cls.getAllAttributes();
		EList<Classifier> lstParents = cls.allParents();
		for (Classifier classifier : lstParents) {
			lstAttributes.addAll(classifier.getAllAttributes());
		}
		return lstAttributes;
	}
	
	public static String getInterfaces(Class clazz){
		StringBuilder buffer = new StringBuilder();
		List<Interface> interfaces = clazz.getImplementedInterfaces();
		
		//clazz.getAttributes		
		Property prop;
		//prop.g
		
		if(!interfaces.isEmpty())
			buffer.append(" implements ");

		for (int i = 0; i < interfaces.size(); i++) {
			buffer.append(interfaces.get(i).getName());

			if (i < (interfaces.size() - 1)) // add comma to all but last
				buffer.append(", ");
		}
		
		return buffer.toString();
	}

	public static String getPackageName(Class cls) {
		String result = "";
		for (Package pck = cls.getPackage(); pck != null; pck = pck.getNestingPackage()) {
			result = pck.getName() + (result.length() > 0 ? "." + result : "");
		}
		return result;
	}
}
