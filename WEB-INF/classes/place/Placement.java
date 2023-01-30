package place;

import connection.BddObject;
import connection.ForeignKey;
import event.Evenement;
import zone.Zone;

public class Placement extends BddObject<Placement> {
    
/// FIELD
    @ForeignKey(column = "idEvenement", typeColumn = String.class)
    Evenement evenement;
    @ForeignKey(column = "idZone", typeColumn = String.class)
    Zone zone;
    @ForeignKey(column = "idPlace", typeColumn = String.class)
    Place place;
    boolean libre = true;
    boolean confirme = false;

/// GETTERS
    public void setEvenement(Evenement evenement) {
        this.evenement = evenement;
    }
    public void setEvenement(String idEvenement) {
        Evenement event = new Evenement();
        event.setIdEvenement(idEvenement);
        setEvenement(event);
    }
    public void setZone(Zone zone) {
        this.zone = zone;
    }
    public void setZone(String idZone) {
        Zone zone = new Zone();
        zone.setIdZone(idZone);
        setZone(zone);
    }
    public void setPlace(Place place) {
        this.place = place;
    }
    public void setPlace(String idPlace) {
        Place place = new Place();
        place.setIdPlace(idPlace);
        setPlace(place);
    }
    public void setLibre(boolean libre) {
        this.libre = libre;
    }
    public void setConfirme(boolean confirme) {
        this.confirme = confirme;
    }

/// GETTERS
    public Evenement getEvenement() {
        return evenement;
    }
    public Place getPlace() {
        return place;
    }
    public Zone getZone() {
        return zone;
    }
    public boolean getConfirme() {
        return confirme;
    }
    public boolean getLibre() {
        return libre;
    }

/// CONSTRUCTORS
    public Placement() {
        setTable("placement");
    }

    public Placement(Evenement evenement, Zone zone, Place place) {
        this();
        setEvenement(evenement);
        setZone(zone);
        setPlace(place);
    }

    public Placement(String idEvenement, String idZone, String idPlace) {
        this();
        setEvenement(idEvenement);
        setZone(idZone);
        setPlace(idPlace);
    }
}
