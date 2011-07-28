

package com.oreon.smartsis.web.action.fees;


	import javax.persistence.EntityManager;
	import javax.persistence.Query;
	
	import org.jboss.seam.security.Identity;
	import org.testng.annotations.Test;
	import org.witchcraft.base.entity.*;
	import org.hibernate.annotations.Filter;
	
	import org.testng.annotations.BeforeClass;
	import org.witchcraft.seam.action.BaseAction;
	import com.oreon.smartsis.fees.MonthlyFee;


public class MonthlyFeeActionTest extends MonthlyFeeActionTestBase{
	
	@Test
	public void testLoad(){
		//getAction();
		MonthlyFee monthlyFee = em.find(MonthlyFee.class, 1L);
		if(monthlyFee != null){
			System.out.println(monthlyFee.getMonth());
		}
	}
	
}
