package com.oreon.cerebrum.drugs;

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

import com.oreon.cerebrum.service.CategoryService;

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

	public Category createCategoryOne() {
		Category category = new Category();

		try {

			category.setCode("delta19240");
			category.setDescription("Mark");

			TestDataFactory parentTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("categoryTestDataFactory");

			register(category);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return category;
	}

	public Category createCategoryTwo() {
		Category category = new Category();

		try {

			category.setCode("pi53982");
			category.setDescription("epsilon");

			TestDataFactory parentTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("categoryTestDataFactory");

			register(category);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return category;
	}

	public Category createCategoryThree() {
		Category category = new Category();

		try {

			category.setCode("beta97109");
			category.setDescription("Mark");

			TestDataFactory parentTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("categoryTestDataFactory");

			register(category);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return category;
	}

	public Category createCategoryFour() {
		Category category = new Category();

		try {

			category.setCode("Malissa87018");
			category.setDescription("zeta");

			TestDataFactory parentTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("categoryTestDataFactory");

			register(category);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return category;
	}

	public Category createCategoryFive() {
		Category category = new Category();

		try {

			category.setCode("Lavendar48122");
			category.setDescription("Wilson");

			TestDataFactory parentTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("categoryTestDataFactory");

			register(category);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

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

			createCategoryOne();
			createCategoryTwo();
			createCategoryThree();
			createCategoryFour();
			createCategoryFive();

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

		category.setCode((String) RandomValueGeneratorFactory
				.createInstance("String"));
		category.setDescription((String) RandomValueGeneratorFactory
				.createInstance("String"));

		TestDataFactory parentTestDataFactory = (TestDataFactory) BeanHelper
				.getBean("categoryTestDataFactory");

		return category;
	}

}
