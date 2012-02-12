package com.hrb.tservices.web.action.taxnews;

import com.hrb.tservices.domain.taxnews.TaxNews;

import org.witchcraft.seam.action.BaseAction;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.framework.EntityQuery;
import org.witchcraft.base.entity.BaseQuery;
import org.witchcraft.base.entity.Range;

import org.jboss.seam.annotations.Observer;

import com.hrb.tservices.domain.taxnews.TaxNews;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class TaxNewsListQueryBase extends BaseQuery<TaxNews, Long> {

	private static final String EJBQL = "select taxNews from TaxNews taxNews";

	protected TaxNews taxNews = new TaxNews();

	public TaxNews getTaxNews() {
		return taxNews;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<TaxNews> getEntityClass() {
		return TaxNews.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"taxNews.id = #{taxNewsList.taxNews.id}",

			"lower(taxNews.title) like concat(lower(#{taxNewsList.taxNews.title}),'%')",

			"taxNews.newsCategory.id = #{taxNewsList.taxNews.newsCategory.id}",

			"taxNews.inactive = #{taxNewsList.taxNews.inactive}",

			"taxNews.dateCreated <= #{taxNewsList.dateCreatedRange.end}",
			"taxNews.dateCreated >= #{taxNewsList.dateCreatedRange.begin}",};

	@Observer("archivedTaxNews")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, TaxNews e) {

		builder.append("\""
				+ (e.getTitle() != null ? e.getTitle().replace(",", "") : "")
				+ "\",");

		builder.append("\""
				+ (e.getNewsCategory() != null ? e.getNewsCategory()
						.getDisplayName().replace(",", "") : "") + "\",");

		builder.append("\"" + (e.getInactive() != null ? e.getInactive() : "")
				+ "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("Title" + ",");

		builder.append("NewsCategory" + ",");

		builder.append("Inactive" + ",");

		builder.append("\r\n");
	}
}
