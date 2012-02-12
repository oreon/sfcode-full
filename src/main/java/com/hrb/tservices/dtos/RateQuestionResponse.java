package com.hrb.tservices.dtos;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Date;
import javax.ws.rs.core.Response;

@javax.xml.bind.annotation.XmlRootElement
public class RateQuestionResponse extends com.hrb.tservices.dtos.BaseDTO {

	private Faq faq;

	public void setFaq(Faq faq) {
		this.faq = faq;
	}

	public Faq getFaq() {
		return faq;
	}

}
