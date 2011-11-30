package ${package};

import org.apache.wicket.Session;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;

import ${package}.home.HomePage;
import ${package}.session.BaseSession;

/**
 * Base web application class.
 * 
 * @author Kamalpreet Singh
 *
 */
public class BaseWebApplication extends WebApplication {

	/**
     * Default constructor.
     */
	public BaseWebApplication() {

	}
	
	@Override
	protected void init() {
		super.init();
		
		// Strip Wicket tags (<wicket:..>) and wicket:id attributes from output.
		getMarkupSettings().setStripWicketTags(true);
		
		// Strip HTML comments during rendering.
		getMarkupSettings().setStripComments(true);
		
		// Compress multiple tabs/spaces to a single space.
		getMarkupSettings().setCompressWhitespace(true);
		
		getComponentInstantiationListeners().add(new SpringComponentInjector(this));
	}
	
	@Override
	public Session newSession(Request request, Response response) {
		return new BaseSession(request);
	}

	@Override
	public Class<? extends WebPage> getHomePage() {
		return HomePage.class;
	}
}
