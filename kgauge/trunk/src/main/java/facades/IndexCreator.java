package facades;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.witchcraft.model.support.springbeanhelpers.BeanHelper;

import com.oreon.kgauge.domain.Exam;

public class IndexCreator {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		EntityManagerFactory emf = (EntityManagerFactory) BeanHelper
				.getBean("entityManagerFactory");
		EntityManager em = emf.createEntityManager();
		// entityManagerFactory.createEntityManager();
		FullTextEntityManager fullTextEntityManager = Search
				.createFullTextEntityManager(em);
		createIndex(em, fullTextEntityManager);

		textSearch(fullTextEntityManager);
		
	}

	private static void createIndex(EntityManager em,
			FullTextEntityManager fullTextEntityManager) {
		List<Exam> exams = em.createQuery("select exam from Exam as exam")
				.getResultList();
		for (Exam exam : exams) {
			fullTextEntityManager.index(exam);
		}
	}

	private static void textSearch(
			FullTextEntityManager fullTextEntityManager) {
		MultiFieldQueryParser parser = new MultiFieldQueryParser(new String[] {
				"name", "description", "number" }, new StandardAnalyzer());
		Query query = null;
		try {
			query = parser.parse("java");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 org.hibernate.search.jpa.FullTextQuery ftq =  fullTextEntityManager
				.createFullTextQuery(query, Exam.class);
		List<Exam> result = ftq.getResultList();
		
		System.out.println(result.size());
	}

}
