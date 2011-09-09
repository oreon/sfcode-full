package com.oreon.cerebrum.web.action.billing;

import com.oreon.cerebrum.billing.Service;

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

import com.oreon.cerebrum.billing.Service;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class ServiceListQueryBase extends BaseQuery<Service, Long> {

	private static final String EJBQL = "select service from Service service";

	protected Service service = new Service();

	public Service getService() {
		return service;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<Service> getEntityClass() {
		return Service.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private Range<Double> priceRange = new Range<Double>();
	public Range<Double> getPriceRange() {
		return priceRange;
	}
	public void setPrice(Range<Double> priceRange) {
		this.priceRange = priceRange;
	}

	private static final String[] RESTRICTIONS = {
			"service.id = #{serviceList.service.id}",

			"lower(service.name) like concat(lower(#{serviceList.service.name}),'%')",

			"service.price >= #{serviceList.priceRange.begin}",
			"service.price <= #{serviceList.priceRange.end}",

			"service.dateCreated <= #{serviceList.dateCreatedRange.end}",
			"service.dateCreated >= #{serviceList.dateCreatedRange.begin}",};

	@Observer("archivedService")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, Service e) {

		builder.append("\""
				+ (e.getName() != null ? e.getName().replace(",", "") : "")
				+ "\",");

		builder.append("\"" + (e.getPrice() != null ? e.getPrice() : "")
				+ "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("Name" + ",");

		builder.append("Price" + ",");

		builder.append("\r\n");
	}
}
