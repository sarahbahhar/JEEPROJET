<%--
  Created by IntelliJ IDEA.
  User: CYTech Student
  Date: 10/31/2023
  Time: 11:19 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign In</title>
</head>
<body>
<form action="<%=request.getContextPath()%>/login" method="post">
    <label for="email">Email</label>
    <input type="text" id="email" name="email"><br>
    <label for="password">password</label>
    <input type="password" id="password" name="password">

    <input type="submit" value="se connecter">

</form>

</body>
</html>
