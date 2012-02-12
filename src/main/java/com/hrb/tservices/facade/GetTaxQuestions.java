package com.hrb.tservices.facade;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;
import javax.ws.rs.core.Response;

import org.apache.commons.lang.StringUtils;
import org.drools.lang.DRLParser.query_id_return;
import org.drools.process.command.GetSessionClockCommand;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.w3c.dom.ranges.RangeException;
import org.witchcraft.exceptions.ContractViolationException;

import com.hrb.tservices.domain.department.Partner;
import com.hrb.tservices.domain.faq.FaqCategory;
import com.hrb.tservices.domain.faq.FaqQuestion;
import com.hrb.tservices.domain.faq.FaqQuestionMetrics;
import com.hrb.tservices.domain.faq.QuestionTranslation;
import com.hrb.tservices.domain.faq.Rating;
import com.hrb.tservices.domain.taxnews.Language;
import com.hrb.tservices.dtos.Faq;
import com.hrb.tservices.dtos.FaqCategoryList;
import com.hrb.tservices.dtos.FaqList;
import com.hrb.tservices.dtos.RateQuestionResponse;
import com.hrb.tservices.web.action.faq.FaqCategoryAction;
import com.hrb.tservices.web.action.faq.FaqCategoryListQuery;
import com.hrb.tservices.web.action.faq.FaqQuestionAction;
import com.hrb.tservices.web.action.faq.FaqQuestionMetricsAction;
import com.sun.org.apache.commons.beanutils.BeanUtils;

@Name("getTaxQuestions")
public class GetTaxQuestions extends GetTaxQuestionsBase {

	@In(create = true)
	FaqCategoryListQuery faqCategoryList;

	@In(create = true)
	FaqQuestionMetricsAction faqQuestionMetricsAction;

	@In(create = true)
	FaqCategoryAction faqCategoryAction;

	@In(create = true)
	FaqQuestionAction faqQuestionAction;

	@Override
	public RateQuestionResponse doUpdateQuestionRating(Long questionId,
			Integer rating, String securityKey) {

		QuestionTranslation transl = entityManager.find(
				QuestionTranslation.class, questionId);
		if (transl != null) {
			FaqQuestion faqQuestion = transl.getFaqQuestion();
			Rating ratingEntity = new Rating();
			ratingEntity.setFaqQuestion(faqQuestion);
			ratingEntity.setRating(rating);
			faqQuestion.addRating(ratingEntity);
			faqQuestionAction.persist(faqQuestion);

			RateQuestionResponse rateQuestionResponse = new RateQuestionResponse();
			rateQuestionResponse.setFaq(createDto(transl));
			return rateQuestionResponse;
		} else
			throw new ContractViolationException("No question with id "
					+ questionId + " exists");

	}

	public FaqCategoryList doGetCategories(String language, String securityKey) {

		List<com.hrb.tservices.dtos.FaqCategory> listFaqQuestion = new ArrayList<com.hrb.tservices.dtos.FaqCategory>();

		List<FaqCategory> catList = faqCategoryList.getResultList();

		Language lang = getLanguageEnum(language);

		for (FaqCategory newsCategory : catList) {
			com.hrb.tservices.dtos.FaqCategory dto = new com.hrb.tservices.dtos.FaqCategory();
			try {
				BeanUtils.copyProperties(dto, newsCategory);
				dto.setName(lang == Language.ENGLISH ? newsCategory.getName()
						: newsCategory.getFrenchName());
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}
			listFaqQuestion.add(dto);
		}

		FaqCategoryList faqCategoryList = new FaqCategoryList();
		faqCategoryList.setFaqCategorys(listFaqQuestion);

		return faqCategoryList;
	}

	protected FaqList doGetTaxQuestions(String categoryIds, String language,
			Integer pageNumber, Integer pageSize, String securityKey) {

		Language lang = getLanguageEnum(language);

		if (pageSize == null) {
			pageSize = DEFAULT_RECORDS;
		}

		List<QuestionTranslation> result = new ArrayList<QuestionTranslation>();

		try {
			String categoriesClause = !StringUtils.isEmpty(categoryIds) ? " and na.faqCategory.id in ("
					+ categoryIds + ")"
					: "";
			String qry = "Select  na from QuestionTranslation na where na.faqQuestion.inactive = false and na.language = ?1 "
					+ categoriesClause;
			Query query = faqCategoryAction.getEntityManager().createQuery(qry);
			query.setParameter(1, lang);

			if (pageNumber != null)
				query.setFirstResult(pageNumber * pageSize);
			query.setMaxResults(pageSize);

			result = query.getResultList();
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ContractViolationException(
					"CategoryIds are invalid - provide a comma delimited string e.g 3,4,7 ");
		}
		List<Faq> listFaqQuestion = new ArrayList<Faq>();

		for (QuestionTranslation questionTranslation : result) {
			Faq dto = createDto(questionTranslation);
			listFaqQuestion.add(dto);
		}

		updateMetrics(result);

		FaqList faqList = new FaqList();
		faqList.setFaqs(listFaqQuestion);
		return faqList;
	}

	private Faq createDto(QuestionTranslation questionTranslation) {
		Faq dto = new Faq();
		try {
			BeanUtils.copyProperties(dto, questionTranslation);
			dto.setCategoryId(questionTranslation.getFaqQuestion()
					.getFaqCategory().getId());
			dto.setViews(questionTranslation.getFaqQuestion().getViews());
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		return dto;
	}

	private void updateMetrics(List<QuestionTranslation> result) {

		Partner partner = getCurrentPartner();

		for (QuestionTranslation taxNews : result) {
			FaqQuestionMetrics metrics = faqQuestionMetricsAction
					.getMetricsByQuestionAndSession(taxNews.getFaqQuestion(),
							getSessionId());
			if (metrics != null)
				continue;
			FaqQuestionMetrics taxNewsMetrics = new FaqQuestionMetrics();
			taxNewsMetrics.setFaqQuestion(taxNews.getFaqQuestion());
			taxNewsMetrics.setPartner(partner);
			taxNewsMetrics.setSessionId(getSessionId());
			taxNewsMetrics.setLanguage(taxNews.getLanguage());
			taxNewsMetrics.setSessionId(getSessionId());
			faqQuestionMetricsAction.persist(taxNewsMetrics);
			faqQuestionMetricsAction.clearInstance();
		}
	}

	@Override
	protected RateQuestionResponse doGetQuestion(Long questionId,
			String language, String securityKey) {
		QuestionTranslation question = entityManager.find(
				QuestionTranslation.class, questionId);
		if (question == null)
			throw new ContractViolationException("No question with id "
					+ questionId + " exists ");
		updateMetricsForClick(question);

		RateQuestionResponse rateQuestionResponse = new RateQuestionResponse();
		rateQuestionResponse.setFaq(createDto(question));
		return rateQuestionResponse;

	}

	private void updateMetricsForClick(QuestionTranslation question) {
		FaqQuestionMetrics metrics = faqQuestionMetricsAction
				.getMetricsByQuestionAndSession(question.getFaqQuestion(),
						getSessionId());
		if (metrics != null) {
			metrics.setDateViewed(new Date());
			faqQuestionMetricsAction.persist(metrics);
		}
	}

}
