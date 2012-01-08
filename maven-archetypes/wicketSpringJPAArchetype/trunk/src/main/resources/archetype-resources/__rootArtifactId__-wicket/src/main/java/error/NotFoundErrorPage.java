package ${package}.error;

import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 * Not found error page (URL - "/error/404").
 *
 * @author Kamalpreet Singh
 *
 */
public class NotFoundErrorPage extends BaseErrorPage {

	/** Serialization version UID. */
	private static final long serialVersionUID = -4406040399451941401L;

	/**
     * Creates a new instance of <code>NotFoundErrorPage</code>.
	 *
	 * @param pageParameters
     */
	public NotFoundErrorPage(final PageParameters pageParameters) {
		super(pageParameters);
	}
	
	/**
	 * Returns 404 error code.
	 * 
	 * @return returns error code
	 */
	@Override
	public int getHttpStatusErrorCode() {
		return 404;
	}
}
