package com.oreon.olympics.domain.web.jsf;

import javax.faces.component.UIParameter;
import javax.faces.event.ActionEvent;

import org.witchcraft.model.jsf.BaseBackingBean;
import org.witchcraft.model.support.service.BaseService;

import com.oreon.olympics.domain.Tournament;
import com.oreon.olympics.domain.service.TournamentService;

public class TournamentBackingBean extends BaseBackingBean<Tournament> {

	private Tournament tournament = new Tournament();

	private TournamentService tournamentService;

	public void setTournamentService(TournamentService tournamentService) {
		this.tournamentService = tournamentService;
	}

	public Tournament getTournament() {
		return tournament;
	}

	public void set(Tournament tournament) {
		this.tournament = tournament;
	}

	@SuppressWarnings("unchecked")
	public BaseService<Tournament> getBaseService() {
		return tournamentService;
	}

	public Tournament getEntity() {
		return getTournament();
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

		tournament = tournamentService.load(id);
	}

}
