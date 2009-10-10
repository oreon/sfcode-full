<%--  
/*
 * 
 * Copyright (c) 2001-2002. Department of Family Medicine, McMaster University. All Rights Reserved. *
 * This software is published under the GPL GNU General Public License. 
 * This program is free software; you can redistribute it and/or 
 * modify it under the terms of the GNU General Public License 
 * as published by the Free Software Foundation; either version 2 
 * of the License, or (at your option) any later version. * 
 * This program is distributed in the hope that it will be useful, 
 * but WITHOUT ANY WARRANTY; without even the implied warranty of 
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the 
 * GNU General Public License for more details. * * You should have received a copy of the GNU General Public License 
 * along with this program; if not, write to the Free Software 
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA 02111-1307, USA. * 
 * 
 * <OSCAR TEAM>
 * 
 * This software was written for the 
 * Department of Family Medicine 
 * McMaster Unviersity 
 * Hamilton 
 * Ontario, Canada 
 */
--%>

<%@ page import="java.lang.*"%>

<%
        boolean fromMessenger = request.getParameter("fromMessenger") == null ? false : (request.getParameter("fromMessenger")).equalsIgnoreCase("true")?true:false;            
%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<script language="JavaScript">
function searchInactive() {
    document.titlesearch.ptstatus.value="inactive";
    if (checkTypeIn()) document.titlesearch.submit();
}

function searchAll() {
    document.titlesearch.ptstatus.value="";
    if (checkTypeIn()) document.titlesearch.submit();
}
</script>

<form method="get" name="titlesearch" action="demographiccontrol.jsp"
	onsubmit="return checkTypeIn()">
<table BORDER="0" CELLPADDING="0" CELLSPACING="2" WIDTH="100%"
	bgcolor="#CCCCFF">
	<tr>
		<td colspan="6" class="RowTop"><b><bean:message
			key="demographic.zdemographicfulltitlesearch.msgSearch" /></b></td>
	</tr>
	<% String searchMode = request.getParameter("search_mode");
         String keyWord = request.getParameter("keyword");
         if (searchMode == null || searchMode.equals("")) {
             searchMode = "search_name";
         } 
         if (keyWord == null) {
             keyWord = "";
         }
     %>
	<tr>
		<td>
		<table bgcolor="white" width="100%">
			<tr>
				<td width="10%" nowrap><input type="radio" name="search_mode"
					value="search_name"
					<%=searchMode.equals("search_name")?"checked":""%>> <bean:message
					key="demographic.zdemographicfulltitlesearch.formName" /></td>
				<td nowrap><input type="radio" name="search_mode"
					value="search_phone"
					<%=searchMode.equals("search_phone")?"checked":""%>> <bean:message
					key="demographic.zdemographicfulltitlesearch.formPhone" /></td>
				<td nowrap><input type="radio" name="search_mode"
					value="search_dob"
					<%=searchMode.equals("search_dob")?"checked":""%>> <bean:message
					key="demographic.zdemographicfulltitlesearch.formDOB" /></td>
				<td valign="middle" rowspan="2" ALIGN="left"><input type="text"
					NAME="keyword" VALUE="<%=keyWord%>" SIZE="17" MAXLENGTH="100">
				<INPUT TYPE="hidden" NAME="orderby" VALUE="last_name, first_name">
				<INPUT TYPE="hidden" NAME="dboperation" VALUE="search_titlename">
				<INPUT TYPE="hidden" NAME="limit1" VALUE="0"> <INPUT
					TYPE="hidden" NAME="limit2" VALUE="10"> <INPUT
					TYPE="hidden" NAME="displaymode" VALUE="Search"> <INPUT
					TYPE="hidden" NAME="ptstatus" VALUE="active"> <INPUT
					TYPE="hidden" NAME="fromMessenger" VALUE="<%=fromMessenger%>">
				<INPUT TYPE="SUBMIT"
					VALUE="<bean:message key="demographic.zdemographicfulltitlesearch.msgSearch" />"
					SIZE="17"
					TITLE="<bean:message key="demographic.zdemographicfulltitlesearch.tooltips.searchActive"/>">
				&nbsp;&nbsp;&nbsp; <INPUT TYPE="button" onclick="searchInactive();"
					TITLE="<bean:message key="demographic.zdemographicfulltitlesearch.tooltips.searchInactive"/>"
					VALUE="<bean:message key="demographic.search.Inactive"/>">
				<INPUT TYPE="button" onclick="searchAll();"
					TITLE="<bean:message key="demographic.zdemographicfulltitlesearch.tooltips.searchAll"/>"
					VALUE="<bean:message key="demographic.search.All"/>"></td>
			</tr>
			<tr bgcolor="white">
				<td nowrap><input type="radio" name="search_mode"
					value="search_address"
					<%=searchMode.equals("search_address")?"checked":""%>> <bean:message
					key="demographic.zdemographicfulltitlesearch.formAddr" /></td>
				<td nowrap><input type="radio" name="search_mode"
					value="search_hin"
					<%=searchMode.equals("search_hin")?"checked":""%>> <bean:message
					key="demographic.zdemographicfulltitlesearch.formHIN" /></td>
				<td nowrap><input type="radio" name="search_mode"
					value="search_chart_no"
					<%=searchMode.equals("search_chart_no")?"checked":""%>> <bean:message
					key="demographic.zdemographicfulltitlesearch.formChart" /></td>
			</tr>
		</table>
		</td>
	</tr>
</table>
</form>
