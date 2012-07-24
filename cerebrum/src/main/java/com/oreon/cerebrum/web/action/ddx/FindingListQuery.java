
package com.oreon.cerebrum.web.action.ddx;



import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.framework.EntityQuery;
import org.witchcraft.base.entity.BaseQuery;
import org.witchcraft.base.entity.Range;

import org.jboss.seam.annotations.Observer;

import java.math.BigDecimal;
	
	
@Name("findingList")
//@Scope(ScopeType.CONVERSATION)
public class FindingListQuery extends FindingListQueryBase implements java.io.Serializable{
	
	private static final String[] CUSTOM_RESTRICTIONS = {
		"finding.id = #{findingList.finding.id}",

		"lower(finding.name) like concat('%',lower(#{findingList.finding.name}),'%')",

		"finding.dateCreated <= #{findingList.dateCreatedRange.end}",
		"finding.dateCreated >= #{findingList.dateCreatedRange.begin}",};
	
	
	@Override
	public String[] getEntityRestrictions() {
		// TODO Auto-generated method stub
		return CUSTOM_RESTRICTIONS;
	}
	
}
