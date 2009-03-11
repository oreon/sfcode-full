package org.cerebrum.domain.action;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.cerebrum.domain.Customer;
import org.jboss.seam.mock.SeamTest;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CusotmerActionTest extends SeamTest{
	/*
	@Test(dependsOnMethods={ "checkRegistration" })
    public void checkSecondRegistration() throws Exception {
      new ComponentTest() {
        @Override
        protected void testComponents() throws Exception {
          setValue("#{user.emailAddress}", "chrismaki@mac.com");
          setValue("#{user.firstName}", "chris");
          setValue("#{user.lastName}", "maki");
          setValue("#{user.password}", "jjj");
          setValue("#{register.verifyPassword}", "vbn");
          invokeMethod("#{register.register}");
          assert getValue("#{register.registrationSuccessful}").equals(false);
        }
        
      }.run();
      
    }*/

	@Test
	public void testRegisterAction() {
		EntityManager em = getEntityManagerFactory().createEntityManager();
		em.getTransaction().begin();

		Customer gavin = new Customer();
		gavin.setLastName("Gavin King");
		gavin.setFirstName("1ovthafew");
		gavin.setEmail("gk@jboss.com");
		// gavin.setPassword("secret");

		CustomerAction action = new CustomerAction();
		action.setEntity(gavin);
		action.setEntityManager(em);

		assert "save".equals(action.save());

		em.getTransaction().commit();
		em.close();
	}

	private EntityManagerFactory emf;

	public EntityManagerFactory getEntityManagerFactory() {
		return emf;
	}

	@BeforeClass
	public void init() {
		emf = Persistence
				.createEntityManagerFactory("newSeam");
	}

	@AfterClass
	public void destroy() {
		emf.close();
	}

}
