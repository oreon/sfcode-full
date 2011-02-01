package com.nas.recovery.web.action.domain;

import org.wc.mytapovan.domain.Sponsoror;

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

import org.wc.mytapovan.domain.Sponsorship;

public abstract class SponsororActionBase
		extends
			com.nas.recovery.web.action.domain.PersonAction<Sponsoror>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private Sponsoror sponsoror;

	@In(create = true, value = "sponsorshipAction")
	com.nas.recovery.web.action.domain.SponsorshipAction sponsorshipsAction;

	@DataModel
	private List<Sponsoror> sponsororRecordList;

	public void setSponsororId(Long id) {
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
	public void setSponsororIdForModalDlg(Long id) {
		setId(id);
		clearLists();
		loadAssociations();
	}

	public Long getSponsororId() {
		return (Long) getId();
	}

	public Sponsoror getEntity() {
		return sponsoror;
	}

	//@Override
	public void setEntity(Sponsoror t) {
		this.sponsoror = t;
		loadAssociations();
	}

	public Sponsoror getSponsoror() {
		return (Sponsoror) getInstance();
	}

	@Override
	protected Sponsoror createInstance() {
		return new Sponsoror();
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();

	}

	public boolean isWired() {
		return true;
	}

	public Sponsoror getDefinedInstance() {
		return (Sponsoror) (isIdDefined() ? getInstance() : null);
	}

	public void setSponsoror(Sponsoror t) {
		this.sponsoror = t;
		loadAssociations();
	}

	@Override
	public Class<Sponsoror> getEntityClass() {
		return Sponsoror.class;
	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		initListSponsorships();

	}

	public void updateAssociations() {

		org.wc.mytapovan.domain.Sponsorship sponsorships = (org.wc.mytapovan.domain.Sponsorship) org.jboss.seam.Component
				.getInstance("sponsorship");
		sponsorships.setSponsoror(sponsoror);
		events.raiseTransactionSuccessEvent("archivedSponsorship");

	}

	protected List<org.wc.mytapovan.domain.Sponsorship> listSponsorships = new ArrayList<org.wc.mytapovan.domain.Sponsorship>();

	void initListSponsorships() {

		if (listSponsorships.isEmpty())
			listSponsorships.addAll(getInstance().getSponsorships());

	}

	public List<org.wc.mytapovan.domain.Sponsorship> getListSponsorships() {

		prePopulateListSponsorships();
		return listSponsorships;
	}

	public void prePopulateListSponsorships() {
	}

	public void setListSponsorships(
			List<org.wc.mytapovan.domain.Sponsorship> listSponsorships) {
		this.listSponsorships = listSponsorships;
	}

	public void deleteSponsorships(int index) {
		listSponsorships.remove(index);
	}

	@Begin(join = true)
	public void addSponsorships() {
		initListSponsorships();
		Sponsorship sponsorships = new Sponsorship();

		sponsorships.setSponsoror(getInstance());

		getListSponsorships().add(sponsorships);
	}

	public void updateComposedAssociations() {

		if (listSponsorships != null) {
			getInstance().getSponsorships().clear();
			getInstance().getSponsorships().addAll(listSponsorships);
		}
	}

	public void clearLists() {
		listSponsorships.clear();

	}

}
