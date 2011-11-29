package ${package}.error;

import org.apache.wicket.request.mapper.parameter.PageParameters;

import ${package}.BaseWebPage;

/**
 * Abstract base error page class for all error web pages.
 *
 * @author Kamalpreet Singh
 *
 */
public abstract class BaseErrorPage extends BaseWebPage {

	/**
     * Creates a new instance of <code>BaseErrorPage</code>.
	 *
	 * @param pageParameters
     */
	public BaseErrorPage(final PageParameters pageParameters) {
		super(pageParameters);
	}
}
