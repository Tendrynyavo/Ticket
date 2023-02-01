package reservation;

import java.sql.Connection;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import client.Client;
import connection.BddObject;
import connection.ForeignKey;
import event.Evenement;
import payement.Payement;
import place.Place;

public class Reservation extends BddObject<Reservation> {
    
/// FIELD
    String idReservation;
    Date date;
    @ForeignKey(column = "idClient", typeColumn = String.class)
    Client client;
    @ForeignKey(column = "idEvenement", typeColumn = String.class)
    Evenement evenement;
    Timestamp limite;
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
    public void setLimite(Timestamp limite) {
        this.limite = limite;
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
    public Place[] getPlaces() throws Exception {
        if (places == null) chargerPlace();
        return places;
    }
    public Evenement getEvenement() {
        return evenement;
    }
    public Timestamp getLimite() {
        return limite;
    }
    public String getPlacesString() throws Exception {
        String value = "";
        Place[] places = getPlaces();
        for (int i = 0; i < places.length; i++) {
            value += places[i].getNumero();
            if (i != places.length - 1) value += ";";
        }
        return value;
    }
    public double getPrix() throws Exception {
        double somme = 0;
        for (Place place : getPlaces()) {
            somme += place.getZone().getPrix();
        }
        return somme;
    }

/// CONSTRUCTORS
    public Reservation() throws Exception {
        setTable("reservation");
        setCountPK(7);
        setFunctionPK("nextval('seqreservation')");
        setPrefix("RES");
    }

    public Reservation(Client client, Evenement evenement, Place[] places) throws Exception {
        this();
        setIdReservation(buildPrimaryKey(getPostgreSQL()));
        setDate(new Date(System.currentTimeMillis()));
        setClient(client);
        setEvenement(evenement);
        setPlaces(places);
        setLimite(getTime(30));
    }

    public Reservation(Client client, Evenement evenement) throws Exception {
        this();
        setIdReservation(buildPrimaryKey(getPostgreSQL()));
        setDate(new Date(System.currentTimeMillis()));
        setClient(client);
        setEvenement(evenement);
        setLimite(getTime(30));
    }

    public Timestamp getTime(int duree) throws Exception {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(timestamp);
        calendar.add(Calendar.MINUTE, duree);
        return new Timestamp(calendar.getTime().getTime());
    }

    public void insert() throws Exception {
        Connection connection = null;
        try {
            connection = BddObject.getPostgreSQL();
            this.insert(connection);
            for (Place place : places) {
                place.reserver(connection);
                new PlaceReserve(this, place, getEvenement()).insert(connection);
            }
            connection.commit();
        } catch (Exception e) {
            if (connection != null) connection.rollback();
            throw e;
        } finally {
            if (connection != null) connection.close();
        }
    }

    public void confirme() throws Exception {
        Connection connection = null;
        try {
            connection = BddObject.getPostgreSQL();
            for (Place place : getPlaces()) {
                place.confirme(connection);
            }
            Payement payement = new Payement(this, getClient(), getEvenement(), getPrix(), getPlaces().length, false);
            payement.insert(connection);
            connection.commit();
        } catch (Exception e) {
            if (connection != null) connection.rollback();
            throw e;
        } finally {
            if (connection != null) connection.close();
        }
    }

    public void annuler() throws Exception {
        Connection connection = null;
        try {
            connection = BddObject.getPostgreSQL();
            for (Place place : getPlaces()) {
                place.annuler(connection);
            }
            Annulation annulation = new Annulation(this);
            annulation.insert(connection);
            connection.commit();
        } catch (Exception e) {
            if (connection != null) connection.rollback();
            throw e;
        } finally {
            if (connection != null) connection.close();
        }
    }

    public void chargerPlace() throws Exception {
        Place place = new Place();
        place.setTable("place_reserve");
        place.setReservation(this);
        place.setEvenement(getEvenement());
        Place[] places = place.getData(getPostgreSQL(), null, "reservation", "evenement");
        for (Place p : places) {
            p.getZone().setEvenement(getEvenement());
        }
        setPlaces(places);        
    }

    public static Reservation getReservationById(String id) throws Exception {
        Reservation reservation = new Reservation();
        reservation.setIdReservation(id);
        return reservation.getData(getPostgreSQL(), null, "idReservation")[0];
    }
}