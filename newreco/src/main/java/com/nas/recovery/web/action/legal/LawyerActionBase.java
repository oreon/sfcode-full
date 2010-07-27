package com.nas.recovery.web.action.legal;

import com.nas.recovery.domain.legal.Lawyer;

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

import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.log.Log;
import org.jboss.seam.annotations.Observer;

public class LawyerActionBase extends BaseAction<Lawyer>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private Lawyer lawyer;

	@In(create = true, value = "lawfirmAction")
	com.nas.recovery.web.action.legal.LawfirmAction lawfirmAction;

	@DataModel
	private List<Lawyer> lawyerRecordList;

	public void setLawyerId(Long id) {

		setId(id);
		loadAssociations();
	}

	public void setLawfirmId(Long id) {
		if (id != null && id > 0)
			getInstance().setLawfirm(lawfirmAction.loadFromId(id));
	}

	public Long getLawfirmId() {
		if (getInstance().getLawfirm() != null)
			return getInstance().getLawfirm().getId();
		return 0L;
	}

	public Long getLawyerId() {
		return (Long) getId();
	}

	//@Factory("lawyerRecordList")
	//@Observer("archivedLawyer")
	public void findRecords() {
		//search();
	}

	public Lawyer getEntity() {
		return lawyer;
	}

	@Override
	public void setEntity(Lawyer t) {
		this.lawyer = t;
		loadAssociations();
	}

	public Lawyer getLawyer() {
		return getInstance();
	}

	@Override
	protected Lawyer createInstance() {
		return new Lawyer();
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();
		com.nas.recovery.domain.legal.Lawfirm lawfirm = lawfirmAction
				.getDefinedInstance();
		if (lawfirm != null) {
			getInstance().setLawfirm(lawfirm);
		}

	}

	public boolean isWired() {
		return true;
	}

	public Lawyer getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public void setLawyer(Lawyer t) {
		this.lawyer = t;
		loadAssociations();
	}

	@Override
	public Class<Lawyer> getEntityClass() {
		return Lawyer.class;
	}

	@Override
	public void setEntityList(List<Lawyer> list) {
		this.lawyerRecordList = list;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	public void addAssoications(Criteria criteria) {

		if (lawyer.getLawfirm() != null) {
			criteria = criteria.add(Restrictions.eq("lawfirm.id", lawyer
					.getLawfirm().getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (lawyer.getLawfirm() != null) {
			lawfirmAction.setEntity(getEntity().getLawfirm());
		}

	}

	public void updateAssociations() {

	}

	public List<Lawyer> getEntityList() {
		if (lawyerRecordList == null) {
			findRecords();
		}
		return lawyerRecordList;
	}

}
