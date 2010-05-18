package com.nas.recoveryportal.appraisal.action;

import com.nas.recoveryportal.appraisal.ScreenShots;

import org.witchcraft.seam.action.BaseAction;

import java.util.Arrays;
import java.util.Date;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.framework.EntityQuery;
import org.witchcraft.base.entity.BaseQuery;
import org.witchcraft.base.entity.Range;

import com.nas.recoveryportal.appraisal.ScreenShots;

/**
 * @author WitchcraftMDA Seam Cartridge
 *
 */
public abstract class ScreenShotsListQueryBase
		extends
			BaseQuery<ScreenShots, Long> {

	//private static final String EJBQL = "select screenShots from ScreenShots screenShots";

	private ScreenShots screenShots = new ScreenShots();

	private static final String[] RESTRICTIONS = {
			"screenShots.id = #{screenShotsList.screenShots.id}",

			"screenShots.image = #{screenShotsList.screenShots.image}",

			"lower(screenShots.tite) like concat(lower(#{screenShotsList.screenShots.tite}),'%')",

			"screenShots.story = #{screenShotsList.screenShots.story}",

			"screenShots.dateCreated <= #{screenShotsList.dateCreatedRange.end}",
			"screenShots.dateCreated >= #{screenShotsList.dateCreatedRange.begin}",};

	public ScreenShots getScreenShots() {
		return screenShots;
	}

	@Override
	public Class<ScreenShots> getEntityClass() {
		return ScreenShots.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		// TODO Auto-generated method stub
		return RESTRICTIONS;
	}
}
