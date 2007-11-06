package oaw4.demo.classic.uml.extend;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.WordUtils;
import org.openarchitectureware.meta.uml.Type;

/** Class for static utils e.
 * @author jsingh
 *
 */
public class GenericUtils {
	
	public static String createSingleLineComment(String commentText){
		return "//" +  commentText +  "\n";
	}

	public static String createComment(String string) {
		//System.out.println("returning " + "/*" + string + "*/");
		return "/*" + string + "*/";
	}
	
	/** Will capitalize the first character and uncapitalize 
	 *  all other characters
	 * @param string
	 * @return
	 */
	public static String toFirstUpper(String string){
		return WordUtils.capitalizeFully(string);
	}
	
	public static String toUpper(String string){
		return string.toUpperCase();
	}
	
	/** if arg is [x], it will return x
	 * @param target
	 * @return
	 */
	public static String removeSquareBrackets(String target){
		String s = target;
		s = s.replace("[", "");
		s = s.replace("]", "");
		target = s;
		return s;
	}
	
	/** If a name is of the form xxx.yyy.zzz - this function will 
	 *  return zzz
	 * @param target
	 * @return
	 */
	public static String getSimpleName(String target){
		String arr[] = target.split("\\.");
		return arr[arr.length - 1];
	}
	
	/** Get the fully qualified name for type e.g Integer would 
	 *  return java.lang.Integer
	 * @param type
	 * @return
	 */
	public static String getFullyQualifiedName(Type type){
		return type.getClass().getCanonicalName();
	}
	
	/** Returns the object equivalent of a primitive type
	 * e.g int would return Integer and boolean would return Boolean
	 * @param arg
	 * @return
	 */
	public static String getObjectTypeFromPrimitive(String arg){
		String arrPrimitiveTypes[] = {"INTEGER", "INT", "FLOAT", "DOUBLE",
			"STRING", "DATE", "BOOLEAN", "LONG"
		};
		
		List<String> lstPrimitives = Arrays.asList(arrPrimitiveTypes);
		
		String upperCaseArg = arg.toUpperCase();
		
		if(!lstPrimitives.contains(upperCaseArg)){
			return arg;
		}
		
		if(arg.equalsIgnoreCase("int"))
			return "Integer";
		else if (arg.equals("java.util.Date"))
			return arg;
		else if (arg.equals("Date"))
			return "java.util.Date";
		else 
			return toFirstUpper(arg);
	}

}
