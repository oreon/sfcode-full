package ${package};

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import ${package}.common.FooterPanel;
import ${package}.common.HeaderPanel;
import ${package}.session.BaseSession;

/**
 * Abstract base web page class for all web pages common elements.
 * 
 * @author Kamalpreet Singh
 *
 */
public abstract class BaseWebPage extends WebPage {

	/**
     * Creates a new instance of <code>BaseWebPage</code>.
	 *
	 * @param pageParameters
     */
	public BaseWebPage(final PageParameters pageParameters) {
		super(pageParameters);

		add(new HeaderPanel("headerPanel"));
		add(new FooterPanel("footerPanel"));
	}

	public BaseSession getBaseSession() {
		return (BaseSession) getSession();
	}
}
