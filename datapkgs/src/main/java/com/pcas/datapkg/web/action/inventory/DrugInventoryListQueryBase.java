package com.pcas.datapkg.web.action.inventory;

import com.pcas.datapkg.domain.inventory.DrugInventory;

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

import com.pcas.datapkg.domain.inventory.DrugInventory;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class DrugInventoryListQueryBase
		extends
			BaseQuery<DrugInventory, Long> {

	private static final String EJBQL = "select drugInventory from DrugInventory drugInventory";

	protected DrugInventory drugInventory = new DrugInventory();

	public DrugInventory getDrugInventory() {
		return drugInventory;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<DrugInventory> getEntityClass() {
		return DrugInventory.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private Range<Integer> qtyRange = new Range<Integer>();

	public Range<Integer> getQtyRange() {
		return qtyRange;
	}
	public void setQty(Range<Integer> qtyRange) {
		this.qtyRange = qtyRange;
	}

	private static final String[] RESTRICTIONS = {
			"drugInventory.id = #{drugInventoryList.drugInventory.id}",

			"drugInventory.drugAbstract.id = #{drugInventoryList.drugInventory.drugAbstract.id}",

			"drugInventory.machine.id = #{drugInventoryList.drugInventory.machine.id}",

			"drugInventory.qty >= #{drugInventoryList.qtyRange.begin}",
			"drugInventory.qty <= #{drugInventoryList.qtyRange.end}",

			"drugInventory.dateCreated <= #{drugInventoryList.dateCreatedRange.end}",
			"drugInventory.dateCreated >= #{drugInventoryList.dateCreatedRange.begin}",};

	public List<DrugInventory> getDrugInventorysByMachine(
			com.pcas.datapkg.domain.inventory.Machine machine) {
		//setMaxResults(10000);
		drugInventory.setMachine(machine);
		return getResultList();
	}

	@Observer("archivedDrugInventory")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, DrugInventory e) {

		builder.append("\""
				+ (e.getDrugAbstract() != null ? e.getDrugAbstract()
						.getDisplayName().replace(",", "") : "") + "\",");

		builder.append("\""
				+ (e.getMachine() != null ? e.getMachine().getDisplayName()
						.replace(",", "") : "") + "\",");

		builder.append("\"" + (e.getQty() != null ? e.getQty() : "") + "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("DrugAbstract" + ",");

		builder.append("Machine" + ",");

		builder.append("Qty" + ",");

		builder.append("\r\n");
	}
}
