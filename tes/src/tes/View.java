package tes;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.ViewPart;

import cntrls.ComboControlContainer;
import cntrls.ControlContainer;
import cntrls.ControlType;
import cntrls.TextControlContainer;

public class View extends ViewPart {
	public static final String ID = "tes.view";

	Map<String, ControlContainer> controls = new HashMap<String, ControlContainer>();
	String[] yesNo = { "Y", "N" };
	String[] choices =  { "Val1", "Val2", "Val3" };

	Object[] flds = {
			"Reference Number      ", ControlType.TEXT,
			
			"New Client      ", ControlType.YESNO,
			
			"Client Name", ControlType.TEXT, 
			
			"Client CID                  ", ControlType.TEXT,

			"Account Number              ", ControlType.TEXT,

			"Client Segment               ", ControlType.TEXT,

			"Business Nature              ", ControlType.TEXT,

			"Primary Geography Operation  ", ControlType.TEXT,

			"Cash Depost Value/Mnth       ", ControlType.TEXT,

			"Wire Transfer Value/Mnth     ", ControlType.TEXT,

			"Agent Name                  ", ControlType.TEXT,

			"Transit Number              ", ControlType.TEXT,

			"Customer Type                ", ControlType.TEXT,

			"NAFTA Region                ", ControlType.YESNO,

			"European Region              ", ControlType.YESNO,

			"Initial Rating              ", ControlType.COMBO,

			"Final Rating                 ", ControlType.COMBO,

			"Source of Review               ", ControlType.TEXT,

			"Relationship                   ", ControlType.TEXT,

			"UTR Raised", ControlType.COMBO
			
	};

	@Override
	public void createPartControl(Composite parent) {

		parent.setLayout(new GridLayout(4, false));
		
		for(int i = 0; i < flds.length; i+= 2){
			if( flds[i+1] == ControlType.COMBO){
				createComboControl(parent, (String)flds[i], Arrays.asList(choices) );
			}else if (flds[i+1] == ControlType.YESNO){
				createYesNoControl(parent, (String)flds[i]);
			}else
				createTextControl(parent, (String)flds[i]);
		}

	
		createBtn(parent);

	}

	private void createTextControl(Composite parent, String label) {
		new Label(parent, SWT.NONE).setText(label);
		Text text = new Text(parent, SWT.BORDER);
		controls.put(label, new TextControlContainer(text, label));
	}

	private void createComboControl(Composite parent, String label,
			List<String> choices) {
		new Label(parent, SWT.NONE).setText(label);
		Combo combo = new Combo(parent, SWT.DROP_DOWN | SWT.DROP_DOWN
				| SWT.BORDER | SWT.READ_ONLY);
		controls.put(label, new ComboControlContainer(combo, label, choices));
	}

	private void createYesNoControl(Composite parent, String label) {
		createComboControl(parent, label, Arrays.asList(yesNo));
	}

	@Override
	public void setFocus() {

	}

	private void createBtn(Composite parent) {
		Button btn = new Button(parent, SWT.NONE);
		btn.setText("INFO");
		btn.addListener(SWT.MouseDown, new Listener() {
			@Override
			public void handleEvent(Event event) {
				Set<String> keys = controls.keySet();
				for (String key : keys) {
					System.out.println(key + " :"
							+ (controls.get(key)).getValue());
				}
			}
		});
	}
}