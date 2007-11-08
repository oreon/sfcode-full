package states.web.jsf;

import java.util.Date;

import org.apache.log4j.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public abstract class ForgotPasswordBeanBase {

	private facades.ServiceFacade serviceFacade;

	public facades.ServiceFacade getServiceFacade() {
		return serviceFacade;
	}

	public void setServiceFacade(facades.ServiceFacade serviceFacade) {
		this.serviceFacade = serviceFacade;
	}

	private String password;
	public void setPassword(String param) {
		this.password = param;
	}
	public String getPassword() {
		return password;
	}

	private static final Logger log = Logger
			.getLogger(ForgotPasswordBeanBase.class);

	public String emailPassword() {
		try {
			doEmailPassword();
		} catch (Throwable t) {
			createErrorMessage(t.getMessage(), "Update Error");
			log.error(t);
			return "failure";

		}

		return "emailPass";
	}

	public abstract String doEmailPassword();

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
