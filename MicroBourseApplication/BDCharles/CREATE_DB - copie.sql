--Supprimer les FK si au cas ou elles existent
ALTER TABLE ACTIONS DROP CONSTRAINT FK_ACTIONNAIRE_2;
ALTER TABLE ACTIONS DROP CONSTRAINT FK_ENTREPRISE;
ALTER TABLE CAPITAUXENTREPRISES DROP CONSTRAINT FK_MONNAIE;
ALTER TABLE CAPITAUXENTREPRISES DROP CONSTRAINT FK_ACTIONNAIRE;
ALTER TABLE OFFRES DROP CONSTRAINT FK_MONNAIES;
ALTER TABLE OFFRES DROP CONSTRAINT FK_ENTREPRISE_2;
ALTER TABLE OFFRES DROP CONSTRAINT FK_ACTIONNAIRE_OFFRE;
ALTER TABLE OFFRES DROP CONSTRAINT FK_ACTIONNAIRE_OP_IM;
ALTER TABLE PRODUCTEURCONSOMMATEURS DROP CONSTRAINT FK_ENTREPRISE_3;
ALTER TABLE CAPITAUXACTIONNAIRES DROP CONSTRAINT FK_MONNAIE_2;
ALTER TABLE CAPITAUXACTIONNAIRES DROP CONSTRAINT FK_ACTIONNAIRE_3;
ALTER TABLE HISTORIQUESCAPITAUX DROP CONSTRAINT FK_MONNAIE_3;
ALTER TABLE HISTORIQUESCAPITAUX DROP CONSTRAINT FK_HISTORIQUE_ENTRE;
ALTER TABLE HISTORIQUESMONNAIES DROP CONSTRAINT FK_MONNAIE_4;

--Supprimer les tables si elles existent dans la BD
DROP  TABLE  HISTORIQUESACTIONNAIRES;
DROP  TABLE  OFFRES;
DROP  TABLE  MONNAIES;
DROP  TABLE  CAPITAUXENTREPRISES;
DROP  TABLE  ACTIONS;
DROP  TABLE  ACTIONNAIRES;
DROP  TABLE  ENTREPRISES;
DROP  TABLE  PRODUCTEURCONSOMMATEURS;
DROP  TABLE  CAPITAUXACTIONNAIRES;
DROP  TABLE  HISTORIQUESENTREPRISES;
DROP  TABLE  HISTORIQUESCAPITAUX;
DROP  TABLE  HISTORIQUESMONNAIES;

--Création des tables

CREATE TABLE ACTIONS
(
id INTEGER PRIMARY KEY NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
quantite INTEGER NOT NULL
) ;
CREATE TABLE ACTIONNAIRES
(
id INTEGER PRIMARY KEY NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
NOM VARCHAR(50) NOT NULL,
CAPITAL DOUBLE NOT NULL
) ;
CREATE TABLE ENTREPRISES
(
id INTEGER PRIMARY KEY NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
NOM VARCHAR(50) NOT NULL,
CAPITAL DOUBLE NOT NULL,
QUANTITE_RESSOURCE INTEGER NOT NULL
) ;
CREATE TABLE OFFRES
(
id INTEGER PRIMARY KEY NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
QUANTITE INTEGER NOT NULL,
PRIX DOUBLE NOT NULL,
STATUT INTEGER NOT NULL,
OPERATIONS INTEGER NOT NULL,
DATE_ECHANGE DATE NOT NULL
) ;

CREATE TABLE PRODUCTEURCONSOMMATEURS
(
id INTEGER PRIMARY KEY NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
STATUT INTEGER NOT NULL,
FIABILITE INTEGER NOT NULL,
QUANTITE_RESSOURCE INTEGER NOT NULL
) ;

CREATE TABLE HISTORIQUESENTREPRISES
(
id INTEGER PRIMARY KEY NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
DATE_HISTO DATE NOT NULL,
QUANTITE_RESSOURCE INTEGER NOT NULL,
CAPITAL DOUBLE NOT NULL
) ;
CREATE TABLE HISTORIQUESACTIONNAIRES
(
id INTEGER PRIMARY KEY NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
CAPITAL DOUBLE NOT NULL,
DATE_HISTO DATE NOT NULL
) ;



--Ajout des contraintes FK

ALTER TABLE ACTIONS ADD CONSTRAINT FK_ACTIONNAIRE_2
Foreign Key (ID) REFERENCES ACTIONNAIRES (ID);

ALTER TABLE ACTIONS ADD CONSTRAINT FK_ENTREPRISE
Foreign Key (ID) REFERENCES ENTREPRISES (ID);

ALTER TABLE OFFRES ADD CONSTRAINT FK_ENTREPRISE_2
Foreign Key (ID) REFERENCES ENTREPRISES (ID);

ALTER TABLE OFFRES ADD CONSTRAINT FK_ACTIONNAIRE_OFFRE
Foreign Key (ID) REFERENCES ACTIONNAIRES (ID);

ALTER TABLE OFFRES ADD CONSTRAINT FK_ACTIONNAIRE_OP_IM
Foreign Key (ID) REFERENCES ACTIONNAIRES (ID);

ALTER TABLE PRODUCTEURCONSOMMATEURS ADD CONSTRAINT FK_ENTREPRISE_3
Foreign Key (ID) REFERENCES ENTREPRISES (ID);

ALTER TABLE HISTORIQUESACTIONNAIRES ADD CONSTRAINT FK_ACTIONNAIRE_3
Foreign Key (ID) REFERENCES ACTIONNAIRES (ID);

ALTER TABLE HISTORIQUESENTREPRISES ADD CONSTRAINT FK_HISTORIQUE_ENTRE
Foreign Key (ID) REFERENCES ENTREPRISES (ID);