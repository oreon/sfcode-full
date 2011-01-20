package com.nas.recovery.web.action.reports;

import org.witchcraft.jasperreports.BaseReportAction;

import java.util.Date;
import java.util.Map;

import org.jboss.seam.annotations.Name;

@Name("timeSheetReportAction")
public class TimeSheetReportAction extends BaseReportAction {

	org.wc.trackrite.domain.Employee developer;

	Date from;

	Date to;

	public void setDeveloper(org.wc.trackrite.domain.Employee developer) {
		this.developer = developer;
	}

	public org.wc.trackrite.domain.Employee getDeveloper() {

		return developer;

	}

	public void setFrom(Date from) {
		this.from = from;
	}

	public Date getFrom() {

		return from;

	}

	public void setTo(Date to) {
		this.to = to;
	}

	public Date getTo() {

		return to;

	}

	@SuppressWarnings("unchecked")
	@Override
	public void updateParameters(Map map) {

		map.put("developer", developer);

		map.put("from", from);

		map.put("to", to);

	}

}
