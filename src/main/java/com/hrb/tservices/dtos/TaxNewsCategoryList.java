package com.hrb.tservices.dtos;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Date;
import javax.ws.rs.core.Response;

@javax.xml.bind.annotation.XmlRootElement
public class TaxNewsCategoryList extends com.hrb.tservices.dtos.BaseDTO {

	private java.util.List<com.hrb.tservices.dtos.TaxNewsCategory> taxNewsCategorys = new ArrayList<com.hrb.tservices.dtos.TaxNewsCategory>();

	public void setTaxNewsCategorys(List<TaxNewsCategory> taxNewsCategorys) {
		this.taxNewsCategorys = taxNewsCategorys;
	}

	public List<TaxNewsCategory> getTaxNewsCategorys() {
		return taxNewsCategorys;
	}

}
