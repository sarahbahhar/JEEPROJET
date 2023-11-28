<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Demande en cours</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/table.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" integrity="sha512-0mUGoUp9ORxY3d5ZKJywuPjr5JP0iKEJhcJRBHIoikqQoRgPdz5VcHcWI1aEjRRW2pSNzNeVbWxgA5eZ3ozGbQA==" crossorigin="anonymous" />
    <link rel="shortcut icon" href="<%=request.getContextPath()%>/img/logo_onglet.ico" type="image/x-icon">
</head>
<body>
<c:choose>
    <c:when test="${empty moderators}">
        <div class="message"><h1>Toute les demandes ont été traitées</h1></div>
    </c:when>
            <c:otherwise>
                <div id="demo" style="border-radius: 20px;">
        <div style="border-radius: 20px;" class="table-responsive-vertical shadow-z-1">
                <table style="border-radius: 20px;" id="table" class="table table-hover table-mc-light-blue">
                    <thead>
                    <tr>
                        <th>Email</th>
                        <th>Motivation</th>
                        <th>Accepter</th>
                        <th>Refuser</th>
                    </tr>
                    </thead>
                    <tbody>

                    <c:forEach items="${moderators}" var="moderator">
                        <tr>
                            <td><p style="font-size: 100%;">${moderator.email}</p></td>
                            <td><p style="font-size: 100%;">${moderator.message}</p></td>
                            <td>
                                <form action="${pageContext.request.contextPath}/add-moderator-servlet" method="post">
                                    <input type="hidden" name="email" value="${moderator.email}" />
                                    <p style="font-size: 100%;"><button type="submit" class="btn btn-success">
                                        <i class="fas fa-check"></i> <!-- Icône "Accepter" -->
                                    </button></p>
                                </form>
                            </td>
                            <td>
                                <form action="${pageContext.request.contextPath}/delete-dismissed-mod-servlet" method="post">
                                    <input type="hidden" name="email" value="${moderator.email}" />
                                    <p style="font-size: 100%;"><button type="submit" class="btn btn-danger">
                                        <i class="fas fa-times"></i> <!-- Icône "Refuser" -->
                                    </button></p>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table></div>
            </div>
            </c:otherwise>

        </c:choose>


</body>
</html>
