package com.mycomapny.employeemgr.service;

import com.mycomapny.employeemgr.domain.Task;
import org.springframework.test.jpa.AbstractJpaTests;
import java.util.List;

import org.witchcraft.model.support.testing.TestDataFactory;
import org.witchcraft.model.support.springbeanhelpers.BeanHelper;

import java.text.SimpleDateFormat;

import javax.persistence.PersistenceException;
import org.hibernate.PropertyValueException;
import java.util.Date;

public class TaskDaoTest extends AbstractJpaTests {

	protected Task taskInstance = new Task();

	protected TaskService taskService;

	protected boolean bTest = true;

	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy.MM.dd HH:mm:ss z");

	public void setTaskService(TaskService taskService) {
		this.taskService = taskService;
	}

	protected TestDataFactory taskTestDataFactory = (TestDataFactory) BeanHelper
			.getBean("taskTestDataFactory");

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

			taskInstance.setTitle("alpha");
			taskInstance.setDescription("Malissa");
			taskInstance
					.setStatus(com.mycomapny.employeemgr.domain.Status.STARTED);

			TestDataFactory employeeTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("employeeTestDataFactory");

			taskInstance
					.setEmployee((com.mycomapny.employeemgr.domain.Employee) employeeTestDataFactory
							.loadOneRecord());

			taskService.save(taskInstance);
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
			Task task = new Task();

			try {

				task.setTitle("pi");
				task.setDescription("Eric");
				task
						.setStatus(com.mycomapny.employeemgr.domain.Status.FINISHED);

				TestDataFactory employeeTestDataFactory = (TestDataFactory) BeanHelper
						.getBean("employeeTestDataFactory");

				task
						.setEmployee((com.mycomapny.employeemgr.domain.Employee) employeeTestDataFactory
								.loadOneRecord());

			} catch (Exception ex) {
				ex.printStackTrace();
			}

			taskService.save(task);
			assertNotNull(task.getId());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testEdit() {

		try {
			//test saving a new record and updating an existing record;
			Task task = (Task) taskTestDataFactory.loadOneRecord();

			task.setTitle("Mark");
			task.setDescription("gamma");
			task.setStatus(com.mycomapny.employeemgr.domain.Status.ASSIGNED);

			taskService.save(task);

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testCount() {
		assertTrue(taskService.getCount() > 0);
	}

	public void testDelete() {
		//return false;
	}

	public void testLoad() {

		try {
			Task task = taskService.load(taskInstance.getId());
			assertNotNull(task.getId());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testSearchByExample() {
		try {
			List<Task> tasks = taskService.searchByExample(taskInstance);
			assertTrue(!tasks.isEmpty());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

}
