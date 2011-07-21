package com.oreon.smartsis.web.action.fees;

import com.oreon.smartsis.fees.StudentPaidFee;

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

public abstract class StudentPaidFeeActionBase
		extends
			BaseAction<StudentPaidFee> implements java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private StudentPaidFee studentPaidFee;

	@In(create = true, value = "studentAction")
	com.oreon.smartsis.web.action.domain.StudentAction studentAction;

	@In(create = true, value = "gradeAction")
	com.oreon.smartsis.web.action.domain.GradeAction gradeAction;

	@DataModel
	private List<StudentPaidFee> studentPaidFeeRecordList;

	public void setStudentPaidFeeId(Long id) {
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
	public void setStudentPaidFeeIdForModalDlg(Long id) {
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

	public void setGradeId(Long id) {

		if (id != null && id > 0)
			getInstance().setGrade(gradeAction.loadFromId(id));

	}

	public Long getGradeId() {
		if (getInstance().getGrade() != null)
			return getInstance().getGrade().getId();
		return 0L;
	}

	public Long getStudentPaidFeeId() {
		return (Long) getId();
	}

	public StudentPaidFee getEntity() {
		return studentPaidFee;
	}

	//@Override
	public void setEntity(StudentPaidFee t) {
		this.studentPaidFee = t;
		loadAssociations();
	}

	public StudentPaidFee getStudentPaidFee() {
		return (StudentPaidFee) getInstance();
	}

	@Override
	protected StudentPaidFee createInstance() {
		return new StudentPaidFee();
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

		com.oreon.smartsis.domain.Grade grade = gradeAction
				.getDefinedInstance();
		if (grade != null && isNew()) {
			getInstance().setGrade(grade);
		}

	}

	public boolean isWired() {
		return true;
	}

	public StudentPaidFee getDefinedInstance() {
		return (StudentPaidFee) (isIdDefined() ? getInstance() : null);
	}

	public void setStudentPaidFee(StudentPaidFee t) {
		this.studentPaidFee = t;
		if (studentPaidFee != null)
			setStudentPaidFeeId(t.getId());
		loadAssociations();
	}

	@Override
	public Class<StudentPaidFee> getEntityClass() {
		return StudentPaidFee.class;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	@Override
	public void addAssociations(Criteria criteria) {

		if (studentPaidFee.getStudent() != null) {
			criteria = criteria.add(Restrictions.eq("student.id",
					studentPaidFee.getStudent().getId()));
		}

		if (studentPaidFee.getGrade() != null) {
			criteria = criteria.add(Restrictions.eq("grade.id", studentPaidFee
					.getGrade().getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (studentPaidFee.getStudent() != null) {
			studentAction.setInstance(getInstance().getStudent());
		}

		if (studentPaidFee.getGrade() != null) {
			gradeAction.setInstance(getInstance().getGrade());
		}

	}

	public void updateAssociations() {

	}

	public void updateComposedAssociations() {
	}

	public void clearLists() {

	}

}
