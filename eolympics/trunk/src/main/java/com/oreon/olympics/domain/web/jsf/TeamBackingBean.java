package com.oreon.olympics.domain.web.jsf;

import javax.faces.component.UIParameter;
import javax.faces.event.ActionEvent;

import org.witchcraft.model.jsf.BaseBackingBean;
import org.witchcraft.model.support.service.BaseService;

import com.oreon.olympics.domain.Team;
import com.oreon.olympics.domain.service.TeamService;

public class TeamBackingBean extends BaseBackingBean<Team> {

	private Team team = new Team();

	private TeamService teamService;

	public void setTeamService(TeamService teamService) {
		this.teamService = teamService;
	}

	public Team getTeam() {
		return team;
	}

	public void set(Team team) {
		this.team = team;
	}

	@SuppressWarnings("unchecked")
	public BaseService<Team> getBaseService() {
		return teamService;
	}

	public Team getEntity() {
		return getTeam();
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

		team = teamService.load(id);
	}

}
