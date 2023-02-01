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
    nombre INTEGER NOT NULL 0,
    date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE SEQUENCE seqEvenement 
START WITH 1
INCREMENT BY 1;

CREATE TABLE Zone (
    idZone VARCHAR PRIMARY KEY,
    nom VARCHAR UNIQUE,
    prix DOUBLE PRECISION NOT NULL DEFAULT 0
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
    idZone VARCHAR REFERENCES zone (idzone),
    idPlace VARCHAR REFERENCES place (idplace)
);

CREATE TABLE reservation (
    idReservation VARCHAR PRIMARY KEY,
    date DATE,
    idClient VARCHAR REFERENCES client (idclient),
    idEvenement VARCHAR REFERENCES Evenement (idEvenement),
    limite TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
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

CREATE SEQUENCE seqPayement
START WITH 1
INCREMENT BY 1;

CREATE TABLE payement (
    idPayement VARCHAR PRIMARY KEY,
    idClient VARCHAR REFERENCES client (idclient),
    idEvenement VARCHAR REFERENCES Evenement (idEvenement),
    idReservation VARCHAR REFERENCES reservation (idreservation),
    somme DOUBLE PRECISION NOT NULL DEFAULT 0,
    quantite INTEGER,
    simple BOOLEAN
);

CREATE VIEW place_reserve AS
SELECT rp.idplace, p2.numero, p.libre, p.confirme, p.idZone, p.idEvenement, rp.idReservation
FROM reservation_place rp
    JOIN reservation r ON rp.idReservation=r.idReservation
    JOIN placement p ON (p.idEvenement, p.idPlace) = (r.idEvenement, rp.idPlace)
    JOIN place p2 on p2.idplace = p.idplace;

CREATE OR REPLACE VIEW reservation_restant AS
SELECT *
FROM reservation
WHERE idReservation NOT IN (
    SELECT idReservation
    FROM payement
    UNION 
    SELECT idReservation
    FROM reservation_annule
);

CREATE TABLE reservation_annule (
    idReservation VARCHAR REFERENCES reservation (idReservation),
);

CREATE TABLE promotion (
    idPromotion VARCHAR PRIMARY KEY,
    debut TIMESTAMP,
    fin TIMESTAMP,
    pourcentage DOUBLE PRECISION NOT NULL DEFAULT 0,
    idZone VARCHAR REFERENCES zone (idzone),
    idEvenement VARCHAR REFERENCES Evenement (idEvenement)
);

CREATE SEQUENCE seqPromotion
START WITH 1
INCREMENT BY 1;

CREATE VIEW total AS
SELECT idEvenement, SUM(quantite) as total
FROM payement
    WHERE simple = true
GROUP BY idEvenement;