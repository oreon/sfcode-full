package com.publicfountain.domain.web.jsf;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.faces.component.UIParameter;
import javax.faces.event.ActionEvent;

import org.richfaces.component.UITree;
import org.richfaces.event.NodeSelectedEvent;
import org.richfaces.model.TreeNode;
import org.richfaces.model.TreeNodeImpl;
import org.witchcraft.model.jsf.BaseBackingBean;
import org.witchcraft.model.support.service.BaseService;

import com.publicfountain.domain.Category;
import com.publicfountain.domain.service.CategoryService;

public class CategoryBackingBean extends BaseBackingBean<Category> {

	private Category category = new Category();

	private CategoryService categoryService;
	
	TreeNode root = null;

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

	public String search() {
		action = SEARCH;
		return "search";
	}

	/** Returns a success string upon selection of an entity in model - majority of work is done
	 * in the actionListener selectEntity
	 * @return - "success" if everthing goes fine
	 * @see - 
	 */
	public String select() {
		return "edit";
	}

	/** This action Listener Method is called when a row is clicked in the dataTable
	 *  
	 * @param event contains the database id of the row being selected 
	 */
	public void selectEntity(ActionEvent event) {

		UIParameter component = (UIParameter) event.getComponent()
				.findComponent("editId");

		// parse the value of the UIParameter component    	 
		long id = Long.parseLong(component.getValue().toString());

		category = categoryService.load(id);
	}
	
	public TreeNode getTree() {
		if(root == null){
			root = new TreeNodeImpl();
			List<Category> categories = categoryService.findTopLevelCategories();
			addCategories(categories, root);
		}
		return root;
	}

	private void addCategories(Collection<Category> categories, TreeNode currentNode) {
		int counter = 1;
		for (Category category : categories) {
			System.out.println(category.getName());
			
			TreeNodeImpl nodeImpl = new TreeNodeImpl();

			nodeImpl.setData(category.getName());
			currentNode.addChild(category, nodeImpl);
			
			Set<Category> subcategories = category.getSubcategories();
			addCategories(subcategories, nodeImpl);
			counter++;
		}
	}
	
	public void processSelection(NodeSelectedEvent event) {
        UITree tree = (UITree) event.getComponent();
        //nodeTitle = (String) tree.getRowData();
    }

}
