package ${package}.biz.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
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
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Country createCountry(Country country) {
		return countryDAO.create(country);
	}
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Country updateCountry(Country country) {
		return countryDAO.update(country);
	}
	
	@Override
	public Country findCountryById(Long countryId) {
		return countryDAO.findById(countryId);
	}
	
	@Override
	public List<Country> findAllCountries() {
		return countryDAO.findAll();
	}
	
	@Override
	public List<Country> findAllActiveCountries() {
		return countryDAO.findAllActiveCountries();
	}
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public State createState(State state) {
		return stateDAO.create(state);
	}
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public State updateState(State state) {
		return stateDAO.create(state);
	}
	
	@Override
	public State findStateById(Long stateId) {
		return stateDAO.findById(stateId);
	}
	
	@Override
	public List<State> findAllStates() {
		return stateDAO.findAll();
	}
	
	@Override
	public List<State> findAllActiveStates() {
		return stateDAO.findAllActiveStates();
	}
}
