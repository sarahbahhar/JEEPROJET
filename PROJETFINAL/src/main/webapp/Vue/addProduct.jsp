<%--
  Created by IntelliJ IDEA.
  User: CYTech Student
  Date: 08/11/2023
  Time: 21:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Product</title>
</head>
<body>
<form method="POST" action="<%=request.getContextPath()%>/add-product-servlet">

    <label for="titre">Titre</label>
    <input type="text" id="titre" name="titre"><br>

    <label for="description">Description</label>
    <textarea id="description" name="description"></textarea><br>

    <label for="stock">Stock</label>
    <input type="text" id="stock" name="stock"><br>

    <label for="email">Email</label>
    <input type="text" id="email" name="email"><br>

    <label for="nomImage">Nom de l'image</label>
    <input type="text" id="nomImage" name="nomImage"><br>

    <label for="miniDescription">Mini Description</label>
    <textarea id="miniDescription" name="miniDescription"></textarea><br>

    <label for="prix">Prix</label>
    <input type="text" id="prix" name="prix"><br>

    <input type="submit" value="Ajouter Produit">
</form>


</body>
</html>
