package event;

import connection.BddObject;
import zone.Zone;

public class Evenement extends BddObject<Evenement> {
    
/// FIELD
    String idEvenement;
    String nom;
    Zone[] zones;

/// SETTERS
    public void setIdEvenement(String idEvenement) {
        this.idEvenement = idEvenement;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public void setZones(Zone[] zones) {
        this.zones = zones;
    }

/// GETTERS
    public String getIdEvenement() {
        return idEvenement;
    }
    public String getNom() {
        return nom;
    }
    public Zone[] getZones() {
        return zones;
    }

/// CONSTRUCTORS
    public Evenement() {
        setTable("evenement");
        setCountPK(7);
        setPrefix("EVE");
        setFunctionPK("nexval('seqEvenement')");
    }
}
