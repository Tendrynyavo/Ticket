package reservation;

import connection.BddObject;
import connection.ForeignKey;
import place.Place;

public class PlaceReserve extends BddObject<PlaceReserve> {
    
/// FIELD
    @ForeignKey(column = "idReservation", typeColumn = String.class)
    Reservation reservation;
    @ForeignKey(column = "idPlace", typeColumn = String.class)
    Place place;

/// SETTERS
    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }
    public void setPlace(Place place) {
        this.place = place;
    }

/// GETTERS
    public Reservation getReservation() {
        return reservation;
    }
    public Place getPlace() {
        return place;
    }

/// CONSTRUCTORS
    public PlaceReserve() {
        this.setTable("reservation_place");
    }
    public PlaceReserve(Reservation reservation, Place place) {
        this();
        setReservation(reservation);
        setPlace(place);
    }
}