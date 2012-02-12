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

@Name("getTaxQuestionsService")
@Path("getTaxQuestionsService")
public class GetTaxQuestionsService implements java.io.Serializable {

	@In(create = true)
	com.hrb.tservices.facade.GetTaxQuestions getTaxQuestions;

	@GET
	@Path("getTaxQuestions")
	@Produces("application/xml")
	//@Restrict("#{s:hasPermission('GetTaxQuestions','getTaxQuestions')}")
	public com.hrb.tservices.dtos.FaqList getTaxQuestions(
			@QueryParam("categoryIds") String categoryIds,
			@QueryParam("language") String language,
			@QueryParam("pageNumber") Integer pageNumber,
			@QueryParam("pageSize") Integer pageSize,
			@QueryParam("securityKey") String securityKey) {
		return getTaxQuestions.getTaxQuestions(categoryIds, language,
				pageNumber, pageSize, securityKey);
	}

	@GET
	@Path("getCategories")
	@Produces("application/xml")
	//@Restrict("#{s:hasPermission('GetTaxQuestions','getCategories')}")
	public com.hrb.tservices.dtos.FaqCategoryList getCategories(
			@QueryParam("language") String language,
			@QueryParam("securityKey") String securityKey) {
		return getTaxQuestions.getCategories(language, securityKey);
	}

	@GET
	@Path("updateQuestionRating")
	@Produces("application/xml")
	//@Restrict("#{s:hasPermission('GetTaxQuestions','updateQuestionRating')}")
	public com.hrb.tservices.dtos.RateQuestionResponse updateQuestionRating(
			@QueryParam("questionId") Long questionId,
			@QueryParam("rating") Integer rating,
			@QueryParam("securityKey") String securityKey) {
		return getTaxQuestions.updateQuestionRating(questionId, rating,
				securityKey);
	}

	@GET
	@Path("getQuestion")
	@Produces("application/xml")
	//@Restrict("#{s:hasPermission('GetTaxQuestions','getQuestion')}")
	public com.hrb.tservices.dtos.RateQuestionResponse getQuestion(
			@QueryParam("questionId") Long questionId,
			@QueryParam("language") String language,
			@QueryParam("securityKey") String securityKey) {
		return getTaxQuestions.getQuestion(questionId, language, securityKey);
	}

}
