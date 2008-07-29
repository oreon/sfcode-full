package com.cc.civlit.web.jsf;

import java.util.List;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.richfaces.component.html.HtmlListShuttle;
import org.witchcraft.model.support.BusinessEntity;

import com.cc.civlit.domain.CaseAdministrator;

/**
 * This class is used to convert to and from objects and strings in controls
 * such as selectonemenu
 * 
 * @author jsingh
 * 
 */
public class ListShuttleComponentToObjectConverter implements Converter {

	//private static final String ID_DATA_SEPERATOR = ":@#";
	protected Logger log = Logger
			.getLogger(ListShuttleComponentToObjectConverter.class);

	public ListShuttleComponentToObjectConverter() {
	}

	public String getAsString(FacesContext facesContext,
			UIComponent uiComponent, Object obj) {

		if (obj == null) {
			return "";
		}
		return ((BusinessEntity) obj).getId().toString();
	}

	protected Object createNewValue(FacesContext context,
			UIComponent component, long id) {
		// component.get
		ValueExpression ve = component.getValueExpression("targetValue");
		try {
			List listObj = (List) ve.getValue(context.getELContext());

			for (Object object : listObj) {
				if (((BusinessEntity) object).getId() == id) {
					return object;
				}
			}
			Object newObj = getObjectType(context, component).newInstance();
			BusinessEntity pk = (BusinessEntity) newObj;
			pk.setId(id);
			return pk;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(
					"Converter error could not instantiate the assignable object",
					e);
		}
	}

	private Class getObjectType(FacesContext context, UIComponent component) {
		ValueExpression ve = component.getValueExpression("sourceValue");
		List listObj = (List) ve.getValue(context.getELContext());
		if(listObj.isEmpty())
			throw new RuntimeException ("Soruce list should not be empty");
		return listObj.get(0).getClass();
	}

	public Object getAsObject(FacesContext facesContext, UIComponent component,
			String value) throws ConverterException {

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

		HtmlListShuttle shuttleComponent = (HtmlListShuttle) component;

		if (shuttleComponent.getValue() == null) {
			return createNewValue(facesContext, component, id);
		} else {
			BusinessEntity be = (BusinessEntity) (shuttleComponent).getValue();

			// To be sure the valueChangeListener is called
			if (be == null || be.getId() == null
					|| be.getId().longValue() != id) {
				return createNewValue(facesContext, component, id);
			}
			return be;
		}

	}

}