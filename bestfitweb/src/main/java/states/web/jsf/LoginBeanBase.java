package states.web.jsf;

import java.util.Date;

import org.apache.log4j.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public abstract class LoginBeanBase {

	private facades.ServiceFacade serviceFacade;

	public facades.ServiceFacade getServiceFacade() {
		return serviceFacade;
	}

	public void setServiceFacade(facades.ServiceFacade serviceFacade) {
		this.serviceFacade = serviceFacade;
	}

}
