package ${package}.dao.geog;

import java.util.List;

import ${package}.dao.IGenericDAO;
import ${package}.domain.geog.Country;

/**
 * @author Kamalpreet Singh
 * 
 */
public interface ICountryDAO extends IGenericDAO<Country, Long> {

	List<Country> findAllActiveCountries();
}
