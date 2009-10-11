<%@ taglib uri="/WEB-INF/taglibs/oscarhtm.tld" prefix="osc"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>

<%@page import="org.caisi.sessionbeans.DemographicSessionBean"%>
<%@page import="org.caisi.persistence.base.exceptions.BusinessException"%>
<%@page import="org.caisi.sessionbeans.ServiceFacade"%>

<%@page import="org.caisi.persistence.model.Demographic"%>
<%@page import="org.caisi.persistence.viewhelper.Utils"%>

<%@page import="org.caisi.persistence.viewhelper.ViewHelper"%>
<%@page import="org.caisi.persistence.base.Range"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
	

<decorator:title > Welcome to JBlazer </decorator:title>


<script>
function expandRow(row, imgCol){
	var element = document.getElementById(row);
	var img = document.getElementById(imgCol);
	//alert(img.innerHTML);
	
	if (element.style.display=="none"){
		img.innerHTML = "-";
		element.style.display="block";
	}else{
		img.innerHTML = "+";
		element.style.display="none";
		
	}
}

</script>

<%
	out.println(ViewHelper.getMessage(request));
%>

<table style="border:1px solid #aaa">
	<tr style="display: block; background: #ffa" id="r1">
		<td ><a href="#" onclick="expandRow('r1h', 'r1c1');" id="r1c1"> +</a>
		<td >Search</td>
	</tr>
	<tr style="display: Block" id="r1h">
		<td colspan="2">
			<%@ include file="searchDemo.jsp" %>
		</td>
	</tr>
</table>


<br>

<%
	DemographicSessionBean demographicSessionBean = ServiceFacade.getInstance().getDemographicSessionBean();
	java.util.List<Demographic> dems = demographicSessionBean.getRecords(request, response);
%>

<osc:paginator style="border 1px solid gray ; background-color: #ffd"
	totalRecords="<%= demographicSessionBean.getSearchByExampleCount(request, response)%>" />

<br> <br>

<osc:table list="<%= dems %>" styleClass="dataTable" width="60%" >
	<osc:column name="firstName" linkTo="formHandler/demographicSessionBean.load" param="entityId" />
	<osc:column name="lastName" />
	<osc:column name="sex" />
	<osc:column name="city" />
	<osc:column name="hcType" />
	<osc:column name="dateJoined" />
	<osc:column name="provider.displayName" label="Provider"/>
</osc:table>

