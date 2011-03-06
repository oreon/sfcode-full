

package com.oreon.smartsis.web.action.exam;

import org.jboss.seam.mock.AbstractSeamTest.ComponentTest;
import org.testng.annotations.Test;


	

public class ElectronicExamInstanceActionTest extends ElectronicExamInstanceActionTestBase{
	
	@Test
	public void testCalculateScore() throws Exception {
		new ComponentTest() {

			protected void testComponents() throws Exception {
				ElectronicExamInstanceAction electronicExamInstanceAction = (ElectronicExamInstanceAction) org.jboss.seam.Component
						.getInstance("electronicExamInstanceAction");
				electronicExamInstanceAction.setId(1L);

				//assert(electronicExamInstanceAction.calculateScore()) == 3;
				
				System.out.println(electronicExamInstanceAction.calculateScore());
			}

		}.run();
	}

	
}
