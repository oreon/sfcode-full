package com.nas.recovery.web.action.onepack;

import org.wc.trackrite.onepack.Candidate;

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

import org.wc.trackrite.onepack.Candidate;

/**
 * @author WitchcraftMDA Seam Cartridge
 *
 */
public abstract class CandidateListQueryBase extends BaseQuery<Candidate, Long> {

	//private static final String EJBQL = "select candidate from Candidate candidate";

	protected Candidate candidate = new Candidate();

	private static final String[] RESTRICTIONS = {
			"candidate.id = #{candidateList.candidate.id}",

			"candidate.dateCreated <= #{candidateList.dateCreatedRange.end}",
			"candidate.dateCreated >= #{candidateList.dateCreatedRange.begin}",};

	public Candidate getCandidate() {
		return candidate;
	}

	@Override
	public Class<Candidate> getEntityClass() {
		return Candidate.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		// TODO Auto-generated method stub
		return RESTRICTIONS;
	}

	@Observer("archivedCandidate")
	public void onArchive() {
		refresh();
	}
}
