package com.oreon.kgauge.web.jsf;

import javax.faces.event.ActionEvent;
import javax.faces.context.FacesContext;

import org.witchcraft.model.jsf.BaseBackingBean;
import org.witchcraft.model.support.service.BaseService;
import org.apache.commons.lang.StringUtils;

import java.util.Set;
import org.apache.commons.collections.ListUtils;

import com.oreon.kgauge.domain.Category;
import com.oreon.kgauge.service.CategoryService;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import org.witchcraft.model.support.Range;

import com.oreon.kgauge.domain.Category;

import java.util.Collection;
import org.richfaces.component.UITree;
import org.richfaces.event.NodeSelectedEvent;
import org.richfaces.model.TreeNode;
import org.richfaces.model.TreeNodeImpl;
import org.richfaces.component.html.HtmlMenuItem;

/**
 * This is generated code - to edit code or override methods use - Category class
 * @author witchCraft Code Generator
 * WARNING  - DO NOT EDIT - CHANGES WILL BE OVERWRITTEN
 */

public class CategoryBackingBeanBase extends BaseBackingBean<Category> {

	protected Category category = new Category();

	protected CategoryService categoryService;

	protected TreeNode root = null;

	private List<Category> listSubcategoriess = new ArrayList<Category>();

	private Range<Date> rangeCreationDate = new Range<Date>("dateCreated");

	public Range<Date> getRangeCreationDate() {
		return rangeCreationDate;
	}

	public void setRangeCreationDate(Range<Date> rangeCreationDate) {
		this.rangeCreationDate = rangeCreationDate;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public CategoryService getCategoryService() {
		return this.categoryService;
	}

	@SuppressWarnings("unchecked")
	public BaseService<Category> getBaseService() {
		return categoryService;
	}

	public Category getEntity() {
		return getCategory();
	}

	/**
	 * Any initializations of the member entity should be done in this method - 
	 * It will be called before add new action
	 */
	public void initForAddNew() {

	}

	public void reset() {
		category = new Category();
		resetRanges();

		listSubcategoriess.clear();

	}

	@Override
	protected List<Range> getRangeList() {

		List<Range> listRanges = super.getRangeList();

		listRanges.add(rangeCreationDate);
		return listRanges;
	}

	protected void reloadFromId(long id) {
		if (id != 0)
			category = categoryService.load(id);

	}

	@Override
	public String update() {

		addSubcategoriessToCategory();

		return super.update();
	}

	public TreeNode getTree() {
		if (root == null) {
			root = new TreeNodeImpl();
			Collection<Category> categorys = categoryService
					.findTopLevelElements();
			addChildren(categorys, root);
			return root;
		}
		return root;
	}

	private void addChildren(Collection<Category> categorys,
			TreeNode currentNode) {

		for (Category currentEntity : categorys) {

			TreeNodeImpl nodeImpl = new TreeNodeImpl();

			nodeImpl.setData(currentEntity.getDisplayName());
			currentNode.addChild(currentEntity, nodeImpl);

			Collection<Category> childEntites = currentEntity
					.getSubcategories();
			addChildren(childEntites, nodeImpl);
		}
	}

	public void processSelection(NodeSelectedEvent event) {
		UITree tree = (UITree) event.getComponent();
		//nodeTitle = (String) tree.getRowData();
	}

	public List<Category> getListSubcategoriess() {
		if (listSubcategoriess.isEmpty())
			loadSubcategoriess();

		return listSubcategoriess;
	}

	public void setListSubcategoriess(List<Category> listSubcategoriess) {
		this.listSubcategoriess = listSubcategoriess;
	}

	private void loadSubcategoriess() {
		listSubcategoriess.clear();
		if (category != null) {
			listSubcategoriess.addAll(category.getSubcategories());
		}
		int sizeOfExistingElements = listSubcategoriess.size();
		// add a few spare rows - lets say parent has 3 children and we need to
		// show 5 rows - then add 2 rows with 2 new parents
		for (int i = 0; i < INITIAL_RECORDS - sizeOfExistingElements; i++) {
			listSubcategoriess.add(new Category());
		}
	}

	private void addSubcategoriessToCategory() {
		category = categoryService.save(category);//To prevent lazy initialization exception
		category.getSubcategories().clear();
		List<Category> listValidSubcategoriess = new ArrayList<Category>();

		for (Category subcategories : listSubcategoriess) {

			subcategories.setParent(category);
			listValidSubcategoriess.add(subcategories);

		}

		category.getSubcategories().addAll(listValidSubcategoriess);
	}

	/**
	 * @param actionEvent
	 */
	public void addNewSubcategoriesRow(ActionEvent actionEvent) {
		listSubcategoriess.add(new Category());
	}

	/**
	 * @param actionEvent
	 */
	public void deleteSubcategoriesRow(ActionEvent actionEvent) {
		String rowIndex = (String) FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap().get(
						"deleteRowIndex");

		int index = Integer.parseInt(rowIndex);
		Category category = listSubcategoriess.get(index);
		listSubcategoriess.remove(index);

		/*
			TaskService taskService = (TaskService) BeanHelper
					.getBean("taskService");

			if (task.getId() != null && task.getId() > 0) {
				taskService.delete(task);
			}*/
	}

}
