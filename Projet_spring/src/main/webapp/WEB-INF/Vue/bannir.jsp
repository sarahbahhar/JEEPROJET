<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Bannir un Modérateur</title>
    <link rel="icon" type="image/png" href="./img/logo2.png">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/singIn.css">
</head>
<body>
<div class="container" id="container">
    <div class="form-container sign-in-container">
        <form action="${pageContext.request.contextPath}/bannir-moderator-servlet" method="post">
            <h1>Bannir un Modérateur</h1>
            <p>Email du modérateur : ${emailModerateur}</p>
            <input id="motifCourt" name="motifCourt" maxlength="100" placeholder="Mini motif" required>
            <textarea class="custom-textarea-g" id="motifLong" name="motifLong" placeholder="Motif" required></textarea>
            <input type="date" id="dateFinBan" name="dateFinBan" required>
            <input type="hidden" name="email" value="${emailModerateur}" />
            <button class="lien" type="submit" >Bannir</button>
        </form>
    </div>
    <div class="overlay-container">
        <div class="overlay">
            <div class="overlay-panel overlay-right">
                <img src="${pageContext.request.contextPath}/img/Banni.jpg" alt="canape">
            </div>
        </div>
    </div>
</div>
</body>
</html>
