package com.oreon.olympics.domain.service.impl;

import com.oreon.olympics.domain.Country;
import com.oreon.olympics.domain.service.CountryService;
import com.oreon.olympics.domain.dao.CountryDao;
import java.util.List;
import com.oreon.olympics.domain.service.CountryService;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;

import org.apache.log4j.Logger;

import usermanagement.Authority;
import usermanagement.service.AuthorityService;

import org.witchcraft.model.support.dao.GenericDAO;
import org.witchcraft.model.support.errorhandling.BusinessException;
import org.witchcraft.model.support.service.BaseServiceImpl;

@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
public class CountryServiceImplBase extends BaseServiceImpl<Country>
		implements
			CountryService {

	private static final Logger log = Logger
			.getLogger(CountryServiceImplBase.class);

	private CountryDao countryDao;

	public void setCountryDao(CountryDao countryDao) {
		this.countryDao = countryDao;
	}

	@Override
	public GenericDAO<Country> getDao() {
		return countryDao;
	}

	//// Delegate all crud operations to the Dao ////

	public Country save(Country country) {
		Long id = country.getId();

		countryDao.save(country);

		return country;
	}

	public void delete(Country country) {
		countryDao.delete(country);
	}

	public Country load(Long id) {
		return countryDao.load(id);
	}

	public List<Country> loadAll() {
		return countryDao.loadAll();
	}

	public List<Country> searchByExample(Country country) {
		return countryDao.searchByExample(country);
	}

	/*
	public List query(String queryString, Object... params) {
		return basicDAO.query(queryString, params);
	}*/

}
