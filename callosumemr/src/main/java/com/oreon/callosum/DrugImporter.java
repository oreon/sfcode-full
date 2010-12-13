package com.oreon.callosum;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.FlushModeType;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.lang.StringUtils;
import org.apache.solr.client.solrj.request.CoreAdminRequest.Persist;
import org.drools.StatelessSession;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.validator.InvalidStateException;
import org.testng.annotations.BeforeClass;

import com.oreon.callosum.drugs.Drug;
import com.oreon.callosum.drugs.DrugCategory;

import dbdrugs.Dosage;
import dbdrugs.DrugInteraction;
import dbdrugs.DrugType;
import dbdrugs.Drugs;

public class DrugImporter {

	private static final String NOMBRE_PERSISTENCE_UNIT = "appEntityManager";
	private static EntityManagerFactory emf;
	protected static EntityManager em;

	public EntityManagerFactory getEntityManagerFactory() {
		return emf;
	}

	public static void init() {
		emf = Persistence.createEntityManagerFactory(NOMBRE_PERSISTENCE_UNIT);
		em = emf.createEntityManager();
	}

	public static void main(String[] args) throws IOException, JAXBException {

		// importDrugs();
		loadInteractions();
	}

	private static String listToStringDosage(List<Dosage> dosage) {
		// TODO Auto-generated method stub
		return null;
	}

	private static double massageHalfLife(String halfLife) {
		return 0;
	}

	private static String listToString(List<String> atcCode) {
		StringBuilder sb = new StringBuilder();
		for (String string : atcCode) {
			sb.append(string + "; ");
		}
		return sb.toString();
	}

	public static void loadInteractions() throws IOException, JAXBException {
		init();
		InputStream inputStream = new FileInputStream("/devtools/drugbank.xml");
		List<DrugType> drugs = getDrugs(inputStream);
		for (DrugType drugType : drugs) {
			
			if (Long.parseLong(drugType.getDrugbankId().substring(2)) <= 1340)
				continue;
			
			if (drugType.getGroups().getGroup().contains("experimental"))
				continue;
			
			secondpass(drugType);
			
		}
		inputStream.close();
		deinit();
	}

	public static void secondpass(DrugType drugType) {
		Drug d = getDrugByDrugBankId(drugType.getDrugbankId());
		List<DrugInteraction> diList = drugType.getDrugInteractions()
				.getDrugInteraction();

		for (DrugInteraction drugInteraction : diList) {
			if(!em.getTransaction().isActive())
				em.getTransaction().begin();
			com.oreon.callosum.drugs.DrugInteraction di = new com.oreon.callosum.drugs.DrugInteraction();
			try {
				di.setInteractingDrug(getDrugByDrugBankId(drugInteraction
						.getDrug()
						+ ""));
				di.setDrug(d);
				di.setDescription(drugInteraction.getDescription());
				em.persist(di);
				em.getTransaction().commit();
			} catch (NoResultException nre) {
				System.out.println("WARN:: No drug found for ->"
						+ drugInteraction.getDrug());
				em.getTransaction().rollback();
			}catch(Exception e){
				e.printStackTrace();
				em.getTransaction().rollback();
			}
		}

	}

	private static Drug getDrugByDrugBankId(String dbid) {
		if (!dbid.startsWith("DB")) {
			String prefix = "";

			if (dbid.length() == 4)
				prefix = "0";
			if (dbid.length() == 3)
				prefix = "00";
			if (dbid.length() == 2)
				prefix = "000";
			if (dbid.length() == 1)
				prefix = "0000";

			dbid = "DB" + prefix + dbid;
		}
		javax.persistence.Query qry = em
				.createQuery("select d from Drug d where d.drugBankId= :id");
		qry.setParameter("id", dbid);
		Drug d = (Drug) qry.getSingleResult();
		return d;
	}

	public static void importDrugs() throws Exception {

		init();
		// em.setFlushMode(FlushModeType.COMMIT);
		// em.getTransaction().begin();
		InputStream inputStream = new FileInputStream("/devtools/drugbank.xml");

		List<DrugType> drugs = getDrugs(inputStream);

		int i = 0;
		// TODO dosages, halflifenumberofhours, patientinfo, contra, synonyms,
		// prices

		javax.persistence.Query qry = em
				.createQuery("select e from DrugCategory e where e.name = :name");

		if (i == 0 && !em.getTransaction().isActive())
			em.getTransaction().begin();

		for (DrugType drugType : drugs) {

			if (!em.getTransaction().isActive())
				em.getTransaction().begin();

			if (drugType.getGroups().getGroup().contains("experimental"))
				continue;

			if (drugType.getName().length() > 255) {
				System.out.println(drugType.getName() + "too long ; skipping");
				continue;
			}

			Drug salt = new Drug();

			salt.setName(drugType.getName());
			System.out.println("IMPRTING : " + drugType.getName());

			List<String> catlist = drugType.getCategories().getCategory();
			salt.setDrugBankId(drugType.getDrugbankId());

			salt.setAbsorption(drugType.getAbsorption());
			salt.setBiotransformation(drugType.getBiotransformation());
			salt.setAtcCodes(listToString(drugType.getAtcCodes().getAtcCode()));
			salt.setDescription(drugType.getDescription());
			salt.setFoodInteractions(listToString(drugType
					.getFoodInteractions().getFoodInteraction()));
			salt
					.setHalfLife(StringUtils.isEmpty(drugType.getHalfLife()) ? "Not Available"
							: drugType.getHalfLife());
			salt.setIndication(drugType.getIndication());
			salt.setMechanismOfAction(drugType.getMechanismOfAction());
			salt.setPharmacology(drugType.getPharmacology());
			salt.setRouteOfElimination(drugType.getRouteOfElimination());
			salt.setToxicity(drugType.getToxicity());
			salt.setVolumeOfDistribution(drugType.getVolumeOfDistribution());
			// salt.setHalfLifeNumberOfHours(halfLifeNumberOfHours)
			// salt.setDosageForm(listToStringDosage(drugType.getDosages().getDosage()))

			for (String cat : catlist) {
				DrugCategory dc = new DrugCategory();
				dc.setName(cat);
				salt.getDrugCategorys().add(dc);
				dc.getDrugs().add(salt);
				System.out.println("added cat " + dc.getName());

				qry.setParameter("name", dc.getName());

				if (qry.getResultList().isEmpty()) {

					try {
						em.persist(dc);
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}

				}

			}
			try {
				em.persist(salt);
				em.getTransaction().commit();
				System.out.println("imported " + drugType.getName());
			} catch (InvalidStateException ise) {
				// em.getTransaction().rollback();
				ise.printStackTrace();
			} catch (Exception e) {
				// em.getTransaction().rollback();
				System.out.println("error importing " + drugType.getName()
						+ " " + e.getMessage());
			}

			if (i++ >= 10) {
				i = 0;
				// em.flush();
				// em.clear();
			}

		}

		// System.out.println(d.getDrug().get(0).getName());
		inputStream.close();

		// tx.commit();
		// sess.close();
		deinit();
	}

	private static void deinit() {
		if (em.getTransaction().isActive())
			em.getTransaction().commit();
		em.close();
		emf.close();
	}

	private static List<DrugType> getDrugs(InputStream inputStream)
			throws JAXBException {
		String packageName = "dbdrugs";
		JAXBContext jc = JAXBContext.newInstance(packageName);
		Unmarshaller u = jc.createUnmarshaller();
		Drugs d = (Drugs) u.unmarshal(inputStream);
		System.out.println("fd " + d.getDrug().size());
		List<DrugType> drugs = d.getDrug();
		return drugs;
	}
}
