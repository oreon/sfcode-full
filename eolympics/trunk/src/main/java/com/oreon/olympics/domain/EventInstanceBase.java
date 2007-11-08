
/**
 * This is generated code - to edit code or override methods use - EventInstance class
 * @author witchCraft Code Generator
 * WARNING  - DO NOT EDIT - CHANGES WILL BE OVERWRITTEN
 */

package com.oreon.olympics.domain;

import javax.persistence.*;
import org.hibernate.annotations.Cascade;
import java.util.Date;

@MappedSuperclass
/*@Entity
@Table(name="EventInstance",uniqueConstraints={@UniqueConstraint(columnNames={})})*/
public abstract class EventInstanceBase
		extends
			org.witchcraft.model.support.BusinessEntity
		implements
			java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private com.oreon.olympics.domain.Event event;

	private com.oreon.olympics.domain.Tournament tournament;

	public void setEvent(com.oreon.olympics.domain.Event event) {
		this.event = event;
	}

	@ManyToOne
	@JoinColumn(name = "event_ID", nullable = false)
	public com.oreon.olympics.domain.Event getEvent() {
		return this.event;
	}

	public void setTournament(com.oreon.olympics.domain.Tournament tournament) {
		this.tournament = tournament;
	}

	@ManyToOne
	@JoinColumn(name = "Tournament_ID", nullable = true)
	public com.oreon.olympics.domain.Tournament getTournament() {
		return this.tournament;
	}

	public abstract EventInstance eventInstanceInstance();

}
