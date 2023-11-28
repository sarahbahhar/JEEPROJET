<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Demande de Modérateur</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/singIn.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
    <link rel="icon" type="image/png" href="./img/logo2.png">
    <%@ include file="header.jsp" %>
</head>
<body>
<div class="container" id="container">
    <div class="form-container sign-in-container">
        <form action="${pageContext.request.contextPath}/demande-moderator-servlet" method="post">
            <h1>Demande Moderateur</h1>
            <p>Veuillez expliquer pourquoi vous souhaitez devenir modérateur et comment vous envisagez de contribuer à Divan.</p>
            <textarea class="custom-textarea-g" id="description" name="description" placeholder="Description" required></textarea>
            <input type="hidden" name="email" value="${sessionScope.email}" />
            <button class="lien" type="submit" >Soumettre la Demande</button>
        </form>
    </div>
    <div class="overlay-container">
        <div class="overlay">
            <div class="overlay-panel overlay-right">
                <img src="${pageContext.request.contextPath}/img/demandeMod.jpg" alt="canape">
            </div>
        </div>
    </div>
</div>
</body>
<footer>
    <%@ include file="footer.jsp" %>
</footer>
</html>





