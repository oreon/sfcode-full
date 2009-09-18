<%@page import="com.ganz.wonders.model.*, 
                java.util.List"%>
<?xml version="1.0" encoding="utf-8"?>
<catalog>
<%
    
    List<Product> list = Product.createProducts();
	

    for (int i=0; i<list.size(); i++)
    {
       Product product = (Product) list.get(i);
%>    

<name><%= product.getName() %></name>
<description><%= product.getDescription() %></description>
<price><%= product.getPrice() %></price>
<image><%= product.getImage() %></image>
<qtyInStock><%= product.getQtyInStock() %></qtyInStock>
</product>
<%
    }
%>
</catalog>
