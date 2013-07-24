package com.oreon.cerebrum.web.action.billing;

import com.oreon.cerebrum.billing.InvoiceItem;

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

import org.jboss.seam.annotations.security.Restrict;

import com.oreon.cerebrum.billing.InvoiceItem;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class InvoiceItemListQueryBase
		extends
			BaseQuery<InvoiceItem, Long> {

	private static final String EJBQL = "select invoiceItem from InvoiceItem invoiceItem";

	protected InvoiceItem invoiceItem = new InvoiceItem();

	public InvoiceItem getInvoiceItem() {
		return invoiceItem;
	}

	@Override
	public InvoiceItem getInstance() {
		return getInvoiceItem();
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	//@Restrict("#{s:hasPermission('invoiceItem', 'view')}")
	public List<InvoiceItem> getResultList() {
		return super.getResultList();
	}

	@Override
	public Class<InvoiceItem> getEntityClass() {
		return InvoiceItem.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private Range<Integer> unitsRange = new Range<Integer>();

	public Range<Integer> getUnitsRange() {
		return unitsRange;
	}
	public void setUnits(Range<Integer> unitsRange) {
		this.unitsRange = unitsRange;
	}

	private Range<BigDecimal> appliedPriceRange = new Range<BigDecimal>();

	public Range<BigDecimal> getAppliedPriceRange() {
		return appliedPriceRange;
	}
	public void setAppliedPrice(Range<BigDecimal> appliedPriceRange) {
		this.appliedPriceRange = appliedPriceRange;
	}

	private static final String[] RESTRICTIONS = {
			"invoiceItem.id = #{invoiceItemList.invoiceItem.id}",

			"invoiceItem.archived = #{invoiceItemList.invoiceItem.archived}",

			"invoiceItem.units >= #{invoiceItemList.unitsRange.begin}",
			"invoiceItem.units <= #{invoiceItemList.unitsRange.end}",

			"invoiceItem.service.id = #{invoiceItemList.invoiceItem.service.id}",

			"invoiceItem.invoice.id = #{invoiceItemList.invoiceItem.invoice.id}",

			"invoiceItem.appliedPrice >= #{invoiceItemList.appliedPriceRange.begin}",
			"invoiceItem.appliedPrice <= #{invoiceItemList.appliedPriceRange.end}",

			"invoiceItem.dateCreated <= #{invoiceItemList.dateCreatedRange.end}",
			"invoiceItem.dateCreated >= #{invoiceItemList.dateCreatedRange.begin}",};

	public List<InvoiceItem> getInvoiceItemsByInvoice(
			com.oreon.cerebrum.billing.Invoice invoice) {
		invoiceItem.setInvoice(invoice);
		return getResultList();
	}

	@Observer("archivedInvoiceItem")
	public void onArchive() {
		refresh();
	}

	public void setServiceId(Long id) {
		if (invoiceItem.getService() == null) {
			invoiceItem.setService(new com.oreon.cerebrum.billing.Service());
		}
		invoiceItem.getService().setId(id);
	}

	public Long getServiceId() {
		return invoiceItem.getService() == null ? null : invoiceItem
				.getService().getId();
	}

	public void setInvoiceId(Long id) {
		if (invoiceItem.getInvoice() == null) {
			invoiceItem.setInvoice(new com.oreon.cerebrum.billing.Invoice());
		}
		invoiceItem.getInvoice().setId(id);
	}

	public Long getInvoiceId() {
		return invoiceItem.getInvoice() == null ? null : invoiceItem
				.getInvoice().getId();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, InvoiceItem e) {

		builder.append("\"" + (e.getUnits() != null ? e.getUnits() : "")
				+ "\",");

		builder.append("\""
				+ (e.getService() != null ? e.getService().getDisplayName()
						.replace(",", "") : "") + "\",");

		builder.append("\""
				+ (e.getInvoice() != null ? e.getInvoice().getDisplayName()
						.replace(",", "") : "") + "\",");

		builder.append("\""
				+ (e.getAppliedPrice() != null ? e.getAppliedPrice() : "")
				+ "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("Units" + ",");

		builder.append("Service" + ",");

		builder.append("Invoice" + ",");

		builder.append("AppliedPrice" + ",");

		builder.append("\r\n");
	}
}
