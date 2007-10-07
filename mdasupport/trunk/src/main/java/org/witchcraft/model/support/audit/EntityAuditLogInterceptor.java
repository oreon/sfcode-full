package org.witchcraft.model.support.audit;

import java.io.Serializable;
import java.lang.reflect.Type;

import org.acegisecurity.context.SecurityContext;
import org.acegisecurity.context.SecurityContextHolder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.EmptyInterceptor;
import org.witchcraft.model.support.security.AbstractUser;

/**
 * @author jsingh
 *
 */
public class EntityAuditLogInterceptor extends EmptyInterceptor {

	protected Log logger = LogFactory.getLog(getClass());

	private AuditLogDao auditLogDao;

	public boolean onSave(Object entity, Serializable id, Object[] state,
			String[] propertyNames, Type[] types) {
		
		saveAuditLog(entity, AuditAction.CREATE);
		return false;
	}

	public boolean onFlushDirty(Object entity, Serializable id,
			Object[] currentState, Object[] previousState,
			String[] propertyNames, Type[] types) {
		saveAuditLog(entity, AuditAction.EDIT);
		return false;
	}
	
	public void onDelete(Object entity, Serializable id, Object[] state,
			String[] propertyNames, Type[] types) {
		saveAuditLog(entity, AuditAction.DELETE);
	}
	
	private void saveAuditLog(Object entity, AuditAction action) {
		if (entity instanceof Auditable) {
			
			SecurityContext securityContext = SecurityContextHolder.getContext();
			
			AuditLog auditLog = new AuditLog(action, 
					entity.toString(),
					entity.getClass().getCanonicalName(),
					securityContext.getAuthentication().getDetails().toString()
			);
			
			auditLogDao.save(auditLog);
		}
	}

	

}
