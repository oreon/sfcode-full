package com.cc.civlit.web.jsf;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.faces.event.ValueChangeEvent;

import org.apache.log4j.Logger;
import org.witchcraft.model.data.location.City;
import org.witchcraft.model.data.location.Country;
import org.witchcraft.model.data.location.State;

import com.cc.civlit.domain.LocationDataProvider;
import com.cc.civlit.domain.LocationDataProviderImpl;

public class FirmBackingBean extends FirmBackingBeanBase implements
		java.io.Serializable {

	private static final Logger log = Logger.getLogger(FirmBackingBean.class);
	private static final long serialVersionUID = 1L;
	private static List<State> listStates = new ArrayList<State>();
	LocationDataProvider locationDataProvider = new LocationDataProviderImpl();

	
	
	public List<City> autoCompleteCity(Object enteredText) {
		return genericAutoComplete(enteredText, locationDataProvider.getCities(), "getName");
	}
	
	public List autoCompleteState(Object enteredText) {
		return genericAutoComplete(enteredText, locationDataProvider.getStates(), "getName");
	}


	public void handleStateChanged(ValueChangeEvent vce) {
		String stateName = (String) vce.getNewValue();
		updateState(stateName);
	}

	private void updateState(String stateName) {
		System.out.println("handle Value Change with " + stateName);
		State state = locationDataProvider.getStateByName(stateName);
		if( state != null ){
			System.out.println("setting country to " + state.getCountry());
			firm.getContactDetails().setCountry(state.getCountry().getIsoCode());
		}
	}
	
	public void handleCityChanged(ValueChangeEvent vce){
		String cityName = (String)vce.getNewValue();
		City city = locationDataProvider.getCityByName(cityName);
		if( city != null ){
			firm.getContactDetails().setState(city.getState().getName());
			updateState(city.getState().getName());
		}
	}
	
	
	public <S>List genericAutoComplete(Object enteredText, List<S> data, String stringFunctionName){
		List<S> returnList  = new ArrayList();
		log.debug("autocomplete state called for " + enteredText);

		for (S t : data) {
			String text = ((String) enteredText).toUpperCase();
			//t.getClass().getMethod("getName")
			Method method = null;
			String nameFromObject = null;
			try {
				method = t.getClass().getMethod(stringFunctionName);
				nameFromObject = (String) method.invoke(t);
			} catch (Exception e) {
				log.error("An exception occured trying to invoke " + stringFunctionName, e);
				return null;
			} 
			
			if (nameFromObject.startsWith(text)) {
				returnList.add(t);
			}
		}

		return returnList;
	}

}
