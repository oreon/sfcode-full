package ${package}.error;

import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 * Forbidden error page (URL - "/error/403").
 *
 * @author Kamalpreet Singh
 *
 */
public class ForbiddenErrorPage extends BaseErrorPage {

	/** Serialization version UID. */
	private static final long serialVersionUID = 7515141223477114746L;

	/**
     * Creates a new instance of <code>ForbiddenErrorPage</code>.
	 *
	 * @param pageParameters
     */
	public ForbiddenErrorPage(final PageParameters pageParameters) {
		super(pageParameters);
	}
	
	/**
	 * Returns 403 error code.
	 * 
	 * @return returns error code
	 */
	@Override
	public int getHttpStatusErrorCode() {
		return 403;
	}
}
