package client;

import java.util.ArrayList;

import connection.BddObject;
import payement.Reservation;

public class Client extends BddObject<Client> {
    
/// FIELD
    String idClient;
    String nom;
    ArrayList<Reservation> reservations;

/// SETTERS
    public void setIdClient(String idClient) {
        this.idClient = idClient;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public void setReservations(ArrayList<Reservation> reservations) {
        this.reservations = reservations;
    }

/// GETTERS
    public String getIdClient() {
        return idClient;
    }
    public String getNom() {
        return nom;
    }
    public ArrayList<Reservation> getReservations() {
        return reservations;
    }
}
