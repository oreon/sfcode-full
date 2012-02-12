package com.hrb.tservices.web.action.message;

import com.hrb.tservices.domain.message.MarketingMessage;

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

import com.hrb.tservices.domain.message.MessageTranslation;

public abstract class MarketingMessageActionBase
		extends
			BaseAction<MarketingMessage> implements java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private MarketingMessage marketingMessage;

	@DataModel
	private List<MarketingMessage> marketingMessageRecordList;

	public void setMarketingMessageId(Long id) {
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
	public void setMarketingMessageIdForModalDlg(Long id) {
		setId(id);
		clearLists();
		loadAssociations();
	}

	public Long getMarketingMessageId() {
		return (Long) getId();
	}

	public MarketingMessage getEntity() {
		return marketingMessage;
	}

	//@Override
	public void setEntity(MarketingMessage t) {
		this.marketingMessage = t;
		loadAssociations();
	}

	public MarketingMessage getMarketingMessage() {
		return (MarketingMessage) getInstance();
	}

	@Override
	protected MarketingMessage createInstance() {
		MarketingMessage instance = super.createInstance();

		return instance;
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

	public MarketingMessage getDefinedInstance() {
		return (MarketingMessage) (isIdDefined() ? getInstance() : null);
	}

	public void setMarketingMessage(MarketingMessage t) {
		this.marketingMessage = t;
		if (marketingMessage != null)
			setMarketingMessageId(t.getId());
		loadAssociations();
	}

	@Override
	public Class<MarketingMessage> getEntityClass() {
		return MarketingMessage.class;
	}

	public com.hrb.tservices.domain.message.MarketingMessage findByUnqMessageTitle(
			String messageTitle) {
		return executeSingleResultNamedQuery(
				"marketingMessage.findByUnqMessageTitle", messageTitle);
	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		initListMessageTranslations();

	}

	public void updateAssociations() {

	}

	protected List<com.hrb.tservices.domain.message.MessageTranslation> listMessageTranslations = new ArrayList<com.hrb.tservices.domain.message.MessageTranslation>();

	void initListMessageTranslations() {

		if (listMessageTranslations.isEmpty())
			listMessageTranslations.addAll(getInstance()
					.getMessageTranslations());

	}

	public List<com.hrb.tservices.domain.message.MessageTranslation> getListMessageTranslations() {

		prePopulateListMessageTranslations();
		return listMessageTranslations;
	}

	public void prePopulateListMessageTranslations() {
	}

	public void setListMessageTranslations(
			List<com.hrb.tservices.domain.message.MessageTranslation> listMessageTranslations) {
		this.listMessageTranslations = listMessageTranslations;
	}

	public void deleteMessageTranslations(int index) {
		listMessageTranslations.remove(index);
	}

	@Begin(join = true)
	public void addMessageTranslations() {
		initListMessageTranslations();
		MessageTranslation messageTranslations = new MessageTranslation();

		messageTranslations.setMarketingMessage(getInstance());

		getListMessageTranslations().add(messageTranslations);
	}

	public void updateComposedAssociations() {

		if (listMessageTranslations != null) {
			getInstance().getMessageTranslations().clear();
			getInstance().getMessageTranslations().addAll(
					listMessageTranslations);
		}
	}

	public void clearLists() {
		listMessageTranslations.clear();

	}

	public String viewMarketingMessage() {
		load(currentEntityId);
		return "viewMarketingMessage";
	}

}
