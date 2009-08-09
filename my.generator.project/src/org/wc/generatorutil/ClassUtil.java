package org.wc.generatorutil;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;
import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EList;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.StateMachine;
import org.eclipse.uml2.uml.Transition;
import org.openarchitectureware.uml2.UML2MetaModel;
import org.openarchitectureware.uml2.profile.ProfileMetaModel;
import org.openarchitectureware.xtend.XtendFacade;
 


public class ClassUtil {
	Operation operation;
	
	static XtendFacade xtendFacade;
	static Properties properties = new Properties();
	
	static{
		
		xtendFacade = XtendFacade.create("template::GeneratorExtensions");
		UML2MetaModel mm = new UML2MetaModel();
		ProfileMetaModel pmm = new ProfileMetaModel();
		loadProperties();
		
		pmm.setProfile(properties.getProperty("model.dir") + "/wcprofile.profile.uml");
		xtendFacade.registerMetaModel(mm);
		xtendFacade.registerMetaModel(pmm);
	}
	
	
	
	//state variables
	private static Class currentEntity;
	
	private static Class currentEmbeddable;
	
	private static String currentCartridge = null;
	
	private static Boolean currentMultiMode = false;
	
	public static String getCurrentCartridge() {
		if(currentCartridge == null){
			currentCartridge = readProperty("cartridge");
		}
		return currentCartridge;
	}

	public static void setCurrentCartridge(String currentCartridge) {
		ClassUtil.currentCartridge = currentCartridge;
	}

	
	
	private static final Logger logger = Logger.getLogger(ClassUtil.class);
	

	
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
		
		StateMachine s;
		Transition t;
		//t.getGuard().getSpecification().stringValue();
		//s.get
		//s.getSubmachineStates()
		
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
	
	/** Returns a comma delimited string with , after every list member except for the last
	 * @return
	 */
	public static String getCommaDelimitedString(List<String> listStrings, int offset){
		StringBuffer buffer = new StringBuffer();
		
		for (int i = 0; i < listStrings.size(); i++) {
			String param = listStrings.get(i);
			
			if(StringUtils.isEmpty(param) )
				continue;
			
			buffer.append(param);

			if (i < (listStrings.size() - offset)) // add comma to all but last
				buffer.append(", ");
		}
		return buffer.toString();
	}
	
	/** For an operation will return comma delimited parameters with their type names - 
	 * e.g. String firstName, String lastname
	 * @param op
	 * @return
	 */
	public static String  getParametersSignature(Operation op){
		
		
		
		List<Parameter> params = op.getOwnedParameters();
		StringBuffer buffer = new StringBuffer();
		List<String> lstStrings = new ArrayList<String>();
			
		for (int i = 0; i < params.size(); i++) {
			Parameter param = params.get(i);
			String result  = (String)xtendFacade.call("fqn",new Object[]{param.getType()});
			if(!StringUtils.isEmpty(param.getName()) )
				lstStrings.add(result + " " + param.getName());
		}
		
		return  getCommaDelimitedString(lstStrings, 2);
	}
	
	public static String  getParameters(Operation op){
		List<Parameter> params = op.getOwnedParameters();
		StringBuffer buffer = new StringBuffer();
		List<String> lstStrings = new ArrayList<String>();
			
		for (int i = 0; i < params.size(); i++) {
			Parameter param = params.get(i);
			if(!StringUtils.isEmpty(param.getName()) )
				lstStrings.add( param.getName());
		}
		
		return  getCommaDelimitedString(lstStrings, 2);
	}
	
	public static String getOpReturnType(Operation op){
		
		if(op.getType() == null)
			return "void"; 
		
		System.out.println("opret" + op.getType().getName());
		System.out.println(op.getType().getPackage().getName());
		
		if(op.getType().getName() == null)
			return "void"; 
		
		String name = op.getType().getName();
		if(op.getType().getPackage().getName().startsWith("collections")){
			if(op.getTemplateParameter() != null)
				return name + "<" + op.getTemplateParameter().getSignature() + ">";
		}
		return "name";
	}
	
	public static String getInterfaces(Class clazz){
		StringBuilder buffer = new StringBuilder();
		List<Interface> interfaces = clazz.getImplementedInterfaces();
	
		if(!interfaces.isEmpty())
			buffer.append(" implements ");
		
		List<String> lstStrings = new ArrayList<String>();
		
		for (int i = 0; i < interfaces.size(); i++) {
			Interface param = interfaces.get(i);
			lstStrings.add( param.getName());
		}
		
		return  buffer.append( getCommaDelimitedString(lstStrings, 2) ).toString();
		
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
	
	public static String getDisplayNameFromAttribs(Class cls){
		EList<Property> attribs = cls.getAllAttributes();
		
		for (Property property : attribs) {
			if( property.getName().contains("name") && property.getAssociation() == null 
					&& isStringType(property.getType().getName()))
				return property.getName();
		}
		
		
		for (Property property : attribs) {
			System.out.println( property.getType().getName() + " : prp " + property.getName() );
			if(property.getAssociation() == null && isStringType(property.getType().getName()) )
				return property.getName();
		}
		
		//couldnt find any suitable display name
		return attribs.get(0).getName() + "+ \"\"";
	}

	private static boolean loadProperties() {
		properties = new Properties();
		try {
			//InputStream stream = ClassUtil.class.getResourceAsStream("/workflow.properties");
			properties.load(new FileInputStream("src/workflow/workflow.properties"));
			if (properties == null) {
				logger.error("workflow properties file is not in the classpath");
				return false;
			}
			//properties.load(stream);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static String getSingular(String word){
		return word.endsWith("s")?word.substring(0, word.length() -1):word;
	}
	
	public static boolean isStringType(String name){
		return (name.equalsIgnoreCase("String") || name.equalsIgnoreCase("nameType") ||
				name.equalsIgnoreCase("uniqueNameType"));
	}
	
	
	
	
}
