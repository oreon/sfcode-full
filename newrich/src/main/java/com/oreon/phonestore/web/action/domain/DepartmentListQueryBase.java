package com.oreon.phonestore.web.action.domain;

import com.oreon.phonestore.domain.Department;

import org.witchcraft.seam.action.BaseAction;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.framework.EntityQuery;
import org.witchcraft.base.entity.BaseQuery;
import org.witchcraft.base.entity.Range;
import org.witchcraft.base.entity.EntityQueryDataModel;

import org.jboss.seam.annotations.Observer;

import java.math.BigDecimal;
import javax.faces.model.DataModel;

import org.jboss.seam.annotations.security.Restrict;

import org.jboss.seam.annotations.In;
import org.jboss.seam.Component;

import com.oreon.phonestore.domain.Department;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class DepartmentListQueryBase
		extends
			BaseQuery<Department, Long> {

	private static final String EJBQL = "select department from Department department";

	protected Department department = new Department();

	@In(create = true)
	DepartmentAction departmentAction;

	DepartmentDataModel departmentDataModel;

	public DepartmentListQueryBase() {
		super();
		setOrderColumn("id");
		setOrderDirection("desc");
	}

	protected static final class DepartmentDataModel
			extends
				EntityQueryDataModel<Department, Long> {

		public DepartmentDataModel(DepartmentListQuery departmentListQuery) {
			super(departmentListQuery, Department.class);
		}

		@Override
		protected Long getId(Department item) {
			// TODO Auto-generated method stub
			return item.getId();
		}
	}

	@Override
	public DataModel<Department> getDataModel() {

		if (departmentDataModel == null) {
			departmentDataModel = new DepartmentDataModel(
					(DepartmentListQuery) Component
							.getInstance("departmentList"));
		}
		return departmentDataModel;
	}

	public Department getDepartment() {
		return department;
	}

	@Override
	public Department getInstance() {
		return getDepartment();
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	//@Restrict("#{s:hasPermission('department', 'view')}")
	public List<Department> getResultList() {
		return super.getResultList();
	}

	@Override
	public Class<Department> getEntityClass() {
		return Department.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"department.id = #{departmentList.department.id}",

			"department.archived = #{departmentList.department.archived}",

			"lower(department.name) like concat(lower(#{departmentList.department.name}),'%')",

			"department.dateCreated <= #{departmentList.dateCreatedRange.end}",
			"department.dateCreated >= #{departmentList.dateCreatedRange.begin}",};

	@Observer("archivedDepartment")
	public void onArchive() {
		refresh();
	}

	//@Restrict("#{s:hasPermission('department', 'delete')}")
	public void archiveById(Long id) {
		departmentAction.archiveById(id);
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, Department e) {

		builder.append("\""
				+ (e.getName() != null ? e.getName().replace(",", "") : "")
				+ "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("Name" + ",");

		builder.append("\r\n");
	}
}
