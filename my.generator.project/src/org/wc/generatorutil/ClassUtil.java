package org.wc.generatorutil;

import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Package;

public class ClassUtil {
	Operation operation;
	static org.eclipse.uml2.uml.Class cls;

	public static void main(String[] args) {
		// cls.getPackage().
		// Association assoc;
		// assoc.getMemberEnds();
		// cls.getA
		// cls.
	}

	public static String getPackageName(Class cls) {
		String result = "";
		for (Package pck = cls.getPackage(); pck != null; pck = pck.getNestingPackage()) {
			result = pck.getName() + (result.length() > 0 ? "." + result : "");
		}
		return result;
	}
}
