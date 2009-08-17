package com.shan.customermgt.domain.action;

import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Observer;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.witchcraft.seam.action.BaseAction;

import com.shan.customermgt.domain.Product;

@Scope(ScopeType.CONVERSATION)
@Name("productAction")
public class ProductAction extends BaseAction<Product>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private Product product;

	@DataModel
	private List<Product> productList;

	@Factory("productList2")
	@Observer("archivedProduct")
	public void findRecords() {
		search();
	}

	public Product getEntity() {
		return product;
	}

	@Override
	public void setEntity(Product t) {
		this.product = t;
	}

	@Override
	public void setEntityList(List<Product> list) {
		this.productList = list;
	}

	public void updateAssociations() {

	}

	public List<Product> getEntityList() {
		if (productList == null) {
			findRecords();
		}
		return productList;
	}

}
