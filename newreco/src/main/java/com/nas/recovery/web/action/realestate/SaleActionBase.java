package com.nas.recovery.web.action.realestate;

import com.nas.recovery.domain.realestate.Sale;

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

public abstract class SaleActionBase extends BaseAction<Sale>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private Sale sale;

	@In(create = true, value = "realEstateListingAction")
	com.nas.recovery.web.action.realestate.RealEstateListingAction realEstateListingAction;

	@DataModel
	private List<Sale> saleRecordList;

	public void setSaleId(Long id) {

		setId(id);
		loadAssociations();
	}

	public void setRealEstateListingId(Long id) {
		if (id != null && id > 0)
			getInstance().setRealEstateListing(
					realEstateListingAction.loadFromId(id));
	}

	public Long getRealEstateListingId() {
		if (getInstance().getRealEstateListing() != null)
			return getInstance().getRealEstateListing().getId();
		return 0L;
	}

	public Long getSaleId() {
		return (Long) getId();
	}

	//@Factory("saleRecordList")
	//@Observer("archivedSale")
	public void findRecords() {
		//search();
	}

	public Sale getEntity() {
		return sale;
	}

	@Override
	public void setEntity(Sale t) {
		this.sale = t;
		loadAssociations();
	}

	public Sale getSale() {
		return getInstance();
	}

	@Override
	protected Sale createInstance() {
		return new Sale();
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();
		com.nas.recovery.domain.realestate.RealEstateListing realEstateListing = realEstateListingAction
				.getDefinedInstance();
		if (realEstateListing != null) {
			getInstance().setRealEstateListing(realEstateListing);
		}

	}

	public boolean isWired() {
		return true;
	}

	public Sale getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public void setSale(Sale t) {
		this.sale = t;
		loadAssociations();
	}

	@Override
	public Class<Sale> getEntityClass() {
		return Sale.class;
	}

	@Override
	public void setEntityList(List<Sale> list) {
		this.saleRecordList = list;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	public void addAssoications(Criteria criteria) {

		if (sale.getRealEstateListing() != null) {
			criteria = criteria.add(Restrictions.eq("realEstateListing.id",
					sale.getRealEstateListing().getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (sale.getRealEstateListing() != null) {
			realEstateListingAction.setInstance(getInstance()
					.getRealEstateListing());
		}

	}

	public void updateAssociations() {

	}

	public void updateComposedAssociations() {
	}

	public List<Sale> getEntityList() {
		if (saleRecordList == null) {
			findRecords();
		}
		return saleRecordList;
	}

}
