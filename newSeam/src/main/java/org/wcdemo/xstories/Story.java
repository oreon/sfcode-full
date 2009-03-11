package org.wcdemo.xstories;

import java.util.Set;
import java.util.HashSet;
import java.util.Date;

import javax.persistence.*;
import org.hibernate.validator.*;

import org.jboss.seam.annotations.Name;
import org.witchcraft.base.entity.BusinessEntity;

@Entity
@Table(name = "Story")
@Name("story")
public class Story extends BusinessEntity {

	protected String title;

	@Lob
	protected String description;

	@ManyToOne(optional = true, fetch = FetchType.LAZY)
	@JoinColumn(name = "assignee_id", nullable = true)
	protected TeamMember assignee;

	protected Resoultion resoultion = Resoultion.NONE;

	protected Status status = Status.OPEN;

	protected Priority priority = Priority.URGENT;

	protected Date estimatedEndDate;

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setAssignee(TeamMember assignee) {
		this.assignee = assignee;
	}

	public TeamMember getAssignee() {
		return assignee;
	}

	public void setResoultion(Resoultion resoultion) {
		this.resoultion = resoultion;
	}

	public Resoultion getResoultion() {
		return resoultion;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Status getStatus() {
		return status;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	public Priority getPriority() {
		return priority;
	}

	public void setEstimatedEndDate(Date estimatedEndDate) {
		this.estimatedEndDate = estimatedEndDate;
	}

	public Date getEstimatedEndDate() {
		return estimatedEndDate;
	}

	@Transient
	public String getDisplayName() {
		return title + "";
	}

}
