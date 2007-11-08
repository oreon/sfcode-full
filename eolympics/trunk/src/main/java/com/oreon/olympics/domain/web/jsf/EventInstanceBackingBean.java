package com.oreon.olympics.domain.web.jsf;

import javax.faces.component.UIParameter;
import javax.faces.event.ActionEvent;

import org.witchcraft.model.jsf.BaseBackingBean;
import org.witchcraft.model.support.service.BaseService;

import com.oreon.olympics.domain.EventInstance;
import com.oreon.olympics.domain.service.EventInstanceService;

public class EventInstanceBackingBean extends BaseBackingBean<EventInstance> {

	private EventInstance eventInstance = new EventInstance();

	private EventInstanceService eventInstanceService;

	public void setEventInstanceService(
			EventInstanceService eventInstanceService) {
		this.eventInstanceService = eventInstanceService;
	}

	public EventInstance getEventInstance() {
		return eventInstance;
	}

	public void set(EventInstance eventInstance) {
		this.eventInstance = eventInstance;
	}

	@SuppressWarnings("unchecked")
	public BaseService<EventInstance> getBaseService() {
		return eventInstanceService;
	}

	public EventInstance getEntity() {
		return getEventInstance();
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

		eventInstance = eventInstanceService.load(id);
	}

}
