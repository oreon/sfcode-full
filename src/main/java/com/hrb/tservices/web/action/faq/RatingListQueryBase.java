package com.hrb.tservices.web.action.faq;

import com.hrb.tservices.domain.faq.Rating;

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

import com.hrb.tservices.domain.faq.Rating;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class RatingListQueryBase extends BaseQuery<Rating, Long> {

	private static final String EJBQL = "select rating from Rating rating";

	protected Rating rating = new Rating();

	public Rating getRating() {
		return rating;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<Rating> getEntityClass() {
		return Rating.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private Range<Integer> ratingRange = new Range<Integer>();
	public Range<Integer> getRatingRange() {
		return ratingRange;
	}
	public void setRating(Range<Integer> ratingRange) {
		this.ratingRange = ratingRange;
	}

	private static final String[] RESTRICTIONS = {
			"rating.id = #{ratingList.rating.id}",

			"rating.rating >= #{ratingList.ratingRange.begin}",
			"rating.rating <= #{ratingList.ratingRange.end}",

			"rating.faqQuestion.id = #{ratingList.rating.faqQuestion.id}",

			"rating.dateCreated <= #{ratingList.dateCreatedRange.end}",
			"rating.dateCreated >= #{ratingList.dateCreatedRange.begin}",};

	public List<Rating> getRatingsByFaqQuestion(
			com.hrb.tservices.domain.faq.FaqQuestion faqQuestion) {
		//setMaxResults(10000);
		rating.setFaqQuestion(faqQuestion);
		return getResultList();
	}

	@Observer("archivedRating")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, Rating e) {

		builder.append("\"" + (e.getRating() != null ? e.getRating() : "")
				+ "\",");

		builder.append("\""
				+ (e.getFaqQuestion() != null ? e.getFaqQuestion()
						.getDisplayName().replace(",", "") : "") + "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("Rating" + ",");

		builder.append("FaqQuestion" + ",");

		builder.append("\r\n");
	}
}
