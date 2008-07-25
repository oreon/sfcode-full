package com.cc.civlit.domain.dao;

import com.cc.civlit.domain.auth.Role;
import org.witchcraft.model.support.dao.GenericDAO;
import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface RoleDao extends GenericDAO<Role> {

	public Role findByName(String name);

}
