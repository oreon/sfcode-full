package org.wc.generatorutil;

import java.util.List;

import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Package;

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
	
	public static String getInterfaces(Class clazz){
		StringBuilder buffer = new StringBuilder();
		List<Interface> interfaces = clazz.getImplementedInterfaces();
		
		// Stereotype myStereotype =
		//	 myProfile.getOwnedStereotype("MyExtendedClass");
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
