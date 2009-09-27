package com.witchcraft.domain;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

@Name("myBean")
@Scope(ScopeType.CONVERSATION)
public class MyBean {

	private String text = "domain";

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	
}
