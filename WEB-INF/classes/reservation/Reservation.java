package reservation;

import java.sql.Connection;
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
    @ForeignKey(column = "idClient", typeColumn = String.class)
    Client client;
    @ForeignKey(column = "idEvenement", typeColumn = String.class)
    Evenement evenement;
    Place[] places;

/// SETTERS
    public void setIdReservation(String idReservation) {
        this.idReservation = idReservation;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public void setDate(String date) throws Exception {
        setDate(Date.valueOf(date));
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
    public Evenement getEvenement() {
        return evenement;
    }

/// CONSTRUCTORS
    public Reservation() throws Exception {
        setTable("reservation");
        setCountPK(7);
        setFunctionPK("nextval('seqreservation')");
        setPrefix("RES");
    }

    public Reservation(String date, Client client, Evenement evenement, Place[] places) throws Exception {
        this();
        setIdReservation(buildPrimaryKey(getPostgreSQL()));
        setDate(date);
        setClient(client);
        setEvenement(evenement);
        setPlaces(places);
    }

    public void insert() throws Exception {
        Connection connection = null;
        try {
            connection = BddObject.getPostgreSQL();
            this.insert(connection);
            for (Place place : places) {
                place.reserver(connection);
                new PlaceReserve(this, place).insert(connection);
            }
            connection.commit();
        } catch (Exception e) {
            if (connection != null) connection.rollback();
            throw e;
        } finally {
            if (connection != null) connection.close();
        }
    }
}