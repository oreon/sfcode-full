package org.wc.generatorutil;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;


import org.apache.commons.lang.WordUtils;
import org.apache.log4j.Logger;
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
	
	private static Boolean currentMultiMode = false;
	
	private static final Logger logger = Logger.getLogger(ClassUtil.class);
	
	private static Properties properties ;

	
	static Map<String, String[]> mapTypes = new HashMap<String, String[]>();
	
	static {
		mapTypes.put("imageFile", new String[]{"byte[]",""});
		mapTypes.put("largeText", new String[]{"String","@Lob"});
		mapTypes.put("nameType", new String[]{"String","@NotNull @Length(min=2, max=50)"});
		mapTypes.put("uniqueNameType", new String[]{"String","@NotNull @Length(min=2, max=50)"});
		loadProperties();
	}

	private static Boolean currentEditMode = false;
	
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
		Property property;
		Package pack;
		
		//pack.getown
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
		//prop.getAssociation().getEndTy
		
		
		if(!interfaces.isEmpty())
			buffer.append(" implements ");

		for (int i = 0; i < interfaces.size(); i++) {
			buffer.append(interfaces.get(i).getName());

			if (i < (interfaces.size() - 1)) // add comma to all but last
				buffer.append(", ");
		}
		
		return buffer.toString();
	}
	
	public static String getValidatorAnnotations(Property prop){
		return org.apache.commons.lang.StringUtils.EMPTY;
	}
	
	public static String getTypeName(String typeName){
		if(mapTypes.containsKey(typeName)){
			System.out.println(typeName + " mapped to " + mapTypes.get(typeName)[0]);
			return mapTypes.get(typeName)[0];
		}
		return typeName;
	}
	
	/** Annotation for the mapped type e.g for largetText we need to return "@Lob" 
	 * @param typeName
	 * @return
	 */
	public static String getTypeAnnotation(String typeName){
		if(mapTypes.containsKey(typeName)){
			System.out.println(typeName + " mapped to annotation " + mapTypes.get(typeName)[1]);
			return mapTypes.get(typeName)[1];
		}
		return "";
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
	
	
	public static String readProperty(String key) {
		if(properties == null ){
			if(!loadProperties())
				return "COULDNT_READ_FILE";
		}
			
		String value = properties.getProperty(key);
		logger.info("Returning value " + value + " for key " + key);
		return value;
	}

	private static boolean loadProperties() {
		properties = new Properties();
		try {
			InputStream stream = ClassUtil.class
					.getResourceAsStream("/workflow.properties");
			if (stream == null) {
				logger.error("workflow properties file is not in the classpath");
				return false;
			}
			properties.load(stream);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static String getSingular(String word){
		return word.endsWith("s")?word.substring(0, word.length() -1):word;
	}
	
	
	
}
