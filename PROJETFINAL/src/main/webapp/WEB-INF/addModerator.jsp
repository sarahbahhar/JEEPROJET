<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>New Moderator</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/moderator-servlet" method="get">
    <label>Email</label>
    <input type="email" name="email" required><br>
    <label>Can Add Product</label><br>
    <select id="addP" name="pays">
        <option value="Yes">Yes</option>
        <option value="No">No</option>
    </select><br>
    <label>Can Delete Product</label><br>
    <select id="deleteP" name="pays">
        <option value="Yes">Yes</option>
        <option value="No">No</option>
    </select><br>
    <label>Max Product</label><br>
    <input type="number" name="maxProd" required><br>
    <input type="submit">
</form>
</body>
</html>
