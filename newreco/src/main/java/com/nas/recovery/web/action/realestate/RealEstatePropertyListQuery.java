
package com.nas.recovery.web.action.realestate;



import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import com.nas.recovery.domain.realestate.RealEstateProperty;
	
	
@Name("realEstatePropertyList")
@Scope(ScopeType.CONVERSATION)
public class RealEstatePropertyListQuery extends RealEstatePropertyListQueryBase implements java.io.Serializable{
	
	
	public List<RealEstateProperty> getListNew(){
		String qry = "Select c from RealEstateProperty c where c.processId is null ";
		return getEntityManager().createQuery(qry).getResultList();
	}
	
	public List<RealEstateProperty> getInProcess(){
		String qry = "Select c from RealEstateProperty c where c.processId is not null ";
		return getEntityManager().createQuery(qry).getResultList();
	}
	
}
