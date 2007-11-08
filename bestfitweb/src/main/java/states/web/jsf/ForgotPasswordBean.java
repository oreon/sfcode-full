package states.web.jsf;

import org.apache.log4j.Logger;
import org.witchcraft.model.mail.GenericMailTask;
import org.witchcraft.model.support.errorhandling.BusinessException;

import bizobjects.Customer;

public class ForgotPasswordBean extends ForgotPasswordBeanBase {

	private static final Logger log = Logger
			.getLogger(ForgotPasswordBean.class);

	@Override
	public String doEmailPassword() {
		System.out.println("forgot password called");
		Customer customer = getServiceFacade().getCustomerService()
			.findByEmail(getPassword());
		
		if(customer != null){
			//new GenericMailTask().s
			return "success";
		}
		else
			throw new BusinessException("Email.NotFound");
	}

}
