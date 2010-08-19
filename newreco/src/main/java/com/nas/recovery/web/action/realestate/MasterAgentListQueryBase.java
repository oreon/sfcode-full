package com.nas.recovery.web.action.realestate;

import com.nas.recovery.domain.realestate.MasterAgent;

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

import com.nas.recovery.domain.realestate.MasterAgent;

/**
 * @author WitchcraftMDA Seam Cartridge
 *
 */
public abstract class MasterAgentListQueryBase
		extends
			BaseQuery<MasterAgent, Long> {

	//private static final String EJBQL = "select masterAgent from MasterAgent masterAgent";

	private MasterAgent masterAgent = new MasterAgent();

	private Range<Integer> address_streetNumberRange = new Range<Integer>();
	public Range<Integer> getAddress_streetNumberRange() {
		return address_streetNumberRange;
	}
	public void setAddress_streetNumber(Range<Integer> address_streetNumberRange) {
		this.address_streetNumberRange = address_streetNumberRange;
	}

	private Range<Integer> address_unitNumberRange = new Range<Integer>();
	public Range<Integer> getAddress_unitNumberRange() {
		return address_unitNumberRange;
	}
	public void setAddress_unitNumber(Range<Integer> address_unitNumberRange) {
		this.address_unitNumberRange = address_unitNumberRange;
	}

	private static final String[] RESTRICTIONS = {
			"masterAgent.id = #{masterAgentList.masterAgent.id}",

			"masterAgent.realEstateFirm.id = #{masterAgentList.masterAgent.realEstateFirm.id}",

			"lower(masterAgent.lastName) like concat(lower(#{masterAgentList.masterAgent.lastName}),'%')",

			"lower(masterAgent.firstName) like concat(lower(#{masterAgentList.masterAgent.firstName}),'%')",

			"lower(masterAgent.address.streetDirection) like concat(lower(#{masterAgentList.masterAgent.address.streetDirection}),'%')",

			"masterAgent.address.streetNumber >= #{masterAgentList.address_streetNumberRange.begin}",
			"masterAgent.address.streetNumber <= #{masterAgentList.address_streetNumberRange.end}",

			"lower(masterAgent.address.streetName) like concat(lower(#{masterAgentList.masterAgent.address.streetName}),'%')",

			"lower(masterAgent.address.province) like concat(lower(#{masterAgentList.masterAgent.address.province}),'%')",

			"lower(masterAgent.address.streetType) like concat(lower(#{masterAgentList.masterAgent.address.streetType}),'%')",

			"lower(masterAgent.address.postalCode) like concat(lower(#{masterAgentList.masterAgent.address.postalCode}),'%')",

			"masterAgent.address.unitNumber >= #{masterAgentList.address_unitNumberRange.begin}",
			"masterAgent.address.unitNumber <= #{masterAgentList.address_unitNumberRange.end}",

			"lower(masterAgent.address.city) like concat(lower(#{masterAgentList.masterAgent.address.city}),'%')",

			"lower(masterAgent.contactDetails.primaryPhone) like concat(lower(#{masterAgentList.masterAgent.contactDetails.primaryPhone}),'%')",

			"lower(masterAgent.contactDetails.secondaryPhone) like concat(lower(#{masterAgentList.masterAgent.contactDetails.secondaryPhone}),'%')",

			"lower(masterAgent.user.userName) like concat(lower(#{masterAgentList.masterAgent.user.userName}),'%')",

			"masterAgent.user.enabled = #{masterAgentList.masterAgent.user.enabled}",

			"lower(masterAgent.user.email) like concat(lower(#{masterAgentList.masterAgent.user.email}),'%')",

			"masterAgent.dateCreated <= #{masterAgentList.dateCreatedRange.end}",
			"masterAgent.dateCreated >= #{masterAgentList.dateCreatedRange.begin}",};

	public MasterAgent getMasterAgent() {
		return masterAgent;
	}

	@Override
	public Class<MasterAgent> getEntityClass() {
		return MasterAgent.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		// TODO Auto-generated method stub
		return RESTRICTIONS;
	}

	@Observer("archivedMasterAgent")
	public void onArchive() {
		refresh();
	}
}
