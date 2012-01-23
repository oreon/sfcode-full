package ${package}.dao.geog;

import java.util.List;

import ${package}.dao.IGenericDAO;
import ${package}.domain.geog.State;

/**
 * @author Kamalpreet Singh
 * 
 */
public interface IStateDAO extends IGenericDAO<State, Long> {

	List<State> findAllActiveStates();
}
