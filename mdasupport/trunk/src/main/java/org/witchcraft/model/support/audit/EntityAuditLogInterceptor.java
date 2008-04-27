package org.witchcraft.model.support.audit;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Type;

import org.acegisecurity.Authentication;
import org.acegisecurity.context.SecurityContext;
import org.acegisecurity.context.SecurityContextHolder;
import org.acegisecurity.userdetails.UserDetails;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.EmptyInterceptor;
import org.witchcraft.model.support.BusinessEntity;
import org.witchcraft.model.support.security.AbstractUser;

/**
 * @author jsingh
 * 
 */
public class EntityAuditLogInterceptor extends EmptyInterceptor {

	protected Log logger = LogFactory.getLog(getClass());

	private AuditLogDao auditLogDao;

	@Override
	public boolean onSave(Object entity, Serializable id, Object[] state,
			String[] propertyNames, org.hibernate.type.Type[] types) {

		saveAuditLog(entity, AuditAction.CREATE);
		return false;
	}

	@Override
	public boolean onFlushDirty(Object entity, Serializable id,
			Object[] currentState, Object[] previousState,
			String[] propertyNames, org.hibernate.type.Type[] types) {
		
		saveAuditLog(entity, AuditAction.EDIT);
		return false;
	}

	@Override
	public void onDelete(Object entity, Serializable id, Object[] state,
			String[] propertyNames, org.hibernate.type.Type[] types) {
		saveAuditLog(entity, AuditAction.DELETE);
	}

	private void saveAuditLog(Object entity, AuditAction action) {

		logger.info("SAVE AUDIT LOG CALLED FOR " + action);
		
		if (entity instanceof Auditable) {
			
			Authentication authentication = SecurityContextHolder.getContext()
					.getAuthentication();

			AuditLog auditLog = new AuditLog(action, (BusinessEntity) entity, entity.getClass()
					.getCanonicalName(), authentication == null ? "UNKNOWN"
					: ((UserDetails)authentication.getPrincipal()).getUsername());
			

			auditLogDao.save(auditLog);
		}
	}

	public void setAuditLogDao(AuditLogDao auditLogDao) {
		this.auditLogDao = auditLogDao;
	}

	byte[] objectToByteArray(Object object) {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream oos;
		byte barr[] = null;

		try {
			oos = new ObjectOutputStream(bos);
			oos.writeObject(bos);
			barr = bos.toByteArray();

			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return barr;
	}

}
