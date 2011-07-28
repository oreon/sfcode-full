package org.wc.generatorutil;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;
import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EList;
import org.eclipse.uml2.uml.ActivityEdge;
import org.eclipse.uml2.uml.ActivityFinalNode;
import org.eclipse.uml2.uml.ActivityNode;
import org.eclipse.uml2.uml.AggregationKind;
import org.eclipse.uml2.uml.CallBehaviorAction;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.ControlFlow;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.ForkNode;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Transition;
import org.eclipse.uml2.uml.Type;
import org.openarchitectureware.uml2.UML2MetaModel;
import org.openarchitectureware.uml2.profile.ProfileMetaModel;
import org.openarchitectureware.xtend.XtendFacade;

public class ClassUtil {
	Operation operation;

	static XtendFacade xtendFacade;
	static Properties properties = new Properties();

	static int count = 0;
	
	private static final Logger logger = Logger.getLogger(ClassUtil.class);

	static Map<String, String[]> mapTypes = new HashMap<String, String[]>();

//	Logger logger = Logger.getLogger(ClassUtil.class);
	
	private static Model model;

	static {
		Operation op;

		CallBehaviorAction cba;

		Property prop;

		xtendFacade = XtendFacade.create("template::GeneratorExtensions");
		UML2MetaModel mm = new UML2MetaModel();
		ProfileMetaModel pmm = new ProfileMetaModel();
		loadProperties();

		pmm.setProfile(properties.getProperty("model.dir")
				+ "/wcprofile.profile.uml");
		xtendFacade.registerMetaModel(mm);
		xtendFacade.registerMetaModel(pmm);
	}

	// state variables
	private static Class currentEntity;

	private static Property currentEmbeddable;

	private static String currentEmbeddableName;

	private static String currentCartridge = null;

	private static Boolean currentMultiMode = false;

	private static Boolean currentTemplateMode = false;

	public static String getCurrentCartridge() {
		if (currentCartridge == null) {
			currentCartridge = readProperty("cartridge");
		}
		return currentCartridge;
	}

	public static void setCurrentCartridge(String currentCartridge) {
		ClassUtil.currentCartridge = currentCartridge;
	}

	

	static {
		mapTypes.put("datatypes.Integer", new String[] { "Integer", "" });
		mapTypes.put("Currency", new String[] { "Double", "" });
		mapTypes.put("imageFile", new String[] { "FileAttachment", "" });
		mapTypes.put("largeText", new String[] { "String", "@Lob" });
		mapTypes.put("nameType", new String[] { "String",
				"@NotNull @Length(min=2, max=250)" });
		mapTypes.put("uniqueNameType", new String[] { "String",
				"@NotNull @Length(min=2, max=250)  @Column(unique=true)" });
		loadProperties();
	}

	private static Boolean currentEditMode = false;

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

	public static Property getCurrentEmbeddable() {
		// if(currentEmbeddable != null)
		// System.out.println("getting embeddable -> " +
		// currentEmbeddable.getName());
		return currentEmbeddable;
	}

	public static String getCurrentEmbeddableName() {
		return currentEmbeddableName;
	}
	
	public static void  setCurrentEmbeddableName(String ce) {
		 currentEmbeddableName = ce;
	}

	public static void setCurrentEmbeddable(Property currentEmbeddable) {
		// System.out.println("setting embeddable to " +
		// currentEmbeddable.getName());
		ClassUtil.currentEmbeddable = currentEmbeddable;
	}

	public static void clearCurrentEmbeddable() {
		ClassUtil.currentEmbeddable = null;
	}

	public static Class getCurrentEntity() {
		return currentEntity;
	}

	public static void setCurrentEntity(Class currentEntity) {
		ClassUtil.currentEntity = currentEntity;
	}

	public static void main(String[] args) {
		String ret = elToJava("address.phone");
		System.out.println(ret);
	}

	public static int getRandomNumber() {
		return new Random().nextInt(15000);

	}

	private static final int VERSION = 1;

	public static String serialver(Class cls) {
		//cls.
		return new Integer((cls.getName() + "-" + cls.getPackage().getName())
				.hashCode()
				^ VERSION).toString()
				+ "L";
	}

