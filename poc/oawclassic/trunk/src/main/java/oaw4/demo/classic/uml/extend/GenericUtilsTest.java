package oaw4.demo.classic.uml.extend;

import junit.framework.TestCase;

public class GenericUtilsTest extends TestCase {
	
	public void testGetSimpleName(){
		assertEquals(GenericUtils.getSimpleName("customer.primaryAddress.state"),
				"state");
	}
	
	public void testRemoveSquareBrackets(){
		assertEquals(GenericUtils.removeSquareBrackets("[primaryAddress.state]"),
		"primaryAddress.state");
	}

}
