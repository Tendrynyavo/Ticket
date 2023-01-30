CREATE TABLE Client (
    idClient VARCHAR PRIMARY KEY,
    nom VARCHAR
);

CREATE SEQUENCE seqClient 
START WITH 1
INCREMENT BY 1;

CREATE TABLE Evenement (
    idEvenement VARCHAR PRIMARY KEY,
    nom VARCHAR,
    nombre INTEGER
);

CREATE SEQUENCE seqEvenement 
START WITH 1
INCREMENT BY 1;

CREATE TABLE Zone (
    idZone VARCHAR PRIMARY KEY,
    nom VARCHAR UNIQUE,
    prix DOUBLE PRECISION
);

CREATE SEQUENCE seqZone
START WITH 1
INCREMENT BY 1;

CREATE TABLE Place (
    idPlace VARCHAR PRIMARY KEY,
    numero VARCHAR UNIQUE
);

CREATE SEQUENCE seqPlace
START WITH 1
INCREMENT BY 1;

CREATE TABLE placement (
    idEvenement VARCHAR REFERENCES Evenement (idEvenement),
    idZone VARCHAR  REFERENCES zone (idzone),
    idPlace VARCHAR REFERENCES place (idplace)
);

CREATE TABLE reservation (
    idReservation VARCHAR PRIMARY KEY,
    date DATE,
    idClient VARCHAR REFERENCES client (idclient),
    idEvenement VARCHAR REFERENCES Evenement (idEvenement)
);

CREATE SEQUENCE seqReservation
START WITH 1
INCREMENT BY 1;

CREATE TABLE reservation_place (
    idReservation VARCHAR REFERENCES reservation (idReservation),
    idPlace VARCHAR REFERENCES place (idPlace),
    idEvenement VARCHAR REFERENCES Evenement (idEvenement)
);

CREATE VIEW zone_event AS 
SELECT DISTINCT p.idZone, nom, prix, p.idEvenement
FROM placement p
    JOIN zone z ON p.idZone = z.idZone;

CREATE VIEW place_zone AS
SELECT p.idplace, p.numero, pla.libre, pla.confirme, pla.idZone, pla.idEvenement
FROM placement pla
    JOIN place p ON p.idPlace = pla.idPlace;