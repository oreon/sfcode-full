package com.wc.shopper.view;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.wc.shopper.domain.Car;
import com.wc.shopper.domain.Employee;

public abstract class EmployeeActionBase extends PersonAction<Employee> implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5867093559332541564L;
	private LazyDataModel<Employee> model;

	public LazyDataModel<Employee> getModel() {
		return model;
	}

	protected Predicate[] getSearchPredicates( Root<Employee> root ) {

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		List<Predicate> predicatesList = new ArrayList<Predicate>();

		String name = search.getFirstName();
		if ( name != null && !"".equals( name ) ) {
			predicatesList.add( builder.like( root.<String> get( "name" ), '%' + name + '%' ) );
		}
		/*
		 * int stock = search.getStock(); if (stock != 0) { predicatesList.add(builder.equal(root.get("stock"), stock)); }
		 */

		return predicatesList.toArray( new Predicate[predicatesList.size()] );
	}

	@Override
	protected Class<Employee> getEntityClass() {
		return Employee.class;
	}

	public Employee createInstance() {
		return new Employee();
	}

	public Employee getEmployee() {
		return this.entity;
	}

	protected class EmployeeLazyDataModel extends LazyDataModel<Employee> {
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public List<Employee> load( int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,String> filters ) {
			//List<Employee> result = null;

			Session s = (Session) entityManager.getDelegate();

	 		Criteria crit = s.createCriteria(Car.class);

	 		if (sortField != null && !sortField.isEmpty()) {
	 			if (sortOrder == SortOrder.ASCENDING) {
	 				crit = crit.addOrder(Order.asc(sortField));
	 			} else {
	 				crit = crit.addOrder(Order.desc(sortField));
	 			}
	 		}

	 		if (!filters.isEmpty()) {
	 			Iterator<Entry<String, String>> iterator = filters.entrySet()
	 					.iterator();
	 			while (iterator.hasNext()) {
	 				Entry<String, String> entry = iterator.next();
	 				crit = crit.add(Restrictions.like(entry.getKey(),
	 						entry.getValue(), MatchMode.START));
	 			}
	 		}

	 		crit = crit.setFirstResult(first).setMaxResults(pageSize);

			model.setRowCount(  safeLongToInt (getCount() )  );
			return crit.list();
		}
		
		
		public Long getCount() {
	 		Session s = (Session) entityManager.getDelegate();

	 		Criteria crit = s.createCriteria(Employee.class).setProjection(
	 				Projections.rowCount());

	 		return (Long) crit.list().get(0);
	 	}

		@Override
		public Object getRowKey( Employee Employee ) {
			return Employee.getId();
		}

		@Override
		public Employee getRowData( String rowKey ) {
			Employee employee = null;
			employee = entityManager.find( getEntityClass(), Long.valueOf( rowKey ) );
			return employee;
		}
		
		public int safeLongToInt(long l) {
		    int i = (int)l;
		    if ((long)i != l) {
		        throw new IllegalArgumentException(l + " cannot be cast to int without changing its value.");
		    }
		    return i;
		}

	}
	
	

	@PostConstruct
	public void init() {
		model = new EmployeeLazyDataModel();
	}

}