<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Demande de Modérateur</title>
    <link rel="stylesheet" href="./css/style.css">
    <link rel="icon" type="image/png" href="./img/logo2.png">

</head>
<body>

<div style="width: 40%; margin: auto;">
    <h1>Demande Moderateur</h1>
        <fieldset>
            <legend>Informations</legend>
                <form id="demande-moderateur-form" action="${pageContext.request.contextPath}/demande-moderator-servlet" method="post">

                    <p>Veuillez expliquer pourquoi vous souhaitez devenir modérateur et comment vous envisagez de contribuer à Divan.</p>
                    <label for="description"></label>
                    <textarea style ="width: 100%;height: 300px; " id="description" name="description" required></textarea>


                    <input type="hidden" name="email" value="${sessionScope.email}" />

                </form>

        </fieldset>
    <div style="display: flex; justify-content: space-between; margin-top: 20px;">
        <form action="${pageContext.request.contextPath}/redirect-servlet" method="post">
            <input type="hidden" name="path" value="myProfile.jsp" />
            <button class="bouton-golden" type="submit">Retour</button>
        </form>
        <button class="bouton-golden" type="submit" form="demande-moderateur-form">Soumettre la Demande</button>
    </div>
    </div>
</body>
</html>





