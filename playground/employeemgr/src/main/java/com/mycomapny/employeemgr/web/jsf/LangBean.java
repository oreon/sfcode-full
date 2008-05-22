package com.mycomapny.employeemgr.web.jsf;

import java.util.HashMap;
import java.util.Locale;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;



public class LangBean {
	
	 Locale locale = Locale.getDefault();
	private HashMap<String, Locale> locales = null;

    public LangBean() {
        locales = new HashMap<String, Locale>(4);
        locales.put("english", new Locale("en", "US"));
        locales.put("englishUK", new Locale("en", "GB"));
        locales.put("french", new Locale("fr", "FR"));
        locales.put("german", new Locale("de", "DE"));
        locales.put("en", new Locale("en"));
    }
    
    public Locale getLocale(){
    	return locale;
    }
       
    public void onChooseLocale(ActionEvent event) {
        String current = event.getComponent().getId();
        FacesContext context = FacesContext.getCurrentInstance();
        context.getViewRoot().setLocale((Locale) locales.get(current));
        this.locale = context.getViewRoot().getLocale();
        Locale.setDefault(locale);
        System.out.println("Changed locale to: "+current);
    }
    
    public void setLocale(){
    	FacesContext context = FacesContext.getCurrentInstance();
        context.getViewRoot().setLocale(locale);
        this.locale = context.getViewRoot().getLocale();
    	
    }
}

