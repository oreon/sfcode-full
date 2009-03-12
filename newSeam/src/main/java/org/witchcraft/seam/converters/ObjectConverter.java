package org.witchcraft.seam.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.ConverterException;

import org.jboss.seam.ScopeType;
import org.jboss.seam.Seam;
import org.jboss.seam.annotations.Install;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.faces.Converter;
import org.jboss.seam.annotations.intercept.BypassInterceptors;

@Name(ObjectConverter.SEAM_ID)
@Scope(ScopeType.PAGE)
@Install(precedence = Install.APPLICATION)
@Converter
@BypassInterceptors
public class ObjectConverter implements javax.faces.convert.Converter {
    
    public static final String SEAM_ID = "GenericObjectConverter";
	
	
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) throws ConverterException {

		if (value == null || component == null) {
			return null;
		}	
		
		// Get the relevant Component's objectStore
		ObjectConverterStore ocStore = (ObjectConverterStore)ComponentStore.instance().get(component, context);
		
		if (ocStore == null) {
			return null;
		}
		
		return ocStore.get(value);
	}


	


	public String getAsString(FacesContext context, UIComponent component,
			Object value) throws ConverterException {
		
		if (value == null || component == null) {
			return null;
		}

		ObjectConverterStore ocStore = (ObjectConverterStore)ComponentStore.instance().get(component, context);
		
		if (ocStore == null) {
			ocStore = new ObjectConverterStore();
		}
		
		if(context.getApplication().getStateManager().isSavingStateInClient(context)
				&& Seam.isEntityClass(value.getClass())) {
			throw new ConverterException("ObjectConverter is unable to handle entity classes when client-side " +
					"state saving is enabled. Please use EntityConverter instead, or enable server-side state saving");
		}
		
		String	key = ocStore.put(value);	
		ComponentStore.instance().put(component, ocStore, context);

		return key;
	}

}