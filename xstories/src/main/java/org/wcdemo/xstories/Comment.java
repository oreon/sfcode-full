package org.wcdemo.xstories;

import java.util.Set;
import java.util.HashSet;
import java.util.Date;

import javax.persistence.*;
import org.hibernate.validator.*;

import org.jboss.seam.annotations.Name;
import org.witchcraft.base.entity.BusinessEntity;

@Entity
@Table(name = "comment")
@Name("comment")
public class Comment extends BusinessEntity {

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "story_id", nullable = false)
	protected Story story;

	@Lob
	protected String text;

	public void setStory(Story story) {
		this.story = story;
	}

	public Story getStory() {
		return story;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	@Transient
	public String getDisplayName() {
		return story + "";
	}

}
