package com.wc.jshopper.domain.action;

import com.wc.jshopper.domain.Category;

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

import com.wc.jshopper.domain.Category;

public class CategoryActionBase extends BaseAction<Category>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private Category category;

	@In(create = true, value = "categoryAction")
	com.wc.jshopper.domain.action.CategoryAction parentAction;

	@DataModel
	private List<Category> categoryRecordList;

	public void setCategoryId(Long id) {

		listSubactegories = new ArrayList<Category>();

		setId(id);
		loadAssociations();
	}

	public Long getCategoryId() {
		return (Long) getId();
	}

	@Factory("categoryRecordList")
	@Observer("archivedCategory")
	public void findRecords() {
		search();
	}

	public Category getEntity() {
		return category;
	}

	@Override
	public void setEntity(Category t) {
		this.category = t;
		loadAssociations();
	}

	public Category getCategory() {
		return getInstance();
	}

	@Override
	protected Category createInstance() {
		return new Category();
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();
		com.wc.jshopper.domain.Category parent = parentAction
				.getDefinedInstance();
		if (parent != null) {
			getInstance().setParent(parent);
		}

	}

	public boolean isWired() {
		return true;
	}

	public Category getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public void setCategory(Category t) {
		this.category = t;
		loadAssociations();
	}

	@Override
	public Class<Category> getEntityClass() {
		return Category.class;
	}

	@Override
	public void setEntityList(List<Category> list) {
		this.categoryRecordList = list;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	public void addAssoications(Criteria criteria) {

		if (category.getParent() != null) {
			criteria = criteria.add(Restrictions.eq("parent.id", category
					.getParent().getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (category.getParent() != null) {
			parentAction.setEntity(getEntity().getParent());
		}

	}

	public void updateAssociations() {

	}

	private List<Category> listSubactegories;

	void initListSubactegories() {
		listSubactegories = new ArrayList<Category>();
		if (getInstance().getSubactegories().isEmpty()) {

		} else
			listSubactegories.addAll(getInstance().getSubactegories());
	}

	public List<Category> getListSubactegories() {
		if (listSubactegories == null) {
			initListSubactegories();
		}
		return listSubactegories;
	}

	public void setListSubactegories(List<Category> listSubactegories) {
		this.listSubactegories = listSubactegories;
	}

	public void deleteSubactegories(Category subactegories) {
		listSubactegories.remove(subactegories);
	}

	@Begin(join = true)
	public void addSubactegories() {
		Category subactegories = new Category();

		subactegories.setParent(getInstance());

		listSubactegories.add(subactegories);
	}

	public void updateComposedAssociations() {

		getInstance().getSubactegories().clear();
		getInstance().getSubactegories().addAll(listSubactegories);

	}

	public List<Category> getEntityList() {
		if (categoryRecordList == null) {
			findRecords();
		}
		return categoryRecordList;
	}

}
