package zone;

import connection.BddObject;

public class Place extends BddObject<Place> {
    
/// FIELD
    String idPlace;
    String numero;
    boolean libre;
    boolean confirme;
    Zone zone;

/// SETTERS
    public void setIdPlace(String idPlace) {
        this.idPlace = idPlace;
    }
    public void setNumero(String numero) {
        this.numero = numero;
    }
    public void setLibre(boolean libre) {
        this.libre = libre;
    }
    public void setConfirme(boolean confirme) {
        this.confirme = confirme;
    }
    public void setZone(Zone zone) {
        this.zone = zone;
    }

/// GETTERS
    public String getIdPlace() {
        return idPlace;
    }
    public String getNumero() {
        return numero;
    }
    public boolean isLibre() {
        return libre;
    }
    public boolean isConfirme() {
        return confirme;
    }
    public Zone getZone() {
        return zone;
    }
}
