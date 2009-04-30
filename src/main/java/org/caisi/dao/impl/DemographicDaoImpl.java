package org.caisi.dao.impl;

import javax.annotation.security.RolesAllowed;

import org.caisi.dao.DemographicDao;
import org.caisi.persistence.base.GenericDaoImpl;
import org.caisi.persistence.model.Demographic;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

public class DemographicDaoImpl extends GenericDaoImpl<Demographic> implements DemographicDao{
	
	
	
	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseDao#createExampleCriteria(java.lang.Object)
	 */
	public void addAssoications(Criteria criteria, Demographic exampleInstance) {

		if (exampleInstance.getProvider() != null) {
			criteria = criteria.add(Restrictions.eq("provider.providerNo",
					exampleInstance.getProvider().getProviderNo()));
		}

	}
	
	@Override
	@RolesAllowed("ROLE_DOCTOR")
	public Demographic save(Demographic entity) {
		// TODO Auto-generated method stub
		return super.save(entity);
	}

}
