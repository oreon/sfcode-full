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
public abstract class GetTaxQuestionsBase
		extends
			org.witchcraft.seam.action.BaseWebAction {

	/** 
	 * 
	  
	 *	  @param categoryIds  String Comma delimted string consisting of article ids e.g 3,4,5  - if not provided questions from  all categories will be returned  
	 *	  @param language  String   
	 *	  @param pageNumber  Integer   
	 *	  @param pageSize  Integer   
	 *	  @param securityKey  String   
	 *
	 * @return FaqList 
	 */
	@Restrict("#{s:hasPermission('GetTaxQuestions','getTaxQuestions')}")
	public com.hrb.tservices.dtos.FaqList getTaxQuestions(String categoryIds,
			String language, Integer pageNumber, Integer pageSize,
			String securityKey) {

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

		return doGetTaxQuestions(categoryIds, language, pageNumber, pageSize,
				securityKey);
	}

	/** 
	 * []
	 */

	protected abstract com.hrb.tservices.dtos.FaqList doGetTaxQuestions(
			String categoryIds, String language, Integer pageNumber,
			Integer pageSize, String securityKey);

	/** 
	 * 
	  
	 *	  @param language  String   
	 *	  @param securityKey  String   
	 *
	 * @return FaqCategoryList 
	 */
	@Restrict("#{s:hasPermission('GetTaxQuestions','getCategories')}")
	public com.hrb.tservices.dtos.FaqCategoryList getCategories(
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

	protected abstract com.hrb.tservices.dtos.FaqCategoryList doGetCategories(
			String language, String securityKey);

	/** 
	 * 
	  @param questionId  Long   
	 *	  @param rating  Integer   
	 *	  
	 *	  @param securityKey  String   
	 *
	 * @return RateQuestionResponse 
	 */
	@Restrict("#{s:hasPermission('GetTaxQuestions','updateQuestionRating')}")
	public com.hrb.tservices.dtos.RateQuestionResponse updateQuestionRating(
			Long questionId, Integer rating, String securityKey) {

		StringBuilder exceptionMessages = new StringBuilder();

		try {

			AssertContractCondition.enforce(questionId != null,
					"questionId is required");

		} catch (ContractViolationException cve) {
			exceptionMessages.append(cve.getMessage() + ";");
		}

		try {

			AssertContractCondition.enforce(rating != null,
					"rating is required");

			AssertContractCondition.enforce(rating > 0 && rating < 6,
					"rating value should be between 1 and 5");

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

		return doUpdateQuestionRating(questionId, rating, securityKey);
	}

	/** 
	 * []
	 */

	protected abstract com.hrb.tservices.dtos.RateQuestionResponse doUpdateQuestionRating(
			Long questionId, Integer rating, String securityKey);

	/** 
	 * 
	  @param questionId  Long   
	 *	  
	 *	  @param language  String   
	 *	  @param securityKey  String   
	 *
	 * @return RateQuestionResponse 
	 */
	@Restrict("#{s:hasPermission('GetTaxQuestions','getQuestion')}")
	public com.hrb.tservices.dtos.RateQuestionResponse getQuestion(
			Long questionId, String language, String securityKey) {

		StringBuilder exceptionMessages = new StringBuilder();

		try {

			AssertContractCondition.enforce(questionId != null,
					"questionId is required");

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

		return doGetQuestion(questionId, language, securityKey);
	}

	/** 
	 * []
	 */

	protected abstract com.hrb.tservices.dtos.RateQuestionResponse doGetQuestion(
			Long questionId, String language, String securityKey);

}
