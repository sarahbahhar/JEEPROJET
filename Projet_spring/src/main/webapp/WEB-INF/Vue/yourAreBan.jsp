<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Banie</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
    <link rel="shortcut icon" href="<%=request.getContextPath()%>/img/logo_onglet.ico" type="image/x-icon">
    <%@ include file="header.jsp" %>
</head>
<body>
<div class="container-left" >
    <c:if test="${not empty sessionScope.dateBanni}">
        <div >
            <h2 class="red-text">Vous avez été banni</h2>
            <form action="${pageContext.request.contextPath}/home.jsp" method="get">

            <table >
                <tr>
                    <th>Date de fin du bannissement :</th>
                    <td>${sessionScope.dateBanni}</td>
                </tr>
                <tr>
                    <th>Nombre de bannissements :</th>
                    <td>${sessionScope.nbBannissement}</td>
                </tr>
                <tr>
                    <th>Raison du bannissement :</th>
                    <td>${sessionScope.motifCourtBannissement}</td>
                </tr>
                <tr>
                    <th>Détails :</th>
                    <td>${sessionScope.motifLongBanissement}</td>
                </tr>
                <tr>
                    <td colspan="2">
                        Une fois la date de bannissement passée, veuillez vous reconnecter pour réactiver votre compte.
                    </td>
                </tr>
            </table>
            <button type="submit" class="bouton-golden">Retour à l'accueil</button>
            </form>
        </div>

    </c:if>
</div>
</body>
<footer>
    <%@ include file="footer.jsp" %>
</footer>
</html>