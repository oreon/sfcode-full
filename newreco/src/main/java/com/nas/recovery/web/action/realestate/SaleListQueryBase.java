package com.nas.recovery.web.action.realestate;

import com.nas.recovery.domain.realestate.Sale;

import org.witchcraft.seam.action.BaseAction;

import java.util.Arrays;
import java.util.Date;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.framework.EntityQuery;
import org.witchcraft.base.entity.BaseQuery;
import org.witchcraft.base.entity.Range;

import org.jboss.seam.annotations.Observer;

import com.nas.recovery.domain.realestate.Sale;

/**
 * @author WitchcraftMDA Seam Cartridge
 *
 */
public abstract class SaleListQueryBase extends BaseQuery<Sale, Long> {

	//private static final String EJBQL = "select sale from Sale sale";

	private Sale sale = new Sale();

	private Range<Date> sale_soldDateRange = new Range<Date>();
	public Range<Date> getSale_soldDateRange() {
		return sale_soldDateRange;
	}
	public void setSale_soldDate(Range<Date> sale_soldDateRange) {
		this.sale_soldDateRange = sale_soldDateRange;
	}

	private Range<Date> sale_closingDateRange = new Range<Date>();
	public Range<Date> getSale_closingDateRange() {
		return sale_closingDateRange;
	}
	public void setSale_closingDate(Range<Date> sale_closingDateRange) {
		this.sale_closingDateRange = sale_closingDateRange;
	}

	private Range<Double> sale_soldPriceRange = new Range<Double>();
	public Range<Double> getSale_soldPriceRange() {
		return sale_soldPriceRange;
	}
	public void setSale_soldPrice(Range<Double> sale_soldPriceRange) {
		this.sale_soldPriceRange = sale_soldPriceRange;
	}

	private Range<Double> sale_depositRange = new Range<Double>();
	public Range<Double> getSale_depositRange() {
		return sale_depositRange;
	}
	public void setSale_deposit(Range<Double> sale_depositRange) {
		this.sale_depositRange = sale_depositRange;
	}

	private Range<Double> sale_commissionRange = new Range<Double>();
	public Range<Double> getSale_commissionRange() {
		return sale_commissionRange;
	}
	public void setSale_commission(Range<Double> sale_commissionRange) {
		this.sale_commissionRange = sale_commissionRange;
	}

	private static final String[] RESTRICTIONS = {
			"sale.id = #{saleList.sale.id}",

			"sale.soldDate >= #{saleList.sale_soldDateRange.begin}",
			"sale.soldDate <= #{saleList.sale_soldDateRange.end}",

			"sale.closingDate >= #{saleList.sale_closingDateRange.begin}",
			"sale.closingDate <= #{saleList.sale_closingDateRange.end}",

			"sale.soldPrice >= #{saleList.sale_soldPriceRange.begin}",
			"sale.soldPrice <= #{saleList.sale_soldPriceRange.end}",

			"sale.deposit >= #{saleList.sale_depositRange.begin}",
			"sale.deposit <= #{saleList.sale_depositRange.end}",

			"sale.commission >= #{saleList.sale_commissionRange.begin}",
			"sale.commission <= #{saleList.sale_commissionRange.end}",

			"sale.realEstateListing.id = #{saleList.sale.realEstateListing.id}",

			"sale.dateCreated <= #{saleList.dateCreatedRange.end}",
			"sale.dateCreated >= #{saleList.dateCreatedRange.begin}",};

	public Sale getSale() {
		return sale;
	}

	@Override
	public Class<Sale> getEntityClass() {
		return Sale.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		// TODO Auto-generated method stub
		return RESTRICTIONS;
	}

	@Observer("archivedSale")
	public void onArchive() {
		refresh();
	}
}
