<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="DrugForm.aspx.cs" Inherits="emed.WebForm3" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" >
<head runat="server">
    <title></title>
</head>
<body>
    <form id="form1" runat="server">
    <div>
    
    </div>
    <asp:TextBox ID="name" runat="server" Text='<%# Bind("drug.Name")%>'></asp:TextBox>
    <p>
        <asp:TextBox ID="dosage" runat="server"></asp:TextBox>
    </p>
    <p>
        <asp:TextBox ID="form" runat="server"></asp:TextBox>
    </p>
    <asp:Button ID="Button1" runat="server" Text="Button" />
    </form>
</body>
</html>
