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