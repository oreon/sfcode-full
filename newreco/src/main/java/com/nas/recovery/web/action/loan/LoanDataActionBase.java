package com.nas.recovery.web.action.loan;

import com.nas.recovery.domain.loan.LoanData;

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

public abstract class LoanDataActionBase extends BaseAction<LoanData>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private LoanData loanData;

	@In(create = true, value = "lenderAction")
	com.nas.recovery.web.action.loan.LenderAction lenderAction;

	@In(create = true, value = "mortgageInsurerAction")
	com.nas.recovery.web.action.loan.MortgageInsurerAction mortgageInsurerAction;

	@In(create = true, value = "titleInsurerAction")
	com.nas.recovery.web.action.loan.TitleInsurerAction titleInsurerAction;

	@In(create = true, value = "brokerAction")
	com.nas.recovery.web.action.loan.BrokerAction brokerAction;

	@In(create = true, value = "underwriterAction")
	com.nas.recovery.web.action.loan.UnderwriterAction underwriterAction;

	@DataModel
	private List<LoanData> loanDataRecordList;

	public void setLoanDataId(Long id) {

		setId(id);
		loadAssociations();
	}

	public void setLenderId(Long id) {
		if (id != null && id > 0)
			getInstance().setLender(lenderAction.loadFromId(id));
	}

	public Long getLenderId() {
		if (getInstance().getLender() != null)
			return getInstance().getLender().getId();
		return 0L;
	}
	public void setMortgageInsurerId(Long id) {
		if (id != null && id > 0)
			getInstance().setMortgageInsurer(
					mortgageInsurerAction.loadFromId(id));
	}

	public Long getMortgageInsurerId() {
		if (getInstance().getMortgageInsurer() != null)
			return getInstance().getMortgageInsurer().getId();
		return 0L;
	}
	public void setTitleInsurerId(Long id) {
		if (id != null && id > 0)
			getInstance().setTitleInsurer(titleInsurerAction.loadFromId(id));
	}

	public Long getTitleInsurerId() {
		if (getInstance().getTitleInsurer() != null)
			return getInstance().getTitleInsurer().getId();
		return 0L;
	}
	public void setBrokerId(Long id) {
		if (id != null && id > 0)
			getInstance().setBroker(brokerAction.loadFromId(id));
	}

	public Long getBrokerId() {
		if (getInstance().getBroker() != null)
			return getInstance().getBroker().getId();
		return 0L;
	}
	public void setUnderwriterId(Long id) {
		if (id != null && id > 0)
			getInstance().setUnderwriter(underwriterAction.loadFromId(id));
	}

	public Long getUnderwriterId() {
		if (getInstance().getUnderwriter() != null)
			return getInstance().getUnderwriter().getId();
		return 0L;
	}

	public Long getLoanDataId() {
		return (Long) getId();
	}

	//@Factory("loanDataRecordList")
	//@Observer("archivedLoanData")
	public void findRecords() {
		//search();
	}

	public LoanData getEntity() {
		return loanData;
	}

	@Override
	public void setEntity(LoanData t) {
		this.loanData = t;
		loadAssociations();
	}

	public LoanData getLoanData() {
		return getInstance();
	}

	@Override
	protected LoanData createInstance() {
		return new LoanData();
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();
		com.nas.recovery.domain.loan.Lender lender = lenderAction
				.getDefinedInstance();
		if (lender != null) {
			getInstance().setLender(lender);
		}
		com.nas.recovery.domain.loan.MortgageInsurer mortgageInsurer = mortgageInsurerAction
				.getDefinedInstance();
		if (mortgageInsurer != null) {
			getInstance().setMortgageInsurer(mortgageInsurer);
		}
		com.nas.recovery.domain.loan.TitleInsurer titleInsurer = titleInsurerAction
				.getDefinedInstance();
		if (titleInsurer != null) {
			getInstance().setTitleInsurer(titleInsurer);
		}
		com.nas.recovery.domain.loan.Broker broker = brokerAction
				.getDefinedInstance();
		if (broker != null) {
			getInstance().setBroker(broker);
		}
		com.nas.recovery.domain.loan.Underwriter underwriter = underwriterAction
				.getDefinedInstance();
		if (underwriter != null) {
			getInstance().setUnderwriter(underwriter);
		}

	}

	public boolean isWired() {
		return true;
	}

	public LoanData getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public void setLoanData(LoanData t) {
		this.loanData = t;
		loadAssociations();
	}

	@Override
	public Class<LoanData> getEntityClass() {
		return LoanData.class;
	}

	@Override
	public void setEntityList(List<LoanData> list) {
		this.loanDataRecordList = list;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	public void addAssoications(Criteria criteria) {

		if (loanData.getLender() != null) {
			criteria = criteria.add(Restrictions.eq("lender.id", loanData
					.getLender().getId()));
		}

		if (loanData.getMortgageInsurer() != null) {
			criteria = criteria.add(Restrictions.eq("mortgageInsurer.id",
					loanData.getMortgageInsurer().getId()));
		}

		if (loanData.getTitleInsurer() != null) {
			criteria = criteria.add(Restrictions.eq("titleInsurer.id", loanData
					.getTitleInsurer().getId()));
		}

		if (loanData.getBroker() != null) {
			criteria = criteria.add(Restrictions.eq("broker.id", loanData
					.getBroker().getId()));
		}

		if (loanData.getUnderwriter() != null) {
			criteria = criteria.add(Restrictions.eq("underwriter.id", loanData
					.getUnderwriter().getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (loanData.getLender() != null) {
			lenderAction.setInstance(getInstance().getLender());
		}

		if (loanData.getMortgageInsurer() != null) {
			mortgageInsurerAction.setInstance(getInstance()
					.getMortgageInsurer());
		}

		if (loanData.getTitleInsurer() != null) {
			titleInsurerAction.setInstance(getInstance().getTitleInsurer());
		}

		if (loanData.getBroker() != null) {
			brokerAction.setInstance(getInstance().getBroker());
		}

		if (loanData.getUnderwriter() != null) {
			underwriterAction.setInstance(getInstance().getUnderwriter());
		}

	}

	public void updateAssociations() {

	}

	public List<LoanData> getEntityList() {
		if (loanDataRecordList == null) {
			findRecords();
		}
		return loanDataRecordList;
	}

}
