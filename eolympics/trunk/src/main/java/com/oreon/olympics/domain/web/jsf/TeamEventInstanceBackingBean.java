package com.oreon.olympics.domain.web.jsf;

import javax.faces.component.UIParameter;
import javax.faces.event.ActionEvent;

import org.witchcraft.model.jsf.BaseBackingBean;
import org.witchcraft.model.support.service.BaseService;

import com.oreon.olympics.domain.TeamEventInstance;
import com.oreon.olympics.domain.service.TeamEventInstanceService;

public class TeamEventInstanceBackingBean
		extends
			BaseBackingBean<TeamEventInstance> {

	private TeamEventInstance teamEventInstance = new TeamEventInstance();

	private TeamEventInstanceService teamEventInstanceService;

	public void setTeamEventInstanceService(
			TeamEventInstanceService teamEventInstanceService) {
		this.teamEventInstanceService = teamEventInstanceService;
	}

	public TeamEventInstance getTeamEventInstance() {
		return teamEventInstance;
	}

	public void set(TeamEventInstance teamEventInstance) {
		this.teamEventInstance = teamEventInstance;
	}

	@SuppressWarnings("unchecked")
	public BaseService<TeamEventInstance> getBaseService() {
		return teamEventInstanceService;
	}

	public TeamEventInstance getEntity() {
		return getTeamEventInstance();
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

		teamEventInstance = teamEventInstanceService.load(id);
	}

}
