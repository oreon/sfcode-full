package org.cerebrum.domain.provider;

import java.util.Set;
import java.util.HashSet;
import java.util.Date;

import javax.persistence.*;
import org.hibernate.validator.*;

import org.jboss.seam.annotations.Name;
import org.witchcraft.base.entity.*;

@Entity
@Table(name = "specialization")
@Name("specialization")
public class Specialization extends BusinessEntity {

}
