package ${package}.common;

import org.apache.wicket.markup.html.basic.Label;

import ${package}.BasePanel;

/**
 * This panel class is responsible for preparing data for footer part of the site.
 *
 * @author Kamalpreet Singh
 *
 */
public class FooterPanel extends BasePanel {

	/** Serialization version UID. */
	private static final long serialVersionUID = 7253464469594177022L;

	/**
     * Creates a new instance of <code>FooterPanel</code>.
	 *
	 * @param footerPanelId
     */
	public FooterPanel(String footerPanelId) {
		super(footerPanelId);

		add(new Label("footer", "Footer"));
	}
}
