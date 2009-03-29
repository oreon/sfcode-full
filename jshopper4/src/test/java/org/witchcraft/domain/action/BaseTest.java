package  org.witchcraft.domain.action;

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

	@BeforeClass
	public void init() {
		emf = Persistence
				.createEntityManagerFactory("jshopper4");
	}

	@AfterClass
	public void destroy() {
		emf.close();
	}

}
