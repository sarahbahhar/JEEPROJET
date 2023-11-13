USE projet_jee;

 

-- Supprimer les tables si elles existent
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
                        mot_de_passe VARCHAR(50) NOT NULL
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
-- Table Mod�rateur avec les droits int�gr�s
CREATE TABLE Moderateur (
                            email VARCHAR(100) PRIMARY KEY NOT NULL,
                            peut_ajouter_produit BOOLEAN NOT NULL DEFAULT TRUE,
                            peut_supprimer_produit BOOLEAN NOT NULL DEFAULT TRUE,
                            max_produits_ligne INT NOT NULL DEFAULT 10,
                            FOREIGN KEY (email) REFERENCES Compte(email)
);
-- Table Demande Moderateur avec les droits int�gr�s
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
                                nom VARCHAR(255) NOT NULL,
                                numero VARCHAR(255) NOT NULL,
                                date DATE NOT NULL,
                                cvv VARCHAR(3) NOT NULL,
                                email VARCHAR(100) NOT NULL PRIMARY KEY,
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
                          FOREIGN KEY (email) REFERENCES Compte(email),
                          FOREIGN KEY (email) REFERENCES Panier(email)
);



-- Table ProduitCommande
CREATE TABLE ProduitCommande (
                                 produit_id INT NOT NULL,
                                 commande_numero INT NOT NULL,
                                 quantite INT NOT NULL,
                                 FOREIGN KEY (produit_id) REFERENCES Produit(id),
                                 FOREIGN KEY (commande_numero) REFERENCES Commande(numero),
                                 PRIMARY KEY (produit_id, commande_numero)
);

CREATE TABLE ProduitPanier (

                               email VARCHAR(100) NOT NULL,
                               produit_id INT NOT NULL,
                               quantite INT NOT NULL,


                               FOREIGN KEY (produit_id) REFERENCES Produit(id),
                               FOREIGN KEY (email) REFERENCES Compte(email),
                               PRIMARY KEY (email, produit_id)

);



-- Ajout des comptes dans la table Compte
INSERT INTO Compte (email, mot_de_passe) VALUES ('abdellah.hassani@gmail.com', '123456');
INSERT INTO Compte (email, mot_de_passe) VALUES ('simon.ren@gmail.com', '123456');
INSERT INTO Compte (email, mot_de_passe) VALUES ('loucas.terchani@gmail.com', '123456');
INSERT INTO Compte (email, mot_de_passe) VALUES ('sarah.bahhar@gmail.com', '123456');

-- Ajout des informations du client dans la table Client
INSERT INTO Client (email, points_fidelite) VALUES ('abdellah.hassani@gmail.com', 0);

-- Ajout du mod�rateur dans la table Moderateur
INSERT INTO Admin (email) VALUES ('simon.ren@gmail.com');

-- Ajout des vendeurs dans la table Moderateur
INSERT INTO Moderateur (email, peut_ajouter_produit, peut_supprimer_produit, max_produits_ligne)
VALUES ('loucas.terchani@gmail.com', TRUE, FALSE, 50),
       ('sarah.bahhar@gmail.com', TRUE, TRUE, 100);

-- Ajout des informations suppl�mentaires dans la table infocompte
INSERT INTO infocompte (email, prenom, nom, dateNaissance, telephone, adresse, ville, codePostal, pays)
VALUES ('abdellah.hassani@gmail.com', 'Abdellah', 'Hassani', '2002-06-15', '0702030405', '123 Rue Fictive', 'Paris', 75001, 'France'),
       ('simon.ren@gmail.com', 'Simon', 'Ren', '2002-12-01', '0706070809', '456 Rue Imaginaire', 'Lyon', 69001, 'France'),
       ('loucas.terchani@gmail.com', 'Loucas', 'Terchani', '2002-03-20', '0708091011', '789 Rue Inexistante', 'Marseille', 13001, 'France'),
       ('sarah.bahhar@gmail.com', 'Sarah', 'Bahhar', '1985-08-25', '012131415', '101 Rue Invent�e', 'Toulouse', 31000, 'France');

-- Ajout de 5 produits vendus par des vendeurs diff�rents en une seule requ�te
INSERT INTO Produit (titre, nomImage, miniDescription, prix, description, stock, email)
VALUES
    ('Casque audio', 'casque.png', 'Casque audio de haute qualit�', 59.99, 'Ce casque audio de haute qualit� offre une exp�rience sonore exceptionnelle. Profitez de votre musique pr�f�r�e avec une clart� audio in�gal�e.', 100, 'loucas.terchani@gmail.com'),
    ('Casquette de baseball', 'casquette.jpg', 'Casquette tendance pour tous les styles', 19.99, 'La casquette de baseball parfaite pour compl�ter votre look. Disponible en plusieurs couleurs, elle est id�ale pour toutes les occasions.', 150, 'sarah.bahhar@gmail.com'),
    ('Chaussettes de sport', 'chaussette.jpg', 'Chaussettes confortables pour le sport', 9.99, 'Ces chaussettes de sport vous offrent un confort ultime pendant vos s�ances d entra�nement. Respirantes et durables, elles sont un choix id�al pour les athl�tes.', 200, 'loucas.terchani@gmail.com'),
    ('Claquettes de plage', 'claquette.jpg', 'Claquettes l�g�res pour l�t�', 14.99, 'Les claquettes de plage sont essentielles pour l�t�. L�g�res et confortables, elles sont parfaites pour se d�tendre � la plage ou � la piscine.', 250, 'sarah.bahhar@gmail.com'),
    ('Clavier m�canique', 'clavier.jpg', 'Clavier m�canique r�actif', 79.99, 'Ce clavier m�canique est con�u pour les joueurs exigeants. Avec des touches r�actives et un �clairage RGB personnalisable, il am�liorera votre exp�rience de jeu.', 300, 'loucas.terchani@gmail.com');

-- Ajout de 3 enregistrements dans la table ProduitCommande avec NULL comme commande_numero
INSERT INTO ProduitPanier (email, produit_id, quantite)
VALUES
    ("abdellah.hassani@gmail.com",1, 2),
    ("abdellah.hassani@gmail.com",2, 3),
    ("abdellah.hassani@gmail.com",3, 1);
