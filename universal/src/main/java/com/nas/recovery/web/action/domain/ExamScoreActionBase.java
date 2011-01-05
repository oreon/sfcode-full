package com.nas.recovery.web.action.domain;

import com.oreon.tapovan.domain.ExamScore;

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

public abstract class ExamScoreActionBase extends BaseAction<ExamScore>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private ExamScore examScore;

	@In(create = true, value = "studentAction")
	com.nas.recovery.web.action.domain.StudentAction studentAction;

	@In(create = true, value = "examInstanceAction")
	com.nas.recovery.web.action.domain.ExamInstanceAction examInstanceAction;

	@DataModel
	private List<ExamScore> examScoreRecordList;

	public void setExamScoreId(Long id) {
		if (id == 0) {
			clearInstance();
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
	public void setExamScoreIdForModalDlg(Long id) {
		setId(id);
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

	public void setExamInstanceId(Long id) {

		if (id != null && id > 0)
			getInstance().setExamInstance(examInstanceAction.loadFromId(id));

	}

	public Long getExamInstanceId() {
		if (getInstance().getExamInstance() != null)
			return getInstance().getExamInstance().getId();
		return 0L;
	}

	public Long getExamScoreId() {
		return (Long) getId();
	}

	public ExamScore getEntity() {
		return examScore;
	}

	//@Override
	public void setEntity(ExamScore t) {
		this.examScore = t;
		loadAssociations();
	}

	public ExamScore getExamScore() {
		return (ExamScore) getInstance();
	}

	@Override
	protected ExamScore createInstance() {
		return new ExamScore();
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();

		com.oreon.tapovan.domain.Student student = studentAction
				.getDefinedInstance();
		if (student != null) {
			getInstance().setStudent(student);
		}

		com.oreon.tapovan.domain.ExamInstance examInstance = examInstanceAction
				.getDefinedInstance();
		if (examInstance != null) {
			getInstance().setExamInstance(examInstance);
		}

	}

	public boolean isWired() {
		return true;
	}

	public ExamScore getDefinedInstance() {
		return (ExamScore) (isIdDefined() ? getInstance() : null);
	}

	public void setExamScore(ExamScore t) {
		this.examScore = t;
		loadAssociations();
	}

	@Override
	public Class<ExamScore> getEntityClass() {
		return ExamScore.class;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	@Override
	public void addAssociations(Criteria criteria) {

		if (examScore.getStudent() != null) {
			criteria = criteria.add(Restrictions.eq("student.id", examScore
					.getStudent().getId()));
		}

		if (examScore.getExamInstance() != null) {
			criteria = criteria.add(Restrictions.eq("examInstance.id",
					examScore.getExamInstance().getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (examScore.getStudent() != null) {
			studentAction.setInstance(getInstance().getStudent());
		}

		if (examScore.getExamInstance() != null) {
			examInstanceAction.setInstance(getInstance().getExamInstance());
		}

	}

	public void updateAssociations() {

	}

	public void updateComposedAssociations() {
	}

	public void clearLists() {

	}

}
