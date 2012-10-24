package org.witchcraft.seam.action;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.pcas.datapkg.MetaData;
import com.pcas.datapkg.customReports.MetaEntity;
import com.pcas.datapkg.customReports.MetaField;

public class MetaDataUpdater {
	
	private static final String NOMBRE_PERSISTENCE_UNIT = "appEntityManager";

	//@Create
	public synchronized void start() {

		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory(NOMBRE_PERSISTENCE_UNIT);
		EntityManager em = emf.createEntityManager();

		for (String[] arr : MetaData.ARR_FIELDS) {
			MetaEntity metaEntity = new MetaEntity();
			metaEntity.setName(arr[0]);
			for (int i = 1; i < arr.length; i += 2) {
				MetaField metaField = new MetaField();
				metaField.setName(arr[i]);
				metaField.setType(arr[i+1]);
				metaEntity.getMetaFields().add(metaField);
				metaField.setMetaEntity(metaEntity);
			}
			try {

				//metaEntityAction.persist(metaEntity);
				//String qry = "Select f from MetaField f where f."
				
				em.getTransaction().begin();
				em.persist(metaEntity);
				em.getTransaction().commit();

			} catch (Exception e) {
				System.out.println(e.getMessage());
				em.getTransaction().rollback();
			}
		}
		
		em.close();
		emf.close();
	}

	public static void main(String[] args) {
		new MetaDataUpdater().start();
	}

}
