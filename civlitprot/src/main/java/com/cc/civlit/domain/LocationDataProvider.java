package com.cc.civlit.domain;

import java.util.List;

import org.witchcraft.model.data.location.City;
import org.witchcraft.model.data.location.Country;
import org.witchcraft.model.data.location.State;;

/** This interface should be implemented by clients who want to provide 
 * @author jsingh
 *
 */
public interface LocationDataProvider {
	
	/**
	 * @return
	 */
	public List<City> getCities();
	/**
	 * @return
	 */
	public List<State> getStates();
	/**
	 * @return
	 */
	public List<Country> getCountries();
	
	/** Return a city with the given name
	 * @param name
	 * @return null if not found
	 */
	public City getCityByName(String name);
	
	/**  Return a State with the given name
	 * @param name
	 * @return
	 */
	public State getStateByName(String name);
	
	/** Return a country with the given name
	 * @param name - 
	 * @return - null if the country is not found
	 */
	public Country getCountryByName(String name);
	
}
