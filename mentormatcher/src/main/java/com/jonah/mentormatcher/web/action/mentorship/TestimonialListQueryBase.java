package com.jonah.mentormatcher.web.action.mentorship;

import com.jonah.mentormatcher.domain.mentorship.Testimonial;

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

import com.jonah.mentormatcher.domain.mentorship.Testimonial;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class TestimonialListQueryBase
		extends
			BaseQuery<Testimonial, Long> {

	private static final String EJBQL = "select testimonial from Testimonial testimonial";

	protected Testimonial testimonial = new Testimonial();

	public Testimonial getTestimonial() {
		return testimonial;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<Testimonial> getEntityClass() {
		return Testimonial.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"testimonial.id = #{testimonialList.testimonial.id}",

			"testimonial.employee.id = #{testimonialList.testimonial.employee.id}",

			"lower(testimonial.description) like concat(lower(#{testimonialList.testimonial.description}),'%')",

			"testimonial.dateCreated <= #{testimonialList.dateCreatedRange.end}",
			"testimonial.dateCreated >= #{testimonialList.dateCreatedRange.begin}",};

	public List<Testimonial> getTestimonialsByEmployee(
			com.jonah.mentormatcher.domain.Employee employee) {
		//setMaxResults(10000);
		testimonial.setEmployee(employee);
		return getResultList();
	}

	@Observer("archivedTestimonial")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, Testimonial e) {

		builder.append("\""
				+ (e.getEmployee() != null ? e.getEmployee().getDisplayName()
						.replace(",", "") : "") + "\",");

		builder.append("\""
				+ (e.getDescription() != null ? e.getDescription().replace(",",
						"") : "") + "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("Employee" + ",");

		builder.append("Description" + ",");

		builder.append("\r\n");
	}
}
