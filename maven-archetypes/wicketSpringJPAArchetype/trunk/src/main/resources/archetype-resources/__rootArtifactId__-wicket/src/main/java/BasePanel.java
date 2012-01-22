package ${package};

import org.apache.wicket.markup.html.panel.Panel;

import ${package}.session.${webSessionClassName};

/**
 * Abstract base panel class for all panels.
 * 
 * @author Kamalpreet Singh
 *
 */
public abstract class BasePanel extends Panel {

	/** Serialization version UID. */
	private static final long serialVersionUID = -2055818756997607933L;

	/**
	 * Creates a new instance of <code>BasePanel</code>.
	 * 
	 * @param panelId
	 */
	public BasePanel(String panelId) {
		super(panelId);
	}

	public ${webSessionClassName} get${webSessionClassName}() {
		return (${webSessionClassName}) getSession();
	}
}
