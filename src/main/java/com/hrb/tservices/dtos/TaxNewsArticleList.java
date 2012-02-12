package com.hrb.tservices.dtos;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Date;
import javax.ws.rs.core.Response;

@javax.xml.bind.annotation.XmlRootElement
public class TaxNewsArticleList extends com.hrb.tservices.dtos.BaseDTO {

	private java.util.List<com.hrb.tservices.dtos.TaxNewsArticle> taxNewsArticles = new ArrayList<com.hrb.tservices.dtos.TaxNewsArticle>();

	public void setTaxNewsArticles(List<TaxNewsArticle> taxNewsArticles) {
		this.taxNewsArticles = taxNewsArticles;
	}

	public List<TaxNewsArticle> getTaxNewsArticles() {
		return taxNewsArticles;
	}

}
