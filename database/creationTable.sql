USE projet_jee;







-- Supprimer les tables si elles existent
DROP TABLE IF EXISTS Token;
DROP TABLE IF EXISTS Commentaires;
DROP TABLE IF EXISTS DemandeModerateur;
DROP TABLE IF EXISTS ProduitCommande;
DROP TABLE IF EXISTS ProduitPanier;
DROP TABLE IF EXISTS Commande;
DROP TABLE IF EXISTS Panier;
DROP TABLE IF EXISTS Produit;
DROP TABLE IF EXISTS Moderateur;
DROP TABLE IF EXISTS Admin;
DROP TABLE IF EXISTS CompteBancaire;
DROP TABLE IF EXISTS Client;
DROP TABLE IF EXISTS InfoCompte;
DROP TABLE IF EXISTS Compte;







-- Table Compte
CREATE TABLE Compte (
                        email VARCHAR(100) PRIMARY KEY NOT NULL,
                        mot_de_passe VARCHAR(150) NOT NULL
);







CREATE TABLE infocompte (
                            email varchar(100) NOT NULL PRIMARY KEY,
                            prenom varchar(50) NOT NULL,
                            nom varchar(50) NOT NULL,
                            dateNaissance date NOT NULL,
                            telephone varchar(10) NOT NULL,
                            adresse varchar(250) NOT NULL,
                            ville varchar(50) NOT NULL,
                            codePostal int NOT NULL,
                            pays varchar(50) NOT NULL,
                            FOREIGN KEY (email) REFERENCES Compte(email)
);
-- Table Client
CREATE TABLE Client (
                        email VARCHAR(100) PRIMARY KEY NOT NULL,
                        points_fidelite INT NOT NULL DEFAULT 0,
                        FOREIGN KEY (email) REFERENCES Compte(email)
);
-- Table Modérateur avec les droits intégrés
CREATE TABLE Moderateur (
                            email VARCHAR(100) PRIMARY KEY NOT NULL,
                            peut_ajouter_produit BOOLEAN NOT NULL DEFAULT TRUE,
                            peut_supprimer_produit BOOLEAN NOT NULL DEFAULT TRUE,
                            max_produits_ligne INT NOT NULL DEFAULT 10,
                            nbBannissement INT NOT NULL DEFAULT 0,
                            dateBanni date,
                            FOREIGN KEY (email) REFERENCES Compte(email)
);
-- Table Demande Moderateur avec les droits intégrés
CREATE TABLE DemandeModerateur (
                                   email VARCHAR(100) PRIMARY KEY NOT NULL,
                                   message TEXT NOT NULL,
                                   FOREIGN KEY (email) REFERENCES Compte(email)
);
-- Table Admin
CREATE TABLE Admin (
                       email VARCHAR(100) PRIMARY KEY NOT NULL,
                       FOREIGN KEY (email) REFERENCES Compte(email)
);







-- Table CompteBancaire
CREATE TABLE CompteBancaire (
                                numero VARCHAR(16) PRIMARY KEY  NOT NULL,
                                nom VARCHAR(255) NOT NULL,
                                date VARCHAR(5) NOT NULL,
                                cvv VARCHAR(3) NOT NULL,
                                email VARCHAR(100) NOT NULL,
                                FOREIGN KEY (email) REFERENCES Client(email)
);








-- Table Produit
CREATE TABLE Produit (
                         id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
                         titre VARCHAR(255) NOT NULL,
                         description TEXT NOT NULL,
                         stock INT NOT NULL,
                         email VARCHAR(100) NOT NULL,
                         nomImage VARCHAR(250) NOT NULL,
                         miniDescription VARCHAR(200) NOT NULL,
                         prix decimal(10,2) NOT NULL,
                         categorie varchar(100),
                         FOREIGN KEY (email) REFERENCES Moderateur(email)
);








-- Table Panier
CREATE TABLE Panier (
                        email VARCHAR(100) PRIMARY KEY NOT NULL,
                        HT decimal(10,2) NOT NULL,
                        TVA decimal(10,2) NOT NULL,
                        TTC decimal(10,2) NOT NULL,
                        FOREIGN KEY (email) REFERENCES Compte(email)
);



-- Table Commande
CREATE TABLE Commande (
                          numero INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
                          dateDePaiement DATE NOT NULL,
                          total DECIMAL(10,2) NOT NULL,
                          email VARCHAR(100) NOT NULL,
                          FOREIGN KEY (email) REFERENCES Compte(email)
);

CREATE TABLE Commentaires (
                              id INT AUTO_INCREMENT PRIMARY KEY,
                              idProduit INT,
                              note INT NOT NULL CHECK (note BETWEEN 0 AND 5),
                              emailVendeur VARCHAR(100),
                              commentaire TEXT,
                              email VARCHAR(100) NOT NULL,
                              FOREIGN KEY (email) REFERENCES Compte(email)
);




-- Table ProduitCommande
CREATE TABLE ProduitCommande (
                                 id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
                                 idProduit INT NOT NULL,
                                 commande_numero INT NOT NULL,
                                 quantite INT NOT NULL,
                                 emailVendeur VARCHAR(100) ,
                                 titre VARCHAR(255) NOT NULL,
                                 prix decimal(10,2) NOT NULL,

                                 FOREIGN KEY (commande_numero) REFERENCES Commande(numero)
);

