package oaw4.demo.classic.uml.extend;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import oaw4.demo.classic.uml.meta.ConstrainedParameter;
import oaw4.demo.classic.uml.meta.CustomAssociation;
import oaw4.demo.classic.uml.meta.Entity;
import oaw4.demo.classic.uml.meta.Service;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;
import org.apache.log4j.Logger;
import org.openarchitectureware.meta.uml.Type;
import org.openarchitectureware.meta.uml.classifier.AssociationEnd;
import org.openarchitectureware.meta.uml.classifier.Attribute;
import org.openarchitectureware.meta.uml.classifier.Class;
import org.openarchitectureware.meta.uml.classifier.Enumeration;
import org.openarchitectureware.meta.uml.classifier.Interface;
import org.openarchitectureware.meta.uml.classifier.Operation;
import org.openarchitectureware.meta.uml.classifier.Package;
import org.openarchitectureware.meta.uml.classifier.Parameter;
import org.witchcraft.model.helper.ClassHelper;

public class ClassUtil {

	private static final String BUSINESS_ENTITY = "org.witchcraft.model.support.BusinessEntity";

	// Entity mappings for hibernate cfg
	private static StringBuffer entityMappings = new StringBuffer();

	private static List<Entity> entities = new ArrayList<Entity>();

	private static List<Service> services = new ArrayList<Service>();

	private static final Logger logger = Logger.getLogger(ClassUtil.class);

	public static List<Entity> getEntities() {
		return entities;
	}

	/**
	 * Returns the package name of the given
	 * 
	 * @param cls
	 * @return
	 */
	public static String getPackageName(Class cls) {

		Package beginPack = cls.Package();
		
		return getPackageName(beginPack);
	}

	private static String getPackageName(Package beginPack) {
		String result = "";
			
		
		for (Package pck = beginPack; pck != null; pck = pck.SuperPackage()) {
			result = pck.NameS() + (result.length() > 0 ? "." + result : "");
			// System.out.print(pck.)
		}

		return result;
	}

	public static String getParentPackageName(Class cls) {
		if (cls.Package().SuperPackage() != null) {
			return getPackageName(cls.Package().SuperPackage());
		} else
			return "";
	}

	public static String getPackageName(Enumeration enm) {
		return getPackageName((Package) enm.Namespace());
	}

	public static String operationHelper(Operation operation) {
		String opText = getOperationDeclaration(operation);

		opText += (operation.Class() instanceof Interface) ? ";"
				: getOperationBody(operation);

		return opText;
	}

	/**
	 * Create method declaration
	 * 
	 * @param operation
	 * @return
	 */
	public static String getOperationDeclaration(Operation operation) {
		String opText = new String();
		// if (operation.Documentation() != null)
		// opText += operation.Documentation() + "\n";
		opText += operation.Visibility() + " "
				+ getParamTypeString(operation, operation.ReturnType()) + " "
				+ operation.NameS() + "(";

		opText += getOperationParams(operation);

		opText += ")";
		return opText;
	}

	/** creates an invocation string for the given operation - uses paramNames e.g.
	 * for an operation sum(int a, int b ) it will return sum(a, b) - note semicolon is not appended
	 * @param operation
	 * @return
	 */
	public static String createOperationInvocation(Operation operation) {
		String text = operation.NameS() + "(";

		text += createOperationParams(operation);
		
		return text + ")";
	}

	/** Returns the names of arguements of an operation e.g. for sum(int a, intb) it
	 * will return a,b 
	 * @param operation
	 * @return
	 */
	public static String createOperationParams(Operation operation ) {
		String text = new String();
		for (Iterator<Parameter> iter = operation.Parameter().iterator(); iter
				.hasNext();) {
			Parameter param = iter.next();
			text += param.NameS();
			if (iter.hasNext())
				text += ",";
		}
		return text;
	}

	public static String getOperationParams(Operation operation) {

		String opText = new String();

		for (Iterator<Parameter> iter = operation.Parameter().iterator(); iter
				.hasNext();) {
			Parameter param = iter.next();

			String paramType = getParamTypeString(operation, param.Type());

			opText += paramType + " " + param.NameS();
			if (iter.hasNext())
				opText += ",";
		}
		return opText;
	}

	/**
	 * Returns operation param type name - fully qualified if the param doesnt
	 * belong to this operation's package
	 * 
	 * @param operation
	 * @param param
	 * @return
	 */
	private static String getParamTypeString(Operation operation, Type type) {
		String paramType = type.NameS();
		String paramTypePackage = type.Namespace().NameS();

		if (paramType.equals("void"))
			return paramType;

		System.out.println("comapring " + type.Namespace().NameS() + " "
				+ type.Namespace().NameS());
		if (!type.Namespace().NameS().equals(
				operation.Class().Namespace.NameS())) {

			if (!type.Namespace().NameS().equals("datatypes")) {
				if (StringUtils.equals(paramTypePackage, "collections"))
					paramType = "java.util" + "." + paramType;
				else
					paramType = getPackageName((Class) type) + "." + paramType;
			}
		}
		return paramType;
	}

