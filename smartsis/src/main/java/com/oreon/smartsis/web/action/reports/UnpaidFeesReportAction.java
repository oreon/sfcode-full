package com.oreon.smartsis.web.action.reports;

import org.witchcraft.jasperreports.BaseReportAction;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.apache.commons.beanutils.PropertyUtils;
import org.jboss.seam.annotations.Name;
import org.witchcraft.jasperreports.BaseReportAction;

import java.util.Date;
import org.witchcraft.utils.DateUtils;

import com.oreon.smartsis.ProjectUtils;

@Name("unpaidFeesReportAction")
public class UnpaidFeesReportAction extends BaseReportAction {

	Integer year = ProjectUtils.getCurrentYear();

	com.oreon.smartsis.domain.FeeMonth month;

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getYear() {

		return year;

	}

	public void setMonth(com.oreon.smartsis.domain.FeeMonth month) {
		this.month = month;
	}

	public com.oreon.smartsis.domain.FeeMonth getMonth() {

		return month;

	}

	@SuppressWarnings("unchecked")
	@Override
	public void updateParameters(Map map) {

		map.put("year", year);

		map.put("month", month);

	}

}
