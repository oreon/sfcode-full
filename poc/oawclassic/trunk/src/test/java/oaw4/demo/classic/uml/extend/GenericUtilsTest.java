package oaw4.demo.classic.uml.extend;

import java.util.List;

import junit.framework.TestCase;

public class GenericUtilsTest extends TestCase {

	public void testGetSimpleName() {
		assertEquals(GenericUtils
				.getSimpleName("customer.primaryAddress.state"), "state");
	}

	public void testRemoveSquareBrackets() {
		assertEquals(GenericUtils
				.removeSquareBrackets("[primaryAddress.state]"),
				"primaryAddress.state");
	}

	public void testTokenizeStrings() {
		String arg = "hi there [[ qry is very nice ]][[another qry ]]";
		
		List<String> lstTokens = GenericUtils.tokenizeString(arg,
				" *\\[\\[ *| *\\]\\] *");
		System.out.println(lstTokens.size());
		for (String string : lstTokens) {
			System.out.println(string);
		}
	}
	
	public void testReadProperties(){
		String value = GenericUtils.readProperty("users.package");
		assertNotNull(value);
	}

}
