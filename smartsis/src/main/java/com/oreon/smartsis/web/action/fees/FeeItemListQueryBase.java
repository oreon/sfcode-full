package com.oreon.smartsis.web.action.fees;

import com.oreon.smartsis.fees.FeeItem;

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

import com.oreon.smartsis.fees.FeeItem;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class FeeItemListQueryBase extends BaseQuery<FeeItem, Long> {

	private static final String EJBQL = "select feeItem from FeeItem feeItem";

	protected FeeItem feeItem = new FeeItem();

	public FeeItem getFeeItem() {
		return feeItem;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<FeeItem> getEntityClass() {
		return FeeItem.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"feeItem.id = #{feeItemList.feeItem.id}",

			"lower(feeItem.name) like concat(lower(#{feeItemList.feeItem.name}),'%')",

			"feeItem.dateCreated <= #{feeItemList.dateCreatedRange.end}",
			"feeItem.dateCreated >= #{feeItemList.dateCreatedRange.begin}",};

	@Observer("archivedFeeItem")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, FeeItem e) {

		builder.append("\""
				+ (e.getName() != null ? e.getName().replace(",", "") : "")
				+ "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("Name" + ",");

		builder.append("\r\n");
	}
}
