package com.oreon.proj.onepack;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Filters;

/**
 * This file is an Entity Class generated by Witchcraftmda.
 * DO NOT MODIFY any changes will be overwritten with the next run of the code generator.
 */

@Entity
@Table(name = "customerorder")
@Filters({@Filter(name = "archiveFilterDef"), @Filter(name = "tenantFilterDef")})

public class CustomerOrder extends CustomerOrderBase
		implements
			java.io.Serializable {
	private static final long serialVersionUID = 938468003L;
}
