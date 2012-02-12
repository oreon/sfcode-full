package com.hrb.tservices.dtos;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Date;
import javax.ws.rs.core.Response;

@javax.xml.bind.annotation.XmlRootElement
public class TaxNewsArticle {

	private String title;

	private String text;

	private String link;

	private TaxNewsArticleList taxNewsArticleList;

	private Long id;

	private Long categoryId;

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getLink() {
		return link;
	}

	public void setTaxNewsArticleList(TaxNewsArticleList taxNewsArticleList) {
		this.taxNewsArticleList = taxNewsArticleList;
	}

	public TaxNewsArticleList getTaxNewsArticleList() {
		return taxNewsArticleList;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public Long getCategoryId() {
		return categoryId;
	}

}
