package oaw4.demo.classic.uml.extend;

import org.apache.commons.lang.WordUtils;

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
	
	public static String toFirstUpper(String string){
		return WordUtils.capitalizeFully(string);
	}
	
	public static String toUpper(String string){
		return string.toUpperCase();
	}

}
