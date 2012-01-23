package ${package}.dao.geog.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import ${package}.dao.geog.ICountryDAO;
import ${package}.dao.impl.GenericDAO;
import ${package}.domain.geog.Country;

/**
 * @author Kamalpreet Singh
 *
 */
@Repository("countryDAO")
public class CountryDAO extends GenericDAO<Country, Long> implements ICountryDAO {

	@Override
	public List<Country> findAllActiveCountries() {
		String queryString = "select country from Country country where active = ?1";
		return executeQuery(queryString, true);
	}
}
