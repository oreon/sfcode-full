package com.pwc.insuranceclaims.web.action.quickclaim;

import com.pwc.insuranceclaims.quickclaim.ClaimDocument;

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

import com.pwc.insuranceclaims.quickclaim.ClaimDocument;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class ClaimDocumentListQueryBase
		extends
			BaseQuery<ClaimDocument, Long> {

	private static final String EJBQL = "select claimDocument from ClaimDocument claimDocument";

	protected ClaimDocument claimDocument = new ClaimDocument();

	public ClaimDocument getClaimDocument() {
		return claimDocument;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<ClaimDocument> getEntityClass() {
		return ClaimDocument.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private Range<Date> documentDateRange = new Range<Date>();
	public Range<Date> getDocumentDateRange() {
		return documentDateRange;
	}
	public void setDocumentDate(Range<Date> documentDateRange) {
		this.documentDateRange = documentDateRange;
	}

	private static final String[] RESTRICTIONS = {
			"claimDocument.id = #{claimDocumentList.claimDocument.id}",

			"claimDocument.documentDate >= #{claimDocumentList.documentDateRange.begin}",
			"claimDocument.documentDate <= #{claimDocumentList.documentDateRange.end}",

			"lower(claimDocument.documentType) like concat(lower(#{claimDocumentList.claimDocument.documentType}),'%')",

			"lower(claimDocument.documentDescription) like concat(lower(#{claimDocumentList.claimDocument.documentDescription}),'%')",

			"claimDocument.claim.id = #{claimDocumentList.claimDocument.claim.id}",

			"claimDocument.dateCreated <= #{claimDocumentList.dateCreatedRange.end}",
			"claimDocument.dateCreated >= #{claimDocumentList.dateCreatedRange.begin}",};

	public List<ClaimDocument> getClaimDocumentsByClaim(
			com.pwc.insuranceclaims.quickclaim.Claim claim) {
		//setMaxResults(10000);
		claimDocument.setClaim(claim);
		return getResultList();
	}

	@Observer("archivedClaimDocument")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, ClaimDocument e) {

		builder.append("\""
				+ (e.getDocumentDate() != null ? e.getDocumentDate() : "")
				+ "\",");

		builder.append("\""
				+ (e.getDocumentType() != null ? e.getDocumentType().replace(
						",", "") : "") + "\",");

		builder.append("\""
				+ (e.getDocumentDescription() != null ? e
						.getDocumentDescription().replace(",", "") : "")
				+ "\",");

		builder.append("\""
				+ (e.getClaim() != null ? e.getClaim().getDisplayName()
						.replace(",", "") : "") + "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("DocumentDate" + ",");

		builder.append("DocumentType" + ",");

		builder.append("DocumentDescription" + ",");

		builder.append("Claim" + ",");

		builder.append("\r\n");
	}
}
