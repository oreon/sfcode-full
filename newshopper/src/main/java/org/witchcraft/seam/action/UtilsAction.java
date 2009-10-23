package org.witchcraft.seam.action;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.jboss.seam.annotations.Name;

@Name("utilsAction")
public class UtilsAction {
	
	public static String getBasePath(){
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
	}

}
