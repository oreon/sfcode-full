package ${package}.security;

import org.apache.wicket.Component;
import org.apache.wicket.Page;
import org.apache.wicket.authorization.Action;
import org.apache.wicket.authorization.IAuthorizationStrategy;
import org.apache.wicket.request.component.IRequestableComponent;

import ${package}.ProtectedPage;

/**
 * Authorization strategy.
 * 
 * @author Kamalpreet Singh
 *
 */
public class AuthorizationStrategy implements IAuthorizationStrategy {

	@Override
	public <T extends IRequestableComponent> boolean isInstantiationAuthorized(Class<T> componentClass) {
		// First, filter out all queries to non-page components, as we are only interested in intercepting instantiation of pages 
		// and not components such as labels and text fields.
		if (!Page.class.isAssignableFrom(componentClass)) {
			return true;
		}
		
		// Second, check if the page is protected i.e. requires authentication.
		// If the page requires authentication then check if the subject (i.e. user) is in session or not
		// and if the subject (i.e. user) is not in session return false.
		ProtectedPage protectedPage = componentClass.getAnnotation(ProtectedPage.class);
		if (protectedPage != null) {
			// The page is protected i.e. requires authentication.
			return false;
		}
		
        // Subject (i.e. user) is authorized to see this page.
		return true;
	}
	
	@Override
	public boolean isActionAuthorized(Component component, Action action) {
		// TODO - perform authorization check using roles or permission checks, just like above.
		return true;
	}
}
