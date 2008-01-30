package oaw4.demo.classic.uml.meta;

import org.openarchitectureware.meta.uml.state.StateMachine;

/** 
 * Represents a stereotype for viewable state machine 
 * @author jesing
 *
 */
public class ViewStateMachine extends StateMachine{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5271528760454306849L;
	
	private String template;
	
	

	/** The facelets template to be used for this state machine - would be applied to all viewstaes
	 * in this statemachine
	 * @return
	 */
	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}
	
	

}
