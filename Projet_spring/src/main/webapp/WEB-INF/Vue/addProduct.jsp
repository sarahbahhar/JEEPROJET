<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ajouter un Produit</title>
    <link rel="stylesheet" href="./css/vendeur.css">
    <link rel="stylesheet" href="./css/style.css">
    <link rel="icon" type="image/png" href="./img/logo2.png">
</head>
<body>
<div style="width: 40%; margin: auto;">
    <h1>Ajouter un Produit</h1>
    <fieldset>
        <legend>Informations du Produit</legend>
        <form id="add-product-form" method="post" action="<%=request.getContextPath()%>/add-product-servlet" enctype="multipart/form-data">
            <table>
                <tr>
                    <td><label for="titre">Titre</label></td>
                    <td><input style="width: 100%;" type="text" id="titre" name="titre" required></td>
                </tr>
                <tr>
                    <td><label for="description">Description</label></td>
                    <td><textarea id="description" name="description" style="width: 100%; " required></textarea></td>

                </tr>

                <tr>
                    <td><label for="miniDescription">Mini Description</label></td>
                    <td><textarea style="width: 100%;" id="miniDescription" name="miniDescription" required></textarea></td>
                </tr>
                <tr>
                    <td><label for="categorie">Catégorie</label></td>
                    <td>
                        <select id="categorie" name="categorie" required>
                            <option value="Velour">Velour</option>
                            <option value="Cuir">Cuir</option>
                            <option value="Banc">Banc</option>
                            <option value="Tissu">Tissu</option>
                            <option value="Lin">Lin</option>
                            <option value="Exterieur">Exterieur</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td><label for="price">Prix</label></td>
                    <td><input style="width: 20%;" type="number" id="price" name="price" min="1" required></td>
                </tr>
                <tr>
                    <td><label for="stock">Stock</label></td>
                    <td><input style="width: 20%;" type="number" id="stock" name="stock" min="1" required></td>
                </tr>
                <tr>
                    <td><label for="image">Image</label></td>
                    <td><input style="width: 100%;" type="file" id="image" name="image" required></td>
                </tr>
                <tr><input type="hidden" name="email" value="${sessionScope.email}" /><tr>

            </table>

        </form>
    </fieldset>

    <div style="display: flex; justify-content: space-between; margin-top: 20px;">

        <form  action="${pageContext.request.contextPath}/my-product-list-servlet" method="post">
            <input type="hidden" name="email" value="${sessionScope.email}" />
            <button class="bouton-golden" type="submit">Retour</button>
        </form>
        <button class="bouton-golden" type="submit" form="add-product-form">Ajouter</button>
    </div>
</div>
</body>
</html>
