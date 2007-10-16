package com.publicfountain.domain;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import org.witchcraft.model.support.springbeanhelpers.BeanHelper;
import org.witchcraft.model.support.testing.AbstractTestDataFactory;

import org.witchcraft.model.support.testing.TestDataFactory;

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

			topic.setName("Malissa");
			topic.setExpiry(dateFormat.parse("2007.10.26 04:36:00 EDT"));
			topic.setStatus(com.publicfountain.domain.Status.Archived);
			topic.setTopicType(com.publicfountain.domain.TopicType.Edtiorial);

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

			topic.setName("John");
			topic.setExpiry(dateFormat.parse("2007.09.25 12:57:05 EDT"));
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

			topic.setName("gamma");
			topic.setExpiry(dateFormat.parse("2007.09.21 23:21:31 EDT"));
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

	public Topic createTopicFour() {
		Topic topic = new Topic();

		try {

			topic.setName("epsilon");
			topic.setExpiry(dateFormat.parse("2007.11.08 07:04:18 EST"));
			topic.setStatus(com.publicfountain.domain.Status.Archived);
			topic.setTopicType(com.publicfountain.domain.TopicType.Video);

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

			topic.setName("Mark");
			topic.setExpiry(dateFormat.parse("2007.11.05 11:53:45 EST"));
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

}
