package com.oreon.collosum.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

import org.witchcraft.base.entity.BusinessEntity;

import com.oreon.callosum.drugs.AtcDrug;
import com.oreon.callosum.drugs.Drug;

public class FileParse {

	private static final String NOMBRE_PERSISTENCE_UNIT = "appEntityManager";
	private EntityManagerFactory emf;
	private EntityManager em;

	public EntityManagerFactory getEntityManagerFactory() {
		return emf;
	}

	public FileParse() {
		emf = Persistence.createEntityManagerFactory(NOMBRE_PERSISTENCE_UNIT);
		em = getEntityManagerFactory().createEntityManager();

		// getAction().setEntityManager(Search.getFullTextEntityManager(em));
	}

	public void setDrug() {
		String qry = "Select s from AtcDrug s where s.drug is null ";
		String drugq = "Select d from Drug d where d.name = ?1";
		List<AtcDrug> atc = em.createQuery(qry).getResultList();
		for (AtcDrug atcDrug : atc) {
			try{
				Drug drug = (Drug) em.createQuery(drugq).setParameter(1, atcDrug.getName()).getSingleResult();
				atcDrug.setArchived(false);
				atcDrug.setDrug(drug);
				persist(atcDrug);
			}catch(NoResultException nre){
				System.out.println("no drug found "  + atcDrug.getName());
			}catch(Exception e){
				e.printStackTrace();
			}
			
		
		}

	}

	public void parseFile() {
		File file = new File("C:\\tmp\\drugcards.txt");
		int cnt = 1;

		try {
			BufferedReader br = new BufferedReader(new java.io.FileReader(file));
			String line;
			Drug drug = new Drug();

			while ((line = getValue(br)) != null) {
				line = line.trim();
				if (line.startsWith("#")) {
					if (line.contains("Absorption")) {
						drug.setAbsorption(getValue(br));
						// System.t.println(cnt++ + " " + drug.getName() + " "
						// +drug.getAbsorption());
					} else if (line.contains("Mechanism_Of_Action")) {
						drug.setMechanism(getValue(br));
					} else if (line.contains("Biotransformation")) {
						drug.setBiotransformation(getValue(br));
					} else if (line.contains("Dosage_Forms")) {
						drug.setDosageForm(getValue(br));
					} else if (line.contains("Drug_Category")) {
						drug.setDrugCategory(getValue(br));
					} else if (line.contains("Description")) {
						drug.setDescription(getValue(br));
					} else if (line.contains("Food_Interactions")) {
						drug.setFoodInteractions(getValue(br));
					} else if (line.contains("Half_Life")) {
						drug.setHalfLife(getValue(br));
					} else if (line.contains("Generic_Name")) {
						drug.setName(getValue(br));
						System.out
								.println(cnt++ + " " + drug.getName() + " "
										+ drug.getAbsorption()
										+ drug.getDrugCategory());
					} else if (line.contains("Toxicity")) {
						drug.setToxicity(getValue(br));
					} else if (line.contains("Pharmacology")) {
						drug.setPharmacology(getValue(br));
					} else if (line.contains("patient_information_insert")) {
						drug.setPatientInfo(getValue(br));
					} else if (line.contains("interaction_insert")) {
						drug.setInteractions(getValue(br));
					} else if (line.contains("contraindication_insert")) {
						drug.setContraIndication(getValue(br));

					} else if (line.contains("Drug_Type")) {
						// drug.setD(getValue(br));
						// drug.setName(br.readLine());
						// System.out.println(cnt++ + " " + drug.getName() + " "
						// +drug.getAbsorption());
					} else if (line.contains("END_DRUGCARD")) {
						persist(drug);
						drug = new Drug();
					}
				}
			}
			br.close();

			// System.out.println(line);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void persist(BusinessEntity drug) {
		// EntityManager em = getEntityManagerFactory().createEntityManager();
		em.getTransaction().begin();
		em.persist(drug);
		em.getTransaction().commit();
	}

	public static void main(String[] args) {
		new FileParse().setDrug();
	}

	private static String getValue(BufferedReader br) throws IOException {
		return br.readLine();

		/*
		 * String line = br.readLine(); if(line == null) return ""; line =
		 * line.trim(); StringBuffer buffer = new StringBuffer(); while (line !=
		 * null && !line.equals("" ) ) { buffer.append(line); line =
		 * br.readLine();
		 * 
		 * } return buffer.toString();
		 */
	}

}
