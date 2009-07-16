<%@ Page Title="" Language="C#" MasterPageFile="~/tapovan.Master" AutoEventWireup="true" CodeBehind="createStudent.aspx.cs" Inherits="newtap.createStudent" %>

<asp:Content ID="Content2" ContentPlaceHolderID="body" runat="server">
<asp:HiddenField runat="server" ID="recordId" Value='<%# st.id  %>' />
<table><tr>

<td>First Name</td><td><asp:TextBox ID="firstname" runat="server" Text='<%# st.FirstName  %>'/></td></tr>
<tr><td>Last Name</td><td><asp:TextBox ID="lastname" runat="server" Text='<%# st.LastName  %>' /></td></tr>
<tr><td>DOB</td><td><asp:TextBox ID="dob" runat="server" Text='<%# st.DOB  %>' /></td></tr>
<tr><td>Grade</td><td>

<asp:DropDownList  DataSourceID="ObjectDataSource1" DataValueField="id" ID="GradeDropDown" runat="server"  DataTextField="gradeName" >
<asp:ListItem Selected="True" Value="0" Text="Select..."  ></asp:ListItem>
</asp:DropDownList>
<tr> <td> <asp:FileUpload ID="FileUpload1" runat="server"></asp:FileUpload>
   </td></tr>
    <td><asp:Label ID="UploadStatusLabel1" runat="server">
    </asp:Label></td>


</td></tr>

<tr><td><asp:Button ID="submit" Text="Submit" runat="server" onclick="submit_Click" /></td></tr>
</table>
 <asp:ObjectDataSource ID="ObjectDataSource1" TypeName="newtap.createGrade"
   SelectMethod="getGrades"  UpdateMethod="getGrades" runat="server"></asp:ObjectDataSource>
<asp:Label ID="fn" runat="server" />


</asp:Content>
