package org.cerebrum.drugimporter;

import org.cerebrum.domain.vitals.Vitals;
import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import org.apache.commons.lang.StringUtils;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Scope;

import org.jboss.seam.Component;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;

import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.log.Log;

import org.witchcraft.seam.action.BaseAction;
import org.jboss.seam.annotations.Observer;
import java.sql.*;
import java.sql.*;
import java.sql.*;
import java.sql.*;

public class UADrugAction extends BaseAction<UADrug> implements
		java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private UADrug uaDrug;

	@DataModel
	private List<UADrug> uaDrugList;

	@Factory("uaDrugList")
	@Observer("archivedVitals")
	public void findRecords() {
		search();
	}

	public UADrug getEntity() {
		return uaDrug;
	}

	@Override
	public void setEntity(UADrug t) {
		this.uaDrug = t;
	}

	@Override
	public void setEntityList(List<UADrug> list) {
		this.uaDrugList = list;
	}

	public void updateAssociations() {

	}

	public List<UADrug> getEntityList() {
		if (uaDrugList == null) {
			findRecords();
		}
		return uaDrugList;
	}

	public static void main(String args[]) {
		UADrug drug = new UADrug();
		drug.setBrandNames("Lipitor");

		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("imed");
		EntityManager em = emf.createEntityManager();
		em.persist(drug);

		/*
		 * Connection DbConn ; try {
		 * Class.forName("com.mysql.jdbc.Driver").newInstance(); String url =
		 * "jdbc:mysql://localhost/imeddb"; DbConn =
		 * DriverManager.getConnection(url, "root", "jessmada");
		 * System.out.print("\n[Performing INSERT] ... "); String INSERT_RECORD
		 * = "insert into uadrugs(id, archived,date_created) "+
		 * 
		 * 
		 * "values(?, ?)"; try { Statement st = DbConn.createStatement();
		 * st.executeUpdate("INSERT INTO uadrugs " +
		 * "VALUES ( 1,0,'','',6,'a','sd','fd','df',8)"); } catch (SQLException
		 * ex) { System.err.println(ex.getMessage()); }
		 * 
		 * DbConn.close(); } catch (ClassNotFoundException ex)
		 * {System.err.println(ex.getMessage());} catch (IllegalAccessException
		 * ex) {System.err.println(ex.getMessage());} catch
		 * (InstantiationException ex) {System.err.println(ex.getMessage());}
		 * catch (SQLException ex) {System.err.println(ex.getMessage());} }
		 */

	}

}
