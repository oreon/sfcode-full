package com.publicfountain.domain.service;

import com.publicfountain.domain.Topic;
import org.springframework.test.jpa.AbstractJpaTests;
import java.util.List;

import org.witchcraft.model.support.testing.TestDataFactory;
import org.witchcraft.model.support.springbeanhelpers.BeanHelper;

import java.text.SimpleDateFormat;

import javax.persistence.PersistenceException;
import org.hibernate.PropertyValueException;

public class TopicDaoTest extends AbstractJpaTests {

	protected Topic topicInstance = new Topic();

	protected TopicService topicService;

	protected boolean bTest = true;

	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy.MM.dd HH:mm:ss z");

	public void setTopicService(TopicService topicService) {
		this.topicService = topicService;
	}

	protected TestDataFactory topicTestDataFactory = (TestDataFactory) BeanHelper
			.getBean("topicTestDataFactory");

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

			topicInstance.setName("John");
			topicInstance
					.setExpiry(dateFormat.parse("2007.10.02 19:50:41 EDT"));
			topicInstance.setStatus(com.publicfountain.domain.Status.Archived);
			topicInstance
					.setTopicType(com.publicfountain.domain.TopicType.Edtiorial);

			TestDataFactory topicCreatorTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("registeredUserTestDataFactory");

			topicInstance
					.setTopicCreator((bizobjects.RegisteredUser) topicCreatorTestDataFactory
							.loadOneRecord());

			TestDataFactory categoryTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("categoryTestDataFactory");

			topicInstance
					.setCategory((com.publicfountain.domain.Category) categoryTestDataFactory
							.loadOneRecord());

			topicService.save(topicInstance);
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
			Topic topic = new Topic();

			try {

				topic.setName("zeta");
				topic.setExpiry(dateFormat.parse("2007.10.02 17:22:55 EDT"));
				topic.setStatus(com.publicfountain.domain.Status.Inactive);
				topic.setTopicType(com.publicfountain.domain.TopicType.Image);

				TestDataFactory topicCreatorTestDataFactory = (TestDataFactory) BeanHelper
						.getBean("registeredUserTestDataFactory");

				topic
						.setTopicCreator((bizobjects.RegisteredUser) topicCreatorTestDataFactory
								.loadOneRecord());

				TestDataFactory categoryTestDataFactory = (TestDataFactory) BeanHelper
						.getBean("categoryTestDataFactory");

				topic
						.setCategory((com.publicfountain.domain.Category) categoryTestDataFactory
								.loadOneRecord());

			} catch (Exception ex) {
				ex.printStackTrace();
			}

			topicService.save(topic);
			assertNotNull(topic.getId());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testEdit() {

		try {
			//test saving a new record and updating an existing record;
			Topic topic = (Topic) topicTestDataFactory.loadOneRecord();

			topic.setName("zeta");
			topic.setExpiry(dateFormat.parse("2007.10.26 15:52:55 EDT"));
			topic.setStatus(com.publicfountain.domain.Status.Active);
			topic.setTopicType(com.publicfountain.domain.TopicType.TopicOfDay);

			topicService.save(topic);

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testDelete() {
		//return false;
	}

	public void testLoad() {

		try {
			Topic topic = topicService.load(topicInstance.getId());
			assertNotNull(topic.getId());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testSearchByExample() {
		try {
			List<Topic> topics = topicService.searchByExample(topicInstance);
			assertTrue(!topics.isEmpty());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

}
