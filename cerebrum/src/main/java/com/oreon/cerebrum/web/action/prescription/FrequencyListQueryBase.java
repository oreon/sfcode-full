package com.oreon.cerebrum.web.action.prescription;

import com.oreon.cerebrum.prescription.Frequency;

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

import java.math.BigDecimal;

import com.oreon.cerebrum.prescription.Frequency;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class FrequencyListQueryBase extends BaseQuery<Frequency, Long> {

	private static final String EJBQL = "select frequency from Frequency frequency";

	protected Frequency frequency = new Frequency();

	public Frequency getFrequency() {
		return frequency;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<Frequency> getEntityClass() {
		return Frequency.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private Range<Integer> qtyPerDayRange = new Range<Integer>();

	public Range<Integer> getQtyPerDayRange() {
		return qtyPerDayRange;
	}
	public void setQtyPerDay(Range<Integer> qtyPerDayRange) {
		this.qtyPerDayRange = qtyPerDayRange;
	}

	private static final String[] RESTRICTIONS = {
			"frequency.id = #{frequencyList.frequency.id}",

			"lower(frequency.name) like concat(lower(#{frequencyList.frequency.name}),'%')",

			"frequency.qtyPerDay >= #{frequencyList.qtyPerDayRange.begin}",
			"frequency.qtyPerDay <= #{frequencyList.qtyPerDayRange.end}",

			"frequency.dateCreated <= #{frequencyList.dateCreatedRange.end}",
			"frequency.dateCreated >= #{frequencyList.dateCreatedRange.begin}",};

	@Observer("archivedFrequency")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, Frequency e) {

		builder.append("\""
				+ (e.getName() != null ? e.getName().replace(",", "") : "")
				+ "\",");

		builder.append("\""
				+ (e.getQtyPerDay() != null ? e.getQtyPerDay() : "") + "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("Name" + ",");

		builder.append("QtyPerDay" + ",");

		builder.append("\r\n");
	}
}
