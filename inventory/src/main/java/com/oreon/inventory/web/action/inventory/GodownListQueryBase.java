package com.oreon.inventory.web.action.inventory;

import com.oreon.inventory.inventory.Godown;

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

import com.oreon.inventory.inventory.Godown;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class GodownListQueryBase extends BaseQuery<Godown, Long> {

	private static final String EJBQL = "select godown from Godown godown";

	protected Godown godown = new Godown();

	public Godown getGodown() {
		return godown;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<Godown> getEntityClass() {
		return Godown.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"godown.id = #{godownList.godown.id}",

			"lower(godown.name) like concat(lower(#{godownList.godown.name}),'%')",

			"lower(godown.address.primaryPhone) like concat(lower(#{godownList.godown.address.primaryPhone}),'%')",

			"lower(godown.address.address) like concat(lower(#{godownList.godown.address.address}),'%')",

			"lower(godown.address.city) like concat(lower(#{godownList.godown.address.city}),'%')",

			"lower(godown.address.state) like concat(lower(#{godownList.godown.address.state}),'%')",

			"godown.dateCreated <= #{godownList.dateCreatedRange.end}",
			"godown.dateCreated >= #{godownList.dateCreatedRange.begin}",};

	@Observer("archivedGodown")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, Godown e) {

		builder.append("\""
				+ (e.getName() != null ? e.getName().replace(",", "") : "")
				+ "\",");

		builder.append("\"" + (e.getAddress() != null ? e.getAddress() : "")
				+ "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("Name" + ",");

		builder.append("Address" + ",");

		builder.append("\r\n");
	}
}
