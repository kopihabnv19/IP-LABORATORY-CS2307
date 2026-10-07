<%@ page contentType="text/html;charset=UTF-8" import="java.sql.*" %>
<% request.setCharacterEncoding("UTF-8"); %>
<%!
    private String escape(String value) {
        if (value == null) return "";
        return value.replace("&", "&amp;").replace("<", "&lt;")
            .replace(">", "&gt;").replace("\"", "&quot;").replace("'", "&#39;");
    }
%>
<!DOCTYPE html>
<html><head><meta charset="UTF-8"><title>Order Details</title></head><body>
<%
try {
    String customer = request.getParameter("customer");
    String product = request.getParameter("product");
    int quantity = Integer.parseInt(request.getParameter("quantity"));
    double price = Double.parseDouble(request.getParameter("price"));
    if (customer == null || customer.trim().isEmpty() || customer.length() > 50
            || product == null || product.length() > 50 || quantity < 1
            || !Double.isFinite(price) || price < 0) {
        throw new IllegalArgumentException("Enter valid order details.");
    }
    Class.forName("com.mysql.cj.jdbc.Driver");
    String sql = "INSERT INTO orders (customer_name,product_name,quantity,price) VALUES(?,?,?,?)";
    try (Connection con = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/shoppingjspdb", "root", "");
         PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setString(1, customer.trim());
        ps.setString(2, product);
        ps.setInt(3, quantity);
        ps.setDouble(4, price);
        ps.executeUpdate();
        out.println("<h2>Order Placed Successfully!</h2>");
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM orders ORDER BY order_id")) {
%>
<h2>Order Details</h2>
<table border="1" cellpadding="10">
<tr><th>Order ID</th><th>Customer</th><th>Product</th><th>Quantity</th><th>Price</th></tr>
<% while (rs.next()) { %>
<tr>
<td><%= rs.getInt("order_id") %></td>
<td><%= escape(rs.getString("customer_name")) %></td>
<td><%= escape(rs.getString("product_name")) %></td>
<td><%= rs.getInt("quantity") %></td>
<td><%= rs.getBigDecimal("price") %></td>
</tr>
<% } %>
</table>
<%
        }
    }
} catch (Exception e) {
    out.println("<h3>Error: " + escape(e.getMessage()) + "</h3>");
}
%>
<p><a href="order.html">Place Another Order</a></p>
</body></html>
