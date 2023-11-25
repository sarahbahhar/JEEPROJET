<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ajouter un Produit</title>
    <link rel="stylesheet" href="./css/vendeur.css">
    <link rel="stylesheet" href="./css/style.css">
    <link rel="icon" type="image/png" href="./img/logo2.png">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/singIn.css">
    <script>
        function submitForms() {
            document.getElementById('partie1').submit();
            document.getElementById('partie2').submit();
        }
    </script>
</head>
<body>
<div class="container" id="container">
    <div class="form-container sign-up-container">
        <form id="partie2" method="POST" action="<%=request.getContextPath()%>/inscriptionServlet">
            <h1>Description</h1>
            <br>
            <textarea class="custom-textarea-g" id="description" name="description" placeholder="Description" required></textarea>

            <textarea class="custom-textarea-p" id="miniDescription" name="miniDescription" placeholder="Mini description" required></textarea>
            <button onclick="submitForms()" class="lien" type="button">Ajouter</button>
        </form>
    </div>
    <div class="form-container sign-in-container">
        <form id="partie1" method="post" action="<%=request.getContextPath()%>/add-product-servlet" enctype="multipart/form-data">
            <h1>Ajouter un Produit</h1>
            <input type="text" id="titre" name="titre" placeholder="Titre" required>
            <select id="categorie" name="categorie" required>
                <option value="" disabled selected>Choisissez une catégorie</option>
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
            <input type="hidden" name="email" value="${sessionScope.email}" />

        </form>
    </div>
    <div class="overlay-container">
        <div class="overlay">
            <div class="overlay-panel overlay-left">
                <h1>Informations du produit</h1>
                <button class="ghost" id="signIn">Precedent</button>
            </div>
            <div class="overlay-panel overlay-right">
                <h1>Description du produit</h1>
               <button class="ghost" id="signUp">Suivant</button>
            </div>
        </div>
    </div>
</div>
<script src="${pageContext.request.contextPath}/js/signIn.js"></script>

</body>
</html>
