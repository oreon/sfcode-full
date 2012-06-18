package org.witchcraft.seam.action;

import javax.enterprise.context.ConversationScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Produces;
import javax.inject.Named;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import org.hibernate.Session;
import org.jboss.seam.persistence.SeamManagedPersistenceContextCreated;
import org.jboss.solder.core.ExtensionManaged;

@Named
public class EMFactory {

	@ExtensionManaged
	@Produces
	@PersistenceUnit
	@ConversationScoped
	private static EntityManagerFactory entityManagerFactory;

	public void setupEntityManager( @Observes SeamManagedPersistenceContextCreated event ) {

		Session session = (Session) event.getEntityManager().getDelegate();
		//session.disableFilter( "archiveFilterDef" );
		//session.enableFilter("archiveFilterDef").setParameter("aArchived", "0");
		//session.enableFilter( "myfilter" );

	}
}
