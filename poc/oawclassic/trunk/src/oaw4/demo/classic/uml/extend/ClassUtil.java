package oaw4.demo.classic.uml.extend;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;
import org.openarchitectureware.core.meta.core.Element;
import org.openarchitectureware.meta.uml.classifier.AssociationEnd;
import org.openarchitectureware.meta.uml.classifier.Attribute;
import org.openarchitectureware.meta.uml.classifier.Class;
import org.openarchitectureware.meta.uml.classifier.Enumeration;
import org.openarchitectureware.meta.uml.classifier.Interface;
import org.openarchitectureware.meta.uml.classifier.Operation;
import org.openarchitectureware.meta.uml.classifier.Package;
import org.openarchitectureware.meta.uml.classifier.Parameter;

public class ClassUtil {
	
	private static final String BUSINESS_ENTITY = "org.witchcraft.model.support.BusinessEntity";
	//Entity mappings for hibernate cfg 
	private static StringBuffer entityMappings = new StringBuffer();
	
	public static StringBuffer getEntityMappings() {
		return entityMappings;
	}


	/** Returns the package name of the given 
	 * @param cls
	 * @return
	 */
	public static String getPackageName(Enumeration enm) {

		String result = "";
		/*
		for (Package pck = (Package) enm.Namespace(); pck != null; pck = pck.SuperPackage()) {
			result = pck.NameS() + (result.length() > 0 ? "." + result : "");
		}*/

		return enm.Namespace().NameS();
	}
	

	/** Returns the package name of the given 
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
		String opText = operation.Visibility() + " "
				+ operation.ReturnType().NameS() + " " + operation.NameS()
				+ "(";

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
			
			buffer.append(GenericUtils.createSingleLineComment("Implementing interface " +  interfaces.get(i).Name()));
			
			for (int j = 0; j < operations.size(); j++) {
				buffer.append(getOperationDeclaration(operations.get(j)));
				buffer.append(getOperationBody(operations.get(j)));
			}
			
			buffer.append(GenericUtils.createSingleLineComment
					("*****Done Implementing interface " +  interfaces.get(i).Name() + " ****"));

		}

		return buffer.toString();
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
		buffer.append(GenericUtils.createComment(cls.Stereotype().size() + " "));
		buffer.append(cls.NameS());

		if (cls.hasSuperClass())
			buffer.append(" extends " + cls.SuperClass().NameS());
		else if (StereoTypeManager.isEntity(cls) || 
				StereoTypeManager.isMappedSuperClass(cls)){
			buffer.append(" extends " + BUSINESS_ENTITY );
		}
		
		List<Interface> interfaces = cls.Interface().toList();

		//if (!interfaces.isEmpty())
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
			buffer.append(GenericUtils.createComment(cls.Documentation()) + "\n");
	}

	/**
	 * This method returns if the given class is an entity - for now it just
	 * checks for a hardcoded package name called "bizobjects" - in production
	 * it should look for a stereotype like entity.
	 * 
	 * @param cls
	 */
	private static String addEntityIfApplies(Class cls) {
		
		if(cls.getMetaClass().getSimpleName().equals("Class"))
			return "";
		
		if (cls.getMetaClass().getSimpleName().equals("Entity") ){
			entityMappings.append("<mapping class=\"" + fullyQualifiedName(cls) + "\"/>\n");
			//System.out.println(entityMappings);
			return "" /*"@Entity\n"*/;
		}
		else
			return "@" + cls.getMetaClass().getSimpleName() + "\n";
	}
	
	public static boolean isEntity(Class cls){
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

	/** Get property declaration  - e.g private String firstName = "" ;
	 * @param attribute
	 * @return
	 */
	public static String getPropertyDeclaration(Attribute attribute) {
		
		String declaration = new String();
		
		//if(attribute.getMetaClass().getName().equals("oaw4.demo.classic.uml.meta.Key"))
		//	declaration += "//" + attribute.getMetaClass().getName();
		
		 declaration += "private " + attribute.Type().NameS() + " "
				+ attribute.NameS();

		if (attribute.InitValue() != null)
			declaration += " = " + attribute.InitValue();

		declaration += ";";

		return declaration;
	}
	
	public static String fullyQualifiedName(Class cls){
		return cls.Package().NameS() + "."  + cls.NameS();
	}

	public static String manyToOne(AssociationEnd ae){
		if(StereoTypeManager.isEntity(ae.Class()) )
			return "@ManyToOne";
		else
			return "";
	}
	
	/**
	 * @return input firstName - output First Name
	 */
	public static String getViewLabelFromVariable(String varName){
		char[] characters = varName.toCharArray();
		for(char ch : characters){
			if(Character.isUpperCase(ch))
				varName = varName.replace(new String(ch + ""), " " + ch);
		}
		return WordUtils.capitalizeFully(varName);
	}
	
	/** Makes the first letter small case
	 * @param varName
	 * @return
	 */
	public static String asVariable(String varName){
		return StringUtils.uncapitalize(varName);
	}
	
	public static String generateEnumLiterals(Enumeration enm){
		StringBuffer buffer = new StringBuffer();
		
		for(int i = 0; i < enm.Literal().size(); i++){
			buffer.append(enm.Literal(i).NameS());
			if( i < (enm.Literal().size() - 1 ) )
				buffer.append(", ");
		}
		
		return buffer.toString();
	}
}