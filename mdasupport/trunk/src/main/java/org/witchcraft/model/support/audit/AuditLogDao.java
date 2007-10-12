package org.witchcraft.model.support.audit;

import java.util.List;

import org.witchcraft.model.support.BusinessEntity;
import org.witchcraft.model.support.dao.GenericDAO;

public interface AuditLogDao extends GenericDAO<AuditLog>{
	
	public List<AuditLog> getAuditLogsForEntity(String name);

}
