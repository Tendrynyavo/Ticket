package payement;

import java.sql.Date;

import connection.BddObject;

public class Promotion extends BddObject<Promotion> {

/// FIELD
    String idPromotion;
    Date debut;
    Date fin;
    double pourcentage;

/// SETTERS
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