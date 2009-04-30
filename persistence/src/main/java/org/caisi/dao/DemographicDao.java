package org.caisi.dao;

import javax.annotation.security.RolesAllowed;

import org.caisi.persistence.base.GenericDao;
import org.caisi.persistence.model.Demographic;

public interface DemographicDao extends GenericDao<Demographic>{

	
	public Demographic save(Demographic entity);
}
