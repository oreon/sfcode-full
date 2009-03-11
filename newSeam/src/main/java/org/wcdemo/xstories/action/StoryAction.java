package org.wcdemo.xstories.action;

import org.wcdemo.xstories.Story;
import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import org.apache.commons.lang.StringUtils;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.log.Log;

import org.witchcraft.seam.action.BaseAction;

@Scope(ScopeType.CONVERSATION)
@Name("storyAction")
public class StoryAction extends BaseAction<Story>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private Story story;

	@DataModel
	private List<Story> storyList;

	@Factory("storyList")
	public void findRecords() {
		storyList = entityManager.createQuery(
				"select story from Story story order by story.id")
				.getResultList();
	}

	public Story getEntity() {
		return story;
	}

	@Override
	public void setEntity(Story t) {
		this.story = t;
	}

	@Override
	public void setEntityList(List<Story> list) {
		this.storyList = list;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	public void addAssoications(Criteria criteria) {

		if (story.getAssignee() != null) {
			criteria = criteria.add(Restrictions.eq("assignee.id", story
					.getAssignee().getId()));
		}

	}

}
