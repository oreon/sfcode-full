package oaw4.demo.classic.uml.meta;

import org.openarchitectureware.meta.uml.classifier.Operation;

public class NamedQuery extends Operation{

	private String text;
	private String name;
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
