package com.hrb.tservices.web.action.taxnews;

import com.hrb.tservices.domain.taxnews.NewsCategory;

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

import com.hrb.tservices.domain.taxnews.NewsCategory;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class NewsCategoryListQueryBase
		extends
			BaseQuery<NewsCategory, Long> {

	private static final String EJBQL = "select newsCategory from NewsCategory newsCategory";

	protected NewsCategory newsCategory = new NewsCategory();

	public NewsCategory getNewsCategory() {
		return newsCategory;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<NewsCategory> getEntityClass() {
		return NewsCategory.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"newsCategory.id = #{newsCategoryList.newsCategory.id}",

			"lower(newsCategory.name) like concat(lower(#{newsCategoryList.newsCategory.name}),'%')",

			"lower(newsCategory.nameFrench) like concat(lower(#{newsCategoryList.newsCategory.nameFrench}),'%')",

			"newsCategory.dateCreated <= #{newsCategoryList.dateCreatedRange.end}",
			"newsCategory.dateCreated >= #{newsCategoryList.dateCreatedRange.begin}",};

	@Observer("archivedNewsCategory")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, NewsCategory e) {

		builder.append("\""
				+ (e.getName() != null ? e.getName().replace(",", "") : "")
				+ "\",");

		builder.append("\""
				+ (e.getNameFrench() != null ? e.getNameFrench().replace(",",
						"") : "") + "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("Name" + ",");

		builder.append("NameFrench" + ",");

		builder.append("\r\n");
	}
}
