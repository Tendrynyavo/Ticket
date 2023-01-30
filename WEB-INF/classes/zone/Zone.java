package zone;

import connection.BddObject;
import connection.ForeignKey;
import event.Evenement;
import payement.Promotion;
import place.Place;

public class Zone extends BddObject<Zone> {
    
/// FIELD
    String idZone;
    String nom;
    double prix;
    @ForeignKey(column = "idEvenement", typeColumn = String.class)
    Evenement evenement;
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
    public void setPrix(String prix) throws Exception {
        setPrix(Double.parseDouble(prix));
    }
    public void setPromotion(Promotion promotion) {
        this.promotion = promotion;
    }
    public void setPlaces(Place[] places) {
        this.places = places;
    }
    public void setEvenement(Evenement evenement) {
        this.evenement = evenement;
    }

/// GETTERS 
    public String getIdZone() {
        return idZone;
    }
    public String getNom() {
        return nom;
    }
    public double getPrix() {
        return (getPromotion() == null) ? prix : getPromotion().getPourcentage() * prix;
    }
    public Promotion getPromotion() {
        return promotion;
    }
    public Place[] getPlaces() throws Exception {
        if (places == null) this.charger();
        return places;
    }
    public Evenement getEvenement() {
        return evenement;
    }

/// CONSTRUCTORS
    public Zone() {
        setTable("zone");
        setCountPK(7);
        setFunctionPK("nextval('seqzone')");
        setPrefix("ZO");
    }

    public Zone(String nom, double prix) throws Exception {
        this();
        setIdZone(buildPrimaryKey(getPostgreSQL()));
        setNom(nom);
        setPrix(prix);
    }

    public Zone(String nom, String prix) throws Exception {
        this();
        setIdZone(buildPrimaryKey(getPostgreSQL()));
        setNom(nom);
        setPrix(prix);
    }

/// FUNCTIONS
    public static Zone[] getAllZones() throws Exception {
        return new Zone().getData(getPostgreSQL(), null);
    }

    public void charger() throws Exception {
        Place place = new Place();
        place.setTable("place_zone");
        place.setZone(this);
        place.setEvenement(getEvenement());
        Place[] places = place.getData(getPostgreSQL(), "idPlace", "zone", "evenement");
        for (int i = 0; i < places.length; i++) {
            places[i].setZone(this);
        }
        setPlaces(places);
    }
}