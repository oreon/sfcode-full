package org.witchcraft.action.test;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.security.Identity;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.witchcraft.seam.action.BaseAction;
import org.witchcraft.users.Role;
import org.witchcraft.users.User;
import org.witchcraft.users.action.UserAction;


public class AuthenticatorTest extends BaseTest<User> {
	
	UserAction action = new UserAction();
	
	@BeforeClass
	public void init(){
		super.init();
	}
	
	@Override
	public BaseAction<User> getAction() {
		return action;
	}

    @Test(dependsOnMethods={"testRegisterAction"})
    public void validateAuthenticationBadUser() throws Exception {
        new ComponentTest() {
            
            protected void testComponents() throws Exception {
                Identity.instance().getCredentials().setUsername("admin");
                Identity.instance().getCredentials().setPassword("admin");
                /*
                assert invokeMethod(
                        "#{authenticator.authenticate}")
                        .equals(true);
                */
            }
            
        }.run();
    }
    
    
    @Test
	public void testRegisterAction() {
		EntityManager em = getEntityManagerFactory().createEntityManager();
		
		
		Query query = em.createQuery("Select u From User u where u.userName = ?1 ");
		query.setParameter(1, "admin");
		if(!query.getResultList().isEmpty())
			return;

		createUser(em, "admin", "admin", "admin");
		createUser(em, "jim", "jim", "support");
		createUser(em, "roger", "roger", "clerk");
		createUser(em, "erica", "erica", "manager");
		
	
		em.close();
	}

	private User createUser(EntityManager em, String username, String password, String role) {
		em.getTransaction().begin();
		User admin = new User();
		admin.setUserName(username);
		admin.setPassword(password);
		
		Role adminRole = new Role();
		adminRole.setName(role);
		admin.getRoles().add(adminRole);
		
		getAction().setEntity(admin);
		assert "save".equals(getAction().save()) : "Not return waiting result";
		em.getTransaction().commit();
		return admin;
	}
    
}