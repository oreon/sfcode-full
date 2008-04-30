package com.oreon.kgauge.web.jsf;

import javax.faces.event.ActionEvent;
import javax.faces.context.FacesContext;

import org.witchcraft.model.jsf.BaseBackingBean;
import org.witchcraft.model.support.service.BaseService;
import org.apache.commons.lang.StringUtils;

import com.oreon.kgauge.domain.Category;
import com.oreon.kgauge.service.CategoryService;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
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

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public Category getCategory() {
		return category;
	}

	public void set(Category category) {
		this.category = category;
	}

	@SuppressWarnings("unchecked")
	public BaseService<Category> getBaseService() {
		return categoryService;
	}

	public Category getEntity() {
		return getCategory();
	}

	public void reset() {
		category = new Category();

		listSubcategoriess.clear();

	}

	@Override
	protected List<Range> getRangeList() {

		List<Range> listRanges = super.getRangeList();

		listRanges.add(rangeCreationDate);
		return listRanges;
	}

	protected void reloadFromId(long id) {
		category = categoryService.load(id);
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

	@Override
	public String update() {

		addSubcategoriessToCategory();

		return super.update();
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
		category.getSubcategories().clear();
		List<Category> listValidSubcategoriess = new ArrayList<Category>();

		for (Category subcategories : listSubcategoriess) {
			if (StringUtils.isNotEmpty(subcategories.getName()

			)) {
				subcategories.setParent(category);
				listValidSubcategoriess.add(subcategories);
			}
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