	public static List<Property> getAllAttribs(Class cls) {
		List<Property> lstAttributes = new ArrayList<Property>();
		
		EList<Class> classes = cls.getSuperClasses();
		try {
			for (Class classifier : classes) {
				//System.out.println("found " + classifier.getAllAttributes().size());
				lstAttributes.addAll(classifier.getAllAttributes());
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		lstAttributes.addAll(cls.getAllAttributes());
		return lstAttributes;
	}

	/**
	 * Returns a comma delimited string with , after every list member except
	 * for the last
	 * 
	 * @return
	 */
	public static String getCommaDelimitedString(List<String> listStrings,
			int offset) {
		StringBuffer buffer = new StringBuffer();

		for (int i = 0; i < listStrings.size(); i++) {
			String param = listStrings.get(i);

			if (StringUtils.isEmpty(param))
				continue;

			buffer.append(param);

			if (i < (listStrings.size() - offset)) // add comma to all but last
				buffer.append(", ");
		}
		return buffer.toString();
	}

	public static List<String> getListFromCommaDeleimtedString(String arg) {
		String[] arr = arg.split(",");
		int i = 0;
		for (String string : arr) {
			string = string.trim();
			arr[i++] = string;
		}
		return Arrays.asList(arr);
	}
	
	
	public static String getParametersSignatureRest(Operation op){
		op.getOwnedParameters();

		List<Parameter> params = op.getOwnedParameters();
		StringBuffer buffer = new StringBuffer();
		List<String> lstStrings = new ArrayList<String>();

		Transition tr;
		
		Element el;
		//op.getEAnnotation(source);

		for (int i = 0; i < params.size(); i++) {
			Parameter param = params.get(i);
			String result = (String) xtendFacade.call("fqn",
					new Object[] { param.getType() });
			if (!StringUtils.isEmpty(param.getName()))
				lstStrings.add("@QueryParam(" + "\"" + param.getName() + "\")" + " " + result + " " + param.getName());
		}

		return getCommaDelimitedString(lstStrings, 1);
	}
	
	public static String getParametersSignature(Operation op){
		return getParametersSignature(op, "");
	}

	/**
	 * For an operation will return comma delimited parameters with their type
	 * names - e.g. String firstName, String lastname
	 * 
	 * @param op
	 * @return
	 */
	public static String getParametersSignature(Operation op, String ext) {
		
		op.getOwnedParameters();

		List<Parameter> params = op.getOwnedParameters();
		StringBuffer buffer = new StringBuffer();
		List<String> lstStrings = new ArrayList<String>();

		Transition tr;

		for (int i = 0; i < params.size(); i++) {
			Parameter param = params.get(i);
			String result = (String) xtendFacade.call("fqn",
					new Object[] { param.getType() });
			if (!StringUtils.isEmpty(param.getName()))
				lstStrings.add(ext + " " + result + " " + param.getName());
		}

		return getCommaDelimitedString(lstStrings, 1);
	}

	public static String getParameters(Operation op) {
		List<Parameter> params = op.getOwnedParameters();
		StringBuffer buffer = new StringBuffer();
		List<String> lstStrings = new ArrayList<String>();

		for (int i = 0; i < params.size(); i++) {
			Parameter param = params.get(i);
			if (!StringUtils.isEmpty(param.getName()))
				lstStrings.add(param.getName());
		}

		return getCommaDelimitedString(lstStrings, 1);
	}

	public static String getOpReturnType(Operation op) {

		if (op.getType() == null)
			return "void";

		System.out.println("opret" + op.getType().getName());
		System.out.println(op.getType().getPackage().getName());

		if (op.getType().getName() == null)
			return "void";

		String name = op.getType().getName();
		if (op.getType().getPackage().getName().startsWith("collections")) {
			if (op.getTemplateParameter() != null)
				return name + "<" + op.getTemplateParameter().getSignature()
						+ ">";
		}
		return "name";
	}

	public static String getInterfaces(Class clazz) {
		StringBuilder buffer = new StringBuilder();
		List<Interface> interfaces = clazz.getImplementedInterfaces();

		if (!interfaces.isEmpty())
			buffer.append(" implements ");

		List<String> lstStrings = new ArrayList<String>();

		for (int i = 0; i < interfaces.size(); i++) {
			Interface param = interfaces.get(i);
			String result = (String) xtendFacade.call("fqn",
					new Object[] { param });
			lstStrings.add(result);
		}

		return buffer.append(getCommaDelimitedString(lstStrings, 2)).toString();

	}

	public static String getValidatorAnnotations(Property prop) {
		return org.apache.commons.lang.StringUtils.EMPTY;
	}

	public static String getTypeName(String typeName) {
		if (mapTypes.containsKey(typeName)) {
		
			return mapTypes.get(typeName)[0];
		}
		return typeName;
	}

	/**
	 * Annotation for the mapped type e.g for largetText we need to return
	 * "@Lob"
	 * 
	 * @param typeName
	 * @return
	 */
	public static String getTypeAnnotation(String typeName) {
		if (mapTypes.containsKey(typeName)) {
			System.out.println(typeName + " mapped to annotation "
					+ mapTypes.get(typeName)[1]);
			return mapTypes.get(typeName)[1];
		}
		return "";
	}

	public static String getPackageName(Class cls) {
		String result = "";
		for (Package pck = cls.getPackage(); pck != null; pck = pck
				.getNestingPackage()) {
			result = pck.getName() + (result.length() > 0 ? "." + result : "");
		}
		return result;
	}

	public static String removeSpaces(String target) {
		target = target.replace(" ", "");
		return target;
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
		if (properties == null) {
			if (!loadProperties())
				return "COULDNT_READ_FILE";
		}

		String value = properties.getProperty(key);
		// logger.info("Returning value " + value + " for key " + key);
		return value;
	}

	public static String readProperty(String key, String def) {
		String ret = readProperty(key);
		if (ret == null || ret.equals(""))
			ret = def;
		return ret;

	}

	public static String getDisplayNameFromAttribs(Class cls) {
		EList<Property> attribs = cls.getAllAttributes();

		// System.out.println("before first loop");
		for (Property property : attribs) {
			if (property.getName().contains("name")
					&& property.getAssociation() == null
					&& isStringType(property.getType().getName()))
				return property.getName();
		}

		// System.out.println("after first loop");
		for (Property property : attribs) {
			if (property.getType() != null) {
				System.out.println(property.getType().getName() + " : prp "
						+ property.getName());
				if (property.getAssociation() == null
						&& isStringType(property.getType().getName()))
					return property.getName();
			}
		}
		// System.out.println("after second loop");
		// couldnt find any suitable display name
		return attribs.get(0).getName() + "+ \"\"";
	}

	public static List<Property> attribsOfThisClass(Class cls) {
		List<Property> target = new ArrayList<Property>();
		return getAttribs(cls, target, null);
		// return target;
	}

	public static List<Property> getAttribs(Class cls, List<Property> props,
			String name) {
		List<Property> properties = new ArrayList();
		properties.addAll(cls.getAllAttributes());

		// cls.getat

		EList<Class> classes = cls.getSuperClasses();
		if (classes != null) {
			for (Class class1 : classes) {
				try {
					properties.addAll(class1.getAllAttributes());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		for (Property property : properties) {
			if (property.getAssociation() != null
					&& (property.getType().getAppliedStereotype(
							"wcprofile::Embeddable") != null || property
							.getAssociation().getAppliedStereotype(
									"wcprofile::ContainedAssociation") != null)) {

				getAttribs((Class) property.getType(), props, property
						.getName());
			} else {
				// System.out.println("deployment is " + name);
				property.createDeployment(name);
				props.add(property);
			}
		}

		return props;
	}

	public static String getDeployName(Property prop) {
		// System.out.println( prop.getClass_().getName() + " " +

		if (prop.getDeployments().size() > 0
				&& !prop.getClass_().getName().equals(
						getCurrentEntity().getName())) {
			return prop.getDeployments().get(0).getName();
		}
		return "";
	}

	private static boolean loadProperties() {
		properties = new Properties();
		try {
			// InputStream stream =
			// ClassUtil.class.getResourceAsStream("/workflow.properties");
			properties.load(new FileInputStream(
					"properties/workflow.properties"));
			if (properties == null) {
				logger
						.error("workflow properties file is not in the classpath");
				return false;
			}
			// properties.load(stream);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static EList<ActivityEdge> outgoing(ActivityNode act) {
		return act.getOutgoings();
	}

	public static String getSwimlane(ActivityNode act) {
		if (act.getInPartitions().size() > 0)
			return act.getInPartitions().get(0).getName();
		return null;
	}

	private static int finalNodeCounter;

	public static String getTaskMassagedName(NamedElement act) {

		// Date today = new Date();

		if (act.getName() == null || act.getName() == "") {

			if (act instanceof ControlFlow) {
				ControlFlow cf = (ControlFlow) act;
				act.setName(cf.getGuard().stringValue());
			}

			if (act instanceof ActivityFinalNode) {
				ActivityFinalNode cf = (ActivityFinalNode) act;
				act.setName("endState" + ++finalNodeCounter);
			}

			if (act instanceof ForkNode) {
				ForkNode forkNode = (ForkNode) act;
				forkNode.setName("from"
						+ forkNode.getIncomings().get(0).getName() + "Fork");
			}

		}

		act.setName(massagedName(act.getName()));
		// System.out.println(act.getName());
		return act.getName();
	}

	public static String massagedName(String orgName) {
		// String orgName = act.getName();
		// System.out.println(act.getName());vf
		String dest = orgName.trim();

		dest = orgName.replace("/", "Or");
		dest = dest.replace("\\", "Or");
		dest = dest.replace("=", "Is");
		dest = dest.replace(" ", "");
		dest = dest.replace("+", "And");
		// act.setName(dest);

		System.out.println(dest);
		dest = StringUtils.uncapitalize(dest);
		System.out.println(dest);
		return dest;
	}

	public static String getSingular(String word) {
		return word.endsWith("s") ? word.substring(0, word.length() - 1) : word;
	}

	public static boolean isStringType(String name) {
		return (name.equalsIgnoreCase("String")
				|| name.equalsIgnoreCase("nameType") || name
				.equalsIgnoreCase("uniqueNameType"));
	}

	public static boolean isAggregate(Property prop) {
		Class c;
		return prop.getAggregation().getValue() == AggregationKind.SHARED;
	}

	public static int getCounter() {
		return count++;
	}

	public static String resetCounter() {
		count = 0;
		return "";
	}
	
	public static void print(String message){
		System.out.println(message);
		logger.info(message);
	}

	public static String getTreeParent(String arg) {
		System.out.println("arg is " + arg);
		String[] tokens = arg.split(",");
		if (tokens.length < 2) {
			System.out.println("Invalid tree field in the model");
		}
		return tokens[0].trim();
	}

	public static String getTreeChildren(String arg) {
		String[] tokens = arg.split(",");
		if (tokens.length < 2) {
			System.out.println("Invalid tree field in the model");
		}
		return tokens[1].trim();
	}

	public static String getTreeDetails(String arg) {
		String[] tokens = arg.split(",");
		if (tokens.length < 3) {
			System.out
					.println("Invalid tree field in the model / or no details provided");
		}
		return tokens[2].trim();
	}

	public static String appendBraces(String str){
		str = str.trim();
		if (!str.startsWith("(") ) 
			str = "(" + str;
		if (!str.endsWith(")") )
			str = str + ")";
		return str;
	}
	
	public static Property getAttrib(Class cls, String name) {
		return cls.getAttribute(name, null);
	}

	private static String[] arrString = { "One", "Two", "Three", "Four", "Five" };

	public static List getCounters() {
		return Arrays.asList(arrString);
	}

	// for report group generation we need to seperate calc from field
	public static String getCalc(String t) {
		return t.split(":")[0].trim();
	}

	public static String getComponentName(String arg) {
		String[] arr = arg.split("/.");
		return "";
	}

	// for report group generation we need to seperate calc from field
	public static String getField(String t) {
		String[] arr = t.split(":");
		return (arr.length > 1) ? arr[1].trim() : "";
	}

	public static Map<String, String> mapFieldsForReports = new HashMap<String, String>();

	public static String getFieldTypeForReports(String key) {
		mapFieldsForReports.put("String", "java.lang.String");
		mapFieldsForReports.put("nameType", "java.lang.String");
		mapFieldsForReports.put("uniqueNameType", "java.lang.String");
		mapFieldsForReports.put("largeText", "java.lang.String");
		mapFieldsForReports.put("long", "java.lang.Long");
		mapFieldsForReports.put("Long", "java.lang.Long");
		mapFieldsForReports.put("Double", "java.lang.Double");
		mapFieldsForReports.put("Date", "java.util.Date");
		mapFieldsForReports.put("Boolean", "java.lang.Boolean");
		mapFieldsForReports.put("Integer", "java.lang.Integer");

		if (mapFieldsForReports.containsKey(key))
			return mapFieldsForReports.get(key);

		System.out.println("Couldnt find report type for " + key);
		return key;

		// return "java.lang.String";
	}

	static String[] primitives = { "date", "long", "integer", "boolean",
			"double", "float", "string" };

	public static boolean isPrimitive(Type type) {
		String name = type.getName().toLowerCase();
		return Arrays.asList(primitives).contains(name);
	}

	/**
	 * for an expression like customer.address.phone we need to return
	 * getCustomer().getAddress().setPhone
	 * 
	 * @param arg
	 * @return
	 */
	public static String elToJava(String arg) {
		String[] arr = arg.split("\\.");
		int i = 0;
		for (String string : arr) {
			arr[i++] = StringUtils.capitalize(string);
		}

		for (i = 0; i < arr.length - 1; i++) {
			arr[i] = "get" + arr[i] + "().";
		}
		arr[arr.length - 1] = "set" + arr[arr.length - 1];

		StringBuffer sb = new StringBuffer();
		for (String string : arr) {
			sb.append(string);
		}

		return sb.toString();
	}

	public static Class getFirstChild(Class c) {

		Model m = c.getModel();
		EList<Element> children = m.allOwnedElements();
		for (Element element : children) {
			if (element instanceof Class
					&& ((Class) element).allParents().contains(c))
				return (Class) element;
		}

		return null;
	}

	public static void setCurrentTemplateMode(Boolean currentTemplateMode) {
		ClassUtil.currentTemplateMode = currentTemplateMode;
	}

	public static Boolean isCurrentTemplateMode() {
		return currentTemplateMode;
	}

	public static void setModel(Model model) {
		ClassUtil.model = model;
	}

	public static Model getModel() {
		return model;
	}

}
