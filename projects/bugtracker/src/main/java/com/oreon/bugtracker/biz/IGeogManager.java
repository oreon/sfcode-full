package com.oreon.bugtracker.biz;

import com.oreon.bugtracker.domain.geog.Country;
import com.oreon.bugtracker.domain.geog.State;

/**
 * @author Kamalpreet Singh
 *
 */
public interface IGeogManager {

	public abstract Country createCountry(Country country);

	public abstract State createState(State state);
}
