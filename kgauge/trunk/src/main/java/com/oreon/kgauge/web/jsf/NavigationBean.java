package com.oreon.kgauge.web.jsf;

import java.util.Map;

import javax.faces.context.FacesContext;

public class NavigationBean {
	
	private String currentPage = "/crud/domain/test.xhtml";

	public String getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(String page) {
		String arrPagePackage[] = page.split("-");
		if(arrPagePackage[1].startsWith("new"))
			this.currentPage = "/crud/" + arrPagePackage[0] + "/" +  arrPagePackage[1].substring(3) + ".xhtml";
		else
			this.currentPage = "/crud/" + arrPagePackage[0] + "/" +  arrPagePackage[1] + "List.xhtml";
		/*
		if(page.equals("questions") )
			this.currentPage = "/crud/domain/test.xhtml";
		else
			this.currentPage = "/crud/domain/test2.xhtml";*/
	}
	
	public void updatePage(){
		Map paramMap = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String page = (String) paramMap.get("nextPage");
		setCurrentPage(page);
	}
	
	

}
