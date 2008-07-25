
/**
 * This is generated code - to edit code or override methods use - Role class
 * @author witchCraft Code Generator
 * WARNING  - DO NOT EDIT - CHANGES WILL BE OVERWRITTEN
 */

package com.cc.civlit.domain.service.impl;

import com.cc.civlit.domain.auth.Role;
import com.cc.civlit.domain.service.RoleService;
import com.cc.civlit.domain.dao.RoleDao;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;

import org.apache.log4j.Logger;

import org.witchcraft.model.support.dao.GenericDAO;
import org.witchcraft.model.support.errorhandling.BusinessException;
import org.witchcraft.model.support.service.BaseServiceImpl;

import javax.jws.WebService;

import org.witchcraft.model.support.Range;

@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
public class RoleServiceImplBase extends BaseServiceImpl<Role>
		implements
			RoleService {

	private static final Logger log = Logger
			.getLogger(RoleServiceImplBase.class);

	private RoleDao roleDao;

	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

	@Override
	public GenericDAO<Role> getDao() {
		return roleDao;
	}

	//// Delegate all crud operations to the Dao ////

	public Role save(Role role) {
		Long id = role.getId();
		checkUniqueConstraints(role);
		roleDao.save(role);

		return role;
	}

	/** Before saving a record we need to ensure that no unique constraints
	 * will be violated. 
	 * @param customer
	 */
	private void checkUniqueConstraints(Role role) {
		Role existingRole = null;

		existingRole = roleDao.findByName(role.getName());
		ensureUnique(role, existingRole, "Entity.exists.withName");

	}

	public void delete(Role role) {
		roleDao.delete(role);
	}

	public Role load(Long id) {
		return roleDao.load(id);
	}

	public List<Role> loadAll() {
		return roleDao.loadAll();
	}

	public Role findByName(String name) {
		return roleDao.findByName(name);
	}

	public List<Role> searchByExample(Role role) {
		return roleDao.searchByExample(role);
	}

	public List<Role> searchByExample(Role role, List<Range> rangeObjects) {
		return roleDao.searchByExample(role, rangeObjects);
	}

}
