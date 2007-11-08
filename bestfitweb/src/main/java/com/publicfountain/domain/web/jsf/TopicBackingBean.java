package com.publicfountain.domain.web.jsf;

import javax.faces.component.UIParameter;
import javax.faces.event.ActionEvent;

import org.witchcraft.model.jsf.BaseBackingBean;
import org.witchcraft.model.support.service.BaseService;

import com.publicfountain.domain.Topic;
import com.publicfountain.domain.service.TopicService;

public class TopicBackingBean extends BaseBackingBean<Topic> {

	private Topic topic = new Topic();

	private TopicService topicService;

	public void setTopicService(TopicService topicService) {
		this.topicService = topicService;
	}

	public Topic getTopic() {
		return topic;
	}

	public void set(Topic topic) {
		this.topic = topic;
	}

	@SuppressWarnings("unchecked")
	public BaseService<Topic> getBaseService() {
		return topicService;
	}

	public Topic getEntity() {
		return getTopic();
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

		topic = topicService.load(id);
	}

}
