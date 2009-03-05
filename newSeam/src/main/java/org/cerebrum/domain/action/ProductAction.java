package org.cerebrum.domain.action;

import org.cerebrum.domain.Product;
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
@Name("productAction")
public class ProductAction extends BaseAction implements java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private Product product;

	@DataModel
	private List<Product> productList;

	@Factory("productList")
	public void findRecords() {
		productList = entityManager.createQuery(
				"select product from Product product order by product.id")
				.getResultList();
	}

	@Begin
	public String select(Product product) {
		this.product = entityManager.merge(product);
		log
				.info("User selected Product: #{product.displayName} #{product.id} ");
		return "select";
	}

	@End
	public String save() {
		facesMessages.add("Successfully saved  #{product.displayName}");
		entityManager.persist(product);
		return "save";
	}

	@End
	public String cancel() {
		return "cancel";
		//System.out.println("in cancel");
	}

}
