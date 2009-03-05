package org.cerebrum.domain.action;

import org.cerebrum.domain.Item;
import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;

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
@Name("itemAction")
public class ItemAction extends BaseAction implements java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private Item item;

	@DataModel
	private List<Item> itemList;

	@Factory("itemList")
	public void findRecords() {
		itemList = entityManager.createQuery(
				"select item from Item item order by item.id").getResultList();
	}

	@Begin
	public String select(Item item) {
		this.item = entityManager.merge(item);
		log.info("User selected Item: #{item.displayName} #{item.id} ");
		return "select";
	}

	@End
	public String save() {
		facesMessages.add("Successfully saved  #{item.displayName}");
		entityManager.persist(item);
		return "save";
	}

	@End
	public String cancel() {
		return "cancel";
		//System.out.println("in cancel");
	}

}
