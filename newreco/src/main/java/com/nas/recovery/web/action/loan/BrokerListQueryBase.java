package com.nas.recovery.web.action.loan;

import com.nas.recovery.domain.loan.Broker;

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

import com.nas.recovery.domain.loan.Broker;

/**
 * @author WitchcraftMDA Seam Cartridge
 *
 */
public abstract class BrokerListQueryBase extends BaseQuery<Broker, Long> {

	//private static final String EJBQL = "select broker from Broker broker";

	private Broker broker = new Broker();

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
			"broker.id = #{brokerList.broker.id}",

			"lower(broker.lastName) like concat(lower(#{brokerList.broker.lastName}),'%')",

			"lower(broker.firstName) like concat(lower(#{brokerList.broker.firstName}),'%')",

			"lower(broker.address.streetDirection) like concat(lower(#{brokerList.broker.address.streetDirection}),'%')",

			"broker.address.streetNumber >= #{brokerList.address_streetNumberRange.begin}",
			"broker.address.streetNumber <= #{brokerList.address_streetNumberRange.end}",

			"lower(broker.address.streetName) like concat(lower(#{brokerList.broker.address.streetName}),'%')",

			"lower(broker.address.province) like concat(lower(#{brokerList.broker.address.province}),'%')",

			"lower(broker.address.streetType) like concat(lower(#{brokerList.broker.address.streetType}),'%')",

			"lower(broker.address.postalCode) like concat(lower(#{brokerList.broker.address.postalCode}),'%')",

			"broker.address.unitNumber >= #{brokerList.address_unitNumberRange.begin}",
			"broker.address.unitNumber <= #{brokerList.address_unitNumberRange.end}",

			"lower(broker.address.city) like concat(lower(#{brokerList.broker.address.city}),'%')",

			"lower(broker.contactDetails.primaryPhone) like concat(lower(#{brokerList.broker.contactDetails.primaryPhone}),'%')",

			"lower(broker.contactDetails.secondaryPhone) like concat(lower(#{brokerList.broker.contactDetails.secondaryPhone}),'%')",

			"lower(broker.user.userName) like concat(lower(#{brokerList.broker.user.userName}),'%')",

			"broker.user.enabled = #{brokerList.broker.user.enabled}",

			"lower(broker.user.email) like concat(lower(#{brokerList.broker.user.email}),'%')",

			"broker.dateCreated <= #{brokerList.dateCreatedRange.end}",
			"broker.dateCreated >= #{brokerList.dateCreatedRange.begin}",};

	public Broker getBroker() {
		return broker;
	}

	@Override
	public Class<Broker> getEntityClass() {
		return Broker.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		// TODO Auto-generated method stub
		return RESTRICTIONS;
	}

	@Observer("archivedBroker")
	public void onArchive() {
		refresh();
	}
}
