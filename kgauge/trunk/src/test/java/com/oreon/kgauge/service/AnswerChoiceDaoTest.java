package com.oreon.kgauge.service;

import com.oreon.kgauge.domain.AnswerChoice;
import org.springframework.test.jpa.AbstractJpaTests;
import java.util.List;

import org.witchcraft.model.support.testing.TestDataFactory;
import org.witchcraft.model.support.springbeanhelpers.BeanHelper;

import java.text.SimpleDateFormat;

import javax.persistence.PersistenceException;
import org.hibernate.PropertyValueException;
import java.util.Date;

public class AnswerChoiceDaoTest extends AbstractJpaTests {

	protected AnswerChoice answerChoiceInstance = new AnswerChoice();

	protected AnswerChoiceService answerChoiceService;

	protected boolean bTest = true;

	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy.MM.dd HH:mm:ss z");

	public void setAnswerChoiceService(AnswerChoiceService answerChoiceService) {
		this.answerChoiceService = answerChoiceService;
	}

	protected TestDataFactory answerChoiceTestDataFactory = (TestDataFactory) BeanHelper
			.getBean("answerChoiceTestDataFactory");

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

			answerChoiceInstance.setAnswerText("gamma");
			answerChoiceInstance.setScore(5700);

			TestDataFactory questionTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("questionTestDataFactory");

			answerChoiceInstance
					.setQuestion((com.oreon.kgauge.domain.Question) questionTestDataFactory
							.loadOneRecord());

			answerChoiceService.save(answerChoiceInstance);
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
			AnswerChoice answerChoice = new AnswerChoice();

			try {

				answerChoice.setAnswerText("Wilson");
				answerChoice.setScore(3327);

				TestDataFactory questionTestDataFactory = (TestDataFactory) BeanHelper
						.getBean("questionTestDataFactory");

				answerChoice
						.setQuestion((com.oreon.kgauge.domain.Question) questionTestDataFactory
								.loadOneRecord());

			} catch (Exception ex) {
				ex.printStackTrace();
			}

			answerChoiceService.save(answerChoice);
			assertNotNull(answerChoice.getId());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testEdit() {

		try {
			//test saving a new record and updating an existing record;
			AnswerChoice answerChoice = (AnswerChoice) answerChoiceTestDataFactory
					.loadOneRecord();

			answerChoice.setAnswerText("alpha");
			answerChoice.setScore(2646);

			answerChoiceService.save(answerChoice);

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testCount() {
		assertTrue(answerChoiceService.getCount() > 0);
	}

	//count the number of records - add one delete it - check count is same after delete
	public void testDelete() {
		long count, newCount, diff = 0;
		count = answerChoiceService.getCount();
		AnswerChoice answerChoice = (AnswerChoice) answerChoiceTestDataFactory
				.loadOneRecord();
		answerChoiceService.delete(answerChoice);
		newCount = answerChoiceService.getCount();
		diff = newCount - count;
		try {
			assertEquals(diff, 0);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testLoad() {

		try {
			AnswerChoice answerChoice = answerChoiceService
					.load(answerChoiceInstance.getId());
			assertNotNull(answerChoice.getId());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testSearchByExample() {
		try {
			List<AnswerChoice> answerChoices = answerChoiceService
					.searchByExample(answerChoiceInstance);
			assertTrue(!answerChoices.isEmpty());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

}
