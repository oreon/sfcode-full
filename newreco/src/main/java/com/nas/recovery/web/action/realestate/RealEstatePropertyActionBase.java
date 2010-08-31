package com.nas.recovery.web.action.realestate;

import com.nas.recovery.domain.realestate.RealEstateProperty;

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

import com.nas.recovery.domain.realestate.TenantInfo;
import com.nas.recovery.domain.realestate.Cma;
import com.nas.recovery.domain.appraisal.Appraisal;
import com.nas.recovery.domain.realestate.FilesUploaded;
import com.nas.recovery.domain.propertymanagement.Inspection;
import com.nas.recovery.domain.propertymanagement.Utilitiy;
import com.nas.recovery.domain.propertymanagement.RequestForApproval;

public abstract class RealEstatePropertyActionBase
		extends
			BaseAction<RealEstateProperty> implements java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private RealEstateProperty realEstateProperty;

	@In(create = true, value = "mortgageInsurerAction")
	com.nas.recovery.web.action.loan.MortgageInsurerAction insurerAction;

	@In(create = true, value = "legalAction")
	com.nas.recovery.web.action.legal.LegalAction legalAction;

	@In(create = true, value = "realEstateListingList")
	com.nas.recovery.web.action.realestate.RealEstateListingListQuery realEstateListingList;

	@DataModel
	private List<RealEstateProperty> realEstatePropertyRecordList;

	public void setRealEstatePropertyId(Long id) {

		//if (listTenantInfos == null || isDifferentFromCurrent(id))
		//	listTenantInfos = new ArrayList<TenantInfo>();

		if (listCmas == null || isDifferentFromCurrent(id))
			listCmas = new ArrayList<Cma>();

		if (listAppraisals == null || isDifferentFromCurrent(id))
			listAppraisals = new ArrayList<Appraisal>();

		if (listFilesUploadeds == null || isDifferentFromCurrent(id))
			listFilesUploadeds = new ArrayList<FilesUploaded>();

		if (listInspections == null || isDifferentFromCurrent(id))
			listInspections = new ArrayList<Inspection>();

		if (listUtilitiys == null || isDifferentFromCurrent(id))
			listUtilitiys = new ArrayList<Utilitiy>();

		if (listRequestForApprovals == null || isDifferentFromCurrent(id))
			listRequestForApprovals = new ArrayList<RequestForApproval>();

		setId(id);
		loadAssociations();
	}

	public void setInsurerId(Long id) {
		if (id != null && id > 0)
			getInstance().setInsurer(insurerAction.loadFromId(id));
	}

	public Long getInsurerId() {
		if (getInstance().getInsurer() != null)
			return getInstance().getInsurer().getId();
		return 0L;
	}
	public void setLegalId(Long id) {
		if (id != null && id > 0)
			getInstance().setLegal(legalAction.loadFromId(id));
	}

	public Long getLegalId() {
		if (getInstance().getLegal() != null)
			return getInstance().getLegal().getId();
		return 0L;
	}

	public Long getRealEstatePropertyId() {
		return (Long) getId();
	}

	//@Factory("realEstatePropertyRecordList")
	//@Observer("archivedRealEstateProperty")
	public void findRecords() {
		//search();
	}

	public RealEstateProperty getEntity() {
		return realEstateProperty;
	}

	@Override
	public void setEntity(RealEstateProperty t) {
		this.realEstateProperty = t;
		loadAssociations();
	}

	public RealEstateProperty getRealEstateProperty() {
		return getInstance();
	}

	@Override
	protected RealEstateProperty createInstance() {
		return new RealEstateProperty();
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();
		com.nas.recovery.domain.loan.MortgageInsurer insurer = insurerAction
				.getDefinedInstance();
		if (insurer != null) {
			getInstance().setInsurer(insurer);
		}
		com.nas.recovery.domain.legal.Legal legal = legalAction
				.getDefinedInstance();
		if (legal != null) {
			getInstance().setLegal(legal);
		}

	}

	public boolean isWired() {
		return true;
	}

	public RealEstateProperty getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public void setRealEstateProperty(RealEstateProperty t) {
		this.realEstateProperty = t;
		loadAssociations();
	}

	@Override
	public Class<RealEstateProperty> getEntityClass() {
		return RealEstateProperty.class;
	}

	@Override
	public void setEntityList(List<RealEstateProperty> list) {
		this.realEstatePropertyRecordList = list;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	public void addAssoications(Criteria criteria) {

		if (realEstateProperty.getInsurer() != null) {
			criteria = criteria.add(Restrictions.eq("insurer.id",
					realEstateProperty.getInsurer().getId()));
		}

		if (realEstateProperty.getLegal() != null) {
			criteria = criteria.add(Restrictions.eq("legal.id",
					realEstateProperty.getLegal().getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (realEstateProperty.getInsurer() != null) {
			insurerAction.setInstance(getInstance().getInsurer());
		}

		if (realEstateProperty.getLegal() != null) {
			legalAction.setInstance(getInstance().getLegal());
		}

		try {

			realEstateListingList.getRealEstateListing().setRealEstateProperty(
					getInstance());

		} catch (Exception e) {
			facesMessages.add(e.getMessage());
		}

	}

	public void updateAssociations() {

		com.nas.recovery.domain.realestate.RealEstateListing realEstateListing = (com.nas.recovery.domain.realestate.RealEstateListing) org.jboss.seam.Component
				.getInstance("realEstateListing");
		realEstateListing.setRealEstateProperty(realEstateProperty);
		events.raiseTransactionSuccessEvent("archivedRealEstateListing");

	}

	protected List<com.nas.recovery.domain.realestate.TenantInfo> listTenantInfos;

	void initListTenantInfos() {
		listTenantInfos = new ArrayList<com.nas.recovery.domain.realestate.TenantInfo>();

		if (getInstance().getTenantInfos().isEmpty()) {

		} else
			listTenantInfos.addAll(getInstance().getTenantInfos());

	}

	public List<com.nas.recovery.domain.realestate.TenantInfo> getListTenantInfos() {
		if (listTenantInfos == null || listTenantInfos.isEmpty()) {
			initListTenantInfos();
		}
		return listTenantInfos;
	}

	public void setListTenantInfos(
			List<com.nas.recovery.domain.realestate.TenantInfo> listTenantInfos) {
		this.listTenantInfos = listTenantInfos;
	}

	public void deleteTenantInfos(int index) {
		realEstateProperty.getTenantInfos().remove(index);
	}

	@Begin(join = true)
	public void addTenantInfos() {
		TenantInfo tenantInfos = new TenantInfo();

		tenantInfos.setRealEstateProperty(getInstance());

		realEstateProperty.getTenantInfos().add(tenantInfos);
	}

	protected List<com.nas.recovery.domain.realestate.Cma> listCmas;

	void initListCmas() {
		listCmas = new ArrayList<com.nas.recovery.domain.realestate.Cma>();

		if (getInstance().getCmas().isEmpty()) {

		} else
			listCmas.addAll(getInstance().getCmas());

	}

	public List<com.nas.recovery.domain.realestate.Cma> getListCmas() {
		if (listCmas == null || listCmas.isEmpty()) {
			initListCmas();
		}
		return listCmas;
	}

	public void setListCmas(
			List<com.nas.recovery.domain.realestate.Cma> listCmas) {
		this.listCmas = listCmas;
	}

	public void deleteCmas(int index) {
		listCmas.remove(index);
	}

	@Begin(join = true)
	public void addCmas() {
		Cma cmas = new Cma();
		listCmas.add(cmas);
	}

	protected List<com.nas.recovery.domain.appraisal.Appraisal> listAppraisals;

	void initListAppraisals() {
		listAppraisals = new ArrayList<com.nas.recovery.domain.appraisal.Appraisal>();

		if (getInstance().getAppraisals().isEmpty()) {

		} else
			listAppraisals.addAll(getInstance().getAppraisals());

	}

	public List<com.nas.recovery.domain.appraisal.Appraisal> getListAppraisals() {
		if (listAppraisals == null || listAppraisals.isEmpty()) {
			initListAppraisals();
		}
		return listAppraisals;
	}

	public void setListAppraisals(
			List<com.nas.recovery.domain.appraisal.Appraisal> listAppraisals) {
		this.listAppraisals = listAppraisals;
	}

	public void deleteAppraisals(int index) {
		listAppraisals.remove(index);
	}

	@Begin(join = true)
	public void addAppraisals() {
		Appraisal appraisals = new Appraisal();

		appraisals.setRealEstateProperty(getInstance());

		listAppraisals.add(appraisals);
	}

	protected List<com.nas.recovery.domain.realestate.FilesUploaded> listFilesUploadeds;

	void initListFilesUploadeds() {
		listFilesUploadeds = new ArrayList<com.nas.recovery.domain.realestate.FilesUploaded>();

		if (getInstance().getFilesUploadeds().isEmpty()) {

		} else
			listFilesUploadeds.addAll(getInstance().getFilesUploadeds());

	}

	public List<com.nas.recovery.domain.realestate.FilesUploaded> getListFilesUploadeds() {
		if (listFilesUploadeds == null || listFilesUploadeds.isEmpty()) {
			initListFilesUploadeds();
		}
		return listFilesUploadeds;
	}

	public void setListFilesUploadeds(
			List<com.nas.recovery.domain.realestate.FilesUploaded> listFilesUploadeds) {
		this.listFilesUploadeds = listFilesUploadeds;
	}

	public void deleteFilesUploadeds(int index) {
		listFilesUploadeds.remove(index);
	}

	@Begin(join = true)
	public void addFilesUploadeds() {
		FilesUploaded filesUploadeds = new FilesUploaded();

		filesUploadeds.setRealEstateProperty(getInstance());

		listFilesUploadeds.add(filesUploadeds);
	}

	protected List<com.nas.recovery.domain.propertymanagement.Inspection> listInspections;

	void initListInspections() {
		listInspections = new ArrayList<com.nas.recovery.domain.propertymanagement.Inspection>();

		if (getInstance().getInspections().isEmpty()) {

		} else
			listInspections.addAll(getInstance().getInspections());

	}

	public List<com.nas.recovery.domain.propertymanagement.Inspection> getListInspections() {
		if (listInspections == null || listInspections.isEmpty()) {
			initListInspections();
		}
		return listInspections;
	}

	public void setListInspections(
			List<com.nas.recovery.domain.propertymanagement.Inspection> listInspections) {
		this.listInspections = listInspections;
	}

	public void deleteInspections(int index) {
		listInspections.remove(index);
	}

	@Begin(join = true)
	public void addInspections() {
		Inspection inspections = new Inspection();

		listInspections.add(inspections);
	}

	protected List<com.nas.recovery.domain.propertymanagement.Utilitiy> listUtilitiys;

	void initListUtilitiys() {
		listUtilitiys = new ArrayList<com.nas.recovery.domain.propertymanagement.Utilitiy>();

		if (getInstance().getUtilitiys().isEmpty()) {

		} else
			listUtilitiys.addAll(getInstance().getUtilitiys());

	}

	public List<com.nas.recovery.domain.propertymanagement.Utilitiy> getListUtilitiys() {
		if (listUtilitiys == null || listUtilitiys.isEmpty()) {
			initListUtilitiys();
		}
		return listUtilitiys;
	}

	public void setListUtilitiys(
			List<com.nas.recovery.domain.propertymanagement.Utilitiy> listUtilitiys) {
		this.listUtilitiys = listUtilitiys;
	}

	public void deleteUtilitiys(int index) {
		listUtilitiys.remove(index);
	}

	@Begin(join = true)
	public void addUtilitiys() {
		Utilitiy utilitiys = new Utilitiy();

		listUtilitiys.add(utilitiys);
	}

	protected List<com.nas.recovery.domain.propertymanagement.RequestForApproval> listRequestForApprovals;

	void initListRequestForApprovals() {
		listRequestForApprovals = new ArrayList<com.nas.recovery.domain.propertymanagement.RequestForApproval>();

		if (getInstance().getRequestForApprovals().isEmpty()) {

		} else
			listRequestForApprovals.addAll(getInstance()
					.getRequestForApprovals());

	}

	public List<com.nas.recovery.domain.propertymanagement.RequestForApproval> getListRequestForApprovals() {
		if (listRequestForApprovals == null
				|| listRequestForApprovals.isEmpty()) {
			initListRequestForApprovals();
		}
		return listRequestForApprovals;
	}

	public void setListRequestForApprovals(
			List<com.nas.recovery.domain.propertymanagement.RequestForApproval> listRequestForApprovals) {
		this.listRequestForApprovals = listRequestForApprovals;
	}

	public void deleteRequestForApprovals(int index) {
		listRequestForApprovals.remove(index);
	}

	@Begin(join = true)
	public void addRequestForApprovals() {
		RequestForApproval requestForApprovals = new RequestForApproval();

		listRequestForApprovals.add(requestForApprovals);
	}

	public void updateComposedAssociations() {
		/*
		if (listTenantInfos != null) {
			getInstance().getTenantInfos().clear();
			getInstance().getTenantInfos().addAll(listTenantInfos);
		}*/

		if (listCmas != null) {
			getInstance().getCmas().clear();
			getInstance().getCmas().addAll(listCmas);
		}

		if (listAppraisals != null) {
			getInstance().getAppraisals().clear();
			getInstance().getAppraisals().addAll(listAppraisals);
		}

		if (listFilesUploadeds != null) {
			getInstance().getFilesUploadeds().clear();
			getInstance().getFilesUploadeds().addAll(listFilesUploadeds);
		}

		if (listInspections != null) {
			getInstance().getInspections().clear();
			getInstance().getInspections().addAll(listInspections);
		}

		if (listUtilitiys != null) {
			getInstance().getUtilitiys().clear();
			getInstance().getUtilitiys().addAll(listUtilitiys);
		}

		if (listRequestForApprovals != null) {
			getInstance().getRequestForApprovals().clear();
			getInstance().getRequestForApprovals().addAll(
					listRequestForApprovals);
		}
	}

	public List<RealEstateProperty> getEntityList() {
		if (realEstatePropertyRecordList == null) {
			findRecords();
		}
		return realEstatePropertyRecordList;
	}

	public com.nas.recovery.domain.realestate.RealEstateListing findCurrentListing() {

		return executeSingleResultNamedQuery("findCurrentListing");

	}

}
