
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
        <td><button>Accept</button></td>
        <td><button>Refuse</button></td>
        </c:forEach>
</table>
</body>
</html>
