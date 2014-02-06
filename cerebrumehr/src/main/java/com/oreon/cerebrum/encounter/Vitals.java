package com.oreon.cerebrum.encounter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Date;
import javax.ws.rs.core.Response;

import javax.persistence.*;
import org.hibernate.validator.*;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import org.apache.solr.analysis.LowerCaseFilterFactory;
import org.apache.solr.analysis.SnowballPorterFilterFactory;
import org.apache.solr.analysis.StandardTokenizerFactory;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Filters;
import org.hibernate.annotations.Cascade;

import org.hibernate.search.annotations.AnalyzerDef;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Boost;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Parameter;
import org.hibernate.search.annotations.TokenFilterDef;
import org.hibernate.search.annotations.TokenizerDef;
import org.hibernate.search.annotations.ContainedIn;
import org.hibernate.search.annotations.IndexedEmbedded;

import org.hibernate.annotations.Filter;

import org.hibernate.validator.constraints.*;
import javax.validation.constraints.*;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.jboss.seam.annotations.Name;

import org.witchcraft.model.support.audit.Auditable;

import org.witchcraft.utils.*;

import org.witchcraft.base.entity.FileAttachment;
import org.witchcraft.base.entity.BaseEntity;

import com.oreon.cerebrum.ProjectUtils;

@Embeddable
public class Vitals implements java.io.Serializable {
	private static final long serialVersionUID = -946647828L;

	@Column(unique = false)
	protected Integer SysBP

	;

	@Column(unique = false)
	protected Integer DiasBP

	;

	@Column(unique = false)
	protected Double Temperature

	;

	@Column(unique = false)
	protected Integer Pulse

	;

	@Column(unique = false)
	protected Integer RespirationRate

	;

	public void setSysBP(Integer SysBP) {
		this.SysBP = SysBP;
	}

	public Integer getSysBP() {

		return SysBP;

	}

	public void setDiasBP(Integer DiasBP) {
		this.DiasBP = DiasBP;
	}

	public Integer getDiasBP() {

		return DiasBP;

	}

	public void setTemperature(Double Temperature) {
		this.Temperature = Temperature;
	}

	public Double getTemperature() {

		return Temperature;

	}

	public void setPulse(Integer Pulse) {
		this.Pulse = Pulse;
	}

	public Integer getPulse() {

		return Pulse;

	}

	public void setRespirationRate(Integer RespirationRate) {
		this.RespirationRate = RespirationRate;
	}

	public Integer getRespirationRate() {

		return RespirationRate;

	}

	@Transient
	public String getDisplayName() {
		try {
			return SysBP + "";
		} catch (Exception e) {
			return "Exception - " + e.getMessage();
		}
	}

}
