package com.wc.shopper.view;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.wc.shopper.domain.Department;

public abstract class DepartmentActionBase extends BaseAction<Department>
		implements
			java.io.Serializable {

	protected Predicate[] getSearchPredicates(Root<Department> root) {

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		List<Predicate> predicatesList = new ArrayList<Predicate>();

		String name = search.getName();
		if (name != null && !"".equals(name)) {
			predicatesList.add(builder.like(root.<String> get("name"),
					'%' + name + '%'));
		}
		/*
		int stock = search.getStock();
		if (stock != 0) {
			predicatesList.add(builder.equal(root.get("stock"), stock));
		}*/

		return predicatesList.toArray(new Predicate[predicatesList.size()]);
	}

	@Override
	protected Class<Department> getEntityClass() {
		return Department.class;
	}

	public Department createInstance() {
		return new Department();
	}

	public Department getDepartment() {
		return this.entity;
	}

}
