package com.nas.recovery.web.action.propertymanagement;

import com.nas.recovery.domain.propertymanagement.Utilitiy;

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

import com.nas.recovery.domain.propertymanagement.Utilitiy;

/**
 * @author WitchcraftMDA Seam Cartridge
 *
 */
public abstract class UtilitiyListQueryBase extends BaseQuery<Utilitiy, Long> {

	//private static final String EJBQL = "select utilitiy from Utilitiy utilitiy";

	private Utilitiy utilitiy = new Utilitiy();

	private static final String[] RESTRICTIONS = {
			"utilitiy.id = #{utilitiyList.utilitiy.id}",

			"lower(utilitiy.name) like concat(lower(#{utilitiyList.utilitiy.name}),'%')",

			"utilitiy.transferred = #{utilitiyList.utilitiy.transferred}",

			"lower(utilitiy.accountNumber) like concat(lower(#{utilitiyList.utilitiy.accountNumber}),'%')",

			"utilitiy.dateCreated <= #{utilitiyList.dateCreatedRange.end}",
			"utilitiy.dateCreated >= #{utilitiyList.dateCreatedRange.begin}",};

	public Utilitiy getUtilitiy() {
		return utilitiy;
	}

	@Override
	public Class<Utilitiy> getEntityClass() {
		return Utilitiy.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		// TODO Auto-generated method stub
		return RESTRICTIONS;
	}

	@Observer("archivedUtilitiy")
	public void onArchive() {
		refresh();
	}
}
