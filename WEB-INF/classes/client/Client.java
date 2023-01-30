package client;

import java.sql.Timestamp;
import java.util.ArrayList;

import connection.BddObject;
import event.Evenement;
import place.Place;
import reservation.Reservation;

public class Client extends BddObject<Client> {
    
/// FIELD
    String idClient;
    String nom;
    Evenement evenement;
    Reservation[] reservations;

/// SETTERS
    public void setEvenement(Evenement evenement) {
        this.evenement = evenement;
    }
    public void setIdClient(String idClient) {
        this.idClient = idClient;
    }
    public void setNom(String nom) throws Exception {
        // Regular expression to match valid names
        String nameRegex = "^[a-zA-Z\\s]*$";
        if (!nom.matches(nameRegex)) throw new Exception("Nom du Client invalide");
        this.nom = nom;
    }
    public void setReservations(Reservation[] reservations) {
        this.reservations = reservations;
    }

/// GETTERS
    public Evenement getEvenement() {
        return evenement;
    }
    public String getIdClient() {
        return idClient;
    }
    public String getNom() {
        return nom;
    }
    public Reservation[] getReservations() throws Exception {
        if (reservations == null) chargerReservations();
        return reservations;
    }

/// CONSTRUCTORS
    public Client() {
        setPrefix("CLI");
        setCountPK(7);
        setTable("client");
        setFunctionPK("nextval('seqClient')");
    }

    public Client(String nom) throws Exception {
        this();
        setIdClient(buildPrimaryKey(getPostgreSQL()));
        setNom(nom);
    }

/// FUNCTIONS
    public static Client[] getAllClients() throws Exception {
        return new Client().getData(getPostgreSQL(), null);
    }

    public static Client getClientById(String id) throws Exception {
        Client client = new Client();
        client.setIdClient(id);
        return client.getData(getPostgreSQL(), null, "idClient")[0];
    }

    public void reserver(String numeros) throws Exception {
        Place[] places = getEvenement().convertToPlace(numeros);
        Reservation reservation = new Reservation(this, getEvenement(), places);
        reservation.insert();
    }

    public void chargerReservations() throws Exception {
        Reservation reservation = new Reservation();
        reservation.setTable("reservation_restant");
        reservation.setClient(this);
        Reservation[] reservations = reservation.getData(getPostgreSQL(), null, "client");
        setReservations(reservations);
    }

    public void check() throws Exception {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        this.chargerReservations();
        Reservation[] reservations = getReservations();
        for (int i = 0; i < reservations.length; i++) {
            if (reservations[i].getLimite().compareTo(timestamp) < 0) {
                reservations[i].annuler();
            }
        }
    }
}