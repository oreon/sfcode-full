package com.nas.recovery.web.action.billing;

import com.oreon.callosum.billing.Invoice;

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

import com.oreon.callosum.billing.Invoice;

/**
 * D
 * @author WitchcraftMDA Seam Cartridge
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

	private static final String[] RESTRICTIONS = {
			"invoice.id = #{invoiceList.invoice.id}",

			"invoice.patient.id = #{invoiceList.invoice.patient.id}",

			"lower(invoice.notes) like concat(lower(#{invoiceList.invoice.notes}),'%')",

			"invoice.dateCreated <= #{invoiceList.dateCreatedRange.end}",
			"invoice.dateCreated >= #{invoiceList.dateCreatedRange.begin}",};

	@Observer("archivedInvoice")
	public void onArchive() {
		refresh();
	}

}
