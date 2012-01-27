package ${package}.biz.geog;

import java.util.List;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ${package}.biz.IGeogManager;
import ${package}.domain.geog.Country;
import ${package}.domain.geog.State;

/**
 * JUnit tests for <code>IGeogManager</code> API.
 * 
 * @author Kamalpreet Singh
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/junitContext.xml"})
@TransactionConfiguration(defaultRollback = false)
@Transactional(readOnly = true)
public class GeogManagerTest {

	@Resource(name = "geogManager")
	private IGeogManager geogManager;
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void testCreateCountry() {
		Country usCountry = new Country();
		usCountry.setActive(true);
		usCountry.setCode("US");
		usCountry.setName("United states");
		geogManager.createCountry(usCountry);
		
		usCountry = geogManager.findCountryById(new Long(1));
		Assert.assertEquals("US", usCountry.getCode());
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void testUpdateCountry() {
		Country usCountry = geogManager.findCountryById(new Long(1));
		usCountry.setActive(true);
		usCountry.setName("United States");
		geogManager.updateCountry(usCountry);
		
		usCountry = geogManager.findCountryById(new Long(1));
		Assert.assertEquals("United States", usCountry.getName());
	}
	
	@Test
	public void testFindAllCountries() {
		List<Country> countryList = geogManager.findAllCountries();
		Assert.assertEquals(1, countryList.size());
	}
	
	@Test
	public void testFindAllActiveCountries() {
		List<Country> countryList = geogManager.findAllActiveCountries();
		Assert.assertEquals(1, countryList.size());
	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void testCreateState() {
		State albertaState = new State();
		albertaState.setActive(true);
		albertaState.setCode("AL");
		albertaState.setName("Alberta");
		
		Country usCountry = geogManager.findCountryById(new Long(1));
		albertaState.setCountry(usCountry);
		
		geogManager.createState(albertaState);
		
		albertaState = geogManager.findStateById(new Long(1));
		Assert.assertEquals("AL", albertaState.getCode());
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void testCreateStateAndAssignToCountry() {
		State britishColumbiaState = new State();
		britishColumbiaState.setActive(true);
		britishColumbiaState.setCode("BC");
		britishColumbiaState.setName("British Columbia");
		
		Country usCountry = geogManager.findCountryById(new Long(1));
		britishColumbiaState.setCountry(usCountry);
		geogManager.createState(britishColumbiaState);
		usCountry.getStates().add(britishColumbiaState);
		geogManager.updateCountry(usCountry);
	}
}
