package com.oreon.cerebrum.web.action.facility;

import com.oreon.cerebrum.facility.Facility;

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

import org.jboss.seam.annotations.security.Restrict;

import org.jboss.seam.annotations.In;

import com.oreon.cerebrum.facility.Facility;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class FacilityListQueryBase extends BaseQuery<Facility, Long> {

	private static final String EJBQL = "select facility from Facility facility";

	protected Facility facility = new Facility();

	@In(create = true)
	FacilityAction facilityAction;

	public FacilityListQueryBase() {
		super();
		setOrderColumn("id");
		setOrderDirection("desc");
	}

	public Facility getFacility() {
		return facility;
	}

	@Override
	public Facility getInstance() {
		return getFacility();
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	//@Restrict("#{s:hasPermission('facility', 'view')}")
	public List<Facility> getResultList() {
		return super.getResultList();
	}

	@Override
	public Class<Facility> getEntityClass() {
		return Facility.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"facility.id = #{facilityList.facility.id}",

			"facility.archived = #{facilityList.facility.archived}",

			"lower(facility.name) like concat(lower(#{facilityList.facility.name}),'%')",

			"facility.dateCreated <= #{facilityList.dateCreatedRange.end}",
			"facility.dateCreated >= #{facilityList.dateCreatedRange.begin}",};

	@Observer("archivedFacility")
	public void onArchive() {
		refresh();
	}

	//@Restrict("#{s:hasPermission('facility', 'delete')}")
	public void archiveById(Long id) {
		facilityAction.archiveById(id);
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, Facility e) {

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
