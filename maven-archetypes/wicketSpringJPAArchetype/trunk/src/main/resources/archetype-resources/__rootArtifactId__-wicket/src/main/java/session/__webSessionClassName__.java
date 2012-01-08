package ${package}.session;

import org.apache.wicket.request.Request;
import org.apache.wicket.Session;
import org.apache.wicket.protocol.http.WebSession;

/**
 * This class holds session related information. Each browser session gets its own instance of this class.
 *
 * @author Kamalpreet Singh
 *
 */
public class ${webSessionClassName} extends WebSession {

	/** Serialization version UID. */
	private static final long serialVersionUID = 2849000994827417011L;

	public ${webSessionClassName}(Request request) {
		super(request);
	}

	public static ${webSessionClassName} get() {
		return (${webSessionClassName}) Session.get();
	}
}
