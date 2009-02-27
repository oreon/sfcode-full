package com.oreon.cerebrum.service;

import com.oreon.cerebrum.drugs.Category;
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

			categoryInstance.setCode("zeta8527");
			categoryInstance.setDescription("Eric");

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

				category.setCode("theta35508");
				category.setDescription("zeta");

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

			category.setCode("alpha63837");
			category.setDescription("Malissa");

			categoryService.save(category);

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testCount() {
		assertTrue(categoryService.getCount() > 0);
	}

	//count the number of records - add one delete it - check count is same after delete
	/*
	public void testDelete() {
									
		try{
			long count,newCount,diff=0;			
			count=categoryService.getCount();
			Category category = (Category)categoryTestDataFactory.loadOneRecord();					
			categoryService.delete(category);
			newCount=categoryService.getCount();
			diff=count - newCount;
			assertEquals(diff, 1);
		}catch(Exception e){
			fail(e.getMessage());
		}
	}*/

	public void testLoad() {

		try {
			Category category = categoryService.load(categoryInstance.getId());
			assertNotNull(category.getId());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testFindByCode() {
		if (!bTest)
			return;

		assertNotNull("Couldn't find a Category with code ", categoryService
				.findByCode(categoryInstance.getCode()));
		//assertNull("Found a Category with code YYY", categoryService.findByCode("YYY"));			

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

	/////////////////// Queries //////////////////////////////////

}