	/**
	 * Create method implementations for all implemented interfaces
	 * 
	 * @param cls
	 * @return
	 */
	public static String generateOperationsForImplementedInterfaces(Class cls) {
		List<Interface> interfaces = cls.Interface().toList();

		StringBuffer buffer = new StringBuffer();

		for (int i = 0; i < interfaces.size(); i++) {

			List<Operation> operations = interfaces.get(i).Operation().toList();

			buffer.append(GenericUtils
					.createSingleLineComment("Implementing interface "
							+ interfaces.get(i).Name()));

			for (int j = 0; j < operations.size(); j++) {
				Operation operation = operations.get(j);
				// if the operation is already implemented by the class
				if (!classContainsOperation(cls, operation)) {
					buffer.append(getOperationDeclaration(operation));
					buffer.append(getOperationBody(operation));
				}
			}

			buffer
					.append(GenericUtils
							.createSingleLineComment("*****Done Implementing interface "
									+ interfaces.get(i).Name() + " ****"));

		}

		return buffer.toString();
	}

	private static boolean classContainsOperation(Class cls, Operation operation) {
		for (int j = 0; j < cls.Operation().size(); j++) {
			Operation operationInClass = (Operation) cls.Operation().get(j);
			if (operationInClass.Name().equals(operation.Name())) // TODO add
				// signature
				// comaprison
				return true;
		}

		// For attributes we add getters and setters programatically
		// We need to comapre these methods too
		List<Attribute> attribs = ClassHelper.getAllAttributes(cls);
		for (Attribute attribute : attribs) {
			// System.out.println("Comparing " + operation.Name() + " with "
			// + attribute.NameS());
			if (operation.Name().equals(
					ClassHelper.getterFor(attribute.NameS()))
					|| operation.Name().equals(
							ClassHelper.setterFor(attribute.NameS())))
				return true;
		}

		return false;
	}

	/**
	 * @param cls
	 * @return e.g public class <clsname> extends <superclass> implements
	 *         <intf1>, <intf2>
	 */
	public static String getClassDeclaration(Class cls) {

		StringBuffer buffer = new StringBuffer();

		try {
			addDocumentation(cls, buffer);

			addEntityIfApplies(cls);

			buffer.append("public abstract class ");

			buffer.append(cls.NameS() + "Base");

			if (cls.hasSuperClass())
				buffer.append(" extends " + cls.SuperClass().NameS());
			else if (StereoTypeManager.isEntity(cls)
					&& ((Entity) cls).getBaseClass() != null) {
				buffer.append(" extends " + ((Entity) cls).getBaseClass());
			} else if (StereoTypeManager.isEntity(cls)
					|| StereoTypeManager.isMappedSuperClass(cls)) {
				buffer.append(" extends " + BUSINESS_ENTITY);
			}

			List<Interface> interfaces = cls.Interface().toList();

			// if (!interfaces.isEmpty())
			buffer.append(" implements java.io.Serializable");

			if (!interfaces.isEmpty())
				buffer.append(", ");

			for (int i = 0; i < interfaces.size(); i++) {
				buffer.append(interfaces.get(i).NameS());

				if (i < (interfaces.size() - 1)) // add comma to all but last
					buffer.append(", ");
			}

		} catch (Exception e) {
			System.out.println("Exception getting class name " + cls.NameS()
					+ ":" + buffer);
			e.printStackTrace();
		}

		return buffer.toString();
	}

	private static void addDocumentation(Class cls, StringBuffer buffer) {
		if (cls.Documentation() != null)
			buffer.append(GenericUtils.createComment(cls.Documentation())
					+ "\n");
	}

	/**
	 * This method returns if the given class is an entity - for now it just
	 * checks for a hardcoded package name called "bizobjects" - in production
	 * it should look for a stereotype like entity.
	 * 
	 * @param cls
	 */
	private static String addEntityIfApplies(Class cls) {

		if (cls.getMetaClass().getSimpleName().equals("Class"))
			return "";

		if (cls.getMetaClass().getSimpleName().equals("Entity")) {

			// This is a hack - we need to find a more elegant
			// way to get all entities form model
			entities.add((Entity) cls);

			entityMappings.append("<mapping class=\"" + fullyQualifiedName(cls)
					+ "\"/>\n");
			return "" /* "@Entity\n" */;
		} else
			return "@" + cls.getMetaClass().getSimpleName() + "\n";
	}

	public static boolean isEntity(Class cls) {
		boolean res = cls.getMetaClass().getSimpleName().equals("Entity");
		return cls.getMetaClass().getSimpleName().equals("Entity");
	}

	/**
	 * Creates method body
	 * 
	 * @param operation
	 * @return
	 */
	public static String getOperationBody(Operation operation) {
		if (operation.hasReturnType()
				&& !operation.ReturnType().NameS().equals("void")) {

			StringBuffer constraints = new StringBuffer();

			for (Object object : operation.Parameter()) {
				Parameter parameter = (Parameter) object;
				System.out.println(parameter.Name() + " -->>"
						+ parameter.getMetaClass().getName());

				if (StereoTypeManager.isConstrainedParameter(parameter)) {
					constraints.append(((ConstrainedParameter) parameter)
							.getConstraints());
				}
			}

			return "{\n" + constraints.toString() +

			" return null; " + "}\n";
		} else
			return "{ }";
	}

