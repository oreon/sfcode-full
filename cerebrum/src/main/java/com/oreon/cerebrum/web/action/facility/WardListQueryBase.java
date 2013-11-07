package com.oreon.cerebrum.web.action.facility;

import com.oreon.cerebrum.facility.Ward;

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

import com.oreon.cerebrum.facility.Ward;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class WardListQueryBase extends BaseQuery<Ward, Long> {

	private static final String EJBQL = "select ward from Ward ward";

	protected Ward ward = new Ward();

	@In(create = true)
	WardAction wardAction;

	public WardListQueryBase() {
		super();
		setOrderColumn("id");
		setOrderDirection("desc");
	}

	public Ward getWard() {
		return ward;
	}

	@Override
	public Ward getInstance() {
		return getWard();
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	//@Restrict("#{s:hasPermission('ward', 'view')}")
	public List<Ward> getResultList() {
		return super.getResultList();
	}

	@Override
	public Class<Ward> getEntityClass() {
		return Ward.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"ward.id = #{wardList.ward.id}",

			"ward.archived = #{wardList.ward.archived}",

			"ward.facility.id = #{wardList.ward.facility.id}",

			"lower(ward.name) like concat(lower(#{wardList.ward.name}),'%')",

			"ward.gender = #{wardList.ward.gender}",

			"ward.dateCreated <= #{wardList.dateCreatedRange.end}",
			"ward.dateCreated >= #{wardList.dateCreatedRange.begin}",};

	public List<Ward> getWardsByFacility(
			com.oreon.cerebrum.facility.Facility facility) {
		ward.setFacility(facility);
		return getResultList();
	}

	@Observer("archivedWard")
	public void onArchive() {
		refresh();
	}

	public void setFacilityId(Long id) {
		if (ward.getFacility() == null) {
			ward.setFacility(new com.oreon.cerebrum.facility.Facility());
		}
		ward.getFacility().setId(id);
	}

	public Long getFacilityId() {
		return ward.getFacility() == null ? null : ward.getFacility().getId();
	}

	//@Restrict("#{s:hasPermission('ward', 'delete')}")
	public void archiveById(Long id) {
		wardAction.archiveById(id);
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, Ward e) {

		builder.append("\""
				+ (e.getFacility() != null ? e.getFacility().getDisplayName()
						.replace(",", "") : "") + "\",");

		builder.append("\""
				+ (e.getName() != null ? e.getName().replace(",", "") : "")
				+ "\",");

		builder.append("\"" + (e.getGender() != null ? e.getGender() : "")
				+ "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("Facility" + ",");

		builder.append("Name" + ",");

		builder.append("Gender" + ",");

		builder.append("\r\n");
	}
}
