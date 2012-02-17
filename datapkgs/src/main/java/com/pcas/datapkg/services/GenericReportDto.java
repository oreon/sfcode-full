package com.pcas.datapkg.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Date;
import javax.ws.rs.core.Response;

@javax.xml.bind.annotation.XmlRootElement
public class GenericReportDto {

	private String data;

	public void setData(String data) {
		this.data = data;
	}

	public String getData() {
		return data;
	}

}
