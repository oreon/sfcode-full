package com.nas.recovery.web.action.domain;

import com.oreon.tapovan.domain.GradeFee;

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

public abstract class GradeFeeActionBase extends BaseAction<GradeFee>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private GradeFee gradeFee;

	@In(create = true, value = "gradeAction")
	com.nas.recovery.web.action.domain.GradeAction gradeAction;

	@In(create = true, value = "feeAction")
	com.nas.recovery.web.action.domain.FeeAction feeAction;

	@DataModel
	private List<GradeFee> gradeFeeRecordList;

	public void setGradeFeeId(Long id) {
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
	public void setGradeFeeIdForModalDlg(Long id) {
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

	public void setFeeId(Long id) {

		if (id != null && id > 0)
			getInstance().setFee(feeAction.loadFromId(id));

	}

	public Long getFeeId() {
		if (getInstance().getFee() != null)
			return getInstance().getFee().getId();
		return 0L;
	}

	public Long getGradeFeeId() {
		return (Long) getId();
	}

	public GradeFee getEntity() {
		return gradeFee;
	}

	//@Override
	public void setEntity(GradeFee t) {
		this.gradeFee = t;
		loadAssociations();
	}

	public GradeFee getGradeFee() {
		return (GradeFee) getInstance();
	}

	@Override
	protected GradeFee createInstance() {
		return new GradeFee();
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();

		com.oreon.tapovan.domain.Grade grade = gradeAction.getDefinedInstance();
		if (grade != null && isNew()) {
			getInstance().setGrade(grade);
		}

		com.oreon.tapovan.domain.Fee fee = feeAction.getDefinedInstance();
		if (fee != null && isNew()) {
			getInstance().setFee(fee);
		}

	}

	public boolean isWired() {
		return true;
	}

	public GradeFee getDefinedInstance() {
		return (GradeFee) (isIdDefined() ? getInstance() : null);
	}

	public void setGradeFee(GradeFee t) {
		this.gradeFee = t;
		loadAssociations();
	}

	@Override
	public Class<GradeFee> getEntityClass() {
		return GradeFee.class;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	@Override
	public void addAssociations(Criteria criteria) {

		if (gradeFee.getGrade() != null) {
			criteria = criteria.add(Restrictions.eq("grade.id", gradeFee
					.getGrade().getId()));
		}

		if (gradeFee.getFee() != null) {
			criteria = criteria.add(Restrictions.eq("fee.id", gradeFee.getFee()
					.getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (gradeFee.getGrade() != null) {
			gradeAction.setInstance(getInstance().getGrade());
		}

		if (gradeFee.getFee() != null) {
			feeAction.setInstance(getInstance().getFee());
		}

	}

	public void updateAssociations() {

	}

	public void updateComposedAssociations() {
	}

	public void clearLists() {

	}

}
