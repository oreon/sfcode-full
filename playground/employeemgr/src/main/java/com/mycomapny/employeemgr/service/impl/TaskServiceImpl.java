package com.mycomapny.employeemgr.service.impl;

import java.util.List;

import javax.persistence.*;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.witchcraft.model.support.Range;

import org.apache.log4j.Logger;

import com.mycomapny.employeemgr.domain.Task;

import javax.jws.WebService;

@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
@WebService(endpointInterface = "com.mycomapny.employeemgr.service.TaskService", serviceName = "TaskService")
public class TaskServiceImpl extends TaskServiceImplBase {

	private static final Logger log = Logger.getLogger(TaskServiceImpl.class);

	public TaskServiceImpl taskServiceImplInstance() {
		return this;
	}

	public List<Task> searchByExample(Task arg0, List<Range> arg1) {
		// TODO Auto-generated method stub
		return null;
	}
}
