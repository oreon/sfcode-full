package com.hrb.tservices.web.action.taxnews;

import com.hrb.tservices.domain.taxnews.NewsCategory;

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

public abstract class NewsCategoryActionBase extends BaseAction<NewsCategory>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private NewsCategory newsCategory;

	@DataModel
	private List<NewsCategory> newsCategoryRecordList;

	public void setNewsCategoryId(Long id) {
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
	public void setNewsCategoryIdForModalDlg(Long id) {
		setId(id);
		clearLists();
		loadAssociations();
	}

	public Long getNewsCategoryId() {
		return (Long) getId();
	}

	public NewsCategory getEntity() {
		return newsCategory;
	}

	//@Override
	public void setEntity(NewsCategory t) {
		this.newsCategory = t;
		loadAssociations();
	}

	public NewsCategory getNewsCategory() {
		return (NewsCategory) getInstance();
	}

	@Override
	protected NewsCategory createInstance() {
		NewsCategory instance = super.createInstance();

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

	public NewsCategory getDefinedInstance() {
		return (NewsCategory) (isIdDefined() ? getInstance() : null);
	}

	public void setNewsCategory(NewsCategory t) {
		this.newsCategory = t;
		if (newsCategory != null)
			setNewsCategoryId(t.getId());
		loadAssociations();
	}

	@Override
	public Class<NewsCategory> getEntityClass() {
		return NewsCategory.class;
	}

	public com.hrb.tservices.domain.taxnews.NewsCategory findByUnqName(
			String name) {
		return executeSingleResultNamedQuery("newsCategory.findByUnqName", name);
	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

	}

	public void updateAssociations() {

	}

	public void updateComposedAssociations() {
	}

	public void clearLists() {

	}

	public String viewNewsCategory() {
		load(currentEntityId);
		return "viewNewsCategory";
	}

}
