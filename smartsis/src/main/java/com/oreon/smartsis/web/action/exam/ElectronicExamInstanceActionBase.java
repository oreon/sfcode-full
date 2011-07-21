package com.oreon.smartsis.web.action.exam;

import com.oreon.smartsis.exam.ElectronicExamInstance;

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

import com.oreon.smartsis.exam.QuestionInstance;

public abstract class ElectronicExamInstanceActionBase
		extends
			BaseAction<ElectronicExamInstance> implements java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private ElectronicExamInstance electronicExamInstance;

	@In(create = true, value = "studentAction")
	com.oreon.smartsis.web.action.domain.StudentAction studentAction;

	@In(create = true, value = "electronicExamEventAction")
	com.oreon.smartsis.web.action.exam.ElectronicExamEventAction electronicExamEventAction;

	@DataModel
	private List<ElectronicExamInstance> electronicExamInstanceRecordList;

	public void setElectronicExamInstanceId(Long id) {
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
	public void setElectronicExamInstanceIdForModalDlg(Long id) {
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

	public void setElectronicExamEventId(Long id) {

		if (id != null && id > 0)
			getInstance().setElectronicExamEvent(
					electronicExamEventAction.loadFromId(id));

	}

	public Long getElectronicExamEventId() {
		if (getInstance().getElectronicExamEvent() != null)
			return getInstance().getElectronicExamEvent().getId();
		return 0L;
	}

	public Long getElectronicExamInstanceId() {
		return (Long) getId();
	}

	public ElectronicExamInstance getEntity() {
		return electronicExamInstance;
	}

	//@Override
	public void setEntity(ElectronicExamInstance t) {
		this.electronicExamInstance = t;
		loadAssociations();
	}

	public ElectronicExamInstance getElectronicExamInstance() {
		return (ElectronicExamInstance) getInstance();
	}

	@Override
	protected ElectronicExamInstance createInstance() {
		return new ElectronicExamInstance();
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

		com.oreon.smartsis.exam.ElectronicExamEvent electronicExamEvent = electronicExamEventAction
				.getDefinedInstance();
		if (electronicExamEvent != null && isNew()) {
			getInstance().setElectronicExamEvent(electronicExamEvent);
		}

	}

	public boolean isWired() {
		return true;
	}

	public ElectronicExamInstance getDefinedInstance() {
		return (ElectronicExamInstance) (isIdDefined() ? getInstance() : null);
	}

	public void setElectronicExamInstance(ElectronicExamInstance t) {
		this.electronicExamInstance = t;
		if (electronicExamInstance != null)
			setElectronicExamInstanceId(t.getId());
		loadAssociations();
	}

	@Override
	public Class<ElectronicExamInstance> getEntityClass() {
		return ElectronicExamInstance.class;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	@Override
	public void addAssociations(Criteria criteria) {

		if (electronicExamInstance.getStudent() != null) {
			criteria = criteria.add(Restrictions.eq("student.id",
					electronicExamInstance.getStudent().getId()));
		}

		if (electronicExamInstance.getElectronicExamEvent() != null) {
			criteria = criteria.add(Restrictions.eq("electronicExamEvent.id",
					electronicExamInstance.getElectronicExamEvent().getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (electronicExamInstance.getStudent() != null) {
			studentAction.setInstance(getInstance().getStudent());
		}

		if (electronicExamInstance.getElectronicExamEvent() != null) {
			electronicExamEventAction.setInstance(getInstance()
					.getElectronicExamEvent());
		}

		initListQuestionInstances();

	}

	public void updateAssociations() {

	}

	protected List<com.oreon.smartsis.exam.QuestionInstance> listQuestionInstances = new ArrayList<com.oreon.smartsis.exam.QuestionInstance>();

	void initListQuestionInstances() {

		if (listQuestionInstances.isEmpty())
			listQuestionInstances.addAll(getInstance().getQuestionInstances());

	}

	public List<com.oreon.smartsis.exam.QuestionInstance> getListQuestionInstances() {

		prePopulateListQuestionInstances();
		return listQuestionInstances;
	}

	public void prePopulateListQuestionInstances() {
	}

	public void setListQuestionInstances(
			List<com.oreon.smartsis.exam.QuestionInstance> listQuestionInstances) {
		this.listQuestionInstances = listQuestionInstances;
	}

	public void deleteQuestionInstances(int index) {
		listQuestionInstances.remove(index);
	}

	@Begin(join = true)
	public void addQuestionInstances() {
		initListQuestionInstances();
		QuestionInstance questionInstances = new QuestionInstance();

		questionInstances.setElectronicExamInstance(getInstance());

		getListQuestionInstances().add(questionInstances);
	}

	public void updateComposedAssociations() {

		if (listQuestionInstances != null) {
			getInstance().getQuestionInstances().clear();
			getInstance().getQuestionInstances().addAll(listQuestionInstances);
		}
	}

	public void clearLists() {
		listQuestionInstances.clear();

	}

	public Integer calculateScore(
			com.oreon.smartsis.exam.ElectronicExamInstance examInstance) {

		return null;

	}

}
