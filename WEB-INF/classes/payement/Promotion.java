package payement;

import java.sql.Date;
import connection.BddObject;
import connection.ForeignKey;
import event.Evenement;
import zone.Zone;
import java.sql.Timestamp;

public class Promotion extends BddObject<Promotion> {

/// FIELD
    String idPromotion;
    Timestamp debut;
    Timestamp fin;
    double pourcentage;
    @ForeignKey(column = "idZone", typeColumn = String.class)
    Zone zone;
    @ForeignKey(column = "idEvenement", typeColumn = String.class)
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
    public void setDebut(Timestamp debut) {
        this.debut = debut;
    }
    public void setFin(Timestamp fin) {
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
    public Timestamp getDebut() {
        return debut;
    }
    public Timestamp getFin() {
        return fin;
    }
    public double getPourcentage() {
        return pourcentage;
    }

/// CONSTRUCTORS
    public Promotion() {
        setTable("promotion");
        setPrefix("PRO");
        setCountPK(7);
        setFunctionPK("nextval('seqpromotion')");
    }

    public Promotion(String debut, String fin, String pourcentage, String idZone, String idEvenement) throws Exception {
        this();
        setIdPromotion(buildPrimaryKey(getPostgreSQL()));
        setDebut(Timestamp.valueOf(debut));
        setFin(Timestamp.valueOf(fin));
        setPourcentage(Double.parseDouble(pourcentage));
        Zone zone = new Zone();
        zone.setIdZone(idZone);
        setZone(zone);
        Evenement evenement = new Evenement();
        evenement.setIdEvenement(idEvenement);
        setEvenement(evenement);
    }
}