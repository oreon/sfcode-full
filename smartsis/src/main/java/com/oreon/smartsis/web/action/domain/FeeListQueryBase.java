package com.oreon.smartsis.web.action.domain;

import com.oreon.smartsis.domain.Fee;

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

import com.oreon.smartsis.domain.Fee;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class FeeListQueryBase extends BaseQuery<Fee, Long> {

	private static final String EJBQL = "select fee from Fee fee";

	protected Fee fee = new Fee();

	public Fee getFee() {
		return fee;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<Fee> getEntityClass() {
		return Fee.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private Range<Double> defaultAmountRange = new Range<Double>();
	public Range<Double> getDefaultAmountRange() {
		return defaultAmountRange;
	}
	public void setDefaultAmount(Range<Double> defaultAmountRange) {
		this.defaultAmountRange = defaultAmountRange;
	}

	private static final String[] RESTRICTIONS = {"fee.id = #{feeList.fee.id}",

	"lower(fee.name) like concat(lower(#{feeList.fee.name}),'%')",

	"fee.defaultAmount >= #{feeList.defaultAmountRange.begin}",
			"fee.defaultAmount <= #{feeList.defaultAmountRange.end}",

			"fee.frequency = #{feeList.fee.frequency}",

			"fee.dateCreated <= #{feeList.dateCreatedRange.end}",
			"fee.dateCreated >= #{feeList.dateCreatedRange.begin}",};

	@Observer("archivedFee")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, Fee e) {

		builder.append("\""
				+ (e.getName() != null ? e.getName().replace(",", "") : "")
				+ "\",");

		builder.append("\""
				+ (e.getDefaultAmount() != null ? e.getDefaultAmount() : "")
				+ "\",");

		builder.append("\""
				+ (e.getFrequency() != null ? e.getFrequency() : "") + "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("Name" + ",");

		builder.append("DefaultAmount" + ",");

		builder.append("Frequency" + ",");

		builder.append("\r\n");
	}
}
