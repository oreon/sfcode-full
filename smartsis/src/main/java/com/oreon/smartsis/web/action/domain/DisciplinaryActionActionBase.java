package com.oreon.smartsis.web.action.domain;

import com.oreon.smartsis.domain.DisciplinaryAction;

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
import org.jboss.seam.Component;
import org.jboss.seam.security.Identity;

import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.log.Log;
import org.jboss.seam.annotations.Observer;

import org.witchcraft.base.entity.FileAttachment;

import org.apache.commons.io.FileUtils;
import org.richfaces.event.UploadEvent;
import org.richfaces.model.UploadItem;

public abstract class DisciplinaryActionActionBase
		extends
			BaseAction<DisciplinaryAction> implements java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private DisciplinaryAction disciplinaryAction;

	@In(create = true, value = "studentAction")
	com.oreon.smartsis.web.action.domain.StudentAction studentAction;

	@In(create = true, value = "employeeAction")
	com.oreon.smartsis.web.action.domain.EmployeeAction employeeAction;

	@DataModel
	private List<DisciplinaryAction> disciplinaryActionRecordList;

	public void setDisciplinaryActionId(Long id) {
		if (id == 0) {
			clearInstance();
			clearLists();
			loadAssociations();
			return;
		}
		setId(id);
		if (!isPostBack())
			loadAssociations();
	}

	/** for modal dlg we need to load associaitons regardless of postback
	 * @param id
	 */
	public void setDisciplinaryActionIdForModalDlg(Long id) {
		setId(id);
		clearLists();
		loadAssociations();
	}

	public void setStudentId(Long id) {

		if (id != null && id > 0)
			getInstance().setStudent(studentAction.loadFromId(id));

	}

	public Long getStudentId() {
		if (getInstance().getStudent() != null)
			return getInstance().getStudent().getId();
		return 0L;
	}

	public void setEmployeeId(Long id) {

		if (id != null && id > 0)
			getInstance().setEmployee(employeeAction.loadFromId(id));

	}

	public Long getEmployeeId() {
		if (getInstance().getEmployee() != null)
			return getInstance().getEmployee().getId();
		return 0L;
	}

	public Long getDisciplinaryActionId() {
		return (Long) getId();
	}

	public DisciplinaryAction getEntity() {
		return disciplinaryAction;
	}

	//@Override
	public void setEntity(DisciplinaryAction t) {
		this.disciplinaryAction = t;
		loadAssociations();
	}

	public DisciplinaryAction getDisciplinaryAction() {
		return (DisciplinaryAction) getInstance();
	}

	@Override
	protected DisciplinaryAction createInstance() {
		return new DisciplinaryAction();
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();

		com.oreon.smartsis.domain.Student student = studentAction
				.getDefinedInstance();
		if (student != null && isNew()) {
			getInstance().setStudent(student);
		}

		com.oreon.smartsis.domain.Employee employee = employeeAction
				.getDefinedInstance();
		if (employee != null && isNew()) {
			getInstance().setEmployee(employee);
		}

	}

	public boolean isWired() {
		return true;
	}

	public DisciplinaryAction getDefinedInstance() {
		return (DisciplinaryAction) (isIdDefined() ? getInstance() : null);
	}

	public void setDisciplinaryAction(DisciplinaryAction t) {
		this.disciplinaryAction = t;
		if (disciplinaryAction != null)
			setDisciplinaryActionId(t.getId());
		loadAssociations();
	}

	@Override
	public Class<DisciplinaryAction> getEntityClass() {
		return DisciplinaryAction.class;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	@Override
	public void addAssociations(Criteria criteria) {

		if (disciplinaryAction.getStudent() != null) {
			criteria = criteria.add(Restrictions.eq("student.id",
					disciplinaryAction.getStudent().getId()));
		}

		if (disciplinaryAction.getEmployee() != null) {
			criteria = criteria.add(Restrictions.eq("employee.id",
					disciplinaryAction.getEmployee().getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (disciplinaryAction.getStudent() != null) {
			studentAction.setInstance(getInstance().getStudent());
		}

		if (disciplinaryAction.getEmployee() != null) {
			employeeAction.setInstance(getInstance().getEmployee());
		}

	}

	public void updateAssociations() {

	}

	public void updateComposedAssociations() {
	}

	public void clearLists() {

	}

}
