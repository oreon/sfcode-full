package com.publicfountain.domain.service;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.richfaces.model.TreeNode;
import org.richfaces.model.TreeNodeImpl;
import org.springframework.test.jpa.AbstractJpaTests;
import org.witchcraft.model.support.springbeanhelpers.BeanHelper;
import org.witchcraft.model.support.testing.TestDataFactory;

import com.publicfountain.domain.Category;

public class TreeCategoryDaoTest extends AbstractJpaTests {
	
	protected CategoryService categoryService;
	
	TreeNode root = new TreeNodeImpl();

	protected boolean bTest = true;

	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy.MM.dd HH:mm:ss z");

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@Override
	protected String[] getConfigLocations() {
		return new String[] { "classpath:/applicationContext.xml",
				"classpath:/testDataFactories.xml" };
	}

	@Override
	protected void runTest() throws Throwable {
		if (!bTest)
			return;
		super.runTest();
	}

	public void testTree() {
		List<Category> categories = categoryService.findTopLevelCategories();
		printCategories(categories, root);

	}

	private void printCategories(Collection<Category> categories, TreeNode currentNode) {
		int counter = 1;
		for (Category category : categories) {
			System.out.println(category.getName());
			
			TreeNodeImpl nodeImpl = new TreeNodeImpl();

			nodeImpl.setData(category.getName());
			currentNode.addChild(counter, nodeImpl);
			
			Set<Category> subcategories = category.getSubcategories();
			printCategories(subcategories, nodeImpl);
			counter++;
		}
	}

}
