package com.nas.recovery.web.action.legal;

import com.nas.recovery.domain.legal.MortgageeInformation;

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

import com.nas.recovery.domain.legal.MortgageeInformation;

/**
 * @author WitchcraftMDA Seam Cartridge
 *
 */
public abstract class MortgageeInformationListQueryBase
		extends
			BaseQuery<MortgageeInformation, Long> {

	//private static final String EJBQL = "select mortgageeInformation from MortgageeInformation mortgageeInformation";

	private MortgageeInformation mortgageeInformation = new MortgageeInformation();

	private static final String[] RESTRICTIONS = {
			"mortgageeInformation.id = #{mortgageeInformationList.mortgageeInformation.id}",

			"mortgageeInformation.dateCreated <= #{mortgageeInformationList.dateCreatedRange.end}",
			"mortgageeInformation.dateCreated >= #{mortgageeInformationList.dateCreatedRange.begin}",};

	public MortgageeInformation getMortgageeInformation() {
		return mortgageeInformation;
	}

	@Override
	public Class<MortgageeInformation> getEntityClass() {
		return MortgageeInformation.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		// TODO Auto-generated method stub
		return RESTRICTIONS;
	}

	@Observer("archivedMortgageeInformation")
	public void onArchive() {
		refresh();
	}
}
