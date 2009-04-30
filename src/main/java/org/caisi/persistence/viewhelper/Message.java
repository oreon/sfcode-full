package org.caisi.persistence.viewhelper;


public class Message {

	public static final String SEPERATOR = ";;";
	boolean error;
	String text;

	public boolean isError() {
		return error;
	}

	public Message(String text, boolean error) {
		super();
		this.error = error;
		this.text = text;
	}

	public void setError(boolean error) {
		this.error = error;
	}

	public String getText() {		
		return text;
	}
	
	
	public void setText(String text) {
		this.text = text;
	}

}
