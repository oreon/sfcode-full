package org.caisi.persistence.viewhelper;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;
import org.caisi.persistence.base.Range;
import org.caisi.persistence.base.UserMessage;

public class ViewHelper {

	private static final String MESSAGE = "message";

	public static void createMessage(HttpSession session, String text) {
		session.setAttribute(MESSAGE, new Message(text, false));
	}
	
	public static void createMessage(HttpServletRequest request, String text) {
		request.setAttribute(MESSAGE, new Message(text, false));
	}
	
	public static void createMessage(HttpServletRequest request, String text,
			boolean isError) {
		request.setAttribute(MESSAGE, new Message(text, isError));
	}

	public static void createMessage(HttpSession session, String text,
			boolean isError) {
		session.setAttribute(MESSAGE, new Message(text, isError));
	}

	public static String getMessage(HttpServletRequest request) {
	//	HttpSession session = request.getSession();
		Message message = (Message) request.getAttribute(MESSAGE);

		if (message != null) {
			String divId = message.isError() ? "errorMessage" : "infoMessage";
			request.setAttribute(MESSAGE, null);
			return ("<div id='" + divId + "'>" + message.getText() + "</div>");
		}
		return StringUtils.EMPTY;
	}

	
	
	public static Range<Integer> createIntRange(HttpServletRequest request,String fldName, String fromFld, String toFld){
		int from = (fromFld != null ) ? Integer.parseInt(request.getParameter(fromFld)):null;
		int to = (toFld != null ) ? Integer.parseInt(request.getParameter(toFld)):null;
		return new Range<Integer>( fldName,from, to);
	}
	
	
	
	public static Map<String, String> createHCTypeMap(){
		java.util.Map<String, String> map = new java.util.LinkedHashMap<String, String>();
		map.put("ON" ,"Ontario");
		map.put("AB","Alberta");
		map.put("BC","British Columbia");
		map.put("MB","Manitoba");
		map.put("NF","Newfoundland");
		map.put("NB","New Brunswick");
		map.put("YT","Yukon");
		map.put("NS","Nova Scotia");
		map.put("PE","Prince Edward Island");
		map.put("SK","Saskatchewan");
		return map;
	}
	
	public static Map<String, String> createGenderMap(){
		String[] genders = {"F", "M"};
		return createMapFromArray(genders);
	}
	
	public static Map<String, String> createRecordsPerPageMap(){
		String[] pages = {"10","20","50","100"};
		return createMapFromArray(pages);
	}
	
	private static Map<String, String> createMapFromArray(String[] arrStrings){
		java.util.Map<String, String> map = new java.util.LinkedHashMap<String, String>();
		for (String val : arrStrings) {
			map.put(val, val);
		}
		return map;
	}
	
	public static String createMessage(List<UserMessage> msgs){
		StringBuilder builder = new StringBuilder("<UL>");
		for (UserMessage userMessage : msgs) {
			builder.append("<LI>" + userMessage.getFormattedMessage() + "</LI>");
		}
		return builder.toString();
	}
	
	public static String getEmptyIfNull(String property){
		return (property == null || property.equalsIgnoreCase("null"))?StringUtils.EMPTY:property;
	}

	/** Will convert a camelcase variable to title case e.g firstName will be returned as First Name
	 * @param varName
	 * @param concatChar
	 * @return
	 */
	public static String convertCamelCaseToTitleCase(String varName,
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
	
	public static String convertCamelCaseToTitleCase(String varName){
		return convertCamelCaseToTitleCase(varName, " ");
	}

}
