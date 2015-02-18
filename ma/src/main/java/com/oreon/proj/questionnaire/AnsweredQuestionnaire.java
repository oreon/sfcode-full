package com.oreon.proj.questionnaire;

import javax.persistence.*;

import org.witchcraft.base.entity.FileAttachment;
import org.witchcraft.base.entity.BaseEntity;
import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Filters;
import org.hibernate.annotations.Formula;

import javax.validation.constraints.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

/**
 * This file is an Entity Class generated by Witchcraftmda.
 * DO NOT MODIFY any changes will be overwritten with the next run of the code generator.
 */

@Entity
@Table(name = "answeredquestionnaire")
@Filters({@Filter(name = "archiveFilterDef"), @Filter(name = "tenantFilterDef")})
public class AnsweredQuestionnaire extends AnsweredQuestionnaireBase
		implements
			java.io.Serializable {
	private static final long serialVersionUID = -1880888387L;

	
	@Formula(value="(select SUM(a.score) FROM answer a,  answeredquestion_answer aa , answeredquestion aq WHERE aa.answers_id = a.id AND aa.answeredquestion_id =  aq.id  AND  aq.answeredQuestionnaire_id = id )" )
	private Integer defaultScore;
	
	
	public Integer getDefaultScore() {
		return defaultScore;
	}
}