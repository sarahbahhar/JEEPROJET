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
                            motifCourtBannissement VARCHAR(100) DEFAULT NULL,
                            motifLongBanissement TEXT DEFAULT NULL,
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
								id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
                                numero VARCHAR(16)  NOT NULL,
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
                         description TEXT  NOT NULL,
                         stock INT NOT NULL,
                         email VARCHAR(100) NOT NULL,
                         nomImage VARCHAR(250) NOT NULL,
                         nomImage2 VARCHAR(250) NOT NULL,
                         miniDescription VARCHAR(200)  NOT NULL,
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
INSERT INTO Compte (email, mot_de_passe) VALUES ('abdellah.hassani2002@gmail.com', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92');
INSERT INTO Compte (email, mot_de_passe) VALUES ('loucas.terchani@gmail.com', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92');
INSERT INTO Compte (email, mot_de_passe) VALUES ('sarah.bahhar@gmail.com', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92');



-- Ajout des informations du client dans la table Client
INSERT INTO Client (email, points_fidelite) VALUES
                                                ('abdellah.hassani@gmail.com', 0),
                                                ('loucas.terchani@gmail.com', 0),
                                                ('sarah.bahhar@gmail.com', 0);




-- Ajout du modérateur dans la table Moderateur
INSERT INTO Admin (email) VALUES ('abdellah.hassani2002@gmail.com');



-- Ajout des vendeurs dans la table Moderateur
INSERT INTO Moderateur (email, peut_ajouter_produit, peut_supprimer_produit, max_produits_ligne)
VALUES ('loucas.terchani@gmail.com', TRUE, FALSE, 50),
       ('sarah.bahhar@gmail.com', TRUE, TRUE, 2);



-- Ajout des informations supplémentaires dans la table infocompte
INSERT INTO infocompte (email, prenom, nom, dateNaissance, telephone, adresse, ville, codePostal, pays)
VALUES ('abdellah.hassani@gmail.com', 'Abdellah', 'Hassani', '2002-06-15', '0702030405', '123 Rue Fictive', 'Paris', 75001, 'France'),
       ('abdellah.hassani2002@gmail.com', 'Simon', 'Ren', '2002-12-01', '0706070809', '456 Rue Imaginaire', 'Lyon', 69001, 'France'),
       ('loucas.terchani@gmail.com', 'Loucas', 'Terchani', '2002-03-20', '0708091011', '789 Rue Inexistante', 'Marseille', 13001, 'France'),
       ('sarah.bahhar@gmail.com', 'Sarah', 'Bahhar', '1985-08-25', '012131415', '101 Rue Inventée', 'Toulouse', 31000, 'France');



INSERT INTO Produit (titre, nomImage,nomImage2, miniDescription, prix, description, stock, email,categorie)
VALUES
    ('Simone','canape2.jpg','canape22.png' , 'Un lin d exellence', 1999, 'Bienvenue dans le monde du raffinement naturel avec notre nouveau canapé en lin. Sa texture légère et son design élégant apportent une touche de fraîcheur à votre espace de vie. Doté d une assise confortable et d une esthétique contemporaine, ce canapé en lin crée un coin de détente accueillant. Avec son charme naturel et son allure moderne, ce canapé incarne le confort et l élégance pour des moments de relaxation incomparables dans votre intérieur.', 41, 'loucas.terchani@gmail.com','Lin'),
    ('Castafiore', 'canape3.jpg','canape33.png' , 'Un tissu haut de gamme', 999, 'Ce remarquable canapé en tissu incarne le confort et l élégance. Les coussins du dossier et des accoudoirs offrent un soutien optimal pour une détente absolue. Sa structure soigneusement définie et son design élevé du sol confèrent une allure raffinée et contemporaine. Doté d appuis-tête réglables et d une possibilité de personnalisation avec une variété de couleurs, ce modèle s adapte aussi bien aux intérieurs contemporains qu aux styles plus classiques, offrant une polyvalence et un confort inégalés pour votre espace de vie.', 12, 'sarah.bahhar@gmail.com','Tissu'),
    ('Louchino', 'canape5.jpg','canape55.png',  'Un confort naturel', 2999, 'Plongez dans le confort naturel avec notre canapé en lin. Son revêtement léger et respirant offre une sensation de fraîcheur, idéale pour les journées ensoleillées. Doté d un design épuré et d une texture naturelle, ce canapé en lin apporte une touche de décontraction élégante à votre intérieur. Profitez du mariage parfait entre confort et style avec ce canapé qui incarne le charme naturel pour des moments de détente inégalés.', 20, 'loucas.terchani@gmail.com','Lin'),
    ('Lukaš', 'canape66.jpg','canape6.png',  'Canape cuir d exellence', 1099, 'Ce majestueux canapé en cuir incarne l élégance et le confort suprêmes. Fabriqué à la main par des artisans experts, chaque détail respire l excellence. Son cuir souple et luxueux vous accueille avec une douceur enveloppante, offrant un équilibre parfait entre fermeté et souplesse pour un soutien optimal.', 15, 'loucas.terchani@gmail.com','Cuir'),
    ('Sartorano', 'canape7.jpg','canape77.png',  'Un canapé aux formes harmonieuses', 600, 'Divinement conçu, ce somptueux canapé en cuir incarne l élégance et le confort suprêmes. Fabriqué à la main par des artisans experts, chaque détail respire l excellence. Son cuir souple et luxueux accueille avec une douceur enveloppante, offrant un équilibre parfait entre fermeté et souplesse pour un soutien optimal. Sa conception en deux parties distinctes ajoute une dimension de polyvalence et de style à votre espace de vie.', 30, 'loucas.terchani@gmail.com','Cuir'),
    ('Benedello', 'canape8.jpg','canape88.png', 'Un canapé revêtu en cuir', 900, 'Ce canapé en cuir simple mais raffiné offre une élégance intemporelle et un confort exceptionnel. Les coussins du dossier sont généreusement hauts et confortables, accompagnés d appuis-têtes ajustables pour une expérience personnalisée. Les coussins d assise assurent une posture parfaite et un soutien optimal, complétant ainsi l ensemble pour une relaxation totale.', 20, 'loucas.terchani@gmail.com','Cuir'),
    ('Sarita', 'canape9.jpg','canape99.png', 'Conception minimaliste et moderne', 729, 'Ce canapé en cuir présente un design essentiel, caractérisé par des volumes bien définis et imposants. Les coussins des dossiers, hauts et confortables, intègrent des appuis-tête pour un soutien supplémentaire, tandis que les coussins des assises garantissent une posture parfaite et un soutien optimal. Sa conception épurée et ses proportions généreuses en font un choix élégant et confortable pour tout espace de vie.', 25, 'loucas.terchani@gmail.com','Cuir'),
    ('Mesolino', 'canape10.jpg','canape1010.png', 'Un design aux lignes carrées', 699, 'Ce canapé en cuir offre une option supplémentaire : une variante avec un accoudoir plus petit, réduisant son encombrement. Mais ce n est pas tout : avec une simple manipulation, ce canapé se métamorphose en un lit double confortable, équipé d un matelas de 17 cm de hauteur. Pratique et polyvalent, il conjugue habilement fonctionnalité et confort, offrant une solution astucieuse pour les espaces restreints.', 30, 'loucas.terchani@gmail.com','Cuir'),
    ('Badetto', 'canape11.jpg','canape1111.png', 'Relaxation incomparable', 1299, 'Ce canapé en cuir propose un confort absolu grâce à ses fonctionnalités avancées. Le mouvement relax électrique, l appui-tête à cliquet et une table amovible pratique en font un choix idéal pour tout moment de la journée. L accoudoir avec sa table amovible offre un grand compartiment de rangement, permettant de travailler, se détendre ou savourer un encas tout en profitant du confort à 360° du canapé. Sa polyvalence et ses fonctionnalités astucieuses en font un meuble aussi pratique que confortable pour votre espace de vie.', 7, 'loucas.terchani@gmail.com','Cuir'),
    ('Badolo', 'canape12.jpg','canape1212.png', 'Relaxation pratique', 999, 'Ce canapé en cuir allie confort et personnalisation pour répondre à vos besoins. Le rembourrage des accoudoirs, du dossier et du repose-pieds garantit un soutien optimal, vous offrant ainsi un confort ultime et une détente totale. De plus, il offre la possibilité de personnalisation en permettant le choix de la couleur de l insert de l accoudoir. Cette attention aux détails associée à son confort exceptionnel en font un choix remarquable pour votre espace de vie, adapté à vos préférences.', 11, 'loucas.terchani@gmail.com','Cuir'),
    ('Mosale', 'canape13.jpg','canape1313.png', 'Angle composable', 1900, 'Ce canapé en cuir est conçu pour offrir un confort supérieur et une esthétique soignée. Les dossiers sont équipés d appuis-tête utiles, inclinables pour offrir un soutien optimal, améliorant ainsi votre niveau de confort. Sa définition formelle soignée et sa structure élevée du sol confèrent un design raffiné et contemporain. Il s adapte parfaitement à des contextes tant contemporains que classiques, faisant de lui un modèle polyvalent pour compléter votre espace de vie avec élégance et confort.', 17, 'loucas.terchani@gmail.com','Cuir'),
    ('Cavarelli', 'canape14.jpg','canape1414.png' , 'Le canapé modulaire', 1299, 'Ce canapé en tissu modulaire permet d assembler une chaise longue, un pouf ou un fauteuil pour créer votre espace de confort sur mesure. Des appuie-têtes optionnels ajoutent un soutien supplémentaire à chaque module. Sa modularité et ses options d ajout en font un choix pratique pour personnaliser votre espace de vie.', 15, 'sarah.bahhar@gmail.com','Tissu'),
    ('Gameda', 'canape15.jpg','canape1515.png' , 'Le canapé en tissu 3D', 899, 'Ce canapé en tissu 3D offre une relaxation innovante avec un dossier rembourré pour un confort optimal. Gameda se démarque par son élégance contemporaine et une grande attention portée aux détails, fusionnant style et confort pour des sièges remarquables.', 41, 'sarah.bahhar@gmail.com','Tissu'),
    ('Cazena', 'canape16.jpg','canape1616.png' , 'Le tissu rouge vif', 749, 'Le canapé Cezena met en valeur les espaces modernes avec ses pieds élégants et sa structure surélevée. Son mécanisme de dossier pratique offre une adaptabilité unique, permettant de rapprocher ou d éloigner les dossiers pour des moments de conversation confortables', 25, 'sarah.bahhar@gmail.com','Tissu'),
    ('Lissano', 'canape17.jpg','canape1717.png' , 'Modulable et optimal', 699, 'Ce canapé vous offre une liberté créative avec sa gamme de couleurs et de textures variées, vous permettant de choisir entre des tons unis ou des compositions de teintes. Sa polyvalence modulaire vous donne la liberté de déplacer les dossiers et les sièges selon votre convenance, vous permettant ainsi de façonner à tout moment le canapé de vos rêves, alliant style et personnalisation pour s adapter parfaitement à votre espace de vie.', 39, 'sarah.bahhar@gmail.com','Tissu'),
    ('Montbello', 'canape18.jpg','canape1818.png' , 'Relaxation électrique', 999, 'Ce canapé propose un confort exceptionnel avec une assise longue et un mouvement de relaxation électrique pratique. Les coussins du dossier, hauts et conçus pour un repos optimal de la tête, intègrent une structure offrant un soutien supplémentaire. Cette attention portée aux détails, associée à une haute qualité, garantit une expérience de confort remarquable.', 37, 'sarah.bahhar@gmail.com','Tissu'),
    ('Tromelo', 'canape19.jpg','canape1919.png' , 'Classique et romantique', 899, 'Cet élégant modèle s adapte harmonieusement à tout style et espace, qu ils soient modernes ou classiques. Disponible en plusieurs dimensions, ce canapé peut être agrémenté d appuis-tête et de coussins très souples pour une personnalisation optimale, offrant ainsi une polyvalence qui s adapte à vos besoins spécifiques et à votre esthétique.', 70, 'sarah.bahhar@gmail.com','Tissu'),
    ('Vighizzollo', 'canape20.jpg','canape2020.png' , 'Angle aux lignes inimitables', 1199, 'Ce canapé propose des dossiers ergonomiques avec des cale-reins intégrés et des appuie-têtes, offrant un confort maximal. Avec ses généreux volumes, le Vighizzolo peut accueillir plusieurs personnes à la fois, s intégrant parfaitement dans des espaces modernes et contemporains. Son design attentif à l ergonomie en fait un choix idéal pour des moments de détente conviviaux.', 14, 'sarah.bahhar@gmail.com','Tissu'),
    ('Valtori', 'canape21.jpg','canape2121.png' , 'Réversible ultra moelleux', 899, 'Plongez dans le luxe et l élégance avec notre somptueux canapé en velours. Son revêtement doux au toucher offre un confort incomparable, tandis que son design raffiné apporte une touche sophistiquée à votre intérieur. Laissez-vous séduire par le mariage parfait entre confort et style.', 24, 'sarah.bahhar@gmail.com','Velour'),
    ('Arsenni', 'canape222.jpg','canape2222.png' , 'Un velour remarquable', 1999, 'Explorez le raffinement ultime avec notre deuxième canapé en velours. Doté d une allure moderne et d un revêtement somptueux, ce canapé incarne le confort absolu et le chic contemporain. Laissez-vous envelopper par son élégance et son charme intemporel pour sublimer votre espace de vie.', 12, 'sarah.bahhar@gmail.com','Velour'),
    ('Vauban', 'canape233.jpg','canape2323.png' , 'Le velour haut de gamme', 2499, 'Plongez dans le monde du luxe avec notre exceptionnel canapé en velours haut de gamme. Conçu pour les connaisseurs exigeants, ce modèle incarne l élégance sophistiquée et le confort suprême. Son revêtement en velours offre une expérience tactile inégalée, tandis que son design opulent ajoute une touche majestueuse à tout intérieur. Découvrez le summum du style et du raffinement avec ce canapé d exception.', 8, 'sarah.bahhar@gmail.com','Velour'),
    ('Mollina', 'canape244.jpg','canape2424.png' , 'Canape droit fixe', 1299, 'Redécouvrez le confort douillet avec notre canapé en velours. Son revêtement doux et accueillant vous invite à la détente, tandis que son design simple et moderne apporte une touche de chaleur à votre espace de vie. Confortable et abordable, ce canapé en velours est l allié parfait pour créer une atmosphère relaxante dans votre foyer.', 29, 'sarah.bahhar@gmail.com','Velour'),
    ('Mbappino', 'canape255.jpg','canape2525.png' , 'Simple et élegant', 1099, 'Explorez le charme décontracté avec notre canapé en velours. Son allure décontractée et son revêtement velouté vous offrent une expérience de détente incomparable. Avec un design contemporain et une sensation de confort, ce canapé en velours est parfait pour créer un espace accueillant et relaxant dans votre maison, sans compromis sur le style ni le budget.', 31, 'sarah.bahhar@gmail.com','Velour'),
    ('Kolimuani', 'canape266.jpg','canape2626.png' , 'Pour un confort parfait', 1299, 'Découvrez le confort moderne avec notre canapé en velours. Son revêtement doux et soyeux vous invite à vous détendre après une longue journée, tout en apportant une touche d élégance à votre espace de vie. Avec son design épuré et son prix abordable, ce canapé en velours est l ajout parfait pour créer un coin cosy où vous pourrez vous relaxer en toute simplicité.', 17, 'sarah.bahhar@gmail.com','Velour'),
    ('Donnaruma', 'canape277.jpg','canape2727.png' , 'Une place idéale', 599, 'Plongez dans le confort stylé avec notre dernier canapé en velours. Son revêtement luxueux et sa silhouette moderne offrent une combinaison parfaite entre élégance et décontraction. Profitez d un moment de détente tout en ajoutant une touche sophistiquée à votre espace de vie. Avec son design contemporain et son allure accueillante, ce canapé en velours est l essence même du confort abordable et du style intemporel.', 19, 'sarah.bahhar@gmail.com','Velour'),
    ('Enrique', 'canape288.jpg','canape2828.png' , 'Un velour envoutant', 1499, 'Explorez le monde du confort avec notre nouveau canapé en velours. Sa texture douce et son design élégant créent un coin de détente parfait dans votre maison. Avec un mélange parfait entre style et praticité, ce canapé en velours est une invitation à la relaxation sans compromettre votre budget. Ajoutez une touche de luxe abordable à votre espace de vie avec ce canapé à la fois accueillant et moderne.', 47, 'sarah.bahhar@gmail.com','Velour'),
    ('Kakuta', 'canape299.jpg','canape2929.png' , 'La pureté du lin', 2499, 'Bienvenue dans le monde du confort naturel avec notre canapé en lin. Son revêtement léger et respirant offre une sensation de fraîcheur et de détente, parfaite pour créer une atmosphère apaisante dans votre maison. Avec son design épuré et sa texture naturelle, ce canapé en lin incarne l élégance décontractée, offrant un style sophistiqué à votre espace de vie.', 27, 'sarah.bahhar@gmail.com','Lin'),
    ('Turbanc', 'canape30.jpg','canape3030.png' , 'Le banc parfait', 199, 'Découvrez l alliance parfaite entre fonctionnalité et style avec notre banc d intérieur en lin. Son design épuré et sa texture naturelle apportent une touche de charme à votre espace tout en offrant une assise confortable. Ce banc polyvalent est idéal pour créer un coin accueillant dans votre intérieur, combinant avec habileté praticité et esthétique pour une atmosphère chaleureuse et moderne.', 77, 'sarah.bahhar@gmail.com','Banc'),
    ('Pochino', 'canape311.jpg','canape3131.png' , 'Simple et élégant', 299, 'Découvrez l élégance discrète avec notre nouveau banc d intérieur en lin. Son design épuré et sa texture naturelle apportent une touche de sophistication à votre espace de vie. Avec une assise confortable et polyvalente, ce banc en lin est l ajout parfait pour créer un coin accueillant dans votre maison. Alliant avec subtilité fonctionnalité et esthétique, ce banc vous invite à vous détendre dans un cadre raffiné et moderne.', 84, 'sarah.bahhar@gmail.com','Banc'),
    ('Materazi', 'canape322.jpg','canape3232.png' , 'Utile et Pratique', 89, 'Découvrez le charme contemporain avec notre banc d intérieur. Son design élégant et ses matériaux de qualité offrent une esthétique moderne à votre espace. Avec une assise confortable et une structure robuste, ce banc est l ajout parfait pour créer un coin accueillant dans votre maison. Alliant style et fonctionnalité, ce banc d intérieur offre une touche sophistiquée à votre décoration tout en offrant un confort optimal pour des moments de détente.', 44, 'sarah.bahhar@gmail.com','Banc'),
    ('Ngoli', 'canape333.jpg','canape3333.png' , 'Avec rangements efficace', 59, 'Plongez dans le raffinement avec notre banc d intérieur sophistiqué. Son design moderne et sa construction solide ajoutent une touche d élégance à votre espace. Doté d une assise confortable et d une allure contemporaine, ce banc est l élément idéal pour créer un coin accueillant dans votre intérieur. Avec une combinaison parfaite entre style et praticité, ce banc d intérieur vous invite à vous détendre dans un cadre chic et confortable au quotidien.', 101, 'sarah.bahhar@gmail.com','Banc'),
    ('Giroadi', 'canape344.jpg','canape3434.png' , 'Simplement pur', 79, 'Explorez le confort et le style avec notre tout nouveau banc d intérieur. Son design contemporain et sa structure solide offrent une esthétique moderne à votre espace de vie. Avec une assise confortable et une conception fonctionnelle, ce banc est l ajout parfait pour créer un coin chaleureux dans votre maison. Offrant une alliance parfaite entre élégance et praticité, ce banc d intérieur vous invite à vous détendre et à profiter d un confort exceptionnel au quotidien.', 71, 'sarah.bahhar@gmail.com','Banc'),
    ('Furina', 'canape355.jpg','canape3535.png' , 'Une singularité originale', 89, 'Plongez dans le charme fonctionnel avec notre dernier banc d intérieur. Son design polyvalent et sa robustesse ajoutent une touche contemporaine à votre espace. Doté d une assise confortable et d une allure pratique, ce banc est l élément idéal pour créer un coin accueillant dans votre intérieur. Alliant avec brio esthétique et praticité, ce banc d intérieur vous invite à vous détendre et à profiter d un confort optimal au quotidien.', 44, 'sarah.bahhar@gmail.com','Banc'),
    ('Talibanc', 'canape366.jpg','canape3636.png' , 'Vintage foncé noyer', 229, 'Explorez le mariage parfait entre style et commodité avec notre banc d intérieur dernier cri. Son design contemporain et sa structure robuste apportent une esthétique moderne à votre espace. Doté d une assise confortable et polyvalente, ce banc est l ajout idéal pour créer un coin accueillant dans votre maison. Offrant une combinaison parfaite entre élégance et fonctionnalité, ce banc d intérieur vous invite à vous détendre et à profiter dun confort exceptionnel au quotidien.', 74, 'sarah.bahhar@gmail.com','Banc'),
    ('Abidjani', 'canape377.jpg','canape3737.png' , 'Blanc scandinave', 179, 'Plongez dans l élégance pratique avec notre tout nouveau banc d intérieur. Son design contemporain et sa solidité ajoutent une touche moderne à votre espace de vie. Avec une assise confortable et polyvalente, ce banc est l élément idéal pour créer un coin accueillant dans votre intérieur. Alliant avec habileté esthétique et fonctionnalité, ce banc d intérieur vous invite à vous détendre et à profiter d un confort optimal au quotidien.', 19, 'sarah.bahhar@gmail.com','Banc'),
    ('Abinlin', 'canape388.jpg','canape3838.png' , 'Le lin soyeux', 2499, 'Explorez le confort naturel avec notre canapé en lin. Son revêtement léger et respirant offre une sensation de fraîcheur, parfait pour les journées ensoleillées. Avec un design épuré et une texture naturelle, ce canapé en lin apporte une touche décontractée à votre intérieur. Profitez du mariage parfait entre confort et style avec ce canapé qui incarne le charme naturel pour des moments de détente inégalés.', 40, 'sarah.bahhar@gmail.com','Lin'),
    ('Verrati', 'canape399.jpg','canape3939.png' , 'Un confort parfait', 3499, 'Plongez dans l élégance discrète avec notre canapé en lin. Sa texture naturelle et son design épuré apportent une touche authentique à votre espace de vie. Doté d une assise confortable et d une esthétique subtile, ce canapé en lin crée un coin de détente accueillant. Avec son charme naturel et sa simplicité sophistiquée, ce canapé est l essence même du confort décontracté pour votre intérieur.', 18, 'sarah.bahhar@gmail.com','Lin'),
    ('Luccani', 'canape400.jpg','canape4040.png' , 'Le luxe dans un lin', 3899, 'Explorez le raffinement naturel avec notre nouveau canapé en lin. Sa texture légère et sa conception élégante apportent une touche de fraîcheur à votre espace de vie. Doté d une assise confortable et d une esthétique contemporaine, ce canapé en lin crée un coin de détente accueillant et stylé. Avec son charme naturel et son allure moderne, ce canapé incarne le confort et l élégance pour des moments de relaxation inégalés.', 7, 'sarah.bahhar@gmail.com','Lin'),
    ('Parfoto', 'canape411.jpg','canape4141.png' , 'L exellence en douceur', 4299, 'Plongez dans le confort délicat avec notre canapé en lin. Sa texture douce et son design épuré ajoutent une note de légèreté à votre intérieur. Doté d une assise accueillante et d une esthétique minimaliste, ce canapé en lin crée un espace de détente à la fois paisible et élégant. Avec son charme naturel et son style épuré, ce canapé offre une expérience de relaxation unique, alliant simplicité et confort pour votre foyer.', 9, 'sarah.bahhar@gmail.com','Lin'),
    ('Vitinia', 'canape422.jpg','canape4242.png' , 'Pour un confort certain', 3099, 'Explorez l authenticité décontractée avec notre dernier canapé en lin. Sa texture naturelle et son design épuré ajoutent une touche de simplicité à votre espace de vie. Doté d une assise confortable et d une esthétique naturelle, ce canapé en lin crée un espace de détente chaleureux. Avec son charme délicat et son allure accueillante, ce canapé incarne le confort décontracté pour des moments de relaxation paisibles dans votre intérieur.', 11, 'sarah.bahhar@gmail.com','Lin'),
    ('Pirlo', 'canape433.jpg','canape4343.png' , 'En famille ou entre amis', 1099, 'Explorez le confort en plein air avec notre canapé extérieur. Sa résistance aux intempéries et son design moderne apportent une touche élégante à votre espace extérieur. Doté d une assise confortable et d une esthétique durable, ce canapé offre un lieu de détente idéal pour votre patio ou jardin. Avec son allure accueillante et sa robustesse, ce canapé extérieur invite à profiter de moments de détente en plein air, alliant style et confort pour vos instants de relaxation en extérieur.', 15, 'sarah.bahhar@gmail.com','Exterieur'),
    ('Asensio', 'canape444.jpg','canape4444.png' , 'Un confort inégalable', 999, 'Découvrez le confort en plein air avec notre nouveau canapé extérieur. Son design moderne et sa robustesse apportent une touche contemporaine à votre espace extérieur. Doté d une assise confortable et d une esthétique résistante, ce canapé offre un lieu de détente parfait pour votre terrasse ou jardin. Avec son allure accueillante et sa durabilité, ce canapé extérieur invite à profiter de moments de relaxation en plein air dans un cadre élégant et confortable.', 15, 'sarah.bahhar@gmail.com','Exterieur'),
    ('Ugarte', 'canape455.jpg','canape4545.png' , 'Simple élegant', 899, 'Plongez dans le luxe décontracté avec notre canapé extérieur haut de gamme. Sa structure résistante et son design élégant apportent une touche sophistiquée à votre espace extérieur. Doté d une assise moelleuse et d une esthétique moderne, ce canapé offre un coin de détente somptueux pour vos moments en plein air. Profitez du mariage parfait entre confort et style avec ce canapé extérieur prestigieux.', 15, 'sarah.bahhar@gmail.com','Exterieur'),
    ('Nuno', 'canape466.jpg','canape4646.png' , 'La beauté pour dehors', 799, 'Découvrez l élégance contemporaine avec notre canapé extérieur en rotin. Son design tressé et sa résistance aux éléments extérieurs ajoutent une touche chic à votre espace de détente en plein air. Doté d une assise confortable et d une esthétique raffinée, ce canapé offre un cadre sophistiqué pour vos instants de relaxation à l extérieur.', 15, 'sarah.bahhar@gmail.com','Exterieur'),
    ('Navas', 'canape477.jpg','canape4747.png' , 'Pour profiter de l ete', 1199, 'Plongez dans le confort décontracté avec notre canapé extérieur en bois. Sa structure solide et son allure naturelle créent une ambiance chaleureuse sur votre patio ou dans votre jardin. Doté d une assise confortable et d une esthétique rustique, ce canapé invite à des moments détente dans un cadre authentique en plein air.', 15, 'sarah.bahhar@gmail.com','Exterieur'),
    ('Gonçalo', 'canape488.jpg','canape4848.png' , 'Un exterieur parfait', 1099, 'Explorez la modernité avec notre canapé extérieur minimaliste. Son design épuré et sa résistance aux intempéries offrent une esthétique contemporaine à votre espace extérieur. Doté d une assise confortable et d une esthétique fonctionnelle, ce canapé combine style et praticité pour des moments de détente en plein air.', 15, 'sarah.bahhar@gmail.com','Exterieur'),
    ('Riuzo', 'canape499.jpg','canape4949.png' , 'Ensoleillé chaleureux', 599, 'Explorez l authenticité décontractée avec notre dernier canapé en lin. Sa texture naturelle et son design épuré ajoutent une touche de simplicité à votre espace de vie. Doté d une assise confortable et d une esthétique naturelle, ce canapé en lin crée un espace de détente chaleureux. Avec son charme délicat et son allure accueillante, ce canapé incarne le confort décontracté pour des moments de relaxation paisibles dans votre intérieur.', 15, 'sarah.bahhar@gmail.com','Exterieur'),
    ('Locateli', 'canape500.jpg','canape5050.png' , 'Pour un bon exterieur', 799, 'Découvrez le confort tout-terrain avec notre canapé extérieur convertible. Sa polyvalence et sa résistance en font un choix idéal pour diverses configurations extérieures. Doté d une assise ajustable et d une esthétique moderne, ce canapé offre une solution flexible pour créer un espace détente personnalisé en extérieur.', 15, 'sarah.bahhar@gmail.com','Exterieur');



-- Ajout de 3 enregistrements dans la table ProduitCommande avec NULL comme commande_numero





-- Insérer la première commande
INSERT INTO Commande (numero,dateDePaiement, total, email)
VALUES (1,'2023-11-12', 99.99, 'abdellah.hassani@gmail.com'),
       (2,'2023-11-13', 149.99, 'abdellah.hassani@gmail.com');

INSERT INTO Token(email,token)
VALUES('abdellah.hassani@gmail.com','0'),
      ('sarah.bahhar@gmail.com','0'),
      ('loucas.terchani@gmail.com','0'),
      ('abdellah.hassani2002@gmail.com','0');



INSERT INTO ProduitCommande (idProduit,commande_numero, quantite,emailVendeur, titre, prix)
VALUES (1,1, 2,'sarah.bahhar@gmail.com', 'Casque audio', 59.99),
       (1,2, 3,'loucas.terchani@gmail.com', 'Casquette de baseball', 19.99);


INSERT INTO Panier (email, HT, TVA,TTC)
VALUES
    ("abdellah.hassani@gmail.com",0,0,0),
    ("loucas.terchani@gmail.com",0,0,0),
    ("sarah.bahhar@gmail.com",0,0,0);




