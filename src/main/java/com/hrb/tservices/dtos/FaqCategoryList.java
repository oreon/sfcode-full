package com.hrb.tservices.dtos;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Date;
import javax.ws.rs.core.Response;

@javax.xml.bind.annotation.XmlRootElement
public class FaqCategoryList extends com.hrb.tservices.dtos.BaseDTO {

	private java.util.List<com.hrb.tservices.dtos.FaqCategory> faqCategorys = new ArrayList<com.hrb.tservices.dtos.FaqCategory>();

	public void setFaqCategorys(List<FaqCategory> faqCategorys) {
		this.faqCategorys = faqCategorys;
	}

	public List<FaqCategory> getFaqCategorys() {
		return faqCategorys;
	}

}
