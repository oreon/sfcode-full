package com.hrb.tservices.web.action.message;

import com.hrb.tservices.domain.message.MessageTranslation;

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

public abstract class MessageTranslationActionBase
		extends
			BaseAction<MessageTranslation> implements java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private MessageTranslation messageTranslation;

	@In(create = true, value = "marketingMessageAction")
	com.hrb.tservices.web.action.message.MarketingMessageAction marketingMessageAction;

	@DataModel
	private List<MessageTranslation> messageTranslationRecordList;

	public void setMessageTranslationId(Long id) {
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
	public void setMessageTranslationIdForModalDlg(Long id) {
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

	public Long getMessageTranslationId() {
		return (Long) getId();
	}

	public MessageTranslation getEntity() {
		return messageTranslation;
	}

	//@Override
	public void setEntity(MessageTranslation t) {
		this.messageTranslation = t;
		loadAssociations();
	}

	public MessageTranslation getMessageTranslation() {
		return (MessageTranslation) getInstance();
	}

	@Override
	protected MessageTranslation createInstance() {
		MessageTranslation instance = super.createInstance();

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

	}

	public boolean isWired() {
		return true;
	}

	public MessageTranslation getDefinedInstance() {
		return (MessageTranslation) (isIdDefined() ? getInstance() : null);
	}

	public void setMessageTranslation(MessageTranslation t) {
		this.messageTranslation = t;
		if (messageTranslation != null)
			setMessageTranslationId(t.getId());
		loadAssociations();
	}

	@Override
	public Class<MessageTranslation> getEntityClass() {
		return MessageTranslation.class;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	@Override
	public void addAssociations(Criteria criteria) {

		if (messageTranslation.getMarketingMessage() != null) {
			criteria = criteria.add(Restrictions.eq("marketingMessage.id",
					messageTranslation.getMarketingMessage().getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (messageTranslation.getMarketingMessage() != null) {
			marketingMessageAction.setInstance(getInstance()
					.getMarketingMessage());
		}

	}

	public void updateAssociations() {

	}

	public void updateComposedAssociations() {
	}

	public void clearLists() {

	}

	public String viewMessageTranslation() {
		load(currentEntityId);
		return "viewMessageTranslation";
	}

}
