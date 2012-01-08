package ${package};

import org.apache.wicket.Session;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;

import ${package}.error.ForbiddenErrorPage;
import ${package}.error.InternalServerErrorPage;
import ${package}.error.NotFoundErrorPage;
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
		
		initializeMarkupSettings();
		
		initializeWicketSpringIntegration();

		mountErrorPages();
	}
	
	private void initializeMarkupSettings() {
		// Strip Wicket tags ( <wicket: ..>) and wicket:id attributes from output.
		getMarkupSettings().setStripWicketTags(true);
		
		// Strip HTML comments during rendering.
		getMarkupSettings().setStripComments(true);
		
		// Compress multiple tabs/spaces to a single space.
		getMarkupSettings().setCompressWhitespace(true);
	}
	
	private void initializeWicketSpringIntegration() {
		getComponentInstantiationListeners().add(new SpringComponentInjector(this));
	}

	private void mountErrorPages() {
		mountPage("/error/403", ForbiddenErrorPage.class);
		mountPage("/error/404", NotFoundErrorPage.class);
		mountPage("/error/500", InternalServerErrorPage.class);
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
