package com.nas.recovery.web.action.domain;

import com.oreon.tapovan.domain.CourseDocuments;

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

public abstract class CourseDocumentsActionBase
		extends
			BaseAction<CourseDocuments> implements java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private CourseDocuments courseDocuments;

	@In(create = true, value = "gradeSubjectAction")
	com.nas.recovery.web.action.domain.GradeSubjectAction gradeSubjectAction;

	@DataModel
	private List<CourseDocuments> courseDocumentsRecordList;

	public void setCourseDocumentsId(Long id) {
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
	public void setCourseDocumentsIdForModalDlg(Long id) {
		setId(id);
		clearLists();
		loadAssociations();
	}

	public void setGradeSubjectId(Long id) {

		if (id != null && id > 0)
			getInstance().setGradeSubject(gradeSubjectAction.loadFromId(id));

	}

	public Long getGradeSubjectId() {
		if (getInstance().getGradeSubject() != null)
			return getInstance().getGradeSubject().getId();
		return 0L;
	}

	public Long getCourseDocumentsId() {
		return (Long) getId();
	}

	public CourseDocuments getEntity() {
		return courseDocuments;
	}

	//@Override
	public void setEntity(CourseDocuments t) {
		this.courseDocuments = t;
		loadAssociations();
	}

	public CourseDocuments getCourseDocuments() {
		return (CourseDocuments) getInstance();
	}

	@Override
	protected CourseDocuments createInstance() {
		return new CourseDocuments();
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();

		com.oreon.tapovan.domain.GradeSubject gradeSubject = gradeSubjectAction
				.getDefinedInstance();
		if (gradeSubject != null && isNew()) {
			getInstance().setGradeSubject(gradeSubject);
		}

	}

	public boolean isWired() {
		return true;
	}

	public CourseDocuments getDefinedInstance() {
		return (CourseDocuments) (isIdDefined() ? getInstance() : null);
	}

	public void setCourseDocuments(CourseDocuments t) {
		this.courseDocuments = t;
		loadAssociations();
	}

	@Override
	public Class<CourseDocuments> getEntityClass() {
		return CourseDocuments.class;
	}

	public String downloadDocument(Long id) {
		if (id == null || id == 0)
			id = currentEntityId;
		setId(id);
		downloadAttachment(getInstance().getDocument());
		return "success";
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	@Override
	public void addAssociations(Criteria criteria) {

		if (courseDocuments.getGradeSubject() != null) {
			criteria = criteria.add(Restrictions.eq("gradeSubject.id",
					courseDocuments.getGradeSubject().getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (courseDocuments.getGradeSubject() != null) {
			gradeSubjectAction.setInstance(getInstance().getGradeSubject());
		}

	}

	public void updateAssociations() {

	}

	public void updateComposedAssociations() {
	}

	public void clearLists() {

	}

}
