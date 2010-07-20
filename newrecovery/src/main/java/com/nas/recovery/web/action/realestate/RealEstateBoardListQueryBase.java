package com.nas.recovery.web.action.realestate;

import com.nas.recovery.domain.realestate.RealEstateBoard;

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

import com.nas.recovery.domain.realestate.RealEstateBoard;

/**
 * @author WitchcraftMDA Seam Cartridge
 *
 */
public abstract class RealEstateBoardListQueryBase
		extends
			BaseQuery<RealEstateBoard, Long> {

	//private static final String EJBQL = "select realEstateBoard from RealEstateBoard realEstateBoard";

	private RealEstateBoard realEstateBoard = new RealEstateBoard();

	private static final String[] RESTRICTIONS = {
			"realEstateBoard.id = #{realEstateBoardList.realEstateBoard.id}",

			"lower(realEstateBoard.name) like concat(lower(#{realEstateBoardList.realEstateBoard.name}),'%')",

			"realEstateBoard.dateCreated <= #{realEstateBoardList.dateCreatedRange.end}",
			"realEstateBoard.dateCreated >= #{realEstateBoardList.dateCreatedRange.begin}",};

	public RealEstateBoard getRealEstateBoard() {
		return realEstateBoard;
	}

	@Override
	public Class<RealEstateBoard> getEntityClass() {
		return RealEstateBoard.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		// TODO Auto-generated method stub
		return RESTRICTIONS;
	}

	@Observer("archivedRealEstateBoard")
	public void onArchive() {
		refresh();
	}
}
