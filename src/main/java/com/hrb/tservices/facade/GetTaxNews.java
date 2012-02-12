package com.hrb.tservices.facade;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.management.RuntimeErrorException;
import javax.persistence.Query;
import javax.ws.rs.core.Response;

import org.apache.commons.lang.StringUtils;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.witchcraft.exceptions.ContractViolationException;

import com.hrb.tservices.domain.department.Partner;
import com.hrb.tservices.domain.faq.FaqQuestionMetrics;
import com.hrb.tservices.domain.taxnews.Language;
import com.hrb.tservices.domain.taxnews.NewsCategory;
import com.hrb.tservices.domain.taxnews.TaxNews;
import com.hrb.tservices.domain.taxnews.TaxNewsMetrics;
import com.hrb.tservices.domain.taxnews.TaxNewsTranslation;
import com.hrb.tservices.dtos.TaxNewsArticleList;
import com.hrb.tservices.dtos.TaxNewsCategory;
import com.hrb.tservices.dtos.TaxNewsArticle;
import com.hrb.tservices.dtos.TaxNewsCategoryList;
import com.hrb.tservices.web.action.taxnews.NewsCategoryAction;
import com.hrb.tservices.web.action.taxnews.NewsCategoryListQuery;
import com.hrb.tservices.web.action.taxnews.TaxNewsMetricsAction;
import com.sun.org.apache.commons.beanutils.BeanUtils;

@Name("getTaxNews")
public class GetTaxNews extends GetTaxNewsBase {

	@In(create = true)
	NewsCategoryListQuery newsCategoryList;

	@In(create = true)
	NewsCategoryAction newsCategoryAction;

	@In(create = true)
	TaxNewsMetricsAction taxNewsMetricsAction;

	// @In(create = true)
	// FacesContext facesContext;

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.hrb.tservices.facade.GetTaxNewsBase#getCategories() return the
	 *      categories for taxnews
	 */
	@Override
	public TaxNewsCategoryList doGetCategories(String language,
			String securityKey) {

		List<TaxNewsCategory> listTaxNews = new ArrayList<TaxNewsCategory>();

		Language lang = getLanguageEnum(language);

		List<NewsCategory> catList = newsCategoryList.getResultList();

		for (NewsCategory newsCategory : catList) {
			TaxNewsCategory dto = new TaxNewsCategory();
			try {
				BeanUtils.copyProperties(dto, newsCategory);
				dto.setName(lang == Language.ENGLISH ? newsCategory.getName()
						: newsCategory.getNameFrench());
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}
			listTaxNews.add(dto);
		}

		TaxNewsCategoryList taxNewsCategoryList = new TaxNewsCategoryList();
		taxNewsCategoryList.setTaxNewsCategorys(listTaxNews);
		return taxNewsCategoryList;
	}

	@Override
	public Response doGetArticle(Long articleId, String language,
			String securityKey) {

		TaxNewsTranslation translation = entityManager.find(
				TaxNewsTranslation.class, articleId);

		if (translation == null)
			throw new ContractViolationException("No Artlice with id "
					+ articleId + " exists ");

		updateMetricsForClick(translation);

		return createRedirectResponse(translation.getLink());
	}

	private void updateMetricsForClick(TaxNewsTranslation translation) {
		TaxNewsMetrics metrics = taxNewsMetricsAction
				.getMetricsByArticleAndSession(translation.getTaxNews(),
						getSessionId());
		if (metrics != null) {
			metrics.setDateViewed(new Date());
			taxNewsMetricsAction.persist(metrics);
		}
	}

	@Override
	protected TaxNewsArticleList doGetNewsArticles(String categoryIds,
			String language, Integer pageNumber, Integer pageSize,
			String securityKey) {

		Language lang = getLanguageEnum(language);

		if (pageSize == null) {
			pageSize = DEFAULT_RECORDS;
		}
		String categoriesClause = !StringUtils.isEmpty(categoryIds) ? " and na.taxNews.newsCategory.id in ("
				+ categoryIds + ")"
				: "";
		String qry = "Select  na from TaxNewsTranslation na where na.taxNews.inactive = false and na.language = ?1 "
				+ categoriesClause;

		List<TaxNewsTranslation> result = new ArrayList<TaxNewsTranslation>();

		try {
			Query query = newsCategoryAction.getEntityManager()
					.createQuery(qry);

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
		List<TaxNewsArticle> listTaxNews = new ArrayList<TaxNewsArticle>();

		for (TaxNewsTranslation taxNews : result) {
			TaxNewsArticle dto = new TaxNewsArticle();

			try {
				BeanUtils.copyProperties(dto, taxNews);
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}
			listTaxNews.add(dto);
		}

		updateMetrics(result);
		TaxNewsArticleList taxNewsArticleList = new TaxNewsArticleList();
		taxNewsArticleList.setTaxNewsArticles(listTaxNews);
		return taxNewsArticleList;
	}

	private void updateMetrics(List<TaxNewsTranslation> result) {

		for (TaxNewsTranslation taxNews : result) {
			TaxNewsMetrics metrics = taxNewsMetricsAction
					.getMetricsByArticleAndSession(taxNews.getTaxNews(),
							getSessionId());
			if (metrics != null)
				continue;
			TaxNewsMetrics taxNewsMetrics = taxNewsMetricsAction.getInstance();
			initMetrics(taxNewsMetrics);
			taxNewsMetrics.setTaxNews(taxNews.getTaxNews());
			taxNewsMetrics.setLanguage(taxNews.getLanguage());
			taxNewsMetricsAction.persist(taxNewsMetrics);
			taxNewsMetricsAction.clearInstance();
		}
	}

}
