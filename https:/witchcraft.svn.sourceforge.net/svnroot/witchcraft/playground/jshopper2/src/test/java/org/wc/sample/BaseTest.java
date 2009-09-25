package  org.wc.sample;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.search.jpa.Search;
import org.jboss.seam.mock.SeamTest;
import org.testng.annotations.AfterClass;
import org.witchcraft.base.entity.BusinessEntity;
import org.witchcraft.seam.action.BaseAction;

public class BaseTest<T extends BusinessEntity> extends SeamTest{
	
	private EntityManagerFactory emf;

	public EntityManagerFactory getEntityManagerFactory() {
		return emf;
	}

	
	public void init() {
		emf = Persistence.createEntityManagerFactory("jshopper");
		EntityManager em =  getEntityManagerFactory().createEntityManager();
		getAction().setEntityManager(Search.getFullTextEntityManager(em));
	}
	
	public BaseAction<T> getAction(){
		return null;
	}

	@AfterClass
	public void destroy() {
		emf.close();
	}

}
