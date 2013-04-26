package com.oreon.cerebrum.web.action.billing;

import com.oreon.cerebrum.billing.Invoice;

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

import com.oreon.cerebrum.billing.Invoice;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class InvoiceListQueryBase extends BaseQuery<Invoice, Long> {

	private static final String EJBQL = "select invoice from Invoice invoice";

	protected Invoice invoice = new Invoice();

	public Invoice getInvoice() {
		return invoice;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<Invoice> getEntityClass() {
		return Invoice.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private Range<BigDecimal> totalAmountRange = new Range<BigDecimal>();

	public Range<BigDecimal> getTotalAmountRange() {
		return totalAmountRange;
	}
	public void setTotalAmount(Range<BigDecimal> totalAmountRange) {
		this.totalAmountRange = totalAmountRange;
	}

	private static final String[] RESTRICTIONS = {
			"invoice.id = #{invoiceList.invoice.id}",

			"invoice.patient.id = #{invoiceList.invoice.patient.id}",

			"lower(invoice.notes) like concat(lower(#{invoiceList.invoice.notes}),'%')",

			"invoice.totalAmount >= #{invoiceList.totalAmountRange.begin}",
			"invoice.totalAmount <= #{invoiceList.totalAmountRange.end}",

			"invoice.dateCreated <= #{invoiceList.dateCreatedRange.end}",
			"invoice.dateCreated >= #{invoiceList.dateCreatedRange.begin}",};

	@Observer("archivedInvoice")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, Invoice e) {

		builder.append("\""
				+ (e.getPatient() != null ? e.getPatient().getDisplayName()
						.replace(",", "") : "") + "\",");

		builder.append("\""
				+ (e.getNotes() != null ? e.getNotes().replace(",", "") : "")
				+ "\",");

		builder.append("\""
				+ (e.getTotalAmount() != null ? e.getTotalAmount() : "")
				+ "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("Patient" + ",");

		builder.append("Notes" + ",");

		builder.append("TotalAmount" + ",");

		builder.append("\r\n");
	}
}
