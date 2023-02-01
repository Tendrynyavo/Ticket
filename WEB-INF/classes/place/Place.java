package place;

import java.sql.Connection;
import connection.BddObject;
import connection.ForeignKey;
import event.Evenement;
import reservation.Reservation;
import zone.Zone;

public class Place extends BddObject<Place> {
    
/// FIELD
    String idPlace;
    String numero;
    boolean libre;
    boolean confirme;
    @ForeignKey(column = "idZone", typeColumn = String.class)
    Zone zone;
    @ForeignKey(column = "idEvenement", typeColumn = String.class)
    Evenement evenement;
    @ForeignKey(column = "idReservation", typeColumn = String.class)
    Reservation reservation;

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
    public void setEvenement(Evenement evenement) {
        this.evenement = evenement;
    }
    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

/// GETTERS
    public Evenement getEvenement() {
        return evenement;
    }
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
    public Reservation getReservation() {
        return reservation;
    }
    public String getColor() {
        return (this.isConfirme()) ? "danger" : (!this.isLibre()) ? "success" : "black-50";
    }

/// CONSTRUCTORS
    public Place() {
        setTable("place");
        setCountPK(7);
        setPrefix("PLA");
        setFunctionPK("nextval('seqplace')");
    }

    public Place(String numero) throws Exception {
        this();
        setIdPlace(buildPrimaryKey(getPostgreSQL()));
        setNumero(numero);
    }

/// FUNCTIONS
    public static Place[] getAllPlaces() throws Exception {
        return new Place().getData(getPostgreSQL(), null);
    }

    public void reserver(Connection connection) throws Exception {
        String table = getTable();
        this.setLibre(false);
        this.setTable("placement");
        this.update(new String[] {"libre"}, new Boolean[] {false}, "idPlace='"+this.getIdPlace()+"' AND idEvenement='"+getEvenement().getIdEvenement()+"'", connection);
        this.setTable(table);
    }

    public void confirme(Connection connection) throws Exception {
        String table = getTable();
        this.setConfirme(true);
        this.setTable("placement");
        this.update(new String[] {"confirme"}, new Boolean[] {true}, "idPlace='"+this.getIdPlace()+"' AND idEvenement='"+getEvenement().getIdEvenement()+"'", connection);
        this.setTable(table);
    }

    public void annuler(Connection connection) throws Exception {
        String table = getTable();
        this.setConfirme(true);
        this.setTable("placement");
        this.update(new String[] {"libre"}, new Boolean[] {true}, "idPlace='"+this.getIdPlace()+"' AND idEvenement='"+getEvenement().getIdEvenement()+"'", connection);
        this.setTable(table);
    }
}