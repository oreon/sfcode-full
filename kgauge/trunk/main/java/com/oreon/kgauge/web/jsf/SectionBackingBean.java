package com.oreon.kgauge.web.jsf;

import java.util.Iterator;

import javax.persistence.*;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.oreon.kgauge.domain.Question;
import javax.faces.model.*;
import javax.faces.event.*;

import org.apache.log4j.Logger;

import com.oreon.kgauge.domain.Question;

import javax.faces.event.ValueChangeEvent;
import javax.jws.WebService;

public class SectionBackingBean extends SectionBackingBeanBase {

	private static final Logger log = Logger
			.getLogger(SectionBackingBean.class);
	
	/**Method can be used on Value change event to change a questions text*/
	public void changeQuestion(ValueChangeEvent vce) {
		String oldV=(String)vce.getOldValue();
		String newV=(String)vce.getNewValue();
		Iterator <Question> qs=this.getSection().getQuestionIterator();
		while(qs.hasNext()){
			Question q=(Question)qs.next();
			String qText=q.getQuestionText();
			if(qText.equals(oldV)){
				q.setQuestionText(newV);			
			}
		}		
	}

}
