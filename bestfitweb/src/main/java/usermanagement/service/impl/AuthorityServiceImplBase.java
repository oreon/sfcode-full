package usermanagement.service.impl;

import usermanagement.Authority;
import usermanagement.service.AuthorityService;
import usermanagement.dao.AuthorityDao;
import java.util.List;
import usermanagement.service.AuthorityService;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;

import org.apache.log4j.Logger;

import usermanagement.Authority;
import usermanagement.service.AuthorityService;

import org.witchcraft.model.support.dao.GenericDAO;
import org.witchcraft.model.support.errorhandling.BusinessException;
import org.witchcraft.model.support.service.BaseServiceImpl;

@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
public class AuthorityServiceImplBase extends BaseServiceImpl<Authority>
		implements
			AuthorityService {

	private static final Logger log = Logger
			.getLogger(AuthorityServiceImplBase.class);

	private AuthorityDao authorityDao;

	public void setAuthorityDao(AuthorityDao authorityDao) {
		this.authorityDao = authorityDao;
	}

	@Override
	public GenericDAO<Authority> getDao() {
		return authorityDao;
	}

	//// Delegate all crud operations to the Dao ////

	public Authority save(Authority authority) {
		Long id = authority.getId();

		authorityDao.save(authority);

		return authority;
	}

	public void delete(Authority authority) {
		authorityDao.delete(authority);
	}

	public Authority load(Long id) {
		return authorityDao.load(id);
	}

	public List<Authority> loadAll() {
		return authorityDao.loadAll();
	}

	public List<Authority> searchByExample(Authority authority) {
		return authorityDao.searchByExample(authority);
	}

	/*
	public List query(String queryString, Object... params) {
		return basicDAO.query(queryString, params);
	}*/

}
