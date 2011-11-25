package ${package}.biz.geog;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ${package}.biz.IGeogManager;
import ${package}.domain.geog.Country;

/**
 * JUnit tests for <code>IGeogManager</code> API.
 * 
 * @author Kamalpreet Singh
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/com/oreon/bugtracker/junit-context.xml"})
@Transactional(readOnly = true)
public class GeogManagerTest {

	@Resource(name = "geogManager")
	private IGeogManager geogManager;
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void testCreateCountry() {
		Country country = new Country();
		geogManager.createCountry(country);
	}
}
