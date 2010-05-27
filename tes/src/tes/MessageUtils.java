package tes;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;



public class MessageUtils {
	
	
	public static String padString(String arg, int padlength) {
		StringBuffer sb = new StringBuffer();
		sb.append(arg);
		int padCount = padlength - arg.length();
		for (int i = 0; i < padCount; i++)
			sb.append(" ");
		return sb.toString();
	}

}
