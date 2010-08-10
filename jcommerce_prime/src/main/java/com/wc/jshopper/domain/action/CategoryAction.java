package com.wc.jshopper.domain.action;

import java.util.List;

import org.jboss.seam.annotations.Name;
import org.richfaces.component.html.HtmlTree;
import org.richfaces.event.NodeSelectedEvent;
import org.witchcraft.base.entity.BusinessEntity;

import com.wc.jshopper.domain.Category;
import com.wc.jshopper.domain.Product;

//@Scope(ScopeType.CONVERSATION)
@Name("categoryAction")
public class CategoryAction extends CategoryActionBase implements
		java.io.Serializable {
	
	private Long productId;

	public List<Category> getTopLevelCategories() {
		return executeQuery("select c from Category c where c.parent is null");
	}

	public void processSelection(NodeSelectedEvent event) {

		HtmlTree tree = (HtmlTree) event.getComponent();
		

		BusinessEntity be = (BusinessEntity) tree.getRowData();
		if(be instanceof Product){
			
			productId = be.getId();
			
		}
		
		

	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Long getProductId() {
		return productId;
	}
}
