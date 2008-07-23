package com.cc.civlit.dao;

import com.cc.civlit.domain.LitigationCase;
import org.witchcraft.model.support.dao.GenericDAO;
import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface LitigationCaseDao extends GenericDAO<LitigationCase> {

}
