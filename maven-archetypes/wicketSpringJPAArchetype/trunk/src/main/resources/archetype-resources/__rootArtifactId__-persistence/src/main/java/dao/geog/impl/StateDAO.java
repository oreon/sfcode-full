package ${package}.dao.geog.impl;

import org.springframework.stereotype.Repository;

import ${package}.dao.geog.IStateDAO;
import ${package}.dao.impl.GenericDAO;
import ${package}.domain.geog.State;

/**
 * @author Kamalpreet Singh
 * 
 */
@Repository("stateDAO")
public class StateDAO extends GenericDAO<State, Long> implements IStateDAO {

}
