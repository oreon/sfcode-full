package com.nas.recovery.web.action.realestate;

import com.nas.recovery.domain.realestate.TenantInfo;

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

import com.nas.recovery.domain.realestate.TenantInfo;

/**
 * @author WitchcraftMDA Seam Cartridge
 *
 */
public abstract class TenantInfoListQueryBase
		extends
			BaseQuery<TenantInfo, Long> {

	//private static final String EJBQL = "select tenantInfo from TenantInfo tenantInfo";

	private TenantInfo tenantInfo = new TenantInfo();

	private Range<Double> tenantInfo_rentRange = new Range<Double>();
	public Range<Double> getTenantInfo_rentRange() {
		return tenantInfo_rentRange;
	}
	public void setTenantInfo_rent(Range<Double> tenantInfo_rentRange) {
		this.tenantInfo_rentRange = tenantInfo_rentRange;
	}

	private Range<Date> tenantInfo_leaseExpiryRange = new Range<Date>();
	public Range<Date> getTenantInfo_leaseExpiryRange() {
		return tenantInfo_leaseExpiryRange;
	}
	public void setTenantInfo_leaseExpiry(
			Range<Date> tenantInfo_leaseExpiryRange) {
		this.tenantInfo_leaseExpiryRange = tenantInfo_leaseExpiryRange;
	}

	private Range<Date> tenantInfo_attornmentRange = new Range<Date>();
	public Range<Date> getTenantInfo_attornmentRange() {
		return tenantInfo_attornmentRange;
	}
	public void setTenantInfo_attornment(Range<Date> tenantInfo_attornmentRange) {
		this.tenantInfo_attornmentRange = tenantInfo_attornmentRange;
	}

	private static final String[] RESTRICTIONS = {
			"tenantInfo.id = #{tenantInfoList.tenantInfo.id}",

			"lower(tenantInfo.unit) like concat(lower(#{tenantInfoList.tenantInfo.unit}),'%')",

			"lower(tenantInfo.name) like concat(lower(#{tenantInfoList.tenantInfo.name}),'%')",

			"tenantInfo.rent >= #{tenantInfoList.tenantInfo_rentRange.begin}",
			"tenantInfo.rent <= #{tenantInfoList.tenantInfo_rentRange.end}",

			"tenantInfo.lease = #{tenantInfoList.tenantInfo.lease}",

			"tenantInfo.leaseExpiry >= #{tenantInfoList.tenantInfo_leaseExpiryRange.begin}",
			"tenantInfo.leaseExpiry <= #{tenantInfoList.tenantInfo_leaseExpiryRange.end}",

			"tenantInfo.attornment >= #{tenantInfoList.tenantInfo_attornmentRange.begin}",
			"tenantInfo.attornment <= #{tenantInfoList.tenantInfo_attornmentRange.end}",

			"tenantInfo.utilities = #{tenantInfoList.tenantInfo.utilities}",

			"tenantInfo.realEstateProperty = #{tenantInfoList.tenantInfo.realEstateProperty}",

			"tenantInfo.dateCreated <= #{tenantInfoList.dateCreatedRange.end}",
			"tenantInfo.dateCreated >= #{tenantInfoList.dateCreatedRange.begin}",};

	public TenantInfo getTenantInfo() {
		return tenantInfo;
	}

	@Override
	public Class<TenantInfo> getEntityClass() {
		return TenantInfo.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		// TODO Auto-generated method stub
		return RESTRICTIONS;
	}

	@Observer("archivedTenantInfo")
	public void onArchive() {
		refresh();
	}
}
