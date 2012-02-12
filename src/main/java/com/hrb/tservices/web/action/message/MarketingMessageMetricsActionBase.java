package com.hrb.tservices.web.action.message;

import com.hrb.tservices.domain.message.MarketingMessageMetrics;

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

public abstract class MarketingMessageMetricsActionBase
		extends
			com.hrb.tservices.web.action.metrics.BaseMetricsAction<MarketingMessageMetrics>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private MarketingMessageMetrics marketingMessageMetrics;

	@In(create = true, value = "messageTranslationAction")
	com.hrb.tservices.web.action.message.MessageTranslationAction messageTranslationAction;

	@DataModel
	private List<MarketingMessageMetrics> marketingMessageMetricsRecordList;

	public void setMarketingMessageMetricsId(Long id) {
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
	public void setMarketingMessageMetricsIdForModalDlg(Long id) {
		setId(id);
		clearLists();
		loadAssociations();
	}

	public void setMessageTranslationId(Long id) {

		if (id != null && id > 0)
			getInstance().setMessageTranslation(
					messageTranslationAction.loadFromId(id));

	}

	public Long getMessageTranslationId() {
		if (getInstance().getMessageTranslation() != null)
			return getInstance().getMessageTranslation().getId();
		return 0L;
	}

	public Long getMarketingMessageMetricsId() {
		return (Long) getId();
	}

	public MarketingMessageMetrics getEntity() {
		return marketingMessageMetrics;
	}

	//@Override
	public void setEntity(MarketingMessageMetrics t) {
		this.marketingMessageMetrics = t;
		loadAssociations();
	}

	public MarketingMessageMetrics getMarketingMessageMetrics() {
		return (MarketingMessageMetrics) getInstance();
	}

	@Override
	protected MarketingMessageMetrics createInstance() {
		MarketingMessageMetrics instance = super.createInstance();

		return instance;
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();

		com.hrb.tservices.domain.message.MessageTranslation messageTranslation = messageTranslationAction
				.getDefinedInstance();
		if (messageTranslation != null && isNew()) {
			getInstance().setMessageTranslation(messageTranslation);
		}

	}

	public boolean isWired() {
		return true;
	}

	public MarketingMessageMetrics getDefinedInstance() {
		return (MarketingMessageMetrics) (isIdDefined() ? getInstance() : null);
	}

	public void setMarketingMessageMetrics(MarketingMessageMetrics t) {
		this.marketingMessageMetrics = t;
		if (marketingMessageMetrics != null)
			setMarketingMessageMetricsId(t.getId());
		loadAssociations();
	}

	@Override
	public Class<MarketingMessageMetrics> getEntityClass() {
		return MarketingMessageMetrics.class;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	@Override
	public void addAssociations(Criteria criteria) {

		if (marketingMessageMetrics.getMessageTranslation() != null) {
			criteria = criteria.add(Restrictions.eq("messageTranslation.id",
					marketingMessageMetrics.getMessageTranslation().getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (marketingMessageMetrics.getMessageTranslation() != null) {
			messageTranslationAction.setInstance(getInstance()
					.getMessageTranslation());
		}

	}

	public void updateAssociations() {

	}

	public void updateComposedAssociations() {
	}

	public void clearLists() {

	}

	public String viewMarketingMessageMetrics() {
		load(currentEntityId);
		return "viewMarketingMessageMetrics";
	}

}
