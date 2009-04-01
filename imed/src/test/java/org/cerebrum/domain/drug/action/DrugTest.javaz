package org.cerebrum.domain.drug.action;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.cerebrum.domain.drug.Drug;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class DrugTest extends org.witchcraft.action.test.BaseTest {

	DrugAction drugAction = new DrugAction();

	@BeforeClass
	public void init() {
		super.init();
		EntityManager em = getEntityManagerFactory().createEntityManager();
		drugAction.setEntityManager(em);
	}

	@Test
	public void testSearch() {
		Query qry = drugAction.getEntityManager().createNativeQuery(
				"select count(*) from drugs");
		Query listQry = drugAction.getEntityManager().createQuery(
				"select d from Drug d ");
		listQry.setMaxResults(30);
		System.out.println("Total Memory" + Runtime.getRuntime().totalMemory());
		System.out.println("Free Memory" + Runtime.getRuntime().freeMemory());

		List<Drug> list = listQry.getResultList();
		Drug drug = list.get(0);
		System.out.println(drug.getName() + " " + drug.getDosage() + " "
				+ drug.getForm());

		// System.out.println("Total Memory"+Runtime.getRuntime().totalMemory());
		System.out.println("Free Memory" + Runtime.getRuntime().freeMemory());
		System.out.println("drugs " + qry.getSingleResult());
	}

}
