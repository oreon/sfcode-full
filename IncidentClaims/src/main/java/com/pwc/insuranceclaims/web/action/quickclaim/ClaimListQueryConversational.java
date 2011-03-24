
package com.pwc.insuranceclaims.web.action.quickclaim;



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

import com.pwc.insuranceclaims.quickclaim.Claim;
	
	
@Name("claimListConversational")
@Scope(ScopeType.CONVERSATION)
public class ClaimListQueryConversational extends ClaimListQueryBase implements java.io.Serializable{
	
	@Override
	public Claim getClaim() {
		if (!isPostBack()) {
			claim.setClaimDate(null);
		}
		return claim;
	}
}
