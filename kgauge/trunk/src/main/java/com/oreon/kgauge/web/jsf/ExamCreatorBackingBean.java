package com.oreon.kgauge.web.jsf;

import javax.persistence.*;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.apache.log4j.Logger;
import javax.jws.WebService;

import java.util.Date;

public class ExamCreatorBackingBean extends ExamCreatorBackingBeanBase
		implements
			java.io.Serializable {

	private static final Logger log = Logger
			.getLogger(ExamCreatorBackingBean.class);
	private static final long serialVersionUID = 1L;

}
