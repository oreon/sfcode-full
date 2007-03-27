package oaw4.demo.classic.uml.extend;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.openarchitectureware.meta.uml.classifier.AssociationEnd;
import org.openarchitectureware.meta.uml.classifier.Class;
import org.openarchitectureware.meta.uml.classifier.Interface;
import org.openarchitectureware.meta.uml.classifier.Operation;
import org.openarchitectureware.meta.uml.classifier.Package;
import org.openarchitectureware.meta.uml.classifier.Parameter;

import sun.security.krb5.internal.crypto.Aes128;



public class ClassUtil {
	
	public static String getPackageName(Class cls) {
		
		AssociationEnd ae;
		Operation operation;
		//operation.Parameter()
		
		String result = "";
		for (Package pck = cls.Package(); pck != null; pck = pck.SuperPackage()) {
			result = pck.NameS() + (result.length() > 0 ? "." + result : "");
			//System.out.print(pck.)
		}
		
		//cls.Interface().isEmpty()
		
		return result;
	
	}
	
	public static String operationHelper(Operation operation){
		String opText = getOperationDeclaration(operation);
		
		opText +=  ( operation.Class() instanceof Interface )?";":getOperationBody(operation);
		
		return opText ;
	}

	/** Create method declaration
	 * @param operation
	 * @return
	 */
	private static String getOperationDeclaration(Operation operation) {
		String opText = operation.Visibility() + " " + operation.ReturnType().NameS() 
			+ " " + operation.NameS() + "(";
		
		for(Iterator<Parameter> iter = operation.Parameter().iterator(); iter.hasNext(); ){
			Parameter param = iter.next();
			opText += param.Type().NameS() + " " + param.NameS();
			if(iter.hasNext())
				opText += ",";
 		}
		
		opText += ")";
		return opText;
	}
	
	/** Create method implementations for all implemented interfaces
	 * @param cls
	 * @return
	 */
	public static String generateOperationsForImplementedInterfaces(Class cls){
		List<Interface> interfaces = cls.Interface().toList();
		
		StringBuffer buffer = new StringBuffer();
		
		for (int i = 0; i < interfaces.size(); i++) {
			
			List<Operation> operations = interfaces.get(i).Operation().toList();
			
			for(int j = 0; j < operations.size(); j++){
				buffer.append( getOperationDeclaration(operations.get(j)) );
				buffer.append( getOperationBody(operations.get(j)) );
			}
		}
		
		return buffer.toString();
	}
	
	
	/**
	 * @param cls
	 * @return e.g public class <clsname> extends <superclass> implements <intf1>, <intf2>
	 */
	public static String getClassDeclaration(Class cls){
		
		StringBuffer buffer = new StringBuffer();
		
		buffer.append(addEntityIfApplies(cls));
		
		buffer.append("public class ");
		buffer.append(createComment(cls.Stereotype().size() + " " ));
		buffer.append(cls.NameS());
		
		if(cls.hasSuperClass())
			buffer.append(" extends " + cls.SuperClass().NameS());
		List<Interface> interfaces = cls.Interface().toList();
		
		if(!interfaces.isEmpty())
			buffer.append(" implements ");
		
		for (int i = 0; i < interfaces.size(); i++) {
			buffer.append(interfaces.get(i).NameS());
			
			if(i  < (interfaces.size() - 1 ) ) //add comma to all but last
				buffer.append(", ");
		}
		
		return buffer.toString();
	}

	/** This method returns if the given class is an entity - for now
	 * it just checks for a hardcoded package name called "bizobjects" - in production
	 * it should look for a stereotype like entity. 
	 * @param cls
	 */
	private static String addEntityIfApplies(Class cls) {
		if(StringUtils.equalsIgnoreCase(cls.Package().NameS(), "bizobjects" )  )
			return "@Entity\n";
		else
			return new String();
	}

	private static String createComment(String string) {
		// TODO Auto-generated method stub
		System.out.println("returning " + "/*"  + string + "*/");
		return "/*"  + string + "*/";
	}

	/** Creates method body
	 * @param operation
	 * @return
	 */
	private static String getOperationBody(Operation operation) {
		if(operation.hasReturnType()){
			return "{ return null; "  + "\n //should return " + operation.ReturnType().NameS() +"\n}";
		}else
			return "{ }";
	}

}