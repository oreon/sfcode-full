package com.oreon.smartsis;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import com.oreon.smartsis.domain.Student;
import com.oreon.smartsis.web.action.domain.StudentAction;

@Path("/customer")
@Name("customerResource")
public class MyCustomerResource {
	
	@In(create=true)
	StudentAction studentAction;

	@GET
	@Path("/{customerId}")
	@Produces("application/xml")
	public Student getCustomer(@PathParam("customerId") long id) {
		return studentAction.loadFromId(id);
	}

}
