package org.caisi;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import org.oscar.servlets.ReflectionBasedPopulator;

public class ReflectionBasedPopulatorTest {

	@Test
	public void testCreateBeanFromMap() {
		Map map = new HashMap();
		map.put("firstName", "sohan");
		map.put("lastName", null);
		map.put("age", "23");
		
		
		map.put("address.city", "Toronto");
		map.put("address.country", StringUtils.EMPTY);
		
		Student student = new Student();
		
		try {
			student = (Student) ReflectionBasedPopulator.createBeanFromMap(map, student);
			assertEquals(student.getFirstName(), "sohan");
			assertEquals(student.getFirstName(),BeanUtils.getProperty(student, "firstName"));
			assertEquals(student.getLastName(), null);
			assertEquals(student.getAge(), 23);
			assertNull(student.getAddress().getCountry());
			//assertEquals(student.getAddress().getCity(), "Toronto");
			System.out.println(student);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		} 
	}
	
	@Test 
	public void testSafeGetValue(){
		Student student = new Student(); 
		//student.setAddress(address);
		assertNull(ReflectionBasedPopulator.safeGetValue(student, "address.city"));
		Address address = new Address();
		address.setCity("tor");
		student.setAddress(address);
		assertEquals(ReflectionBasedPopulator.safeGetValue(student, "address.city"), "tor");
	}

}


