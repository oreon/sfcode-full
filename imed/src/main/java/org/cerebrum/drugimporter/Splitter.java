package org.cerebrum.drugimporter;
 
	
	import java.util.regex.Pattern;
 

	public class Splitter  {
	    public static void main(String[] args) throws Exception {
	        // Create a pattern to match breaks
	        Pattern p = Pattern.compile("[,\\s]+");
	        // Split input with the pattern
	        String[] result = 
	                 p.split("one,two, three   four ,  five");
	        for (int i=0; i<result.length; i++)
	            System.out.println(result[i]);
	    } 
}