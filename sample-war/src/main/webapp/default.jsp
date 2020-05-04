<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    String message = java.util.Objects.toString(request.getAttribute("message"), "");
%>
<!DOCTYPE html>
<html>
<head>
<title>Sample</title>
<meta charset="UTF-8" />
</head>
<body>
    <p>Click Run to begin transaction</p>
    <form method="post" action="<%= request.getContextPath() %>">
        <button type="submit">Run</button>
    </form>
    <p><%= message %></p>
</body>
</html>
