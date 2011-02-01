package com.nas.recovery.web.action.domain;

import com.oreon.tapovan.domain.GradeSubject;

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

import com.oreon.tapovan.domain.CourseDocuments;

public abstract class GradeSubjectActionBase extends BaseAction<GradeSubject>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private GradeSubject gradeSubject;

	@In(create = true, value = "subjectAction")
	com.nas.recovery.web.action.domain.SubjectAction subjectAction;

	@In(create = true, value = "employeeAction")
	com.nas.recovery.web.action.domain.EmployeeAction employeeAction;

	@In(create = true, value = "gradeAction")
	com.nas.recovery.web.action.domain.GradeAction gradeAction;

	@DataModel
	private List<GradeSubject> gradeSubjectRecordList;

	public void setGradeSubjectId(Long id) {
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
	public void setGradeSubjectIdForModalDlg(Long id) {
		setId(id);
		clearLists();
		loadAssociations();
	}

	public void setSubjectId(Long id) {

		if (id != null && id > 0)
			getInstance().setSubject(subjectAction.loadFromId(id));

	}

	public Long getSubjectId() {
		if (getInstance().getSubject() != null)
			return getInstance().getSubject().getId();
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

	public void setGradeId(Long id) {

		if (id != null && id > 0)
			getInstance().setGrade(gradeAction.loadFromId(id));

	}

	public Long getGradeId() {
		if (getInstance().getGrade() != null)
			return getInstance().getGrade().getId();
		return 0L;
	}

	public Long getGradeSubjectId() {
		return (Long) getId();
	}

	public GradeSubject getEntity() {
		return gradeSubject;
	}

	//@Override
	public void setEntity(GradeSubject t) {
		this.gradeSubject = t;
		loadAssociations();
	}

	public GradeSubject getGradeSubject() {
		return (GradeSubject) getInstance();
	}

	@Override
	protected GradeSubject createInstance() {
		return new GradeSubject();
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();

		com.oreon.tapovan.domain.Subject subject = subjectAction
				.getDefinedInstance();
		if (subject != null && isNew()) {
			getInstance().setSubject(subject);
		}

		com.oreon.tapovan.domain.Employee employee = employeeAction
				.getDefinedInstance();
		if (employee != null && isNew()) {
			getInstance().setEmployee(employee);
		}

		com.oreon.tapovan.domain.Grade grade = gradeAction.getDefinedInstance();
		if (grade != null && isNew()) {
			getInstance().setGrade(grade);
		}

	}

	public boolean isWired() {
		return true;
	}

	public GradeSubject getDefinedInstance() {
		return (GradeSubject) (isIdDefined() ? getInstance() : null);
	}

	public void setGradeSubject(GradeSubject t) {
		this.gradeSubject = t;
		loadAssociations();
	}

	@Override
	public Class<GradeSubject> getEntityClass() {
		return GradeSubject.class;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	@Override
	public void addAssociations(Criteria criteria) {

		if (gradeSubject.getSubject() != null) {
			criteria = criteria.add(Restrictions.eq("subject.id", gradeSubject
					.getSubject().getId()));
		}

		if (gradeSubject.getEmployee() != null) {
			criteria = criteria.add(Restrictions.eq("employee.id", gradeSubject
					.getEmployee().getId()));
		}

		if (gradeSubject.getGrade() != null) {
			criteria = criteria.add(Restrictions.eq("grade.id", gradeSubject
					.getGrade().getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (gradeSubject.getSubject() != null) {
			subjectAction.setInstance(getInstance().getSubject());
		}

		if (gradeSubject.getEmployee() != null) {
			employeeAction.setInstance(getInstance().getEmployee());
		}

		if (gradeSubject.getGrade() != null) {
			gradeAction.setInstance(getInstance().getGrade());
		}

		initListCourseDocumentses();

	}

	public void updateAssociations() {

	}

	protected List<com.oreon.tapovan.domain.CourseDocuments> listCourseDocumentses = new ArrayList<com.oreon.tapovan.domain.CourseDocuments>();

	void initListCourseDocumentses() {

		if (listCourseDocumentses.isEmpty())
			listCourseDocumentses.addAll(getInstance().getCourseDocumentses());

	}

	public List<com.oreon.tapovan.domain.CourseDocuments> getListCourseDocumentses() {

		prePopulateListCourseDocumentses();
		return listCourseDocumentses;
	}

	public void prePopulateListCourseDocumentses() {
	}

	public void setListCourseDocumentses(
			List<com.oreon.tapovan.domain.CourseDocuments> listCourseDocumentses) {
		this.listCourseDocumentses = listCourseDocumentses;
	}

	public void deleteCourseDocumentses(int index) {
		listCourseDocumentses.remove(index);
	}

	@Begin(join = true)
	public void addCourseDocumentses() {
		initListCourseDocumentses();
		CourseDocuments courseDocumentses = new CourseDocuments();

		courseDocumentses.setGradeSubject(getInstance());

		getListCourseDocumentses().add(courseDocumentses);
	}

	public void updateComposedAssociations() {

		if (listCourseDocumentses != null) {
			getInstance().getCourseDocumentses().clear();
			getInstance().getCourseDocumentses().addAll(listCourseDocumentses);
		}
	}

	public void clearLists() {
		listCourseDocumentses.clear();

	}

}
