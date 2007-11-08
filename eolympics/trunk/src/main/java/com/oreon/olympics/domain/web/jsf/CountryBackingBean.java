package com.oreon.olympics.domain.web.jsf;

import javax.faces.component.UIParameter;
import javax.faces.event.ActionEvent;

import org.witchcraft.model.jsf.BaseBackingBean;
import org.witchcraft.model.support.service.BaseService;

import com.oreon.olympics.domain.Country;
import com.oreon.olympics.domain.service.CountryService;

public class CountryBackingBean extends BaseBackingBean<Country> {

	private Country country = new Country();

	private CountryService countryService;

	public void setCountryService(CountryService countryService) {
		this.countryService = countryService;
	}

	public Country getCountry() {
		return country;
	}

	public void set(Country country) {
		this.country = country;
	}

	@SuppressWarnings("unchecked")
	public BaseService<Country> getBaseService() {
		return countryService;
	}

	public Country getEntity() {
		return getCountry();
	}

	public String search() {
		action = SEARCH;
		return "search";
	}

	/** Returns a success string upon selection of an entity in model - majority of work is done
	 * in the actionListener selectEntity
	 * @return - "success" if everthing goes fine
	 * @see - 
	 */
	public String select() {
		return "edit";
	}

	/** This action Listener Method is called when a row is clicked in the dataTable
	 *  
	 * @param event contains the database id of the row being selected 
	 */
	public void selectEntity(ActionEvent event) {

		UIParameter component = (UIParameter) event.getComponent()
				.findComponent("editId");

		// parse the value of the UIParameter component    	 
		long id = Long.parseLong(component.getValue().toString());

		country = countryService.load(id);
	}

}
