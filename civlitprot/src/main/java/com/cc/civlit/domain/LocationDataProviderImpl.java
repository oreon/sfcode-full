package com.cc.civlit.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.jfree.util.Log;
import org.witchcraft.model.data.location.City;
import org.witchcraft.model.data.location.Country;
import org.witchcraft.model.data.location.State;

public class LocationDataProviderImpl implements LocationDataProvider {

	private static final Logger logger = Logger.getLogger(LocationDataProvider.class);

	public List<City> getCities() {
		State ontario = getStateByName("Ontario");
		State ohio = getStateByName("Ohio");

		return Arrays.asList(new City[] { new City("Scarborough", ontario),
				new City("Simcoe", ontario), new City("Toronto", ontario),
				new City("Missauga", ontario), new City("Columbus", ohio) });
		// "Kingston", "Ottawa", "Hamilton" });

	}

	public List<Country> getCountries() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<State> getStates() {
		List<State> listStates = new ArrayList<State>();
		if (listStates.isEmpty()) {
			Country us = new Country("USA", "US", 1);
			Country ca = new Country("Canada", "CA", 2);

			listStates.add(new State(us, "Ohio"));
			listStates.add(new State(us, "California"));
			listStates.add(new State(us, "Kansas"));
			listStates.add(new State(us, "Philadelphia"));

			listStates.add(new State(ca, "Ontario"));
			listStates.add(new State(ca, "PEI"));
		}
		return listStates;
	}

	public State getStateByName(String name) {
		for (State state : getStates()) {
			if (state.getName().equalsIgnoreCase(name))
				return state;
		}
		logger.info("No state found with name " + name);
		return null;
	}

	public City getCityByName(String name) {
		for (City city : getCities()) {
			if (city.getName().equalsIgnoreCase(name))
				return city;
		}
		logger.info("No city found with name " + name);
		return null;

	}

	public Country getCountryByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
