package com.cc.civlit.domain.service;

import com.cc.civlit.domain.auth.GrantedRole;
import com.cc.civlit.domain.dao.GrantedRoleDao;
import org.witchcraft.model.support.service.BaseService;

import javax.jws.WebParam;
import javax.jws.WebService;

/** The Service interface for entity - GrantedRole
 * @author - Witchcraft Generated {Do not Modify } 
 * 
 */
@WebService
public interface GrantedRoleService
		extends
			GrantedRoleDao,
			BaseService<GrantedRole> {

}
