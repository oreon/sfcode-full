package com.oreon.bugtracker.biz.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oreon.bugtracker.biz.IGeogManager;
import com.oreon.bugtracker.dao.geog.ICountryDAO;
import com.oreon.bugtracker.dao.geog.IStateDAO;
import com.oreon.bugtracker.domain.geog.Country;
import com.oreon.bugtracker.domain.geog.State;

/**
 * @author Kamalpreet Singh
 *
 */
@Service("geogManager")
@Transactional(readOnly = true)
public class GeogManager implements IGeogManager {

	@Resource(name = "countryDAO")
	private ICountryDAO countryDAO;
	
	@Resource(name = "stateDAO")
	private IStateDAO stateDAO;
	
	@Override
	public Country createCountry(Country country) {
		return countryDAO.create(country);
	}
	
	@Override
	public State createState(State state) {
		return stateDAO.create(state);
	}
}
