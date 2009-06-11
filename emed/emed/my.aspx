<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="my.aspx.cs" Inherits="emed._Default" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title></title>
    <link rel="Stylesheet" type="text/css" href="Style.css" />
</head>
<body>
    U r using <%# Request.Browser.Browser %>
    <form id="form1" runat="server" method="get">
    <asp:Label ID="debug" runat="server" />
    <div style="border: 1px solid azure; font-family: Arial, Verdana; background-color:#ffd">
        <asp:Table runat="server">
            <asp:TableRow> 
            <asp:TableCell>First Name </asp:TableCell> <asp:TableCell> <asp:TextBox ID="firstName" Text="<%# student.firstName %>" runat="server" /> </asp:TableCell>
            </asp:TableRow>
            <asp:TableRow> 
            <asp:TableCell>Last Name: </asp:TableCell> <asp:TableCell> <asp:TextBox ID="lastName" Text="<%# student.lastName %>" BackColor="Azure" runat="server" /> </asp:TableCell>
            </asp:TableRow>
            <asp:TableFooterRow>
            <asp:TableCell>  <asp:Button ID="Button1" runat="server" Text="Submit" OnClick="Convert_ServerClick" /></asp:TableCell>
            </asp:TableFooterRow>
        </asp:Table>
       
    </div>
 
    </form>
    
</body>
</html>
