package com.oreon.smartsis.web.action.domain;

import com.oreon.smartsis.domain.Student;

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

import com.oreon.smartsis.domain.StudentVitalInfo;

public abstract class StudentActionBase
		extends
			com.oreon.smartsis.web.action.domain.PersonAction<Student>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private Student student;

	@In(create = true, value = "gradeAction")
	com.oreon.smartsis.web.action.domain.GradeAction gradeAction;

	@In(create = true, value = "parentAction")
	com.oreon.smartsis.web.action.domain.ParentAction parentAction;

	@In(create = true, value = "parentAction")
	com.oreon.smartsis.web.action.domain.ParentAction secondaryAction;

	@DataModel
	private List<Student> studentRecordList;

	public void setStudentId(Long id) {
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
	public void setStudentIdForModalDlg(Long id) {
		setId(id);
		clearLists();
		loadAssociations();
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

	public void setParentId(Long id) {

		if (id != null && id > 0)
			getInstance().setParent(parentAction.loadFromId(id));

	}

	public Long getParentId() {
		if (getInstance().getParent() != null)
			return getInstance().getParent().getId();
		return 0L;
	}

	public void setSecondaryId(Long id) {

		if (id != null && id > 0)
			getInstance().setSecondary(secondaryAction.loadFromId(id));

	}

	public Long getSecondaryId() {
		if (getInstance().getSecondary() != null)
			return getInstance().getSecondary().getId();
		return 0L;
	}

	public Long getStudentId() {
		return (Long) getId();
	}

	public Student getEntity() {
		return student;
	}

	//@Override
	public void setEntity(Student t) {
		this.student = t;
		loadAssociations();
	}

	public Student getStudent() {
		return (Student) getInstance();
	}

	@Override
	protected Student createInstance() {
		return new Student();
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();

		com.oreon.smartsis.domain.Grade grade = gradeAction
				.getDefinedInstance();
		if (grade != null && isNew()) {
			getInstance().setGrade(grade);
		}

		com.oreon.smartsis.domain.Parent parent = parentAction
				.getDefinedInstance();
		if (parent != null && isNew()) {
			getInstance().setParent(parent);
		}

		com.oreon.smartsis.domain.Parent secondary = secondaryAction
				.getDefinedInstance();
		if (secondary != null && isNew()) {
			getInstance().setSecondary(secondary);
		}

	}

	public boolean isWired() {
		return true;
	}

	public Student getDefinedInstance() {
		return (Student) (isIdDefined() ? getInstance() : null);
	}

	public void setStudent(Student t) {
		this.student = t;
		if (student != null)
			setStudentId(t.getId());
		loadAssociations();
	}

	@Override
	public Class<Student> getEntityClass() {
		return Student.class;
	}

	public String downloadPicture(Long id) {
		if (id == null || id == 0)
			id = currentEntityId;
		setId(id);
		downloadAttachment(getInstance().getPicture());
		return "success";
	}

	public void pictureUploadListener(UploadEvent event) throws Exception {
		UploadItem uploadItem = event.getUploadItem();
		if (getInstance().getPicture() == null)
			getInstance().setPicture(new FileAttachment());
		getInstance().getPicture().setName(uploadItem.getFileName());
		getInstance().getPicture().setContentType(uploadItem.getContentType());
		getInstance().getPicture().setData(
				FileUtils.readFileToByteArray(uploadItem.getFile()));
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	@Override
	public void addAssociations(Criteria criteria) {

		if (student.getGrade() != null) {
			criteria = criteria.add(Restrictions.eq("grade.id", student
					.getGrade().getId()));
		}

		if (student.getParent() != null) {
			criteria = criteria.add(Restrictions.eq("parent.id", student
					.getParent().getId()));
		}

		if (student.getSecondary() != null) {
			criteria = criteria.add(Restrictions.eq("secondary.id", student
					.getSecondary().getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (student.getGrade() != null) {
			gradeAction.setInstance(getInstance().getGrade());
		}

		if (student.getParent() != null) {
			parentAction.setInstance(getInstance().getParent());
		}

		if (student.getSecondary() != null) {
			secondaryAction.setInstance(getInstance().getSecondary());
		}

		initListStudentVitalInfos();

	}

	public void updateAssociations() {

	}

	protected List<com.oreon.smartsis.domain.StudentVitalInfo> listStudentVitalInfos = new ArrayList<com.oreon.smartsis.domain.StudentVitalInfo>();

	void initListStudentVitalInfos() {

		if (listStudentVitalInfos.isEmpty())
			listStudentVitalInfos.addAll(getInstance().getStudentVitalInfos());

	}

	public List<com.oreon.smartsis.domain.StudentVitalInfo> getListStudentVitalInfos() {

		prePopulateListStudentVitalInfos();
		return listStudentVitalInfos;
	}

	public void prePopulateListStudentVitalInfos() {
	}

	public void setListStudentVitalInfos(
			List<com.oreon.smartsis.domain.StudentVitalInfo> listStudentVitalInfos) {
		this.listStudentVitalInfos = listStudentVitalInfos;
	}

	public void deleteStudentVitalInfos(int index) {
		listStudentVitalInfos.remove(index);
	}

	@Begin(join = true)
	public void addStudentVitalInfos() {
		initListStudentVitalInfos();
		StudentVitalInfo studentVitalInfos = new StudentVitalInfo();

		studentVitalInfos.setStudent(getInstance());

		getListStudentVitalInfos().add(studentVitalInfos);
	}

	public void updateComposedAssociations() {

		if (listStudentVitalInfos != null) {
			getInstance().getStudentVitalInfos().clear();
			getInstance().getStudentVitalInfos().addAll(listStudentVitalInfos);
		}
	}

	public void clearLists() {
		listStudentVitalInfos.clear();

	}

	public List eExamsForStudent() {

		return executeNamedQuery("eExamsForStudent");

	}

}
