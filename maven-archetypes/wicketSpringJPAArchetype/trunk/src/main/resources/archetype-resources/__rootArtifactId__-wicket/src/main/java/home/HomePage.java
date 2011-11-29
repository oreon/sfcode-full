package ${package}.home;

import org.apache.wicket.request.mapper.parameter.PageParameters;

import ${package}.BaseWebPage;

/**
 * Home page.
 *
 * @author Kamalpreet Singh
 *
 */
public class HomePage extends BaseWebPage {

	/** Serialization version UID. */
	private static final long serialVersionUID = 3078420415848870389L;

	/**
     * Creates a new instance of <code>HomePage</code>.
	 *
	 * @param pageParameters
     */
	public HomePage(final PageParameters pageParameters) {
		super(pageParameters);
	}
}
