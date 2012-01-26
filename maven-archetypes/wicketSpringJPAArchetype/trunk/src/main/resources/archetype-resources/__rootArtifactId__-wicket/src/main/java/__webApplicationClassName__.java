package ${package};

import org.apache.wicket.Session;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ${package}.error.ForbiddenErrorPage;
import ${package}.error.InternalServerErrorPage;
import ${package}.error.NotFoundErrorPage;
import ${package}.home.HomePage;
import ${package}.security.AuthorizationStrategy;
import ${package}.session.${webSessionClassName};

/**
 * Web application class.
 * 
 * @author Kamalpreet Singh
 *
 */
public class ${webApplicationClassName} extends WebApplication {

	private final Logger logger = LoggerFactory.getLogger(${webApplicationClassName}.class);
	
	/**
     * Default constructor.
     */
	public ${webApplicationClassName}() {

	}
	
	@Override
	protected void init() {
		logger.info("Start of web application initialization settings.");
		
		super.init();
		
		initializeMarkupSettings();
		
		initializeWicketSpringIntegration();

		initializeErrorPageSettings();
		
		initializeSecuritySettings();
		
		logger.info("End of web application initialization settings");
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
	
	private void initializeErrorPageSettings() {
		//getApplicationSettings().setAccessDeniedPage(accessDeniedPage);
		//getApplicationSettings().setPageExpiredErrorPage(pageExpiredErrorPage);
		//getApplicationSettings().setInternalErrorPage(internalErrorPage);
		
		mountErrorPages();
	}
	
	private void mountErrorPages() {
		mountPage("/error/403", ForbiddenErrorPage.class);
		mountPage("/error/404", NotFoundErrorPage.class);
		mountPage("/error/500", InternalServerErrorPage.class);
	}
	
	private void initializeSecuritySettings() {
		getSecuritySettings().setAuthorizationStrategy(new AuthorizationStrategy());
	}
	
	@Override
	public Session newSession(Request request, Response response) {
		return new ${webSessionClassName}(request);
	}

	@Override
	public Class<? extends WebPage> getHomePage() {
		return HomePage.class;
	}
}
