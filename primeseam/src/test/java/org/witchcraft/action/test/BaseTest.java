package  org.witchcraft.action.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.jboss.seam.mock.JUnitSeamTest;
import org.jboss.seam.security.Identity;
import org.junit.After;
import org.junit.Before;
import org.witchcraft.base.entity.BaseEntity;
import org.witchcraft.seam.action.BaseAction;

public abstract class BaseTest<T extends BaseEntity> extends JUnitSeamTest{
	
	private static final String NOMBRE_PERSISTENCE_UNIT = "appEntityManager";
	private EntityManagerFactory emf;

	protected EntityManager em;

	public EntityManagerFactory getEntityManagerFactory() {
		return emf;
	}

	
	public void init() {
		emf = Persistence.createEntityManagerFactory(NOMBRE_PERSISTENCE_UNIT);
		em = getEntityManagerFactory().createEntityManager();
	}
	
	@Before
	public void beginTx() {
		try {
			em.getTransaction().begin();
		} catch (Exception e) {
			//e.printStackTrace();
		}
		super.begin();
	}

	@After
	public void endTx() {
		try {
			if(em.getTransaction() != null && em.getTransaction().isActive())
				em.getTransaction().rollback();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	abstract public BaseAction<T> getAction();

	@After
	public void destroy() {
	//	em.getTransaction().commit();
		em.close();
		emf.close();
	}
	
	public void login(final String username, final String pwd){
		try {
			new ComponentTest() {
				protected void testComponents() throws Exception {
					Identity.instance().getCredentials().setUsername(username);
					Identity.instance().getCredentials().setPassword(pwd);
					
					  assert invokeMethod( "#{authenticator.authenticate}")
					  .equals(true);
				}

			}.run();
		} catch (Exception e) {
	
		}
	}

}
