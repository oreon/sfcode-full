package com.nas.recovery.web.action.legal;

import com.nas.recovery.domain.legal.Legal;

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

import com.nas.recovery.domain.legal.ClosingProcess;
import com.nas.recovery.domain.legal.LegalProcess;
import com.nas.recovery.domain.legal.InsurerProcess;

public abstract class LegalActionBase extends BaseAction<Legal>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private Legal legal;

	@In(create = true, value = "lawyerAction")
	com.nas.recovery.web.action.legal.LawyerAction lawyerAction;

	@In(create = true, value = "realEstatePropertyAction")
	com.nas.recovery.web.action.realestate.RealEstatePropertyAction realEstatePropertyAction;

	@In(create = true, value = "titleInsurerAction")
	com.nas.recovery.web.action.loan.TitleInsurerAction titleInsurerAction;

	@DataModel
	private List<Legal> legalRecordList;

	public void setLegalId(Long id) {

		if (listClosingProcesses == null || isDifferentFromCurrent(id))
			listClosingProcesses = new ArrayList<ClosingProcess>();

		if (listLegalProcesses == null || isDifferentFromCurrent(id))
			listLegalProcesses = new ArrayList<LegalProcess>();

		if (listInsurerProcesses == null || isDifferentFromCurrent(id))
			listInsurerProcesses = new ArrayList<InsurerProcess>();

		setId(id);
		loadAssociations();
	}

	public void setLawyerId(Long id) {
		if (id != null && id > 0)
			getInstance().setLawyer(lawyerAction.loadFromId(id));
	}

	public Long getLawyerId() {
		if (getInstance().getLawyer() != null)
			return getInstance().getLawyer().getId();
		return 0L;
	}
	public void setRealEstatePropertyId(Long id) {
		if (id != null && id > 0)
			getInstance().setRealEstateProperty(
					realEstatePropertyAction.loadFromId(id));
	}

	public Long getRealEstatePropertyId() {
		if (getInstance().getRealEstateProperty() != null)
			return getInstance().getRealEstateProperty().getId();
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

	public Long getLegalId() {
		return (Long) getId();
	}

	//@Factory("legalRecordList")
	//@Observer("archivedLegal")
	public void findRecords() {
		//search();
	}

	public Legal getEntity() {
		return legal;
	}

	@Override
	public void setEntity(Legal t) {
		this.legal = t;
		loadAssociations();
	}

	public Legal getLegal() {
		return getInstance();
	}

	@Override
	protected Legal createInstance() {
		return new Legal();
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();
		com.nas.recovery.domain.legal.Lawyer lawyer = lawyerAction
				.getDefinedInstance();
		if (lawyer != null) {
			getInstance().setLawyer(lawyer);
		}
		com.nas.recovery.domain.realestate.RealEstateProperty realEstateProperty = realEstatePropertyAction
				.getDefinedInstance();
		if (realEstateProperty != null) {
			getInstance().setRealEstateProperty(realEstateProperty);
		}
		com.nas.recovery.domain.loan.TitleInsurer titleInsurer = titleInsurerAction
				.getDefinedInstance();
		if (titleInsurer != null) {
			getInstance().setTitleInsurer(titleInsurer);
		}

	}

	public boolean isWired() {
		return true;
	}

	public Legal getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public void setLegal(Legal t) {
		this.legal = t;
		loadAssociations();
	}

	@Override
	public Class<Legal> getEntityClass() {
		return Legal.class;
	}

	@Override
	public void setEntityList(List<Legal> list) {
		this.legalRecordList = list;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	public void addAssoications(Criteria criteria) {

		if (legal.getLawyer() != null) {
			criteria = criteria.add(Restrictions.eq("lawyer.id", legal
					.getLawyer().getId()));
		}

		if (legal.getRealEstateProperty() != null) {
			criteria = criteria.add(Restrictions.eq("realEstateProperty.id",
					legal.getRealEstateProperty().getId()));
		}

		if (legal.getTitleInsurer() != null) {
			criteria = criteria.add(Restrictions.eq("titleInsurer.id", legal
					.getTitleInsurer().getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (legal.getLawyer() != null) {
			lawyerAction.setEntity(getEntity().getLawyer());
		}

		if (legal.getRealEstateProperty() != null) {
			realEstatePropertyAction.setEntity(getEntity()
					.getRealEstateProperty());
		}

		if (legal.getTitleInsurer() != null) {
			titleInsurerAction.setEntity(getEntity().getTitleInsurer());
		}

	}

	public void updateAssociations() {

	}

	protected List<ClosingProcess> listClosingProcesses;

	void initListClosingProcesses() {
		listClosingProcesses = new ArrayList<ClosingProcess>();
		if (getInstance().getClosingProcesses().isEmpty()) {

		} else
			listClosingProcesses.addAll(getInstance().getClosingProcesses());
	}

	public List<ClosingProcess> getListClosingProcesses() {
		if (listClosingProcesses == null || listClosingProcesses.isEmpty()) {
			initListClosingProcesses();
		}
		return listClosingProcesses;
	}

	public void setListClosingProcesses(
			List<ClosingProcess> listClosingProcesses) {
		this.listClosingProcesses = listClosingProcesses;
	}

	public void deleteClosingProcesses(int index) {
		listClosingProcesses.remove(index);
	}

	@Begin(join = true)
	public void addClosingProcesses() {
		ClosingProcess closingProcesses = new ClosingProcess();

		closingProcesses.setLegal(getInstance());

		listClosingProcesses.add(closingProcesses);
	}

	protected List<LegalProcess> listLegalProcesses;

	void initListLegalProcesses() {
		listLegalProcesses = new ArrayList<LegalProcess>();
		if (getInstance().getLegalProcesses().isEmpty()) {

		} else
			listLegalProcesses.addAll(getInstance().getLegalProcesses());
	}

	public List<LegalProcess> getListLegalProcesses() {
		if (listLegalProcesses == null || listLegalProcesses.isEmpty()) {
			initListLegalProcesses();
		}
		return listLegalProcesses;
	}

	public void setListLegalProcesses(List<LegalProcess> listLegalProcesses) {
		this.listLegalProcesses = listLegalProcesses;
	}

	public void deleteLegalProcesses(int index) {
		listLegalProcesses.remove(index);
	}

	@Begin(join = true)
	public void addLegalProcesses() {
		LegalProcess legalProcesses = new LegalProcess();

		legalProcesses.setLegal(getInstance());

		listLegalProcesses.add(legalProcesses);
	}

	protected List<InsurerProcess> listInsurerProcesses;

	void initListInsurerProcesses() {
		listInsurerProcesses = new ArrayList<InsurerProcess>();
		if (getInstance().getInsurerProcesses().isEmpty()) {

		} else
			listInsurerProcesses.addAll(getInstance().getInsurerProcesses());
	}

	public List<InsurerProcess> getListInsurerProcesses() {
		if (listInsurerProcesses == null || listInsurerProcesses.isEmpty()) {
			initListInsurerProcesses();
		}
		return listInsurerProcesses;
	}

	public void setListInsurerProcesses(
			List<InsurerProcess> listInsurerProcesses) {
		this.listInsurerProcesses = listInsurerProcesses;
	}

	public void deleteInsurerProcesses(int index) {
		listInsurerProcesses.remove(index);
	}

	@Begin(join = true)
	public void addInsurerProcesses() {
		InsurerProcess insurerProcesses = new InsurerProcess();

		insurerProcesses.setLegal(getInstance());

		listInsurerProcesses.add(insurerProcesses);
	}

	public void updateComposedAssociations() {

		if (listClosingProcesses != null) {
			getInstance().getClosingProcesses().clear();
			getInstance().getClosingProcesses().addAll(listClosingProcesses);
		}

		if (listLegalProcesses != null) {
			getInstance().getLegalProcesses().clear();
			getInstance().getLegalProcesses().addAll(listLegalProcesses);
		}

		if (listInsurerProcesses != null) {
			getInstance().getInsurerProcesses().clear();
			getInstance().getInsurerProcesses().addAll(listInsurerProcesses);
		}

	}

	public List<Legal> getEntityList() {
		if (legalRecordList == null) {
			findRecords();
		}
		return legalRecordList;
	}

}
