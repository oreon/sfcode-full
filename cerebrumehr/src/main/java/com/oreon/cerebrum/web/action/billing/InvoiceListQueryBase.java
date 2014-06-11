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

import org.witchcraft.seam.action.BaseQuery;

import org.witchcraft.base.entity.Range;

import org.primefaces.model.SortOrder;
import org.witchcraft.seam.action.EntityLazyDataModel;
import org.primefaces.model.LazyDataModel;
import java.util.Map;

import org.jboss.seam.annotations.Observer;

import java.math.BigDecimal;
import javax.faces.model.DataModel;

import org.jboss.seam.annotations.security.Restrict;

import org.jboss.seam.annotations.In;
import org.jboss.seam.Component;

import com.oreon.cerebrum.billing.Invoice;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class InvoiceListQueryBase extends BaseQuery<Invoice, Long> {

	private static final String EJBQL = "select invoice from Invoice invoice";

	protected Invoice invoice = new Invoice();

	public InvoiceListQueryBase() {
		super();
		setOrderColumn("id");
		setOrderDirection("desc");
	}

	public Invoice getInvoice() {
		return invoice;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Invoice getInstance() {
		return getInvoice();
	}

	@Override
	//@Restrict("#{s:hasPermission('invoice', 'view')}")
	public List<Invoice> getResultList() {
		return super.getResultList();
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

	private Range<BigDecimal> paidAmountRange = new Range<BigDecimal>();

	public Range<BigDecimal> getPaidAmountRange() {
		return paidAmountRange;
	}
	public void setPaidAmount(Range<BigDecimal> paidAmountRange) {
		this.paidAmountRange = paidAmountRange;
	}

	private static final String[] RESTRICTIONS = {
			"invoice.id = #{invoiceList.invoice.id}",

			"invoice.archived = #{invoiceList.invoice.archived}",

			"invoice.patient.id = #{invoiceList.invoice.patient.id}",

			"lower(invoice.notes) like concat(lower(#{invoiceList.invoice.notes}),'%')",

			"invoice.totalAmount >= #{invoiceList.totalAmountRange.begin}",
			"invoice.totalAmount <= #{invoiceList.totalAmountRange.end}",

			"invoice.paidAmount >= #{invoiceList.paidAmountRange.begin}",
			"invoice.paidAmount <= #{invoiceList.paidAmountRange.end}",

			"invoice.dateCreated <= #{invoiceList.dateCreatedRange.end}",
			"invoice.dateCreated >= #{invoiceList.dateCreatedRange.begin}",};

	@Observer("archivedInvoice")
	public void onArchive() {
		refresh();
	}

	public void setPatientId(Long id) {
		if (invoice.getPatient() == null) {
			invoice.setPatient(new com.oreon.cerebrum.patient.Patient());
		}
		invoice.getPatient().setId(id);
	}

	public Long getPatientId() {
		return invoice.getPatient() == null ? null : invoice.getPatient()
				.getId();
	}

}
