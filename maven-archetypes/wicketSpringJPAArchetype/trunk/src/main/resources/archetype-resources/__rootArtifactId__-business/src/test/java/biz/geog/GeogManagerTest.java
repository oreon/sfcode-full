package ${package}.biz.geog;

import javax.annotation.Resource;

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
@ContextConfiguration({"/junit-context.xml"})
@TransactionConfiguration(defaultRollback = false)
@Transactional(readOnly = true)
public class GeogManagerTest {

	@Resource(name = "geogManager")
	private IGeogManager geogManager;
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void testCreateCountry() {
		Country country1 = new Country();
		country1.setActive(true);
		country1.setCode("US");
		country1.setName("United States");
		geogManager.createCountry(country1);
		
		Country country2 = new Country();
		country2.setActive(true);
		country2.setCode("CA");
		country2.setName("Canada");
		geogManager.createCountry(country2);
	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void testCreateState() {
		State state1 = new State();
		state1.setActive(true);
		state1.setCode("AL");
		state1.setName("Alberta");
		geogManager.createState(state1);
		
		State state2 = new State();
		state2.setActive(true);
		state2.setCode("CA");
		state2.setName("California");
		geogManager.createState(state2);
	}
}
