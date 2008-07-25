package com.cc.civlit.dao;

import com.cc.civlit.domain.FirmAdministrator;
import org.witchcraft.model.support.dao.GenericDAO;
import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface FirmAdministratorDao extends GenericDAO<FirmAdministrator> {

	public FirmAdministrator findByEmail(String email);

	public FirmAdministrator findByUsername(String username);

}
