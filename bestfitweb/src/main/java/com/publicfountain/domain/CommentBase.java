package com.publicfountain.domain;

import javax.persistence.*;
import org.hibernate.annotations.Cascade;
import java.util.Date;

@MappedSuperclass
/*@Entity
@Table(name="Comment",uniqueConstraints={@UniqueConstraint(columnNames={})})*/
public abstract class CommentBase
		extends
			org.witchcraft.model.support.BusinessEntity
		implements
			java.io.Serializable {

	private static final long serialVersionUID = 1L;

	protected String text;

	protected String userDisplayName;

	@Column(nullable = false, unique = false)
	public String getText() {

		return this.text;
	}

	public String getUserDisplayName() {
		return this.userDisplayName;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setUserDisplayName(String userDisplayName) {
		this.userDisplayName = userDisplayName;
	}

	private bizobjects.RegisteredUser commentCreator;

	private com.publicfountain.domain.Topic topic;

	public void setCommentCreator(bizobjects.RegisteredUser commentCreator) {
		this.commentCreator = commentCreator;
	}

	@ManyToOne
	@JoinColumn(name = "commentCreator_ID", nullable = true)
	public bizobjects.RegisteredUser getCommentCreator() {
		return this.commentCreator;
	}

	public void setTopic(com.publicfountain.domain.Topic topic) {
		this.topic = topic;
	}

	@ManyToOne
	@JoinColumn(name = "topic_ID", nullable = false)
	public com.publicfountain.domain.Topic getTopic() {
		return this.topic;
	}

	public void newOperation() {
	}

	public abstract Comment commentInstance();

	@Transient
	public String getDisplayName() {
		return text + "";
	}

}
