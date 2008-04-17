package org.witchcraft.model.support.jsfbackingbeans;

import java.util.HashMap;
import java.util.Locale;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

/**
 * This class enables locae/language switching  
 * @author jesing
 *
 */
public class LocaleChooserBackingBean {

	private Locale locale = Locale.getDefault();
	private String language; 
	
	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public void onChooseLocale(ValueChangeEvent event) {
		String current = (String)event.getNewValue();
		FacesContext context = FacesContext.getCurrentInstance();
		//TODO: Change to a factory for optimized locale object creation
		context.getViewRoot().setLocale(new Locale(current) );
		this.locale = context.getViewRoot().getLocale();
		Locale.setDefault(locale);
	}

	public void setLocale() {
		FacesContext context = FacesContext.getCurrentInstance();
		context.getViewRoot().setLocale(locale);
		this.locale = context.getViewRoot().getLocale();
	}
}
