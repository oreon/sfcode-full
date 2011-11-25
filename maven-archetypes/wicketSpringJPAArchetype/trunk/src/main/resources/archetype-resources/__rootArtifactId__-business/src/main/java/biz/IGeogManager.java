package ${package}.biz;

import ${package}.domain.geog.Country;
import ${package}.domain.geog.State;

/**
 * @author Kamalpreet Singh
 *
 */
public interface IGeogManager {

	public abstract Country createCountry(Country country);

	public abstract State createState(State state);
}
