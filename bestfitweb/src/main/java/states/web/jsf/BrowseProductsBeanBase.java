package states.web.jsf;

import java.util.Date;

import org.apache.log4j.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public abstract class BrowseProductsBeanBase {

	private facades.ServiceFacade serviceFacade;

	public facades.ServiceFacade getServiceFacade() {
		return serviceFacade;
	}

	public void setServiceFacade(facades.ServiceFacade serviceFacade) {
		this.serviceFacade = serviceFacade;
	}

	private String product;
	public void setProduct(String param) {
		this.product = param;
	}
	public String getProduct() {
		return product;
	}

	private static final Logger log = Logger
			.getLogger(BrowseProductsBeanBase.class);

	public String viewProduct() {
		try {
			doViewProduct();
		} catch (Throwable t) {
			createErrorMessage(t.getMessage(), "Update Error");
			log.error(t);
			return "failure";

		}

		return "viewProduct";
	}

	public abstract String doViewProduct();

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
