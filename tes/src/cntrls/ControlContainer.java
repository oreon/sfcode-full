package cntrls;

import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;

public abstract class ControlContainer {
	
	protected Control control;
	private String label;
	
	public ControlContainer(Control control, String label) {
		super();
		this.control = control;
		this.label = label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getLabel() {
		return label;
	}
	public void setControl(Control control) {
		this.control = control;
	}
	public Control getControl() {
		return control;
	}
	public abstract String getValue();
	
	

}
