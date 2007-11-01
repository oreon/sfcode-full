package oaw4.demo.classic.uml.meta;

import org.openarchitectureware.meta.uml.classifier.Attribute;

/** Indicates a given field will be displayed in a report
 * @author jsingh
 *
 */
public class ReportField extends Attribute {
	private String fieldExpression;
	
	private boolean visible = true;

	public String getFieldExpression() {
		return fieldExpression;
	}

	public void setFieldExpression(String fieldExpression) {
		this.fieldExpression = fieldExpression;
	}

	/** Whether this field should be shown - typically used as part
	 *  of derived fields e.g field "name" consisting of "firstName, LastName"
	 * @return
	 */
	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
}
