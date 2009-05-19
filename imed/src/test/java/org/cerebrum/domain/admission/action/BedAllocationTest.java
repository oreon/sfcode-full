
		package org.cerebrum.domain.admission.action;
		
	import javax.persistence.EntityManager;
	import javax.persistence.Query;
	
	import org.jboss.seam.security.Identity;
	import org.testng.annotations.Test;
	import org.witchcraft.base.entity.*;
	import org.hibernate.annotations.Filter;
	
	import org.testng.annotations.BeforeClass;
	import org.witchcraft.seam.action.BaseAction;
	import org.cerebrum.domain.admission.BedAllocation;

		

	public class BedAllocationTest extends org.witchcraft.action.test.BaseTest<BedAllocation>{
	
		BedAllocationAction bedAllocationAction = new BedAllocationAction();
	
		@BeforeClass
		public void init(){
			super.init();
		}
		
		@Override
		public BaseAction<BedAllocation> getAction() {
			return bedAllocationAction;
		}
	}

	