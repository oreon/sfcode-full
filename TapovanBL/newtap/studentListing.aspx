<%@ Page Title="" Language="C#" MasterPageFile="~/tapovan.Master" AutoEventWireup="true" CodeBehind="studentListing.aspx.cs" Inherits="newtap.studentListing" %>
<asp:Content ID="Content1" ContentPlaceHolderID="body" runat="server">
First Name <asp:TextBox ID="firstName" runat="server" />
Last Name <asp:TextBox ID="lastName" runat="server" />
<asp:Button ID="BtnFilter"  runat="server" onclick="BtnFilter_Click"/>

  Successfully saved student <%= "xxx " + Request["lastname"]%>
  <asp:ObjectDataSource ID="ObjectDataSource1" TypeName="newtap.studentListing"
   SelectMethod="getStudents"  UpdateMethod="getStudents" runat="server"></asp:ObjectDataSource>
<asp:GridView ID="GridView1" runat="server"  DataSourceID="ObjectDataSource1">


</asp:GridView>

<asp:
</asp:Content>

