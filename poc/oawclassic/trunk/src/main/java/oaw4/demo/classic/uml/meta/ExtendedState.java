package oaw4.demo.classic.uml.meta;

import org.openarchitectureware.meta.uml.state.State;

public class ExtendedState extends State {
	
	private boolean visible;

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}
}
