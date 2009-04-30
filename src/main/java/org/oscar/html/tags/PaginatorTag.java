package org.oscar.html.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;

public class PaginatorTag extends AbstractOutputHtmlTag {

	private int totalRecords;
	private int recordsPerPage = 20;

	public int getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}

	public int getRecordsPerPage() {
		return recordsPerPage;
	}

	public void setRecordsPerPage(int recordsPerPage) {
		this.recordsPerPage = recordsPerPage;
	}

	@Override
	public int doEndTag() throws JspException {
		try {
			getWriter().println(
					"<div " + getRenderedProperty("style", getStyle()) + " >");
			int numPages = totalRecords / recordsPerPage
					- ((totalRecords % recordsPerPage == 0) ? 1 : 0);

			if (numPages > 1) {

				for (int i = 0; i <= numPages; i++) {
					getWriter().println(
							"<a href='?currentPage=" + (i + 1) + "'> "
									+ (i + 1) + "</a> ");
				}
			}

			getWriter().println("</div>");
		} catch (IOException e) {
			throw new JspException(e);
		}
		return super.doEndTag();
	}

}
