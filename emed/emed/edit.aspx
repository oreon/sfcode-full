<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="edit.aspx.cs"  %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" >
<head runat="server">
    <title></title>
</head>
<body>
    <form id="form1" runat="server">
    <div>
    
        <asp:FormView ID="FormView1" runat="server" DataSourceID="myds" DataKeyNames="id">
            <ItemTemplate>
                <table border="1">
                <tr>
                    <td>First Name</td>
                    <td><%# Eval("firstName") %></td>
                </tr>
                <tr>
                    <td>Last Name</td>
                    <td><%# Eval("lastname") %></td>
                </tr>
                <tr>
                    <td> </td>
                    <td>
                        <asp:Button ID="btnEdit" runat="Server" CommandName="Edit" Text="Edit" />
                         <asp:Button ID="btnInsert" runat="Server" CommandName="New" Text="New" />
                     </td>
                 </tr>
                </table>
            </ItemTemplate>
            
            <EditItemTemplate>
                <table border="1">
                <td>First Name</td>
                    <td><%# Eval("id") %></td>
                <tr>
                    <td>First Name</td>
                    <td><asp:TextBox ID="TextBox1" runat="Server" Text='<%# Bind("firstName")%>' /></td>
                </tr>
                <tr>
                    <td>Last Name</td>
                    <td><asp:TextBox ID="TextBox2" runat="Server" Text='<%# Bind("lastName")%>' /></td>
                </tr>
                <tr>
                    <td> </td>
                    <td>
                       <asp:Button ID="btnUpdate" runat="Server" CommandName="Update" Text="Update" />
                        <asp:Button ID="Button1" runat="Server" CommandName="Cancel" Text="Cancel" />
                     </td>
                 </tr>
                </table>
            </EditItemTemplate>
            
            <InsertItemTemplate>
                <table border="1">
                    <td>First Name</td>
                    <td><asp:TextBox ID="TextBox1" runat="Server" Text='<%# Bind("firstName")%>' /></td>
                </tr>
                <tr>
                    <td>Last Name</td>
                    <td><asp:TextBox ID="TextBox2" runat="Server" Text='<%# Bind("lastName")%>' /></td>
                </tr>
                <tr>
                    <td> </td>
                    <td>
                       <asp:Button ID="btnUpdate" runat="Server" CommandName="Insert" Text="Add" />
                        <asp:Button ID="Button1" runat="Server" CommandName="Cancel" Text="Cancel" />
                     </td>
                 </tr>
                </table>
            </InsertItemTemplate>
            
        </asp:FormView>
    
    </div>
        
        <asp:SqlDataSource ID="myds" runat="server" ConnectionString="Data Source=localhost\SQLEXPRESS;Initial Catalog=model;Integrated Security=True"
         SelectCommand="SELECT [id], [firstname], [lastname]  FROM [students] WHERE [id] = @id"
         UpdateCommand="UPDATE [students] SET [lastname] = @lastname, [firstname] = @firstname WHERE [id] = @id"
         DeleteCommand="DELETE FROM [students] WHERE [id] = @id" 
         InsertCommand="Insert into [students] ( [firstname], [lastname]) values ( @firstname, @lastname)"
         >
         
        <SelectParameters>
            <asp:QueryStringParameter Name="id" QueryStringField="id" />
        </SelectParameters>

        </asp:SqlDataSource>
        
    </form>
</body>
</html>
