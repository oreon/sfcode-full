package com.oreon.smartsis.web.action.attendance;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.security.Identity;
import org.testng.annotations.Test;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;
import com.oreon.smartsis.attendance.GradeAttendance;

public class GradeAttendanceActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<GradeAttendance> {

	GradeAttendanceAction gradeAttendanceAction = new GradeAttendanceAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<GradeAttendance> getAction() {
		return gradeAttendanceAction;
	}

}
