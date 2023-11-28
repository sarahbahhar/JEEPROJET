<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Réinitialisation du mot de passe</title>
    <style>
        /* Style du bouton */
        .reset-button {
            padding: 10px 20px;
            background-color: #4CAF50; /* Couleur de fond */
            color: white; /* Couleur du texte */
            border: none;
            border-radius: 5px;
            cursor: pointer;
            text-decoration: none;
        }

        .reset-button:hover {
            background-color: #45a049; /* Couleur de fond au survol */
        }
    </style>
</head>
<body>
<h1>Réinitialisation du mot de passe</h1>
<p>Vous avez demandé une réinitialisation de mot de passe pour votre compte. Cliquez sur le bouton ci-dessous pour réinitialiser votre mot de passe :</p>

<!-- Bouton de réinitialisation -->
<a href="${resetLink}" class="reset-button">Réinitialiser le mot de passe</a>

<p>Si vous n'avez pas demandé cette réinitialisation, veuillez ignorer cet e-mail.</p>
<p>Cordialement,</p>
<p>L'équipe de Divan</p>
</body>
</html>
