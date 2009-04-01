package  org.witchcraft.action.test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.jboss.seam.mock.SeamTest;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTest extends SeamTest{
	
	private EntityManagerFactory emf;

	public EntityManagerFactory getEntityManagerFactory() {
		return emf;
	}

	
	public void init() {
		emf = Persistence.createEntityManagerFactory("imed");
	}

	@AfterClass
	public void destroy() {
		emf.close();
	}

}
