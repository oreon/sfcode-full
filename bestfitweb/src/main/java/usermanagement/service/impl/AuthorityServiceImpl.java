package usermanagement.service.impl;

import usermanagement.Authority;
import usermanagement.service.AuthorityService;
import usermanagement.dao.AuthorityDao;
import java.util.List;
import usermanagement.service.AuthorityService;
import org.springframework.transaction.annotation.Transactional;
import org.apache.log4j.Logger;

@Transactional
public class AuthorityServiceImpl implements AuthorityService {

	protected static final Logger log = Logger
			.getLogger(AuthorityServiceImpl.class);

	private AuthorityDao authorityDao;

	public void setAuthorityDao(AuthorityDao authorityDao) {
		this.authorityDao = authorityDao;
	}

	//// Delegate all crud operations to the Dao ////

	public Authority save(Authority authority) {

		return authorityDao.save(authority);
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
