package com.hrb.tservices.web.action.department;

import com.hrb.tservices.domain.department.Partner;

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

public abstract class PartnerActionBase extends BaseAction<Partner>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private Partner partner;

	@In(create = true, value = "marketingMessageAction")
	com.hrb.tservices.web.action.message.MarketingMessageAction marketingMessageAction;

	@In(create = true, value = "appUserAction")
	com.hrb.tservices.web.action.users.AppUserAction appUserAction;

	@DataModel
	private List<Partner> partnerRecordList;

	public void setPartnerId(Long id) {
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
	public void setPartnerIdForModalDlg(Long id) {
		setId(id);
		clearLists();
		loadAssociations();
	}

	public void setMarketingMessageId(Long id) {

		if (id != null && id > 0)
			getInstance().setMarketingMessage(
					marketingMessageAction.loadFromId(id));

	}

	public Long getMarketingMessageId() {
		if (getInstance().getMarketingMessage() != null)
			return getInstance().getMarketingMessage().getId();
		return 0L;
	}

	public void setAppUserId(Long id) {

		if (id != null && id > 0)
			getInstance().setAppUser(appUserAction.loadFromId(id));

	}

	public Long getAppUserId() {
		if (getInstance().getAppUser() != null)
			return getInstance().getAppUser().getId();
		return 0L;
	}

	public Long getPartnerId() {
		return (Long) getId();
	}

	public Partner getEntity() {
		return partner;
	}

	//@Override
	public void setEntity(Partner t) {
		this.partner = t;
		loadAssociations();
	}

	public Partner getPartner() {
		return (Partner) getInstance();
	}

	@Override
	protected Partner createInstance() {
		Partner instance = super.createInstance();

		return instance;
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();

		com.hrb.tservices.domain.message.MarketingMessage marketingMessage = marketingMessageAction
				.getDefinedInstance();
		if (marketingMessage != null && isNew()) {
			getInstance().setMarketingMessage(marketingMessage);
		}

		com.hrb.tservices.domain.users.AppUser appUser = appUserAction
				.getDefinedInstance();
		if (appUser != null && isNew()) {
			getInstance().setAppUser(appUser);
		}

	}

	public boolean isWired() {
		return true;
	}

	public Partner getDefinedInstance() {
		return (Partner) (isIdDefined() ? getInstance() : null);
	}

	public void setPartner(Partner t) {
		this.partner = t;
		if (partner != null)
			setPartnerId(t.getId());
		loadAssociations();
	}

	@Override
	public Class<Partner> getEntityClass() {
		return Partner.class;
	}

	public com.hrb.tservices.domain.department.Partner findByUnqName(String name) {
		return executeSingleResultNamedQuery("partner.findByUnqName", name);
	}

	public com.hrb.tservices.domain.department.Partner findByUnqPartnerId(
			String partnerId) {
		return executeSingleResultNamedQuery("partner.findByUnqPartnerId",
				partnerId);
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	@Override
	public void addAssociations(Criteria criteria) {

		if (partner.getMarketingMessage() != null) {
			criteria = criteria.add(Restrictions.eq("marketingMessage.id",
					partner.getMarketingMessage().getId()));
		}

		if (partner.getAppUser() != null) {
			criteria = criteria.add(Restrictions.eq("appUser.id", partner
					.getAppUser().getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (partner.getMarketingMessage() != null) {
			marketingMessageAction.setInstance(getInstance()
					.getMarketingMessage());
		}

		if (partner.getAppUser() != null) {
			appUserAction.setInstance(getInstance().getAppUser());
		}

	}

	public void updateAssociations() {

	}

	public void updateComposedAssociations() {
	}

	public void clearLists() {

	}

	public Partner getCurrentLoggedInPartner() {
		String query = "Select e from Partner e where e.appUser.userName = ?1";
		return (Partner) executeSingleResultQuery(query, Identity.instance()
				.getCredentials().getUsername());
	}

	public String viewPartner() {
		load(currentEntityId);
		return "viewPartner";
	}

}
