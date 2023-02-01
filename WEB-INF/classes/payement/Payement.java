package payement;

import client.Client;
import connection.BddObject;
import connection.ForeignKey;
import event.Evenement;
import place.Place;
import reservation.Reservation;

public class Payement extends BddObject<Payement> {
    
/// FIELD
    String idPayement;
    @ForeignKey(column = "idClient", typeColumn = String.class)
    Client client;
    @ForeignKey(column = "idEvenement", typeColumn = String.class)
    Evenement evenement;
    @ForeignKey(column = "idReservation", typeColumn = String.class)
    Reservation reservation;
    double somme;
    int quantite;
    boolean simple;
    Place[] places;

/// SETTERS
    public void setIdPayement(String idPayement) {
        this.idPayement = idPayement;
    }
    public void setSimple(boolean simple) {
        this.simple = simple;
    }
    public void setSomme(double prix) throws Exception {
        if (prix < 0) throw new Exception("Prix non valide");
        this.somme = prix;
    }
    public void setClient(Client client) {
        this.client = client;
    }
    public void setEvenement(Evenement evenement) {
        this.evenement = evenement;
    }
    public void setQuantite(int quantite) throws Exception {
        if (quantite < 0) throw new Exception("Quantite non valide");
        this.quantite = quantite;
    }
    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

/// GETTERS
    public String getIdPayement() {
        return idPayement;
    }
    public double getSomme() {
        return somme;
    }
    public boolean getSimple() {
        return simple;
    }
    public Client getClient() {
        return client;
    }
    public Evenement getEvenement() {
        return evenement;
    }
    public int getQuantite() {
        return quantite;
    }
    public Reservation getReservation() {
        return reservation;
    }

/// CONSTRUCTORS
    public Payement() {
        setTable("payement");
        setPrefix("PAY");
        setCountPK(7);
        setFunctionPK("nextval('seqpayement')");
    }

    public Payement(Reservation reservation, Client client, Evenement evenement, int quantite, boolean simple) throws Exception {
        this();
        setIdPayement(buildPrimaryKey(getPostgreSQL()));
        setReservation(reservation);
        setClient(client);
        setEvenement(evenement);
        setSomme(evenement.getPrix());
        setQuantite(quantite);
        setSimple(simple);
    }

    public Payement(Reservation reservation, Client client, Evenement evenement, double prix, int quantite, boolean simple) throws Exception {
        this();
        setIdPayement(buildPrimaryKey(getPostgreSQL()));
        setReservation(reservation);
        setClient(client);
        setEvenement(evenement);
        setSomme(prix * quantite);
        setQuantite(quantite);
        setSimple(simple);
    }
}