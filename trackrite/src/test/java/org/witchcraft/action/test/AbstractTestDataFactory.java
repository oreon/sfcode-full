package org.witchcraft.action.test;

import javax.faces.FactoryFinder;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.exception.ConstraintViolationException;
import org.jboss.seam.Seam;
import org.jboss.seam.contexts.ServletLifecycle;
import org.jboss.seam.core.Init;
import org.jboss.seam.init.Initialization;
import org.jboss.seam.mock.MockApplicationFactory;
import org.jboss.seam.mock.MockServletContext;

public class AbstractTestDataFactory<T> {

	private static final String NOMBRE_PERSISTENCE_UNIT = "appEntityManager";
	protected EntityManagerFactory emf;
	protected EntityManager em;

	public EntityManagerFactory getEntityManagerFactory() {
		return emf;
	}

	public void init() {
		emf = Persistence.createEntityManagerFactory(NOMBRE_PERSISTENCE_UNIT);
		em = getEntityManagerFactory().createEntityManager();

	}

	void create() {
		MockServletContext servletContext = new MockServletContext();
		ServletLifecycle.beginApplication(servletContext);
		FactoryFinder.setFactory(FactoryFinder.APPLICATION_FACTORY,
				MockApplicationFactory.class.getName());
		new Initialization(servletContext).create().init();
		((Init) servletContext.getAttribute(Seam.getComponentName(Init.class)))
				.setDebug(false);
	}

	protected void persist(T t) {
		try {
			em.getTransaction().begin();
			em.persist(t);
			em.getTransaction().commit();
		} catch (ConstraintViolationException cve) {
			System.out.println("Const Violation:" + cve.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			em.getTransaction().rollback();
		}
	}
}
