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

import org.witchcraft.seam.action.BaseQuery;

import org.witchcraft.base.entity.Range;

import org.primefaces.model.SortOrder;
import org.witchcraft.seam.action.EntityLazyDataModel;
import org.primefaces.model.LazyDataModel;
import java.util.Map;

import org.jboss.seam.annotations.Observer;

import java.math.BigDecimal;
import javax.faces.model.DataModel;

import org.jboss.seam.annotations.security.Restrict;

import org.jboss.seam.annotations.In;
import org.jboss.seam.Component;

import com.oreon.cerebrum.facility.Ward;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class WardListQueryBase extends BaseQuery<Ward, Long> {

	private static final String EJBQL = "select ward from Ward ward";

	protected Ward ward = new Ward();

	public WardListQueryBase() {
		super();
		setOrderColumn("id");
		setOrderDirection("desc");
	}

	public Ward getWard() {
		return ward;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Ward getInstance() {
		return getWard();
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

	public LazyDataModel<Ward> getWardsByFacility(
			final com.oreon.cerebrum.facility.Facility facility) {

		EntityLazyDataModel<Ward, Long> wardLazyDataModel = new EntityLazyDataModel<Ward, Long>(
				this) {

			@Override
			public List<Ward> load(int first, int pageSize, String sortField,
					SortOrder sortOrder, Map<String, String> filters) {

				ward.setFacility(facility);
				return super.load(first, pageSize, sortField, sortOrder,
						filters);
			}
		};

		return wardLazyDataModel;

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

}
