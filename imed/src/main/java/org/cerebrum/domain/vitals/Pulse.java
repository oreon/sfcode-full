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

@Embeddable
@Indexed
public class Pulse {

	protected Integer BPM;

	protected Pattern pattern;

	protected Integer breathsPerMinute;

	protected PulseSite site;

	public void setBPM(Integer BPM) {
		this.BPM = BPM;
	}

	public Integer getBPM() {
		return BPM;
	}

	public void setPattern(Pattern pattern) {
		this.pattern = pattern;
	}

	public Pattern getPattern() {
		return pattern;
	}

	public void setBreathsPerMinute(Integer breathsPerMinute) {
		this.breathsPerMinute = breathsPerMinute;
	}

	public Integer getBreathsPerMinute() {
		return breathsPerMinute;
	}

	public void setSite(PulseSite site) {
		this.site = site;
	}

	public PulseSite getSite() {
		return site;
	}

}
