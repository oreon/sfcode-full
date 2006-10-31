<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<f:view>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>MyFaces Test</title>
</head>
<body>
<h1>Register</h1>

<h:form id="form">
	<table>
	<tr>
		<td>First name:</td>
		<td>
			<h:inputText value="#{customer.firstName}" id="firstName" required="true"/>
		</td>
		<td>
			<h:message for="firstName" style="color: red"/>
		</td>
	</tr>
	<tr>
		<td>Last name:</td>
		<td>
			<h:inputText value="#{customer.lastName}" id="lastName" required="true"/>
		</td>
		<td>
			<h:message for="lastName" style="color: red"/>
		</td>
	</tr>
	<tr>
		<td>Password:</td>
		<td>
			<h:inputSecret value="#{customer.password}" />			
		</td>
		<td>
			&nbsp;
		</td>
	</tr>
	<tr>
		<td>Email:</td>
		<td>			
    		<h:inputText value="#{customer.email}" id="email">
    			<t:validateEmail/>
    		</h:inputText>    		
		</td>
		<td>
			<h:message for="email" style="color: red"/>
		</td>
	</tr>
	<tr>
		<td>Country:</td>
		<td>			
    		<h:selectOneMenu value="#{customer.country}">
    			<f:selectItems value="#{customer.availableCountries}"/>
    		</h:selectOneMenu>
		</td>
		<td>
			&nbsp;
		</td>
	</tr>
	<tr>
		<td colspan="3" align="center">
			&nbsp;
		</td>
	</tr>	
	<tr>
		<td colspan="2" align="center">
			<h:commandButton action="#{customer.register}"/>
		</td>
		<td>
			&nbsp;
		</td>
	</tr>		
	</table>
</h:form>

</body>
</html>
</f:view>