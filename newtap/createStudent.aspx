<%@ Page Title="" Language="C#" MasterPageFile="~/tapovan.Master" AutoEventWireup="true" CodeBehind="createStudent.aspx.cs" Inherits="newtap.createStudent" %>

<asp:Content ID="Content2" ContentPlaceHolderID="body" runat="server">
<asp:HiddenField runat="server" ID="recordId" Value='<%# st.id  %>' />
<table><tr>

<td>First Name</td><td><asp:TextBox ID="firstname" runat="server" Text='<%# st.FirstName  %>'/></td></tr>
<tr><td>Last Name</td><td><asp:TextBox ID="lastname" runat="server" Text='<%# st.LastName  %>' /></td></tr>
<tr><td>DOB</td><td><asp:TextBox ID="dob" runat="server" Text='<%# st.DOB  %>' /></td></tr>
<tr><td> <asp:Calendar ID="Calendar1" runat="server"/> </td></tr>

<tr><td><asp:Button ID="submit" Text="Submit" runat="server" onclick="submit_Click" /></td></tr>
</table>

<asp:Label ID="fn" runat="server" />


</asp:Content>