	/**
	 * Get property declaration - e.g private String firstName = "" ;
	 * 
	 * @param attribute
	 * @return
	 */
	public static String getPropertyDeclaration(Attribute attribute) {

		String declaration = new String();

		declaration += "protected " + attribute.Type().NameS() + " "
				+ attribute.NameS();

		if (attribute.InitValue() != null)
			declaration += " = " + attribute.InitValue();
		else if (attribute.Type().NameS().equals("Boolean")) {
			declaration += " = false"; // Need to init Boolean values
		}

		declaration += ";";

		return declaration;
	}

	public static String fullyQualifiedName(Class cls) {
		return getPackageName(cls) + "." + cls.NameS();
	}

	/**
	 * Return if many-to-one applies
	 * 
	 * @param ae
	 * @return
	 */
	public static String manyToOne(AssociationEnd ae) {
		if (StereoTypeManager.isEntity(ae.Class())) {
			String nullable = new Boolean(isAssocNullable(ae)).toString();

			AssociationEnd opposite = ae.Opposite();
			String multiplicity = (opposite.MultiplicityMinAsInt() == 1 && opposite
					.MultiplicityMaxAsInt() == 1) ? "OneToOne(cascade=CascadeType.ALL)"
					: "ManyToOne";

			boolean updatable = true;

			if (ae.Association().getMetaClass().getSimpleName().equals(
					"CustomAssociation")) {
				updatable = ((CustomAssociation) ae.Association()).isMutable();
			}

			return "@" + multiplicity + "\n @JoinColumn(name=\""
					+ getAssocName(ae) + "_ID\", nullable=" + nullable
					+ ", updatable=" + updatable + ")";

		} else
			return "";
	}

	public static String getAssocName(AssociationEnd ae) {
		return ae.NameS() == null ? WordUtils.uncapitalize(ae.Class().NameS())
				: ae.NameS();
	}

	/**
	 * Whether an association can be null - if the multiplicity is greater than
	 * or equal to 1 it is not nullable.
	 * 
	 * @param ae
	 * @return
	 */
	public static boolean isAssocNullable(AssociationEnd ae) {
		logger.info(ae.Opposite().Class().NameS() + "-->" + ae.Class().NameS()
				+ " has min multiplicty " + ae.MultiplicityMin());
		return ae.MultiplicityMinAsInt() >= 1 ? false : true;
	}

	/**
	 * For compositions with 1 multiplicity and one to one relationships we
	 * instantiate the composed entity e.g if Person contains address then we
	 * declare Address address = <b> new Address() </b>
	 * 
	 * @param ae
	 * @return
	 */
	public static String getInstantiationIfComposition(AssociationEnd ae) {
		// for self referencing associations we dont need instantion
		if (ae.Opposite().Class().equals(ae.Class()))
			return "";

		if (ae.Opposite().isComposition())
			return " = new " + fullyQualifiedName(ae.Class()) + "()";
		else if (isAssociationOneOnOne(ae)
				|| StereoTypeManager.isEmbeddable(ae.Class()))
			return " = new " + fullyQualifiedName(ae.Class()) + "()";
		return "";
	}

	public static boolean isAssociationOneOnOne(AssociationEnd ae) {
		return ae.MultiplicityMinAsInt() == 1 && ae.MultiplicityMaxAsInt() == 1
				&& ae.Opposite().MultiplicityMinAsInt() == 1
				&& ae.Opposite().MultiplicityMaxAsInt() == 1;
	}

	public static String getViewLabel(Attribute attribute) {
		return GenericUtils.getViewLabelFromVariable(attribute.NameS()); // +
	}

	public static String getViewLabel(String name) {
		return GenericUtils.getViewLabelFromVariable(name); // +
	}

	/**
	 * Makes the first letter small case
	 * 
	 * @param varName
	 * @return
	 */
	public static String asVariable(String varName) {
		return StringUtils.uncapitalize(varName);
	}

	public static String generateEnumLiterals(Enumeration enm) {
		StringBuffer buffer = new StringBuffer();

		for (int i = 0; i < enm.Literal().size(); i++) {
			buffer.append(enm.Literal(i).NameS());
			if (i < (enm.Literal().size() - 1))
				buffer.append(", ");
		}

		return buffer.toString();
	}

	private static String[] arrString = { "One", "Two", "Three", "Four", "Five" };

	private static int count = 0;

	public static List getCounters(Class cls) {
		return Arrays.asList(arrString);
	}

	public static String replaceSlashesWithDots(String source) {
		return source.replace('/', '.');
	}

	public static int getCounter() {
		return count++;
	}

	public static String resetCounter() {
		count = 0;
		return "";
	}

	public static List<Service> getServices() {
		return services;
	}

	public static void addService(Service service) {
		services.add(service);
	}

}