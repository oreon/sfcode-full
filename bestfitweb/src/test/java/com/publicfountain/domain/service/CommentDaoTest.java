package com.publicfountain.domain.service;

import com.publicfountain.domain.Comment;
import org.springframework.test.jpa.AbstractJpaTests;
import java.util.List;

import org.witchcraft.model.support.testing.TestDataFactory;
import org.witchcraft.model.support.springbeanhelpers.BeanHelper;

import java.text.SimpleDateFormat;

import javax.persistence.PersistenceException;
import org.hibernate.PropertyValueException;

public class CommentDaoTest extends AbstractJpaTests {

	protected Comment commentInstance = new Comment();

	protected CommentService commentService;

	protected boolean bTest = true;

	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy.MM.dd HH:mm:ss z");

	public void setCommentService(CommentService commentService) {
		this.commentService = commentService;
	}

	protected TestDataFactory commentTestDataFactory = (TestDataFactory) BeanHelper
			.getBean("commentTestDataFactory");

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

			commentInstance.setText("Wilson");
			commentInstance.setUserDisplayName("Malissa");

			TestDataFactory commentCreatorTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("registeredUserTestDataFactory");

			commentInstance
					.setCommentCreator((bizobjects.RegisteredUser) commentCreatorTestDataFactory
							.loadOneRecord());

			TestDataFactory topicTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("topicTestDataFactory");

			commentInstance
					.setTopic((com.publicfountain.domain.Topic) topicTestDataFactory
							.loadOneRecord());

			commentService.save(commentInstance);
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
			Comment comment = new Comment();

			try {

				comment.setText("pi");
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

			} catch (Exception ex) {
				ex.printStackTrace();
			}

			commentService.save(comment);
			assertNotNull(comment.getId());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testEdit() {

		try {
			//test saving a new record and updating an existing record;
			Comment comment = (Comment) commentTestDataFactory.loadOneRecord();

			comment.setText("Mark");
			comment.setUserDisplayName("epsilon");

			commentService.save(comment);

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testDelete() {
		//return false;
	}

	public void testLoad() {

		try {
			Comment comment = commentService.load(commentInstance.getId());
			assertNotNull(comment.getId());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testSearchByExample() {
		try {
			List<Comment> comments = commentService
					.searchByExample(commentInstance);
			assertTrue(!comments.isEmpty());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

}
