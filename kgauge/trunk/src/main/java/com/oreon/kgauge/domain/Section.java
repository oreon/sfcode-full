package com.oreon.kgauge.domain;

import java.util.Iterator;

import javax.faces.event.ActionEvent;
import javax.persistence.Entity;

import org.apache.log4j.Logger;

import com.oreon.kgauge.domain.Question;
import javax.faces.model.*;
import javax.faces.event.*;

@Entity
public class Section extends SectionBase {

	private static final Logger log = Logger.getLogger(Section.class);

	/* Default Constructor */
	public Section() {
	}

	/* Constructor with all attributes */
	public Section(String name) {
		super(name);
	}

	public Section sectionInstance() {
		return this;
	}
	
	/**Method can be used on Value change event to change a questions text*/
	public void changeQuestion(ValueChangeEvent vce) {
		String oldV=(String)vce.getOldValue();
		String newV=(String)vce.getNewValue();
		Iterator <Question> qs=super.getQuestionIterator();
		while(qs.hasNext()){
			Question q=(Question)qs.next();
			String qText=q.getQuestionText();
			if(qText.equals(oldV)){
				q.setQuestionText(newV);			
			}
		}		
	}
}
