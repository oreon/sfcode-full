package com.pcas.datapkg.web.action.inventory;

import com.pcas.datapkg.domain.inventory.InventoryHistory;

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

import com.pcas.datapkg.domain.inventory.InventoryHistory;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class InventoryHistoryListQueryBase
		extends
			BaseQuery<InventoryHistory, Long> {

	private static final String EJBQL = "select inventoryHistory from InventoryHistory inventoryHistory";

	protected InventoryHistory inventoryHistory = new InventoryHistory();

	public InventoryHistory getInventoryHistory() {
		return inventoryHistory;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<InventoryHistory> getEntityClass() {
		return InventoryHistory.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private Range<Date> dateRange = new Range<Date>();

	public Range<Date> getDateRange() {
		return dateRange;
	}
	public void setDate(Range<Date> dateRange) {
		this.dateRange = dateRange;
	}

	private Range<Integer> qtyRange = new Range<Integer>();

	public Range<Integer> getQtyRange() {
		return qtyRange;
	}
	public void setQty(Range<Integer> qtyRange) {
		this.qtyRange = qtyRange;
	}

	private static final String[] RESTRICTIONS = {
			"inventoryHistory.id = #{inventoryHistoryList.inventoryHistory.id}",

			"inventoryHistory.drugInventory.id = #{inventoryHistoryList.inventoryHistory.drugInventory.id}",

			"inventoryHistory.date >= #{inventoryHistoryList.dateRange.begin}",
			"inventoryHistory.date <= #{inventoryHistoryList.dateRange.end}",

			"inventoryHistory.qty >= #{inventoryHistoryList.qtyRange.begin}",
			"inventoryHistory.qty <= #{inventoryHistoryList.qtyRange.end}",

			"inventoryHistory.dateCreated <= #{inventoryHistoryList.dateCreatedRange.end}",
			"inventoryHistory.dateCreated >= #{inventoryHistoryList.dateCreatedRange.begin}",};

	@Observer("archivedInventoryHistory")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, InventoryHistory e) {

		builder.append("\""
				+ (e.getDrugInventory() != null ? e.getDrugInventory()
						.getDisplayName().replace(",", "") : "") + "\",");

		builder.append("\"" + (e.getDate() != null ? e.getDate() : "") + "\",");

		builder.append("\"" + (e.getQty() != null ? e.getQty() : "") + "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("DrugInventory" + ",");

		builder.append("Date" + ",");

		builder.append("Qty" + ",");

		builder.append("\r\n");
	}
}
