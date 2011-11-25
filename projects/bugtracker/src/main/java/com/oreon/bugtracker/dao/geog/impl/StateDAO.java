package com.oreon.bugtracker.dao.geog.impl;

import org.springframework.stereotype.Repository;

import com.oreon.bugtracker.dao.geog.IStateDAO;
import com.oreon.bugtracker.dao.impl.GenericDAO;
import com.oreon.bugtracker.domain.geog.State;

/**
 * @author Kamalpreet Singh
 * 
 */
@Repository("stateDAO")
public class StateDAO extends GenericDAO<State, Long> implements IStateDAO {

}
