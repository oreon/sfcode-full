package com.nas.recovery.web.action.common;

import org.jboss.seam.annotations.Name;

@Name("friendshipRequest")
public class FriendShipRequest implements java.io.Serializable {
	
	String from ;
	
	String to;
	
	String description;

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
