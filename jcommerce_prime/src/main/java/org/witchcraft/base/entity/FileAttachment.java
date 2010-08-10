package org.witchcraft.base.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class FileAttachment {
	
	@Column(length = 4194304)
	private byte[] data;

	private String name;
	
	private String contentType;

	
	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public String getContentType() {
		return this.contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

}
