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

import com.publicfountain.domain.service.CommentService;

@Transactional
public class CommentTestDataFactory extends AbstractTestDataFactory<Comment> {

	List<Comment> comments = new ArrayList<Comment>();

	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy.MM.dd HH:mm:ss z");

	CommentService commentService;

	public CommentService getCommentService() {
		return commentService;
	}

	public void setCommentService(CommentService commentService) {
		this.commentService = commentService;
	}

	public void register(Comment comment) {
		comments.add(comment);
	}

	public Comment createCommentOne() {
		Comment comment = new Comment();

		try {

			comment.setText("Wilson");
			comment.setUserDisplayName("alpha");

			TestDataFactory commentCreatorTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("registeredUserTestDataFactory");

			comment
					.setCommentCreator((bizobjects.RegisteredUser) commentCreatorTestDataFactory
							.loadOneRecord());

			TestDataFactory topicTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("topicTestDataFactory");

			comment
					.setTopic((com.publicfountain.domain.Topic) topicTestDataFactory
							.loadOneRecord());

			register(comment);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return comment;
	}

	public Comment createCommentTwo() {
		Comment comment = new Comment();

		try {

			comment.setText("Malissa");
			comment.setUserDisplayName("John");

			TestDataFactory commentCreatorTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("registeredUserTestDataFactory");

			comment
					.setCommentCreator((bizobjects.RegisteredUser) commentCreatorTestDataFactory
							.loadOneRecord());

			TestDataFactory topicTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("topicTestDataFactory");

			comment
					.setTopic((com.publicfountain.domain.Topic) topicTestDataFactory
							.loadOneRecord());

			register(comment);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return comment;
	}

	public Comment createCommentThree() {
		Comment comment = new Comment();

		try {

			comment.setText("beta");
			comment.setUserDisplayName("epsilon");

			TestDataFactory commentCreatorTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("registeredUserTestDataFactory");

			comment
					.setCommentCreator((bizobjects.RegisteredUser) commentCreatorTestDataFactory
							.loadOneRecord());

			TestDataFactory topicTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("topicTestDataFactory");

			comment
					.setTopic((com.publicfountain.domain.Topic) topicTestDataFactory
							.loadOneRecord());

			register(comment);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return comment;
	}

	public Comment createCommentFour() {
		Comment comment = new Comment();

		try {

			comment.setText("epsilon");
			comment.setUserDisplayName("beta");

			TestDataFactory commentCreatorTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("registeredUserTestDataFactory");

			comment
					.setCommentCreator((bizobjects.RegisteredUser) commentCreatorTestDataFactory
							.loadOneRecord());

			TestDataFactory topicTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("topicTestDataFactory");

			comment
					.setTopic((com.publicfountain.domain.Topic) topicTestDataFactory
							.loadOneRecord());

			register(comment);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return comment;
	}

	public Comment createCommentFive() {
		Comment comment = new Comment();

		try {

			comment.setText("beta");
			comment.setUserDisplayName("Wilson");

			TestDataFactory commentCreatorTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("registeredUserTestDataFactory");

			comment
					.setCommentCreator((bizobjects.RegisteredUser) commentCreatorTestDataFactory
							.loadOneRecord());

			TestDataFactory topicTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("topicTestDataFactory");

			comment
					.setTopic((com.publicfountain.domain.Topic) topicTestDataFactory
							.loadOneRecord());

			register(comment);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return comment;
	}

	public Comment loadOneRecord() {
		List<Comment> comments = commentService.loadAll();

		if (comments.isEmpty()) {
			persistAll();
			comments = commentService.loadAll();
		}

		return comments.get(new Random().nextInt(comments.size()));
	}

	public List<Comment> getAllAsList() {

		if (comments.isEmpty()) {
			createCommentOne();
			createCommentTwo();
			createCommentThree();
			createCommentFour();
			createCommentFive();

		}

		return comments;
	}

	public void persistAll() {
		if (!isPersistable() || alreadyPersisted)
			return;

		getAllAsList();

		for (Comment comment : comments) {
			commentService.save(comment);
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
			Comment comment = createRandomComment();
			commentService.save(comment);
		}
	}

	public Comment createRandomComment() {
		Comment comment = new Comment();

		comment.setText((String) RandomValueGeneratorFactory
				.createInstance("String"));
		comment.setUserDisplayName((String) RandomValueGeneratorFactory
				.createInstance("String"));

		TestDataFactory commentCreatorTestDataFactory = (TestDataFactory) BeanHelper
				.getBean("registeredUserTestDataFactory");

		comment
				.setCommentCreator((bizobjects.RegisteredUser) commentCreatorTestDataFactory
						.loadOneRecord());

		TestDataFactory topicTestDataFactory = (TestDataFactory) BeanHelper
				.getBean("topicTestDataFactory");

		comment.setTopic((com.publicfountain.domain.Topic) topicTestDataFactory
				.loadOneRecord());

		return comment;
	}

}
