package facades;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.hibernate.Session;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.witchcraft.model.support.springbeanhelpers.BeanHelper;

import com.oreon.kgauge.domain.Exam;

public class IndexCreator {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		EntityManagerFactory emf =  (EntityManagerFactory) BeanHelper.getBean("entityManagerFactory");
		EntityManager em = emf.createEntityManager();
		//entityManagerFactory.createEntityManager();
		FullTextEntityManager fullTextEntityManager = Search.createFullTextEntityManager(em);
		List<Exam> exams = em.createQuery("select exam from Exam as exam").getResultList();
		for (Exam exam : exams) {
		    fullTextEntityManager.index(exam);
		} 

	}

}
