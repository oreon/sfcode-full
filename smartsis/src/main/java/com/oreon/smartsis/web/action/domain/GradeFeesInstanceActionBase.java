package com.oreon.smartsis.web.action.domain;

import com.oreon.smartsis.domain.GradeFeesInstance;

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

import com.oreon.smartsis.domain.PaidFee;

public abstract class GradeFeesInstanceActionBase
		extends
			BaseAction<GradeFeesInstance> implements java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private GradeFeesInstance gradeFeesInstance;

	@In(create = true, value = "gradeAction")
	com.oreon.smartsis.web.action.domain.GradeAction gradeAction;

	@DataModel
	private List<GradeFeesInstance> gradeFeesInstanceRecordList;

	public void setGradeFeesInstanceId(Long id) {
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
	public void setGradeFeesInstanceIdForModalDlg(Long id) {
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

	public Long getGradeFeesInstanceId() {
		return (Long) getId();
	}

	public GradeFeesInstance getEntity() {
		return gradeFeesInstance;
	}

	//@Override
	public void setEntity(GradeFeesInstance t) {
		this.gradeFeesInstance = t;
		loadAssociations();
	}

	public GradeFeesInstance getGradeFeesInstance() {
		return (GradeFeesInstance) getInstance();
	}

	@Override
	protected GradeFeesInstance createInstance() {
		return new GradeFeesInstance();
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

	public GradeFeesInstance getDefinedInstance() {
		return (GradeFeesInstance) (isIdDefined() ? getInstance() : null);
	}

	public void setGradeFeesInstance(GradeFeesInstance t) {
		this.gradeFeesInstance = t;
		loadAssociations();
	}

	@Override
	public Class<GradeFeesInstance> getEntityClass() {
		return GradeFeesInstance.class;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	@Override
	public void addAssociations(Criteria criteria) {

		if (gradeFeesInstance.getGrade() != null) {
			criteria = criteria.add(Restrictions.eq("grade.id",
					gradeFeesInstance.getGrade().getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (gradeFeesInstance.getGrade() != null) {
			gradeAction.setInstance(getInstance().getGrade());
		}

		initListPaidFees();

	}

	public void updateAssociations() {

	}

	protected List<com.oreon.smartsis.domain.PaidFee> listPaidFees = new ArrayList<com.oreon.smartsis.domain.PaidFee>();

	void initListPaidFees() {

		if (listPaidFees.isEmpty())
			listPaidFees.addAll(getInstance().getPaidFees());

	}

	public List<com.oreon.smartsis.domain.PaidFee> getListPaidFees() {

		prePopulateListPaidFees();
		return listPaidFees;
	}

	public void prePopulateListPaidFees() {
	}

	public void setListPaidFees(
			List<com.oreon.smartsis.domain.PaidFee> listPaidFees) {
		this.listPaidFees = listPaidFees;
	}

	public void deletePaidFees(int index) {
		listPaidFees.remove(index);
	}

	@Begin(join = true)
	public void addPaidFees() {
		initListPaidFees();
		PaidFee paidFees = new PaidFee();

		paidFees.setGradeFeesInstance(getInstance());

		getListPaidFees().add(paidFees);
	}

	public void updateComposedAssociations() {

		if (listPaidFees != null) {
			getInstance().getPaidFees().clear();
			getInstance().getPaidFees().addAll(listPaidFees);
		}
	}

	public void clearLists() {
		listPaidFees.clear();

	}

}
