
package com.wc.jshopper.domain.action;

	import javax.persistence.EntityManager;
	import javax.persistence.Query;
	
import org.jboss.seam.mock.AbstractSeamTest.ComponentTest;
	import org.jboss.seam.security.Identity;
	import org.testng.annotations.Test;
	import org.witchcraft.base.entity.*;
	import org.hibernate.annotations.Filter;
	
	import org.testng.annotations.BeforeClass;
	import org.witchcraft.seam.action.BaseAction;
import com.wc.jshopper.domain.Customer;
import com.wc.jshopper.domain.Department;


public class CustomerTest extends CustomerTestBase{
	
	@Test
	public void save() throws Exception {
		EntityManager em = getEntityManagerFactory().createEntityManager();
		//em.getTransaction().begin();

		new ComponentTest() {

			protected void testComponents() throws Exception {

				Department dep = new Department();
				dep.setName("Finance");
				
				CustomerAction customerAction = (CustomerAction) getValue("#{customerAction}");

				String ret = customerAction.retrieveCredentials("erica@rogers.com");
				//assert customer != null;
				//System.out.println(customer.getDisplayName());
			}

		}.run();

		
	/*
		em.persist(dep);
		
		*/
		//em.getTransaction().commit();
	}
	
}
