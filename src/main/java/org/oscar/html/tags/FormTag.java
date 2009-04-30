package org.oscar.html.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;

public class FormTag extends AbstractOutputHtmlTag{
	
	private String method = "post";
	private String action;
	
	public int doStartTag() throws JspException {

		try {
			//Dom
			getWriter().println("<form " + getRenderedProperty("method", getStyleClass()) 
					+ " " + getRenderedProperty("action", getStyleClass()) +
			">");
			return EVAL_BODY_INCLUDE;
		} catch (IOException ioe) {
			throw new JspException(ioe.getMessage());
		}
	}
	
	@Override
	public int doEndTag() throws JspException {
		try {
			//Dom
			getWriter().println("</form>"); 
			return EVAL_PAGE;
		} catch (IOException ioe) {
			throw new JspException(ioe.getMessage());
		}
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

}
