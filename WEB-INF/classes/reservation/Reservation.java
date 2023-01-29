package reservation;

import java.sql.Date;

import client.Client;
import connection.BddObject;
import connection.ForeignKey;
import event.Evenement;
import place.Place;

public class Reservation extends BddObject<Reservation> {
    
/// FIELD
    String idReservation;
    Date date;
    Client client;
    @ForeignKey(column = "idevenement", typeColumn = String.class)
    Evenement evenement;
    Place[] places;

/// SETTERS
    public void setIdReservation(String idReservation) {
        this.idReservation = idReservation;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public void setClient(Client client) {
        this.client = client;
    }
    public void setEvenement(Evenement evenement) {
        this.evenement = evenement;
    }
    public void setPlaces(Place[] places) {
        this.places = places;
    }

/// GETTERS
    public String getIdReservation() {
        return idReservation;
    }
    public Date getDate() {
        return date;
    }
    public Client getClient() {
        return client;
    }
    public Place[] getPlaces() {
        return places;
    }
}
