package org.witchcraft.model.jsf.converters;

import java.util.logging.Logger;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import org.apache.commons.lang.StringUtils;
import org.witchcraft.model.support.BusinessEntity;

/**
 * This class is used to convert to and from objects and strings in
 * controls such as selectonemenu
 * @author jsingh
 *
 */
public class KeyToObjectConverter implements Converter {

	protected Logger log = Logger.getLogger("com.crazysquirrel");

	public KeyToObjectConverter() {
	}

	public String getAsString(FacesContext facesContext,
			UIComponent uiComponent, Object obj) {

		if (obj == null) {

			return "";
		}
		return ((BusinessEntity) obj).getId().toString();
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
			if(be.getId().longValue() != id){
				return createNewValue(facesContext, component, id);
			}
			return be;
		}

	}


	private Object createNewValue(FacesContext context, UIComponent component,
			long id) {
		//component.get
		ValueExpression ve = component.getValueExpression("value");
		try {
			BusinessEntity pk = (BusinessEntity) ve.getType(
					context.getELContext()).newInstance();
			pk.setId(id);
			return pk;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Converer error could not instantiate the assignable object", e);
		}
	}
}