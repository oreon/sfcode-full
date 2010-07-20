package com.nas.recovery.web.action.realestate;

import com.nas.recovery.domain.realestate.FilesUploaded;

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

import com.nas.recovery.domain.realestate.FilesUploaded;

/**
 * @author WitchcraftMDA Seam Cartridge
 *
 */
public abstract class FilesUploadedListQueryBase
		extends
			BaseQuery<FilesUploaded, Long> {

	//private static final String EJBQL = "select filesUploaded from FilesUploaded filesUploaded";

	private FilesUploaded filesUploaded = new FilesUploaded();

	private static final String[] RESTRICTIONS = {
			"filesUploaded.id = #{filesUploadedList.filesUploaded.id}",

			"lower(filesUploaded.title) like concat(lower(#{filesUploadedList.filesUploaded.title}),'%')",

			"filesUploaded.file = #{filesUploadedList.filesUploaded.file}",

			"filesUploaded.realEstateProperty = #{filesUploadedList.filesUploaded.realEstateProperty}",

			"filesUploaded.dateCreated <= #{filesUploadedList.dateCreatedRange.end}",
			"filesUploaded.dateCreated >= #{filesUploadedList.dateCreatedRange.begin}",};

	public FilesUploaded getFilesUploaded() {
		return filesUploaded;
	}

	@Override
	public Class<FilesUploaded> getEntityClass() {
		return FilesUploaded.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		// TODO Auto-generated method stub
		return RESTRICTIONS;
	}

	@Observer("archivedFilesUploaded")
	public void onArchive() {
		refresh();
	}
}
