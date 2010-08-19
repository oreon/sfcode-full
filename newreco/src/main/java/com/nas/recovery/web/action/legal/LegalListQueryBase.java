package com.nas.recovery.web.action.legal;

import com.nas.recovery.domain.legal.Legal;

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

import com.nas.recovery.domain.legal.Legal;

/**
 * @author WitchcraftMDA Seam Cartridge
 *
 */
public abstract class LegalListQueryBase extends BaseQuery<Legal, Long> {

	//private static final String EJBQL = "select legal from Legal legal";

	private Legal legal = new Legal();

	private static final String[] RESTRICTIONS = {
			"legal.id = #{legalList.legal.id}",

			"lower(legal.legalFileNumber) like concat(lower(#{legalList.legal.legalFileNumber}),'%')",

			"lower(legal.courtNumber) like concat(lower(#{legalList.legal.courtNumber}),'%')",

			"legal.litigation = #{legalList.legal.litigation}",

			"legal.specialHandling = #{legalList.legal.specialHandling}",

			"legal.status = #{legalList.legal.status}",

			"lower(legal.legalComments) like concat(lower(#{legalList.legal.legalComments}),'%')",

			"legal.lawyer.id = #{legalList.legal.lawyer.id}",

			"lower(legal.legalDescription) like concat(lower(#{legalList.legal.legalDescription}),'%')",

			"legal.titleInsuranceClaim = #{legalList.legal.titleInsuranceClaim}",

			"lower(legal.titleInsuranceClaimNumber) like concat(lower(#{legalList.legal.titleInsuranceClaimNumber}),'%')",

			"legal.titleInsurer.id = #{legalList.legal.titleInsurer.id}",

			"legal.chargee.id = #{legalList.legal.chargee.id}",

			"legal.realEstateProperty.id = #{legalList.legal.realEstateProperty.id}",

			"legal.dateCreated <= #{legalList.dateCreatedRange.end}",
			"legal.dateCreated >= #{legalList.dateCreatedRange.begin}",};

	public Legal getLegal() {
		return legal;
	}

	@Override
	public Class<Legal> getEntityClass() {
		return Legal.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		// TODO Auto-generated method stub
		return RESTRICTIONS;
	}

	@Observer("archivedLegal")
	public void onArchive() {
		refresh();
	}
}
