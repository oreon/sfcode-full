
/**
 * This is generated code - to edit code or override methods use - Athlete class
 * @author witchCraft Code Generator
 * WARNING  - DO NOT EDIT - CHANGES WILL BE OVERWRITTEN
 */

package com.oreon.olympics.domain;

import javax.persistence.*;
import org.hibernate.annotations.Cascade;
import java.util.Date;

@MappedSuperclass
/*@Entity
@Table(name="Athlete",uniqueConstraints={@UniqueConstraint(columnNames={})})*/
public abstract class AthleteBase extends Person
		implements
			java.io.Serializable,
			Participant {

	private static final long serialVersionUID = 1L;

	//Implementing interface Participant
	//*****Done Implementing interface Participant ****

	public abstract Athlete athleteInstance();

}
