<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Moderator Waiting List</title>
</head>
<body>
<h1>Moderator Waiting List</h1>
<table>
    <tr>
        <th>Email</th>
        <th>Motivation</th>
    </tr>

    <c:forEach items="${moderators}" var="moderator">
    <tr>
        <td>${moderator.email}</td>
        <td>  ${moderator.message}  </td>
        <td><form action="acceptModerator" method="post">
            <input type="hidden" name="email" value="${moderator.email}" />
            <button type="submit">Accept</button>
        </form></td>
        <td><form action="${pageContext.request.contextPath}/delete-dismissed-mod-servlet" method="post">
            <input type="hidden" name="email" value="${moderator.email}" />
            <button type="submit">Refuse</button>
        </form>

        </td>
        </c:forEach>
</table>
</body>
</html>
