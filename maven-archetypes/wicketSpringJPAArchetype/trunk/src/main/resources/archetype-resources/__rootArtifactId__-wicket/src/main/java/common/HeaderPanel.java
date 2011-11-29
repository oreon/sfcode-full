package ${package}.common;

import org.apache.wicket.markup.html.basic.Label;

import ${package}.BasePanel;

/**
 * This panel class is responsible for preparing data for header part of the site.
 *
 * @author Kamalpreet Singh
 *
 */
public class HeaderPanel extends BasePanel {

	/** Serialization version UID. */
	private static final long serialVersionUID = -2362154981039479600L;

	/**
	 * Creates a new instance of <code>HeaderPanel</code>.
	 *
     * @param headerPanelId
     */
	public HeaderPanel(String headerPanelId) {
		super(headerPanelId);

		add(new Label("header", "Header"));
	}
}
