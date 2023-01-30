package payement;

import java.sql.Date;

import connection.BddObject;
import event.Evenement;
import zone.Zone;

public class Promotion extends BddObject<Promotion> {

/// FIELD
    String idPromotion;
    Date debut;
    Date fin;
    double pourcentage;
    Zone zone;
    Evenement evenement;

/// SETTERS
    public void setZone(Zone zone) {
        this.zone = zone;
    }
    public void setEvenement(Evenement evenement) {
        this.evenement = evenement;
    }
    public void setIdPromotion(String idPromotion) {
        this.idPromotion = idPromotion;
    }
    public void setDebut(Date debut) {
        this.debut = debut;
    }
    public void setFin(Date fin) {
        this.fin = fin;
    }
    public void setPourcentage(double pourcentage) throws Exception {
        if (pourcentage < 0) throw new Exception("Pourcentage non valide");
        this.pourcentage = pourcentage;
    }

/// GETTERS
    public Zone getZone() {
        return zone;
    }
    public Evenement getEvenement() {
        return evenement;
    }
    public String getIdPromotion() {
        return idPromotion;
    }
    public Date getDebut() {
        return debut;
    }
    public Date getFin() {
        return fin;
    }
    public double getPourcentage() {
        return pourcentage;
    }
}