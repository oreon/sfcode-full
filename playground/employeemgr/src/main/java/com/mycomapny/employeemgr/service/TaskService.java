package com.mycomapny.employeemgr.service;

import com.mycomapny.employeemgr.domain.Task;
import com.mycomapny.employeemgr.dao.TaskDao;
import org.witchcraft.model.support.service.BaseService;

import javax.jws.WebParam;
import javax.jws.WebService;

/** The Service interface for entity - Task
 * @author - Witchcraft Generated {Do not Modify } 
 * 
 */
@WebService
public interface TaskService extends TaskDao, BaseService<Task> {

}
