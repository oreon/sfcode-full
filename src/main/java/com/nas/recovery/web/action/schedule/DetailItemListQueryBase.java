package com.nas.recovery.web.action.schedule;

import org.wc.trackrite.schedule.DetailItem;

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

import org.wc.trackrite.schedule.DetailItem;

/**
 * @author WitchcraftMDA Seam Cartridge
 *
 */
public abstract class DetailItemListQueryBase
		extends
			BaseQuery<DetailItem, Long> {

	//private static final String EJBQL = "select detailItem from DetailItem detailItem";

	protected DetailItem detailItem = new DetailItem();

	private static final String[] RESTRICTIONS = {
			"detailItem.id = #{detailItemList.detailItem.id}",

			"detailItem.scheduleItem.id = #{detailItemList.detailItem.scheduleItem.id}",

			"detailItem.dateCreated <= #{detailItemList.dateCreatedRange.end}",
			"detailItem.dateCreated >= #{detailItemList.dateCreatedRange.begin}",};

	public DetailItem getDetailItem() {
		return detailItem;
	}

	@Override
	public Class<DetailItem> getEntityClass() {
		return DetailItem.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		// TODO Auto-generated method stub
		return RESTRICTIONS;
	}

	@Observer("archivedDetailItem")
	public void onArchive() {
		refresh();
	}
}
