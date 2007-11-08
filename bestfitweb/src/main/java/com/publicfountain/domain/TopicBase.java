
/**
 * This is generated code - to edit code or override methods use - Topic class
 * @author witchCraft Code Generator
 * WARNING  - DO NOT EDIT - CHANGES WILL BE OVERWRITTEN
 */

package com.publicfountain.domain;

import javax.persistence.*;
import org.hibernate.annotations.Cascade;
import java.util.Date;

@MappedSuperclass
/*@Entity
@Table(name="Topic",uniqueConstraints={@UniqueConstraint(columnNames={})})*/
public abstract class TopicBase
		extends
			org.witchcraft.model.support.BusinessEntity
		implements
			java.io.Serializable {

	private static final long serialVersionUID = 1L;

	protected String name;

	protected Date expiry;

	protected Status status;

	protected TopicType topicType;

	public String getName() {
		return this.name;
	}

	public Date getExpiry() {
		return this.expiry;
	}

	public Status getStatus() {
		return this.status;
	}

	public TopicType getTopicType() {
		return this.topicType;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setExpiry(Date expiry) {
		this.expiry = expiry;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public void setTopicType(TopicType topicType) {
		this.topicType = topicType;
	}

	private bizobjects.RegisteredUser topicCreator;

	private com.publicfountain.domain.Category category;

	public void setTopicCreator(bizobjects.RegisteredUser topicCreator) {
		this.topicCreator = topicCreator;
	}

	@ManyToOne
	@JoinColumn(name = "topicCreator_ID", nullable = false)
	public bizobjects.RegisteredUser getTopicCreator() {
		return this.topicCreator;
	}

	public void setCategory(com.publicfountain.domain.Category category) {
		this.category = category;
	}

	@ManyToOne
	@JoinColumn(name = "category_ID", nullable = false)
	public com.publicfountain.domain.Category getCategory() {
		return this.category;
	}

	public abstract Topic topicInstance();

	@Transient
	public String getDisplayName() {
		return name + "";
	}

}
