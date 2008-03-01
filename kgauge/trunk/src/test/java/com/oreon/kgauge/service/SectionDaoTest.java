package com.oreon.kgauge.service;

import com.oreon.kgauge.domain.Section;
import org.springframework.test.jpa.AbstractJpaTests;
import java.util.List;

import org.witchcraft.model.support.testing.TestDataFactory;
import org.witchcraft.model.support.springbeanhelpers.BeanHelper;

import java.text.SimpleDateFormat;

import javax.persistence.PersistenceException;
import org.hibernate.PropertyValueException;
import java.util.Date;

public class SectionDaoTest extends AbstractJpaTests {

	protected Section sectionInstance = new Section();

	protected SectionService sectionService;

	protected boolean bTest = true;

	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy.MM.dd HH:mm:ss z");

	public void setSectionService(SectionService sectionService) {
		this.sectionService = sectionService;
	}

	protected TestDataFactory sectionTestDataFactory = (TestDataFactory) BeanHelper
			.getBean("sectionTestDataFactory");

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

			sectionInstance.setName("John");

			TestDataFactory examTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("examTestDataFactory");

			sectionInstance
					.setExam((com.oreon.kgauge.domain.Exam) examTestDataFactory
							.loadOneRecord());

			sectionService.save(sectionInstance);
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
			Section section = new Section();

			try {

				section.setName("Mark");

				TestDataFactory examTestDataFactory = (TestDataFactory) BeanHelper
						.getBean("examTestDataFactory");

				section
						.setExam((com.oreon.kgauge.domain.Exam) examTestDataFactory
								.loadOneRecord());

			} catch (Exception ex) {
				ex.printStackTrace();
			}

			sectionService.save(section);
			assertNotNull(section.getId());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testEdit() {

		try {
			//test saving a new record and updating an existing record;
			Section section = (Section) sectionTestDataFactory.loadOneRecord();

			section.setName("beta");

			sectionService.save(section);

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testCount() {
		assertTrue(sectionService.getCount() > 0);
	}

	//count the number of records - add one delete it - check count is same after delete
	public void testDelete() {
		long count, newCount, diff = 0;
		count = sectionService.getCount();
		Section section = (Section) sectionTestDataFactory.loadOneRecord();
		sectionService.delete(section);
		newCount = sectionService.getCount();
		diff = newCount - count;
		try {
			assertEquals(diff, 0);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testLoad() {

		try {
			Section section = sectionService.load(sectionInstance.getId());
			assertNotNull(section.getId());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testSearchByExample() {
		try {
			List<Section> sections = sectionService
					.searchByExample(sectionInstance);
			assertTrue(!sections.isEmpty());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

}
