package com.oreon.smartsis.web.action.reports;

import org.witchcraft.jasperreports.BaseReportAction;

import java.util.Date;
import java.util.Map;

import org.jboss.seam.annotations.Name;

import org.witchcraft.utils.DateUtils;

@Name("studentsByGenderAndGradeAction")
public class StudentsByGenderAndGradeAction extends BaseReportAction {

	@SuppressWarnings("unchecked")
	@Override
	public void updateParameters(Map map) {

	}

}
