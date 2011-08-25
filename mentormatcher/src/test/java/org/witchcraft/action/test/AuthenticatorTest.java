package org.witchcraft.action.test;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.security.Identity;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.witchcraft.seam.action.BaseAction;

import com.jonah.mentormatcher.domain.Employee;
import com.jonah.mentormatcher.domain.users.AppRole;
import com.jonah.mentormatcher.domain.users.AppUser;
import com.jonah.mentormatcher.web.action.users.AppUserAction;

public class AuthenticatorTest extends BaseTest<AppUser> {

	AppUserAction action = new AppUserAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<AppUser> getAction() {
		return action;
	}

	@Test(dependsOnMethods = { "testRegisterAction" })
	public void validateAuthenticationBadUser() throws Exception {
		new ComponentTest() {

			protected void testComponents() throws Exception {
				Identity.instance().getCredentials().setUsername("admin");
				Identity.instance().getCredentials().setPassword("admin");
				Identity.instance().addRole("lenderContact");
				Identity.instance().addRole("lawyer");
				Identity.instance().addRole("pm");
				
				  assert invokeMethod( "#{authenticator.authenticate}")
				  .equals(true);
				 
			}

		}.run();
	}

	@Test
	public void testRegisterAction() throws Exception {
		EntityManager em = getEntityManagerFactory().createEntityManager();
		em.getTransaction().begin();

		Query query = em
				.createQuery("Select u From AppUser u where u.userName = ?1 ");
		query.setParameter(1, "admin");
		if (!query.getResultList().isEmpty())
			return;

		new ComponentTest() {

			protected void testComponents() throws Exception {
				Identity.instance().getCredentials().setUsername("lender");
				Identity.instance().getCredentials().setPassword("lender");

				createUserAndRole( "admin", "admin", "admin");
				createUserAndRole( "jim", "jim", "support");
				createUserAndRole( "roger", "roger", "lawyer");
				createUserAndRole( "erica", "erica", "lenderContact");
			}
			
		}.run();

		// em.getTransaction().commit();
		em.close();
	}
	
	private AppUser createUserAndRole(String username, String password, String role) {
		EntityManager em = getEntityManagerFactory().createEntityManager();
		em.getTransaction().begin();
		
		
		AppUser user = new AppUser();
		user.setUserName(username);
		user.setPassword(password);
		
		Employee employee = new Employee();
		
		AppRole adminRole = new AppRole();
		adminRole.setName(role);
		user.getAppRoles().add(adminRole);
		user.setEmail(username + "@gmail.com");
		user.setEnabled(true);
		
		employee.setFirstName(username);
		employee.setLastName("Kirvokian");
		employee.setAppUser(user);
		
		
		em.persist(user);
		em.persist(employee);
		em.getTransaction().commit();
		return user;
	}
	


    

}