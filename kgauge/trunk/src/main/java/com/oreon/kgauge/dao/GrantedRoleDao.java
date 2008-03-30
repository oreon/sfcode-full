package com.oreon.kgauge.dao;

import com.oreon.kgauge.domain.GrantedRole;
import org.witchcraft.model.support.dao.GenericDAO;
import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface GrantedRoleDao extends GenericDAO<GrantedRole> {

}
