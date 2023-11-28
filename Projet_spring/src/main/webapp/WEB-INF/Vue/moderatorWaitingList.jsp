<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Demande en cours</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
    <link rel="shortcut icon" href="<%=request.getContextPath()%>/img/logo_onglet.ico" type="image/x-icon">
    <%@ include file="header.jsp" %>
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
        <td><form action="${pageContext.request.contextPath}/add-moderator-servlet" method="post">
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
<footer>
    <%@ include file="footer.jsp" %>
</footer>
</html>
