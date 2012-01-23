package ${package}.dao.geog.impl;

import java.util.List;

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

	@Override
	public List<State> findAllActiveStates() {
		String queryString = "select state from State state where active = ?1";
		return executeQuery(queryString, true);
	}
}
