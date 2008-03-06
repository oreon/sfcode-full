package org.witchcraft.model.support.service;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Transient;

import org.hibernate.Criteria;
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
public abstract class BaseServiceImpl<T extends BusinessEntity> {
	
	private AuditLogDao auditLogDao;
	
	private Class<T> persistentService;
	
	@SuppressWarnings("unchecked")
	public BaseServiceImpl() {

		try {
			Type superclass = ((Class) getClass().getGenericSuperclass())
					.getGenericSuperclass();

			this.persistentService = (Class<T>) ((ParameterizedType) superclass)
					.getActualTypeArguments()[0];
		} catch (ClassCastException cce) {
			this.persistentService = (Class<T>) ((ParameterizedType) ((Class) getClass())
					.getGenericSuperclass()).getActualTypeArguments()[0];
		}
	}

	public void setAuditLogDao(AuditLogDao auditLogDao) {
		this.auditLogDao = auditLogDao;
	}
	 
	/** All concrete service classes extending from BaseServiceImpl should implement this method
 	 * @return
	 */
	public abstract  GenericDAO<T> getDao();
	
	/** Returns the auditlogs for the entity T
	 * @param <T>
	 * @return
	 */
	@Transient
	public  List<AuditLog<T>> getAuditLogs(){
		
		List<AuditLog<T>> list = new ArrayList<AuditLog<T>>();
		List<AuditLog> auditLogs =  auditLogDao.getAuditLogsForEntity
			(persistentService.getCanonicalName());
		
			
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
	
	protected  void ensureUnique(T entity, T existingEntity,
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
	
	public long getCount() {
		return getDao().getCount();
	}
	
	public long getCount(Date fromDate, Date toDate){
		return getDao().getCount(fromDate, toDate);
	}
	
	public Criteria createExampleCriteria(T exampleInstance){
		return getDao().createExampleCriteria(exampleInstance);
	}

}
