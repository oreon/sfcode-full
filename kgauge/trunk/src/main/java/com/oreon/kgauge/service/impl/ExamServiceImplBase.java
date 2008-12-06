
/**
 * This is generated code - to edit code or override methods use - Exam class
 * @author witchCraft Code Generator
 * WARNING  - DO NOT EDIT - CHANGES WILL BE OVERWRITTEN
 */

package com.oreon.kgauge.service.impl;

import com.oreon.kgauge.domain.Exam;
import com.oreon.kgauge.service.ExamService;
import com.oreon.kgauge.dao.ExamDao;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;

import org.acegisecurity.Authentication;
import org.acegisecurity.context.SecurityContextHolder;
import org.acegisecurity.userdetails.UserDetails;

import org.apache.log4j.Logger;

import org.witchcraft.model.support.dao.GenericDAO;
import org.witchcraft.model.support.errorhandling.BusinessException;
import org.witchcraft.model.support.service.BaseServiceImpl;

import javax.jws.WebService;

import org.witchcraft.model.support.Range;

@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
public class ExamServiceImplBase extends BaseServiceImpl<Exam>
		implements
			ExamService {

	private static final Logger log = Logger
			.getLogger(ExamServiceImplBase.class);

	private ExamDao examDao;

	public void setExamDao(ExamDao examDao) {
		this.examDao = examDao;
	}

	@Override
	public GenericDAO<Exam> getDao() {
		return examDao;
	}

	//// Delegate all crud operations to the Dao ////

	public Exam save(Exam exam) {
		Long id = exam.getId();

		examDao.save(exam);

		return exam;
	}

	public void delete(Exam exam) {
		examDao.delete(exam);
	}

	public Exam load(Long id) {
		return examDao.load(id);
	}

	public List<Exam> loadAll() {
		return examDao.loadAll();
	}

	public List<Exam> searchByExample(Exam exam) {
		return examDao.searchByExample(exam);
	}

	public List<Exam> searchByExample(Exam exam, List<Range> rangeObjects) {
		return examDao.searchByExample(exam, rangeObjects);
	}

	/** This method should be overridden by classes that want to filter the load all behavior e.g.
	 * showing 
	 * @return
	 */
	public Exam getFilterRecord() {
		return null;
	}

	/**
	 * text=SELECT e FROM Exam e WHERE e.questions > ?1 ORDER  BY e.questions
	 */

	public java.util.List findPopularExams(Integer minScore) {
		return examDao.findPopularExams(minScore);
	}

	public java.util.List findActiveExams() {
		return examDao.findActiveExams();
	}

}
