package com.cc.civlit.domain;

import javax.persistence.*;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.apache.log4j.Logger;
import javax.jws.WebService;

import java.util.Date;

@Entity
public class LitigationCase extends LitigationCaseBase
		implements
			java.io.Serializable {

	private static final Logger log = Logger.getLogger(LitigationCase.class);
	private static final long serialVersionUID = 1L;

	/* Default Constructor */
	public LitigationCase() {
	}

	/* Constructor with all attributes */
	public LitigationCase(String name, String accountName,
			String courtFileNumber, String styleOfCase,
			ProceedingType proceedingType, CaseType caseType) {
		super(name, accountName, courtFileNumber, styleOfCase, proceedingType,
				caseType);
	}

	public LitigationCase litigationCaseInstance() {
		return this;
	}

}
