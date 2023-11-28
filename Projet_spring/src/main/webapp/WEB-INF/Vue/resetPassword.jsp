<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Réinitialisation du Mot de Passe</title>
    <link rel="shortcut icon" href="<%=request.getContextPath()%>/img/logo_onglet.ico" type="image/x-icon">

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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/singIn.css">
</head>
<body>
    <div class="container" id="container">
        <div class="form-container sign-in-container">
            <form action="${pageContext.request.contextPath}/reset-password-servlet" method="POST" onsubmit="return validatePassword()">
                <h1>Réinitialisation du Mot de Passe</h1>
                <input type="hidden" name="email" value="${email}"/>
                <input type="password" id="newPassword" name="newPassword" placeholder="Nouveau mot de passe" required>
                <input type="password" id="confirmPassword" name="confirmPassword" placeholder="Confirmation" required>
                <div id="error" style="color: red;"></div>
                <button class="lien" type="submit" >Réinitialiser le Mot de Passe</button>
            </form>
        </div>
        <div class="overlay-container">
            <div class="overlay">
                <div class="overlay-panel overlay-right">
                    <img src="${pageContext.request.contextPath}/img/Rmdp.jpg" alt="canape">
                </div>
            </div>
        </div>
    </div>

</body>
</html>
