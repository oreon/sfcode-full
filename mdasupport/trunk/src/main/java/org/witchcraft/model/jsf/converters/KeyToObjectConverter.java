package org.witchcraft.model.jsf.converters;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.witchcraft.model.support.BusinessEntity;

/**
 * This class is used to convert to and from objects and strings in
 * controls such as selectonemenu
 * @author jsingh
 *
 */
public class KeyToObjectConverter extends GenericUIConverter {

	protected Logger log = Logger.getLogger(KeyToObjectConverter.class);

	public KeyToObjectConverter() {
	}

	public Object getAsObject(FacesContext facesContext,
			UIComponent component, String value) throws ConverterException {
		
		if (value == null || value.equals(StringUtils.EMPTY)) {
			return null;
		}
 
		long id;
		try {
			id = Long.parseLong(value);
		} catch (NumberFormatException e) {
			id = 0;
			e.printStackTrace();
		}
	
		
		if (((UIInput) component).getValue() == null) {
			return createNewValue(facesContext, component, id);
		} else {
			BusinessEntity be = (BusinessEntity) ((UIInput) component).getValue();
			
			//To be sure the valueChangeListener is called
			if(be == null || be.getId() == null || be.getId().longValue() != id){
				return createNewValue(facesContext, component, id);
			}
			return be;
		}

	}


	
}