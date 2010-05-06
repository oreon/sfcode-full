package org.witchcraft.model.support.audit;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.Transient;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Observer;
import org.jboss.seam.core.Events;
import org.jboss.seam.security.Credentials;
import org.jboss.seam.security.Identity;
import org.witchcraft.base.entity.BusinessEntity;
import org.witchcraft.seam.action.BaseAction;
import org.witchcraft.seam.action.EventTypes;

/**
 * @author jsingh
 * 
 */
@Name("entityAuditLogInterceptor")
public class EntityAuditLogInterceptor extends BaseAction<AuditLog>{

	protected Log logger = LogFactory.getLog(getClass());

	//private AuditLogDao auditLogDao;

	//@Override
	@Observer(value = {"ARCHIVE", "CREATE" , "UPDATE", "DELETE"})
	public boolean onEvent(EventTypes event, Object entity ) {
		saveAuditLog(event, entity );
		return true;
	}

	private void saveAuditLog(EventTypes action, Object entity) {

		//logger.info("SAVE AUDIT LOG CALLED FOR " + action);
		
		if (entity instanceof Auditable) {
			Credentials credentials = Identity.instance().getCredentials();

			AuditLog auditLog = new AuditLog(action, (BusinessEntity) entity, entity.getClass()
					.getCanonicalName(), credentials == null ? "UNKNOWN"
					: credentials.getUsername());
			persist(auditLog);
		}
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
	
	@Transient
	public List<AuditLog> getAuditLogsForEntity(String name){
		String qryString = "Select e from AuditLog e where  e.entityName = ?1";
		Query query = getEntityManager().createQuery(qryString).setParameter(1, name);
		return query.getResultList();
	}

	@Override
	public void findRecords() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public AuditLog getEntity() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setEntity(AuditLog t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setEntityList(List<AuditLog> list) {
		// TODO Auto-generated method stub
		
	}

}
