package com.nas.recovery.web.action.realestate;

import com.nas.recovery.domain.realestate.RealEstateListing;

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

import com.nas.recovery.domain.realestate.Offer;

public abstract class RealEstateListingActionBase
		extends
			BaseAction<RealEstateListing> implements java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private RealEstateListing realEstateListing;

	@In(create = true, value = "realEstateBoardAction")
	com.nas.recovery.web.action.realestate.RealEstateBoardAction realEstateBoardAction;

	@In(create = true, value = "realEstatePropertyAction")
	com.nas.recovery.web.action.realestate.RealEstatePropertyAction realEstatePropertyAction;

	@In(create = true, value = "masterAgentAction")
	com.nas.recovery.web.action.realestate.MasterAgentAction masterAction;

	@In(create = true, value = "masterAgentAction")
	com.nas.recovery.web.action.realestate.MasterAgentAction subagentAction;

	@DataModel
	private List<RealEstateListing> realEstateListingRecordList;

	public void setRealEstateListingId(Long id) {

		if (listOffers == null || isDifferentFromCurrent(id))
			listOffers = new ArrayList<Offer>();

		setId(id);
		loadAssociations();
	}

	public void setRealEstateBoardId(Long id) {
		if (id != null && id > 0)
			getInstance().setRealEstateBoard(
					realEstateBoardAction.loadFromId(id));
	}

	public Long getRealEstateBoardId() {
		if (getInstance().getRealEstateBoard() != null)
			return getInstance().getRealEstateBoard().getId();
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
	public void setMasterId(Long id) {
		if (id != null && id > 0)
			getInstance().setMaster(masterAction.loadFromId(id));
	}

	public Long getMasterId() {
		if (getInstance().getMaster() != null)
			return getInstance().getMaster().getId();
		return 0L;
	}
	public void setSubagentId(Long id) {
		if (id != null && id > 0)
			getInstance().setSubagent(subagentAction.loadFromId(id));
	}

	public Long getSubagentId() {
		if (getInstance().getSubagent() != null)
			return getInstance().getSubagent().getId();
		return 0L;
	}

	public Long getRealEstateListingId() {
		return (Long) getId();
	}

	//@Factory("realEstateListingRecordList")
	//@Observer("archivedRealEstateListing")
	public void findRecords() {
		//search();
	}

	public RealEstateListing getEntity() {
		return realEstateListing;
	}

	@Override
	public void setEntity(RealEstateListing t) {
		this.realEstateListing = t;
		loadAssociations();
	}

	public RealEstateListing getRealEstateListing() {
		return getInstance();
	}

	@Override
	protected RealEstateListing createInstance() {
		return new RealEstateListing();
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();
		com.nas.recovery.domain.realestate.RealEstateBoard realEstateBoard = realEstateBoardAction
				.getDefinedInstance();
		if (realEstateBoard != null) {
			getInstance().setRealEstateBoard(realEstateBoard);
		}
		com.nas.recovery.domain.realestate.RealEstateProperty realEstateProperty = realEstatePropertyAction
				.getDefinedInstance();
		if (realEstateProperty != null) {
			getInstance().setRealEstateProperty(realEstateProperty);
		}
		com.nas.recovery.domain.realestate.MasterAgent master = masterAction
				.getDefinedInstance();
		if (master != null) {
			getInstance().setMaster(master);
		}
		com.nas.recovery.domain.realestate.MasterAgent subagent = subagentAction
				.getDefinedInstance();
		if (subagent != null) {
			getInstance().setSubagent(subagent);
		}

	}

	public boolean isWired() {
		return true;
	}

	public RealEstateListing getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public void setRealEstateListing(RealEstateListing t) {
		this.realEstateListing = t;
		loadAssociations();
	}

	@Override
	public Class<RealEstateListing> getEntityClass() {
		return RealEstateListing.class;
	}

	@Override
	public void setEntityList(List<RealEstateListing> list) {
		this.realEstateListingRecordList = list;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	public void addAssoications(Criteria criteria) {

		if (realEstateListing.getRealEstateBoard() != null) {
			criteria = criteria.add(Restrictions.eq("realEstateBoard.id",
					realEstateListing.getRealEstateBoard().getId()));
		}

		if (realEstateListing.getRealEstateProperty() != null) {
			criteria = criteria.add(Restrictions.eq("realEstateProperty.id",
					realEstateListing.getRealEstateProperty().getId()));
		}

		if (realEstateListing.getMaster() != null) {
			criteria = criteria.add(Restrictions.eq("master.id",
					realEstateListing.getMaster().getId()));
		}

		if (realEstateListing.getSubagent() != null) {
			criteria = criteria.add(Restrictions.eq("subagent.id",
					realEstateListing.getSubagent().getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (realEstateListing.getRealEstateBoard() != null) {
			realEstateBoardAction.setEntity(getEntity().getRealEstateBoard());
		}

		if (realEstateListing.getRealEstateProperty() != null) {
			realEstatePropertyAction.setEntity(getEntity()
					.getRealEstateProperty());
		}

		if (realEstateListing.getMaster() != null) {
			masterAction.setEntity(getEntity().getMaster());
		}

		if (realEstateListing.getSubagent() != null) {
			subagentAction.setEntity(getEntity().getSubagent());
		}

	}

	public void updateAssociations() {

	}

	protected List<Offer> listOffers;

	void initListOffers() {
		listOffers = new ArrayList<Offer>();
		if (getInstance().getOffers().isEmpty()) {

		} else
			listOffers.addAll(getInstance().getOffers());
	}

	public List<Offer> getListOffers() {
		if (listOffers == null || listOffers.isEmpty()) {
			initListOffers();
		}
		return listOffers;
	}

	public void setListOffers(List<Offer> listOffers) {
		this.listOffers = listOffers;
	}

	public void deleteOffers(int index) {
		listOffers.remove(index);
	}

	@Begin(join = true)
	public void addOffers() {
		Offer offers = new Offer();

		offers.setRealEstateListing(getInstance());

		listOffers.add(offers);
	}

	public void updateComposedAssociations() {

		if (listOffers != null) {
			getInstance().getOffers().clear();
			getInstance().getOffers().addAll(listOffers);
		}

	}

	public List<RealEstateListing> getEntityList() {
		if (realEstateListingRecordList == null) {
			findRecords();
		}
		return realEstateListingRecordList;
	}

}
