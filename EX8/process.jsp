<%@ page contentType="text/html;charset=UTF-8" %>
<% request.setCharacterEncoding("UTF-8"); %>
<%!
    private String escape(String value) {
        if (value == null) return "";
        return value.replace("&", "&amp;").replace("<", "&lt;")
            .replace(">", "&gt;").replace("\"", "&quot;").replace("'", "&#39;");
    }
%>
<!DOCTYPE html>
<html lang="en">
<head><meta charset="UTF-8"><title>Registration Details</title></head>
<body>
<h1>Registration Details</h1>
<%
String username = request.getParameter("username");
String password = request.getParameter("password");
String name = request.getParameter("name");
String card = request.getParameter("card");
String email = request.getParameter("email");
String phone = request.getParameter("phone");
%>
<table border="1" cellpadding="10"><tr><th>Field</th><th>Value</th></tr>
<tr><td>Username</td><td><%= escape(username) %></td></tr>
<tr><td>Password</td><td><%= escape(password) %></td></tr>
<tr><td>Name</td><td><%= escape(name) %></td></tr>
<tr><td>Credit Card Number</td><td><%= escape(card) %></td></tr>
<tr><td>Email</td><td><%= escape(email) %></td></tr>
<tr><td>Phone Number</td><td><%= escape(phone) %></td></tr>
</table>
<p><a href="register.html">Back to Registration</a></p>
</body>
</html>
