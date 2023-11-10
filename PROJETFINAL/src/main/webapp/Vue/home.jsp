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
    <title>Home Marketplace</title>
</head>
<body>
<h1>MARKETPLACE</h1>
<a href="home.jsp">Home</a>
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
<button type="button" onclick="window.location.href='Vue/formAddModerator.jsp'">add Moderator</button>






</body>
</html>
