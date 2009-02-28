package org.wc.generatorutil;

import java.util.List;

import org.apache.commons.lang.WordUtils;
import org.eclipse.emf.common.util.EList;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Property;

public class ClassUtil {
	Operation operation;
	
	//state variables
	private static Class currentEntity;
	
	private static Class currentEmbeddable;
	
	private static Boolean currentMultiMode;
	
	

	private static Boolean currentEditMode;
	
	//static org.eclipse.uml2.uml.Class cls;

	public static Boolean isCurrentMultiMode() {
		return currentMultiMode;
	}

	public static void setCurrentMultiMode(Boolean currentMultiMode) {
		ClassUtil.currentMultiMode = currentMultiMode;
	}
	
	public static Boolean isCurrentEditMode() {
		return currentEditMode;
	}


	public static void setCurrentEditMode(Boolean currentEditMode) {
		ClassUtil.currentEditMode = currentEditMode;
	}



	public static Class getCurrentEmbeddable() {
		return currentEmbeddable;
	}



	public static void setCurrentEmbeddable(Class currentEmbeddable) {
		ClassUtil.currentEmbeddable = currentEmbeddable;
	}



	public static Class getCurrentEntity() {
		return currentEntity;
	}



	public static void setCurrentEntity(Class currentEntity) {
		ClassUtil.currentEntity = currentEntity;
	}



	public static void main(String[] args) {
		// cls.getPackage().
		// Association assoc;
		// assoc.getMemberEnds();
		// cls.getA
		// cls.
	}
	
	
	
	public static EList<Property> getInts(Class cls){
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
	
	/**
	 * This function tries to split a camel case variable name into space
	 * delimited user displayable string e.g.
	 * 
	 * @return input firstName - output First Name
	 */
	public static String getViewLabelFromVariable(String varName) {
		return getViewLabelFromVariable(varName, " ");
	}

	/**
	 * This function tries to split a camel case variable name into space
	 * delimited user displayable string e.g.
	 * 
	 * @return input firstName - output First Name
	 */
	public static String getViewLabelFromVariable(String varName,
			String concatChar) {
		if (varName == null) {
			System.out.println("Warn: null variable in getViewLabel ");
			return "";
		}
		char[] characters = varName.toCharArray();
		for (char ch : characters) {
			if (Character.isUpperCase(ch))
				varName = varName.replace(new String(ch + ""), concatChar + ch);
		}
		return WordUtils.capitalizeFully(varName);
	}
}
