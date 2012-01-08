package ${package}.error;

import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 * Internal server error page (URL - "/error/500").
 *
 * @author Kamalpreet Singh
 *
 */
public class InternalServerErrorPage extends BaseErrorPage {

	/** Serialization version UID. */
	private static final long serialVersionUID = -2097995822667773751L;

	/**
     * Creates a new instance of <code>InternalServerErrorPage</code>.
	 *
	 * @param pageParameters
     */
	public InternalServerErrorPage(final PageParameters pageParameters) {
		super(pageParameters);
	}
	
	/**
	 * Returns 500 error code.
	 * 
	 * @return returns error code
	 */
	@Override
	public int getHttpStatusErrorCode() {
		return 500;
	}
}
