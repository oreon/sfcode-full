package com.pcas.datapkg.web.action.inventory;

import com.pcas.datapkg.domain.inventory.Machine;

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

import java.math.BigDecimal;

import com.pcas.datapkg.domain.inventory.Machine;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class MachineListQueryBase extends BaseQuery<Machine, Long> {

	private static final String EJBQL = "select machine from Machine machine";

	protected Machine machine = new Machine();

	public Machine getMachine() {
		return machine;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<Machine> getEntityClass() {
		return Machine.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"machine.id = #{machineList.machine.id}",

			"machine.customer.id = #{machineList.machine.customer.id}",

			"lower(machine.name) like concat(lower(#{machineList.machine.name}),'%')",

			"machine.location.id = #{machineList.machine.location.id}",

			"machine.dateCreated <= #{machineList.dateCreatedRange.end}",
			"machine.dateCreated >= #{machineList.dateCreatedRange.begin}",};

	public List<Machine> getMachinesByCustomer(
			com.pcas.datapkg.domain.inventory.Customer customer) {
		//setMaxResults(10000);
		machine.setCustomer(customer);
		return getResultList();
	}

	public List<Machine> getMachinesByLocation(
			com.pcas.datapkg.domain.inventory.Location location) {
		//setMaxResults(10000);
		machine.setLocation(location);
		return getResultList();
	}

	@Observer("archivedMachine")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, Machine e) {

		builder.append("\""
				+ (e.getCustomer() != null ? e.getCustomer().getDisplayName()
						.replace(",", "") : "") + "\",");

		builder.append("\""
				+ (e.getName() != null ? e.getName().replace(",", "") : "")
				+ "\",");

		builder.append("\""
				+ (e.getLocation() != null ? e.getLocation().getDisplayName()
						.replace(",", "") : "") + "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("Customer" + ",");

		builder.append("Name" + ",");

		builder.append("Location" + ",");

		builder.append("\r\n");
	}
}
