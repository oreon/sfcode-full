package com.hrb.tservices.web.action.taxnews;

import com.hrb.tservices.domain.taxnews.TaxNews;

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

import com.hrb.tservices.domain.taxnews.TaxNewsTranslation;

public abstract class TaxNewsActionBase extends BaseAction<TaxNews>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private TaxNews taxNews;

	@In(create = true, value = "newsCategoryAction")
	com.hrb.tservices.web.action.taxnews.NewsCategoryAction newsCategoryAction;

	@DataModel
	private List<TaxNews> taxNewsRecordList;

	public void setTaxNewsId(Long id) {
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
	public void setTaxNewsIdForModalDlg(Long id) {
		setId(id);
		clearLists();
		loadAssociations();
	}

	public void setNewsCategoryId(Long id) {

		if (id != null && id > 0)
			getInstance().setNewsCategory(newsCategoryAction.loadFromId(id));

	}

	public Long getNewsCategoryId() {
		if (getInstance().getNewsCategory() != null)
			return getInstance().getNewsCategory().getId();
		return 0L;
	}

	public Long getTaxNewsId() {
		return (Long) getId();
	}

	public TaxNews getEntity() {
		return taxNews;
	}

	//@Override
	public void setEntity(TaxNews t) {
		this.taxNews = t;
		loadAssociations();
	}

	public TaxNews getTaxNews() {
		return (TaxNews) getInstance();
	}

	@Override
	protected TaxNews createInstance() {
		TaxNews instance = super.createInstance();

		return instance;
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();

		com.hrb.tservices.domain.taxnews.NewsCategory newsCategory = newsCategoryAction
				.getDefinedInstance();
		if (newsCategory != null && isNew()) {
			getInstance().setNewsCategory(newsCategory);
		}

	}

	public boolean isWired() {
		return true;
	}

	public TaxNews getDefinedInstance() {
		return (TaxNews) (isIdDefined() ? getInstance() : null);
	}

	public void setTaxNews(TaxNews t) {
		this.taxNews = t;
		if (taxNews != null)
			setTaxNewsId(t.getId());
		loadAssociations();
	}

	@Override
	public Class<TaxNews> getEntityClass() {
		return TaxNews.class;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	@Override
	public void addAssociations(Criteria criteria) {

		if (taxNews.getNewsCategory() != null) {
			criteria = criteria.add(Restrictions.eq("newsCategory.id", taxNews
					.getNewsCategory().getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (taxNews.getNewsCategory() != null) {
			newsCategoryAction.setInstance(getInstance().getNewsCategory());
		}

		initListTaxNewsTranslations();

	}

	public void updateAssociations() {

	}

	protected List<com.hrb.tservices.domain.taxnews.TaxNewsTranslation> listTaxNewsTranslations = new ArrayList<com.hrb.tservices.domain.taxnews.TaxNewsTranslation>();

	void initListTaxNewsTranslations() {

		if (listTaxNewsTranslations.isEmpty())
			listTaxNewsTranslations.addAll(getInstance()
					.getTaxNewsTranslations());

	}

	public List<com.hrb.tservices.domain.taxnews.TaxNewsTranslation> getListTaxNewsTranslations() {

		prePopulateListTaxNewsTranslations();
		return listTaxNewsTranslations;
	}

	public void prePopulateListTaxNewsTranslations() {
	}

	public void setListTaxNewsTranslations(
			List<com.hrb.tservices.domain.taxnews.TaxNewsTranslation> listTaxNewsTranslations) {
		this.listTaxNewsTranslations = listTaxNewsTranslations;
	}

	public void deleteTaxNewsTranslations(int index) {
		listTaxNewsTranslations.remove(index);
	}

	@Begin(join = true)
	public void addTaxNewsTranslations() {
		initListTaxNewsTranslations();
		TaxNewsTranslation taxNewsTranslations = new TaxNewsTranslation();

		taxNewsTranslations.setTaxNews(getInstance());

		getListTaxNewsTranslations().add(taxNewsTranslations);
	}

	public void updateComposedAssociations() {

		if (listTaxNewsTranslations != null) {
			getInstance().getTaxNewsTranslations().clear();
			getInstance().getTaxNewsTranslations().addAll(
					listTaxNewsTranslations);
		}
	}

	public void clearLists() {
		listTaxNewsTranslations.clear();

	}

	public String viewTaxNews() {
		load(currentEntityId);
		return "viewTaxNews";
	}

}
