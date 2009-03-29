package org.witchcraft.base.entity;

import java.util.Set;
import java.util.HashSet;
import java.util.Date;

import javax.persistence.*;
import org.hibernate.validator.*;

import org.jboss.seam.annotations.Name;
import org.witchcraft.base.entity.BusinessEntity;

@Entity
@Table(name = "comment")
@Name("entitycomment")
@NamedQuery(name="commentsForRecord", query= "Select c from EntityComment c where c.entityId = ?1 and c.entityName = ?2 ")
public class EntityComment extends BusinessEntity {

	@NotNull
	private String entityName;
	
	@NotNull
	private Long entityId;

	@Lob
	protected String text;

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public Long getEntityId() {
		return entityId;
	}

	public void setEntityId(Long entityId) {
		this.entityId = entityId;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	@Transient
	public String getDisplayName() {
		return text + "";
	}

}
