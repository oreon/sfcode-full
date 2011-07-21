package com.oreon.smartsis.web.action.domain;

import com.oreon.smartsis.domain.PaidFee;

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

public abstract class PaidFeeActionBase extends BaseAction<PaidFee>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private PaidFee paidFee;

	@In(create = true, value = "studentAction")
	com.oreon.smartsis.web.action.domain.StudentAction studentAction;

	@In(create = true, value = "gradeFeeAction")
	com.oreon.smartsis.web.action.domain.GradeFeeAction gradeFeeAction;

	@DataModel
	private List<PaidFee> paidFeeRecordList;

	public void setPaidFeeId(Long id) {
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
	public void setPaidFeeIdForModalDlg(Long id) {
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

	public void setGradeFeeId(Long id) {

		if (id != null && id > 0)
			getInstance().setGradeFee(gradeFeeAction.loadFromId(id));

	}

	public Long getGradeFeeId() {
		if (getInstance().getGradeFee() != null)
			return getInstance().getGradeFee().getId();
		return 0L;
	}

	public Long getPaidFeeId() {
		return (Long) getId();
	}

	public PaidFee getEntity() {
		return paidFee;
	}

	//@Override
	public void setEntity(PaidFee t) {
		this.paidFee = t;
		loadAssociations();
	}

	public PaidFee getPaidFee() {
		return (PaidFee) getInstance();
	}

	@Override
	protected PaidFee createInstance() {
		return new PaidFee();
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

		com.oreon.smartsis.domain.GradeFee gradeFee = gradeFeeAction
				.getDefinedInstance();
		if (gradeFee != null && isNew()) {
			getInstance().setGradeFee(gradeFee);
		}

	}

	public boolean isWired() {
		return true;
	}

	public PaidFee getDefinedInstance() {
		return (PaidFee) (isIdDefined() ? getInstance() : null);
	}

	public void setPaidFee(PaidFee t) {
		this.paidFee = t;
		if (paidFee != null)
			setPaidFeeId(t.getId());
		loadAssociations();
	}

	@Override
	public Class<PaidFee> getEntityClass() {
		return PaidFee.class;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	@Override
	public void addAssociations(Criteria criteria) {

		if (paidFee.getStudent() != null) {
			criteria = criteria.add(Restrictions.eq("student.id", paidFee
					.getStudent().getId()));
		}

		if (paidFee.getGradeFee() != null) {
			criteria = criteria.add(Restrictions.eq("gradeFee.id", paidFee
					.getGradeFee().getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (paidFee.getStudent() != null) {
			studentAction.setInstance(getInstance().getStudent());
		}

		if (paidFee.getGradeFee() != null) {
			gradeFeeAction.setInstance(getInstance().getGradeFee());
		}

	}

	public void updateAssociations() {

	}

	public void updateComposedAssociations() {
	}

	public void clearLists() {

	}

}
