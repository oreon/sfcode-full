package org.witchcraft.utils;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.apache.commons.lang.StringUtils;

/**
 * This class converts the input string to first letter capital - useful for converting strings such as names etc 
 * This converter is applied to Strings which are 'nameType' in UML model.
 * @author J Singh 
 *
 */
public class CapitalizeConverter  implements Converter{
	
	
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		return StringUtils.capitalize(value);
	}

	
	public String getAsString(FacesContext arg0, UIComponent arg1, Object modelValue) {
		String value = (String) modelValue;
		return StringUtils.capitalize(value);
	}

}
