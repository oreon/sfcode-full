package com.hrb.tservices.web.action.facade;

import javax.ws.rs.FormParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import javax.ws.rs.core.Response;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.security.Restrict;

import org.jboss.seam.framework.EntityHome;
import org.jboss.seam.framework.Home;
import org.jboss.seam.resteasy.ResourceHome;
import org.jboss.seam.resteasy.ResourceQuery;

import java.util.List;

@Name("getTaxNewsService")
@Path("getTaxNewsService")
public class GetTaxNewsService implements java.io.Serializable {

	@In(create = true)
	com.hrb.tservices.facade.GetTaxNews getTaxNews;

	@GET
	@Path("getCategories")
	@Produces("application/xml")
	//@Restrict("#{s:hasPermission('GetTaxNews','getCategories')}")
	public com.hrb.tservices.dtos.TaxNewsCategoryList getCategories(
			@QueryParam("language") String language,
			@QueryParam("securityKey") String securityKey) {
		return getTaxNews.getCategories(language, securityKey);
	}

	@GET
	@Path("getNewsArticles")
	@Produces("application/xml")
	//@Restrict("#{s:hasPermission('GetTaxNews','getNewsArticles')}")
	public com.hrb.tservices.dtos.TaxNewsArticleList getNewsArticles(
			@QueryParam("categoryIds") String categoryIds,
			@QueryParam("language") String language,
			@QueryParam("pageNumber") Integer pageNumber,
			@QueryParam("pageSize") Integer pageSize,
			@QueryParam("securityKey") String securityKey) {
		return getTaxNews.getNewsArticles(categoryIds, language, pageNumber,
				pageSize, securityKey);
	}

	@GET
	@Path("getArticle")
	@Produces("application/xml")
	//@Restrict("#{s:hasPermission('GetTaxNews','getArticle')}")
	public Response getArticle(@QueryParam("articleId") Long articleId,
			@QueryParam("language") String language,
			@QueryParam("securityKey") String securityKey) {
		return getTaxNews.getArticle(articleId, language, securityKey);
	}

}
