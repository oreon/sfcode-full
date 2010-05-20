package cntrls;

import org.eclipse.swt.widgets.Text;

public class TextControlContainer extends ControlContainer{
	
	public TextControlContainer(Text control, String label) {
		super(control, label);
		
	}
	
	public  String getValue() {
		return ((Text)getControl()).getText();
	}
}
