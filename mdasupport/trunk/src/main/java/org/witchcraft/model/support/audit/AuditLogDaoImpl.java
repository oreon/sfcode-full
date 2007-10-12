package org.witchcraft.model.support.audit;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.Transient;

import org.witchcraft.model.support.BusinessEntity;
import org.witchcraft.model.support.dao.BaseDao;

public class AuditLogDaoImpl extends BaseDao<AuditLog> implements AuditLogDao {
	
	@Transient
	public List<AuditLog> getAuditLogsForEntity(String name){
		
		String qryString = "Select e from AuditLog e where  e.entityName = ?1";
		Query query = entityManager.createQuery(qryString).setParameter(1, name);

		return query.getResultList();
	}
	
}
