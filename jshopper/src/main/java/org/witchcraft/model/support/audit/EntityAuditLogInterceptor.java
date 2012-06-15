package org.witchcraft.model.support.audit;


/**
 * @author jsingh
 * 
 */
//@Name("entityAuditLogInterceptor")
/*
public class EntityAuditLogInterceptor extends BaseAction<AuditLog>{

	protected Log logger = LogFactory.getLog(getClass());

	//private AuditLogDao auditLogDao;

	//@Override
	@Observer(value = {"ARCHIVE", "CREATE" , "UPDATE", "DELETE"})
	public boolean onEvent(EventTypes event, Object entity ) {
		saveAuditLog(event, entity );
		return true;
	}

	@SuppressWarnings("unchecked")
	private void saveAuditLog(EventTypes action, Object entity) {

		//log.info("SAVE AUDIT LOG CALLED FOR " + action);
		
		if (entity instanceof Auditable) {
			Credentials credentials = Identity.instance().getCredentials();
			String userName = credentials == null ? "UNKNOWN": credentials.getUsername();
			BusinessEntity businessEntity = (BusinessEntity) entity	;
			AuditLog auditLog = new AuditLog(action, businessEntity,  entity.getClass().getCanonicalName(), businessEntity.getId(),  userName);
			persist(auditLog);
		}
	}

	

	private byte[] objectToByteArray(Object object) {
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
	
	
	@Transient
	public List<AuditLog> getAuditLogsForEntityAndId(String name, Long id){
		String qryString = "Select e from AuditLog e where  e.entityName = ?1 and e.entityId = ?2";
		Query query = getEntityManager().createQuery(qryString).setParameter(1, name).setParameter(2, id);
		return query.getResultList();
	}

	

}*/
