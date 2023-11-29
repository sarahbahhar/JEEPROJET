<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Mes infos</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/singIn.css">
    <link rel="shortcut icon" href="<%=request.getContextPath()%>/img/logo_onglet.ico" type="image/x-icon">
    <%@ include file="header.jsp" %>
</head>

<script>
    document.addEventListener('DOMContentLoaded', (event) => {
        var dateField = document.getElementById('date');
        var today = new Date();
        var maxDate = today.toISOString().split('T')[0]; // Convertit la date actuelle en format AAAA-MM-JJ
        dateField.setAttribute('max', maxDate);
    });
</script>

<body>
<div class="container" id="container">
    <div class="form-container sign-in-container">
        <form method="POST" action="<%=request.getContextPath()%>/infoCompteServlet">
            <h1>Mes Informations</h1>
            <input type="date" placeholder="Date de naissance" id="date" name="date" required>
            <input type="text" placeholder="Numéro de téléphone" id="telephone" name="telephone" required>
            <input type="text" id="adresse" name="adresse" placeholder="Adresse" required>
            <input type="text" id="ville" name="ville" placeholder="Ville" required>
            <input type="number" id="codePostal" name="codePostal" placeholder="Code Postal" required>
            <input type="text" id="pays" name="pays" placeholder="Pays" required>
            <button class="lien" type="submit" >Mettre à jour</button>
        </form>
    </div>
    <div class="overlay-container">
        <div class="overlay">
            <div class="overlay-panel overlay-right">
                <img src="${pageContext.request.contextPath}/img/sideInfoCompte.jpg" alt="canape">
            </div>
        </div>
    </div>
</div>
</body>
</html>
