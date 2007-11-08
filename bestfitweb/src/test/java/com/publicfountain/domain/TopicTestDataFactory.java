package com.publicfountain.domain;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import org.witchcraft.model.support.springbeanhelpers.BeanHelper;
import org.witchcraft.model.support.testing.AbstractTestDataFactory;

import org.witchcraft.model.support.testing.TestDataFactory;

import org.witchcraft.model.randomgen.RandomValueGeneratorFactory;

import org.springframework.transaction.annotation.Transactional;

import com.publicfountain.domain.service.TopicService;

@Transactional
public class TopicTestDataFactory extends AbstractTestDataFactory<Topic> {

	List<Topic> topics = new ArrayList<Topic>();

	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy.MM.dd HH:mm:ss z");

	TopicService topicService;

	public TopicService getTopicService() {
		return topicService;
	}

	public void setTopicService(TopicService topicService) {
		this.topicService = topicService;
	}

	public void register(Topic topic) {
		topics.add(topic);
	}

	public Topic createTopicOne() {
		Topic topic = new Topic();

		try {

			topic.setName("pi");
			topic.setExpiry(dateFormat.parse("2007.11.19 08:32:39 EST"));
			topic.setStatus(com.publicfountain.domain.Status.Active);
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

			register(topic);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return topic;
	}

	public Topic createTopicTwo() {
		Topic topic = new Topic();

		try {

			topic.setName("epsilon");
			topic.setExpiry(dateFormat.parse("2007.11.14 18:19:19 EST"));
			topic.setStatus(com.publicfountain.domain.Status.Inactive);
			topic.setTopicType(com.publicfountain.domain.TopicType.TopicOfDay);

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

			register(topic);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return topic;
	}

	public Topic createTopicThree() {
		Topic topic = new Topic();

		try {

			topic.setName("beta");
			topic.setExpiry(dateFormat.parse("2007.10.20 19:28:46 EDT"));
			topic.setStatus(com.publicfountain.domain.Status.Active);
			topic
					.setTopicType(com.publicfountain.domain.TopicType.CustomOpinion);

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

			register(topic);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return topic;
	}

	public Topic createTopicFour() {
		Topic topic = new Topic();

		try {

			topic.setName("alpha");
			topic.setExpiry(dateFormat.parse("2007.11.07 19:59:19 EST"));
			topic.setStatus(com.publicfountain.domain.Status.Inactive);
			topic
					.setTopicType(com.publicfountain.domain.TopicType.CustomOpinion);

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

			register(topic);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return topic;
	}

	public Topic createTopicFive() {
		Topic topic = new Topic();

		try {

			topic.setName("Malissa");
			topic.setExpiry(dateFormat.parse("2007.10.31 22:10:57 EDT"));
			topic.setStatus(com.publicfountain.domain.Status.Active);
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

			register(topic);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return topic;
	}

	public Topic loadOneRecord() {
		List<Topic> topics = topicService.loadAll();

		if (topics.isEmpty()) {
			persistAll();
			topics = topicService.loadAll();
		}

		return topics.get(new Random().nextInt(topics.size()));
	}

	public List<Topic> getAllAsList() {

		if (topics.isEmpty()) {
			createTopicOne();
			createTopicTwo();
			createTopicThree();
			createTopicFour();
			createTopicFive();

		}

		return topics;
	}

	public void persistAll() {
		if (!isPersistable() || alreadyPersisted)
			return;

		getAllAsList();

		for (Topic topic : topics) {
			topicService.save(topic);
		}

		alreadyPersisted = true;
	}

	/** Execute this method to manually generate additional orders
	 * @param args
	 */
	public static void main(String args[]) {

		int recordsTocreate = 30;

		TestDataFactory placedOrderTestDataFactory = (TestDataFactory) BeanHelper
				.getBean("placedOrderTestDataFactory");

		placedOrderTestDataFactory.createAndSaveRecords(recordsTocreate);
	}

	public void createAndSaveRecords(int recordsTocreate) {
		for (int i = 0; i < recordsTocreate; i++) {
			Topic topic = createRandomTopic();
			topicService.save(topic);
		}
	}

	public Topic createRandomTopic() {
		Topic topic = new Topic();

		topic.setName((String) RandomValueGeneratorFactory
				.createInstance("String"));
		topic.setExpiry((java.util.Date) RandomValueGeneratorFactory
				.createInstance("Date"));
		topic.setStatus((Status) RandomValueGeneratorFactory
				.createInstance("Status"));
		topic.setTopicType((TopicType) RandomValueGeneratorFactory
				.createInstance("TopicType"));

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

		return topic;
	}

}
