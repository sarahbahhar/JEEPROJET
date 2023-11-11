<%--
  Created by IntelliJ IDEA.
  User: CYTech Student
  Date: 11/6/2023
  Time: 5:56 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport">
    <title>Accueil</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
    <link rel="shortcut icon"  href="<%=request.getContextPath()%>/img/logo_onglet.ico" type="image/x-icon">
</head>
<body>
<jsp:include page="/Vue/header.jsp" />



<div>
<a href=<%=request.getContextPath()%>>Home</a>


<form>
    <label for="menu">Admin Menu</label>
    <select id="menu" onchange="window.location.href = this.value;">
        <option>-----------</option>
        <option value="Vue/clientList.jsp">Client List</option>
        <option value="Vue/productList.jsp">Product List</option>
        <option value="Vue/moderatorList.jsp">Moderator List</option>
    </select>
</form>


<button type="button" onclick="window.location.href='Vue/signIn.jsp'">Sign In</button>
<button type="button" onclick="window.location.href='Vue/signUp.jsp'">Sign Up</button>
<button type="button" onclick="window.location.href='Vue/shoppingCart.jsp'">Shopping Cart</button>
<button type="button" onclick="window.location.href='Vue/addProduct.jsp'">add Product</button>

</div>




</body>
</html>
