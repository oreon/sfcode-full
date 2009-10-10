package org.oscar.html.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.Tag;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.caisi.persistence.viewhelper.ViewHelper;
import org.oscar.servlets.ReflectionBasedPopulator;

/**
 * @author jsingh
 *
 */
public class ColumnTag extends AbstractOutputHtmlTag {
	
	private String label;
	private boolean sortable;
	private String linkTo;
	private String param;
	private boolean literal = false;

	public boolean isLiteral() {
		return literal;
	}

	public void setLiteral(boolean literal) {
		this.literal = literal;
	}

	public String getLinkTo() {
		return linkTo;
	}

	public void setLinkTo(String linkTo) {
		this.linkTo = linkTo;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public boolean isSortable() {
		return sortable;
	}

	public void setSortable(boolean sortable) {
		this.sortable = sortable;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	private TableTag parent;

	public int doStartTag() throws JspException {

		// Locate and save reference to parent.
		Tag t = getParent();
		if (t == null) {
			throw new JspException("TestBodyNest must be in TestBodyParent.");
		} else {
			parent = (TableTag) t;
			return EVAL_BODY_INCLUDE;
		}
	}

	public int doEndTag() throws JspException {
		try {
			Object bean = pageContext.getAttribute("currentElement");
			if (bean == null) {// set headers
				parent.getWriter().print("<th><a href='?sortBy='" + getName() + "'>"  + getLabelToRender() + "</a></th>");
				return EVAL_PAGE;
			}
			createCellContent(bean);
		} catch (IOException ioe) {
			throw new JspException(ioe.getMessage());
		} catch (Exception e) {
			throw new JspException(e.getMessage());
		}
		return EVAL_PAGE;
	}

	private void createCellContent(Object bean) throws IOException {
		String property = String.valueOf( ReflectionBasedPopulator.safeGetValue(bean, getName()) );
		parent.getWriter().print("<td>" +  getLinkToRenderBegin() +
				ViewHelper.getEmptyIfNull(property) +  getLinkToRenderEnd() + "</td>");
	}
	
	String getLabelToRender(){
		return label == null ?ViewHelper.convertCamelCaseToTitleCase(getName()): getLabel();
	}
	
	String getLinkToRenderBegin(){
		if(linkTo != null ){
			return "<a href=\"" + linkTo + "?" + getIdToRender() + "\">";
		}
		return StringUtils.EMPTY;
	}
	
	String getIdToRender(){
		Object bean = pageContext.getAttribute("currentElement");
		return param != null?(param + "=" + ReflectionBasedPopulator.safeGetValue(bean, param) ):StringUtils.EMPTY;
	}
	
	String getLinkToRenderEnd(){
		return linkTo != null? "</a>":StringUtils.EMPTY;
	}
	
	String getSortableRender(){
		return null;
	}

}
