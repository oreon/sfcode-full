package com.nas.recovery.web.action.realestate;

import com.nas.recovery.domain.realestate.Offer;

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

public abstract class OfferActionBase extends BaseAction<Offer>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private Offer offer;

	@In(create = true, value = "realEstateListingAction")
	com.nas.recovery.web.action.realestate.RealEstateListingAction realEstateListingAction;

	@DataModel
	private List<Offer> offerRecordList;

	public void setOfferId(Long id) {

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

	public Long getOfferId() {
		return (Long) getId();
	}

	//@Factory("offerRecordList")
	//@Observer("archivedOffer")
	public void findRecords() {
		//search();
	}

	public Offer getEntity() {
		return offer;
	}

	@Override
	public void setEntity(Offer t) {
		this.offer = t;
		loadAssociations();
	}

	public Offer getOffer() {
		return getInstance();
	}

	@Override
	protected Offer createInstance() {
		return new Offer();
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

	public Offer getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public void setOffer(Offer t) {
		this.offer = t;
		loadAssociations();
	}

	@Override
	public Class<Offer> getEntityClass() {
		return Offer.class;
	}

	@Override
	public void setEntityList(List<Offer> list) {
		this.offerRecordList = list;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	public void addAssoications(Criteria criteria) {

		if (offer.getRealEstateListing() != null) {
			criteria = criteria.add(Restrictions.eq("realEstateListing.id",
					offer.getRealEstateListing().getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (offer.getRealEstateListing() != null) {
			realEstateListingAction.setInstance(getInstance()
					.getRealEstateListing());
		}

	}

	public void updateAssociations() {

	}

	public void updateComposedAssociations() {
	}

	public List<Offer> getEntityList() {
		if (offerRecordList == null) {
			findRecords();
		}
		return offerRecordList;
	}

}
