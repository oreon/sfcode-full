package ${package}.dao.geog.impl;

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

}
