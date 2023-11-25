<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="Model.Moderateur" %>
<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Modifier droits</title>
    <link rel="shortcut icon" href="<%=request.getContextPath()%>/img/logo_onglet.ico" type="image/x-icon">
</head>
<body>
<h1>Edit the rights of this moderator</h1>


        <c:forEach items="${moderators}" var="moderator">
        <form action="${pageContext.request.contextPath}/edit-moderator-servlet" method="post">
            <table>
                <tr>
                    <th>Email</th>
                    <th>Can Add Product</th>
                    <th>Can Delete Product</th>
                    <th>Max Product</th>
                </tr> <tr>
                <td>${moderator.email}<input type="hidden" name="email" value=${moderator.email} /></td>
                <td>
                    <select id="addP" name="addP">
                        <option value="1" ${moderator.peutAjouterProduit==1 ? 'selected' : ''}>Yes</option>
                        <option value="0" ${moderator.peutAjouterProduit!=1 ? 'selected' : ''}>No</option>
                    </select>
                </td>
                <td>
                    <select id="deleteP" name="deleteP">
                        <option value="1" ${moderator.peutSupprimerProduit==1 ? 'selected' : ''}>Yes</option>
                        <option value="0" ${moderator.peutSupprimerProduit!=1 ? 'selected' : ''}>No</option>
                    </select>
                </td>
                <td>
                    <input type="number" id="maxProd" name="maxProd" value="${moderator.maxProduitsLigne}" />
                </td>
                <td><input type="submit" value="Soumettre"></td>
            </tr>
            </table>
        </form>
        </c:forEach>
<br>
<br>
</body>
</html>