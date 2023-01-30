package reservation;

import java.sql.Timestamp;

import connection.BddObject;
import connection.ForeignKey;

public class Annulation extends BddObject<Annulation> {
    
    @ForeignKey(column = "idReservation", typeColumn = String.class)
    Reservation reservation;

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public Annulation() {
        setTable("reservation_annule");
    }

    public Annulation(Reservation reservation) {
        this();
        setReservation(reservation);
    }
}
