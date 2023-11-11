<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <link rel="shortcut icon" href="<%=request.getContextPath()%>/img/logo_onglet.ico" type="image/x-icon">
</head>
<body>
<jsp:include page="/header.jsp" />

<c:if test="${!empty sessionScope.firstName && !empty sessionScope.lastName}">
    <div><h1>Bienvenue ${sessionScope.firstName} ${sessionScope.lastName}</h1></div>
</c:if>



<div>
<a href=<%=request.getContextPath()%>>Home</a>


<form>
    <label for="menu">Admin Menu</label>
    <select id="menu" onchange="window.location.href = this.value;">
        <option>-----------</option>
        <option value="WEB-INF/Vue/clientList.jsp">Client List</option>
        <option value="WEB-INF/Vue/productList.jsp">Product List</option>
        <option value="WEB-INF/Vue/moderatorList.jsp">Moderator List</option>
    </select>
</form>


<button type="button" onclick="window.location.href='WEB-INF/Vue/signIn.jsp'">Sign In</button>
<button type="button" onclick="window.location.href='WEB-INF/Vue/signUp.jsp'">Sign Up</button>
<button type="button" onclick="window.location.href='WEB-INF/Vue/shoppingCart.jsp'">Shopping Cart</button>
<button type="button" onclick="window.location.href='WEB-INF/Vue/addProduct.jsp'">add Product</button>
<button type="button" onclick="window.location.href='WEB-INF/Vue/formAddModerator.jsp'">add Moderator</button>
<button type="button" onclick="window.location.href='WEB-INF/Vue/addProduct.jsp'">add Product</button>
<button type="button" onclick="window.location.href='WEB-INF/Vue/myProfile.jsp'">My Profile</button>


</div>




</body>
</html>
