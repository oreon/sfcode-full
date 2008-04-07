package com.mycomapny.employeemgr.dao;

import com.mycomapny.employeemgr.domain.Task;
import org.witchcraft.model.support.dao.GenericDAO;
import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface TaskDao extends GenericDAO<Task> {

}
