<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

	<title>Registration</title>
	
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="style/witch.css">
</head>
  
<body>
	<f:view>




<h:form id="form">
<table width="780" align="center" border="0">
	<tr>
		<td align="center" width="100"><br><br>
			<table  class="form" cellspacing="0" cellpadding="0">
				<tr class="headrow">
					<td colspan="2">Register</td>
				</tr>	
				<tr class="odd">
					<td>First name:</td>
					<td>
						<h:inputText value="#{customerBean.customer.firstName}" id="firstName" required="true"/>
					</td>
				</tr>
				<tr >	
					<td colspan="2" >
						<h:message for="firstName" styleClass="error"/>
					</td>
				</tr>
				<tr class="even">
					<td>Last name:</td>
					<td>
						<h:inputText value="#{customerBean.customer.lastName}" id="lastName" required="true"/>
					</td>
				</tr>	
				<tr >	
					<td colspan="2" >
						<h:message for="lastName" styleClass="error"/>
					</td>
				</tr>
				<tr class="odd">
					<td>Password:</td>
					<td>
						<h:inputSecret value="#{customerBean.customer.password}" />			
					</td>					
				</tr>
				<tr class="even">
					<td>Email:</td>
					<td>			
			    		<h:inputText value="#{customerBean.customer.email}" id="email">
			    			<t:validateEmail/>
			    		</h:inputText>    		
					</td>
				</tr>
				<tr>	
					<td colspan="2" >
						<h:message for="email" styleClass="error"/>
					</td>
				</tr>
				<tr class="odd">
					<td>Country:</td>
					<td>			
			    		<h:selectOneMenu value="#{customerBean.customer.country}">
			    			<f:selectItems value="#{customerBean.availableCountries}"/>
			    		</h:selectOneMenu>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						&nbsp;
					</td>
				</tr>	
				<tr class="even">
					<td colspan="2" align="center">
						<h:commandButton value="Submit" action="#{customerBean.register}"/>
					</td>					
				</tr>		
			</table>
		</td>
	</tr>
</table>			
</h:form>


</f:view>
</body>
</html>