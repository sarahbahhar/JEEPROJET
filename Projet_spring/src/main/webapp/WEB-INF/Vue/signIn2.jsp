
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/singIn.css">
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
</head>
<body>


    <div class="form-container sign-in-container">
        <form action="<%=request.getContextPath()%>/inscriptionController" method="post">
            <h1>Connexion</h1>
            <input id="email" name="email" type="email" placeholder="Email" required/>
            <input id="password" name="password" type="password" placeholder="Mot de passe" required/>
            <button class="lien">Connexion</button>
        </form>
    </div>



</body>
</html>
