<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Bannir un Modérateur</title>
    <link rel="stylesheet" href="./css/style.css">
    <link rel="icon" type="image/png" href="./img/logo2.png">
</head>
<body>

<div style="width: 40%; margin: auto;">
    <h1>Bannir un Modérateur</h1>
    <p>Email du modérateur : ${emailModerateur}</p>
    <fieldset>
        <legend>Informations sur le Bannissement</legend>
        <form id="bannir-moderateur-form" action="${pageContext.request.contextPath}/bannir-moderator-servlet" method="post">
            <label for="motifCourt">Motif du Bannissement (court) :</label>
            <input style="width: 100%;" id="motifCourt" name="motifCourt" maxlength="100" required>

            <label for="motifLong">Motif du Bannissement (détaillé) :</label>
            <textarea style ="width: 100%; height: 150px;" id="motifLong" name="motifLong" required></textarea><br>

            <label for="dateFinBan">Date de Fin de Bannissement :</label>
            <input type="date" id="dateFinBan" name="dateFinBan" required><br>

            <input type="hidden" name="email" value="${emailModerateur}" />
        </form>
    </fieldset>

    <div style="display: flex; justify-content: space-between; margin-top: 20px;">
        <form action="${pageContext.request.contextPath}/moderator-servlet" method="get">
            <button class="bouton-golden" type="submit">Retour</button>
        </form>
        <button class="bouton-golden" type="submit" form="bannir-moderateur-form">Bannir</button>
    </div>
</div>

</body>
</html>
