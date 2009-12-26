<%@ Page Title="" Language="C#" MasterPageFile="~/tapovan.Master" AutoEventWireup="true" CodeBehind="search.aspx.cs" Inherits="newtap.WebForm2" %>
<asp:Content ID="Content1" ContentPlaceHolderID="body" runat="server">
Gender<asp:DropDownList id="dropdownlist1"  runat="server">
         <asp:ListItem>ALL</asp:ListItem>
         <asp:ListItem>Boy</asp:ListItem>
         <asp:ListItem>Girl</asp:ListItem>
         
      </asp:DropDownList>
      
      <asp:Button id="Button1"  
           Text="Submit" 
           OnClick="Button1_Click" 
           runat="server"/>
       <asp:Label id="Label1" runat="server"/>
      <asp:ObjectDataSource ID="ObjectDataSource1" TypeName="newtap.studentListing"
   SelectMethod="getStudents"  UpdateMethod="getStudents" runat="server"></asp:ObjectDataSource>
     
      <asp:DataList ID="studentdatalist" DataSourceID="ObjectDataSource1" runat="server" >
            <ItemTemplate>

</ItemTemplate>
      </asp:DataList>
   


</asp:Content>
