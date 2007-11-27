package com.publicfountain.domain.service;

import com.publicfountain.domain.Category;
import org.springframework.test.jpa.AbstractJpaTests;
import java.util.List;

import org.witchcraft.model.support.testing.TestDataFactory;
import org.witchcraft.model.support.springbeanhelpers.BeanHelper;

import java.text.SimpleDateFormat;

import javax.persistence.PersistenceException;
import org.hibernate.PropertyValueException;
import java.util.Date;

public class CategoryDaoTest extends AbstractJpaTests {

	protected Category categoryInstance = new Category();

	protected CategoryService categoryService;

	protected boolean bTest = true;

	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy.MM.dd HH:mm:ss z");

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	protected TestDataFactory categoryTestDataFactory = (TestDataFactory) BeanHelper
			.getBean("categoryTestDataFactory");

	@Override
	protected String[] getConfigLocations() {
		return new String[]{"classpath:/applicationContext.xml",
				"classpath:/testDataFactories.xml"};
	}

	@Override
	protected void runTest() throws Throwable {
		if (!bTest)
			return;
		super.runTest();
	}

	/**
	 * Do the setup before the test in this method
	 **/
	protected void onSetUpInTransaction() throws Exception {
		try {

			categoryInstance.setName("beta");

			TestDataFactory parentTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("categoryTestDataFactory");

			categoryService.save(categoryInstance);
		} catch (PersistenceException pe) {
			//if this instance can't be created due to back references e.g an orderItem needs an Order - 
			// - we will simply skip generated tests.
			if (pe.getCause() instanceof PropertyValueException
					&& pe.getMessage().contains("Backref")) {
				bTest = false;
			}
		} catch (Exception e) {
			fail(e.getMessage());
		}

	}

	//test saving a new record and updating an existing record;
	public void testSave() {

		try {
			Category category = new Category();

			try {

				category.setName("alpha");

				TestDataFactory parentTestDataFactory = (TestDataFactory) BeanHelper
						.getBean("categoryTestDataFactory");

			} catch (Exception ex) {
				ex.printStackTrace();
			}

			categoryService.save(category);
			assertNotNull(category.getId());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testEdit() {

		try {
			//test saving a new record and updating an existing record;
			Category category = (Category) categoryTestDataFactory
					.loadOneRecord();

			category.setName("Lavendar");

			categoryService.save(category);

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testCount() {
		assertTrue(categoryService.getCount() > 0);
	}

	public void testDelete() {
		//return false;
	}

	public void testLoad() {

		try {
			Category category = categoryService.load(categoryInstance.getId());
			assertNotNull(category.getId());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testSearchByExample() {
		try {
			List<Category> categorys = categoryService
					.searchByExample(categoryInstance);
			assertTrue(!categorys.isEmpty());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

}
