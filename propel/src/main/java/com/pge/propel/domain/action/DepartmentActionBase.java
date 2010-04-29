package com.pge.propel.domain.action;

import com.pge.propel.domain.Department;

import org.witchcraft.seam.action.BaseAction;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import org.apache.commons.lang.StringUtils;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Scope;

import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;

import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.log.Log;
import org.jboss.seam.annotations.Observer;

public class DepartmentActionBase extends BaseAction<Department>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private Department department;

	@DataModel
	private List<Department> departmentList;

	@Factory("departmentList")
	@Observer("archivedDepartment")
	public void findRecords() {
		search();
	}

	public Department getEntity() {
		return department;
	}

	@Override
	public void setEntity(Department t) {
		this.department = t;
	}

	@Override
	public void setEntityList(List<Department> list) {
		this.departmentList = list;
	}

	public void updateAssociations() {

		com.pge.propel.domain.Employee employee = (com.pge.propel.domain.Employee) org.jboss.seam.Component
				.getInstance("employee");
		employee.setDepartment(department);
		events.raiseTransactionSuccessEvent("archivedEmployee");

	}

	public List<Department> getEntityList() {
		if (departmentList == null) {
			findRecords();
		}
		return departmentList;
	}

}
