<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ajouter un Produit</title>
    <link rel="stylesheet" href="./css/vendeur.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
    <link rel="icon" type="image/png" href="./img/logo2.png">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/singIn.css">
    <%@ include file="header.jsp" %>
</head>
<body>

<div class="container special-container" id="container">
    <div class="form-container sign-in-container">
        <form method="post" action="<%=request.getContextPath()%>/add-product-servlet" enctype="multipart/form-data">
            <h1>Ajouter un Produit</h1>
            <input type="text" id="titre" name="titre" placeholder="Titre" required>
            <textarea class="custom-textarea-g" id="description" name="description" placeholder="Description" required></textarea>
            <textarea class="custom-textarea-p" id="miniDescription" name="miniDescription" placeholder="Mini description" required></textarea>
            <select id="categorie" name="categorie" required>
                            <option value="Velour">Velour</option>
                            <option value="Cuir">Cuir</option>
                            <option value="Banc">Banc</option>
                            <option value="Tissu">Tissu</option>
                            <option value="Lin">Lin</option>
                            <option value="Exterieur">Exterieur</option>
            </select>
            <input type="number" id="price" name="price" min="1" placeholder="Prix" required>
            <input type="number" id="stock" name="stock" min="1" placeholder="Stock" required>
            <input type="file" id="image" name="image" required>
            <input type="file" id="image2" name="image2" required>
            <input type="hidden" name="email" value="${sessionScope.email}" />
            <button class="lien" type="submit" >Ajouter</button>
        </form>
    </div>
    <div class="overlay-container">
        <div class="overlay">
            <div class="overlay-panel overlay-right">
                <img src="${pageContext.request.contextPath}/img/nouveauprod.jpg" alt="canape">
            </div>
        </div>
    </div>
</div>
</body>
<footer>
    <%@ include file="footer.jsp" %>
</footer>
</html>
