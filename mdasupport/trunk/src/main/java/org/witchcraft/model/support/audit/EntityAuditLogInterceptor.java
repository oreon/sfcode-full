package org.witchcraft.model.support.audit;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Type;

import org.acegisecurity.Authentication;
import org.acegisecurity.context.SecurityContext;
import org.acegisecurity.context.SecurityContextHolder;
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
		// TODO Auto-generated method stub
		saveAuditLog(entity, AuditAction.EDIT);
		return false;
	}

	@Override
	public void onDelete(Object entity, Serializable id, Object[] state,
			String[] propertyNames, org.hibernate.type.Type[] types) {
		saveAuditLog(entity, AuditAction.DELETE);
	}

	private void saveAuditLog(Object entity, AuditAction action) {

		if (entity instanceof Auditable) {
			System.out.println("SAVE AUDIT LOG CALLED");
			Authentication authentication = SecurityContextHolder.getContext()
					.getAuthentication();

			AuditLog auditLog = new AuditLog(action, (BusinessEntity) entity, entity.getClass()
					.getCanonicalName(), authentication == null ? "UNKNOWN"
					: authentication.getDetails().toString());

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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return barr;
	}

}
