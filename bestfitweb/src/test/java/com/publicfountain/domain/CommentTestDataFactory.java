package com.publicfountain.domain;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import org.witchcraft.model.support.springbeanhelpers.BeanHelper;
import org.witchcraft.model.support.testing.AbstractTestDataFactory;

import org.witchcraft.model.support.testing.TestDataFactory;

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

			comment.setText("beta");
			comment.setUserDisplayName("Lavendar");

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

			comment.setText("alpha");
			comment.setUserDisplayName("pi");

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

			comment.setText("alpha");
			comment.setUserDisplayName("Mark");

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

			comment.setText("beta");
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

			comment.setText("gamma");
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

}
