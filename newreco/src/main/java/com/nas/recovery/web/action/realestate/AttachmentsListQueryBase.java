package com.nas.recovery.web.action.realestate;

import com.nas.recovery.domain.realestate.Attachments;

import org.witchcraft.seam.action.BaseAction;

import java.util.Arrays;
import java.util.Date;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.framework.EntityQuery;
import org.witchcraft.base.entity.BaseQuery;
import org.witchcraft.base.entity.Range;

import org.jboss.seam.annotations.Observer;

import com.nas.recovery.domain.realestate.Attachments;

/**
 * @author WitchcraftMDA Seam Cartridge
 *
 */
public abstract class AttachmentsListQueryBase
		extends
			BaseQuery<Attachments, Long> {

	//private static final String EJBQL = "select attachments from Attachments attachments";

	private Attachments attachments = new Attachments();

	private static final String[] RESTRICTIONS = {
			"attachments.id = #{attachmentsList.attachments.id}",

			"lower(attachments.title) like concat(lower(#{attachmentsList.attachments.title}),'%')",

			"attachments.file = #{attachmentsList.attachments.file}",

			"attachments.dateCreated <= #{attachmentsList.dateCreatedRange.end}",
			"attachments.dateCreated >= #{attachmentsList.dateCreatedRange.begin}",};

	public Attachments getAttachments() {
		return attachments;
	}

	@Override
	public Class<Attachments> getEntityClass() {
		return Attachments.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		// TODO Auto-generated method stub
		return RESTRICTIONS;
	}

	@Observer("archivedAttachments")
	public void onArchive() {
		refresh();
	}
}
