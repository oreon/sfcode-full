<%@ page language="java" buffer="8kb" autoFlush="true" isThreadSafe="true" isErrorPage="false"  %>
<%@ page import="java.util.*, java.util.regex.*, java.text.*, com.jamonapi.*,
  com.jamonapi.utils.* " %>

<html>
<head>
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/screen.css" />
</head>
<body>
<%= MonitorFactory.getReport() %>

</html>