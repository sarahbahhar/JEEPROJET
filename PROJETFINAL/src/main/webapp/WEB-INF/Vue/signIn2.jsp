
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/singIn.css">
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
</head>
<body>

<div class="container" id="container">
    <div class="form-container sign-up-container">
        <form method="POST" action="<%=request.getContextPath()%>/inscriptionServlet">
            <h1>Crée un Compte</h1>

            <input type="text" placeholder="Nom" id="nom" name="nom" required/>
            <input type="text" id="prenom" name="prenom" placeholder="Prénom" required>
            <input id="emailI" name="email"  type="email" placeholder="Email" required/>
            <input id="passwordI" name="password" type="password" placeholder="Mot de passe" required/>
            <button class="lien">Sign Up</button>
        </form>
    </div>
    <div class="form-container sign-in-container">
        <form action="<%=request.getContextPath()%>/login" method="post">
            <h1>Connexion</h1>
            <input id="email" name="email" type="email" placeholder="Email" required/>
            <input id="password" name="password" type="password" placeholder="Mot de passe" required/>
            <button class="lien">Connexion</button>
        </form>
    </div>

    <div class="mdpOublie">
        <form action="${pageContext.request.contextPath}/redirect-servlet" method="post">
            <input type="hidden" name="path" value="askMailForResetPassword.jsp" />
            <button type="submit" class="mdpOublieButton"><a>Mot de passe oublié ?</a></button>
        </form>
    </div>

    <div class="overlay-container">
        <div class="overlay">
            <div class="overlay-panel overlay-left">
                <h1>Content de te revoir!</h1>
                <p>Connecte toi</p>
                <button class="ghost" id="signIn">Sign In</button>
            </div>
            <div class="overlay-panel overlay-right">
                <h1>Hello !</h1>
                <p>Rejoins nous.</p>
                <button class="ghost" id="signUp">Sign Up</button>
            </div>
        </div>
    </div>
</div>
<script src="${pageContext.request.contextPath}/js/signIn.js"></script>
</body>
</html>
