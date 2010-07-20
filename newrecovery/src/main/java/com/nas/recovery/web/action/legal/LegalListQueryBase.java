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

	private Range<Integer> legal_mortgageNumberRange = new Range<Integer>();
	public Range<Integer> getLegal_mortgageNumberRange() {
		return legal_mortgageNumberRange;
	}
	public void setLegal_mortgageNumber(Range<Integer> legal_mortgageNumberRange) {
		this.legal_mortgageNumberRange = legal_mortgageNumberRange;
	}

	private Range<Integer> legal_legalNumberRange = new Range<Integer>();
	public Range<Integer> getLegal_legalNumberRange() {
		return legal_legalNumberRange;
	}
	public void setLegal_legalNumber(Range<Integer> legal_legalNumberRange) {
		this.legal_legalNumberRange = legal_legalNumberRange;
	}

	private static final String[] RESTRICTIONS = {
			"legal.id = #{legalList.legal.id}",

			"lower(legal.courtNumber) like concat(lower(#{legalList.legal.courtNumber}),'%')",

			"lower(legal.specialHandling) like concat(lower(#{legalList.legal.specialHandling}),'%')",

			"legal.mortgageNumber >= #{legalList.legal_mortgageNumberRange.begin}",
			"legal.mortgageNumber <= #{legalList.legal_mortgageNumberRange.end}",

			"legal.legalNumber >= #{legalList.legal_legalNumberRange.begin}",
			"legal.legalNumber <= #{legalList.legal_legalNumberRange.end}",

			"lower(legal.status) like concat(lower(#{legalList.legal.status}),'%')",

			"legal.litigation = #{legalList.legal.litigation}",

			"lower(legal.legalDescription) like concat(lower(#{legalList.legal.legalDescription}),'%')",

			"lower(legal.legalFileNumber) like concat(lower(#{legalList.legal.legalFileNumber}),'%')",

			"lower(legal.pin) like concat(lower(#{legalList.legal.pin}),'%')",

			"lower(legal.pid) like concat(lower(#{legalList.legal.pid}),'%')",

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
