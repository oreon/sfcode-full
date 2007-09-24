package bizobjects.dao.impl;

import bizobjects.Customer;

import bizobjects.dao.CustomerDao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.jpa.JpaCallback;
import org.springframework.orm.jpa.support.JpaDaoSupport;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

public class CustomerDaoImpl extends JpaDaoSupport implements CustomerDao {
	private HibernateTemplate hibernateTemplate;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}

	/**
	 * This method saves or updates the given entity based upon whether the id
	 * is null
	 */
	public Customer save(Customer customer) {
		if (customer.getId() == null) {
			getJpaTemplate().persist(customer);
		} else {
			getJpaTemplate().merge(customer);
		}

		return customer;
	}

	public void delete(Customer customer) {
		getJpaTemplate().remove(customer);
	}

	public Customer load(Long id) {
		return getJpaTemplate().find(Customer.class, id);
	}

	/*
	 * loads all records for this entity
	 */
	public List<Customer> loadAll() {
		return getJpaTemplate().find("select customer from Customer customer");
	}

	// // FINDERS /////
	public List<Customer> findByfirstName(Object firstName) {
		return getJpaTemplate().find(
				"select c from Customer c where c.firstName = ?1", firstName);
	}

	public List<Customer> findBylastName(Object lastName) {
		return getJpaTemplate().find(
				"select c from Customer c where c.lastName = ?1", lastName);
	}

	public List<Customer> searchByExample(final Customer customer) {

		return getJpaTemplate().executeFind(new JpaCallback() {

			public Object doInJpa(EntityManager em) throws PersistenceException {
				Session session = (Session) em.getDelegate();
	
				Criteria criteria = session.createCriteria(Customer.class).add(
						Example.create(customer).enableLike(MatchMode.START)
								.ignoreCase().excludeZeroes().excludeProperty(
										"dateModified").excludeProperty("id")
								.excludeProperty("dateCreated"));
				return criteria.list();
			}
		});
	}

}
