<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<f:view>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>MyFacesTest</title>
</head>
<body>
  <h1>Success!</h1>
  <table>
  	<tr>
  		<td colspan="2">
  			You have been registered as:
		</td>
  	</tr>
  	<tr>
  		<td>Name:</td>
  		<td><h:outputText value="#{customer.firstName} #{customer.lastName}"/></td>
  	</tr>
  	<tr>
  		<td>Email:</td>
  		<td><h:outputText value="#{customer.email}"/></td>
  	</tr>
  	<tr>
  		<td>Country:</td>
  		<td><h:outputText value="#{customer.country}"/></td>
  	</tr>
  </table>
</body>
</html>
</f:view>