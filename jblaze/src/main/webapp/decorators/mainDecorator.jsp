<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page" %>

<html>
<head>
    <title><decorator:title default="{ Unknown Page - shouldn't see this, since
    pages should define title }" /></title>
    
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/screen.css" />
    <%--pulls the header from the page we are decorating and inserts it here --%>
    <decorator:head />
</head> 

<body>
<table width="100%" height="100%" border="1">
    <tr >
        <td id="header" colspan="2" height="20px">
        	<h3> JBLAZE OSCAR</h3>
        </td>
    </tr>
    <tr>
        <td valign="top" width="200">
            <%-- grabs the navigation.jsp page and decorates with the
            panel decorator and puts it here 
            <page:applyDecorator page="/WEB-INF/jsps/navigation.jsp" name="panel" />
            --%>
        </td>
        <td>
        <table width="100%" height="100%">
            <tr>
                <td id="pageTitle">
                    <span class="pageTitle">
                        <%--pulls the title from the page we are
                        decorating and inserts it here --%>
                        <decorator:title />
                    </span>
                </td>
            </tr>
            <tr>
                <td valign="top" height="100%">
                    <%--pulls the body from the page we are
                    decorating and inserts it here --%>
                    <decorator:body />
                </td>
            </tr>
        </table>
        </td>
    </tr>
    <tr>
        <td id="footer" colspan="2">
            <bean:message key="label.footer" />
        </td>
    </tr>
</table>
</body>
</html>
