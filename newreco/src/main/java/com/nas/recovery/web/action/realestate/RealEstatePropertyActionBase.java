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

public class RealEstatePropertyActionBase
		extends
			BaseAction<RealEstateProperty> implements java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private RealEstateProperty realEstateProperty;

	@In(create = true, value = "mortgageInsurerAction")
	com.nas.recovery.web.action.loan.MortgageInsurerAction insurerAction;

	@In(create = true, value = "realEstateListingList")
	com.nas.recovery.web.action.realestate.RealEstateListingListQuery realEstateListingList;

	@DataModel
	private List<RealEstateProperty> realEstatePropertyRecordList;

	public void setRealEstatePropertyId(Long id) {

		if (listTenantInfos == null || isDifferentFromCurrent(id))
			listTenantInfos = new ArrayList<TenantInfo>();

		if (listCmas == null || isDifferentFromCurrent(id))
			listCmas = new ArrayList<Cma>();

		if (listAppraisals == null || isDifferentFromCurrent(id))
			listAppraisals = new ArrayList<Appraisal>();

		if (listFilesUploadeds == null || isDifferentFromCurrent(id))
			listFilesUploadeds = new ArrayList<FilesUploaded>();

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

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (realEstateProperty.getInsurer() != null) {
			insurerAction.setEntity(getEntity().getInsurer());
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

	protected List<TenantInfo> listTenantInfos;

	void initListTenantInfos() {
		listTenantInfos = new ArrayList<TenantInfo>();
		if (getInstance().getTenantInfos().isEmpty()) {

		} else
			listTenantInfos.addAll(getInstance().getTenantInfos());
	}

	public List<TenantInfo> getListTenantInfos() {
		if (listTenantInfos == null) {
			initListTenantInfos();
		}
		return listTenantInfos;
	}

	public void setListTenantInfos(List<TenantInfo> listTenantInfos) {
		this.listTenantInfos = listTenantInfos;
	}

	public void deleteTenantInfos(int index) {
		listTenantInfos.remove(index);
	}

	@Begin(join = true)
	public void addTenantInfos() {
		TenantInfo tenantInfos = new TenantInfo();

		listTenantInfos.add(tenantInfos);
	}

	protected List<Cma> listCmas;

	void initListCmas() {
		listCmas = new ArrayList<Cma>();
		if (getInstance().getCmas().isEmpty()) {

		} else
			listCmas.addAll(getInstance().getCmas());
	}

	public List<Cma> getListCmas() {
		if (listCmas == null) {
			initListCmas();
		}
		return listCmas;
	}

	public void setListCmas(List<Cma> listCmas) {
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

	protected List<Appraisal> listAppraisals;

	void initListAppraisals() {
		listAppraisals = new ArrayList<Appraisal>();
		if (getInstance().getAppraisals().isEmpty()) {

		} else
			listAppraisals.addAll(getInstance().getAppraisals());
	}

	public List<Appraisal> getListAppraisals() {
		if (listAppraisals == null) {
			initListAppraisals();
		}
		return listAppraisals;
	}

	public void setListAppraisals(List<Appraisal> listAppraisals) {
		this.listAppraisals = listAppraisals;
	}

	public void deleteAppraisals(int index) {
		listAppraisals.remove(index);
	}

	@Begin(join = true)
	public void addAppraisals() {
		Appraisal appraisals = new Appraisal();

		listAppraisals.add(appraisals);
	}

	protected List<FilesUploaded> listFilesUploadeds;

	void initListFilesUploadeds() {
		listFilesUploadeds = new ArrayList<FilesUploaded>();
		if (getInstance().getFilesUploadeds().isEmpty()) {

		} else
			listFilesUploadeds.addAll(getInstance().getFilesUploadeds());
	}

	public List<FilesUploaded> getListFilesUploadeds() {
		if (listFilesUploadeds == null) {
			initListFilesUploadeds();
		}
		return listFilesUploadeds;
	}

	public void setListFilesUploadeds(List<FilesUploaded> listFilesUploadeds) {
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

	public void updateComposedAssociations() {

		getInstance().getTenantInfos().clear();
		getInstance().getTenantInfos().addAll(listTenantInfos);

		getInstance().getCmas().clear();
		getInstance().getCmas().addAll(listCmas);

		getInstance().getAppraisals().clear();
		getInstance().getAppraisals().addAll(listAppraisals);

		getInstance().getFilesUploadeds().clear();
		getInstance().getFilesUploadeds().addAll(listFilesUploadeds);

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
