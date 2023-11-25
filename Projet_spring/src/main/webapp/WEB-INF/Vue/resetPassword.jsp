<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Réinitialisation du Mot de Passe</title>
    <!-- Ajoutez ici votre CSS et autres en-têtes si nécessaire -->
    <script type="text/javascript">
        function validatePassword() {
            var newPassword = document.getElementById("newPassword").value;
            var confirmPassword = document.getElementById("confirmPassword").value;
            var error = document.getElementById("error");

            if (newPassword !== confirmPassword) {
                error.textContent = "Les mots de passe ne correspondent pas.";
                return false;
            } else {
                error.textContent = "";
                return true;
            }
        }
    </script>
</head>
<body>
<h2>Réinitialisation du Mot de Passe</h2>
<form action="${pageContext.request.contextPath}/reset-password-servlet" method="POST" onsubmit="return validatePassword()">

    <input type="hidden" name="email" value="${email}"/>
    <div>
        <label for="newPassword">Nouveau Mot de Passe:</label>
        <input type="password" id="newPassword" name="newPassword" required>
    </div>
    <div>
        <label for="confirmPassword">Confirmer le Nouveau Mot de Passe:</label>
        <input type="password" id="confirmPassword" name="confirmPassword" required>
    </div>
    <div id="error" style="color: red;"></div>
    <div>
        <button type="submit">Réinitialiser le Mot de Passe</button>
    </div>
</form>
</body>
</html>
