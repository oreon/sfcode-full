package com.hrb.tservices.dtos;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Date;
import javax.ws.rs.core.Response;

@javax.xml.bind.annotation.XmlRootElement
public class FaqList extends com.hrb.tservices.dtos.BaseDTO {

	private java.util.List<com.hrb.tservices.dtos.Faq> faqs = new ArrayList<com.hrb.tservices.dtos.Faq>();

	public void setFaqs(List<Faq> faqs) {
		this.faqs = faqs;
	}

	public List<Faq> getFaqs() {
		return faqs;
	}

}
