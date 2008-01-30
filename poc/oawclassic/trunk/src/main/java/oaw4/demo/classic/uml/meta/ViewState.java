package oaw4.demo.classic.uml.meta;

import org.openarchitectureware.meta.uml.state.State;
import org.openarchitectureware.meta.uml.state.StateMachine;

public class ViewState extends State{
	
	private String message;
	
	private String fields;
	
	private String template;
	
	private String backingBean;

	public void setTemplate(String template) {
		this.template = template;
	}

	/** The message to be displayed
	 * @return
	 */
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	/** The form fields 
	 * @return
	 */
	public String getFields() {
		return fields;
	}

	public void setFields(String fields) {
		this.fields = fields;
	}
	
	public ViewStateMachine getStateMachine(){
		try{
		return (ViewStateMachine) StateMachine();
		}catch(Exception ex){
			//ex.printStackTrace();
			return null;
		}
	}

	/** Get the template associated with this state - if null return parent state machine's 
	 * template
	 * @return
	 */
	public String getTemplate(){
		if ( template == null ){
			return StateMachine().Documentation(); //FIXME: Majour kludge - need a way
		}
		return template;
	}

	public String getBackingBean() {
		return backingBean;
	}

	public void setBackingBean(String backingBean) {
		this.backingBean = backingBean;
	}
}
