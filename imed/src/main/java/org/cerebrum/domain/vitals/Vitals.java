package org.cerebrum.domain.vitals;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Date;

import javax.persistence.*;
import org.hibernate.validator.*;

import org.apache.solr.analysis.LowerCaseFilterFactory;
import org.apache.solr.analysis.SnowballPorterFilterFactory;
import org.apache.solr.analysis.StandardTokenizerFactory;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Filter;
import org.hibernate.search.annotations.AnalyzerDef;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Parameter;
import org.hibernate.search.annotations.TokenFilterDef;
import org.hibernate.search.annotations.TokenizerDef;
import org.hibernate.search.annotations.ContainedIn;
import org.hibernate.search.annotations.IndexedEmbedded;

import org.hibernate.validator.Length;
import org.hibernate.validator.NotNull;
import org.jboss.seam.annotations.Name;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

@Entity
@Table(name = "vitals")
@Name("vitals")
@Filter(name = "archiveFilterDef")
@Indexed
@AnalyzerDef(name = "customanalyzer", tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class), filters = {
		@TokenFilterDef(factory = LowerCaseFilterFactory.class),
		@TokenFilterDef(factory = SnowballPorterFilterFactory.class, params = {@Parameter(name = "language", value = "English")})})
public class Vitals extends BusinessEntity {

	@Lob
	protected String general;

	@IndexedEmbedded
	protected Pulse pulse = new Pulse();

	@IndexedEmbedded
	protected Temperature temperature = new Temperature();

	@IndexedEmbedded
	protected BloodPressure bloodPressure = new BloodPressure();

	public void setGeneral(String general) {
		this.general = general;
	}

	public String getGeneral() {
		return general;
	}

	public void setPulse(Pulse pulse) {
		this.pulse = pulse;
	}

	public Pulse getPulse() {
		return pulse;
	}

	public void setTemperature(Temperature temperature) {
		this.temperature = temperature;
	}

	public Temperature getTemperature() {
		return temperature;
	}

	public void setBloodPressure(BloodPressure bloodPressure) {
		this.bloodPressure = bloodPressure;
	}

	public BloodPressure getBloodPressure() {
		return bloodPressure;
	}

	@Transient
	public String getDisplayName() {
		return general + "";
	}

	/** This method is used by hibernate full text search - override to add additional fields
	 * @see org.witchcraft.model.support.BusinessEntity#retrieveSearchableFieldsArray()
	 */
	@Override
	public List<String> listSearchableFields() {
		List<String> listSearchableFields = new ArrayList<String>();
		listSearchableFields.addAll(super.listSearchableFields());

		return listSearchableFields;
	}

}
