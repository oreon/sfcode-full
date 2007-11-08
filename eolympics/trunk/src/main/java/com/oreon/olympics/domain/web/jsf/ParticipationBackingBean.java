package com.oreon.olympics.domain.web.jsf;

import javax.faces.component.UIParameter;
import javax.faces.event.ActionEvent;

import org.witchcraft.model.jsf.BaseBackingBean;
import org.witchcraft.model.support.service.BaseService;

import com.oreon.olympics.domain.Participation;
import com.oreon.olympics.domain.service.ParticipationService;

public class ParticipationBackingBean extends BaseBackingBean<Participation> {

	private Participation participation = new Participation();

	private ParticipationService participationService;

	public void setParticipationService(
			ParticipationService participationService) {
		this.participationService = participationService;
	}

	public Participation getParticipation() {
		return participation;
	}

	public void set(Participation participation) {
		this.participation = participation;
	}

	@SuppressWarnings("unchecked")
	public BaseService<Participation> getBaseService() {
		return participationService;
	}

	public Participation getEntity() {
		return getParticipation();
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

		participation = participationService.load(id);
	}

}
