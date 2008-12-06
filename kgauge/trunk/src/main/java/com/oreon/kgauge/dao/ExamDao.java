package com.oreon.kgauge.dao;

import com.oreon.kgauge.domain.Exam;
import org.witchcraft.model.support.dao.GenericDAO;
import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface ExamDao extends GenericDAO<Exam> {

	public java.util.List findPopularExams(Integer minScore);

	public java.util.List findActiveExams();

}
