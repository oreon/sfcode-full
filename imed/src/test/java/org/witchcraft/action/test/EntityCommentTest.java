package org.witchcraft.action.test;

import java.util.List;

import javax.persistence.Query;

import org.cerebrum.domain.drug.Drug;
import org.cerebrum.domain.drug.action.DrugAction;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.witchcraft.base.entity.EntityComment;
import org.witchcraft.seam.action.BaseAction;

public class EntityCommentTest extends org.witchcraft.action.test.BaseTest<Drug> {

	DrugAction drugAction = new DrugAction();

	@BeforeClass
	public void init() {
		super.init();
	}
	
	@Override
	public BaseAction<Drug> getAction() {
		return drugAction;
	}
	
	@Test
	public void index(){
		//drugAction.reIndex();
		//resourceloa
	}
	
	@Test
	public void testTextSearch(){
		/*
		drugAction.setEntity(new Drug());
		"clemitidine"
		List<Drug> drugs = drugAction.textSearch();
		
		for (Drug drug : drugs) {
			System.out.println(drug.getName() + " :: " + drug.getActiveIngred() );
		}*/
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
