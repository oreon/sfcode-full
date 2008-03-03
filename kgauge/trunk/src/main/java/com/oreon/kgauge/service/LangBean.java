package com.oreon.kgauge.service;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import java.util.*;
import java.util.Locale;



public class LangBean {
	
	 Locale locale = Locale.getDefault();
	private HashMap<String, Locale> locales = null;

    public LangBean() {
        locales = new HashMap<String, Locale>(4);
        locales.put("english", new Locale("en", "US"));
        locales.put("englishUK", new Locale("en", "GB"));
        locales.put("french", new Locale("fr", "FR"));
        locales.put("german", new Locale("de", "DE"));
    }
    
    public Locale getLocale(){
    	return locale;
    }
       
    public void onChooseLocale(ActionEvent event) {
        String current = event.getComponent().getId();
        FacesContext context = FacesContext.getCurrentInstance();
        context.getViewRoot().setLocale((Locale) locales.get(current));
        this.locale = context.getViewRoot().getLocale();
        System.out.println("Changed locale to: "+current);
    
    }
    
}

