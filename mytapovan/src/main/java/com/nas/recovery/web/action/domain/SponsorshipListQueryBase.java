package com.nas.recovery.web.action.domain;

import org.wc.mytapovan.domain.Sponsorship;

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

import org.wc.mytapovan.domain.Sponsorship;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class SponsorshipListQueryBase
		extends
			BaseQuery<Sponsorship, Long> {

	private static final String EJBQL = "select sponsorship from Sponsorship sponsorship";

	protected Sponsorship sponsorship = new Sponsorship();

	public Sponsorship getSponsorship() {
		return sponsorship;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<Sponsorship> getEntityClass() {
		return Sponsorship.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"sponsorship.id = #{sponsorshipList.sponsorship.id}",

			"sponsorship.student.id = #{sponsorshipList.sponsorship.student.id}",

			"sponsorship.sponsoror.id = #{sponsorshipList.sponsorship.sponsoror.id}",

			"sponsorship.dateCreated <= #{sponsorshipList.dateCreatedRange.end}",
			"sponsorship.dateCreated >= #{sponsorshipList.dateCreatedRange.begin}",};

	public List<Sponsorship> getSponsorshipByStudent(
			org.wc.mytapovan.domain.Student student) {
		//setMaxResults(10000);
		sponsorship.setStudent(student);
		return getResultList();
	}

	public List<Sponsorship> getSponsorshipsBySponsoror(
			org.wc.mytapovan.domain.Sponsoror sponsoror) {
		//setMaxResults(10000);
		sponsorship.setSponsoror(sponsoror);
		return getResultList();
	}

	@Observer("archivedSponsorship")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, Sponsorship e) {

		builder.append("\""
				+ (e.getStudent() != null
						? e.getStudent().getDisplayName()
						: "") + "\",");

		builder.append("\""
				+ (e.getSponsoror() != null
						? e.getSponsoror().getDisplayName()
						: "") + "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("Student" + ",");

		builder.append("Sponsoror" + ",");

		builder.append("\r\n");
	}
}
