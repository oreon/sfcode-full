package org.witchcraft.model.jsf.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import org.apache.commons.lang.StringUtils;

/** This class is a custom converter for enums - 1.2 has built in support 
 * but the current release seems to have some issues
 * @author jsingh
 *
 */
public class EnumConverter implements Converter {

	private static final String SEPARATOR = "::";

	public static final String CONVERTER_FOR_CLASS = "java.lang.Enum";
	
	@SuppressWarnings("unchecked")
	public Object getAsObject(FacesContext context, UIComponent component, String value)
		throws ConverterException {
		if(StringUtils.isEmpty(value)) {
			return null;
		}
		
		String[] sa = StringUtils.split(value,SEPARATOR,2);
		
		if(sa == null || sa.length != 2) {
			throw new ConverterException("Couldn't split input in two pieces: " + value);
		}
		
		String className = sa[0];
		String enumName = sa[1];
		
		if(StringUtils.isEmpty(className)) {
			throw new ConverterException("Empty class name");
		}
		if(StringUtils.isEmpty(enumName)) {
			throw new ConverterException("Empty enum name");
		}	

		Class enumClass;
		try {
			enumClass = Class.forName(className);
		} catch (ClassNotFoundException e) {
			throw new ConverterException("Class for enum type " + className + " not found",e);
		}
		
		return Enum.valueOf(enumClass,enumName);
	}

	public String getAsString(FacesContext context, UIComponent component, Object value)
		throws ConverterException {
		if(value == null) return "";
		else if (value instanceof Enum) {
			Enum e = (Enum) value;
			Class declaringClass = e.getDeclaringClass();
			String className = declaringClass.getName();
			return className.toString() + SEPARATOR + value.toString();
		} else {
			throw new ConverterException("Expected Enum type, got " + value.getClass());
		}
	}
}
