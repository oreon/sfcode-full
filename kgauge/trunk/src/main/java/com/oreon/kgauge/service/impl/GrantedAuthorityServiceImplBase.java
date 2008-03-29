
/**
 * This is generated code - to edit code or override methods use - GrantedAuthority class
 * @author witchCraft Code Generator
 * WARNING  - DO NOT EDIT - CHANGES WILL BE OVERWRITTEN
 */

package com.oreon.kgauge.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.witchcraft.model.support.Range;
import org.witchcraft.model.support.dao.GenericDAO;
import org.witchcraft.model.support.service.BaseServiceImpl;

import com.oreon.kgauge.dao.GrantedAuthorityDao;
import com.oreon.kgauge.domain.GrantedAuthority;
import com.oreon.kgauge.service.GrantedAuthorityService;

@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
public class GrantedAuthorityServiceImplBase
		extends
			BaseServiceImpl<GrantedAuthority>
		implements
			GrantedAuthorityService {

	private static final Logger log = Logger
			.getLogger(GrantedAuthorityServiceImplBase.class);

	private GrantedAuthorityDao grantedAuthorityDao;

	public void setGrantedAuthorityDao(GrantedAuthorityDao grantedAuthorityDao) {
		this.grantedAuthorityDao = grantedAuthorityDao;
	}

	@Override
	public GenericDAO<GrantedAuthority> getDao() {
		return grantedAuthorityDao;
	}

	//// Delegate all crud operations to the Dao ////

	public GrantedAuthority save(GrantedAuthority grantedAuthority) {
		Long id = grantedAuthority.getId();

		grantedAuthorityDao.save(grantedAuthority);

		return grantedAuthority;
	}

	public void delete(GrantedAuthority grantedAuthority) {
		grantedAuthorityDao.delete(grantedAuthority);
	}

	public GrantedAuthority load(Long id) {
		return grantedAuthorityDao.load(id);
	}

	public List<GrantedAuthority> loadAll() {
		return grantedAuthorityDao.loadAll();
	}

	public List<GrantedAuthority> searchByExample(
			GrantedAuthority grantedAuthority) {
		return grantedAuthorityDao.searchByExample(grantedAuthority);
	}

	public List<GrantedAuthority> searchByExample(
			GrantedAuthority grantedAuthority, List<Range> rangeObjects) {
		return grantedAuthorityDao.searchByExample(grantedAuthority,
				rangeObjects);
	}

	/*
	public List query(String queryString, Object... params) {
		return basicDAO.query(queryString, params);
	}*/

}
