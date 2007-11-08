package com.oreon.olympics.domain.web.jsf;

import javax.faces.component.UIParameter;
import javax.faces.event.ActionEvent;

import org.witchcraft.model.jsf.BaseBackingBean;
import org.witchcraft.model.support.service.BaseService;

import com.oreon.olympics.domain.Event;
import com.oreon.olympics.domain.service.EventService;

public class EventBackingBean extends BaseBackingBean<Event> {

	private Event event = new Event();

	private EventService eventService;

	public void setEventService(EventService eventService) {
		this.eventService = eventService;
	}

	public Event getEvent() {
		return event;
	}

	public void set(Event event) {
		this.event = event;
	}

	@SuppressWarnings("unchecked")
	public BaseService<Event> getBaseService() {
		return eventService;
	}

	public Event getEntity() {
		return getEvent();
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

		event = eventService.load(id);
	}

}
