package ${package}.security;

import org.apache.wicket.Component;
import org.apache.wicket.authorization.Action;
import org.apache.wicket.authorization.IAuthorizationStrategy;
import org.apache.wicket.request.component.IRequestableComponent;

import ${package}.ProtectedPage;

/**
 * @author Kamalpreet Singh
 *
 */
public class AuthorizationStrategy implements IAuthorizationStrategy {

	@Override
	public boolean isActionAuthorized(Component component, Action action) {
		// TODO - perform authorization check using roles or permission checks, just like above.
		return true;
	}

	@Override
	public <T extends IRequestableComponent> boolean isInstantiationAuthorized(Class<T> componentClass) {
		// you can check in any number of ways here, perhaps checking for roles or permissions:
        //boolean authorized = SecurityUtils.getSubject().hasRole("someRole");

        // or check based on permissions:
        //authorized = SecurityUtils.getSubject().isPermitted("someComponent:instantiate");

		// KAMAL
		ProtectedPage protectedPage = componentClass.getAnnotation(ProtectedPage.class);
		if (protectedPage != null) {
			// The page is protected, login required.
		}
		// KAMAL
		
		
        //return authorized;
		return true;
	}
}
