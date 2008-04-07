
/**
 * This is generated code - to edit code or override methods use - Task class
 * @author witchCraft Code Generator
 * WARNING  - DO NOT EDIT - CHANGES WILL BE OVERWRITTEN
 */

package com.mycomapny.employeemgr.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.witchcraft.model.support.dao.GenericDAO;
import org.witchcraft.model.support.service.BaseServiceImpl;

import com.mycomapny.employeemgr.dao.TaskDao;
import com.mycomapny.employeemgr.domain.Task;
import com.mycomapny.employeemgr.service.TaskService;

@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
public abstract class TaskServiceImplBase extends BaseServiceImpl<Task>
		implements
			TaskService {

	private static final Logger log = Logger
			.getLogger(TaskServiceImplBase.class);

	private TaskDao taskDao;

	public void setTaskDao(TaskDao taskDao) {
		this.taskDao = taskDao;
	}

	@Override
	public GenericDAO<Task> getDao() {
		return taskDao;
	}

	//// Delegate all crud operations to the Dao ////

	public Task save(Task task) {
		Long id = task.getId();

		taskDao.save(task);

		return task;
	}

	public void delete(Task task) {
		taskDao.delete(task);
	}

	public Task load(Long id) {
		return taskDao.load(id);
	}

	public List<Task> loadAll() {
		return taskDao.loadAll();
	}

	public List<Task> searchByExample(Task task) {
		return taskDao.searchByExample(task);
	}

	/*
	 public List query(String queryString, Object... params) {
	 return basicDAO.query(queryString, params);
	 }*/

}
