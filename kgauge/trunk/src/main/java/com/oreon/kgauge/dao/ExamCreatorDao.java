package com.oreon.kgauge.dao;

import com.oreon.kgauge.domain.ExamCreator;
import org.witchcraft.model.support.dao.GenericDAO;
import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface ExamCreatorDao extends GenericDAO<ExamCreator> {

	public ExamCreator findByUsername(String username);

	public ExamCreator findByEmail(String email);

}
