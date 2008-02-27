package com.oreon.kgauge.service;

import java.util.Locale;

import javax.faces.event.ActionEvent;
import javax.faces.context.FacesContext;

public class LangBean {
	
		 private Locale locale=FacesContext.getCurrentInstance().getViewRoot().getLocale();
		 String localeSt="en";
		 
		 
		 public Locale getLocale() {
		  return locale;
		 }

		 public void setLocale(String locale) {
		  this.localeSt = locale;
		 }
		
		 
 
 	        public void setLocale(Locale locale) {
			  	    this.localeSt = locale.getLanguage().substring(0, 2);
			  	  FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
 	        }

		 
		 
		 public void setLanguage(ActionEvent event){
		  String id = new String(event.getComponent().getId());
		  String thislocale=id.substring(12);
		  System.out.println("event id: "+id+", locale="+thislocale);
		  localeSt=(thislocale);
		 }
		}

