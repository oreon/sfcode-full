package org.witchcraft.model.xmlutils;

import static org.junit.Assert.*;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.witchcraft.model.xmlutils.ObjectFromXMLFactory;

public class ObjectFromXMLFactoryTest {
	private static final Logger logger = Logger
			.getLogger(ObjectFromXMLFactoryTest.class);

	@Test
	public void testFromXMLFile() {
		Config config = new ObjectFromXMLFactory().fromXMLFile("/configfile.xml", new XStreamSetupImplForConfig());
		assertEquals(config.getVersion(), "2.0");
		assertEquals(config.getRepositories().size(), 2);
	}
}
