package com.oreon.kgauge.web.jsf;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import org.richfaces.component.UITree;
import org.richfaces.event.NodeSelectedEvent;
import org.richfaces.model.TreeNode;
import org.richfaces.model.TreeNodeImpl;
import org.witchcraft.model.jsf.BaseBackingBean;
import org.witchcraft.model.support.Range;
import org.witchcraft.model.support.service.BaseService;

import com.oreon.kgauge.domain.Category;
import com.oreon.kgauge.service.CategoryService;

public class CategoryBackingBean extends BaseBackingBean<Category> {

	private Category category = new Category();

	private CategoryService categoryService;

	private TreeNode root = null;

	private Range<Date> rangeCreationDate = new Range<Date>("dateCreated");

	private List<Category> listSubCategories = new ArrayList<Category>();
	
	private DataModel children;

	public static final int INITIAL_RECORDS = 3;

	
	
	public DataModel getAllChildren() {
		//log.debug("getAllTasks");
	
		children = new ListDataModel();
		List<Category> lst = new ArrayList<Category>();
		listSubCategories.addAll(category.getSubcategories());
		children.setWrappedData(listSubCategories);
		
		return children;
	}


	public List<Category> getListSubCategories() {
		if(listSubCategories.isEmpty()){
			//loadSubcategories();
		}
		return listSubCategories;
	}

	public void setListSubCategories(List<Category> listSubCategories) {
		this.listSubCategories = listSubCategories;
	}
	
	/*
	private void loadSubcategories(){
		if (category != null) {
			listSubCategories.addAll(category.getSubcategories());
		}
		int sizeOfExistingElements = listSubCategories.size();
		//add a few spare rows - lets say parent has 3 children and we need to show 5 rows - then add
		//2 rows with 2 new parents
		for (int i = 0; i < INITIAL_RECORDS - sizeOfExistingElements; i++) {
			listSubCategories.add(new Category());
		}
	}*/ 
	


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

	@Override
	protected List<Range> getRangeList() {

		List<Range> listRanges = super.getRangeList();

		listRanges.add(rangeCreationDate);
		return listRanges;
	}

	/**
	 * This action Listener Method is called when a row is clicked in the
	 * dataTable
	 * 
	 * @param event
	 *            contains the database id of the row being selected
	 */
	public void selectEntity(ActionEvent actionEvent) {
		FacesContext ctx = FacesContext.getCurrentInstance();
		String idStr = (String) ctx.getExternalContext()
				.getRequestParameterMap().get("id");
		long id = Long.parseLong(idStr);
		category = categoryService.load(id);
		if (actionEvent.getComponent().getId() == "deleteId") {
			getBaseService().delete(category);
		}
		/*
		 * UIParameter component = (UIParameter)
		 * actionEvent.getComponent().findComponent("editId"); // parse the
		 * value of the UIParameter component long id =
		 * Long.parseLong(component.getValue().toString());
		 */
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
		// nodeTitle = (String) tree.getRowData();
	}

	@Override
	public String update() {
		category.getSubcategories().clear();

		for (Category subcategory : listSubCategories) {
			subcategory.setParent(category);
		}
		
		category.getSubcategories().addAll((Collection<Category>) children.getWrappedData());
		return super.update();
	}

	public DataModel getChildren() {
		return children;
	}

	public void setChildren(DataModel children) {
		this.children = children;
	}

}
