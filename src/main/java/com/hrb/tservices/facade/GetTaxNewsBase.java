package com.hrb.tservices.facade;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Date;
import javax.ws.rs.core.Response;

import org.jboss.seam.annotations.security.Restrict;
import org.witchcraft.exceptions.ContractViolationException;
import org.witchcraft.dbc.AssertContractCondition;
import org.apache.commons.lang.StringUtils;

/** 
 * 
 */
public abstract class GetTaxNewsBase
		extends
			org.witchcraft.seam.action.BaseWebAction {

	/** 
	 * 
	  
	 *	  @param language  String   
	 *	  @param securityKey  String   
	 *
	 * @return TaxNewsCategoryList 
	 */
	@Restrict("#{s:hasPermission('GetTaxNews','getCategories')}")
	public com.hrb.tservices.dtos.TaxNewsCategoryList getCategories(
			String language, String securityKey) {

		StringBuilder exceptionMessages = new StringBuilder();

		try {

			AssertContractCondition.enforce(securityKey != null,
					"securityKey is required");

		} catch (ContractViolationException cve) {
			exceptionMessages.append(cve.getMessage() + ";");
		}

		if (!StringUtils.isEmpty(exceptionMessages.toString())) {
			throw new ContractViolationException(exceptionMessages.toString());
		}

		return doGetCategories(language, securityKey);
	}

	/** 
	 * []
	 */

	protected abstract com.hrb.tservices.dtos.TaxNewsCategoryList doGetCategories(
			String language, String securityKey);

	/** 
	 * 
	  
	 *	  @param categoryIds  String Comma delimted string consisting of article ids e.g 3,4,5  - if not provided articles from  all categories will be returned  
	 *	  @param language  String   
	 *	  @param pageNumber  Integer   
	 *	  @param pageSize  Integer   
	 *	  @param securityKey  String   
	 *
	 * @return TaxNewsArticleList 
	 */
	@Restrict("#{s:hasPermission('GetTaxNews','getNewsArticles')}")
	public com.hrb.tservices.dtos.TaxNewsArticleList getNewsArticles(
			String categoryIds, String language, Integer pageNumber,
			Integer pageSize, String securityKey) {

		StringBuilder exceptionMessages = new StringBuilder();

		try {

			AssertContractCondition.enforce(securityKey != null,
					"securityKey is required");

		} catch (ContractViolationException cve) {
			exceptionMessages.append(cve.getMessage() + ";");
		}

		if (!StringUtils.isEmpty(exceptionMessages.toString())) {
			throw new ContractViolationException(exceptionMessages.toString());
		}

		return doGetNewsArticles(categoryIds, language, pageNumber, pageSize,
				securityKey);
	}

	/** 
	 * []
	 */

	protected abstract com.hrb.tservices.dtos.TaxNewsArticleList doGetNewsArticles(
			String categoryIds, String language, Integer pageNumber,
			Integer pageSize, String securityKey);

	/** 
	 * 
	  @param articleId  Long   
	 *	  
	 *	  @param language  String   
	 *	  @param securityKey  String   
	 *
	 * @return Response 
	 */
	@Restrict("#{s:hasPermission('GetTaxNews','getArticle')}")
	public Response getArticle(Long articleId, String language,
			String securityKey) {

		StringBuilder exceptionMessages = new StringBuilder();

		try {

			AssertContractCondition.enforce(articleId != null,
					"articleId is required");

		} catch (ContractViolationException cve) {
			exceptionMessages.append(cve.getMessage() + ";");
		}

		try {

			AssertContractCondition.enforce(securityKey != null,
					"securityKey is required");

		} catch (ContractViolationException cve) {
			exceptionMessages.append(cve.getMessage() + ";");
		}

		if (!StringUtils.isEmpty(exceptionMessages.toString())) {
			throw new ContractViolationException(exceptionMessages.toString());
		}

		return doGetArticle(articleId, language, securityKey);
	}

	/** 
	 * []
	 */

	protected abstract Response doGetArticle(Long articleId, String language,
			String securityKey);

}
