<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>


		<title>Registration Success</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link rel="stylesheet" type="text/css" href="style/witch.css">

	</head>
	<f:view>

		<body>
			<table align="center" width="780">
				<tr>
					<td align="center">
						<br>
						<br>
						<table class="form">
							<tr class="headrow">
								<td colspan="2">
									You have been registered as:
								</td>
							</tr>
							<tr class="even">
								<td>
									Name:
								</td>
								<td>
									<h:outputText
										value="#{customerBean.customer.firstName} #{customerBean.customer.lastName}" />
								</td>
							</tr>
							<tr class="odd">
								<td>
									Email:
								</td>
								<td>
									<h:outputText value="#{customerBean.customer.email}" />
								</td>
							</tr>
							<tr class="even">
								<td>
									Country:
								</td>
								<td>
									<h:outputText value="#{customerBean.customer.country}" />
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</body>

	</f:view>
</html>
