package oaw4.demo.classic.uml.extend;

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

}
