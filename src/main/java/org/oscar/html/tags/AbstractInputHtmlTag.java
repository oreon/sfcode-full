package org.oscar.html.tags;

import javax.servlet.jsp.tagext.TagSupport;

/**
 * This class will serve as base class for all html tags
 * @author jsingh
 *
 */
public abstract class AbstractInputHtmlTag extends TagSupport{

	private String name;
	private String value;
	private String styleClass;
	private String errorStyleClass;
	//if true fields will automatically be populated from their request parameter
	private boolean populateFromRequest = true;
	
	public boolean isPopulateFromRequest() {
		return populateFromRequest;
	}
	public void setPopulateFromRequest(boolean populateFromRequest) {
		this.populateFromRequest = populateFromRequest;
	}
	public String getStyleClass() {
		return styleClass;
	}
	public void setStyleClass(String styleClass) {
		this.styleClass = styleClass;
	}
	public String getErrorStyleClass() {
		return errorStyleClass;
	}
	public void setErrorStyleClass(String errorStyleClass) {
		this.errorStyleClass = errorStyleClass;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		/*
		ServletRequest request = pageContext.getRequest();
		System.out.println(name + " " + request.getParameter(name));
		if(StringUtils.isEmpty(value) && isPopulateFromRequest() && !StringUtils.isEmpty(request.getParameter(name)))
			value = (String) request.getParameter(name); */
		return value;
	}
	public void setValue(String value) {
		
		this.value = value;
	}
	
	public int doStartTag()  {
		try {
			pageContext.getOut().print(render());
		} catch (Exception ex) {
			ex.printStackTrace();
			//throw new JspTagException("SimpleTag: " + ex.getMessage());
		}
		return SKIP_BODY;
	}

	public int doEndTag() {
		return EVAL_PAGE;
	}
	
	protected abstract String render();
}
