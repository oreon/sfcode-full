package org.witchcraft.seam.action;

import javax.faces.FactoryFinder;

import org.jboss.seam.Seam;
import org.jboss.seam.contexts.ServletLifecycle;
import org.jboss.seam.core.Init;
import org.jboss.seam.init.Initialization;
import org.jboss.seam.mock.MockApplicationFactory;
import org.jboss.seam.mock.MockServletContext;

public class AbstractTestDataFactory<T> {

	void create(){
		MockServletContext servletContext = new MockServletContext();
		ServletLifecycle.beginApplication(servletContext);
		FactoryFinder.setFactory(FactoryFinder.APPLICATION_FACTORY,
				MockApplicationFactory.class.getName());
		new Initialization(servletContext).create().init();
		((Init) servletContext.getAttribute(Seam.getComponentName(Init.class)))
				.setDebug(false);
	}
}
