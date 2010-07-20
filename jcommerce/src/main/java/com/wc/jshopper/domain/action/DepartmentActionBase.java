package com.wc.jshopper.domain.action;

import com.wc.jshopper.domain.Department;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.witchcraft.seam.action.BaseAction;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

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
import org.jboss.seam.Component;

import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.log.Log;
import org.jboss.seam.annotations.Observer;

public class DepartmentActionBase extends BaseAction<Department> implements
		java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private Department department;

	@In(create = true, value = "employeeList")
	com.wc.jshopper.domain.action.EmployeeListQuery employeeList;

	@DataModel
	private List<Department> departmentRecordList;

	public void setDepartmentId(Long id) {

		setId(id);
		loadAssociations();
	}

	public Long getDepartmentId() {
		return (Long) getId();
	}

	@Factory("departmentRecordList")
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
		loadAssociations();
	}

	public Department getDepartment() {
		return getInstance();
	}

	@Override
	protected Department createInstance() {
		return new Department();
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();

	}

	public boolean isWired() {
		return true;
	}

	public Department getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public void setDepartment(Department t) {
		this.department = t;
		loadAssociations();
	}

	@Override
	public Class<Department> getEntityClass() {
		return Department.class;
	}

	@Override
	public void setEntityList(List<Department> list) {
		this.departmentRecordList = list;
	}

	/**
	 * This function is responsible for loading associations for the given
	 * entity e.g. when viewing an order, we load the customer so that customer
	 * can be shown on the customer tab within viewOrder.xhtml
	 * 
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		try {

			employeeList.getEmployee().setDepartment(getInstance());

		} catch (Exception e) {
			facesMessages.add(e.getMessage());
		}

	}

	public void updateAssociations() {

		com.wc.jshopper.domain.Employee employee = (com.wc.jshopper.domain.Employee) org.jboss.seam.Component
				.getInstance("employee");
		employee.setDepartment(department);
		events.raiseTransactionSuccessEvent("archivedEmployee");

	}

	public List<Department> getEntityList() {
		if (departmentRecordList == null) {
			findRecords();
		}
		return departmentRecordList;
	}
	
	public void executeReport(String reportName, Map<String, String> parameters, JRBeanCollectionDataSource dataSource){
		try{
			byte[] bytes = JasperRunManager.runReportToPdf(this.getClass()
					.getResourceAsStream("departmentReport.jasper"),
					parameters, dataSource);
			HttpServletResponse response = (HttpServletResponse) javax.faces.context.FacesContext
					.getCurrentInstance().getExternalContext().getResponse();
			response.setContentType("application/pdf");
			response.addHeader("Content-Disposition",
					"attachment;filename=Report.pdf");
			response.setContentLength(bytes.length);
			ServletOutputStream servletOutputStream = response
					.getOutputStream();
			servletOutputStream.write(bytes, 0, bytes.length);
			servletOutputStream.flush();
			servletOutputStream.close();
			javax.faces.context.FacesContext.getCurrentInstance()
					.responseComplete();
		} catch (JRException jre) {
			jre.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void runReport() {		
		try {
			Map<String, String> parameters = new HashMap<String, String>();
			parameters.put("Title", "Department Report");
			executeReport("departmentReport.jasper", parameters, new JRBeanCollectionDataSource(
					getEntityList()));
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

}
