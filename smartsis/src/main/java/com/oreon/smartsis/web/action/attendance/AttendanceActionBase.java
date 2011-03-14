package com.oreon.smartsis.web.action.attendance;

import com.oreon.smartsis.attendance.Attendance;

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

public abstract class AttendanceActionBase extends BaseAction<Attendance>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private Attendance attendance;

	@In(create = true, value = "studentAction")
	com.oreon.smartsis.web.action.domain.StudentAction studentAction;

	@In(create = true, value = "gradeSubjectAction")
	com.oreon.smartsis.web.action.domain.GradeSubjectAction gradeSubjectAction;

	@In(create = true, value = "gradeAttendanceAction")
	com.oreon.smartsis.web.action.attendance.GradeAttendanceAction gradeAttendanceAction;

	@DataModel
	private List<Attendance> attendanceRecordList;

	public void setAttendanceId(Long id) {
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
	public void setAttendanceIdForModalDlg(Long id) {
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

	public void setGradeSubjectId(Long id) {

		if (id != null && id > 0)
			getInstance().setGradeSubject(gradeSubjectAction.loadFromId(id));

	}

	public Long getGradeSubjectId() {
		if (getInstance().getGradeSubject() != null)
			return getInstance().getGradeSubject().getId();
		return 0L;
	}

	public void setGradeAttendanceId(Long id) {

		if (id != null && id > 0)
			getInstance().setGradeAttendance(
					gradeAttendanceAction.loadFromId(id));

	}

	public Long getGradeAttendanceId() {
		if (getInstance().getGradeAttendance() != null)
			return getInstance().getGradeAttendance().getId();
		return 0L;
	}

	public Long getAttendanceId() {
		return (Long) getId();
	}

	public Attendance getEntity() {
		return attendance;
	}

	//@Override
	public void setEntity(Attendance t) {
		this.attendance = t;
		loadAssociations();
	}

	public Attendance getAttendance() {
		return (Attendance) getInstance();
	}

	@Override
	protected Attendance createInstance() {
		return new Attendance();
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

		com.oreon.smartsis.domain.GradeSubject gradeSubject = gradeSubjectAction
				.getDefinedInstance();
		if (gradeSubject != null && isNew()) {
			getInstance().setGradeSubject(gradeSubject);
		}

		com.oreon.smartsis.attendance.GradeAttendance gradeAttendance = gradeAttendanceAction
				.getDefinedInstance();
		if (gradeAttendance != null && isNew()) {
			getInstance().setGradeAttendance(gradeAttendance);
		}

	}

	public boolean isWired() {
		return true;
	}

	public Attendance getDefinedInstance() {
		return (Attendance) (isIdDefined() ? getInstance() : null);
	}

	public void setAttendance(Attendance t) {
		this.attendance = t;
		loadAssociations();
	}

	@Override
	public Class<Attendance> getEntityClass() {
		return Attendance.class;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	@Override
	public void addAssociations(Criteria criteria) {

		if (attendance.getStudent() != null) {
			criteria = criteria.add(Restrictions.eq("student.id", attendance
					.getStudent().getId()));
		}

		if (attendance.getGradeSubject() != null) {
			criteria = criteria.add(Restrictions.eq("gradeSubject.id",
					attendance.getGradeSubject().getId()));
		}

		if (attendance.getGradeAttendance() != null) {
			criteria = criteria.add(Restrictions.eq("gradeAttendance.id",
					attendance.getGradeAttendance().getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (attendance.getStudent() != null) {
			studentAction.setInstance(getInstance().getStudent());
		}

		if (attendance.getGradeSubject() != null) {
			gradeSubjectAction.setInstance(getInstance().getGradeSubject());
		}

		if (attendance.getGradeAttendance() != null) {
			gradeAttendanceAction.setInstance(getInstance()
					.getGradeAttendance());
		}

	}

	public void updateAssociations() {

	}

	public void updateComposedAssociations() {
	}

	public void clearLists() {

	}

}
