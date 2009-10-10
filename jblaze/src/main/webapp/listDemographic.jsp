<%@ taglib uri="/WEB-INF/taglibs/oscarhtm.tld" prefix="osc"%>

<%@page import="org.caisi.sessionbeans.DemographicSessionBean"%>
<%@page import="org.caisi.persistence.base.exceptions.BusinessException"%>
<%@page import="org.caisi.sessionbeans.ServiceFacade"%>

<%@page import="org.caisi.persistence.model.Demographic"%>
<%@page import="org.caisi.persistence.viewhelper.Utils"%>

<%@page import="org.caisi.persistence.viewhelper.ViewHelper"%>
<%@page import="org.caisi.persistence.base.Range"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>

<head>
<ria:resourcesImport />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/screen.css" />
</head>
<!--  
<script src="js/aa.js"></script>
<script>ajaxAnywhere.formName = "srchForm";</script>
-->
<body>
<jsp:include page="header.jsp" />
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

<table border="1">
	<tr style="display: block" id="r1">
		<td ><a href="#" onclick="expandRow('r1h', 'r1c1');" id="r1c1"> +</a>
		<td >there</td>
	</tr>
	<tr style="display: none" id="r1h">
		<td><a href="ss.jsp">hidden </a>
		<td>data</td>
	</tr>

</table>

<form name="srchForm"
	action="<%=request.getContextPath()%>/listDemographic.jsp"><osc:input
	type="hidden" name="frSearch" value="1" /> <%
 	//if(request.getParameter("frSearch") == null) request.getParameterMap().clear();
 %>
<table class="formTable" border="1">
	<tr>
		<td>Last Name<br> <osc:input value="${param.lastName}" name="lastName" /></td>
		<td>First Name<br> <osc:input name="firstName" value="${param.firstName}" /></td>
		<td>HC Type <br><osc:select name="hcType" value="${param.hcType}" 
			map="<%=ViewHelper.createHCTypeMap()%>" /></td>
	</tr>
	<tr>
		<td>Address <br> <osc:input name="address" value="${param.address}" /></td>

		<td>City <br>
		<osc:input name="city" value="${param.city}" /></td>
		<td>DateJoined From <br> <osc:input name="fromDateJoined"
			value="${param.fromDateJoined}" /> </td><td> Date Joined To <br> <osc:input name="toDateJoined"
			value="${param.toDateJoined}" /></td>
	</tr>
	<tr>
		<td>Provider <br>
		<osc:select name="provider.providerNo"
			value='<%=request.getParameter("provider.providerNo")%>'
			map="<%=ServiceFacade.getInstance().getProviderSessionBean()
							.getAsMap()%>" />
		</td>
		<td><input type="submit" value="Search"></td>
		<td><a href="<%=request.getContextPath()%>/listDemographic.jsp">Clear
		Search</a></td>
		<td>Records <osc:select name="recordsPerPage" showSelect="false" value="${param.hcType}"
			map="<%=ViewHelper.createRecordsPerPageMap() %>" /></td>
	
	</tr>
</table>
</form>
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


</body>
</html>