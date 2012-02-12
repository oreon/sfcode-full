package com.hrb.tservices.dtos;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Date;
import javax.ws.rs.core.Response;

@javax.xml.bind.annotation.XmlRootElement
public class FaqCategory {

	private String name;

	private long id;

	private FaqCategoryList faqCategoryList;

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setFaqCategoryList(FaqCategoryList faqCategoryList) {
		this.faqCategoryList = faqCategoryList;
	}

	public FaqCategoryList getFaqCategoryList() {
		return faqCategoryList;
	}

}
