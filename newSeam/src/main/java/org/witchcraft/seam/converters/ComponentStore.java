package org.witchcraft.seam.converters;

import java.util.HashMap;
import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.ConverterException;

import org.jboss.seam.Component;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Install;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.contexts.Contexts;

import static org.jboss.seam.annotations.Install.APPLICATION;

@Name(ComponentStore.SEAM_ID)
@Install(precedence = APPLICATION)
@Scope(ScopeType.PAGE)
public class ComponentStore {
    
    public static final String SEAM_ID = "ComponentStore";

	private final Map<String, Object> components = new HashMap<String, Object>();

	public void put(UIComponent component, ObjectConverterStore ocStore, FacesContext context) {
		components.put(getKey(component, context), ocStore);
	}

	public Object get(UIComponent component, FacesContext context) {
		return components.get(getKey(component, context));
	}
	
	public String contains(Object object) {
		for (Map.Entry<String, Object> entry : components.entrySet()) {
			if (entry.getValue().equals(object)) {
				return entry.getKey();
			}
		}
		return null;
	}

	public static ComponentStore instance() {
		if (!Contexts.isPageContextActive()) {
			throw new IllegalArgumentException("Page scope not active");
		}
		return (ComponentStore) Component
				.getInstance(ComponentStore.class);
	}
	
	private String getKey(UIComponent component, FacesContext context) {
		if (component.getId() == null) {
			throw new ConverterException("The component ID is null. Each component must have a unique ID");
		}
		
		return component.getClientId(context);
	}
}

