package ${package}.biz.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ${package}.biz.IGeogManager;
import ${package}.dao.geog.ICountryDAO;
import ${package}.dao.geog.IStateDAO;
import ${package}.domain.geog.Country;
import ${package}.domain.geog.State;

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
