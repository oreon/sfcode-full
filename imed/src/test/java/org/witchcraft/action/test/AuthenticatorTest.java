package org.witchcraft.action.test;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.security.Identity;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.witchcraft.users.Role;
import org.witchcraft.users.User;
import org.witchcraft.users.action.UserAction;

public class AuthenticatorTest extends BaseTest {
	
	UserAction action = new UserAction();
	
	@BeforeClass
	public void init(){
		super.init();
		EntityManager em = getEntityManagerFactory().createEntityManager();
		action.setEntityManager(em);
	}

    @Test(dependsOnMethods={"testRegisterAction"})
    public void validateAuthenticationBadUser() throws Exception {
        new ComponentTest() {
            
            protected void testComponents() throws Exception {
                Identity.instance().getCredentials().setUsername("admin");
                Identity.instance().getCredentials().setPassword("admin");
                assert invokeMethod(
                        "#{authenticator.authenticate}")
                        .equals(true);
            }
            
        }.run();
    }
    
    
    @Test
	public void testRegisterAction() {
		EntityManager em = getEntityManagerFactory().createEntityManager();
		em.getTransaction().begin();
		
		Query query = em.createQuery("Select u From User u where u.userName = ?1 ");
		query.setParameter(1, "admin");
		if(!query.getResultList().isEmpty())
			return;

		User admin = new User();
		admin.setUserName("admin");
		admin.setPassword("admin");
		
		Role adminRole = new Role();
		adminRole.setName("admin");
		
		admin.getRoles().add(adminRole);
		
		User gavin = new User();
		gavin.setUserName("gavin");
		gavin.setPassword("gavin");

		
		action.setEntity(admin);

		assert "save".equals(action.save());

		em.getTransaction().commit();
		em.close();
	}
    
}