package com.hrb.tservices.web.action.faq;

import com.hrb.tservices.domain.faq.FaqCategory;

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

import com.hrb.tservices.domain.faq.FaqCategory;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class FaqCategoryListQueryBase
		extends
			BaseQuery<FaqCategory, Long> {

	private static final String EJBQL = "select faqCategory from FaqCategory faqCategory";

	protected FaqCategory faqCategory = new FaqCategory();

	public FaqCategory getFaqCategory() {
		return faqCategory;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<FaqCategory> getEntityClass() {
		return FaqCategory.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"faqCategory.id = #{faqCategoryList.faqCategory.id}",

			"lower(faqCategory.name) like concat(lower(#{faqCategoryList.faqCategory.name}),'%')",

			"lower(faqCategory.frenchName) like concat(lower(#{faqCategoryList.faqCategory.frenchName}),'%')",

			"faqCategory.dateCreated <= #{faqCategoryList.dateCreatedRange.end}",
			"faqCategory.dateCreated >= #{faqCategoryList.dateCreatedRange.begin}",};

	@Observer("archivedFaqCategory")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, FaqCategory e) {

		builder.append("\""
				+ (e.getName() != null ? e.getName().replace(",", "") : "")
				+ "\",");

		builder.append("\""
				+ (e.getFrenchName() != null ? e.getFrenchName().replace(",",
						"") : "") + "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("Name" + ",");

		builder.append("FrenchName" + ",");

		builder.append("\r\n");
	}
}
