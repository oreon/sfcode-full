
		package org.cerebrum.domain.admission.action;
		
	import javax.persistence.EntityManager;
	import javax.persistence.Query;
	
	import org.jboss.seam.security.Identity;
	import org.testng.annotations.Test;
	import org.witchcraft.base.entity.*;
	import org.hibernate.annotations.Filter;
	
	import org.testng.annotations.BeforeClass;
	import org.witchcraft.seam.action.BaseAction;
	import org.cerebrum.domain.admission.Admission;

		

	public class AdmissionTest extends org.witchcraft.action.test.BaseTest<Admission>{
	
		AdmissionAction admissionAction = new AdmissionAction();
	
		@BeforeClass
		public void init(){
			super.init();
		}
		
		@Override
		public BaseAction<Admission> getAction() {
			return admissionAction;
		}
	}

	