<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="editStudent.aspx.cs" Inherits="emed.EditStudent" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" >
<head runat="server">
    <title></title>
</head>
<body>
    <form id="form1" runat="server">
    <div>
        Students
    </div>
    <asp:gridview ID="Gridview1" runat="server" AllowPaging="false" AllowSorting="true" PageSize="10">
        <Columns>
        <asp:BoundField dataField="firstName" Headertext="First Name" /> 
        <asp:BoundField dataField="lastName" headertext="Last Name" /> 
        <asp:CommandField ShowSelectButton="true" ShowDeleteButton="true" />
        </Columns>
    </asp:gridview>
    <asp:ListBox ID="lst" runat="server"></asp:ListBox>
    <asp:SqlDataSource ID="SqlDataSource1" runat="server" 
        onselecting="SqlDataSource1_Selecting" 
        ConnectionString="Data Source=localhost\SQLEXPRESS;Initial Catalog=model;Integrated Security=True" 
        ProviderName="System.Data.SqlClient"></asp:SqlDataSource>
    <asp:DataList ID="DataList1" runat="server" DataSourceID="SqlDataSource1">
    <EditItemTemplate>  
    <asp:TextBox Text='<% Container.getDataItem("firstName") %>' runat="server" />
    </EditItemTemplate>
    </asp:DataList>
    </form>
</body>
</html>

