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
public class BaseSession extends WebSession {

	/** Serialization version UID. */
	private static final long serialVersionUID = 2849000994827417011L;

	public BaseSession(Request request) {
		super(request);
	}

	public static BaseSession get() {
		return (BaseSession) Session.get();
	}
}
