<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %>
</h1>
<br/>
<form method="post" action="/person.do">
    <input type="text" name="firstName" placeholder="First-Name">
    <input type="text" name="lastName" placeholder="Last-Name">
    <input type="submit" value="submit">
</form>
</body>
</html>