package cntrls;

import java.util.List;

import org.eclipse.swt.widgets.Combo;


public class ComboControlContainer extends ControlContainer{
	
	public ComboControlContainer(Combo combo, String label, List<String> choices) {
		super(combo, label);
		for (String choice : choices) {
			combo.add(choice);
		}
		
	}
	
	public  String getValue() {
		Combo combo = (Combo)getControl();
		if(combo.getSelectionIndex() >= 0)
			return combo.getItem(combo.getSelectionIndex());
		return "";
	}
}
