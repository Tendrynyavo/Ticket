package payement;

import connection.BddObject;
import connection.ForeignKey;
import reservation.Reservation;

public class Payement extends BddObject<Payement> {
    
/// FIELD
    String idPayement;
    @ForeignKey(column = "idReservation", typeColumn = String.class)
    Reservation reservation;
    double prix;

/// SETTERS
    public void setIdPayement(String idPayement) {
        this.idPayement = idPayement;
    }
    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }
    public void setPrix(double prix) throws Exception {
        if (prix < 0) throw new Exception("Prix non valide");
        this.prix = prix;
    }

/// GETTERS
    public String getIdPayement() {
        return idPayement;
    }
    public Reservation getReservation() {
        return reservation;
    }
    public double getPrix() {
        return prix;
    }

/// CONSTRUCTORS
    public Payement() {
        setTable("payement");
        setPrefix("PAY");
        setCountPK(7);
        setFunctionPK("nextval('seqpayement')");
    }

    public Payement(Reservation reservation, double prix) throws Exception {
        this();
        setIdPayement(buildPrimaryKey(getPostgreSQL()));
        setReservation(reservation);
        setPrix(prix);
    }
}
