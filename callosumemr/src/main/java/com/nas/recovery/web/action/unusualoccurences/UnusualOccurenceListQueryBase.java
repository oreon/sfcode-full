package com.nas.recovery.web.action.unusualoccurences;

import com.oreon.callosum.unusualoccurences.UnusualOccurence;

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

import com.oreon.callosum.unusualoccurences.UnusualOccurence;

/**
 * D
 * @author WitchcraftMDA Seam Cartridge
 *
 */
public abstract class UnusualOccurenceListQueryBase
		extends
			BaseQuery<UnusualOccurence, Long> {

	private static final String EJBQL = "select unusualOccurence from UnusualOccurence unusualOccurence";

	protected UnusualOccurence unusualOccurence = new UnusualOccurence();

	public UnusualOccurence getUnusualOccurence() {
		return unusualOccurence;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<UnusualOccurence> getEntityClass() {
		return UnusualOccurence.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"unusualOccurence.id = #{unusualOccurenceList.unusualOccurence.id}",

			"unusualOccurence.occurenceType.id = #{unusualOccurenceList.unusualOccurence.occurenceType.id}",

			"unusualOccurence.category = #{unusualOccurenceList.unusualOccurence.category}",

			"unusualOccurence.severity = #{unusualOccurenceList.unusualOccurence.severity}",

			"lower(unusualOccurence.description) like concat(lower(#{unusualOccurenceList.unusualOccurence.description}),'%')",

			"unusualOccurence.patient.id = #{unusualOccurenceList.unusualOccurence.patient.id}",

			"unusualOccurence.createdBy.id = #{unusualOccurenceList.unusualOccurence.createdBy.id}",

			"unusualOccurence.dateCreated <= #{unusualOccurenceList.dateCreatedRange.end}",
			"unusualOccurence.dateCreated >= #{unusualOccurenceList.dateCreatedRange.begin}",};

	public List<UnusualOccurence> getUnusualOccurencesByPatient(
			com.oreon.callosum.patient.Patient patient) {
		//setMaxResults(10000);
		unusualOccurence.setPatient(patient);
		return getResultList();
	}

	@Observer("archivedUnusualOccurence")
	public void onArchive() {
		refresh();
	}

}
