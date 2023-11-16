<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Your bank account</title>
</head>
<body>
<h1>Bank Account List</h1>
<table>
    <c:forEach items="${accounts}" var="account">
        <table>
            <tr>
                <td>Nom: ${account.nom}</td>
            </tr>
            <tr>
                <td>Numero: ${account.numero}</td>
            </tr>
            <tr>
                <td>Date d'expiration: ${account.date}</td>
            </tr>
            <tr>
                <td>CVV: ${account.cvv}</td>
            </tr>
            <tr>
                <td colspan="2">
                    <form action="${pageContext.request.contextPath}/delete-bank-account-servlet" method="post">
                        <input type="hidden" name="email" value="${account.email}" />
                        <input type="hidden" name="numero" value="${account.numero}" />
                        <button type="submit">Delete</button>
                    </form>
                </td>
            </tr>
        </table>
    </c:forEach>
</table>
</body>
</html>
