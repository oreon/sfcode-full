package com.pcas.datapkg.web.action.inventory;

import com.pcas.datapkg.domain.inventory.DrugAbstract;

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

import com.pcas.datapkg.domain.inventory.DrugAbstract;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class DrugAbstractListQueryBase
		extends
			BaseQuery<DrugAbstract, Long> {

	private static final String EJBQL = "select drugAbstract from DrugAbstract drugAbstract";

	protected DrugAbstract drugAbstract = new DrugAbstract();

	public DrugAbstract getDrugAbstract() {
		return drugAbstract;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<DrugAbstract> getEntityClass() {
		return DrugAbstract.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private Range<BigDecimal> priceRange = new Range<BigDecimal>();

	public Range<BigDecimal> getPriceRange() {
		return priceRange;
	}
	public void setPrice(Range<BigDecimal> priceRange) {
		this.priceRange = priceRange;
	}

	private static final String[] RESTRICTIONS = {
			"drugAbstract.id = #{drugAbstractList.drugAbstract.id}",

			"lower(drugAbstract.name) like concat(lower(#{drugAbstractList.drugAbstract.name}),'%')",

			"drugAbstract.price >= #{drugAbstractList.priceRange.begin}",
			"drugAbstract.price <= #{drugAbstractList.priceRange.end}",

			"drugAbstract.dateCreated <= #{drugAbstractList.dateCreatedRange.end}",
			"drugAbstract.dateCreated >= #{drugAbstractList.dateCreatedRange.begin}",};

	@Observer("archivedDrugAbstract")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, DrugAbstract e) {

		builder.append("\""
				+ (e.getName() != null ? e.getName().replace(",", "") : "")
				+ "\",");

		builder.append("\"" + (e.getPrice() != null ? e.getPrice() : "")
				+ "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("Name" + ",");

		builder.append("Price" + ",");

		builder.append("\r\n");
	}
}
