package org.witchcraft.model.jsf.converters;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.witchcraft.model.support.BusinessEntity;

public abstract class GenericUIConverter implements Converter{
	
	protected Logger log = Logger.getLogger(KeyToObjectConverter.class);

	public GenericUIConverter() {
	}

	public String getAsString(FacesContext facesContext,
			UIComponent uiComponent, Object obj) {

		if (obj == null) {

			return "";
		}
		return ((BusinessEntity) obj).getId().toString();
	}

	


	protected Object createNewValue(FacesContext context, UIComponent component,
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
			throw new RuntimeException("Converter error could not instantiate the assignable object", e);
		}
	}
	
}
