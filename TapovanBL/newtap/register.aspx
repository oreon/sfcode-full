<%@ Page Title="" Language="C#" MasterPageFile="~/tapovan.Master" AutoEventWireup="true" CodeBehind="register.aspx.cs" Inherits="newtap.register" %>
<asp:Content ID="Content1" ContentPlaceHolderID="body" runat="server">

    <table>
<tr>
<td>First Name</td><td><asp:TextBox ID="firstname"  runat="server" Text='<%# sp.FirstName  %>' /></td></tr>
<tr><td>Last Name</td><td><asp:TextBox ID="lastname" runat="server" /></td></tr>
<tr><td>Email</td><td><asp:TextBox ID="email" runat="server" /></td></tr>
<tr><td>User Name</td><td><asp:TextBox ID="username" runat="server" /></td></tr>
<tr><td>Password</td><td><asp:TextBox ID="password" runat="server" /></td></tr>
<tr><td>Retype Password</td><td><asp:TextBox ID="retypepassword" runat="server" /></td></tr>
<tr><td>Phone</td><td><asp:TextBox ID="phone" runat="server" /></td></tr>
<tr><td><asp:Button ID="b1" text="Register" runat="server" onclick="b1_Click" /></td></tr>
</table>

<asp:Label ID="fn" runat="server" />

</asp:Content>
