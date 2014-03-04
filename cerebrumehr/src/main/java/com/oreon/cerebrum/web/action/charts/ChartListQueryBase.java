package com.oreon.cerebrum.web.action.charts;

import com.oreon.cerebrum.charts.Chart;

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

import com.oreon.cerebrum.charts.Chart;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class ChartListQueryBase extends BaseQuery<Chart, Long> {

	private static final String EJBQL = "select chart from Chart chart";

	protected Chart chart = new Chart();

	public ChartListQueryBase() {
		super();
		setOrderColumn("id");
		setOrderDirection("desc");
	}

	public Chart getChart() {
		return chart;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Chart getInstance() {
		return getChart();
	}

	@Override
	//@Restrict("#{s:hasPermission('chart', 'view')}")
	public List<Chart> getResultList() {
		return super.getResultList();
	}

	@Override
	public Class<Chart> getEntityClass() {
		return Chart.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"chart.id = #{chartList.chart.id}",

			"chart.archived = #{chartList.chart.archived}",

			"lower(chart.name) like concat(lower(#{chartList.chart.name}),'%')",

			"chart.dateCreated <= #{chartList.dateCreatedRange.end}",
			"chart.dateCreated >= #{chartList.dateCreatedRange.begin}",};

	@Observer("archivedChart")
	public void onArchive() {
		refresh();
	}

}
