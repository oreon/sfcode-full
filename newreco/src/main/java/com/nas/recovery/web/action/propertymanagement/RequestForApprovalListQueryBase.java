package com.nas.recovery.web.action.propertymanagement;

import com.nas.recovery.domain.propertymanagement.RequestForApproval;

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

import com.nas.recovery.domain.propertymanagement.RequestForApproval;

/**
 * @author WitchcraftMDA Seam Cartridge
 *
 */
public abstract class RequestForApprovalListQueryBase
		extends
			BaseQuery<RequestForApproval, Long> {

	//private static final String EJBQL = "select requestForApproval from RequestForApproval requestForApproval";

	private RequestForApproval requestForApproval = new RequestForApproval();

	private Range<Date> requestForApproval_requestDateRange = new Range<Date>();
	public Range<Date> getRequestForApproval_requestDateRange() {
		return requestForApproval_requestDateRange;
	}
	public void setRequestForApproval_requestDate(
			Range<Date> requestForApproval_requestDateRange) {
		this.requestForApproval_requestDateRange = requestForApproval_requestDateRange;
	}

	private Range<Date> requestForApproval_recievedDateRange = new Range<Date>();
	public Range<Date> getRequestForApproval_recievedDateRange() {
		return requestForApproval_recievedDateRange;
	}
	public void setRequestForApproval_recievedDate(
			Range<Date> requestForApproval_recievedDateRange) {
		this.requestForApproval_recievedDateRange = requestForApproval_recievedDateRange;
	}

	private static final String[] RESTRICTIONS = {
			"requestForApproval.id = #{requestForApprovalList.requestForApproval.id}",

			"requestForApproval.requestDate >= #{requestForApprovalList.requestForApproval_requestDateRange.begin}",
			"requestForApproval.requestDate <= #{requestForApprovalList.requestForApproval_requestDateRange.end}",

			"lower(requestForApproval.referenceNumber) like concat(lower(#{requestForApprovalList.requestForApproval.referenceNumber}),'%')",

			"requestForApproval.recievedDate >= #{requestForApprovalList.requestForApproval_recievedDateRange.begin}",
			"requestForApproval.recievedDate <= #{requestForApprovalList.requestForApproval_recievedDateRange.end}",

			"lower(requestForApproval.contractor) like concat(lower(#{requestForApprovalList.requestForApproval.contractor}),'%')",

			"lower(requestForApproval.item) like concat(lower(#{requestForApprovalList.requestForApproval.item}),'%')",

			"requestForApproval.estimate = #{requestForApprovalList.requestForApproval.estimate}",

			"requestForApproval.details = #{requestForApprovalList.requestForApproval.details}",

			"requestForApproval.dateCreated <= #{requestForApprovalList.dateCreatedRange.end}",
			"requestForApproval.dateCreated >= #{requestForApprovalList.dateCreatedRange.begin}",};

	public RequestForApproval getRequestForApproval() {
		return requestForApproval;
	}

	@Override
	public Class<RequestForApproval> getEntityClass() {
		return RequestForApproval.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		// TODO Auto-generated method stub
		return RESTRICTIONS;
	}

	@Observer("archivedRequestForApproval")
	public void onArchive() {
		refresh();
	}
}
