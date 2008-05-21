package com.oreon.kgauge.domain;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import org.witchcraft.model.support.springbeanhelpers.BeanHelper;
import org.witchcraft.model.support.testing.AbstractTestDataFactory;

import org.witchcraft.model.support.testing.TestDataFactory;
import org.witchcraft.model.support.errorhandling.BusinessException;
import org.witchcraft.model.randomgen.RandomValueGeneratorFactory;

import org.springframework.transaction.annotation.Transactional;
import org.apache.log4j.Logger;

import com.oreon.kgauge.service.CategoryService;

@Transactional
public class CategoryTestDataFactory extends AbstractTestDataFactory<Category> {

	private List<Category> categorys = new ArrayList<Category>();

	private static final Logger logger = Logger
			.getLogger(CategoryTestDataFactory.class);

	private static int RECORDS_TO_CREATE = 30;

	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy.MM.dd HH:mm:ss z");

	CategoryService categoryService;

	public CategoryService getCategoryService() {
		return categoryService;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public void register(Category category) {
		categorys.add(category);
	}

	public Category createCategoryJava() {
		Category category = new Category();

		category.setName("Java");

		TestDataFactory parentTestDataFactory = (TestDataFactory) BeanHelper
				.getBean("categoryTestDataFactory");

		register(category);
		return category;
	}

	public Category createCategoryDotNet() {
		Category category = new Category();

		category.setName("DotNet");

		TestDataFactory parentTestDataFactory = (TestDataFactory) BeanHelper
				.getBean("categoryTestDataFactory");

		register(category);
		return category;
	}

	public Category createCategoryPHP() {
		Category category = new Category();

		category.setName("PHP");

		TestDataFactory parentTestDataFactory = (TestDataFactory) BeanHelper
				.getBean("categoryTestDataFactory");

		register(category);
		return category;
	}

	public Category createCategoryXML() {
		Category category = new Category();

		category.setName("XML");

		TestDataFactory parentTestDataFactory = (TestDataFactory) BeanHelper
				.getBean("categoryTestDataFactory");

		register(category);
		return category;
	}

	public Category loadOneRecord() {
		List<Category> categorys = categoryService.loadAll();

		if (categorys.isEmpty()) {
			persistAll();
			categorys = categoryService.loadAll();
		}

		return categorys.get(new Random().nextInt(categorys.size()));
	}

	public List<Category> getAllAsList() {

		if (categorys.isEmpty()) {

			createCategoryJava();

			createCategoryDotNet();

			createCategoryPHP();

			createCategoryXML();

		}

		return categorys;
	}

	public void persistAll() {
		if (!isPersistable() || alreadyPersisted)
			return;

		getAllAsList();

		for (Category category : categorys) {
			try {
				categoryService.save(category);
			} catch (BusinessException be) {
				logger.warn(" Category " + category.getDisplayName()
						+ "couldn't be saved " + be.getMessage());
			}
		}

		alreadyPersisted = true;
	}

	/** Execute this method to manually generate additional orders
	 * @param args
	 */
	public static void main(String args[]) {

		TestDataFactory placedOrderTestDataFactory = (TestDataFactory) BeanHelper
				.getBean("placedOrderTestDataFactory");

		placedOrderTestDataFactory.createAndSaveRecords(RECORDS_TO_CREATE);
	}

	public void createAndSaveRecords(int recordsTocreate) {
		for (int i = 0; i < recordsTocreate; i++) {
			Category category = createRandomCategory();
			categoryService.save(category);
		}
	}

	public Category createRandomCategory() {
		Category category = new Category();

		category.setName((String) RandomValueGeneratorFactory
				.createInstance("String"));

		TestDataFactory parentTestDataFactory = (TestDataFactory) BeanHelper
				.getBean("categoryTestDataFactory");

		return category;
	}

}
