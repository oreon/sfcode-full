package com.wc.shopper.view;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.CreateException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.primefaces.event.SelectEvent;

import com.wc.shopper.domain.Employee;

public abstract class EmployeeActionBase extends PersonAction<Employee> implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5867093559332541564L;
	
	

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
		
		if(entity == null)
			entity = createInstance();
		
		return this.entity;
	}


	public void setEmployee(Employee employee) {
		this.entity = employee;
	}

	
	
	public void onRowSelect(SelectEvent selectEvent){
		//conversation.begin();
		//entity = (Employee)selectEvent.getObject();
		
	}
}