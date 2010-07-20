package com.nas.recovery.web.action.appraisal;

import com.nas.recovery.domain.appraisal.Contact;

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

import com.nas.recovery.domain.appraisal.Contact;

/**
 * @author WitchcraftMDA Seam Cartridge
 *
 */
public abstract class ContactListQueryBase extends BaseQuery<Contact, Long> {

	//private static final String EJBQL = "select contact from Contact contact";

	private Contact contact = new Contact();

	private static final String[] RESTRICTIONS = {
			"contact.id = #{contactList.contact.id}",

			"contact.dateCreated <= #{contactList.dateCreatedRange.end}",
			"contact.dateCreated >= #{contactList.dateCreatedRange.begin}",};

	public Contact getContact() {
		return contact;
	}

	@Override
	public Class<Contact> getEntityClass() {
		return Contact.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		// TODO Auto-generated method stub
		return RESTRICTIONS;
	}

	@Observer("archivedContact")
	public void onArchive() {
		refresh();
	}
}
