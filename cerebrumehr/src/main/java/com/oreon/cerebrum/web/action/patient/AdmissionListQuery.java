package com.oreon.cerebrum.web.action.patient;

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

import com.oreon.cerebrum.patient.Admission;

@Name("admissionList")
@Scope(ScopeType.CONVERSATION)
public class AdmissionListQuery extends AdmissionListQueryBase
		implements
			java.io.Serializable {
	
	
	/*
	public LazyDataModel<Admission> getCurrentAdmissions() {

		EntityLazyDataModel<Admission, Long> admissionLazyDataModel = new EntityLazyDataModel<Admission, Long>(
				this) {

			@Override
			public List<Admission> load(int first, int pageSize,
					String sortField, SortOrder sortOrder,
					Map<String, String> filters) {

				getDischargeDateRange().setEnd(new Date());
				List<Admission> result =  super.load(first, pageSize, sortField, sortOrder,
						filters);
				
				
				getDischargeDateRange().setEnd(null);
				return result;
				
			}
		};

		return admissionLazyDataModel;

	}*/
	
	
	public LazyDataModel<Admission> getCurrentAdmissions() {

		EntityLazyDataModel<Admission, Long> admissionLazyDataModel = new EntityLazyDataModel<Admission, Long>(
				this) {

			@Override
			public List<Admission> load(int first, int pageSize,
					String sortField, SortOrder sortOrder,
					Map<String, String> filters) {

				admission.setIsCurrent(true);
				return super.load(first, pageSize, sortField, sortOrder,
						filters);
			}
		};

		return admissionLazyDataModel;

	}
	
	
	public LazyDataModel<Admission> getPastAdmissions() {

		EntityLazyDataModel<Admission, Long> admissionLazyDataModel = new EntityLazyDataModel<Admission, Long>(
				this) {

			@Override
			public List<Admission> load(int first, int pageSize,
					String sortField, SortOrder sortOrder,
					Map<String, String> filters) {

				admission.setIsCurrent(false);
				return super.load(first, pageSize, sortField, sortOrder,
						filters);
			}
		};

		return admissionLazyDataModel;

	}
	
	
	
	
	

}
