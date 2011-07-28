package com.nas.recovery.web.action.drugs;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;

import com.oreon.callosum.drugs.AtcDrug;

public class AtcDrugActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<AtcDrug> {

	AtcDrugAction atcDrugAction = new AtcDrugAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<AtcDrug> getAction() {
		return atcDrugAction;
	}

}
