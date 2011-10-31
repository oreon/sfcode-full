package com.oreon.smartsis.web.action.attendance;

import com.oreon.smartsis.attendance.GradeAttendance;

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

import com.oreon.smartsis.attendance.Attendance;

public abstract class GradeAttendanceActionBase
		extends
			BaseAction<GradeAttendance> implements java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private GradeAttendance gradeAttendance;

	@In(create = true, value = "gradeAction")
	com.oreon.smartsis.web.action.domain.GradeAction gradeAction;

	@DataModel
	private List<GradeAttendance> gradeAttendanceRecordList;

	public void setGradeAttendanceId(Long id) {
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
	public void setGradeAttendanceIdForModalDlg(Long id) {
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

	public Long getGradeAttendanceId() {
		return (Long) getId();
	}

	public GradeAttendance getEntity() {
		return gradeAttendance;
	}

	//@Override
	public void setEntity(GradeAttendance t) {
		this.gradeAttendance = t;
		loadAssociations();
	}

	public GradeAttendance getGradeAttendance() {
		return (GradeAttendance) getInstance();
	}

	@Override
	protected GradeAttendance createInstance() {
		GradeAttendance instance = super.createInstance();

		return instance;
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

	}

	public boolean isWired() {
		return true;
	}

	public GradeAttendance getDefinedInstance() {
		return (GradeAttendance) (isIdDefined() ? getInstance() : null);
	}

	public void setGradeAttendance(GradeAttendance t) {
		this.gradeAttendance = t;
		if (gradeAttendance != null)
			setGradeAttendanceId(t.getId());
		loadAssociations();
	}

	@Override
	public Class<GradeAttendance> getEntityClass() {
		return GradeAttendance.class;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	@Override
	public void addAssociations(Criteria criteria) {

		if (gradeAttendance.getGrade() != null) {
			criteria = criteria.add(Restrictions.eq("grade.id", gradeAttendance
					.getGrade().getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (gradeAttendance.getGrade() != null) {
			gradeAction.setInstance(getInstance().getGrade());
		}

		initListAttendances();

	}

	public void updateAssociations() {

	}

	protected List<com.oreon.smartsis.attendance.Attendance> listAttendances = new ArrayList<com.oreon.smartsis.attendance.Attendance>();

	void initListAttendances() {

		if (listAttendances.isEmpty())
			listAttendances.addAll(getInstance().getAttendances());

	}

	public List<com.oreon.smartsis.attendance.Attendance> getListAttendances() {

		prePopulateListAttendances();
		return listAttendances;
	}

	public void prePopulateListAttendances() {
	}

	public void setListAttendances(
			List<com.oreon.smartsis.attendance.Attendance> listAttendances) {
		this.listAttendances = listAttendances;
	}

	public void deleteAttendances(int index) {
		listAttendances.remove(index);
	}

	@Begin(join = true)
	public void addAttendances() {
		initListAttendances();
		Attendance attendances = new Attendance();

		attendances.setGradeAttendance(getInstance());

		getListAttendances().add(attendances);
	}

	public void updateComposedAssociations() {

		if (listAttendances != null) {
			getInstance().getAttendances().clear();
			getInstance().getAttendances().addAll(listAttendances);
		}
	}

	public void clearLists() {
		listAttendances.clear();

	}

	public String viewGradeAttendance() {
		load(currentEntityId);
		return "viewGradeAttendance";
	}

}
