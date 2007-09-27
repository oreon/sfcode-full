package oaw4.demo.classic.uml.extend;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import oaw4.demo.classic.uml.meta.Column;
import oaw4.demo.classic.uml.meta.Entity;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;
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

		String result = "";
		for (Package pck = cls.Package(); pck != null; pck = pck.SuperPackage()) {
			result = pck.NameS() + (result.length() > 0 ? "." + result : "");
			// System.out.print(pck.)
		}

		return result;
	}

	public static String getPackageName(Enumeration enm) {
		return enm.Namespace().NameS();
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
	private static String getOperationDeclaration(Operation operation) {
		String opText = new String();
		if (operation.Documentation() != null)
			opText += operation.Documentation() + "\n";
		opText += operation.Visibility() + " " + operation.ReturnType().NameS()
				+ " " + operation.NameS() + "(";

		for (Iterator<Parameter> iter = operation.Parameter().iterator(); iter
				.hasNext();) {
			Parameter param = iter.next();
			opText += param.Type().NameS() + " " + param.NameS();
			if (iter.hasNext())
				opText += ",";
		}

		opText += ")";
		return opText;
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
				if (!classContainsOperation(cls, operation)) { // if the
																// operation is
																// already
																// implemented
																// by the class
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
			//System.out.println("Comparing " + operation.Name() + " with "
			//		+ attribute.NameS());
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

		addDocumentation(cls, buffer);

		buffer.append(addEntityIfApplies(cls));

		buffer.append("public class ");
		buffer
				.append(GenericUtils.createComment(cls.Stereotype().size()
						+ " "));
		buffer.append(cls.NameS());

		if (cls.hasSuperClass())
			buffer.append(" extends " + cls.SuperClass().NameS());
		else if (StereoTypeManager.isEntity(cls)
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
	private static String getOperationBody(Operation operation) {
		if (operation.hasReturnType()) {
			return "{ return null; " + "\n //should return "
					+ operation.ReturnType().NameS() + "\n}\n";
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

		// if(attribute.getMetaClass().getName().equals("oaw4.demo.classic.uml.meta.Key"))
		// declaration += "//" + attribute.getMetaClass().getName();

		declaration += "private " + attribute.Type().NameS() + " "
				+ attribute.NameS();

		if (attribute.InitValue() != null)
			declaration += " = " + attribute.InitValue();

		declaration += ";";

		return declaration;
	}

	public static String fullyQualifiedName(Class cls) {
		return cls.Package().NameS() + "." + cls.NameS();
	}

	/**
	 * REturn if manyto one applies
	 * 
	 * @param ae
	 * @return
	 */
	public static String manyToOne(AssociationEnd ae) {
		if (StereoTypeManager.isEntity(ae.Class())) {
			String nullable = ae.MultiplicityMinAsInt() >= 1 ? "false" : "true";
			AssociationEnd opposite = ae.Opposite();
			String multiplicity = (opposite.MultiplicityMinAsInt() == 1 && opposite.MultiplicityMaxAsInt() == 1 )? 
					"OneToOne": "ManyToOne";
			return "@" + multiplicity +"\n @JoinColumn(name=\"" + ae.NameS()
					+ "_ID\", nullable=" + nullable + ")";
		} else
			return "";
	}

	/**
	 * For compositions with 1 multiplicity and one to one relationships 
	 * we instantiate the composed entity
	 * e.g if Person contains address then we declare Address address = <b> new
	 * Address() </b>
	 * 
	 * @param ae
	 * @return
	 */
	public static String getInstantiationIfComposition(AssociationEnd ae) {
		if (ae.Opposite().isComposition())
			return " = new " + fullyQualifiedName(ae.Class()) + "()";
		if(isAssociationOneOnOne(ae))
			return " = new " + fullyQualifiedName(ae.Class()) + "()";
		return "";
	}

	public static boolean isAssociationOneOnOne(AssociationEnd ae) {
		return ae.MultiplicityMinAsInt() == 1 && ae.MultiplicityMaxAsInt() == 1 && 
				ae.Opposite().MultiplicityMinAsInt() == 1 && ae.Opposite().MultiplicityMaxAsInt() == 1;
	}

	public static String getViewLabel(Attribute attribute) {
		return getViewLabelFromVariable(attribute.NameS()); // +
															// getIndicatorForRequiredAttribute(attribute)
															// ;
	}

	public static String getViewLabel(String name) {
		return getViewLabelFromVariable(name); // +
												// getIndicatorForRequiredAttribute(attribute)
												// ;
	}

	/**
	 * This function tries to split a camel case variable name into space
	 * delimited user displayable string e.g.
	 * 
	 * @return input firstName - output First Name
	 */
	public static String getViewLabelFromVariable(String varName) {
		if (varName == null) {
			System.out.println("Warn: null variable in getViewLabel ");
			return "";
		}
		char[] characters = varName.toCharArray();
		for (char ch : characters) {
			if (Character.isUpperCase(ch))
				varName = varName.replace(new String(ch + ""), " " + ch);
		}
		return WordUtils.capitalizeFully(varName);
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

}