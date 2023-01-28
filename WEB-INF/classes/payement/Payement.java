package payement;

import connection.BddObject;

public class Payement extends BddObject<Payement> {
    
/// FIELD
    String idPayement;
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
    public void setPrix(double prix) {
        this.prix = prix;
    }
    public void setQuantite(int quantite) {
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
