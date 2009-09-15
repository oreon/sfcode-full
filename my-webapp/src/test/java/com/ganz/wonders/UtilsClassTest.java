package com.ganz.wonders;

import static org.junit.Assert.*;

import org.junit.Test;

public class UtilsClassTest {

	@Test
	public void testAdd() {
		assertEquals( "Result should be sum of 5 and 7 ", UtilsClass.add(5, 7), 13);
	}

}
