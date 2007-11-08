package com.oreon.olympics.domain.web.jsf;

import javax.faces.component.UIParameter;
import javax.faces.event.ActionEvent;

import org.witchcraft.model.jsf.BaseBackingBean;
import org.witchcraft.model.support.service.BaseService;

import com.oreon.olympics.domain.Athlete;
import com.oreon.olympics.domain.service.AthleteService;

public class AthleteBackingBean extends BaseBackingBean<Athlete> {

	private Athlete athlete = new Athlete();

	private AthleteService athleteService;

	public void setAthleteService(AthleteService athleteService) {
		this.athleteService = athleteService;
	}

	public Athlete getAthlete() {
		return athlete;
	}

	public void set(Athlete athlete) {
		this.athlete = athlete;
	}

	@SuppressWarnings("unchecked")
	public BaseService<Athlete> getBaseService() {
		return athleteService;
	}

	public Athlete getEntity() {
		return getAthlete();
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

		athlete = athleteService.load(id);
	}

}
