package com.oreon.bugtracker.dao.geog.impl;

import org.springframework.stereotype.Repository;

import com.oreon.bugtracker.dao.geog.ICountryDAO;
import com.oreon.bugtracker.dao.impl.GenericDAO;
import com.oreon.bugtracker.domain.geog.Country;

/**
 * @author Kamalpreet Singh
 *
 */
@Repository("countryDAO")
public class CountryDAO extends GenericDAO<Country, Long> implements ICountryDAO {

}
