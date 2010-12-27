package com.nas.recovery.web.action.exams;

import org.wc.trackrite.exams.Candidate;

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

import org.wc.trackrite.exams.Candidate;

/**
 * D
 * @author WitchcraftMDA Seam Cartridge
 *
 */
public abstract class CandidateListQueryBase extends BaseQuery<Candidate, Long> {

	private static final String EJBQL = "select candidate from Candidate candidate";

	protected Candidate candidate = new Candidate();

	public Candidate getCandidate() {
		return candidate;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<Candidate> getEntityClass() {
		return Candidate.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"candidate.id = #{candidateList.candidate.id}",

			"candidate.dateCreated <= #{candidateList.dateCreatedRange.end}",
			"candidate.dateCreated >= #{candidateList.dateCreatedRange.begin}",};

	@Observer("archivedCandidate")
	public void onArchive() {
		refresh();
	}

}
