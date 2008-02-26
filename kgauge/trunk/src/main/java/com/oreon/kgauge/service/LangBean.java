package com.oreon.kgauge.service;

import javax.faces.event.ActionEvent;

public class LangBean {
	
		 private String locale;
			
		 public String getLocale() {
		  return locale;
		 }

		 public void setLocale(String locale) {
		  this.locale = locale;
		 }
			
		 public void setLanguage(ActionEvent event){
		  String id = new String(event.getComponent().getId());
		  String thislocale=id.substring(12);
		  System.out.println("event id: "+id+", locale="+thislocale);
		  setLocale(thislocale);
		 }
		}

