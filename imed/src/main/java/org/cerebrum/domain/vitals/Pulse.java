package org.cerebrum.domain.vitals;

import java.util.Set;
import java.util.HashSet;
import java.util.Date;

import javax.persistence.*;
import org.hibernate.validator.*;

import org.jboss.seam.annotations.Name;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

@Embeddable
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
