
/**
 * This is generated code - to edit code or override methods use - GrantedRole class
 * @author witchCraft Code Generator
 * WARNING  - DO NOT EDIT - CHANGES WILL BE OVERWRITTEN
 */

package com.oreon.kgauge.service.impl;

import com.oreon.kgauge.domain.GrantedRole;
import com.oreon.kgauge.service.GrantedRoleService;
import com.oreon.kgauge.dao.GrantedRoleDao;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;

import org.apache.log4j.Logger;

import org.witchcraft.model.support.dao.GenericDAO;
import org.witchcraft.model.support.errorhandling.BusinessException;
import org.witchcraft.model.support.service.BaseServiceImpl;

import javax.jws.WebService;

import org.witchcraft.model.support.Range;

import com.oreon.kgauge.domain.GrantedRole;

@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
public class GrantedRoleServiceImplBase extends BaseServiceImpl<GrantedRole>
		implements
			GrantedRoleService {

	private static final Logger log = Logger
			.getLogger(GrantedRoleServiceImplBase.class);

	private GrantedRoleDao grantedRoleDao;

	public void setGrantedRoleDao(GrantedRoleDao grantedRoleDao) {
		this.grantedRoleDao = grantedRoleDao;
	}

	@Override
	public GenericDAO<GrantedRole> getDao() {
		return grantedRoleDao;
	}

	//// Delegate all crud operations to the Dao ////

	public GrantedRole save(GrantedRole grantedRole) {
		Long id = grantedRole.getId();

		grantedRoleDao.save(grantedRole);

		return grantedRole;
	}

	public void delete(GrantedRole grantedRole) {
		grantedRoleDao.delete(grantedRole);
	}

	public GrantedRole load(Long id) {
		return grantedRoleDao.load(id);
	}

	public List<GrantedRole> loadAll() {
		return grantedRoleDao.loadAll();
	}

	public List<GrantedRole> searchByExample(GrantedRole grantedRole) {
		return grantedRoleDao.searchByExample(grantedRole);
	}

	public List<GrantedRole> searchByExample(GrantedRole grantedRole,
			List<Range> rangeObjects) {
		return grantedRoleDao.searchByExample(grantedRole, rangeObjects);
	}

	/*
	public List query(String queryString, Object... params) {
		return basicDAO.query(queryString, params);
	}*/

}
