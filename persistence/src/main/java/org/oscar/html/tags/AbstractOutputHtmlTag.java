package org.oscar.html.tags;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.commons.lang.StringUtils;

public class AbstractOutputHtmlTag extends BodyTagSupport {

	private String name;
	private String id;
	private String styleClass;
	private String style;
	private String width;
	private String height;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	protected JspWriter getWriter() {
		BodyContent bodycontent = getBodyContent();
		if(bodyContent == null)
			return pageContext.getOut();
		JspWriter out = bodycontent.getEnclosingWriter();
		return out;
	}
	
	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getStyleClass() {
		return styleClass;
	}

	public void setStyleClass(String styleClass) {
		this.styleClass = styleClass;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}
	
	protected String getRenderedProperty(String propName, String propValue) {
		return getStyleClass() == null ? StringUtils.EMPTY: propName+ "=\"" + propValue + "\"";
	}
}
