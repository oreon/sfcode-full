package com.pcas.datapkg.web.action.inventory;

import com.pcas.datapkg.domain.inventory.Location;

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

import com.pcas.datapkg.domain.inventory.Location;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class LocationListQueryBase extends BaseQuery<Location, Long> {

	private static final String EJBQL = "select location from Location location";

	protected Location location = new Location();

	public Location getLocation() {
		return location;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<Location> getEntityClass() {
		return Location.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"location.id = #{locationList.location.id}",

			"lower(location.name) like concat(lower(#{locationList.location.name}),'%')",

			"location.dateCreated <= #{locationList.dateCreatedRange.end}",
			"location.dateCreated >= #{locationList.dateCreatedRange.begin}",};

	@Observer("archivedLocation")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, Location e) {

		builder.append("\""
				+ (e.getName() != null ? e.getName().replace(",", "") : "")
				+ "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("Name" + ",");

		builder.append("\r\n");
	}
}
