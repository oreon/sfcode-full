<%@ taglib uri="/WEB-INF/taglibs/oscarhtm.tld" prefix="osc" %>


<%@page import="org.caisi.persistence.base.exceptions.BusinessException"%>
<%@page import="org.caisi.sessionbeans.ServiceFacade"%>
<%@page import="org.caisi.persistence.viewhelper.Utils"%>

<%@page import="org.caisi.persistence.viewhelper.ViewHelper"%><html>
<head>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/screen.css" />
</head>
<body>
<jsp:include page="header.jsp" />
<jsp:useBean id="demographic"
	class="org.caisi.persistence.model.Demographic" scope="request" />
<jsp:setProperty name="demographic" property="*" />

<%=(ViewHelper.getMessage(request) )%>

<form action="<%=request.getContextPath()%>/formHandler/demographicSessionBean.save" method="post" />
 
<osc:input type="hidden" name="demographicNo" value="<%=demographic.getDemographicNo()%>" /> 
 
<table class='viewInfo'>
	<tr>
		<td>Last Name*
		<td><osc:input  name="lastName"
			value="<%=demographic.getLastName()%>" /></td>
	</tr>
	<tr>
		<td>First Name*
		<td><osc:input  name="firstName"
			value="<%=demographic.getFirstName()%>" /></td>
	</tr>
	<tr> 
		<td>Address</td>
		<td><osc:input  name="address"
			value="<%=demographic.getAddress()%>" /></td>
	<tr>
		<td>City</td>
		<td><osc:input  name="city"
			value="<%=demographic.getCity()%>" /></td>
	<tr>
		<td>HC Type </td>
		<td align="left"><osc:select name="hcType" value="<%=demographic.getHcType()%>"
		  map="<%=ViewHelper.createHCTypeMap()%>" />
		</td>
	</tr>
	<tr>
		<td>Sex* </td>
		<td align="left"><osc:select name="sex" value="<%=demographic.getSex()%>"
		  map="<%=ViewHelper.createGenderMap()%>" />
		</td>
	</tr>
	<tr>
		<td>Provider </td>
		<td align="left"><osc:select name="provider.providerNo" 
			value='<%=demographic.getProvider() != null ? demographic.getProvider().getProviderNo():""%>'
		  map="<%=ServiceFacade.getInstance().getProviderSessionBean().getAsMap()%>" />
		</td>
	</tr>
	<tr>
		<td colspan="1"><input name="update" type="submit" value="Update" /></td>
	</tr>
</table>
</form>


</body>


</html>