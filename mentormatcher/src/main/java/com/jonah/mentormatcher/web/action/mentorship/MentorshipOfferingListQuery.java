package com.jonah.mentormatcher.web.action.mentorship;

import org.jboss.seam.annotations.Name;

@Name("mentorshipOfferingList")
// @Scope(ScopeType.CONVERSATION)
public class MentorshipOfferingListQuery extends
		MentorshipOfferingListQueryBase implements java.io.Serializable {

	@Override
	public String getMassagedSearchText(String text) {
		//text += "  AND ( NOT inactive:false ) ";
		return super.getMassagedSearchText(text);
	}
}
