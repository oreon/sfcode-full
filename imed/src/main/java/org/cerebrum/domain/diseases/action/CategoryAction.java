package org.cerebrum.domain.diseases.action;

import org.cerebrum.domain.diseases.Category;
import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import org.apache.commons.lang.StringUtils;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.log.Log;

import org.witchcraft.seam.action.BaseAction;

import org.cerebrum.domain.diseases.Category;

@Scope(ScopeType.CONVERSATION)
@Name("categoryAction")
public class CategoryAction extends BaseAction<Category>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private Category category;

	@DataModel
	private List<Category> categoryList;

	@Factory("categoryList")
	public void findRecords() {
		categoryList = entityManager
				.createQuery(
						"select category from Category category order by category.id desc")
				.getResultList();
	}

	public Category getEntity() {
		return category;
	}

	@Override
	public void setEntity(Category t) {
		this.category = t;
	}

	@Override
	public void setEntityList(List<Category> list) {
		this.categoryList = list;
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

	private List<Category> listChildren;

	void initListChildren() {
		listChildren = new ArrayList<Category>();
		if (category.getChildren().isEmpty()) {

		} else
			listChildren.addAll(category.getChildren());
	}

	public List<Category> getListChildren() {
		if (listChildren == null) {
			initListChildren();
		}
		return listChildren;
	}

	public void setListChildren(List<Category> listChildren) {
		this.listChildren = listChildren;
	}

	public void deleteChildren(Category children) {
		listChildren.remove(children);
	}

	@Begin(join = true)
	public void addChildren() {
		Category children = new Category();

		children.setParent(category);

		listChildren.add(children);
	}

	public void updateComposedAssociations() {

		category.getChildren().clear();
		category.getChildren().addAll(listChildren);

	}

}
