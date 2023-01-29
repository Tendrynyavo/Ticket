package payement;

import connection.BddObject;
import connection.ForeignKey;
import reservation.Reservation;

public class Payement extends BddObject<Payement> {
    
/// FIELD
    String idPayement;
    @ForeignKey(column = "idreservation", typeColumn = String.class)
    Reservation reservation;
    double prix;
    int quantite;

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
    public void setQuantite(int quantite) throws Exception {
        if (quantite < 0) throw new Exception("Quantite non valide");
        this.quantite = quantite;
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
    public int getQuantite() {
        return quantite;
    }
}
