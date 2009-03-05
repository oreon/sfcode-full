package org.witchcraft.seam.action;

import javax.persistence.EntityManager;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.core.Events;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.log.Log;

public class BaseAction {
	@Logger
	protected Log log;

	@In
	// @PersistenceContext(type=EXTENDED)
	protected EntityManager entityManager;

	@In
	protected FacesMessages facesMessages;
	
	@In
	protected Events events;

}
