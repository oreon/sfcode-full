package com.hrb.tservices.web.action.taxnews;

import com.hrb.tservices.domain.taxnews.TaxNewsTranslation;

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

import com.hrb.tservices.domain.taxnews.TaxNewsTranslation;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class TaxNewsTranslationListQueryBase
		extends
			BaseQuery<TaxNewsTranslation, Long> {

	private static final String EJBQL = "select taxNewsTranslation from TaxNewsTranslation taxNewsTranslation";

	protected TaxNewsTranslation taxNewsTranslation = new TaxNewsTranslation();

	public TaxNewsTranslation getTaxNewsTranslation() {
		return taxNewsTranslation;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<TaxNewsTranslation> getEntityClass() {
		return TaxNewsTranslation.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"taxNewsTranslation.id = #{taxNewsTranslationList.taxNewsTranslation.id}",

			"taxNewsTranslation.taxNews.id = #{taxNewsTranslationList.taxNewsTranslation.taxNews.id}",

			"lower(taxNewsTranslation.title) like concat(lower(#{taxNewsTranslationList.taxNewsTranslation.title}),'%')",

			"lower(taxNewsTranslation.link) like concat(lower(#{taxNewsTranslationList.taxNewsTranslation.link}),'%')",

			"lower(taxNewsTranslation.text) like concat(lower(#{taxNewsTranslationList.taxNewsTranslation.text}),'%')",

			"taxNewsTranslation.language = #{taxNewsTranslationList.taxNewsTranslation.language}",

			"taxNewsTranslation.dateCreated <= #{taxNewsTranslationList.dateCreatedRange.end}",
			"taxNewsTranslation.dateCreated >= #{taxNewsTranslationList.dateCreatedRange.begin}",};

	public List<TaxNewsTranslation> getTaxNewsTranslationsByTaxNews(
			com.hrb.tservices.domain.taxnews.TaxNews taxNews) {
		//setMaxResults(10000);
		taxNewsTranslation.setTaxNews(taxNews);
		return getResultList();
	}

	@Observer("archivedTaxNewsTranslation")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, TaxNewsTranslation e) {

		builder.append("\""
				+ (e.getTaxNews() != null ? e.getTaxNews().getDisplayName()
						.replace(",", "") : "") + "\",");

		builder.append("\""
				+ (e.getTitle() != null ? e.getTitle().replace(",", "") : "")
				+ "\",");

		builder.append("\""
				+ (e.getLink() != null ? e.getLink().replace(",", "") : "")
				+ "\",");

		builder.append("\""
				+ (e.getText() != null ? e.getText().replace(",", "") : "")
				+ "\",");

		builder.append("\"" + (e.getLanguage() != null ? e.getLanguage() : "")
				+ "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("TaxNews" + ",");

		builder.append("Title" + ",");

		builder.append("Link" + ",");

		builder.append("Text" + ",");

		builder.append("Language" + ",");

		builder.append("\r\n");
	}
}
