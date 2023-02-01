package reservation;

import connection.BddObject;
import connection.ForeignKey;
import event.Evenement;
import place.Place;

public class PlaceReserve extends BddObject<PlaceReserve> {
    
/// FIELD
    @ForeignKey(column = "idReservation", typeColumn = String.class)
    Reservation reservation;
    @ForeignKey(column = "idPlace", typeColumn = String.class)
    Place place;
    @ForeignKey(column = "idEvenement", typeColumn = String.class)
    Evenement evenement;

/// SETTERS
    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }
    public void setPlace(Place place) {
        this.place = place;
    }
    public void setEvenement(Evenement evenement) {
        this.evenement = evenement;
    }

/// GETTERS
    public Reservation getReservation() {
        return reservation;
    }
    public Place getPlace() {
        return place;
    }
    public Evenement getEvenement() {
        return evenement;
    }

/// CONSTRUCTORS
    public PlaceReserve() {
        this.setTable("reservation_place");
    }
    public PlaceReserve(Reservation reservation, Place place, Evenement evenement) {
        this();
        setReservation(reservation);
        setPlace(place);
        setEvenement(evenement);
    }
}