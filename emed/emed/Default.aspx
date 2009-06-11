<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="Default.aspx.cs" Inherits="emed._Default" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" >
<head runat="server">
    <title></title>
</head>
<body>
  
    <form id="form1" runat="server">
    <div>
        US $:<asp:TextBox ID="US" BackColor="Azure"  runat="server"/>
        <asp:RangeValidator ErrorMessage="Enter a number from 0 to 10000"
         MinimumValue="0" MaximumValue="10000" Type="Currency" ControlToValidate="US" runat="server" />
        <input type="submit" runat="server" id="convert" onserverclick="Convert_ServerClick" />
        
        <div id="Result" style="font-weight:bold"  runat="server" />
         <asp:Button ID="Button1" runat="server" Text="Button" OnClick="Convert_ServerClick" />
    </div>
    </form>
   
    
    <iewc:
    
    
</body>
</html>