CREATE TABLE Produitpanier (



                               email VARCHAR(100) NOT NULL,
                               produit_id INT NOT NULL,
                               quantite INT NOT NULL,





                               FOREIGN KEY (produit_id) REFERENCES Produit(id),
                               FOREIGN KEY (email) REFERENCES Compte(email),
                               PRIMARY KEY (email, produit_id)



);
CREATE TABLE Token(
                      email VARCHAR(100) PRIMARY KEY NOT NULL,
                      token VARCHAR(50),

                      FOREIGN KEY (email) REFERENCES Compte(email)
);








-- Ajout des comptes dans la table Compte
INSERT INTO Compte (email, mot_de_passe) VALUES ('abdellah.hassani@gmail.com', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92');
INSERT INTO Compte (email, mot_de_passe) VALUES ('simon.ren@gmail.com', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92');
INSERT INTO Compte (email, mot_de_passe) VALUES ('loucas.terchani@gmail.com', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92');
INSERT INTO Compte (email, mot_de_passe) VALUES ('sarah.bahhar@gmail.com', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92');



-- Ajout des informations du client dans la table Client
INSERT INTO Client (email, points_fidelite) VALUES
                                                ('abdellah.hassani@gmail.com', 0),
                                                ('loucas.terchani@gmail.com', 0),
                                                ('sarah.bahhar@gmail.com', 0);




-- Ajout du modérateur dans la table Moderateur
INSERT INTO Admin (email) VALUES ('simon.ren@gmail.com');



-- Ajout des vendeurs dans la table Moderateur
INSERT INTO Moderateur (email, peut_ajouter_produit, peut_supprimer_produit, max_produits_ligne)
VALUES ('loucas.terchani@gmail.com', TRUE, FALSE, 50),
       ('sarah.bahhar@gmail.com', TRUE, TRUE, 100);



-- Ajout des informations supplémentaires dans la table infocompte
INSERT INTO infocompte (email, prenom, nom, dateNaissance, telephone, adresse, ville, codePostal, pays)
VALUES ('abdellah.hassani@gmail.com', 'Abdellah', 'Hassani', '2002-06-15', '0702030405', '123 Rue Fictive', 'Paris', 75001, 'France'),
       ('simon.ren@gmail.com', 'Simon', 'Ren', '2002-12-01', '0706070809', '456 Rue Imaginaire', 'Lyon', 69001, 'France'),
       ('loucas.terchani@gmail.com', 'Loucas', 'Terchani', '2002-03-20', '0708091011', '789 Rue Inexistante', 'Marseille', 13001, 'France'),
       ('sarah.bahhar@gmail.com', 'Sarah', 'Bahhar', '1985-08-25', '012131415', '101 Rue Inventée', 'Toulouse', 31000, 'France');



-- Ajout de 5 produits vendus par des vendeurs différents en une seule requête
INSERT INTO Produit (titre, nomImage, miniDescription, prix, description, stock, email)
VALUES
    ('Casque audio', 'casque.png', 'Casque audio de haute qualité', 59.99, 'Ce casque audio de haute qualité offre une expérience sonore exceptionnelle. Profitez de votre musique préférée avec une clarté audio inégalée.', 100, 'loucas.terchani@gmail.com'),
    ('Casquette de baseball', 'casquette.jpg', 'Casquette tendance pour tous les styles', 19.99, 'La casquette de baseball parfaite pour compléter votre look. Disponible en plusieurs couleurs, elle est idéale pour toutes les occasions.', 150, 'sarah.bahhar@gmail.com'),
    ('Chaussettes de sport', 'chaussette.jpg', 'Chaussettes confortables pour le sport', 9.99, 'Ces chaussettes de sport vous offrent un confort ultime pendant vos séances d entraînement. Respirantes et durables, elles sont un choix idéal pour les athlètes.', 200, 'loucas.terchani@gmail.com'),
    ('Claquettes de plage', 'claquette.jpg', 'Claquettes légères pour lété', 14.99, 'Les claquettes de plage sont essentielles pour lété. Légères et confortables, elles sont parfaites pour se détendre à la plage ou à la piscine.', 250, 'sarah.bahhar@gmail.com'),
    ('Clavier mécanique', 'clavier.jpg', 'Clavier mécanique réactif', 79.99, 'Ce clavier mécanique est conçu pour les joueurs exigeants. Avec des touches réactives et un éclairage RGB personnalisable, il améliorera votre expérience de jeu.', 300, 'loucas.terchani@gmail.com');




-- Ajout de 3 enregistrements dans la table ProduitCommande avec NULL comme commande_numero





-- Insérer la première commande
INSERT INTO Commande (numero,dateDePaiement, total, email)
VALUES (1,'2023-11-12', 99.99, 'abdellah.hassani@gmail.com'),
       (2,'2023-11-13', 149.99, 'abdellah.hassani@gmail.com');

INSERT INTO Token(email,token)
VALUES('abdellah.hassani@gmail.com','0');



INSERT INTO ProduitCommande (idProduit,commande_numero, quantite,emailVendeur, titre, prix)
VALUES (1,1, 2,'sarah.bahhar@gmail.com', 'Casque audio', 59.99),
       (1,2, 3,'loucas.terchani@gmail.com', 'Casquette de baseball', 19.99);


INSERT INTO Panier (email, HT, TVA,TTC)
VALUES
    ("abdellah.hassani@gmail.com",0,0,0),
    ("loucas.terchani@gmail.com",0,0,0),
    ("sarah.bahhar@gmail.com",0,0,0);




