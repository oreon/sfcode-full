package com.hrb.tservices.web.action.taxnews;

import com.hrb.tservices.domain.taxnews.TaxNewsTranslation;

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

public abstract class TaxNewsTranslationActionBase
		extends
			BaseAction<TaxNewsTranslation> implements java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private TaxNewsTranslation taxNewsTranslation;

	@In(create = true, value = "taxNewsAction")
	com.hrb.tservices.web.action.taxnews.TaxNewsAction taxNewsAction;

	@DataModel
	private List<TaxNewsTranslation> taxNewsTranslationRecordList;

	public void setTaxNewsTranslationId(Long id) {
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
	public void setTaxNewsTranslationIdForModalDlg(Long id) {
		setId(id);
		clearLists();
		loadAssociations();
	}

	public void setTaxNewsId(Long id) {

		if (id != null && id > 0)
			getInstance().setTaxNews(taxNewsAction.loadFromId(id));

	}

	public Long getTaxNewsId() {
		if (getInstance().getTaxNews() != null)
			return getInstance().getTaxNews().getId();
		return 0L;
	}

	public Long getTaxNewsTranslationId() {
		return (Long) getId();
	}

	public TaxNewsTranslation getEntity() {
		return taxNewsTranslation;
	}

	//@Override
	public void setEntity(TaxNewsTranslation t) {
		this.taxNewsTranslation = t;
		loadAssociations();
	}

	public TaxNewsTranslation getTaxNewsTranslation() {
		return (TaxNewsTranslation) getInstance();
	}

	@Override
	protected TaxNewsTranslation createInstance() {
		TaxNewsTranslation instance = super.createInstance();

		return instance;
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();

		com.hrb.tservices.domain.taxnews.TaxNews taxNews = taxNewsAction
				.getDefinedInstance();
		if (taxNews != null && isNew()) {
			getInstance().setTaxNews(taxNews);
		}

	}

	public boolean isWired() {
		return true;
	}

	public TaxNewsTranslation getDefinedInstance() {
		return (TaxNewsTranslation) (isIdDefined() ? getInstance() : null);
	}

	public void setTaxNewsTranslation(TaxNewsTranslation t) {
		this.taxNewsTranslation = t;
		if (taxNewsTranslation != null)
			setTaxNewsTranslationId(t.getId());
		loadAssociations();
	}

	@Override
	public Class<TaxNewsTranslation> getEntityClass() {
		return TaxNewsTranslation.class;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	@Override
	public void addAssociations(Criteria criteria) {

		if (taxNewsTranslation.getTaxNews() != null) {
			criteria = criteria.add(Restrictions.eq("taxNews.id",
					taxNewsTranslation.getTaxNews().getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (taxNewsTranslation.getTaxNews() != null) {
			taxNewsAction.setInstance(getInstance().getTaxNews());
		}

	}

	public void updateAssociations() {

	}

	public void updateComposedAssociations() {
	}

	public void clearLists() {

	}

	public String viewTaxNewsTranslation() {
		load(currentEntityId);
		return "viewTaxNewsTranslation";
	}

}
