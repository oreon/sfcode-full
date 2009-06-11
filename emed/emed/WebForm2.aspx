<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="WebForm2.aspx.cs" Inherits="emed.WebForm2" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" >
<head runat="server">
    <title></title>
</head>
<body>
    <form id="form1" runat="server">
    <div style="border: 1px solid #dde; background-color: #ffd; padding: 20px; " > 
        
        <asp:DataGrid ID="mygrid"  runat="server" DataSourceID="myds" 
            AllowPaging="True" AllowSorting="True" Height="124px" 
            PageSize="18" 
            Width="701px" >
            <AlternatingItemStyle BackColor="#fafafa"  />
           <Columns>
                      <asp:HyperlinkColumn text="Edit" NavigateUrl="edit.aspx" DataNavigateUrlField="id"
                        DataNavigateUrlFormatString="edit.aspx?id={0}"
                       />     
            </Columns>
        </asp:DataGrid>
       
        
        <asp:SqlDataSource ID="myds" runat="server" ConnectionString="Data Source=localhost\SQLEXPRESS;Initial Catalog=model;Integrated Security=True"
         SelectCommand="SELECT [id], [lastname], [firstname] FROM [students]"
         UpdateCommand="UPDATE [students] SET [lastname] = @lastname, [firstname] = @firstname WHERE id = @id"
         DeleteCommand="DELETE FROM [students] WHERE [id] = @id" />
        
    </div>
    </form>
</body>
</html>
