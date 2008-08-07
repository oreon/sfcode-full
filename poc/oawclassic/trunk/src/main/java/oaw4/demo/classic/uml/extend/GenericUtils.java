package oaw4.demo.classic.uml.extend;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;
import org.apache.log4j.Logger;
import org.openarchitectureware.meta.uml.Type;

/**
 * Class for static utils e.
 * 
 * @author jsingh
 * 
 */
public class GenericUtils {

	private static final Logger logger = Logger.getLogger(GenericUtils.class);

	public static String createSingleLineComment(String commentText) {
		return "//" + commentText + "\n";
	}

	public static String createComment(String string) {
		// System.out.println("returning " + "/*" + string + "*/");
		return "/*" + string + "*/";
	}

	/**
	 * Will capitalize the first character and uncapitalize all other characters
	 * 
	 * @param string
	 * @return
	 */
	public static String toFirstUpper(String string) {
		return WordUtils.capitalizeFully(string);
	}

	/**
	 * First letter will be made small case
	 * 
	 * @param arg
	 * @return
	 */
	public static String toFirstLower(String arg) {
		return WordUtils.uncapitalize(arg);
	}

	public static String toUpper(String string) {
		return string.toUpperCase();
	}

	/**
	 * if arg is [x], it will return x
	 * 
	 * @param target
	 * @return
	 */
	public static String removeSquareBrackets(String target) {
		String s = target;
		s = s.replace("[", "");
		s = s.replace("]", "");
		target = s;
		return s;
	}

	/**
	 * If a name is of the form xxx.yyy.zzz - this function will return zzz
	 * 
	 * @param target
	 * @return
	 */
	public static String getSimpleName(String target) {
		String arr[] = target.split("\\.");
		return arr[arr.length - 1];
	}

	/**
	 * Get the fully qualified name for type e.g Integer would return
	 * java.lang.Integer
	 * 
	 * @param type
	 * @return
	 */
	public static String getFullyQualifiedName(Type type) {
		return type.getClass().getCanonicalName();
	}

	/**
	 * Returns the object equivalent of a primitive type e.g int would return
	 * Integer and boolean would return Boolean
	 * 
	 * @param arg
	 * @return
	 */
	public static String getObjectTypeFromPrimitive(String arg) {
		String arrPrimitiveTypes[] = { "INTEGER", "INT", "FLOAT", "DOUBLE",
				"STRING", "DATE", "BOOLEAN", "LONG" };

		List<String> lstPrimitives = Arrays.asList(arrPrimitiveTypes);

		String upperCaseArg = arg.toUpperCase();

		if (!lstPrimitives.contains(upperCaseArg)) {
			return arg;
		}

		if (arg.equalsIgnoreCase("int"))
			return "Integer";
		else if (arg.equals("java.util.Date"))
			return arg;
		else if (arg.equals("Date"))
			return "java.util.Date";
		else
			return toFirstUpper(arg);
	}

	public static String removeSpacesAndQuotes(String target) {
		String arg = target;
		arg = arg.replace('"', ' ');
		arg = arg.replace(" ", "");
		arg.trim();
		System.out.println("after removing spaces & quotes returning " + arg);
		return arg;
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

	/**
	 * Takes a string like name,city, country and return a List of the split
	 * strings
	 * 
	 * @param target
	 * @return
	 */
	public static List<String> stringArrayAsList(String arg) {

		return tokenizeString(arg, "[ ]*,[ ]*|\\}|\\{");
	}

	/**
	 * Tokenize a given string based on the seperators asd
	 * 
	 * @param arg
	 * @param seperators
	 * @return
	 */
	public static List<String> tokenizeString(String arg, String seperators) {
		String[] array = arg.split(seperators);
		List<String> lst = new ArrayList<String>();

		for (String arrArg : array) {
			if (!StringUtils.isEmpty(arrArg))
				lst.add(arrArg);
		}
		return lst;
	}

	/**
	 * Reads a property from the workflow properties file
	 * 
	 * @param propertyName
	 * @return
	 */
	/**
	 * @param key
	 * @return
	 */
	public static String readProperty(String key) {
		Properties properties = new Properties();
		try {
			InputStream stream = GenericUtils.class
					.getResourceAsStream("/workflow.properties");
			if (stream == null) {
				logger
						.error("workflow properties file is not in the classpath");
				return "COULDNT_READ_FILE";
			}
			properties.load(stream);
		} catch (IOException e) {
			e.printStackTrace();
			return "COULDNT_READ_FILE";
		}
		String value = properties.getProperty(key);
		logger.info("Returning value " + value + " for key " + key);
		return value;
	}
	
	/** Tries to look up the key - if not found returns the defaultProperty
	 * @param key
	 * @param defaultProperty
	 * @return
	 */
	public static String readProperty(String key, String defaultProperty) {
		String value = readProperty(key);
		return key == null ? defaultProperty : value;
	}

}
