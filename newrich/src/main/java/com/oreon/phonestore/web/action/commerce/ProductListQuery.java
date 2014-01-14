package com.oreon.phonestore.web.action.commerce;

import javax.faces.model.DataModel;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.witchcraft.base.entity.EntityQueryDataModel;

import com.oreon.phonestore.domain.commerce.Product;

@Name("productList")
@Scope(ScopeType.CONVERSATION)
public class ProductListQuery extends ProductListQueryBase implements
		java.io.Serializable {
	
	ProductDataModel productDataModel;

	private static final class ProductDataModel extends
			EntityQueryDataModel<Product, Long> {

		public ProductDataModel(ProductListQuery productListQuery) {
			super(productListQuery, Product.class);
		}

		@Override
		protected Long getId(Product item) {
			// TODO Auto-generated method stub
			return item.getId();
		}
	}
	
	@Override
    public DataModel getDataModel() {
		
        if (productDataModel == null) {
        	productDataModel = new ProductDataModel(this);
        }
        return productDataModel;
    }

}
