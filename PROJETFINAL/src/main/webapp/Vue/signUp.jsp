<%--
  Created by IntelliJ IDEA.
  User: CYTech Student
  Date: 08/11/2023
  Time: 21:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign Up</title>
</head>
<body>
    <form method="POST" action="<%=request.getContextPath()%>/inscription">
        <label for="email">Email</label>
        <input type="text" id="email" name="email"><br>
        <label for="password">password</label>
        <input type="password" id="password" name="password">



        <input type="submit" value="verifier">
    </form>

</body>
</html>
