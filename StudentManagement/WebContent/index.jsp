
<%@page import="java.util.Date"%><%= new Date() %>
<br/>

<%
for ( int i = 1	; i < 11; i++){
	out.println(i + "<br/>");
	
	
}

out.println("loop finished");
%>