package com.nas.recovery.web.action.realestate;

import com.nas.recovery.domain.realestate.AgentHistory;

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

import com.nas.recovery.domain.realestate.AgentHistory;

/**
 * @author WitchcraftMDA Seam Cartridge
 *
 */
public abstract class AgentHistoryListQueryBase
		extends
			BaseQuery<AgentHistory, Long> {

	//private static final String EJBQL = "select agentHistory from AgentHistory agentHistory";

	private AgentHistory agentHistory = new AgentHistory();

	private Range<Integer> agentHistory_realEstateNumberRange = new Range<Integer>();
	public Range<Integer> getAgentHistory_realEstateNumberRange() {
		return agentHistory_realEstateNumberRange;
	}
	public void setAgentHistory_realEstateNumber(
			Range<Integer> agentHistory_realEstateNumberRange) {
		this.agentHistory_realEstateNumberRange = agentHistory_realEstateNumberRange;
	}

	private static final String[] RESTRICTIONS = {
			"agentHistory.id = #{agentHistoryList.agentHistory.id}",

			"agentHistory.realEstateNumber >= #{agentHistoryList.agentHistory_realEstateNumberRange.begin}",
			"agentHistory.realEstateNumber <= #{agentHistoryList.agentHistory_realEstateNumberRange.end}",

			"agentHistory.dateCreated <= #{agentHistoryList.dateCreatedRange.end}",
			"agentHistory.dateCreated >= #{agentHistoryList.dateCreatedRange.begin}",};

	public AgentHistory getAgentHistory() {
		return agentHistory;
	}

	@Override
	public Class<AgentHistory> getEntityClass() {
		return AgentHistory.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		// TODO Auto-generated method stub
		return RESTRICTIONS;
	}

	@Observer("archivedAgentHistory")
	public void onArchive() {
		refresh();
	}
}
