
package com.oreon.cerebrum.web.action.ddx;



import org.jboss.seam.annotations.Name;
	
	
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
