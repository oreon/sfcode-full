package com.hrb.tservices.dtos;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Date;
import javax.ws.rs.core.Response;

@javax.xml.bind.annotation.XmlRootElement
public class TaxNewsCategory {

	private TaxNewsCategoryList taxNewsCategoryList;

	private long id;

	private String name;

	public void setTaxNewsCategoryList(TaxNewsCategoryList taxNewsCategoryList) {
		this.taxNewsCategoryList = taxNewsCategoryList;
	}

	public TaxNewsCategoryList getTaxNewsCategoryList() {
		return taxNewsCategoryList;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
