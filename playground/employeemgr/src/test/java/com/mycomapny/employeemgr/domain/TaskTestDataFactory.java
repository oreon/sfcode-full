package com.mycomapny.employeemgr.domain;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import org.witchcraft.model.support.springbeanhelpers.BeanHelper;
import org.witchcraft.model.support.testing.AbstractTestDataFactory;

import org.witchcraft.model.support.testing.TestDataFactory;

import org.witchcraft.model.randomgen.RandomValueGeneratorFactory;

import org.springframework.transaction.annotation.Transactional;

import com.mycomapny.employeemgr.service.TaskService;

@Transactional
public class TaskTestDataFactory extends AbstractTestDataFactory<Task> {

	private List<Task> tasks = new ArrayList<Task>();

	private static int RECORDS_TO_CREATE = 30;

	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy.MM.dd HH:mm:ss z");

	TaskService taskService;

	public TaskService getTaskService() {
		return taskService;
	}

	public void setTaskService(TaskService taskService) {
		this.taskService = taskService;
	}

	public void register(Task task) {
		tasks.add(task);
	}

	public Task createTaskOne() {
		Task task = new Task();

		try {

			task.setTitle("zeta");
			task.setDescription("zeta");
			task.setStatus(com.mycomapny.employeemgr.domain.Status.FINISHED);

			TestDataFactory employeeTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("employeeTestDataFactory");

			task
					.setEmployee((com.mycomapny.employeemgr.domain.Employee) employeeTestDataFactory
							.loadOneRecord());

			register(task);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return task;
	}

	public Task createTaskTwo() {
		Task task = new Task();

		try {

			task.setTitle("John");
			task.setDescription("gamma");
			task.setStatus(com.mycomapny.employeemgr.domain.Status.FINISHED);

			TestDataFactory employeeTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("employeeTestDataFactory");

			task
					.setEmployee((com.mycomapny.employeemgr.domain.Employee) employeeTestDataFactory
							.loadOneRecord());

			register(task);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return task;
	}

	public Task createTaskThree() {
		Task task = new Task();

		try {

			task.setTitle("Lavendar");
			task.setDescription("epsilon");
			task.setStatus(com.mycomapny.employeemgr.domain.Status.ASSIGNED);

			TestDataFactory employeeTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("employeeTestDataFactory");

			task
					.setEmployee((com.mycomapny.employeemgr.domain.Employee) employeeTestDataFactory
							.loadOneRecord());

			register(task);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return task;
	}

	public Task createTaskFour() {
		Task task = new Task();

		try {

			task.setTitle("Wilson");
			task.setDescription("Malissa");
			task.setStatus(com.mycomapny.employeemgr.domain.Status.ASSIGNED);

			TestDataFactory employeeTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("employeeTestDataFactory");

			task
					.setEmployee((com.mycomapny.employeemgr.domain.Employee) employeeTestDataFactory
							.loadOneRecord());

			register(task);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return task;
	}

	public Task createTaskFive() {
		Task task = new Task();

		try {

			task.setTitle("gamma");
			task.setDescription("alpha");
			task.setStatus(com.mycomapny.employeemgr.domain.Status.FINISHED);

			TestDataFactory employeeTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("employeeTestDataFactory");

			task
					.setEmployee((com.mycomapny.employeemgr.domain.Employee) employeeTestDataFactory
							.loadOneRecord());

			register(task);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return task;
	}

	public Task loadOneRecord() {
		List<Task> tasks = taskService.loadAll();

		if (tasks.isEmpty()) {
			persistAll();
			tasks = taskService.loadAll();
		}

		return tasks.get(new Random().nextInt(tasks.size()));
	}

	public List<Task> getAllAsList() {

		if (tasks.isEmpty()) {

			createTaskOne();
			createTaskTwo();
			createTaskThree();
			createTaskFour();
			createTaskFive();

		}

		return tasks;
	}

	public void persistAll() {
		if (!isPersistable() || alreadyPersisted)
			return;

		getAllAsList();

		for (Task task : tasks) {
			taskService.save(task);
		}

		alreadyPersisted = true;
	}

	/** Execute this method to manually generate additional orders
	 * @param args
	 */
	public static void main(String args[]) {

		TestDataFactory placedOrderTestDataFactory = (TestDataFactory) BeanHelper
				.getBean("placedOrderTestDataFactory");

		placedOrderTestDataFactory.createAndSaveRecords(RECORDS_TO_CREATE);
	}

	public void createAndSaveRecords(int recordsTocreate) {
		for (int i = 0; i < recordsTocreate; i++) {
			Task task = createRandomTask();
			taskService.save(task);
		}
	}

	public Task createRandomTask() {
		Task task = new Task();

		task.setTitle((String) RandomValueGeneratorFactory
				.createInstance("String"));
		task.setDescription((String) RandomValueGeneratorFactory
				.createInstance("String"));
		task.setStatus((Status) RandomValueGeneratorFactory
				.createInstance("Status"));

		TestDataFactory employeeTestDataFactory = (TestDataFactory) BeanHelper
				.getBean("employeeTestDataFactory");

		task
				.setEmployee((com.mycomapny.employeemgr.domain.Employee) employeeTestDataFactory
						.loadOneRecord());

		return task;
	}

}
