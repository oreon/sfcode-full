package oaw4.demo.classic.uml.meta;

import org.apache.commons.lang.StringUtils;
import org.openarchitectureware.meta.uml.state.Branch;
import org.openarchitectureware.meta.uml.state.State;
import org.openarchitectureware.meta.uml.state.Transition;

public class ViewState extends State {

	private static final String DIR_SEPERATOR = "/";

	private String message;

	private String fields;

	private String template;

	private String backingBean;

	private String component;

	private String locationDir;

	private boolean commentOutGeneratedCode;

	public boolean isCommentOutGeneratedCode() {
		return commentOutGeneratedCode;
	}

	public void setCommentOutGeneratedCode(boolean commentOutGeneratedCode) {
		this.commentOutGeneratedCode = commentOutGeneratedCode;
	}

	public String getComponent() {
		return component;
	}

	public void setComponent(String component) {
		this.component = component;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	/**
	 * The message to be displayed
	 * 
	 * @return
	 */
	public String getMessage() {

		Branch branch;
		Transition tr;
		// tr.Action().M

		// tr.TargetVertex().getMetaClass().getSimpleName()
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * The form fields
	 * 
	 * @return
	 */
	public String getFields() {
		return fields;
	}

	public void setFields(String fields) {
		this.fields = fields;
	}

	public ViewStateMachine getStateMachine() {
		try {
			return (ViewStateMachine) StateMachine();
		} catch (Exception ex) {
			// ex.printStackTrace();
			return null;
		}
	}

	/**
	 * Get the template associated with this state - if null return parent state
	 * machine's template
	 * 
	 * @return
	 */
	public String getTemplate() {
		if (template == null) {
			return StateMachine().Documentation(); // FIXME: Majour kludge -
													// need a way
		}
		return template;
	}

	public String getBackingBean() {
		return backingBean;
	}

	public void setBackingBean(String backingBean) {
		this.backingBean = backingBean;
	}

	public String getLocationDir() {
		String result = StringUtils.trim(locationDir);

		if (result == null)
			result = StateMachine().NameS().trim();

		if (!result.trim().startsWith(DIR_SEPERATOR))
			result = DIR_SEPERATOR + result;

		if (result.equals(DIR_SEPERATOR))
			return StringUtils.EMPTY;

		return result;
	}

	public void setLocationDir(String locationDir) {
		this.locationDir = locationDir;
	}
}
