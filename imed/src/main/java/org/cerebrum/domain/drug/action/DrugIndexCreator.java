package org.cerebrum.domain.drug.action;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.search.jpa.FullTextEntityManager;
import org.jboss.seam.Component;

public class DrugIndexCreator {
	
	private static EntityManagerFactory emf;


	
	public static void init() {
		emf = Persistence.createEntityManagerFactory("imed");
	}
	
	public static void main(String[] args) {
		//init();
		DrugAction dxCodeAction = new DrugAction();

		dxCodeAction.setEntityManager((FullTextEntityManager) Component.getInstance("entityManager"));
		dxCodeAction.reIndex();
		
	}

}
