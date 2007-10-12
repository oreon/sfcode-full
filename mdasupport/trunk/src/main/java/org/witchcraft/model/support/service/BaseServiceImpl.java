package org.witchcraft.model.support.service;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Transient;

import org.witchcraft.model.support.BusinessEntity;
import org.witchcraft.model.support.audit.AuditLog;
import org.witchcraft.model.support.audit.AuditLogDao;
import org.witchcraft.model.support.dao.GenericDAO;
import org.witchcraft.model.support.errorhandling.BusinessException;

/** 
 * Contains methods common to all ServiceImpls
 * @author jsingh
 *
 */
public abstract class BaseServiceImpl {
	
	private AuditLogDao auditLogDao;

	public void setAuditLogDao(AuditLogDao auditLogDao) {
		this.auditLogDao = auditLogDao;
	}
	 
	/** All concrete service classes extending from BaseServiceImpl should implement this method
 	 * @return
	 */
	public abstract <T> GenericDAO<T> getDao();
	
	/** Returns the auditlogs for the entity T
	 * @param <T>
	 * @return
	 */
	@Transient
	public <T> List<AuditLog<T>> getAuditLogs(){
		
		Class<T> t = (Class<T>) getMethodByName("getAuditLogs").getGenericReturnType();
		String name= t.getCanonicalName();
		
		List<AuditLog<T>> list = new ArrayList<AuditLog<T>>();
		List<AuditLog> auditLogs =  auditLogDao.getAuditLogsForEntity(name);
		
			
		for (AuditLog auditLog : auditLogs) {
			list.add( (AuditLog<T>)auditLog);
		}
		
		return list;
	}
	
	private Method getMethodByName(String name){
		Method[] methods = getClass().getMethods();
		for (java.lang.reflect.Method method : methods) {
			if(method.getName().equalsIgnoreCase(name))
				return method;
		}
		
		return null;
	}
	
	protected <T extends BusinessEntity> void ensureUnique(T entity, T existingEntity,
			String exceptionId) {
		if (existingEntity == null)
			return; //no Entity exists with the given email - no need to check unique constraint violation

		if (entity.getId() == null) { // for a new entity
			throw new BusinessException(exceptionId);
		} else {//for updating an existing entiy
			if (existingEntity.getId().longValue() != entity.getId()
					.longValue())
				throw new BusinessException(exceptionId);
		}

	}
	
	public int getCount() {
		return getDao().getCount();
	}
	
	

}
