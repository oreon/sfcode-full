package com.cc.civlit.domain.dao;

import com.cc.civlit.domain.auth.GrantedRole;
import org.witchcraft.model.support.dao.GenericDAO;
import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface GrantedRoleDao extends GenericDAO<GrantedRole> {

}
