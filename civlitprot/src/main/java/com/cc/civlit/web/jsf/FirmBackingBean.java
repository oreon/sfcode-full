package com.cc.civlit.web.jsf;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.faces.event.ValueChangeEvent;

import org.apache.log4j.Logger;
import org.witchcraft.model.data.location.City;
import org.witchcraft.model.data.location.Country;
import org.witchcraft.model.data.location.State;

public class FirmBackingBean extends FirmBackingBeanBase implements
		java.io.Serializable {

	private static final Logger log = Logger.getLogger(FirmBackingBean.class);
	private static final long serialVersionUID = 1L;
	private static List<State> listStates = new ArrayList<State>();

	public List<City> autoCompleteCity(Object enteredText) {
		List<City> cities = new ArrayList();
		log.debug("autocomplete state called for " + enteredText);

		for (City city : getCities()) {
			String cityName = city.getName().toUpperCase();
			String text = ((String) enteredText).toUpperCase();
			if (cityName.startsWith(text)) {
				cities.add(city);
			}
		}

		return cities;
	}

	public List autoCompleteState(Object enteredText) {
		List<State> results = new ArrayList();
		log.debug("autocomplete state called for " + enteredText);

		for (State state : getStatesList()) {
			String stateName = state.getName().toUpperCase();
			String text = ((String) enteredText).toUpperCase();
			if (stateName.startsWith(text)) {
				results.add(state);
			}
		}

		return results;
	}

	private static List<State> getStatesList() {
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

	private static List<City> getCities() {
		State ontario = getStateByName("Ontario");
		State ohio = getStateByName("Ohio");
		
		return Arrays.asList(new City[] { new City("Scarborough", ontario), 
				new City("Toronto", ontario), new City("Missauga", ontario),
				new City("Columbus", ohio) } );
				//"Kingston", "Ottawa", "Hamilton" });
	}
	
	public static State getStateByName(String name){
		for (State state : getStatesList()) {
			if(state.getName().equalsIgnoreCase(name))
				return state;
		}
		return null;
	}

	/*
	 * private static List<State> getStatesList() { if (listStates.isEmpty()) {
	 * Country us = new Country("USA", "US", 1); Country ca = new
	 * Country("Canada", "CA", 2);
	 * 
	 * listStates.add(new State(us, "Ohio")); listStates.add(new State(us,
	 * "California")); listStates.add(new State(us, "Kansas"));
	 * listStates.add(new State(us, "Philadelphia"));
	 * 
	 * listStates.add(new State(ca, "Ontario")); listStates.add(new State(ca,
	 * "PEI")); } return listStates; }
	 */

	public void handleStateChanged(ValueChangeEvent vce) {
		String stateName = (String) vce.getNewValue();
		updateState(stateName);
	}

	private void updateState(String stateName) {
		System.out.println("handle Value Change with " + stateName);
		State state = getStateByName(stateName);
		if( state != null ){
			System.out.println("setting country to " + state.getCountry());
			firm.getContactDetails().setCountry(state.getCountry().getCountryName());
		}
	}
	
	public void handleCityChanged(ValueChangeEvent vce){
		String cityName = (String)vce.getNewValue();
		City city = getCityByName(cityName);
		if( city != null ){
			firm.getContactDetails().setState(city.getState().getName());
			updateState(city.getState().getName());
			//System.out.println("setting country to " + state.getCountry());
			//firm.getContactDetails().setCountry(state.getCountry().getCountryName());
		}
	}

	private City getCityByName(String cityName) {
		for (City city : getCities()) {
			if(city.getName().equalsIgnoreCase(cityName))
				return city;
		}
		return null;
	}
	

}
