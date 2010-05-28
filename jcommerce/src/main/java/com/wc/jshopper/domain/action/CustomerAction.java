package com.wc.jshopper.domain.action;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.international.StatusMessages;

import com.wc.jshopper.domain.Customer;

//@Scope(ScopeType.CONVERSATION)
@Name("customerAction")
public class CustomerAction extends CustomerActionBase implements
		java.io.Serializable {

	public String retrieveCredentials(String email) {

		Customer customer = findCustomerByEmail(email);
		if (customer == null){
			statusMessages.addFromResourceBundle("noSuchCustomer", email);
			return "failure";
		}
		setInstance(customer);
		customer.getContactDetails().setEmail("singhjess@gmail.com");
		sendMail("/mails/retrievalEmail.xhtml");
		return "success";

	}
	
	public String register(){
		super.save();
		//sendMail("/mails/registrationSuccess.xhtml");
		return "success";
	}

}
