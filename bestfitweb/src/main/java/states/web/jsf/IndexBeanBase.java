package states.web.jsf;

import java.util.Date;

import org.apache.log4j.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public abstract class IndexBeanBase {

	private facades.ServiceFacade serviceFacade;

	public facades.ServiceFacade getServiceFacade() {
		return serviceFacade;
	}

	public void setServiceFacade(facades.ServiceFacade serviceFacade) {
		this.serviceFacade = serviceFacade;
	}

	private static final Logger log = Logger.getLogger(IndexBeanBase.class);

	public String authenticate() {
		try {
			doAuthenticate();
		} catch (Throwable t) {
			createErrorMessage(t.getMessage(), "Update Error");
			log.error(t);
			return "failure";

		}

		return "login";
	}

	public abstract String doAuthenticate();

	/** Creates and add an error message to the faces context
	 * @param errorDetail
	 * @param errorTitle
	 */
	private void createErrorMessage(String errorDetail, String errorTitle) {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, errorTitle,
						errorDetail));
	}

}
