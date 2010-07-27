package com.nas.recovery.web.action.realestate;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.security.Identity;
import org.testng.annotations.Test;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;
import com.nas.recovery.domain.realestate.FilesUploaded;

public class FilesUploadedActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<FilesUploaded> {

	FilesUploadedAction filesUploadedAction = new FilesUploadedAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<FilesUploaded> getAction() {
		return filesUploadedAction;
	}

}
