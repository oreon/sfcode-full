package com.cc.civlit.dao;

import com.cc.civlit.domain.CaseAdministrator;
import org.witchcraft.model.support.dao.GenericDAO;
import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface CaseAdministratorDao extends GenericDAO<CaseAdministrator> {

	public CaseAdministrator findByEmail(String email);

}
