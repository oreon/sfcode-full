
/**
 * This is generated code - to edit code or override methods use - Participation class
 * @author witchCraft Code Generator
 * WARNING  - DO NOT EDIT - CHANGES WILL BE OVERWRITTEN
 */

package com.oreon.olympics.domain;

import javax.persistence.*;
import org.hibernate.annotations.Cascade;
import java.util.Date;

@MappedSuperclass
/*@Entity
@Table(name="Participation",uniqueConstraints={@UniqueConstraint(columnNames={})})*/
public abstract class ParticipationBase
		extends
			org.witchcraft.model.support.BusinessEntity
		implements
			java.io.Serializable {

	private static final long serialVersionUID = 1L;

	protected Integer rank;

	public Integer getRank() {
		return this.rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	private com.oreon.olympics.domain.EventInstance eventInstance;

	private com.oreon.olympics.domain.Athlete particpant;

	public void setEventInstance(
			com.oreon.olympics.domain.EventInstance eventInstance) {
		this.eventInstance = eventInstance;
	}

	@ManyToOne
	@JoinColumn(name = "EventInstance_ID", nullable = true)
	public com.oreon.olympics.domain.EventInstance getEventInstance() {
		return this.eventInstance;
	}

	public void setParticpant(com.oreon.olympics.domain.Athlete particpant) {
		this.particpant = particpant;
	}

	@ManyToOne
	@JoinColumn(name = "particpant_ID", nullable = true)
	public com.oreon.olympics.domain.Athlete getParticpant() {
		return this.particpant;
	}

	public abstract Participation participationInstance();

}
