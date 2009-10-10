package org.oscar.html.tags;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

public class TableTag extends AbstractOutputHtmlTag {
	private List list;
	private int currIndex;
	private Object currentItem;
	

	private String oddRowClass = "odd";
	private String evenRowClass = "even";
	
	
	

	StringBuilder builder = new StringBuilder();

	public Object getCurrentItem() {
		return currentItem;
	}

	public void setCurrentItem(Object currentItem) {
		this.currentItem = currentItem;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public int doStartTag() throws JspException {

		try {
			if (list.isEmpty()) {
				return SKIP_BODY;
			}
			pageContext.getOut().print("<table " + 
					getRenderedProperty("class", getStyleClass()) +  
					getRenderedProperty("width", getWidth())
					+  " > ");
			currIndex = 0;
			return EVAL_BODY_BUFFERED;
		} catch (IOException ioe) {
			throw new JspException(ioe.getMessage());
		}
	}

	

	@Override
	public void doInitBody() throws JspException {
		try {
			getWriter().println("<tr>");
		} catch (IOException e) {
			throw new JspException(e.getMessage());
		}

	}

	public String getOddRowClass() {
		return oddRowClass;
	}

	public void setOddRowClass(String oddRowClass) {
		this.oddRowClass = oddRowClass;
	}

	public String getEvenRowClass() {
		return evenRowClass;
	}

	public void setEvenRowClass(String evenRowClass) {
		this.evenRowClass = evenRowClass;
	}

	public int doAfterBody() throws JspException {
		try {
			if (currIndex < list.size()) {
				//if (currIndex > 0 )
					getWriter().print("</tr>");
					getWriter().println("<tr " + getRenderedRowClass()
						+">");
				pageContext.setAttribute("currentElement", list
						.get(currIndex++));
				return EVAL_BODY_BUFFERED; // Loop
			} else {
				return SKIP_BODY;
			}
		} catch (IOException ioe) {
			throw new JspException(ioe.getMessage());
		}
	}
	
	String getRenderedRowClass(){
		return " class=\""+ (currIndex%2 == 0?getEvenRowClass():getOddRowClass()) + "\" ";
	}

	@Override
	public void setBodyContent(BodyContent b) {
		System.out.println("set body content called");
		super.setBodyContent(b);
	}

	public int doEndTag() throws JspException {

		try {
			getWriter().print("</tr></table>");
			return EVAL_PAGE;
		} catch (IOException ioe) {
			throw new JspException(ioe.getMessage());
		}
	}

	public void setNestedName(String name) throws JspException {

		
	}

	@Override
	public void release() {
		currIndex = 0;
	}

	

}
