package org.witchcraft.model.xmlutils;

import org.apache.log4j.Logger;
import org.witchcraft.model.xmlutils.XStreamSetup;

import com.thoughtworks.xstream.XStream;

public class XStreamSetupImplForConfig implements XStreamSetup{
	private static final Logger logger = Logger
			.getLogger(XStreamSetupImplForConfig.class);

	
	public void setupXStream(XStream xstream) {
		xstream.alias("config", Config.class);
		xstream.alias("repository", Repository.class);
		xstream.useAttributeFor(Config.class, "name");
		xstream.useAttributeFor(Config.class, "version");
	}
}
