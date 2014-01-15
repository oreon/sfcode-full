package com.oreon.phonestore.web.action.commerce;

import javax.faces.model.DataModel;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import com.oreon.phonestore.domain.commerce.Product;

@Name("productList")
@Scope(ScopeType.CONVERSATION)
public class ProductListQuery extends ProductListQueryBase implements
		java.io.Serializable {
	
}
