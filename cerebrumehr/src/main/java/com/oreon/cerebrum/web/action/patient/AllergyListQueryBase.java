package com.oreon.cerebrum.web.action.patient;

import com.oreon.cerebrum.patient.Allergy;

import org.witchcraft.seam.action.BaseAction;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.framework.EntityQuery;

import org.witchcraft.seam.action.BaseQuery;

import org.witchcraft.base.entity.Range;

import org.primefaces.model.SortOrder;
import org.witchcraft.seam.action.EntityLazyDataModel;
import org.primefaces.model.LazyDataModel;
import java.util.Map;

import org.jboss.seam.annotations.Observer;

import java.math.BigDecimal;
import javax.faces.model.DataModel;

import org.jboss.seam.annotations.security.Restrict;

import org.jboss.seam.annotations.In;
import org.jboss.seam.Component;

import com.oreon.cerebrum.patient.Allergy;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class AllergyListQueryBase extends BaseQuery<Allergy, Long> {

	private static final String EJBQL = "select allergy from Allergy allergy";

	protected Allergy allergy = new Allergy();

	public AllergyListQueryBase() {
		super();
		setOrderColumn("id");
		setOrderDirection("desc");
	}

	public Allergy getAllergy() {
		return allergy;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Allergy getInstance() {
		return getAllergy();
	}

	@Override
	//@Restrict("#{s:hasPermission('allergy', 'view')}")
	public List<Allergy> getResultList() {
		return super.getResultList();
	}

	@Override
	public Class<Allergy> getEntityClass() {
		return Allergy.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"allergy.id = #{allergyList.allergy.id}",

			"allergy.archived = #{allergyList.allergy.archived}",

			"allergy.patient.id = #{allergyList.allergy.patient.id}",

			"allergy.allergen.id = #{allergyList.allergy.allergen.id}",

			"allergy.severity = #{allergyList.allergy.severity}",

			"allergy.dateCreated <= #{allergyList.dateCreatedRange.end}",
			"allergy.dateCreated >= #{allergyList.dateCreatedRange.begin}",};

	public LazyDataModel<Allergy> getAllergysByPatient(
			final com.oreon.cerebrum.patient.Patient patient) {

		EntityLazyDataModel<Allergy, Long> allergyLazyDataModel = new EntityLazyDataModel<Allergy, Long>(
				this) {

			@Override
			public List<Allergy> load(int first, int pageSize,
					String sortField, SortOrder sortOrder,
					Map<String, String> filters) {

				allergy.setPatient(patient);
				return super.load(first, pageSize, sortField, sortOrder,
						filters);
			}
		};

		return allergyLazyDataModel;

	}

	@Observer("archivedAllergy")
	public void onArchive() {
		refresh();
	}

	public void setPatientId(Long id) {
		if (allergy.getPatient() == null) {
			allergy.setPatient(new com.oreon.cerebrum.patient.Patient());
		}
		allergy.getPatient().setId(id);
	}

	public Long getPatientId() {
		return allergy.getPatient() == null ? null : allergy.getPatient()
				.getId();
	}

	public void setAllergenId(Long id) {
		if (allergy.getAllergen() == null) {
			allergy.setAllergen(new com.oreon.cerebrum.patient.Allergen());
		}
		allergy.getAllergen().setId(id);
	}

	public Long getAllergenId() {
		return allergy.getAllergen() == null ? null : allergy.getAllergen()
				.getId();
	}

}
