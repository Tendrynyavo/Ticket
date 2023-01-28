package zone;

import connection.BddObject;
import payement.Promotion;

public class Zone extends BddObject<Zone> {
    
/// FIELD
    String idZone;
    String nom;
    double prix;
    Promotion promotion;
    Place[] places;

/// SETTERS
    public void setIdZone(String idZone) {
        this.idZone = idZone;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public void setPrix(double prix) throws Exception {
        if (prix < 0) throw new Exception("Prix non invalide");
        this.prix = prix;
    }
    public void setPromotion(Promotion promotion) {
        this.promotion = promotion;
    }
    public void setPlaces(Place[] places) {
        this.places = places;
    }

/// GETTERS 
    public String getIdZone() {
        return idZone;
    }
    public String getNom() {
        return nom;
    }
    public double getPrix() {
        return prix;
    }
    public Promotion getPromotion() {
        return promotion;
    }
    public Place[] getPlace() {
        return places;
    }
}
