package com.oreon.bugtracker.test;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.oreon.bugtracker.biz.IGeogManager;
import com.oreon.bugtracker.domain.geog.Country;

/**
 * JUnit tests for <code>IGeogManager</code> API.
 * 
 * @author Kamalpreet Singh
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/parent-context.xml"})
@Transactional(readOnly = true)
public class GeogManagerTest {

	@Resource(name = "geogManager")
	private IGeogManager geogManager;
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void testCreateCountry() {
		Country country = new Country();
		geogManager.createCountry(country);
		/*CSR csr1 = new CSR();
		csr1.setEmail("kasingh@marketlive.com");
		csr1.setPassword("kamal123");
		csr1.setFirstName("Kamalpreet");
		csr1.setLastName("Singh");
		CSRType persistedCSRType1 = csrManager.findCSRTypeByCode("CSR");
		csr1.setCsrType(persistedCSRType1);
		csr1.setEmployeeId(null);
		csrManager.createCSR(csr1);
        CSR persistedCSR1 = csrManager.findCSRByEmail("kasingh@marketlive.com");
        assertEquals("kasingh@marketlive.com", persistedCSR1.getEmail());*/
	}
}
